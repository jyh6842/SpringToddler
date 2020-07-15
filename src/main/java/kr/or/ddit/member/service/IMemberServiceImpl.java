package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.IMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

// 설정파일 : <bean name="iMemberServiceImpl"
//			class="kr.or.ddit.member.service.IMemberServiceImpl"
//			c:dao-ref="iMemberDaoImpl"/>
// 스프링이기 때문에 이미 싱글톤이기 때문에 싱글톤 관련된 것 필요 없음
@Service
public class IMemberServiceImpl implements IMemberService{
	@Autowired
	private IMemberDao dao;
	
//	private IMemberServiceImpl(IMemberDao dao) {
//		this.dao = dao;
//	}
	// 왜 필요 없음? c:dao-ref="iMemberDaoImpl" 이거 되서? @Autowired?


	
	@Override
	public MemberVO memberInfo(Map<String, String> parms) {
		MemberVO memberInfo = null;
		try {
			memberInfo = dao.memberInfo(parms);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memberInfo;
	}

	@Override
	public List<MemberVO> memberList(Map<String, String> params) {
		List<MemberVO> memberList = null;
		try {
			memberList = dao.memberList(params);
		} catch (Exception e){
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public void deleteMemberInfo(Map<String, String> params) {
		try{
			dao.deleteMemberInfo(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMemberInfo(MemberVO memberInfo) {
		try {
			dao.updateMemberInfo(memberInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertMember(MemberVO memberInfo) {
		try {
			dao.insertMember(memberInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String totalCount(Map<String, String> params) {
		String totalCount = null;
		try {
			totalCount = dao.totalCount(params);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
}
