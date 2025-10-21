package com.example.basic;

import javax.persistence.*;
import com.example.entity.Member;

public class FlushClearTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member("현진");
            em.persist(member);
            em.flush(); // INSERT 즉시 반영
            em.clear(); // 1차 캐시 초기화

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("조회된 회원 이름: " + findMember.getName());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
