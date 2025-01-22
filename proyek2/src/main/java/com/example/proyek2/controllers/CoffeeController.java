package com.example.proyek2.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.proyek2.models.Coffee;



@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private List<Coffee> coffees = new ArrayList<>();

    public CoffeeController() {
        coffees.addAll(List.of(new Coffee("Cafe Gerenza"), 
        new Coffee("Cafe Ganador"), 
        new Coffee("Cafe Lareno"), 
        new Coffee("Cafe Tres Pontas")));
    }

    @GetMapping
    Iterable<Coffee> getCoffees(){
        return coffees;
    } 

    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        Coffee c;
        for(int i = 0; i < coffees.size(); i++){
            c = coffees.get(i);
            if(c.getId().equals(id)){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    } 

    @PostMapping("/add")
    Coffee postCoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/edit/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeIndex = -1;
        int i = 0;
        while(i < coffees.size() && coffeIndex < 0 ){
            Coffee c = coffees.get(i);
            if(c.getId().equals(id)){
                coffeIndex = coffees.indexOf(c);
                coffees.set(coffeIndex, coffee);
            }
            i++;
        }
        return (coffeIndex == -1 ? postCoffee(coffee) : coffee );
    }


    @DeleteMapping("/hapus/{id}")
    public void deleteUser(@PathVariable String id) {
        // Logic to delete the user with the given ID

        coffees.removeIf(c -> c.getId().equals(id));

    }
}
