package kr.or.ddit.utiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import kr.or.ddit.global.GlobalConstant;





// 파일 다운로드 처리 jsp 내
// FileDownloadView.fileDownlaod(pageContext, "a.png");
public class FileDownloadView {
	public static void fileDownload(PageContext pageContext,
									String realName,
									String downloadFileName) throws IOException{
		
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();// 이건 무슨 역할?
		JspWriter out = pageContext.getOut(); // 무슨 역할?
		
		File downloadFile = new File(GlobalConstant.FILE_PATH, downloadFileName); // 여기에서 스트리밍이 일어난다.
		
		if(downloadFile.exists()){
			realName = URLEncoder.encode(realName, "UTF-8");
			
			response.setHeader("Content-Disposition", "attachmemt;fileName= "+realName); //downloadFileName는 유저가 진짜로 업로드한 파일의 이름이어야한다. 그래서 파라미터 하나를 더 받도록 realName을 받는다.
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			
			byte[] buffer = new byte[(int)downloadFile.length()];
			
			BufferedInputStream inputStream = new BufferedInputStream(
															new FileInputStream(
																	downloadFile));
			BufferedOutputStream outputStream = new BufferedOutputStream(
															response.getOutputStream()); // response에 초기화한 getOutputStream() 가져오면 된다.
			int readCnt = 0;
			while((readCnt = inputStream.read(buffer)) != -1){
				outputStream.write(buffer);
			}
			
			inputStream.close();
			outputStream.close();
						
			
		}
		
	}
}
