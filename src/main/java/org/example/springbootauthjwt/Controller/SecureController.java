package org.example.springbootauthjwt.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/secure")
    public String secure(){
        return "This is a secure endpoint. You are authenticated";

    }
}
