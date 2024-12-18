package com.itwillbs.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberDTO;

@RestController
public class RestTestController {
	
	//가상주소:   http://localhost:8080/rTest
	
	@GetMapping("/rTest1")
	public String rTest1() {
		return "Hello, Spring Boot ~";
	}

	@GetMapping("/rTest2")
	public MemberDTO rTest2() {		

		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test3");
		memberDTO.setPass("123");
		memberDTO.setName("백이서");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		
		return memberDTO;
		
	}
	
	@GetMapping("/rTest3")
	public List<MemberDTO> rTest3() {		

		List<MemberDTO> memberList = new ArrayList<>();
			
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("test1");
		memberDTO.setPass("123");
		memberDTO.setName("백이서");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO2 = new MemberDTO();
		memberDTO2.setId("test2");
		memberDTO2.setPass("123");
		memberDTO2.setName("장두현");
		memberDTO2.setDate(new Timestamp(System.currentTimeMillis()));
		
		MemberDTO memberDTO3 = new MemberDTO();
		memberDTO3.setId("test3");
		memberDTO3.setPass("123");
		memberDTO3.setName("백민경");
		memberDTO3.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberList.add(memberDTO);
		memberList.add(memberDTO2);
		memberList.add(memberDTO3);
			
		return memberList;
	}
}
