package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.entity.Board;
import com.itwillbs.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Service
@RequiredArgsConstructor
@Log
public class BoardService {

	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO) {

		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));

		Board board = Board.setBoardEntity(boardDTO);

		boardRepository.save(board);
	}

	public List<Board> getBoardList() {
		return boardRepository.findAll();
	}

	public Optional<Board> findById(Integer num) {
		return boardRepository.findById(num);
	}

	public void updatesave(BoardDTO boardDTO) {
		
		Board board = Board.setBoardEntity(boardDTO);
		
		boardRepository.save(board);
		
	}

	public void deleteById(Integer num) {
		
		 boardRepository.deleteById(num);	
		
	}
}
