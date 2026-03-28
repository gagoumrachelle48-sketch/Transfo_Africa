package com.transfoafrica.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {

    @NotBlank(message = "Le nom d utilisateur est requis")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "L email est requis")
    @Email(message = "Format d email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank(message = "Le nom complet est requis")
    private String fullName;

    private Set<String> roles;
}
