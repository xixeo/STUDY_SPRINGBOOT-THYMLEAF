package com.itwillbs.entity;

import java.sql.Timestamp;

import com.itwillbs.domain.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 엔티티 매핑 관련 어노테이션
//@Entity : 클래스 엔티티 선언
//@Table : 엔티티와 매핑할 테이블 지정
//@Id : 테이블에서 기본키 사용할 속성 지정
//@Column : 필드와 컬럼 매핑(멤버변수 테이블 열이름 매핑)
//name = "칼럼명", length=크기, nullable=false, unique
//       columnDefinition=varchar(5) 직접지정,insertable, updatable
//@GeneratedValue(strategy=GenerationType.AUTO) 키 값 생성, 자동으로 증가
//@Lob BLOB, CLOB 타입 매핑
//@CreateTimestamp insert 시 시간 자동 저장
//@Enumerated enum 타입 매핑

@Entity
@Table(name = "members")	
@Getter
@Setter
@ToString
public class Member {
	
	@Id
	@Column(name = "id", length = 10)
	private String id;
	
	@Column(name = "pass", nullable = false)
	private String pass;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date")
	private Timestamp date;
	
	public static Member setMemberEntity(MemberDTO memberDTO) {
		
		Member member = new Member();
		
		member.setId(memberDTO.getId());
		member.setPass(memberDTO.getPass());
		member.setName(memberDTO.getName());
		member.setDate(memberDTO.getDate());
		
		return member;
	}

}
