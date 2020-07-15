package kr.or.ddit.fileitem.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.fileitem.dao.IFileItemDaoImpl;
import kr.or.ddit.vo.FileItemVO;

@Service
public class IFileItemServiceImpl implements IFileItemService{
	
//	@Qualifier 타입이 중복되면 이거 사용
	@Autowired
	private IFileItemDao dao;
	


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
