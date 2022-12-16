package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AttatchDownloadController {
	@Inject
	private BoardService service;


	
	@RequestMapping("/board/download.do")
	public String download(@RequestParam(name="what", required=true) int attNo
			, Model model) throws IOException {
		AttatchVO attatch = service.retrieveAttatch(attNo);
		model.addAttribute("attatch", attatch);
		 return "downloadView";
	 
		
	}
	
}
