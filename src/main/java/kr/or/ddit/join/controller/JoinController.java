package kr.or.ddit.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// /SpringToddler/user/join/loginForm.do
// /SpringToddler/user/join/loginCheck.do
// /SpringToddler/user/join/loginOut.do

@Controller /*클라이언트 요청을 처리할 수  있는 클래스가 됨*/
@RequestMapping("/user/join/") /*폼 채크 아웃이 공통으로 쓰는거는 이렇게 미리 선언할 수 있음*/
public class JoinController {
	@Autowired
	private MessageSourceAccessor accessor; // 다국어 지원 모드를 위해서
	
	@Autowired
	private IMemberService service;
	
	@RequestMapping("loginForm") //어차피 do 확장자로 들어오니까 loginForm.do 에서 do 지운다.
	public void loginForm(){
		// 반환값 : join/loginForm
		// internalResourceViewResolver 취득함
		//		prefix(/WEB-INF/views/user/)
		//		suffix(.jsp)
		//	/WEB-INF/view/user/join/loginForm.jsp 포워딩 처리함
//		return "user/join/loginForm";
	}
	
	//  /SpringToddler/user/join/loginCheck.do
	//	POST 전송 방식 : mem_id=a001&mem_pass=asdfasdf
	@RequestMapping("loginCheck")
	public String loginCheck(String mem_id,
							 String mem_pass,
							 HttpServletRequest request,
						 	 HttpSession session,
						 	 HttpServletResponse response
							)throws Exception{
		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
		if(memberInfo == null){
			String message = this.accessor.getMessage("fail.common.join", Locale.KOREA); // fail.common.join = 회원이 아닙니다.
			message = URLEncoder.encode(message, "UTF-8");
			// 리다이렉트 (컨텍스트 루트 | 패스 생략) 컨텍스르 루트 내에서 실행 되는 거니까 생략을 하는거
			return "redirect:/user/join/loginForm.do?message="+message;
		}else {
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			// 포워드 (컨텍스트 루트 | 패스 생략)
			return "forward:/user/member/memberList.do";
		}
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) throws UnsupportedEncodingException{
		session.invalidate();
		
		String message = this.accessor.getMessage("success.common.logout", Locale.KOREA); // fail.common.join = 회원이 아닙니다.
		message = URLEncoder.encode(message, "UTF-8");
		return "redirect:/user/join/loginForm.do?message="+message;
	}
}
