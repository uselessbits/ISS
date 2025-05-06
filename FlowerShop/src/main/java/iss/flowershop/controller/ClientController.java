package iss.flowershop.controller;

import iss.flowershop.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    @Autowired
    private FlowerService flowerService;

    @GetMapping("/client")
    public String showClientPage(Model model) {

        model.addAttribute("flowers", flowerService.getAllFlowers());
        return "client"; // Return the name of the client page template
    }

    @PostMapping("/client/buy")
    public String buyFlower(int flowerId, int quantity) {
        int flowerQty=flowerService.getFlower(flowerId).getQuantity();
        if(flowerQty < quantity)
            return "redirect:/client";
        flowerService.updateFlower(flowerId, flowerQty-quantity);
        return "redirect:/client";
    }

}
