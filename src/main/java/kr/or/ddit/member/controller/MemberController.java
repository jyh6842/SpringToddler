package kr.or.ddit.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// /SpringToddler/user/member/memberList.do
// /SpringToddler/user/member/memberView.do
// /SpringToddler/user/member/memberForm.do
@Controller
@RequestMapping("/user/member/") // 공통된 주소
public class MemberController {
	@Autowired
	private MessageSourceAccessor accessor;
	
	@Autowired
	private IMemberService service;
	
	// void 비슷한 Model
	@RequestMapping("memberList")
	public Model memberList(String search_keycode, // 파라미터로 들어가는게 인젝션?
							String search_keyword,
							Map<String, String> params,
							Model model
							){
//		Map<String, String> params = new HashMap<String, String>();
		params.put("search_keycode", search_keycode);
		params.put("search_keyword", search_keyword);
		
		List<MemberVO> memberList = this.service.memberList(params);
		
		// memberList = view resolver => memberList.jsp
//		Model model = new ExtendedModelMap(); 위에서 해주자
		model.addAttribute("memberList", memberList);
		
		return model;
		
	}
	
	@RequestMapping("memberView")
	public ModelMap memberView(String mem_id // ModelMap 는 void와 마찬가지 알아서 return 해서 갈곳을 알아서 model + void = ModelMap
							   ,Map<String, String> params
							   ,ModelMap modelMap
							   ){
		params.put("mem_id", mem_id);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
//		ModelMap modelMap = new ModelMap();
		
		modelMap.addAttribute("memberInfo", memberInfo);
		
		return modelMap;
	}
	
	@RequestMapping("updateMemberInfo")
	private String updateMember(MemberVO memberInfo // private로 되도 요청은 되지만 public 써야 됨. 왜? 그런 이유가 일단 있다는데
								){
		this.service.updateMemberInfo(memberInfo);
		return "redirect:/user/member/memberList.do";
	}
	
	// /user/member/deleteMemberInfo.do?user_id=a001
	@RequestMapping("deleteMemberInfo")
	public String deleteMember(@RequestParam(required=false, defaultValue="널 대체값" ) String mem_id // @RequestParam(value="user_id") String mem_id
							   ,Map<String, String> params){
		params.put("mem_id", mem_id);
		this.service.deleteMemberInfo(params);
		return "redirect:/user/member/memberList.do";
	}
	
	
	@RequestMapping("memberForm")
	public void memberForm(){}
	
	
	@RequestMapping("insertMemberInfo")
	public String insertMember(MemberVO memberInfo) throws UnsupportedEncodingException{ // 도메인 오브젝트라고 부른다. membervo memberInfo
		this.service.insertMember(memberInfo);
		
		String message = this.accessor.getMessage("cop.regist.msg.confirm", Locale.KOREA);
		message = URLEncoder.encode(message, "UTF-8");
		
		return "redirect:/user/join/loginForm.do?message="+message;
	}
	
	
}
