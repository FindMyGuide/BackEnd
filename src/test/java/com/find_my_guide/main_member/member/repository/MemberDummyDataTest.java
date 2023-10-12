package com.find_my_guide.main_member.member.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerDummyDataGenerator;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourProductDummyDataGenerator;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberDummyDataTest {

    @Autowired
    private MemberDummyDataGenerator dataGenerator;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TourHistoryManagerDummyDataGenerator tourHistoryManagerDummyDataGenerator;

    @Autowired
    private TourHistoryManagerRepository tourHistoryManagerRepository;

    @Autowired
    private TourProductDummyDataGenerator dummyDataGenerator;

    @Autowired
    private TourProductRepository tourProductRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void generateDummyMembersTest() {
        List<Member> dummyMembers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            dummyMembers.add(dataGenerator.generateRandomMember());
        }

        memberRepository.saveAll(dummyMembers);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void generateTourProductDummyData() {
        for (int i = 0; i < 20; i++) {
            TourProduct tourProduct = dummyDataGenerator.generateRandomTourProduct();
            tourProductRepository.save(tourProduct);
        }
    }


    public void generateTourHistoryManagerDummyData() {
        List<Member> guides = memberRepository.findAll();
        List<TourProduct> tourProducts = tourProductRepository.findAll();

        if (guides.isEmpty() || tourProducts.isEmpty()) {
            // Logging or throw an exception indicating not enough data to generate dummy data
            return;
        }

        for (int i = 0; i < 20; i++) {
            Member randomGuide = guides.get(new Random().nextInt(guides.size()));
            TourProduct randomTourProduct = tourProducts.get(new Random().nextInt(tourProducts.size()));
            TourHistoryManager tourHistoryManager = tourHistoryManagerDummyDataGenerator.generateRandomTourHistoryManager(randomGuide, randomTourProduct);
            tourHistoryManagerRepository.save(tourHistoryManager);
        }
    }
}