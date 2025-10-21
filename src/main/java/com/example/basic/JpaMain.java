package com.example.basic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.example.entity.Member;

public class JpaMain {

    public static void main(String[] args) {
        // 1. persistence.xml에 등록된 "hello" 설정을 기반으로 EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 2. 실제 DB와 상호작용을 담당하는 EntityManager 생성
        EntityManager em = emf.createEntityManager();

        // 3. 트랜잭션 시작 (JPA의 모든 데이터 변경은 트랜잭션 안에서 처리해야 함)
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /*
             * ====== 1. 데이터 저장 (CREATE) ======
             * 새로운 Member 엔티티를 생성하고 persist()로 영속성 컨텍스트에 등록하면
             * 트랜잭션 커밋 시점에 DB에 INSERT 쿼리가 실행됨
             */
            Member member = new Member("현진");
            em.persist(member);

            /*
             * ====== 2. 데이터 조회 (READ) ======
             * find(엔티티클래스, 기본키) 형태로 조회 가능.
             * SELECT 쿼리가 실행되어 DB에서 데이터를 가져옴.
             */
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("조회된 회원 이름: " + findMember.getName());

            /*
             * ====== 3. 데이터 수정 (UPDATE) ======
             * 조회된 엔티티의 필드를 변경하면 JPA가 자동으로 변경 감지(Dirty Checking) 후
             * 트랜잭션 커밋 시점에 UPDATE 쿼리를 실행함.
             */
            findMember.setName("현진(수정됨)");

            /*
             * ====== 4. 데이터 삭제 (DELETE) ======
             * remove()로 삭제 요청을 하면, 트랜잭션 커밋 시점에 DELETE 쿼리가 실행됨.
             */
			/* em.remove(findMember); */

            // 5. 모든 작업을 정상적으로 마쳤다면 커밋 (DB 반영)
            tx.commit();

        } catch (Exception e) {
            // 예외 발생 시 트랜잭션 롤백 (DB에 반영하지 않음)
            tx.rollback();
            e.printStackTrace();
        } finally {
            // EntityManager와 Factory 종료 (자원 해제)
            em.close();
            emf.close();
        }
    }
}
