package org.product.security;
import org.product.models.UserModel;

public class Session {
    private static UserModel currentUser;

    public void login(UserModel user){
        currentUser = user;
    }

    public UserModel getUser(){
        return currentUser;
    }

    public void logOut(){
        currentUser = null;
    }
}
