package com.yedam.app.board.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Service // AOP => @Transactional
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;
	
	@Autowired // 생성자 주입
	public BoardServiceImpl(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	@Override
	public List<BoardVO> boareList() {
		return boardMapper.selectBoardAll();
	}

	@Override
	public BoardVO boareInfo(BoardVO boardVO) {
		return boardMapper.selectBoardInfo(boardVO);
		
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		int result = boardMapper.insertBoardInfo(boardVO);
		return result == 1 ? boardVO.getBno() : -1;
	}

	@Override
	public Map<String, Object> updateBoard(BoardVO boardVO) {
		return null;
	}

	@Override
	public int deleteBoard(int boardVo) {
		return 0;
	}

}
