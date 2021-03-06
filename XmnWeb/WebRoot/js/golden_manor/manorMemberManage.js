var anchorList;
var initListUrl = "manorMember/manage/init/list.jhtml";
var imgRoot = $("#fastfdsHttp").val();
$(function() {
	inserTitle(
			' > 黄金庄园 > <a href="manorMember/manage/init.jhtml" target="right">庄园会员管理</a>',
			'userSpan', true);
	anchorList = $("#anchorList").page({
		url : initListUrl,
		success : success,
		pageBtnNum : 10,
		paramForm : 'searchForm',
		param : {
			activityType : "5"
		}
	});
	
	//导出
	$("#exportAnchor").click(function(){
		/*var size=getCurrentDataSize();
		if(size>1000){
			showWarningWindow("warning", "单次最多可导出1000条数据，请输入查询条件！",9999);
			return ;
		}*/
		
		var path="manorMember/manage/export.jhtml";
		$form = $("#searchForm").attr("action",path);
		$form[0].submit();
	});	

});

function success(data, obj) {
	var formId = "searchForm";
	var title = "庄园会员列表", subtitle = "个庄园会员";
	var captionInfo = '<caption style="background:#EED8D8; text-align:left; color:#703636; font-size:16px; line-height:40px;">&nbsp;&nbsp;'
			+ title
			+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>共计【'
			+ data.total
			+ '】' + subtitle + '&nbsp;</font></caption>';
	var callbackParam = "isBackButton=true&callbackParam="+ getFormParam($("#" + formId).serialize());

	obj.find('div').eq(0).scrollTablel({
		identifier : "id",
		callbackParam : callbackParam,
		data : data.content,
		caption : captionInfo,
		// checkable : checkable,
		// 操作列
		handleCols : {
			title : "操作",// 标题
			queryPermission : [ "update"],// 不需要选择checkbox处理的权限
			width : 100,// 宽度
			// 当前列的中元素
			cols : [ 
		        {
					title : "设为买家",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "manorMember/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "update"// 列权限
					},
					customMethod : function(value, data){
						var desc = data.purchasable== 1 ? "设为买家" : "取消买家";
			            var value = "<a href='javascript:updatePurchasable(\""+data.id+"\", \""+data.purchasable+"\")'>" + desc + "</a>";
			            return value;
				    }
				},{
					title : "操作",// 标题
					linkInfoName : "href",
					linkInfo : {
						href : "manorMember/manage/delete.jhtml",// url
						param : ["id"],// 参数
						permission : "update"// 列权限
					},
					customMethod : function(value, data){
						var desc = data.manorStatus== 1 ? "停用" : "启用";
			            var value = "<a href='javascript:updateStatus(\""+data.id+"\", \""+data.uid+"\", \""+data.manorStatus+"\")'>" + desc + "</a>";
			            return value;
				    }
				 }
			]
		},
		cols : [ {
			title : "会员编号",
			name : "uid",
			type : "string",
			width : 80
		},{
			title : "会员昵称",
			name : "nickname",
			type : "string",
			width : 120
		}, {
			title : "手机号码",
			name : "phone",
			type : "string",
			width : 80
		}, {
			title : "状态",
			name : "manorStatus",
			type : "string",
			width : 60,
			customMethod:function(value,data){
				var result="";
				var manorStatus = data.manorStatus;  //庄园状态 1:启用 2:停用
				if(manorStatus == 1){
					result="启用";//替换所有逗号
				}else if(manorStatus == 2){
					result="停用";//替换所有逗号
				}
				
				return result;
			}
		},{
			title : "庄园等级",
			name : "levelName",
			type : "string",
			width : 100
		}, {
			title : "推荐人",
			name : "superName",
			type : "string",
			width : 100
		}, {
			title : "下级",
			name : "comsumLedger",
			type : "string",
			width : 80,
			customMethod:function(value,data){
				var value = "<a href='javascript:lowerLevelView(\""+data.id+"\", \""+data.uid+"\")'>" + data.lowerLevelNumber + "</a>";
				return value;
			}
/*		} , {
			title : "阳光/仓库",
			name : "expectedPriviledgeLedger",
			type : "string",
			width : 200,
			customMethod:function(value,data){
				var sunNumber = data.sunNumber == undefined ? '-' : data.sunNumber;
				var repositoryNumber = data.repositoryNumber == undefined ? '-' : data.repositoryNumber;
				return sunNumber + '/' + repositoryNumber;
			}*/
		} , {
			title : "花蜜（罐/滴）",
			name : "currentConsumeLedger",
			type : "string",
			width : 100,
			customMethod:function(value,data){
				var nectarStoreNumber = data.nectarStoreNumber == undefined ? '-' : data.nectarStoreNumber + '罐';
				var nectarNumber = data.nectarNumber == undefined ? '-' : data.nectarNumber + '滴';
				return nectarStoreNumber + '<br />' + nectarNumber;
			}
		}, {
			title : "累计收益鸟币",
			name : "converCoin",
			type : "string",
			width : 100
		}]
	}, permissions);
}

