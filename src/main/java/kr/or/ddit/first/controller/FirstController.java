package kr.or.ddit.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 클라이언트의 요청을 실제 처리하는 커맨드 컨트롤러 ? DL이 작동 했다?
@Controller
public class FirstController {
	// http://localhost/SpringToddler/first/hello.first
	@RequestMapping("/first/hello.first")
	public String firstMethod(){
		System.out.println("FirstController의 firstMethod() 콜백");
		return "first/hello";
	}
}
