package com.example.demo.controller.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value="/url")
public class RedirectController {
	
	@RequestMapping(value="/url")
	public ModelAndView redirectUrl(String url) {
		System.out.println("-----------------------");
		ModelAndView mv = new ModelAndView();
		mv.setViewName(url);
		return mv;
	}

}
