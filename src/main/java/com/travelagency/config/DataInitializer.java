package com.travelagency.config;

import com.travelagency.entity.*;
import com.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // Créer un utilisateur admin par défaut si aucun n'existe
        if (!userService.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@travel.com");
            admin.setFirstName("Admin");
            admin.setLastName("System");
            admin.setPhone("0123456789");
            admin.setGender(Gender.HOMME);
            admin.setRoles(Set.of(Role.ADMIN));

            userService.save(admin);
            System.out.println("✅ Utilisateur admin créé : admin/admin123");
        }

        // Créer un utilisateur client par défaut si aucun n'existe
        if (!userService.existsByUsername("client")) {
            User client = new User();
            client.setUsername("client");
            client.setPassword("client123");
            client.setEmail("client@travel.com");
            client.setFirstName("Jean");
            client.setLastName("Dupont");
            client.setPhone("0987654321");
            client.setGender(Gender.HOMME);
            client.setRoles(Set.of(Role.CLIENT));

            userService.save(client);
            System.out.println("✅ Utilisateur client créé : client/client123");
        }
    }
}
