package com.xmniao.xmn.core.api.controller.xmer;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseResponse;
import com.xmniao.xmn.core.base.BaseVControlInf;
import com.xmniao.xmn.core.common.ResponseCode;
import com.xmniao.xmn.core.common.request.InquireRequest;
import com.xmniao.xmn.core.xmer.service.MaterialService;


/**
 * 
*    
* 项目名称：xmnService   
* 类名称：MaterialinquireApi   
* 类描述：   查询支付接口
* 创建人：xiaoxiong   
* 创建时间：2016年7月13日 下午5:16:38   
* @version    
*
 */
@Controller
public class MaterialinquireApi implements BaseVControlInf{
	/**
	 * 日志
	 */
	private final Logger log = Logger.getLogger(MaterialinquireApi.class);
		
	/**
	 * 注入service
	 */
	@Autowired
	private MaterialService materialService;
	
	/**
	 * 注入验证
	 */
	@Autowired
	private Validator validator;
	
	
	@ResponseBody
	@RequestMapping(value="materialInquire",method=RequestMethod.POST,produces={"application/json;charset=UTF-8"})
	public Object materialinquire(InquireRequest inquireRequest){
		//验证
				List<ConstraintViolation> result = validator.validate(inquireRequest);
				if(result != null && result.size()>0){
					log.info("提交的数据有问题"+result.get(0).getMessage());
					return new BaseResponse(ResponseCode.DATAERR,"提交的数据不正确!");
				}
				return versionControl(inquireRequest.getApiversion(),inquireRequest);
	}


	@Override
	public Object versionControl(int v, Object object) {
		switch(v){
		case 1:
			return version(object);
			default :
			return new BaseResponse(ResponseCode.ERRORAPIV,"版本号不正确,请重新下载客户端");
		}
	}


	private Object version(Object object) {
		InquireRequest inquireRequest=(InquireRequest) object;
		return materialService.inquire(inquireRequest);
	}
}
