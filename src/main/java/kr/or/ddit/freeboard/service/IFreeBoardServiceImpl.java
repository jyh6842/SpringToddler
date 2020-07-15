package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.fileitem.dao.IFileItemDaoImpl;
import kr.or.ddit.freeboard.dao.IFreeBoardDao;
import kr.or.ddit.freeboard.dao.IFreeBoardDaoImpl;
import kr.or.ddit.utils.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeBoardVO;

import org.apache.commons.fileupload.FileItem;

public class IFreeBoardServiceImpl implements IFreeBoardService{

	private static IFreeBoardService service;
	private IFreeBoardDao dao;
	private IFileItemDao fileitemDao;
	
	private IFreeBoardServiceImpl() {
		dao = IFreeBoardDaoImpl.getInstance();
		fileitemDao = IFileItemDaoImpl.getInstance();
	}
	
	public static IFreeBoardService getInstance() {
		return service == null ? service = new IFreeBoardServiceImpl() : service;
	}
	
	@Override
	public List<FreeBoardVO> freeboardList(Map<String, String> params) {
		List<FreeBoardVO> list = null;
		try {
			list = dao.freeboardList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String insertFreeboard(FreeBoardVO freeboardInfo, FileItem[] items) {
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
	public String insertFreeboardReply(FreeBoardVO freeboardInfo) {
		String bo_no = null;
		try {
			bo_no = dao.insertFreeboardReply(freeboardInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bo_no;
	}

	@Override
	public FreeBoardVO freeboardInfo(Map<String, String> params) {
		FreeBoardVO freeboardInfo = null;
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
	public void updateFreeboard(FreeBoardVO freeboardInfo) {
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
