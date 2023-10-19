package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoSecurityCustomUserApplicationTests {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void contextLoads() {
    }
    @Test
    void save_user() {
        // get role by name
        Role roleAdmin = roleRepository.findByName("ADMIN").get();
        Role roleUser = roleRepository.findByName("USER").get();

        // create user
        User userAdmin = new User();
        userAdmin.setName("Duy Hiếu");
        userAdmin.setEmail("hieu1852002@gmail.com");
        userAdmin.setPassword(passwordEncoder.encode("1234"));
        userAdmin.getRoles().add(roleAdmin);
        userAdmin.getRoles().add(roleUser);

        User userUser = new User();
        userUser.setName("Đặng Thơm");
        userUser.setEmail("thomtho@gmail.com");
        userUser.setPassword(passwordEncoder.encode("123zz"));
        userUser.getRoles().add(roleUser);

        // save user
        userRepository.save(userAdmin);
        userRepository.save(userUser);
    }

}
