<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>促销优惠</title>
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
</head>
<body class="padd-fill-tb bg-color-01">
<input type="hidden" value="${couponId}" id="couponId"/>
<input type="hidden" value="${type}" id="type"/>
<input type="hidden" value="${title}" id="title"/>
    <div class="container-wrap">
    	<c:if test="${type==3}">
        	<div class="fill-list-module resetfill-list">
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="cname">${coupon.cname }</span><span class="item-name">抵用券名称</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="denomination">${coupon.denomination }</span><span class="item-name">抵用金额</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="usecondition">满${coupon.useCondition }可用</span><span class="item-name">使用条件</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="sendnum">${coupon.sendNum }张</span><span class="item-name">发放数量</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item">
                	<span class="item-input-wrap" id="awardCondition">
                		<c:choose>
                			<c:when test="${coupon.awardCondition==0}">
                				无限制
                			</c:when>
                			<c:when test="${coupon.awardCondition==1}">
                				消费满 ${coupon.payAndConsume }元获得
                			</c:when>
                			<c:when test="${coupon.awardCondition==2}">
                				新用户绑定获得
                			</c:when>
                 		</c:choose>
                 	</span><span class="item-name">领取条件</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="activityTime">${coupon.startTime }至${coupon.endTime }</span><span class="item-name">活动时间</span></div>
            </div>
        	</div>
        	<p class="fill-list-desc">
          	 	* 现金抵用券，客户到店消费满特定条件后，自动赠送一张优惠券，下次消费时自动使用该优惠券
        	</p>
    	    </div>     	
        </c:if>
    	<c:if test="${type==4}">
        	<div class="fill-list-module resetfill-list">
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="cname">${coupon.cname }</span><span class="item-name">赠品名称</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="description">${coupon.description }</span><span class="item-name">赠品描述</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="send-num">${coupon.sendNum }</span><span class="item-name">发放数量</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="award-condition">
                	<c:choose>
                		<c:when test="${coupon.awardCondition==0}">
                			无限制
                		</c:when>
                		<c:when test="${coupon.awardCondition==1}">
                			消费满 ${coupon.payAndConsume }元获得
                		</c:when>
                		<c:when test="${coupon.awardCondition==2}">
                			新用户绑定获得
                		</c:when>
                 	</c:choose>
                </span><span class="item-name">获得条件</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="limit-condition">
                	<c:if test="${coupon.limitNumber==0}">
                		无限制
                	</c:if>
                	<c:if test="${coupon.limitNumber==1}">
                		每人限领一张
                	</c:if>
                </span><span class="item-name">限领条件</span></div>
            </div>
            <div class="list-wrap">
                <div class="list-item"><span class="item-input-wrap" id="activity-time">${coupon.startTime }至${coupon.endTime }</span><span class="item-name">活动时间</span></div>
            </div>
        	</div>
        	<p class="fill-list-desc">
          	 	* 赠品劵，客户到店消费满特定条件后，自动赠送一张赠品劵，下次消费时自动使用该赠品劵
        	</p>
    	    </div>     	
        </c:if>
        <c:if test="${type==5}">
        	<div class="fill-list-module resetfill-list">
            	<div class="list-wrap">
                	<div class="list-item"><span class="item-input-wrap">${fullreduction.cname }</span><span class="item-name">满就减名称</span></div>
            	</div>
            	<div class="list-wrap">
                	<div lass="list-item">
                	<span class="item-input-wrap">
                		<c:if test="${fullreduction.isDerate==1 }">
                			满${fullreduction.consumeAndPay1 }减${fullreduction.derateLevel1Amount },
                			满${fullreduction.consumeAndPay2 }减${fullreduction.derateLevel2Amount },
                			满${fullreduction.consumeAndPay3 }减${fullreduction.derateLevel3Amount }
                		</c:if>
                		<c:if test="${fullreduction.isDerate==0 }">
                			满${fullremduction.consumeAndPay }减${fullreduction.offsetAmount }元
                		</c:if>
                	</span>
                	<span class="item-name">满就减规则</span></div>
            	</div>
            	<div class="list-wrap">
                	<div class="list-item">
                	<span class="item-input-wrap">
                		<c:if test="${fullreduction.limitNumber==0 }">
                			否
                		</c:if>
                		<c:if test="${fullreduction.limitNumber==1 }">
                			是
                		</c:if>
                	</span>
                	<span class="item-name">是否限制每人参与一次</span></div>
            	</div>
            	<div class="list-wrap">
                	<div class="list-item"><span class="item-input-wrap">${fullreduction.startTime } 
                		至 ${fullreduction.endTime }</span><span class="item-name">活动时间</span></div>
            	</div>
        	</div>
        	<p class="fill-list-desc">
            	* 满就减 创建活动以后，顾客消费过程中满特定金额后可以进行减免
        	</p>
        </c:if>
        <c:if test="${condition == 0}">
	        <div class="floor-module">
	        	<a class="floor-links links-type2" href="javaScript:void(0)" onclick="viweActivity()">预览</a>
	    	</div>
    	</c:if>
</body>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var type = $("#type").val();
		if(type=='3'){
			document.title = "现金抵用券";
		}
		if(type=='4'){
			document.title = "赠品券";
		}
		if(type=='5'){
			document.title = "满就减";
		}
	})
	function viweActivity(){
		  /*数据提交*/
        $.ajax({
        	type:"post",
        	url: basePath + '/h5/coupon/view_activity',
        	data:{'id': $("#couponId").val(),'title': $("#title").val(),'type':$("#type").val()},
        	dataType:"json",
        	success:function(data){
        		if(data.state==0){
    				window.location=data.result.url;
    			}else{
    				Tips.show('预览失败！');
    			}
        	}
        });
	};
</script>
</html>