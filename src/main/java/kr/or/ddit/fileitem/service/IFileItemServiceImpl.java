package kr.or.ddit.fileitem.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.fileitem.dao.IFileItemDaoImpl;
import kr.or.ddit.vo.FileItemVO;

public class IFileItemServiceImpl implements IFileItemService{
	
	private static IFileItemService service;
	private IFileItemDao dao;
	
	private IFileItemServiceImpl() {
		dao = IFileItemDaoImpl.getInstance();
	}
	
	public static IFileItemService getInstance() {
		return service == null ? service = new IFileItemServiceImpl() : service;
	}

	@Override
	public void insertFileItem(List<FileItemVO> fileitemList) {
		try {
			dao.insertFileItem(fileitemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) {
		FileItemVO fileitemInfo = null;
		try {
			fileitemInfo = dao.fileitemInfo(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileitemInfo;
	}
	
}
