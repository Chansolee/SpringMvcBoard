<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/js/bootstrap-5.1.3-dist/css/bootstrap.min.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery-3.6.1.min.js"></script>

<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
