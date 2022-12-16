package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.board.service.BoardService;

//@Controller
//@ResponseBody
@RestController
@RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BoardRecommendController {
	@Inject
	private BoardService service;

	
	@RequestMapping("/board/boardRecommend.do")
	public int recommend(@RequestParam(name="what", required=true) int boNo) {
		
		 int boRec = service.recommend(boNo);
		
		 return boRec;
	}
}
