<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<form action="<%=request.getContextPath() %>/login.do" method="post">
<table>
<tr><td>用户名：<input name="username"type="text"></td></tr>
<tr><td>密码：<input name="password"type="password"></td></tr>
<tr><td colspan="2"><input value="提交" type="submit"></td></tr>
</table>
</form>
</body>
</html>
