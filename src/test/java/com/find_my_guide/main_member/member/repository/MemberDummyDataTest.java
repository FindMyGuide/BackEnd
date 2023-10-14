//package com.find_my_guide.main_member.member.repository;
//
//import com.find_my_guide.main_member.member.domain.entity.Member;
//import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
//import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerDummyDataGenerator;
//import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
//import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourProductDummyDataGenerator;
//import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
//import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
//import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
//import com.find_my_guide.main_tour_product.tour_product_like.service.TourProductLikeService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//@SpringBootTest
//public class MemberDummyDataTest {
//
//    @Autowired
//    private MemberDummyDataGenerator dataGenerator;
//
//    @Autowired
//    private TourProductLikeRepository tourProductLikeRepository;
//    @Autowired
//    private TourProductLikeService productLikeService;
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private TourHistoryManagerDummyDataGenerator tourHistoryManagerDummyDataGenerator;
//
//    @Autowired
//    private TourHistoryManagerRepository tourHistoryManagerRepository;
//
//    @Autowired
//    private TourProductDummyDataGenerator dummyDataGenerator;
//
//    @Autowired
//    private TourProductRepository tourProductRepository;
//
//    private final Random random = new Random();
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void generateDummyMembersTest() {
//        List<Member> dummyMembers = new ArrayList<>();
//
//        for (int i = 0; i < 20; i++) {
//            dummyMembers.add(dataGenerator.generateRandomMember());
//        }
//
//        memberRepository.saveAll(dummyMembers);
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void generateTourProductLikeDummy() {
//        // 특정 이메일로 멤버 찾기
//        Optional<Member> optionalMember = memberRepository.findByEmail("tlfem3315@naver.com");
//
//        // 멤버가 존재하지 않으면 종료
//        if (!optionalMember.isPresent()) {
//            System.out.println("해당 이메일을 가진 멤버가 없습니다.");
//            return;
//        }
//
//        Member member = optionalMember.get();
//
//        // 모든 TourProduct 조회
//        List<TourProduct> allTourProducts = tourProductRepository.findAll();
//
//        // ID 범위 설정
//        Long startId = 15L;
//        Long endId = 100L;
//
//        List<TourProduct> tourProductsToLike = new ArrayList<>();
//
//        // ID 범위에 따른 TourProduct 필터링
//        for (TourProduct tourProduct : allTourProducts) {
//            if (tourProduct.getTourProductId() >= startId && tourProduct.getTourProductId() <= endId) {
//                tourProductsToLike.add(tourProduct);
//            }
//        }
//
//        // 각각의 TourProduct에 대해 좋아요를 추가
//        for (TourProduct tourProduct : tourProductsToLike) {
//            // 이미 좋아요를 눌렀는지 확인
//            Optional<TourProductLike> optionalLike = tourProductLikeRepository.findByMemberAndTourProduct(member, tourProduct);
//            if (!optionalLike.isPresent()) { // 아직 좋아요를 누르지 않았다면
//                TourProductLike like = TourProductLike.builder()
//                        .member(member)
//                        .tourProduct(tourProduct)
//                        .build();
//                tourProductLikeRepository.save(like);
//            }
//        }
//    }
//
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void setEmail() {
//        List<Member> all = memberRepository.findAll();
//        all.forEach(member -> member.setEmailVerified(true));
//
//        memberRepository.saveAll(all);
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void generateTourProductDummyData() {
//        for (int i = 0; i < 20; i++) {
//            TourProduct tourProduct = dummyDataGenerator.generateRandomTourProduct();
//            tourProductRepository.save(tourProduct);
//        }
//    }
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void generateTourHistoryManagerDummyData() {
//        List<Member> members = memberRepository.findAll();
//        List<TourProduct> tourProducts = tourProductRepository.findAll();
//
//        if (members.isEmpty() || tourProducts.isEmpty()) {
//            return;
//        }
//
//        for (int i = 0; i < 38; i++) {
//            Member randomGuide = getRandomMemberInRange(members, 42, 101);
//            Member randomTourist;
//
//            do {
//                randomTourist = getRandomMemberInRange(members, 42, 101);
//            } while (randomTourist.getIdx().equals(randomGuide.getIdx()));
//
//            TourProduct randomTourProduct = tourProducts.get(random.nextInt(tourProducts.size()));
//            TourHistoryManager tourHistoryManager = tourHistoryManagerDummyDataGenerator.generateRandomTourHistoryManager(randomGuide, randomTourProduct);
//            tourHistoryManager.addGuide(randomGuide);
//
//            tourHistoryManager.addTourist(randomTourist);
//            tourHistoryManagerRepository.save(tourHistoryManager);
//        }
//    }
//
//    private Member getRandomMemberInRange(List<Member> members, int lower, int upper) {
//        Member selectedMember;
//        do {
//            selectedMember = members.get(random.nextInt(members.size()));
//        } while (selectedMember.getIdx() < lower || selectedMember.getIdx() > upper);
//        return selectedMember;
//    }
//
//}
//
////42~101  멤버
////148~224  투매히
