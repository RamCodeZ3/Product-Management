package org.product.models;

public class UserModel {
    public int id;
    public String username;
    public String email;
    private String password;
    public String role;

    public UserModel(int id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
