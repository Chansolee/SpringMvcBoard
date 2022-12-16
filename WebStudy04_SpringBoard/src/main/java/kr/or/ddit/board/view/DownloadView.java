package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.board.vo.AttatchVO;

public class DownloadView extends AbstractView {

	@Value("#{appInfo.attatchFolder}")
	private File saveFolder;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		
		String saveName = attatch.getAttSavename();
		File file = new File(saveFolder, saveName);
		
		String fileName = attatch.getAttFilename();
//		%2T - percent encoding, URL encoding
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replaceAll("\\+", " ");
		resp.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		resp.setContentLengthLong(attatch.getAttFilesize());
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+fileName+"\"");
		try(
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(file, os);
		}
	}

}
