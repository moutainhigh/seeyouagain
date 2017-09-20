/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.vstar.service.TVstarEnrollService;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarEnrollChartController
 * 
 * 类描述： 新时尚大赛报名统计
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月14日 上午10:57:53 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/vstarEnroll/chart")
public class VstarEnrollChartController extends BaseController{

	
	/**
	 * 注入新时尚大赛报名服务
	 */
	@Autowired
	private TVstarEnrollService starEnrollService;
	
	
	@RequestMapping("/init")
	public Object init(){
		return "vstar/vstarEnrollChartList";
	}
	
	
	@RequestMapping("init/getCityRank")
	@ResponseBody
	public Object getCityRank(){
		return starEnrollService.getCityRank();
	}
	
	@RequestMapping("init/getCityRankByDate")
	@ResponseBody
	public Object getCityRankByDate(){
		try {
			return starEnrollService.getCityRankByDate();
		} catch (Exception e) {
			log.error("查询失败",e);
			return null;
		}
	}
	
	
}