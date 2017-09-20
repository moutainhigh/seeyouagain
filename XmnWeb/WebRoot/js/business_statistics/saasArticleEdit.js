var formId = "editForm";
var jsonTextInit;
var isType;
var RELATIVE_PATH="";
$(function(){
	
	isType=$("#isType").val();
	
	if(isType=='add'){
		inserTitle(' > <span>添加文章','saasCelebrityEdit',false);
	}else{
		inserTitle(' > <span>编辑文章','saasCelebrityEdit',false);
	}
	
	// 头像
	$("#imageDiv").uploadImg({
		urlId : "image",
		showImg : $('#image').val()
	});
	
	
	//清除ckEditor实例
	if(CKEDITOR.instances['ckeditor_standard']){
        CKEDITOR.instances['ckeditor_standard'].destroy(true);
	}
	//初始化富文本编辑器
    $("textarea#ckeditor_standard").ckeditor({
    	
    });
    
    //初始化关联作者
    initCelebrityId();
    
    //初始化关联商家
    initSellerid();
    
    //初始化文章图文类型：1:单页图文  2:多图相册
    initStatus();
	
	var dataformInit = $("#" + formId).serializeArray();
	jsonTextInit = JSON.stringify({
		dataform : dataformInit
	});
	
	
	//表单校验
	validate(formId, {
		rules : {
			name : {
				required : true
			}
		},
		messages:{
			name:{
				required:"请输入文章名称"
			}
		}
	}, save);
	
});

/**
 * 保存信息
 */
