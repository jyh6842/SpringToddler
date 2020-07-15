package kr.or.ddit.member.controller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

// /SpringToddler/user/member/memberList.do
// /SpringToddler/user/member/memberView.do
// /SpringToddler/user/member/memberForm.do
@Controller
@RequestMapping("/user/member/") // 공통된 주소
public class MemberController {
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
}
