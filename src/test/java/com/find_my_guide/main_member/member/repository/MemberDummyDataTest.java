package com.find_my_guide.main_member.member.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.service.MemberService;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerDummyDataGenerator;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import com.find_my_guide.main_tour_product.tour_product_like.service.TourProductLikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
public class MemberDummyDataTest {

    @Autowired
    private MemberDummyDataGenerator dataGenerator;

    @Autowired
    private TourProductLikeRepository tourProductLikeRepository;
    @Autowired
    private TourProductLikeService productLikeService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TourHistoryManagerDummyDataGenerator tourHistoryManagerDummyDataGenerator;

    @Autowired private TourHistoryManagerRepository tourHistoryManagerRepository;
    @Autowired
    private TourProductRepository tourProductRepository;

    private final Random random = new Random();

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
    public void changeBumPassword(){
        Member member = memberRepository.findByEmail("tlfem3315@gmail.com").get();
        member.setPassword(passwordEncoder.encode("qpal123!@"));
        memberRepository.save(member);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void setEmail() {
        List<Member> all = memberRepository.findAll();
        all.forEach(member -> member.setEmailVerified(true));

        memberRepository.saveAll(all);
    }


    @Test
    @Transactional
    @Rollback(false)
    public void generateTourHistoryManagerDummyData() {
        List<Member> members = memberRepository.findAll();
        List<TourProduct> tourProducts = tourProductRepository.findAll();

        if (members.isEmpty() || tourProducts.isEmpty()) {
            return;
        }

        for (int i = 0; i < 38; i++) {
            Member randomGuide = getRandomMemberInRange(members, 42, 101);
            Member randomTourist;

            do {
                randomTourist = getRandomMemberInRange(members, 42, 101);
            } while (randomTourist.getIdx().equals(randomGuide.getIdx()));

            TourProduct randomTourProduct = tourProducts.get(random.nextInt(tourProducts.size()));
            TourHistoryManager tourHistoryManager = tourHistoryManagerDummyDataGenerator.generateRandomTourHistoryManager(randomGuide, randomTourProduct);
            tourHistoryManager.addGuide(randomGuide);

            tourHistoryManager.addTourist(randomTourist);
            tourHistoryManagerRepository.save(tourHistoryManager);
        }
    }

    private Member getRandomMemberInRange(List<Member> members, int lower, int upper) {
        Member selectedMember;
        do {
            selectedMember = members.get(random.nextInt(members.size()));
        } while (selectedMember.getIdx() < lower || selectedMember.getIdx() > upper);
        return selectedMember;
    }

}

//42~101  멤버
//148~224  투매히
