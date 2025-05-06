package iss.flowershop.controller;


import iss.flowershop.model.Flower;
import iss.flowershop.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private FlowerService flowerService;

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("flowers", flowerService.getAllFlowers());
        return "admin";
    }
    @PostMapping("/admin/changeQuantity")
    public String changeFlowerQuantity(@RequestParam int flowerId, @RequestParam int quantity) {
        if(quantity == 0)
            flowerService.deleteFlower(flowerId);
        else
            flowerService.updateFlower(flowerId, quantity);
        return "redirect:/admin";
    }
    @PostMapping("/admin/addFlower")
    public String addFlower(@RequestParam String name, @RequestParam int quantity, @RequestParam float price) {
        Flower flower = new Flower();
        flower.setName(name);
        flower.setQuantity(quantity);
        flower.setPrice(price);
        flowerService.addFlower(flower);
        return "redirect:/admin";
    }
}
