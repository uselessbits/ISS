package iss.flowershop;

import iss.flowershop.model.Flower;
import iss.flowershop.model.Role;
import iss.flowershop.model.User;
import iss.flowershop.persistance.FlowerRepository;
import iss.flowershop.persistance.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FlowerShopApplication {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, FlowerRepository flowerRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create admin user
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));  // Always encode passwords!
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);

            // Create customer user
            User customer = new User();
            customer.setUsername("user");
            customer.setPassword(passwordEncoder.encode("user"));
            customer.setRole(Role.CUSTOMER);
            userRepository.save(customer);

            // Create flowers with quantities
            Flower flower1 = new Flower("Rose", 10.0f, 5);
            Flower flower2 = new Flower("Tulip", 8.0f, 7);
            Flower flower3 = new Flower("Daisy", 5.0f, 10);
            flowerRepository.save(flower1);
            flowerRepository.save(flower2);
            flowerRepository.save(flower3);


            System.out.println("Flowers created successfully!");
            System.out.println("Users created successfully!");
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(FlowerShopApplication.class, args);

        System.out.println("Flower Shop Application is running!");
    }

}
