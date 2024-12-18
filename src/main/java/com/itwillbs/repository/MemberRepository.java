package com.itwillbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwillbs.entity.Member;

// extends JpaRepository<(Enitity), ID(기본키)>
// JpaRepository 지원하는 기본 메서드 제공
// save(Entity) :  엔티티 저장 및 수정
// void delete(Entity) : 엔티티 삭제
// void deleteById(id) : 엔티티 삭제
// count : 엔티티 총 개수 반환
// List<Entity> findAll() : 모든 엔티티 조회
// Entity findById(id) : id(기준키)에 대한 엔티티 조회

// 쿼리 메서드 정의
// 아이디 비밀번호 조회 : findByIdAndPass(id,pass) => where id = ? and pass = ?
//					 findByIdOrPass(id,pass) => where id = ? or pass = ?
//					 findByNumBetween() => where num between ? and ?
public interface MemberRepository extends JpaRepository<Member, String>{

//	select * from members where id =  and pass = ?
//	Hibernate: 
//	    select
//	        m1_0.id,
//	        m1_0.date,
//	        m1_0.name,
//	        m1_0.pass 
//	    from
//	        members m1_0 
//	    where
//	        m1_0.id=? 
//	        and m1_0.pass=?
	
	Member findByIdAndPass(String id, String Pass);
}
