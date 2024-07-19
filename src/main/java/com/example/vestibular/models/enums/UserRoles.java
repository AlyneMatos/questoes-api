package com.example.vestibular.models.enums;

public enum UserRoles {

    ADMIN("admim"),
    USER("user");

    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
