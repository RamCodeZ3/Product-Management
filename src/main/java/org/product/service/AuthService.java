package org.product.service;

import org.mindrot.jbcrypt.BCrypt;
import org.product.config.ConnectionDB;
import org.product.models.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {
    public UserModel login(String email, String password){
        String sql = "SELECT * FROM users WHERE email = ?";

        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
            pstmt.setString(1,  email);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Usuario no existe");
            }

            String hash = rs.getString("password");

            if (!BCrypt.checkpw(password, hash)){
                throw new RuntimeException("Contraseña incorrecta");
            }

            return new UserModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("role")
            );


        }catch (Exception e){
            throw new RuntimeException("Hubo un error iniciando session.",e);
        }
    }
}
