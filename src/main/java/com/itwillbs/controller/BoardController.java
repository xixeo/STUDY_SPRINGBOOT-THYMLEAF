package com.itwillbs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.entity.Board;
import com.itwillbs.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Controller
@RequiredArgsConstructor
public class BoardController {

	
	private final BoardService boardService;
	
	//get /boardwrite 가상주소 => /borad/writhe.html 이동
	@GetMapping("/boardwrite")
	public String boardwrite() {
		return "/board/write";
	}
	
//	post /boardwrite 가상주소 => 게시판 글쓰기 BoardDTO 전달받아서 => boardService.save(boardDTO)호출 
//	=> readcount=0, 오늘 날짜 => setBoardEntity에 삽입 => boardRepository.save() 메소드 호출
	@PostMapping("/boardwrite")
	public String boardwrite(BoardDTO boardDTO) {
		
		boardService.save(boardDTO);

		return "redirect:/boardlist";
	}	
	//get /boardlist 가상주소 => /board/list.html 이동
	@GetMapping("/boardlist")
	public String boardlist(Model model) {
		
		List<Board> boardList = boardService.getBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "/board/list";
	}
	
//	http://localhost:8080/boardcontent?num=1
//	get /boardcontent 가상주소 => (@RequestParam("num") int num) 
//	 => Optional<Board> boardService.findById(num)
//	 => model에 담아서 board => /board/content.html 이동
	@GetMapping("/boardcontent")
	public String boardcontent(@RequestParam("num") Integer num, Model model) {
		
		
		Optional<Board> board = boardService.findById(num);
		
		model.addAttribute("board", board.get());
			
		
		return "/board/content";
	}
	
	
//	get /boardupdate 가상주소 =>  (@RequestParam("num") int num) 
//	=> Optional<Board> board = boardService.findById(num)
//	=> model에 담아서 =>/board/update.html 이동
	@GetMapping("/boardupdate")
	public String boardupdate(@RequestParam("num") Integer num, Model model) {
		
		Optional<Board> board = boardService.findById(num);
		model.addAttribute("board", board.get());
		
		return "/board/update";
	}
	
//	post /boardupdate 가상주소
//	=> 게시판 글쓰기 BoardDTO 전달받아서
//	=> boardService.updatesave(boardDTO) 호출
//	=> setBoardEntity
//	=> boardRepository.save() 메서드 호출
	
	@PostMapping("/boardupdate")
	public String boardUpdate(BoardDTO boardDTO) {
		
		boardService.updatesave(boardDTO);
		
		return "redirect:/boardlist";
	}
}
