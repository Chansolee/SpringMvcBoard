<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<nav class="navbar navbar-dark sticky-top flex-md-nowrap w-100 p-0 border border-secondary">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Company403</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <ul class="nav px-3 col">
     <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/board/boardList.do">게시판목록조회(비동기)</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/boardSync/boardList.do">게시판목록조회(동기)</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="?language=en">영어</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="?language=ko">한글</a>
    </li>

  </ul>
 
</nav>