/**
 * 删除操作
 */
 function updateStatus(id,uid, manorStatus){
	//庄园状态 1:启用 2:停用
	 var status;  
	 if (manorStatus == 1){
		 status = 2;
	 }else{
		 status = 1;
	 }
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "manorMember/manage/update.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id,uid:uid,manorStatus:status},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 anchorList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要修改记录吗？");
 }
 
 function updatePurchasable(id, purchasable){
	 //是否具备买家资格 1:不具备资格 2:具备资格
	 var status;  
	 if (purchasable == 1){
		 status = 2;
	 }else{
		 status = 1;
	 }
	 showSmConfirmWindow(function (){
		 $.ajax({
			 url : "manorMember/manage/update.jhtml",
			 type : "post",
			 dataType : "json",
			 data:{id:id, purchasable:status},
			 success : function(result) {
				 if (result.success) {
					 showSmReslutWindow(result.success, result.msg);
					 setTimeout(function() {
						 anchorList.reload();
					 }, 3000);
				 } else {
					 window.messager.warning(result.msg);
				 }
			 }
		 });
	 },"确定要修改记录吗？");
 }
 
 /**
  * 获取当前查询记录数
  */
 function getCurrentDataSize(){
	 var formId = "searchForm";
	 var total=0;
	// 设置同步
    $.ajaxSetup({
        async: false
    });
	 
	$.ajax({
		 url : "manorMember/manage/init/getCurrentDataSize.jhtml",
		 type : "post",
		 dataType : "json",
		 data:jsonFromt($('#' + formId).serializeArray()),
		 success : function(result) {
			 total=result;
		 }
	});
	 
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
    
    return total;
 }
 


 /*查看下级列表信息*/
 function lowerLevelView(id, uid) {
 	// 初始化值清空栏位
 	$("#lowerLevelModal :input").each(function() {
 		$(this).val("");
 	});
 	$("#lowerLevelTB").html("");
 	//加载数据
 	var url='manorMember/manage/list/viewLowerLevel.jhtml';
 	$.ajax({
 		type : 'post',
 		url : url,
// 		data :[id, uid],
		data:{id:id,uid:uid},
 		dataType : 'json',
 	   beforeSend: function (XMLHttpRequest) {
           $('#prompt').show();
       },
 		success :  function(data) {
 			$('#prompt').hide();
 			viewSuccess(data);
 		},
 		error : function(XMLHttpRequest, textStatus, errorThrown) {
 			$('#prompt').hide();
 			$('#lowerLevelModal').modal('hide');
 		}
 	});
 }

 function viewSuccess(data) {
 	if (data.data){//获取到数据
        //循环仓库储存收益信息
 		for(var i = 0;i<data.data.length;i++){
 			//加载
 			var floristUid = data.data[i].floristUid;
 			var floristName = data.data[i].floristName;
 			var currentFlowerRelationCount = data.data[i].currentFlowerRelationCount;
 			var totalFlowerRelationCount = data.data[i].totalFlowerRelationCount;
 			
 			var tr = $("<tr id=" + floristUid + " class='text-center' >").append($("<td>").text(floristUid)).append($("<td>").text(floristName)).append($("<td>").text(currentFlowerRelationCount)).append($("<td>").text(totalFlowerRelationCount));
 			//添加仓库储存收益信息 $("#areaTable tr:last").after(tr);
 			$("#lowerLevelTB").append(tr);
 		}
 	}
 	
 	// 显示模态框数据
 	$('#lowerLevelModal').modal();
 	
 	// 点击关闭遮罩层
 	$(".close-shade").on("click", function() {
 		$(".shade-box,.shade-content").hide();
 	});
 }
