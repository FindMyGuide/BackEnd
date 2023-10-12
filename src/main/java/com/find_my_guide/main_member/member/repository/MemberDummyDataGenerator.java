package com.find_my_guide.main_member.member.repository;

import com.find_my_guide.main_member.member.domain.dto.CreateMemberRequest;
import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class MemberDummyDataGenerator {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final Random random = new Random();

    public Member generateRandomMember() {
        String name = "User" + random.nextInt(100);
        String email = "user" + random.nextInt(100) + "@example.com";
        String password = "Password123!";
        String nickname = "Nick" + random.nextInt(100);
        String nationality = randomNationality();
        String gender = randomGender().name();
        String birthDate = "199" + random.nextInt(10) + "-01-01";
        String phoneNumber = "010-1234-" + String.format("%04d", random.nextInt(10000));
        String nationalCertificationOfGuideYn = random.nextBoolean() ? "Y" : "N";
        String guideProfilePicture = "profilePic" + random.nextInt(10) + ".jpg";
        String guideIntroduction = "Hello, I'm guide " + nickname;
        Integer guideExperience = random.nextInt(20);

        CreateMemberRequest request = new CreateMemberRequest(name, email, password, nickname, nationality, gender, birthDate, phoneNumber,
                nationalCertificationOfGuideYn, guideProfilePicture, guideIntroduction, guideExperience, null);

        return request.toMember(passwordEncoder);
    }

    private String randomNationality() {
        String[] countries = {"South Korea", "USA", "UK", "Australia", "Germany", "France", "Italy", "Japan", "China", "Russia"};
        return countries[random.nextInt(countries.length)];
    }

    private Gender randomGender() {
        return Gender.values()[random.nextInt(Gender.values().length)];
    }
}
