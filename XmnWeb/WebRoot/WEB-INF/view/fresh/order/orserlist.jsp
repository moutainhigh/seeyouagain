<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="screen-desktop-wide device-desktop" lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>生鲜列表</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<link href="<%=path%>/resources/web/css/view.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/css/zui.css" rel="stylesheet">   
	<link href="<%=path%>/resources/zui/lib/chosen/chosen.css" rel="stylesheet">
	<link href="<%=path%>/resources/web/css/jquery.validate.css" rel="stylesheet">
	<link href="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.css" rel="stylesheet">
	<style>
	.submit{float: left;}
	.btn-add{
	    background: #FF5C5C;
    	width: 160px;
		margin-right: 20px;
		border: 1px solid #FF5C5C;
		line-height: 20px;
		text-align: center;
		font-size:16px;
	}
	table.info tr td {
    text-align: center;
    font-size: 14px;
    vertical-align:middle;
}
	</style>
  </head>
  
  <body  style="overflow-x: hidden;overflow-y: auto;min-width: 1024px;background:#EEE" class="doc-views with-navbar">
  	<div id="prompt" style="position: fixed; height: 100%; width: 100%; z-index: 9; background: url(resources/page/loading.gif) #FFF no-repeat center; filter: alpha(opacity =  50); opacity: 0.5; display: none;"></div>
  	<input type="hidden" id="path" value="<%=path%>" />
  	<ul id="myTab" class="nav nav-tabs">
      <li class="active">
        <a href="#tab1" data-toggle="tab">已支付订单</a>
      </li>
      <li class="">
        <a href="#tab2" data-toggle="tab">未支付订单</a>
      </li>
    </ul>
    <div class="tab-content">
    	<div id="tab1" class="tab-pane in active">
		<div class="panel">
			<div class="panel-body">
				<form class="form-horizontal" role="form" method="get" id="searchSubBillForm">
					<table style="width:100%;">
						<tbody>
							<tr>
								<td style="width:5%;"><h5>订单编号:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" id="subOrderSn" name="subOrderSn" style = "width:90%;"/></td>
								<td style="width:5%;"><h5>收货人手机号:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" id="mobile" name="mobile" style = "width:90%;"/></td>
								<td style="width:5%;"><h5>收货人:</h5></td>
								<td style="width:18%;"><input type="text" class="form-control" id="consignee" name="consignee" style = "width:90%;"/></td>
							</tr>
							<tr>
								<td style="width:5%;"><h5>下单时间:</h5></td>
								<td style="width:18%;">
									<input type="text" class="form-control" name="subCreateSdate" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'edate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							    		<label style="float: left;">&nbsp;--&nbsp;</label>
							 		<input type="text" class="form-control" name="subCreateEdate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
								</td>
								<td style="width:5%;"><h5>活动类型:</h5></td>
								<td style="width:18%;"><select name="activityType"  class="form-control" style="width:90%;float:left">
									<option value="-1">-全部-</option>
									<option value="0">采购</option>
									<option value="1">营销活动</option>
									<option value="3">秒杀</option>
									<option value="4">商品专区</option>
									<option value="5">赠送</option>
								</select></td>
							<td style="width:5%;"><h5>发货状态:</h5></td>
								<td style="width:18%;">
									<select type="text" class="form-control" id="status" name="status" style = "width:90%;">
										<option value="">--请选择--</option>
										<option value="0">待发货</option>
										<option value="1">已发货</option>
										<option value="2">订单锁定</option>
										<option value="3">退款中</option>
										<option value="4">退款失败</option>
										<option value="5">已退款</option>
										<option value="6">退款完成</option>
									</select></td>
								<td colspan="2" style="width:29%;">
									<div class="submit" style="text-align: left;width:100%;">
										<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
										<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
					<c:if test="${btnAu['fresh/subOrder/export']}">
						<button type="button" id="exportSubOrder" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;订单导出</button>
					</c:if>
					<c:if test="${btnAu['fresh/subOrder/export']}">
						<button type="button" id="exportSubOrder1" class="btn btn-default" >
						<span class="icon-download-alt"></span>&nbsp;对账导出</button>
					</c:if>
				</div>
				<div id="subOrderList"></div>
			</div>
		</div>
	</div>
    <div id="tab2" class="tab-pane">
  	<div class="panel">
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="get" id="searchBillForm">
				<table style="width:100%;">
					<tbody>
						<tr>
							<td style="width:5%;"><h5>订单编号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="bid" name="bid" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>物流单号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="logistics" name="logistics" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>消费金额:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="money" name="money" style = "width:90%;"/></td>
							<td style="width:5%;"><h5>用户手机:</h5></td>
							<td style="width:18%;">
								<input type="text" class="form-control" id="phoneid" name="phoneid" style = "width:90%;"/>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>支付流水号:</h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="number" name="number" style = "width:90%;" placeholder=""/></td>
						  	<td style="width:5%;"><h5>下单时间:</h5></td>
						 	<td style="width:18%;">
							  <input type="text" class="form-control"  id="sdate" name="sdate" value="" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'edate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  id="edate" name="edate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'sdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" style="width:42%;float:left"/>
							</td>	
							<td style="width:5%;"><h5>物流状态:</h5></td>
						 	<td style="width:18%;">
						 	   <select class="form-control" id="wstatus" name="wstatus" style = "width:90%;">
									<option value="">请选择</option>
									<option value="0">未发货</option>
									<option value="1">已发货</option>
									<option value="2">已收货</option>
								</select>
							</td>
							<td style="width:5%;"><h5>订单状态:</h5></td>
							<td style="width:18%;">
							  <select class="form-control" id="status" name="status" style = "width:90%;">
									<option value="">请选择</option>
									<option value="0">待支付</option>
									<option value="1">已支付</option>
									<option value="2">取消订单</option>
									<option value="3">交易成功</option>
									<option value="4">用户退款处理中</option>
									<option value="5">退款成功</option>
									<option value="6">支付退款处理中</option>
									<option value="7">支付退款失败</option>
									<option value="8">平台退款处理中</option>
								</select>
							</td>
						</tr>
						<tr>
							<td style="width:5%;"><h5>收货人电话:<h5></td>
							<td style="width:18%;"><input type="text" class="form-control" id="mobile" name="mobile" style = "width:90%;" placeholder=""/></td>
						  	<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
						 	<td style="width:5%;"></td>
						 	<td style="width:18%;"></td>
							<td colspan="2" style="width:29%;">
								<div class="submit" style="text-align: left;width:100%;">
									<input class="submit radius-3" type="button"  value="查询全部"  data-bus = 'query' style="width:43%;flaot:left;margin:0 2%;"/>
									<input  class="reset radius-3" type="reset" value="重置全部"   data-bus = 'reset' style="width:43%;flaot:left;margin:0 2%;"/>
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
			</div>
			<div class="btn-group" style="margin-bottom: 5px;">
				<c:if test="${btnAu['fresh/order/export']}"><button type="button" id="export" class="btn btn-default" ><span class="icon-download-alt"></span>&nbsp;订单导出</button>
				</c:if>
			</div>
			<div id="orderList"></div>
		</div>
	</div>
	</div>
	</div>
	<!-- 发货选项模态框（Modal） -->
	<div class="modal fade" id="subfhModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeFhModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">发货选项</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="fhform">
						<div class="form-group">
							<label for="courierType" class="col-sm-2 control-label">快递公司：</label>
							<div class="col-sm-10">
								<select id="courierType" class="form-control" style="width:240px;">
									<option value="">请选择</option>
									<option value="huitongkuaidi">百世快递</option>
									<option value="sfexpress">顺丰速递</option>
									<option value="zto">中通快递</option>
									<option value="yto">圆通快递</option>
									<option value="yunda">韵达快递</option>
									<option value="sto">申通快递</option>
									<option value="ttkdex">天天快递</option>
									<option value="jd">京东快递</option>
									<option value="guangdongyouzhengwuliu">广东邮政</option>
									<option value="ems">EMS</option>
									<option value="tiantian">天天快递</option>
									<option value="zhaijisong">宅急送</option>
									<option value="debangwuliu">德邦物流</option>
									<option value="guotongkuaidi">国通快递</option>
									<option value="jiajiwuliu">佳吉物流</option>
									<option value="youshuwuliu">速尔快递</option>
									<option value="quanfengkuaidi">全峰快递</option>
									<option value="kuaijiesudi">快捷速递</option>
									<option value="lianbangkuaidi">联邦快递</option>
									<option value="dhl">DHL</option>
									<option value="youshuwuliu">优速物流</option>
									<option value="tiandihuayu">天地华宇</option>
						  		</select>
							</div>
						</div>
						<div class="form-group">
							<input type="hidden" id="subOrderId"> 
							<label for="courierNumber" class="col-sm-2 control-label">快递单号：</label>
							<div class="col-sm-10">
								<input id="courierNumberId" name="courierNumber" type="text" style="width:240px;" class="form-control" placeholder="">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="fhconcel" type="button" class="btn btn-default" data-dismiss="modal">取消发货
					</button>
					<button id="fhconfirm" type="button" class="btn btn-default">确认发货</button>
				</div>
			</div>
		</div>
     </div>
     <!-- 按日期导出子订单的模态框 -->
     <div class="modal fade" id="exportSubOrderModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请选择下单时间</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="exporsubform">
						<div class="form-group">
							<label for="courierNumber" class="col-sm-2 control-label"><h5>下单时间:</h5></label>
							<div class="col-sm-10">
							  <input type="text" class="form-control"  id="exportSubSdate" name="subexsdate" value=""  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSubEdate\',{M:-2})||$dp.$DV(\'%y-%M-%d\',{M:-2})}',maxDate:'#F{$dp.$D(\'exportSubEdate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" onblur="valiSubDate()" style="width:40%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  id="exportSubEdate" name="subexedate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSubSdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" onblur="valiSubDate()" style="width:40%;float:left"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="exportsubconcel" type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="exportsubconfirm" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
     
      <!-- 按日期导出子订单的模态框  （只导父订单） -->
     <div class="modal fade" id="exportSubOrderModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="closeSubExportModal" type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">请选择下单时间</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="exporsubform1">
						<div class="form-group">
							<label for="courierNumber" class="col-sm-2 control-label"><h5>下单时间:</h5></label>
							<div class="col-sm-10">
							  <input type="text" class="form-control"  id="exportSubSdate1" name="exsdate" value=""  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSubEdate\',{M:-2})||$dp.$DV(\'%y-%M-%d\',{M:-2})}',maxDate:'#F{$dp.$D(\'exportSubEdate\')||\'%y-%M-%d\'}',dateFmt:'yyyy-MM-dd'})" onblur="valiSubDate()" style="width:40%;float:left"/>
							    <label style="float: left;">&nbsp;--&nbsp;</label>
							  <input type="text" class="form-control"  id="exportSubEdate1" name="exedate" value="" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'exportSubSdate\')}',maxDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})" onblur="valiSubDate()" style="width:40%;float:left"/>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="exportsubconcel1" type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button id="exportsubconfirm1" type="button" class="btn btn-default">确认</button>
				</div>
			</div>
		</div>
     </div>
     <!-- 操作结果提示层 -->
	<div class="modal fade" id="sm_reslut_window" data-position="100px">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>
	<script type="text/json" id="permissions">
		{shipments:'${btnAu['fresh/subOrder/shipments']}',refund:'${btnAu['fresh/subOrder/refund']}',check:'${btnAu['fresh/order/check']}',confirmReceive:'${btnAu['fresh/subOrder/confirmReceive']}',checkSubOrder:'${btnAu['fresh/subOrder/check']}',orderRefund:'${btnAu['fresh/order/refund']}'}
	</script>
	<jsp:include page="../../common.jsp"></jsp:include>
	<script src="<%=path%>/resources/zui/lib/chosen/chosen.js"></script>
	<script src="<%=path%>/js/fresh/order/orderlist.js"></script>
	<script src="<%=path%>/js/fresh/order/subOrderlist.js"></script>
	<script src="<%=path%>/resources/zui/lib/datetimepicker/datetimepicker.min.js"></script>
	<script src="<%=path%>/ux/js/scrollTablel.js"></script>
	<!-- 引入时间插件 -->
   <script src="<%=path%>/resources/datapicker/WdatePicker.js"	type="text/javascript"></script>
  </body>
</html>