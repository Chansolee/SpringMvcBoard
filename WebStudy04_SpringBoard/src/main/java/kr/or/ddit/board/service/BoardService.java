package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;

/**
 * 게시판 관리용 Business Logic Layer
 *
 */
public interface BoardService {
	public ServiceResult createBoard(BoardVO board);
	public BoardVO retrieveBoard(int boNo);
	public List<BoardVO> retrieveBoardList(PagingVO<BoardVO> pagingVO);
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO);
	/**
	 * 게시글 수정(인증 필요)
	 * @param board
	 * @return RuntimeException, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyBoard(BoardVO board);
	/**
	 * 게시글 삭제(인증 필요)
	 * @param board
	 * @return RuntimeException, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeBoard(BoardVO board);
	
	/**
	 * 게시글 추천기능
	 * @param boNo
	 * @return 갱신된 추천수
	 */
	public int recommend(int boNo);
	
	/**
	 * @param attNo
	 * @return RuntimeException, 
	 */
	public AttatchVO retrieveAttatch(int attNo);
}
