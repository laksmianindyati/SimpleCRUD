package com.example.proyek2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyek2.models.Coffee;
import com.example.proyek2.services.CoffeeService;


@RestController
@RequestMapping("/coffees")
public class CoffeeDBController {

    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/alldata")
    public List<Coffee> geCoffees() {
        
        return coffeeService.getAllData();
    }

    @GetMapping("/alldata/{id}")
    public Coffee geCoffeesbyid(@PathVariable String id) {
        return coffeeService.getCoffeeById(id);
    }

    @PostMapping("/insert")
    public void postCoffee(@RequestBody Coffee coffee) {
       
        System.out.println("Test controller"+ coffee.getId() + coffee.getName());
        coffeeService.addData(coffee);

    }

    @PutMapping("/update/{id}")
    public void putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        coffeeService.updateData(id, coffee.getName());
    }
    
    @DeleteMapping("/delete/{id}")
    public void Coffee(@PathVariable String id){
        coffeeService.deletedata(id);
    }
    
}
