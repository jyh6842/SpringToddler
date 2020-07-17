package kr.or.ddit.freeboard.contriller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.freeboard.service.IFreeBoardService;
import kr.or.ddit.utiles.CryptoGenerator;
import kr.or.ddit.utiles.RolePaginationUtil;
import kr.or.ddit.vo.FreeboardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/freeboard/")
public class FreeboardController {
	@Autowired
	private CryptoGenerator cryptoGen;
	@Autowired
	private IFreeBoardService service;
	@Autowired
	private RolePaginationUtil pagination;
	
	
	// localhost/SpringToddler/user/freeboard/freeboardList.do
	@RequestMapping("freeboardList")
	public ModelAndView freeboardList(	String search_keycode
										,String search_keyword
										,Map<String, String> params
										,ModelAndView andView
										,HttpSession session
										,HttpServletRequest request
										,String currentPage
										) throws Exception{
		if(currentPage == null){
			currentPage = "1";
		}
		
		
		Map<String,String> publicKeyMap = this.cryptoGen.genneratePairKey(session);
		
		
		
		
		String totalCount = this.service.totalCount(params);
		
		params.put("totalCount", totalCount);

		
		// 페이지 버튼 동적으로 만들기
		this.pagination.RolePaginationUtil(request, Integer.parseInt(currentPage), Integer.parseInt(totalCount)); // request를 생성자에 준다. 현재 페이지도 준다. 토탈 카운트도 준다.
		
		params.put("startCount", String.valueOf(this.pagination.getStartCount()));
		params.put("endCount", String.valueOf(this.pagination.getEndCount()));
		
		
		List<FreeboardVO> freeboardList = this.service.freeboardList(params);
		
		String paging  = this.pagination.getPagingHtmls();
		
//		ModelAndView andView = new ModelAndView();
		andView.addObject("freeboardList", freeboardList);
		andView.addObject("publicKeyMap", publicKeyMap);
		andView.addObject("paging", paging);
		andView.setViewName("user/freeboard/freeboardList");
		
		return andView;
		
	}
	
	@RequestMapping("freeboardForm")
	public void freeboardForm(){}
	
	
//	@RequestMapping("insertFreeboard")
//	public ModelAndView(FreeboardVO freeboardInfoVo){
//		this.service.insertFreeboard(freeboardInfo, items)
//	}
}