function save() {
//	debugger;
	var url;
	var suffix = ".jhtml";
	var isAdd = isType=='add' ? true : false;
	if (isAdd) {// 添加操作
		url = "saasArticle/manage/add" + suffix;
	} else {// 修改操作
		url = "saasArticle/manage/update" + suffix;
	}
	var dataform = $("#" + formId).serializeArray();
	var jsonText = JSON.stringify({
		dataform : dataform
	});
	
	var result=validateCustomer();
	if(!result){//自定义校验不通过
		return ;
	}
	
	if (isAdd || jsonTextInit != jsonText) {// 添加或者修改改了东西才会发送请求
		$.ajax({
			type : 'post',
			url : url,
			data : jsonFromt($('#' + formId).serializeArray()),
			dataType : 'json',
			beforeSend : function(XMLHttpRequest) {
				$('#prompt').show();
			},
			success : function(data) {
				if (data.success) {
					var url = contextPath +'/saasArticle/manage/init.jhtml';
					setTimeout(function() {
						location.href = url;
					}, 1000);
				}
				showSmReslutWindow(data.success, data.msg);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	} else {
		showWarningWindow('warning', "没做任何修改！");
	}
}



/**
 * 自定义校验方法
 */
function validateCustomer(){
//	debugger;
	var result=true;

	return result;
}


//初始化网红角色下拉框
function initCelebrityId(){
	//文章类型 1:名嘴食评  2:网红晒照
	var type=$("#type").val();
	type='';//网红晒图对应主播角色暂未添加,不过滤
	$("#celebrityId").chosenObject({
		hideValue : "id",
		showValue : "name",
		url : "saasRole/manage/getCelebrities.jhtml",
		filterVal:type,////过滤的值 (filterVal=type)
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

$('body').on("click",'#celebrityId_chosen .chosen-results li',function(){
//	debugger;
	var celebrityId =  $("#celebrityId").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "saasRole/manage/getCelebrityInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"id":celebrityId},
		success : function(data){
			if(data != null){
				$("#celebrityName").val(data.name);
			}
		}
	});
});

//初始化商家下拉框
function initSellerid(){
	$("#sellerId").chosenObject({
		hideValue : "sellerid",
		showValue : "sellername",
		url : "businessman/seller/getSellerIdAndName.jhtml",
		isChosen:true,//是否支持模糊查询
		isCode:true,//是否显示编号
		isHistorical:false,//是否使用历史已加载数据
		width:"100%"
	});
}

$('body').on("click",'#sellerId_chosen .chosen-results li',function(){
//	debugger;
	var sellerid =  $("#sellerId").find("option:selected").val();
	$.ajax({
		type: "POST",
		url : "businessman/seller/getSellerLandmarkInfoById.jhtml?t=new Date()",
		dataType : "json",
		data: {"sellerid":sellerid},
		success : function(data){
			if(data != null){
				$("#sellerName").val(data.sellername);
				$("#zoneName").val(data.areaTitle);
			}
		}
	});
});


/**
 * 根据图文类型，显示对应编辑区域
 */
function initStatus(){
	//1:单页图文  2:多图相册
	var status=$("#status").val();
	console.log('status='+status);
	
	if(status==1){
		$("#imageTr").css('display','table-row');
		$("#htmlContentTr").css('display','table-row');
		$("#photoTr").css('display','none');
	}else if(status==2){
		$("#imageTr").css('display','none');
		$("#htmlContentTr").css('display','none');
		$("#photoTr").css('display','table-row');
		photoAddBatchInit();
	}
}

function photoAddBatchInit(){
		var $ = jQuery,    // just in case. Make sure it's not an other libaray.
	    
	    $wrap = $('#uploader'),

	    // 图片容器
	    $queue = $('<ul class="filelist"></ul>')
	        .appendTo( $wrap.find('.queueList') ),

	    // 状态栏，包括进度和控制按钮
	    $statusBar = $wrap.find('.statusBar'),

	    // 文件总体选择信息。
	    $info = $statusBar.find('.info'),

	    // 上传按钮
	    $upload = $wrap.find('.uploadBtn'),

	    // 没选择文件之前的内容。
	    $placeHolder = $wrap.find('.placeholder'),

	    // 总体进度条
	    $progress = $statusBar.find('.progress').hide(),

	    // 添加的文件数量
	    fileCount = 0,

	    // 添加的文件总大小
	    fileSize = 0,

	    // 优化retina, 在retina下这个值是2
	    ratio = window.devicePixelRatio || 1,

	    // 缩略图大小
	    thumbnailWidth = 110 * ratio,
	    thumbnailHeight = 110 * ratio,

	    // 可能有pedding, ready, uploading, confirm, done.
	    state = 'pedding',

	    // 所有文件的进度信息，key为file id
	    percentages = {},

	    supportTransition = (function(){
	        var s = document.createElement('p').style,
	            r = 'transition' in s ||
	                  'WebkitTransition' in s ||
	                  'MozTransition' in s ||
	                  'msTransition' in s ||
	                  'OTransition' in s;
	        s = null;
	        return r;
	    })(),

	    // WebUploader实例
	    uploader;

		if ( !WebUploader.Uploader.support() ) {
		    alert( 'Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
		    throw new Error( 'WebUploader does not support the browser you are using.' );
		}

		// 实例化
		uploader = WebUploader.create({
			fileVal : "filedata",
			
		    pick: {
		        id: '#filePicker',
		        label: '点击选择图片'
		    },
		    dnd: '#uploader .queueList',
		    paste: document.body,
		
		    accept: {
		        title: 'Images',
		        extensions: 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/*'
		    },
		
		    // swf文件路径
		    swf: BASE_URL + 'resources/webuploader/Uploader.swf',
		
		    disableGlobalDnd: true,
		
		    chunked: true,
		    server : BASE_URL + 'uploadFile.jhtml?sltflag=true',
		    fileNumLimit: 300,
		    fileSizeLimit: 50 * 1024 * 1024,    // 50 M
		    fileSingleSizeLimit: 1 * 1024 * 1024    // 1 M
		});

		// 添加“添加文件”的按钮，
		uploader.addButton({
		    id: '#filePicker2',
		    label: '继续添加'
		});
		
		// 当有文件添加进来时执行，负责view的创建
		function addFile( file ) {
		    var $li = $( '<li id="' + file.id + '">' +
		            '<p class="title">' + file.name + '</p>' +
		            '<p class="imgWrap"></p>'+
		            '<p class="progress"><span></span></p>' +
		            '</li>' ),
		
		        $btns = $('<div class="file-panel">' +
		            '<span class="cancel">删除</span>' +
		            '<span class="rotateRight">向右旋转</span>' +
		            '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
		        $prgress = $li.find('p.progress span'),
		        $wrap = $li.find( 'p.imgWrap' ),
		        $info = $('<p class="error"></p>'),
		
		        showError = function( code ) {
		            switch( code ) {
		                case 'exceed_size':
		                    text = '文件大小超出';
		                    break;
		
		                case 'interrupt':
		                    text = '上传暂停';
		                    break;
		
		                default:
		                    text = '上传失败，请重试';
		                    break;
		            }
		
		            $info.text( text ).appendTo( $li );
		        };
		
		    if ( file.getStatus() === 'invalid' ) {
		        showError( file.statusText );
		    } else {
		        // @todo lazyload
		        $wrap.text( '预览中' );
		        uploader.makeThumb( file, function( error, src ) {
		            if ( error ) {
		                $wrap.text( '不能预览' );
		                return;
		            }
		
		            var img = $('<img src="'+src+'">');
		            $wrap.empty().append( img );
		        }, thumbnailWidth, thumbnailHeight );
		
		        percentages[ file.id ] = [ file.size, 0 ];
		        file.rotation = 0;
		    }
		
		    file.on('statuschange', function( cur, prev ) {
		        if ( prev === 'progress' ) {
		            $prgress.hide().width(0);
		        } else if ( prev === 'queued' ) {
		            $li.off( 'mouseenter mouseleave' );
		            $btns.remove();
		        }
		
		        // 成功
		        if ( cur === 'error' || cur === 'invalid' ) {
		//        	debugger;
		//          console.log( file.statusText );
		            showError( file.statusText );
		            percentages[ file.id ][ 1 ] = 1;
		        } else if ( cur === 'interrupt' ) {
		            showError( 'interrupt' );
		        } else if ( cur === 'queued' ) {
		            percentages[ file.id ][ 1 ] = 0;
		        } else if ( cur === 'progress' ) {
		            $info.remove();
		            $prgress.css('display', 'block');
		        } else if ( cur === 'complete' ) {
		            $li.append( '<span class="success"></span>' );
		        }
		
		        $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
		    });
		
		    $li.on( 'mouseenter', function() {
		        $btns.stop().animate({height: 30});
		    });
		
		    $li.on( 'mouseleave', function() {
		        $btns.stop().animate({height: 0});
		    });
		
		    $btns.on( 'click', 'span', function() {
		        var index = $(this).index(),
		            deg;
		
		        switch ( index ) {
		            case 0:
		                uploader.removeFile( file );
		                return;
		
		            case 1:
		                file.rotation += 90;
		                break;
		
		            case 2:
		                file.rotation -= 90;
		                break;
		        }
		
		        if ( supportTransition ) {
		            deg = 'rotate(' + file.rotation + 'deg)';
		            $wrap.css({
		                '-webkit-transform': deg,
		                '-mos-transform': deg,
		                '-o-transform': deg,
		                'transform': deg
		            });
		        } else {
		            $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
		        }
		
		
		    });
		
		    $li.appendTo( $queue );
		}
		
		// 负责view的销毁
		function removeFile( file ) {
		    var $li = $('#'+file.id);
		
		    delete percentages[ file.id ];
		    updateTotalProgress();
		    $li.off().find('.file-panel').off().end().remove();
		}
		
		function updateTotalProgress() {
		    var loaded = 0,
		        total = 0,
		        spans = $progress.children(),
		        percent;
		
		    $.each( percentages, function( k, v ) {
		        total += v[ 0 ];
		        loaded += v[ 0 ] * v[ 1 ];
		    } );
		
		    percent = total ? loaded / total : 0;
		
		    spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
		    spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
		    updateStatus();
		}
		
		function updateStatus() {
		    var text = '', stats;
		
		    if ( state === 'ready' ) {
		        text = '选中' + fileCount + '张图片，共' +
		                WebUploader.formatSize( fileSize ) + '。';
		    } else if ( state === 'confirm' ) {
		        stats = uploader.getStats();
		        if ( stats.uploadFailNum ) {
		            text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
		                stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>';
		        }
		
		    } else {
		        stats = uploader.getStats();
		        text = '共' + fileCount + '张（' +
		                WebUploader.formatSize( fileSize )  +
		                '），已上传' + stats.successNum + '张';
		
		        if ( stats.uploadFailNum ) {
		            text += '，失败' + stats.uploadFailNum + '张';
		        }
		    }
		
		    $info.html( text );
		}
		
		function setState( val ) {
		    var  stats;
		
		    if ( val === state ) {
		        return;
		    }
		
		    $upload.removeClass( 'state-' + state );
		    $upload.addClass( 'state-' + val );
		    state = val;
		
		    switch ( state ) {
		        case 'pedding':
		            $placeHolder.removeClass( 'element-invisible' );
		            $queue.parent().removeClass('filled');
		            $queue.hide();
		            $statusBar.addClass( 'element-invisible' );
		            uploader.refresh();
		            break;
		
		        case 'ready':
		            $placeHolder.addClass( 'element-invisible' );
		            $( '#filePicker2' ).removeClass( 'element-invisible');
		            $queue.parent().addClass('filled');
		            $queue.show();
		            $statusBar.removeClass('element-invisible');
		            uploader.refresh();
		            break;
		
		        case 'uploading':
		            $( '#filePicker2' ).addClass( 'element-invisible' );
		            $progress.show();
		            $upload.text( '暂停上传' );
		            break;
		
		        case 'paused':
		            $progress.show();
		            $upload.text( '继续上传' );
		            break;
		
		        case 'confirm':
		            $progress.hide();
		            $upload.text( '开始上传' ).addClass( 'disabled' );
		
		            stats = uploader.getStats();
		            if ( stats.successNum && !stats.uploadFailNum ) {
		                setState( 'finish' );
		                return;
		            }
		            break;
		        case 'finish':
		            stats = uploader.getStats();
		            if ( stats.successNum ) {
		                $("#relativePath").val(RELATIVE_PATH);
		                console.info(RELATIVE_PATH);
		            } else {
		                // 没有成功的图片，重设
		                state = 'done';
		                location.reload();
		            }
		            break;
		    }
		
		    updateStatus();
		}

		uploader.onUploadProgress = function( file, percentage ) {
		    var $li = $('#'+file.id),
		        $percent = $li.find('.progress span');
		
		    $percent.css( 'width', percentage * 100 + '%' );
		    percentages[ file.id ][ 1 ] = percentage;
		    updateTotalProgress();
		};
		
		uploader.onFileQueued = function( file ) {
		    fileCount++;
		    fileSize += file.size;
		
		    if ( fileCount === 1 ) {
		        $placeHolder.addClass( 'element-invisible' );
		        $statusBar.show();
		    }
		
		    addFile( file );
		    setState( 'ready' );
		    updateTotalProgress();
		};
		
		uploader.onFileDequeued = function( file ) {
		    fileCount--;
		    fileSize -= file.size;
		
		    if ( !fileCount ) {
		        setState( 'pedding' );
		    }
		
		    removeFile( file );
		    updateTotalProgress();
		
		};
		
		uploader.on( 'all', function( type ) {
		    switch( type ) {
		        case 'uploadFinished':
		            setState( 'confirm' );
		            break;
		
		        case 'startUpload':
		            setState( 'uploading' );
		            break;
		
		        case 'stopUpload':
		            setState( 'paused' );
		            break;
		
		    }
		});
		
		
		uploader.onError = function( code ) {
		    if(code == 'Q_TYPE_DENIED'){
				showSmReslutWindow(false, '图片格式(gif,jpg,jpeg,bmp,png)');
			}
		};
		
		$upload.on('click', function() {
		    if ( $(this).hasClass( 'disabled' ) ) {
		        return false;
		    }
		
		    if ( state === 'ready' ) {
		        uploader.upload();
		    } else if ( state === 'paused' ) {
		        uploader.upload();
		    } else if ( state === 'uploading' ) {
		        uploader.stop();
		    }
		});
		
		/**
		 * 文件上传成功回调
		 */
		uploader.on("uploadSuccess", function(file, response){
			var res = eval(response);
			RELATIVE_PATH +=res.relativePath + ';';
		});
		
		$info.on( 'click', '.retry', function() {
		    uploader.retry();
		} );
		
		$info.on( 'click', '.ignore', function() {
		    alert( 'todo' );
		} );
		
		$upload.addClass( 'state-' + state );
		updateTotalProgress();
}