package com.transfoafrica.auth.config;

import com.transfoafrica.auth.entity.Role;
import com.transfoafrica.auth.entity.User;
import com.transfoafrica.auth.repository.RoleRepository;
import com.transfoafrica.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        for (Role.RoleName roleName : Role.RoleName.values()) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(Role.builder().name(roleName).build());
                logger.info("Role cree: {}", roleName);
            }
        }

        if (!userRepository.existsByUsername("admin")) {
            Set<Role> adminRoles = new HashSet<>();
            roleRepository.findByName(Role.RoleName.ROLE_ADMIN).ifPresent(adminRoles::add);
            roleRepository.findByName(Role.RoleName.ROLE_USER).ifPresent(adminRoles::add);

            User admin = User.builder()
                    .username("admin")
                    .email("admin@transfo-africa.com")
                    .password(passwordEncoder.encode("Admin@2026"))
                    .fullName("Administrateur Transfo Africa")
                    .isActive(true)
                    .roles(adminRoles)
                    .build();

            userRepository.save(admin);
            logger.info("Utilisateur admin cree - login: admin / mot de passe: Admin@2026");
        }
    }
}
