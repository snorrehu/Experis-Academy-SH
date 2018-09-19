package com.example.HelloWorldSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Map the java to a simple command in the browser!
@RestController
@RequestMapping("/greet") //put this in the browser
public class GreetController {
    @GetMapping
    public String getGreet(){
        //This will be shown in the browser!
        return "Hello from the api!";
    }
}
