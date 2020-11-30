<%@page import="java.util.ArrayList"%>
<%@page import="vo.CategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    ArrayList<CategoryBean> categoryList =(ArrayList<CategoryBean>) request.getAttribute("categoryList");
	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%for(int i= 0; i < categoryList.size(); i++){
	CategoryBean categoryBean = categoryList.get(i);%>
	
	<%=categoryBean.getCategory_id() %>:
	<%=categoryBean.getCategory_content() %><br>
	
	<% 
	}%>

</body>
</html>