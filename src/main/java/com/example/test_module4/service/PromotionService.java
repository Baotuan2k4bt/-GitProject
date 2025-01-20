package com.example.test_module4.service;

import com.example.test_module4.model.Promotion;
import com.example.test_module4.repository.IPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PromotionService implements IPromotionService {
@Autowired
private IPromotionRepository promotionRepository;

    @Override
    public List<Promotion> findAll() {
        return promotionRepository.findAll();

    }

    @Override
    public void deleteById(String title ) {
        promotionRepository.delete(title);
    }

    @Override
    public void save(Promotion promotion) {
        promotionRepository.save(promotion);


    }

    @Override
    public Promotion getById(Integer id) {
        if (!promotionRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tìm thấy");
        }
        return promotionRepository.findById(id).get();
    }
    @Override
    public List<Promotion> searchPromotions(Double discount, LocalDate startDate, LocalDate endDate) {
        return promotionRepository.searchPromotions(discount, startDate, endDate);
    }
}
