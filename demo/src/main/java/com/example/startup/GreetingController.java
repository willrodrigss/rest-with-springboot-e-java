package com.example.startup;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class GreetingController {    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting") //mapeamento uma requisição para um método tornando um endereço HTTP acessível via browser
    public Greeting greeting(@RequestParam(value = "nome", defaultValue = "world") //RequestParam é usada para vincular query parameters
                            String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
