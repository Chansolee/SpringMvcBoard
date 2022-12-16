<%@page import="kr.or.ddit.board.vo.MenuVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav id="sidebarMenu"
	class="col-md-2 col-lg-2 d-md-block bg-light sidebar collapse">
	<div class="sidebar-sticky pt-3">
		<ul class="nav flex-column">

			<%
			List<MenuVO> menuList = (List) request.getAttribute("menuList");
			if (menuList != null) {
				for (MenuVO menu : menuList) {
			%>

			<li class="nav-item"><a class="nav-link active"
				href="<%=request.getContextPath()%><%=menu.getMenuURL()%>"><%=menu.getMenuText()%></a>
			</li>
			<%
			}
			}
			%>

		</ul>
	</div>
</nav>