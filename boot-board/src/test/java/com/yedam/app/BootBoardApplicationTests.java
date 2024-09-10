package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class BootBoardApplicationTests {
	@Autowired
	private BoardMapper boardMapper;
	
	//@Test
	void boardList() {
		// 전체조회
		List<BoardVO> list = boardMapper.selectBoardAll();
		assertTrue(!list.isEmpty());
	}
	
	//@Test
	void boardInfo() {
		// 단건조회
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1001);
		
		BoardVO findVO = boardMapper.selectBoardInfo(boardVO);
		assertEquals(findVO.getBno(), 1001);
	}
	
	/*@Test
	void boardInsert() {
		// 등록
		BoardVO boardVO = BoardVO.builder()
				.title("Forst board")
				.writer("Tester")
				.regdate(new Date())
				.build();
		int result = boardMapper.insertBoardInfo(boardVO);
		assertEquals(result, 1);
	}*/
	
	//@Test
	void boardInsert() {
		// 등록
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("Forst board");
		boardVO.setWriter("Tester");
		boardVO.setRegdate(new Date());
		
		int result = boardMapper.insertBoardInfo(boardVO);
		assertEquals(result, 1);
	}
	
	@Test
	void boardUpdate() {
		// 수정
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1001);
		boardVO.setTitle("test");
		boardVO.setContents("test");
		boardVO.setWriter("tst");
		boardVO.setImage("test");
	
		int result = boardMapper.updateBoardInfo(boardVO);
		assertEquals(result, 1);
	}

	//@Test
	void boardDelete() {
		// 삭제
		int result = boardMapper.deleteBoardInfo(1001);
		assertEquals(result, 1);
	}
}
