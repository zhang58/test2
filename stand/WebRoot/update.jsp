<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="update" method="post" enctype="multipart/form-data">
      <table border="1" align="left">
        <tr align="center">
          <td colspan="2"><h2>修改标准号信息</h2></td>
        </tr>
        <tr>
          <td>标准号：</td>
          <td>
          <input type="hidden" name="id" value="${s.id}">
          <input type="text" name="std_name" value="${s.std_name}">
          </td>
        </tr>
        <tr>
          <td>中文名称：</td>
          <td><input type="text" name="name" value="${s.name}"></td>
        </tr>
        <tr>
          <td>版本：</td>
          <td><input type="text" name="version" value="${s.version}"></td>
        </tr>
        <tr>
          <td>关键字：</td>
          <td><input type="text" name="keys" value="${s.keys}"></td>
        </tr>
        <tr>
          <td>发布日期：</td>
          <td><input type="text" name="r_date" value="${s.r_date}<%-- <fmt:formatDate value="${s.r_date}"/> --%>"></td>
        </tr>
        <tr>
          <td>实施日期：</td>
          <td><input type="text" name="i_date" value="${s.i_date}<%-- <fmt:formatDate value="${s.i_date}"/> --%>"></td>
        </tr>
        <tr>
          <td>附件：</td>
          <td>
          <img style="width: 50;height: 50" alt="" src="upload/${s.path}">
          <input type="hidden"  name="path" value="${s.path}"/>
          <input type="file" name="a_path">
          </td>
        </tr>
        <tr>
          <td><input type="submit" value="修改"></td>
          <td><input type="reset" value="取消"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
