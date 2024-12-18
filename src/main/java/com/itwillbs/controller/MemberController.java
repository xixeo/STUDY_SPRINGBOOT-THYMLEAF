package com.itwillbs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.entity.Member;
import com.itwillbs.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RequiredArgsConstructor // 자동으로 알아서 객체 생성해준다.
@Controller
@Log
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/insert")
	public String insert() {
		return "/member/insert";
	}

	@PostMapping("/insert")
	public String insertPro(MemberDTO memberDTO) {
		log.info("post insert");
		log.info(memberDTO.toString());

		// memberService.메서드() 호출
		memberService.save(memberDTO);

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "/member/login";
	}

	@PostMapping("/login")
	public String loginPost(MemberDTO memberDTO, HttpSession session) {

		log.info("login");
		log.info(memberDTO.toString());

//		Member member = memberService.메서드() 호출
		Member member = memberService.findByIdAndPass(memberDTO);

		if (member != null) {
			// 로그인 성공 시, 세션에 id 저장
			session.setAttribute("id", member.getId());

			log.info("로그인 성공: " + member.getId());
			return "redirect:/main"; // 메인 페이지로 리다이렉트

		} else {
			// 로그인 실패 시, 로그인 페이지로 리다이렉트
			log.info("로그인 실패: 아이디 또는 비밀번호 불일치");
			return "redirect:/login"; // 로그인 페이지로 돌아가기
		}

	}

	// get main 가상주소 => /member/main
	@GetMapping("/main")
	public String main() {
		return "/member/main";
	}

	// get logout 가상주소 => 세션 초기화 => /login 이동
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/login";
	}

	// get /info 가상주소 => 세션값 가져오기 => memberService 조회 => model에 담아서 =>
	// /member/info.html 이동
	@GetMapping("/info")
	public String info(HttpSession session, Model model) {

		String id = (String) session.getAttribute("id");

		Optional<Member> member = memberService.findById(id);

		model.addAttribute("member", member.get());

		return "/member/info";
	}

	// get /update 가상주소 => 세션값 가져오기 => memberService 조회 => model에 담아서 =>
	// /member/update.html 이동
	@GetMapping("/update")
	public String update(HttpSession session, Model model) {

		String id = (String) session.getAttribute("id");

		Optional<Member> member = memberService.findById(id);

		model.addAttribute("member", member.get());

		return "/member/update";
	}

	// post /update 가상주소 => memberService.findByIdAndPass() =>
	// !=null 일치 => member setDate() => memberServic updateSave(member)
	// ==null 틀림 => /update 이동
	@PostMapping("/update")
	public String updateMember(MemberDTO memberDTO) {
		// memberDTO : update.html에서 수정한 정보가 저장

		Member member = memberService.findByIdAndPass(memberDTO);
		// member : 데이터 베이스에 저장된 정보

		if (member != null) {
//			save()메서드 이용 => update members set pass = ?, name = ?, date = ? where id = ?
//			member setDate(), memberService updateSave(memberDTO) => /main 이동	

			memberDTO.setDate(member.getDate());
			memberService.updateSave(memberDTO);

			return "redirect:/main";

		} else {
			return "/member/update";
		}

	}

	// Get /delete 가상주소 => /member/delete.html 이동
	@GetMapping("/delete")
	public String delete() {

		return "/member/delete";
	}

	// Post /delete 가상주소 => memberService.findByIdAndPass()
	// => != null 일치 => memberService deleteById(id) => 세션값 초기화 => main 이동
	// => == null => /delete 이동
	@PostMapping("/delete")
	public String deleteMember(MemberDTO memberDTO, HttpSession session) {
		//memberDTO : update.html 수정한 정보가 저장
		
		Member member = memberService.findByIdAndPass(memberDTO);
		//member : 데이터 베이스에 저장된 정보
		
		if (member != null) {
			
			memberService.deleteById(memberDTO.getId());
			session.invalidate();
	
			return "redirect:/login";

		} else {
			return "/member/delete";
		}
		
	}	
	
//	내가 한 방법
//	public String deleteMember(MemberDTO memberDTO, HttpSession session) {
//		
//		Member member = memberService.findByIdAndPass(memberDTO);
//		String id = (String) session.getAttribute("id");
//		
//		if (member != null) {
//			
//			memberService.deleteById(id);
//			session.invalidate();
//
//			return "redirect:/main";
//
//		} else {
//			return "/member/delete";
//		}
//		
//	}
	
	//get /list 가상주소 => memberService.findAll() => model memberList로 받기 => member/list.html 이동
	@GetMapping("/list")
	public String list(Model model) {

		List<Member> memberList = memberService.findAll();
		
		model.addAttribute("memberList", memberList);
		
		return "/member/list";
	}
}
