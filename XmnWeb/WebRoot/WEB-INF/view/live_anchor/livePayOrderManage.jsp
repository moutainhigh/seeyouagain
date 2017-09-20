<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<title>直播频道支付订单管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">
<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">
</head>

<body
	style="overflow-x: auto;overflow-y: auto;min-width: 1024px;background:#EEE"
	class="doc-views with-navbar">
	<div id="prompt"
		style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" id="searchForm">
				<input type="hidden" name="eUid" value="${eUid}">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>&nbsp;&nbsp;订单编号：</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="orderNo" style = "width:85%;"></td>
							<td style="width:5%;"><h5>&nbsp;&nbsp;手机号码：</h5></td>
							<td style="width:25%;"><input type="text" class="form-control"  name="phone" style = "width:85%;"></td>
							<td style="width:5%;"><h5>支付方式：</h5></td>
							<td style="width:30%;">
								<select class="form-control" name="payType" style = "width:67%;">
									<option value="">全部</option>
									<option value="1000001">支付宝SDK支付</option>
									<option value="1000003">微信SDK支付</option>
									<option value="1000013">公众号支付 </option>
									<option value="1000000">钱包支付</option>
									<option value="1000004">AppStore支付</option>
									<option value="1000014">寻蜜鸟服务窗</option>
									<option value="1000021">线下转账</option>
									<option value="1000022">支付宝APP支付（鸟人科技）</option>
									<option value="1000024">微信APP支付（鸟人科技）</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>支付状态：</h5></td>							
							<td style="width:25%;"> 
								<select class="form-control" name="payState" style = "width:85%;">
									<option value="">全部</option>
									<option value="0">未支付</option>
									<option value="1">已支付成功</option>
									<option value="2">取消支付</option>
								</select>
							</td>
							<td style="width:5%;"><h5>充值渠道：</h5></td>
							<td style="width:25%;">
								<select class="form-control" name="objectOriented" style = "width:85%;">
									<option value="">全部</option>
									<option value="0">常规</option>
									<option value="1">VIP</option>
									<option value="2">商家</option>
									<option value="3">直销</option>
									<option value="4">营业厅会员</option>
									<option value="5">黄金庄园</option>
								</select>
							</td>
							<td colspan="2" style="width:35%;">
								<div class="submit" style="text-align: left;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' />
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' />
								</div>
							</td>	
						</tr>
					</tbody>
				</table>

				<c:if test="${!empty param.page}">
					<input type="hidden" value="${param.page}" name="page" />
				</c:if>
			</form>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body data">
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if
					test="${null!=btnAu['livePayOrder/manage/add'] && btnAu['livePayOrder/manage/add']}">
					<button type="button" class="btn btn-success" data-type="ajax"
						data-title="添加充值订单" data-url="livePayOrder/manage/add/init.jhtml"
						data-toggle="modal" data-width="auto">
						<span class="icon-plus"></span>&nbsp;添加订单
					</button>
				</c:if>
				<c:if test="${btnAu['livePayOrder/manage/export']}">
						<button type="button" id="export" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;导出</button>
				</c:if>
			</div>
			<div id="livePayOrderList"></div>
		</div>
	</div>
	
	<!-- modal start -->
	<div class="modal fade" id="excitationProjectModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title">会员信息(修改)</h4>
				</div>
				<div class="modal-body example" >
					<form class="form-horizontal" id="excitationProjectForm">
						<input type="hidden" id="id" name="id">
						<input type="hidden" id="orderNo" name="orderNo">
						
						<div class="form-group">
							<label class="col-md-4 control-label">会员昵称：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="nname"
									id="nname" value="" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">会员手机号码：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="phone"
									id="phone" value="" readonly="true">
							</div>
						</div>
						<!-- style="display:none;" -->
						<div class="form-group" > 
							<label class="col-md-4 control-label">会员等级：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<input type="text" class="form-control" name="ledgerLevel"
									id="ledgerLevel" value="" readonly="true">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">返还模式：<span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<select class="form-control" name="excitationProject">
								    <option value="">不返还</option>
									<option value="A">A模式</option>
									<option value="B">B模式</option>

								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-4 control-label">已返还： <span
								style="color: red;">*</span></label>
							<div class="col-md-7">
								<div class="input-group">
									<input type="text" name="curPeriodExcitation" class="form-control" value="0" readonly="true" /><span
										class="input-group-addon">期</span>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								id="editExcitationProjectSubmit">保存</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
    </div> 
	<!-- modal end -->
	
	<script type="text/json" id="permissions">{
			export:'${ btnAu['livePayOrder/manage/export']}',
            update:'${ btnAu['livePayOrder/manage/export']}' 
		}
	</script>
	<jsp:include page="../common.jsp"></jsp:include>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<script src="<%=path%>/js/live_anchor/livePayOrderManage.js?v=1.0.5"></script>
</body>
</html>