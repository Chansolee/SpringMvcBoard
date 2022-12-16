package kr.or.ddit.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@WebServlet("/xmlView.do")
public class XmlViewServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> model = new HashMap<>();
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String name = (String) attributeNames.nextElement();
			Object value = request.getAttribute(name);
			model.put(name, value);
		}
		
		String contentType = "application/xml;charset=UTF-8";
		response.setContentType(contentType);
		try(
			PrintWriter out = response.getWriter();	
		){
			new XmlMapper().writeValue(out, model);
		}
	}
}










