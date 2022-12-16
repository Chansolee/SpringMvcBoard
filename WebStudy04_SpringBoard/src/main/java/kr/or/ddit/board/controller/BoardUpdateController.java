package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.UpdateGroup;

@Controller
@RequestMapping("/board/boardUpdate.do")
public class BoardUpdateController {
	private final BoardService service;
	@Inject
	public BoardUpdateController(BoardService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public String updateForm(
		@RequestParam(name="what", required=true) int boNo
		, Model model
	) {
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board", board);
		return "board/boardEdit";
	}
	
	@PostMapping
	public String boardUpdate(
		@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board
		, BindingResult errors
		, Model model
	) {
		
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			
			String message = null;
			switch (result) {
				case INVALIDPASSWORD:
					message = "비밀번호 오류";
					viewName = "board/boardEdit";
					break;
				case OK:
					viewName = "redirect:/boardSync/boardView.do?what="+board.getBoNo();
					break;
				default:
					message = "서버 오류";
					viewName = "board/boardEdit";
					break;
			}
			model.addAttribute("message", message);
		}else {
			viewName = "board/boardEdit";
		}
		return viewName;
	}
}

















