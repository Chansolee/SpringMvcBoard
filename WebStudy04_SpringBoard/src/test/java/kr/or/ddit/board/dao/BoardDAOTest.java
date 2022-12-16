package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class BoardDAOTest {
	
	
	
	@Inject
	private BoardDAO dao;

	PagingVO pagingVO = new PagingVO();

	@Before
	public void setUp() {
		pagingVO.setCurrentPage(1);
		pagingVO.setTotalRecord(5);
	
	}
	
	
	
	
	@Test
	public void testSelectBoard() {
		BoardVO board = dao.selectBoard(3);
		log.info("주입된 객체: {}", board);
		System.out.println(board);
	}
	
	@Test
	public void testSelectBoardList() {
		List<BoardVO> board = dao.selectBoardList(pagingVO);
		log.info("주입된 객체: {}", board);
		System.out.println(board);
	}
	
	

	


}
