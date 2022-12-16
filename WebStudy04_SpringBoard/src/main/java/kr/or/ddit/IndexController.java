package kr.or.ddit;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/index.do")
	public String index(Model model) {
		Date now = new Date();
		model.addAttribute("now", now);
		return "index";
				
	}
}
