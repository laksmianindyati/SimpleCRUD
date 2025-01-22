package com.example.proyek2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.proyek2.models.Coffee;
import com.example.proyek2.services.CoffeeService;


@Controller
@RequestMapping("/view")
public class CoffeeDBViewController {

    
    @Autowired
    private CoffeeService coffeeService;


    @GetMapping("/all")
    public String getAllCoffees(Model model){
        List<Coffee> coffees=coffeeService.getAllData();
        model.addAttribute("coffees",coffees);
        return"viewcoffees";
    }

 
    @PostMapping("/all")
    public RedirectView createDataCoffee(RedirectAttributes redirectAttributes, @ModelAttribute("coffee") Coffee coffee) {
        System.out.println("Masuk ke dalam post mapping");
        
        String postMsg;
        coffeeService.addData(coffee);
        /*postMsg = String.format("Created data coffee <b>%s %s</b> ✨.", coffee.getId(), coffee.getName()); */
        postMsg = "Created data coffee ID : <b>" + coffee.getId() + "</b> NAME :  <b>" + coffee.getName()+ "</b> ✨.";

        redirectAttributes.addFlashAttribute("postMsg", postMsg);

        return new RedirectView("/view/all", true); 

    }


    @GetMapping("/edit/{id}")
    public String getCoffee(Model model,  @PathVariable String id) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        model.addAttribute("coffee", coffee);
        
        return "editcoffees";
         
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteCoffee(RedirectAttributes redirectAttributes, @ModelAttribute("coffee") Coffee coffee,  @PathVariable String id) {
        String postMsg;
        System.out.println(coffee.getId());
        coffeeService.deletedata(id);
        
        postMsg = "delete success!";

        redirectAttributes.addFlashAttribute("postMsg", postMsg);

        return new RedirectView("/view/all", true); 
         
    }

    @PostMapping("/edit/{id}")
    public RedirectView UpdateCpffe (@RequestParam("buttonValue") String buttonValue, RedirectAttributes redirectAttributes, @ModelAttribute("coffee") Coffee coffee) {

        if(buttonValue.equalsIgnoreCase("edit")){

            return putCoffee(redirectAttributes, coffee.getId(), coffee);
        }else{

            return new RedirectView("/view/all");
        }
    }

    @PutMapping("/edit/{id}")
    public RedirectView putCoffee(RedirectAttributes redirectAttributes, @PathVariable String id, @ModelAttribute("coffee") Coffee coffee){
        String postMsg;

        coffeeService.updateData(id, coffee.getName());
        
        postMsg = "success update data coffee ID : <b>" + coffee.getId() + "</b>  NAME :  <b>" + coffee.getName()+ "</b> ✨.";

        redirectAttributes.addFlashAttribute("postMsg", postMsg);

        return new RedirectView("/view/edit/{id}", true); 
    }

}
