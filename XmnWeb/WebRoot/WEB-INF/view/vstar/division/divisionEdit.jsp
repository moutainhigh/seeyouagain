<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


	<form id="editFrom" role="form" class="form-horizontal">
		<c:if test="${!empty division}">
			<input type="hidden" name="id" id="id" value="${division.id}">
		</c:if>
		<div class="form-group">
			<label class="col-md-4 control-label">赛区名称：<span style="color:red;">*</span></label>
			<div class="col-md-7">
				<input class="form-control" id="divisionName"  name="divisionName" value="${division.divisionName}"
												 style="width:98%;"/>
			</div>
		</div>
						
			<div class="form-group">
			<label class="col-md-4 control-label">城市：<span style="color:red;">*</span></label>
			<div class="col-md-7">
							<select class="chosen-select form-control" id="cityIds" name="cityIds" multiple
										initValue="${division.cityIds}"  data-placeholder="请选择城市">
									</select>
			</div>
		</div>
		<div class="form-group">
			<div class="text-center" style="padding:10px 0 10px 0;">
				<button type="submit" class="btn btn-success">
					<span class="icon-ok"></span> 保 存
				</button>
				&nbsp;&nbsp;
				<button type="reset" class="btn btn-default" data-dismiss="modal">
					<span class="icon-remove"></span> 取 消
				</button>
			</div>
		</div>
	</form>
	<script src="<%=path%>/js/vstar/division/divisionEdit.js" />
