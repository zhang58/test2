<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    <form action="" method="post">
      <table border="1">
        <tr align="center">
          <td colspan="7"><h2>标准信息列表</h2></td>
        </tr>
        <tr align="right">
          <td colspan="7">
            <input type="text" id="name" name="name" value="${name}">
            <input type="button" value="查询" onclick="cha()">
            <input type="button" value="新增" onclick="zen()">
            <input type="submit" value="批量删除">
          </td>
        </tr>
        <tr>
          <td><input type="checkbox"></td>
          <td>标准号</td>
          <td>中文名称</td>
          <td>版本</td>
          <td>发布日期</td>
          <td>实施日期</td>
          <td>操作</td>
        </tr>
        <c:forEach items="${stanList}" var="s">
        <tr>
          <td><input type="checkbox"></td>
          <td>${s.std_name}</td>
          <td>${s.name}</td>
          <td>${s.version}</td>
          <td>${s.r_date}</td>
          <td>${s.i_date}</td>
          <td>
          <a href="selectId?id=${s.id}">修改</a>
          <a onclick="return del(${s.id})">删除</a>
          </td>
        </tr>
        </c:forEach>
        <tr align="right">
          <td colspan="7">
            <a href="selectAll?pageIndex=1&name=${name}">首页</a>
            <a href="selectAll?pageIndex=${page.currenPageNo-1}&name=${name}">上一页</a>
            <a href="selectAll?pageIndex=${page.currenPageNo+1}&name=${name}">下一页</a>
            <a href="selectAll?pageIndex=${page.totalPageCount}&name=${name}">末页</a>
            <span>第${page.currenPageNo}页/共${page.totalPageCount}页</span>
          </td>
        </tr>
      </table>
    </form>
  </body>
  <script type="text/javascript" src="jquery-1.12.4.js"></script>
  <script type="text/javascript">
  function cha(){
    var name=$("#name").val();
    location.href="${ctx}/selectAll?name="+name;
  }
  
  
  function zen(){
    location="insert.jsp";
  }
  
  function del(id){
   $.post("${ctx}/deleteId","id="+id,function(data){
     if(data=="true"){
     alert("删除成功！")
     window.location.href="${ctx}/selectAll"
     }else{
     alert("删除失败！")
     window.location.href="${ctx}/selectAll"
     }
   })
  }
  </script>
</html>

