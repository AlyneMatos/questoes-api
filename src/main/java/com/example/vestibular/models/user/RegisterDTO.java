package com.example.vestibular.models.user;

import com.example.vestibular.models.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
