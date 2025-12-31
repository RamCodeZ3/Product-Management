package org.product.models;

public class UserModel {
    public int id;
    public String username;
    public String email;
    public String role;

    public UserModel(int id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
