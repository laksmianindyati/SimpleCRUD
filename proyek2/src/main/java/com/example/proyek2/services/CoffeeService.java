package com.example.proyek2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.proyek2.models.Coffee;
import com.example.proyek2.repositories.CoffeeRepository;




@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> getAllData(){
        return coffeeRepository.getAllData();
    }

    public Coffee getCoffeeById(String id){
        return coffeeRepository.getCoffeeById(id);
    }

    public void addData(Coffee cf){
        coffeeRepository.create(cf);
    }

    public void updateData(String id, String name){
        coffeeRepository.update(id, name);
    }

    public void deletedata(String id){
        coffeeRepository.delete(id);
    }
    
}
