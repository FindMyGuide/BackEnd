package com.find_my_guide.main_member.member.repository;

import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomMemberRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Member> findGuidesByCriteria(Gender gender, LocalDate birthDate, Languages language, LocalDate date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> cq = cb.createQuery(Member.class);
        Root<Member> member = cq.from(Member.class);
        Join<Member, TourHistoryManager> tourHistory = member.join("tourHistoriesAsGuide");
        Join<TourHistoryManager, TourProduct> tourProduct = tourHistory.join("tourProduct");
        Join<TourProduct, AvailableDate> availableDates = tourProduct.join("availableDates");

        List<Predicate> predicates = new ArrayList<>();

        if (gender != null) {
            predicates.add(cb.equal(member.get("gender"), gender));
        }

        if (birthDate != null) {
            Expression<Integer> age = cb.diff(
                    cb.function("YEAR", Integer.class, cb.currentDate()),
                    cb.function("YEAR", Integer.class, member.get("birthDate"))
            );
            predicates.add(cb.equal(age, Period.between(birthDate, LocalDate.now()).getYears()));
        }

        if (language != null) {
            predicates.add(cb.isMember(language, tourProduct.get("languages")));
        }

        if (date != null) {
            predicates.add(cb.equal(availableDates.get("date"), date));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Member> query = em.createQuery(cq);
        return query.getResultList();
    }


}
