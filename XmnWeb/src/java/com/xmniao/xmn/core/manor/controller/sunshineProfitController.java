package com.xmniao.xmn.core.manor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.manor.entity.TManorSunshineManage;
import com.xmniao.xmn.core.manor.service.SunshineProfitService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

@RequestLogging(name="阳光收益管理")
@Controller
@RequestMapping(value = "sunshineProfit/manage")
public class sunshineProfitController extends BaseController {
	
	
	/**
	 * 庄园会员管理服务
	 */
	@Autowired
	private SunshineProfitService sunshineProfitService;
	
	
	@RequestMapping(value = "init")
	public String init() {
		return "golden_manor/sunshineProfitManage";
	}
	
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(TManorSunshineManage manorSunshineManage) {
		Pageable<TManorSunshineManage> pageable = new Pageable<TManorSunshineManage>(manorSunshineManage);
		
		//用户类型 1 主播 2 普通用户
		try {
			pageable = sunshineProfitService.getManorSunshineInfoList(manorSunshineManage);
//			JSON.toJSONString(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}


	/**
	 * 方法描述：通过接口查询阳光渠道数据 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月23日上午10:42:15 <br/>
	 * @param manorSunshineManage
	 * @return
	 */
	@RequestMapping(value = "list/viewDetail")
	@ResponseBody
	public Object searchManorSunshineInfo(TManorSunshineManage manorSunshineManage) {
		Resultable resultable = null;
		try {
			TManorSunshineManage manorSunshine = sunshineProfitService.getManorSunshineManageInfo();
			resultable = new Resultable(true, "查询阳光渠道管理成功", manorSunshine);
			
			return resultable;
			
		} catch (Exception e) {
			log.error("查询个人信息失败", e);
			resultable = new Resultable(false, "查询阳光渠道管理信息失败");
			return resultable;
		}

	}
	
	
	/**
	 * 方法描述：通过接口设置庄园的阳光渠道配置 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月23日上午10:43:11 <br/>
	 * @param manorSunshineManage
	 * @return
	 */
	@RequestMapping(value = {"updateSunshineProfit"})
	@ResponseBody
	public Resultable updateSunshineProfit(TManorSunshineManage manorSunshineManage){
		Resultable result=new Resultable();
		try {
			sunshineProfitService.updateManorSunshineManage(manorSunshineManage);  //设置庄园的阳光渠道配置
			
			result.setMsg("更新阳光渠道管理成功!");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setMsg("更新阳光渠道管理失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	

	@RequestMapping(value = "list/viewStoreProfit")
	@ResponseBody
	public Object getManorSunshineProfit(TManorSunshineManage manorSunshineManage) {
		Resultable resultable = null;
		try {
			List<TManorSunshineManage> manorSunshineManageList = sunshineProfitService.getManorSunshineProfitList(manorSunshineManage);
			resultable = new Resultable(true, "查询阳光渠道管理成功", manorSunshineManageList);
			
			return resultable;
			
		} catch (Exception e) {
			log.error("查询个人信息失败", e);
			resultable = new Resultable(false, "查询阳光渠道管理信息失败");
			return resultable;
		}

	}
	
	
	@RequestMapping(value = {"updateStoreProfit"})
	@ResponseBody
	public Resultable updateStoreProfit(TManorSunshineManage manorSunshineManage){
		Resultable result=new Resultable();
		try {
		    sunshineProfitService.updateManorSunshineProfitList(manorSunshineManage);  //保存数据方法
		    
			result.setMsg("更新成功!");
			result.setSuccess(true);
			
		} catch (Exception e) {
			result.setMsg("更新失败!");
			result.setSuccess(false);
			e.printStackTrace();
			this.log.error(e.getMessage(), e);
		}
		return result;
	}
	
	
	@RequestMapping(value = "init/getCurrentDataSize")
	@ResponseBody
	public Object getCurrentDataSize(TManorSunshineManage manorSunshineManage) {
		long count=0l;
		try {
			count = sunshineProfitService.count(manorSunshineManage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 方法描述：导出庄园会员 <br/>
	 * 创建人： Administrator <br/>
	 * 创建时间：2017年6月7日下午2:39:46 <br/>
	 * @param anchor
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="export")
	public void export(TManorSunshineManage manorSunshineManage,HttpServletRequest request,HttpServletResponse response){
		manorSunshineManage.setOrder(PageConstant.NOT_ORDER);
		manorSunshineManage.setLimit(PageConstant.PAGE_LIMIT_NO);
		
		Map<String,Object> params=new HashMap<String,Object>();
//		anchor.setUtype(LiveConstant.UTYPE_COMMON);
		params.put("list", sunshineProfitService.getManorSunshineRecordList(manorSunshineManage));
		doExport(request, response, "golden_manor/sunshineProfit.xls", params);
		
	}
	
	
}
