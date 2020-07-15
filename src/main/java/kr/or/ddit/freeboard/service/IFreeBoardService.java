package kr.or.ddit.freeboard.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.vo.FreeBoardVO;

public interface IFreeBoardService {
	
	public List<FreeBoardVO> freeboardList(Map<String, String> params);
	
	public String insertFreeboard(FreeBoardVO freeboardInfo,
									FileItem[] items);

	public String insertFreeboardReply(FreeBoardVO freeboardInfo);

	public void updateFreeboard(FreeBoardVO freeboardInfo);
	
	public FreeBoardVO freeboardInfo(Map<String, String> params);
	
	public void deleteFreeboard(Map<String, String> params);
	
	public String totalCount(Map<String, String> params);
}
