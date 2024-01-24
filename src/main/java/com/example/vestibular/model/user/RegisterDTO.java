package com.example.vestibular.model.user;

import com.example.vestibular.model.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
