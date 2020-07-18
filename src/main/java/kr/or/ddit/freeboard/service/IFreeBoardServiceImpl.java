package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.fileitem.dao.IFileItemDao;
import kr.or.ddit.freeboard.dao.IFreeBoardDao;
import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 설정파일  : <bean name="iFreeboardServiceImpl" = > @Service("freeboardService") 이거 넣으면 bean name="freeboardService" 이렇게 바뀜
//			class="kr.or.ddit.freeboard.service.IFreeBoardServiceImpl"
@Service("freeboardService")
public class IFreeBoardServiceImpl implements IFreeBoardService {

	@Autowired
	private IFreeBoardDao dao;
	private IFileItemDao fileitemDao;

	@Override
	public List<FreeboardVO> freeboardList(Map<String, String> params)
			throws Exception {

		return dao.freeboardList(params);

	}

	@Override
	public String insertFreeboard(FreeboardVO freeboardInfo, FileItem[] items)
			throws Exception {
		String bo_no = dao.insertFreeboard(freeboardInfo);
		List<FileItemVO> fileItemList = AttachFileMapper.mapper(items, bo_no);

//		fileitemDao.insertFileItem(fileItemList);

		return bo_no;
	}

	@Override
	public String insertFreeboardReply(FreeboardVO freeboardInfo)
			throws Exception {
		return dao.insertFreeboardReply(freeboardInfo);

	}

	@Override
	public FreeboardVO freeboardInfo(Map<String, String> params)
			throws Exception {
		FreeboardVO freeboardInfo = null;
		freeboardInfo = dao.freeboardInfo(params);
		return freeboardInfo;
	}

	@Override
	public void deleteFreeboard(Map<String, String> params) throws Exception {
		dao.deleteFreeboard(params);
	}

	@Override
	public void updateFreeboard(FreeboardVO freeboardInfo) throws Exception {
		dao.updateFreeboard(freeboardInfo);
	}

	@Override
	public String totalCount(Map<String, String> params) throws Exception {
		return dao.totalCount(params);
	}
}
