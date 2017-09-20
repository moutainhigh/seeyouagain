var xmerList1;
$(document).ready(function() {
	xmerList1 = $('#xmerList1').page({
		url : 'xmer/manage/init/list.jhtml',
		success : success1,
		pageBtnNum :10,
		pageSize:15,
		paramForm : 'searchForm1'
	});
	

//	$("#myTab li").click(function(){
//		console.info("选中了第"+$(this).index()+"个");
//		var showTab =  $(this).index();
//		if(showTab==0){
//			$("#objectOriented").val(5);
//			xmerList1.reload();
//		}else if(showTab==1){
//			$("#objectOriented").val(8);
//			xmerList2();
//		}else if(showTab==2){
//			$("#objectOriented").val(7);
//			xmnerList3.relaod();
//		}else if(showTab==3){
//			$("#objectOriented").val(6);
//		}else{
//			$("#objectOriented").val(null);
//		}
//	});
		
	inserTitle(' > 寻蜜客管理 > <a href="xmer/manage/init.jhtml" target="right">寻蜜客成员管理</a>','xmerList',true);
});

/**
 * 构件表格
 * 
 * @param data
 * @param obj
 */
function success1(data, obj) {
	var picTitle;
	var typeTitle;
	var contentTitle;
	var sortTiltle;
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;"><font style="float:left;">&nbsp;&nbsp;平台寻蜜客总数：'+data.total+'人&nbsp;&nbsp;商户端出售总数：'+(0 == data.content.length ? "0":data.content[0].sellCount)+'套&nbsp;&nbsp;平台店铺流水总额：'+(0 == data.content.length ? "0.00":data.content[0].sellerCrrentCountStr)+'元</font></caption>';
	var callbackParam="isBackButton=true&callbackParam="+getFormParam($("#searchForm1").serialize());
	obj.find('div').eq(0).scrollTablel({
			checkable : false,
	    	identifier : "",
	    	tableClass :"table-bordered table-striped info",
	    	callbackParam : callbackParam,
	    	caption : captionInfo,
			//数据
			data:data.content, 
			 //数据行
			cols:[{
					title : "寻蜜客编号",// 标题
					name : "uid",
					//sort : "up",
					width : 100,// 宽度
					leng : 3,//显示长度
					type:"number",//数据类型
			},{
				title : "寻蜜客姓名",// 标题
				name : "name",
				width : 150,// 宽度
				type:"string"//数据类型
				
		},{
			title : "手机号",// 标题
			name : "phoneid",
			//sort : "up",
			width : 150,// 宽度
			type:"string"//数据类型
			
		},{
			title : "签约日期",// 标题
			name : "sdateStr",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "状态",// 标题
			name : "status",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"number",//数据类型
				customMethod : function(value, data) {
					if(data.status==0){
						return "启用";
					} 
					if(data.status==1){
						return "停用";
					} 
					return "-";
				}
		},{
			title : "他的伙伴",// 标题
			name : "partnerNums",
			leng : 8,//显示长度
			type:"string",//数据类型
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/manage/directPartner/init.jhtml"
				},
				param : ["uid"],
				customParam:["objectOriented=5"],
				permission : "dp",
			},
			customMethod : function(value, data) {
				if(data.partnerNums != null && data.partnerNums != ""){
					return $(value).html(data.partnerNums);
				}
				return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0</a>');
			},
			width : 120
		},{
			title : "我的店铺",// 标题
			name : "soldNums",
			//sort : "up",
			leng : 8,//显示长度
			type:"string",//数据类型
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/manage/xmerSeller/init.jhtml"
				},
				param : ["uid"],
				customParam:["saasType=1"],
				permission : "xmer",
			},
			customMethod : function(value, data) {
				if(data.soldNums != null && data.soldNums != ""){
					return $(value).html(data.soldNums);
				}
				return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0</a>');
			},
			width : 120
		},{
			title : "余额/元",// 标题
			name : "differenceStr",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},
