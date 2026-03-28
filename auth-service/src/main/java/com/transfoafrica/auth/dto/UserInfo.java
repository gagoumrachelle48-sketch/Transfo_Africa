package com.transfoafrica.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private Boolean isActive;
    private Set<String> roles;
}
