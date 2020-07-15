package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.IMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class IMemberServiceImpl implements IMemberService{
	
	private static IMemberService service = new IMemberServiceImpl();
	private IMemberDao dao;
	
	private IMemberServiceImpl() {
		dao = IMemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		return (service == null) ? service = new IMemberServiceImpl() : service;
	}

	
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
