<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>交易详情</title>
<meta name="renderer" content="webkit"> <meta name="fragment" content="!">
<meta name="format-detection" content="telephone=no">
<meta name="google" value="notranslate">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-transform">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link rel="stylesheet" href="${ctx}/css/normalize.css">
<link rel="stylesheet" href="${ctx}/css/common.css">
<link rel="stylesheet" href="${ctx}/css/swiper.css">
<link rel="stylesheet" href="${ctx}/css/marketing.css"/>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
</head>
<body class="padd-fill-tb bg-color-01">
	<input type="hidden" id="type" value={}/>
    <div class="container-wrap">
        <div class="expendtails-module">
            <div class="expendtails-wrap">
                <div class="expend-user-loge">
	                <c:choose>
	                        <c:when test="${empty order.avatar}">
	                            <img src="${ctx}/imgs/redpacket/Binding-Member2@2x.png"/>
	                        </c:when>
	                        <c:otherwise>
	                            <img src="${order.avatar}" alt="会员头像"/>
	                        </c:otherwise>
	                </c:choose>
                </div>
                <div class="expend-user-name">${order.userName }</div>
                <div class="expend-user-state"><span>
                 	<c:choose>
	                        <c:when test="${order.isBind==0}">
	                           				 绑定会员
	                        </c:when>
	                        <c:otherwise>
	                            			普通会员
	                        </c:otherwise>
	                </c:choose>
                
                </span></div>
            </div>
        </div>
        <c:if test="${type!=8}">
        	<div class="fill-list-module" >
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.awardTime }</span><span class="item-name">领取时间</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">
            <c:if test="${order.useTime==''|| order.useTime==null}">
           		 未使用
            </c:if>
            <c:if test="${order.useTime!=''&& order.useTime!=null }">
           		 ${order.useTime }
            </c:if>
            </span><span class="item-name">使用时间</span></div></div>
            <c:if test="${type==6}">
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.orderId }</span><span class="item-name">绑定订单</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.serialNo }</span><span class="item-name">现金券编码</span></div></div>
            </c:if>
            <c:if test="${type==7}">
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.serialNo }</span><span class="item-name">赠品券编码</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">
            		<c:if test="${order.isVerify==0}">
            			否
            		</c:if>
            		<c:if test="${order.isVerify==1}">
            			是
            		</c:if>
            	</span><span class="item-name">是否核销</span></div></div>
            </c:if>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">
            	<c:if test="${order.isShare==1}">
            		是
            	</c:if>
            	<c:if test="${order.isShare==0}">
            		否
            	</c:if>
            	</span><span class="item-name">是否分享</span></div></div>
            <div class="list-wrap"><div class="list-item"><span class="item-input-wrap">
            	<c:if test="${order.getWay==1}">
            		系统自动发放
            	</c:if>
            	<c:if test="${order.getWay==2}">
            		用户领取获得
            	</c:if>
            	<c:if test="${order.getWay==3}">
            		抽奖活动获得
            	</c:if>
            	<c:if test="${order.getWay==4}">
            		消费满获得
            	</c:if>
            	<c:if test="${order.getWay==5}">
            		参与活动获得
            	</c:if>
            	<c:if test="${order.getWay==6}">
            		绑定商户获得
            	</c:if>
            	</span><span class="item-name">获取途径</span></div></div>
            	
        </div>
        </c:if>
        <c:if test="${type==8}">
        	<div class="fill-list-module" >
            	<div class="list-wrap">
                	<div class="list-item list-imgpic item-icon-left">
                		<c:choose>
                			<c:when test="${order.amount=='1000003' }">
                				<i class="icon-wrap icon-weixin"></i>
                			</c:when>
                			<c:when test="${order.amount=='1000001' }">
                				<i class="icon-wrap icon-zhifubao"></i>
                			</c:when>
                		</c:choose>
                    	<span class="item-input-wrap"><strong>￥${order.realIncome }</strong><font>实际收益</font></span>
                	</div>
            	</div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${order.amount }</span><span class="item-name">订单金额</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">-￥${order.reduction }</span><span class="item-name">优惠金额</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${order.realPay }</span><span class="item-name">实际支付</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">-￥${order.rebate }</span><span class="item-name">分账金额</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">￥${order.realIncome }</span><span class="item-name">实际收益</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">
            		<c:if test="${order.isaccount==0 }">
            			未分账
            		</c:if>
            		<c:if test="${order.isaccount==1 }">
            			已分账
            		</c:if>	
            		</span><span class="item-name">订单状态</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.paytime }</span><span class="item-name">交易时间</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.orderId }</span><span class="item-name">交易号</span></div></div>
            	<div class="list-wrap"><div class="list-item"><span class="item-input-wrap">${order.codeid }</span><span class="item-name">支付确定码</span></div></div>
        	</div>
        	<p class="fill-list-desc align-center">
            	该笔交易已结清，若有疑问请联系微信客服<br>
            	     (微信客服：weixinkefu)
        	</p>
        </c:if>
    </div>
</body>
</html>