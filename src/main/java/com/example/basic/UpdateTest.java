package com.example.basic;

import javax.persistence.*;
import com.example.entity.Member;

public class UpdateTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 1L);
            member.setName("현진(수정됨)");
            tx.commit(); // Dirty Checking → 자동 UPDATE 실행
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
