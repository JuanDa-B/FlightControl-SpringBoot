package com.app.rest.controller.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }

    @GetMapping("/reservas")
    public String reservas(){
        return "reservas";
    }

    @GetMapping("/vuelos")
    public String vuelos(){
        return "vuelos";
    }

    @GetMapping("/asientos")
    public String asientos(){
        return "asientos";
    }







}
