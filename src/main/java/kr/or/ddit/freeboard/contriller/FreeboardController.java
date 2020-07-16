package kr.or.ddit.freeboard.contriller;

import java.util.List;
import java.util.Map;

import kr.or.ddit.freeboard.service.IFreeBoardService;
import kr.or.ddit.vo.FreeboardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/freeboard/")
public class FreeboardController {
	@Autowired
	private IFreeBoardService service;
	
	// localhost/SpringToddler/user/freeboard/freeboardList.do
	@RequestMapping("freeboardList")
	public ModelAndView freeboardList(Map<String, String> params
										,ModelAndView andView
										) throws Exception{
		
		List<FreeboardVO> freeboardList = this.service.freeboardList(params);
		
//		ModelAndView andView = new ModelAndView();
		andView.addObject("freeboardList", freeboardList);
		andView.setViewName("user/freeboard/freeboardList");
		
		return andView;
		
	}
}
