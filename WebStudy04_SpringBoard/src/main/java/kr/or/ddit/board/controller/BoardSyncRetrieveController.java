package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.board.vo.SearchVO;

/**
 * HandlerAdapter 사용 방법(컨트롤러 구현 방법)
 * 1. @Controller : 컨테이너에 빈으로 등록되며, HandlerMapping 수집되는 컨트롤러 객체임.
 * 2. @RequestMapping (method-level) : 컨트롤러가 처리 가능한 요청을 제한(mapping)함.
 * 		- value : request URI
 * 		- method : http method
 * 		- params : request parameter
 * 		- headers : request header
 *      - consume : request Content-Type 
 *      - produce : reqeust Accept / response Content-Type
 *  3. 요청과 관련된 모든 데이터는 HandlerAdapter 로 부터 받되, 핸들러 메소드 파라미터 형태로.
 *     1) @RequestXXX : RequestParam, RequestHeader, RequestPart
 *     2) @ModelAttribute : 요청 파라미터를 VO 객체로 받을때, 특정(request) scope 를 통해 모델 속성을 공유하게됨. 
 *     3) @RequestBody : request body(message, content) 를 Command Object 를 이용해 받을때.
 *     			@RequestMapping(consumes, unmarshalling)
 *     4) @ResponseBody : 핸들러 메소드의 리턴값으로 response body(message, content) 를 구성할때.
 *     			@RequestMapping(produces, marshalling)    
 */
@Controller
@RequestMapping("/boardSync")
public class BoardSyncRetrieveController {
	@Inject
	private BoardService service;
	@Inject
	private BoardRetrieveController otherController;
	
	@RequestMapping(value="boardList.do" )
	public String  list(
		@RequestParam(name="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("simpleCondition") SearchVO simpleCondition, Model model
		
	) {
		PagingVO<BoardVO> pagingVO = otherController.list(currentPage, simpleCondition);
	
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardListSync";
	}
	
	@RequestMapping("boardView.do")
	public ModelAndView boardView(@RequestParam(name="what", required=true) int boNo) {
		BoardVO board = service.retrieveBoard(boNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board", board);
		mav.setViewName("board/boardView");
		return mav;
	}

}











