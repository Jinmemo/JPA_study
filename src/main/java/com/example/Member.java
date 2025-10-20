package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

	@Id
	@GeneratedValue
	// 아이디
	private Long id;
	
	// 이름
	private String name;
	
	public Member() {}
	
	public Member(String name) {
		this.name = name;
	}
	
	public Long getId() {return id;}
	public String getName() {return name;}
	public void setName(String Name) {this.name = name;}
}
