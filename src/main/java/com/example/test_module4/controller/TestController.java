package com.example.test_module4.controller;

import com.example.test_module4.model.Promotion;
import com.example.test_module4.service.IPromotionService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {
    @Autowired
    private IPromotionService ipromotionService;

    @GetMapping
    public String index(Model model) {
        List<Promotion> list=ipromotionService.findAll();
        model.addAttribute("list", list);
        return "index";

    }
    @GetMapping("delete")
    public String delete(@RequestParam("title") String title) {
        ipromotionService.deleteById(title);
        return "redirect:/";
    }
    @GetMapping("/add")
    public String showAddPromotionForm(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "add";
    }

    @PostMapping("/add")
    public String addPromotion(@Valid @ModelAttribute("promotion") org.example.cinemamanagement.model.PromotionDTO promotionDTO, BindingResult bindingResult, Model model) {
        new org.example.cinemamanagement.model.PromotionDTO().validate(promotionDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("promotion", promotionDTO);
            return "add";
        }
        Promotion promotion = new Promotion();
        BeanUtils.copyProperties(promotionDTO, promotion);
        ipromotionService.save(promotion);
        return "redirect:/promotions";
    }
    @GetMapping("/edit/{id}")
    public String showEditPromotionForm(@PathVariable Integer id, Model model) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

        Promotion promotion = ipromotionService.getById(id);
        if (promotion == null) {
            throw new IllegalArgumentException(" not found with ID: " + id);
        }

        model.addAttribute("promotion", promotion);
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editPromotion(@PathVariable Integer id, @ModelAttribute Promotion promotion) {
        promotion.setId(id);
        ipromotionService.save(promotion);
        return "redirect:/";
    }
    @GetMapping("/search")
    public String searchPromotions(@RequestParam(required = false) Double discount,
                                   @RequestParam(required = false) LocalDate startDate,
                                   @RequestParam(required = false) LocalDate endDate,
                                   Model model) {
        List<Promotion> list = ipromotionService.searchPromotions(discount, startDate, endDate);
        model.addAttribute("list", list);
        return "index";
    }

}
