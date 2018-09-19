package com.example.HelloWorldSpring;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class HelloWorldSpringApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloSpringConfig.class);
		HelloWorld helloWorld = ctx.getBean(HelloWorld.class);

		helloWorld.sayHello();
		SpringApplication.run(HelloWorldSpringApplication.class, args);
	}
}
