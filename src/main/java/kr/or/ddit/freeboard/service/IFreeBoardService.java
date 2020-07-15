package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.vo.FreeboardVO;

public interface IFreeBoardService {
	
	public List<FreeboardVO> freeboardList(Map<String, String> params);
	
	public String insertFreeboard(FreeboardVO freeboardInfo,
									FileItem[] items);

	public String insertFreeboardReply(FreeboardVO freeboardInfo);

	public void updateFreeboard(FreeboardVO freeboardInfo);
	
	public FreeboardVO freeboardInfo(Map<String, String> params);
	
	public void deleteFreeboard(Map<String, String> params);
	
	public String totalCount(Map<String, String> params);
}
