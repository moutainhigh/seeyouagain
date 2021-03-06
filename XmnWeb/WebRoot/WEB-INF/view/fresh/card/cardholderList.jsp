<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
	<head>
		<base href="<%=basePath%>">
		<title>持卡详情列表</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
		<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
		<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
		<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
			
		<style type="text/css">
		body {
			margin: 0;
			font-size: 12px;
			font-family: 'Microsoft Yahei', '微软雅黑';
			overflow-y: auto;
			padding: 10px 30px;
		}
		
		td{
			height:40px;
		}
		</style>
	
	</head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<ul id="myTab" class="nav nav-tabs">
		<li class="active"><a>会员卡信息列表</a></li>
		<c:if test="${null!=btnAu['fresh/card/prepaidList'] && btnAu['fresh/card/prepaidList']}">
			<li class=""><a href="fresh/card/prepaidListView.jhtml">会员卡充值记录</a></li>
		</c:if>
		<c:if test="${null!=btnAu['fresh/card/consumeList'] && btnAu['fresh/card/consumeList']}">
			<li class=""><a href="fresh/card/consumeListView.jhtml">会员卡消费记录</a></li>
		</c:if>
	</ul>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="selectCardholderForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>会员卡编号:</h5></td>
							<td style="width:14%;"><input type="text" class="form-control"  name="noId" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>卡余额:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control"  name="minAmount"  style="width:39%;float:left"/>
								<label style="float: left;">&nbsp;--&nbsp;</label>
								<input type="text" class="form-control"  name="maxAmount"  style="width:39%;float:left"/>
							</td>
							<td style="width:5%;"><h5>卡序号:</h5></td>
							<td style="width:14%;"><input type="text" class="form-control"  name="id" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>卡名称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="cardName" style = "width:90%;"/></td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>用户编号:</h5></td>
							<td style="width:14%;"><input type="text" class="form-control"  name="uid" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>用户昵称:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control"  name="nname" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>用户手机:</h5></td>
							<td style="width:14%;"><input type="text" class="form-control"  name="phoneid" style = "width:90%;"/></td>
							<td colspan="2" style="width:31%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style = "width:43%;" />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style = "width:43%;"/>
								</div>
							</td>	
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${null!=btnAu['businessman/vipCard/export'] && btnAu['businessman/vipCard/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;excel导出</button>
				</c:if>
			</div>
			<div id="cardholderList"></div>
			<div id="scrollTablel"></div>
		</div>
	</div>
	<script type="text/json" id="permissions">{detail:'${ btnAu['fresh/card/userList/details']}',lock:'${ btnAu['fresh/card/lock']}'}</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/fresh/vipCard/listCardholder.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
  </body>
</html>
