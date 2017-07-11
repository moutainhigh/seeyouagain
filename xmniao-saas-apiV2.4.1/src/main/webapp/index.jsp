<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>test</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<style>
.top-banner {
	z-index: 999;
	text-align: right;
	height: 30px !important;
	line-height: 30px !important;
}

.top-banner a {
	color: green !important;
	text-shadow: none;
}

.column {
	margin: 60px auto;
	float: none;
	text-align: center;
	width: 75%;
	min-height: 100px;
}
</style>
<script src="js/modernizr.custom.js"></script>
<link rel="stylesheet"
	href="http://dreamsky.github.io/main/blog/common/init.css">

</head>
<body>
	<div id="st-container" class="st-container">
		<!-- 	
				example menus 
				these menus will be on top of the push wrapper
			-->
		<nav class="st-menu st-effect-1" id="menu-1">
		<h2 class="icon icon-lab">Sidebar</h2>
		<ul>
			<li><a class="icon icon-data" href="#">Data Management</a></li>
			<li><a class="icon icon-location" href="#">Location</a></li>
			<li><a class="icon icon-study" href="#">Study</a></li>
			<li><a class="icon icon-photo" href="#">Collections</a></li>
			<li><a class="icon icon-wallet" href="#">Credits</a></li>
		</ul>
		</nav>

		<div class="st-content">
			<!-- this is the wrapper for the content -->
			<div class="st-content-inner">
				<!-- extra div for emulating position:fixed of the menu -->
				<header class="codrops-header">
				<h1>消息队列服务器已启动!</h1>
				</header>
				<div class="main clearfix">
					<div id="st-trigger-effects" class="column">
						<a href="index.jsp">进入平台</a>
					</div>
				</div>
				<!-- /main -->
				<div class="footer-banner"
					style="width: 728px; margin: 160px auto 0"></div>
			</div>
			<!-- /st-content-inner -->
		</div>
		<!-- /st-content -->
	</div>
	</div>
</body>
</html>
           
