package org.product;

import org.product.dao.ProductDao;
import org.product.models.ProductModel;
import org.product.models.UserModel;
import org.product.service.AuthService;
import org.product.security.Session;
import org.product.dao.UserDao;

public class Main {
    public static void main(String[] args) {
        var dao = new ProductDao();
        var userDao = new UserDao();
        var auth  = new AuthService();
        var session = new Session();
        userDao.addUser("arammusset7@gmail.com", "Aram Musset", "12345678", "user");
        session.login(auth.login("arammusset7@gmail.com", "12345678"));
        UserModel user = session.getUser();


        if(user != null){
            System.out.println("usuario: " + user.username);
            System.out.println("email: " + user.email);
        }


    }
}