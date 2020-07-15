package kr.or.ddit.join.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// /SpringToddler/user/join/loginForm.do
// /SpringToddler/user/join/loginCheck.do
// /SpringToddler/user/join/loginOut.do

@Controller /*클라이언트 요청을 처리할 수  있는 클래스가 됨*/
@RequestMapping("/user/join/") /*폼 채크 아웃이 공통으로 쓰는거는 이렇게 미리 선언할 수 있음*/
public class JoinController {
	@RequestMapping("loginForm") //어차피 do 확장자로 들어오니까 loginForm.do 에서 do 지운다.
	public String loginForm(){
		// 반환값 : join/loginForm
		// internalResourceViewResolver 취득함
		//		prefix(/WEB-INF/views/user/)
		//		suffix(.jsp)
		//	/WEB-INF/view/user/join/loginForm.jsp 포워딩 처리함
		return "join/loginForm";
	}
}
