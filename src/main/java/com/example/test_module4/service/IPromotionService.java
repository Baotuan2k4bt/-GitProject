package com.example.test_module4.service;

import com.example.test_module4.model.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionService {
    List<Promotion> findAll();
    void deleteById(String title);
    void save(Promotion promotion);
    Promotion getById(Integer id);
    List<Promotion> searchPromotions(Double discount, LocalDate startDate, LocalDate endDate);
}
