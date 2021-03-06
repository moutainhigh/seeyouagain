<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>主播银行卡管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
  </head>
  
  <body  style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
			<form class="form-horizontal" role="form" method="post" id="searchForm">
						<input type="hidden" class="form-control" id="uid" name="uid" value="${uid}"/>
						<input type="hidden" class="form-control" id="nickname" name="nickname" value="${nickname}"/>	
			</form>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
			<c:if test="${null!=btnAu['anchor/manage/bindCard']}">
				<button type="button" class="btn btn-success"  data-type="ajax" data-url="anchor/manage/bindCardInit/addCardInit.jhtml?uid=${uid}"  data-toggle="modal" ><span class="icon-plus"></span>&nbsp;添加</button>
				</c:if>
			</div>
			<div id="cardList"></div>
		</div>
	</div>
		<script type="text/json" id="permissions">
		{xg:'${ btnAu['anchor/manage/bindCard']}'}
    </script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/js/live_anchor/anchorCard/bindAnchorCardInit.js"></script>
  </body>
</html>
