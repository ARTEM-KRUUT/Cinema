package fsr.iao.cinema.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CinemaController {
	
	@GetMapping("/main")
	public String index(){
		return "main";
	}

}
