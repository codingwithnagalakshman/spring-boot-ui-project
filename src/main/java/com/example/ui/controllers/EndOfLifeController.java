package com.example.ui.controllers;

import com.example.ui.client.EndOfLifeClient;
import com.example.ui.domain.Software;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class EndOfLifeController {

    Logger logger = LoggerFactory.getLogger(EndOfLifeController.class);

    @Autowired
    EndOfLifeClient endOfLifeClient;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        logger.info("Health check method called");
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/")
    public String getListOfSoftWares(Model model) {
        logger.info("accessed get all products");
        String[] allProducts = endOfLifeClient.getAllProducts();
        model.addAttribute("products", allProducts);
        return "software-list";
    }

    @GetMapping("/get-all-details/{product}")
    public String getAllDetails(Model model, @PathVariable("product") String product) {

        logger.info("accessed product by product version");
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
