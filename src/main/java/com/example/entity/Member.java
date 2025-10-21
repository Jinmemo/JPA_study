package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * @Entity : JPA가 관리하는 엔티티 클래스임을 나타냄
 * DB의 테이블과 매핑됨 (기본적으로 클래스 이름 = 테이블 이름)
 */
@Entity
public class Member {

    /*
     * @Id : 기본 키(PK) 지정
     * @GeneratedValue : 자동 증가 전략 사용 (DB의 시퀀스나 IDENTITY 사용)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 회원 이름 컬럼

    // JPA는 기본 생성자가 반드시 필요함 (public 또는 protected)
    public Member() {}

    // 편의상 이름을 바로 지정할 수 있는 생성자 추가
    public Member(String name) {
        this.name = name;
    }

    // Getter / Setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
