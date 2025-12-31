package org.product.dao;
import org.mindrot.jbcrypt.BCrypt;
import org.product.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.product.models.UserModel;

public class UserDao {

    public List<UserModel> getAllUsers() {
        var users = new ArrayList<UserModel>();
        try(Connection connection = ConnectionDB.connection();
            Statement statement = connection.createStatement()
        ){
            ResultSet rs = statement.executeQuery("SELECT * FROM users");

            while(rs.next()){
                var user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                users.add(user);
            }

        } catch (Exception e){
            throw new RuntimeException("Hubo un error obtiendo los usuarios.",e);
        }
        return users;
    }


    public UserModel getUserById(int id){
        try(Connection connection = ConnectionDB.connection();
            Statement statement = connection.createStatement()
        ){
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE id = " + id);

            if (rs.next()){
                return new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
            } else throw new RuntimeException("Usuario no encontrado");

        } catch (Exception e){
            throw new RuntimeException("Hubo un error obtiendo al usuario.",e);
        }
    }


    public void addUser(String email, String username, String password, String role){
        String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";

        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
            pstmt.setString(1, username);
            pstmt.setString(2,  email);
            pstmt.setString(3, BCrypt.hashpw(password, BCrypt.gensalt()));
            pstmt.setString(4, role);
            pstmt.executeUpdate();

            System.out.println("Se agrego un usuario con exito");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error agregando al usuario.",e);
        }
    }


    public void updateUserById(UserModel user){
        String sql = "UPDATE users SET username = ?, email = ?, role WHERE id = ?";
        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
            pstmt.setString(1, user.username);
            pstmt.setString(2,  user.email);
            pstmt.setString(3, user.role);
            pstmt.setInt(4, user.id);
            pstmt.executeUpdate();

            System.out.println("Se actualizo al usuario con exito");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error actualizando al usuario.", e);
        }
    }


    public void deleteUserById(int id){
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Se elimino al usuario con exito");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error eliminando al usuario.", e);
        }
    }
}
