package com.example.ui.controllers;

import com.example.ui.client.EndOfLifeClient;
import com.example.ui.domain.Software;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EndOfLifeController {

    @Autowired
    EndOfLifeClient endOfLifeClient;

    @GetMapping("/")
    public String getListOfSoftWares(Model model) {
        String[] allProducts = endOfLifeClient.getAllProducts();
        model.addAttribute("products", allProducts);
        return "software-list";
    }

    @GetMapping("/get-all-details/{product}")
    public String getAllDetails(Model model, @PathVariable("product") String product) {

        String[] allProducts = endOfLifeClient.getAllProducts();
        model.addAttribute("products", allProducts);

        List<Software> allDetails= endOfLifeClient.getAllDetails(product);
        model.addAttribute("selectedProduct", product);
        model.addAttribute("productDetails", allDetails);
        return "software-list";
    }

    @GetMapping("/single-cycle-details/{product}/{cycle}")
    public String getSingleCycleDetails(Model model, @PathVariable("product") String product, @PathVariable("cycle") String cycle) {

        Software software = endOfLifeClient.getSingleCycleDetails(cycle, product);

        return "redirect: " + software.getLink() ;
    }
}
