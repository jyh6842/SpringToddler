package kr.or.ddit.fileitem.dao;

import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.factory.SqlMapClientFactory;
import kr.or.ddit.vo.FileItemVO;

public class IFileItemDaoImpl implements IFileItemDao{
	private static IFileItemDao dao;
	private SqlMapClient client;
	
	private IFileItemDaoImpl() {
		client = SqlMapClientFactory.getSqlMapClient();
	};
	
	public static IFileItemDao getInstance() {
		return (dao == null) ? dao = new IFileItemDaoImpl() : dao;
	}

	@Override
	public void insertFileItem(List<FileItemVO> fileitemList) throws Exception {
		try {
			// iBatis 트랜잭션
			// 	Commit : startTransation() => 쿼리 질의(전체 성공)
			// 			=> commitTransaction()
			//          => client.endTransaction();
			//  Rollback : startTransaction() => 쿼리 질의(일부 성공 후 에러)
			//          => endTransaction();
			client.startTransaction();
			for(FileItemVO fileItemInfo : fileitemList) {
				client.insert("fileitem.insertFileItem", fileItemInfo);
			}
			
			client.commitTransaction(); 
		} finally {
			client.endTransaction();   // commitTransaction() 실행이 되지 않았을 시 롤백 됨.
		}
	}

	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception {
		return (FileItemVO) client.queryForObject("fileitem.fileitemInfo", params);
	}
}
