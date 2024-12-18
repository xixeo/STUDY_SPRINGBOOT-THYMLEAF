package com.itwillbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwillbs.entity.Board;

// extends JpaRepository<(Enitity), ID(기본키)>
// JpaRepository 지원하는 기본 메서드 제공
// save(Entity) :  엔티티 저장 및 수정
// void delete(Entity) : 엔티티 삭제
// void deleteById(id) : 엔티티 삭제
// count : 엔티티 총 개수 반환
// List<Entity> findAll() : 모든 엔티티 조회
// Entity findById(id) : id(기준키)에 대한 엔티티 조회

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
