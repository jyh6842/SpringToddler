package kr.or.ddit.second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecondController {

	@RequestMapping("/second/hello.second")
	public String secondMethod(){
		System.out.println("SecondController의 secondMethod() 콜백");
		return "second/hello";
	}
}
