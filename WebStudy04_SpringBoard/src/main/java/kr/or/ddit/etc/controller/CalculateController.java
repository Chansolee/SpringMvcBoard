package kr.or.ddit.etc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.vo.CalculateVO;

@Controller
@RequestMapping("/calculate")
public class CalculateController {
	
	@RequestMapping("/form")
	public String calForm() {
		
		return "etc/calculateForm";
	}
//	json > json
	@PostMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE
			, produces= MediaType.APPLICATION_JSON_UTF8_VALUE   )
	@ResponseBody
	public CalculateVO jsonToJson(@RequestBody CalculateVO vo){
		return vo;
	}
	
//	json > html
	@PostMapping(consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String jsonToHtml(@RequestBody CalculateVO vo,Model model){
		model.addAttribute("calculate",vo);
		return "/etc/result";
	}
//	param > json
	@PostMapping(produces= MediaType.APPLICATION_JSON_UTF8_VALUE )
	@ResponseBody
	public CalculateVO parameterToJson(CalculateVO vo){
		return vo;
	}
//	param > html
	@PostMapping
	public String parameterToHtml(@ModelAttribute("calculate") CalculateVO vo){
		
		return "/etc/result";
	}
}