//		{
//			title : "他的消费金额/元",
//			name : "sumMoney",
//			type : "string",
//			isLink : true,
//			link : {
//				linkInfoName : "href",
//				linkInfo : {
//					href : "xmer/finance/sumMoney/init.jhtml"
//				},
//				param : ["uid"],
//				permission : "xmer",
//			},
//			customMethod : function(value, data) {
//				if(data.sumMoney != null && data.sumMoney != ""){
//					return $(value).html(data.sumMoneyStr);
//				}
//				return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0.00</a>');
//			},
//			width : 120
//		},
		{
			title : "他的总收入",// 标题
			name : "incomeStr",
			//sort : "up",
			width : 120,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},{
			title : "佣金/元",// 标题
			name : "commision",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/finance/walletRecord/commision/init.jhtml"
				},
//				param : ["accountid"],//寻蜜鸟会员钱包ID
				param : ["xid"],//寻蜜客钱包ID
				permission : "wallet",
			},
			customMethod : function(value, data) {
				if(data.commision != null && data.commision != ""){
					return $(value).html(data.commisionStr);
				}
				return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0.00</a>');
			},
			width : 120
		},{
			title : "他的流水收入/元",// 标题
			name : "balance",
			type : "string",
			isLink : true,
			link : {
				linkInfoName : "href",
				linkInfo : {
					href : "xmer/finance/walletRecord/balance/init.jhtml"
				},
//				param : ["accountid"],
				param : ["xid"],
				permission : "wallet",
			},
			customMethod : function(value, data) {
				if(data.balance != null && data.balance != ""){
					return $(value).html(data.balanceStr);
				}
				return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0.00</a>');
			},
			width : 150
		},
//		{
//			title : "押金/元",// 标题
//			name : "deposit",
//			type : "string",
//			isLink : true,
//			link : {
//				linkInfoName : "href",
//				linkInfo : {
//					href : "xmer/finance/walletRecord/deposit/init.jhtml"
//				},
//				param : ["accountid"],
//				permission : "wallet",
//			},
//			customMethod : function(value, data) {
//				if(data.deposit != null && data.deposit != ""){
//					return $(value).html(data.depositStr);
//				}
//				return $(value).html('<a href="javascript:;" disabled="disabled" style="color:#000000;">0.00</a>');
//			},
//			width : 120
//		},
		{
			title : "剩余软件套数/签约套数",// 标题
			name : "stockTotal",
			//sort : "up",
			width : 180,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		},
//		{
//			title : "576元软件剩余套数",// 标题
//			name : "eightFoldNums",
//			//sort : "up",
//			width : 160,// 宽度
//			leng : 8,//显示长度
//			type:"string"//数据类型
//		},{
//			title : "504元软件剩余套数",// 标题
//			name : "sevenFoldNums",
//			//sort : "up",
//			width : 160,// 宽度
//			leng : 8,//显示长度
//			type:"string"//数据类型
//		},
		{
			title : "剩余软件总价/元",// 标题
			name : "stockTotalPrice",
			//sort : "up",
			width : 160,// 宽度
			leng : 8,//显示长度
			type:"string"//数据类型
		}],
			//操作列
			handleCols : {
				title : "操作",// 标题
				queryPermission : ["update","check"],// 不需要选择checkbox处理的权限
				width : 130,// 宽度
				// 当前列的中元素
				cols : [{
							title : "编辑",// 标题
							linkInfoName : "href",
							linkInfo : {
							href : "xmer/manage/update/init.jhtml",// url
							param : ["uid"],// 参数
							permission : "update"// 列权限
						    }
				       },
						 {
							title : "查看",// 标题
							linkInfoName : "modal",
							linkInfo : {
								modal : {
									url : "xmer/manage/check.jhtml",// url
									position:"60px",// 模态框显示位置
									width:"800px"
								},
								param : ["uid"],// 参数
								permission : "check"// 列权限
						    }
					      }
				      ]
	}},permissions);
}
function substr(obj,length){
	if(obj.length > length){
		obj = obj.substring(0,length) +"...";
	}
	return obj;
}

$(function() {
	var today = new Date();
	var todaystr = addDate(today, 0);
	function addDate(date, days) {
		var d = new Date(date);
		d.setDate(d.getDate() + days);
		var month = d.getMonth() + 1;
		var day = d.getDate();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		var val = d.getFullYear() + "-" + month + "-" + day;
		return val;
	}
});
	
$("#export1").click(function(){
		var url = "xmer/manage/export.jhtml";
		$form = $("#searchForm1").attr("action",url);
		$form[0].submit();
	});