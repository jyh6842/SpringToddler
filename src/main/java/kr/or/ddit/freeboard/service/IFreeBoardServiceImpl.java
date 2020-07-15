package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.fileitem.dao.IFileItemDaoImpl;
import kr.or.ddit.freeboard.dao.IFreeBoardDao;
import kr.or.ddit.freeboard.dao.IFreeBoardDaoImpl;
import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 설정파일  : <bean name="iFreeboardServiceImpl" = > @Service("freeboardService") 이거 넣으면 bean name="freeboardService" 이렇게 바뀜
//			class="kr.or.ddit.freeboard.service.IFreeBoardServiceImpl"
@Service("freeboardService")
public class IFreeBoardServiceImpl implements IFreeBoardService{

	@Autowired
	private IFreeBoardDao dao;
	private IFileItemDao fileitemDao;
	

	

	
	@Override
	public List<FreeboardVO> freeboardList(Map<String, String> params) {
		List<FreeboardVO> list = null;
		try {
			list = dao.freeboardList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String insertFreeboard(FreeboardVO freeboardInfo, FileItem[] items) {
		String bo_no = null;
		try {
			 bo_no = dao.insertFreeboard(freeboardInfo);
			 List<FileItemVO> fileItemList = AttachFileMapper.mapper(items, bo_no);
			 
			 fileitemDao.insertFileItem(fileItemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo_no;
	}
	
	
	@Override
	public String insertFreeboardReply(FreeboardVO freeboardInfo) {
		String bo_no = null;
		try {
			bo_no = dao.insertFreeboardReply(freeboardInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo_no;
	}

	@Override
	public FreeboardVO freeboardInfo(Map<String, String> params) {
		FreeboardVO freeboardInfo = null;
		try {
			freeboardInfo = dao.freeboardInfo(params);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return freeboardInfo;
	}

	@Override
	public void deleteFreeboard(Map<String, String> params) {
		try {
			dao.deleteFreeboard(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateFreeboard(FreeboardVO freeboardInfo) {
		try {
			dao.updateFreeboard(freeboardInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String totalCount(Map<String, String> params) {
		String totalCount = null;
		try {
			totalCount = dao.totalCount(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
}
