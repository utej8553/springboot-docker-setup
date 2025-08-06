package org.example.springbootauthjwt.Controller;

import org.example.springbootauthjwt.Entity.UserEntity;
import org.example.springbootauthjwt.Util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> userStore = new HashMap<>();


    public AuthController(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/signup")
    public String signup(@RequestBody UserEntity userEntity) {
        if(userStore.containsKey(userEntity.getUsername())) {
            return "User already exists";
        }
        String encodededPassword = passwordEncoder.encode(userEntity.getPassword());
        userStore.put(userEntity.getUsername(), encodededPassword);
        return "User registered successfully";
    }
    @PostMapping("/login")
    public String login(@RequestBody UserEntity userEntity) {
        String storedPassword = userStore.get(userEntity.getUsername());
        if(storedPassword != null && passwordEncoder.matches(userEntity.getPassword(), storedPassword)) {
            return jwtUtil.generateToken(userEntity.getUsername());
        }
        return "Invalid username or password";
    }
}
