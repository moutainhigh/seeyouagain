package com.xmniao.service.manor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xmniao.common.MapBeanUtil;
import com.xmniao.common.ValidateUtil;
import com.xmniao.domain.manor.ManorFlower;
import com.xmniao.domain.manor.ManorFlowerCount;
import com.xmniao.domain.manor.ManorOperate;
import com.xmniao.domain.urs.UrsEarningsRelation;
import com.xmniao.exception.CustomException;
import com.xmniao.service.manor.vo.ManorActivateInfo;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.common.ResponsePageList;
import com.xmniao.thrift.busine.common.ThriftResult;
import com.xmniao.thrift.busine.manor.ManorService;

/**
 *
 * 黄金庄园Thrift接口实现
 * Created by yang.qiang on 2017/6/6.
 */
@Service("ManorServiceImpI")
public class ThriftManorServiceImpl implements ManorService.Iface {
    private final Logger logger = LoggerFactory.getLogger(ThriftManorServiceImpl.class);
    @Autowired
    private ManorInfoService manorInfoService;
    @Autowired
    private ManorFlowerService manorFlowerService;
    @Autowired
    private ManorEarningService manorEarningService;


    /**
     * 1.20.1 激活/续租黄金庄园
     * params :
     * uid : 用户id
     * transNo : 交易号
     * months  : 续租月份
     *
     * @param params
     */
    @Override
    public ResponseData activateManor(Map<String, String> params) throws FailureException, TException {
        logger.info("调用Thrift接口[1.20.1 激活/续租黄金庄园]" + params);
        ResponseData responseData = new ResponseData();
        Integer uid = null;
        String transNo = null;
        Integer months = null;
        try {   // 参数校验
            // 判断参数是否为空
            if (ValidateUtil.validateNull(params, "uid","transNo")) {
                uid = Integer.valueOf(params.get("uid"));
                transNo = params.get("transNo");
                String paramsMonths = params.get("months");
                months = paramsMonths != null ? Integer.valueOf(paramsMonths) : 1;
            } else {
                logger.info("参数不足, paraMap=" + params);
                responseData.setState(2);
                responseData.setMsg("参数不足,paraMap=" + params);
                return responseData;
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + params);
            return responseData;
        }

        try {   // 业务逻辑
            ManorActivateInfo manorActivateInfo = manorInfoService.activateManor(uid, transNo,months);
            Map<String, String> resultMap = MapBeanUtil.convertMap(manorActivateInfo,
                    "manorDeadline", "activateDays", "activateType");
            responseData.setResultMap(resultMap);
            responseData.setState(0);
            responseData.setMsg("请求成功!");
            return responseData;

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("后台系统出现异常!");
            logger.error("调用Thrift接口[1.20.1 激活/续租黄金庄园]", e);
        }
        return responseData;
    }

    /**
     * 1.20.2 种植花朵
     * <p>
     * uid : 用户id
     * plantParam: 种植参数每个花田一条数据, 如果有3个花田都要种植 则list有3条数据
     *      location : 线路位置 0:走  1:中  2:右
     *      quantity : 种植数量
     * transNo : 交易号
     *
     * @param uid
     * @param plantParam
     * @param transNo
     */
    @Override
    public ThriftResult plantFlower(int uid, List<Map<String, String>> plantParam, String transNo) throws FailureException, TException {
        logger.info("调用Thrift接口[1.20.2 种植花朵]" + "uid="+uid + "  plantParam="+plantParam + "  transNo" + transNo);
        ThriftResult responseData = new ThriftResult();

        ArrayList<ManorOperate> plantOperateList = new ArrayList<>();
        try {   // 参数校验
            uid = Integer.valueOf(uid);
            for (Map<String, String> param : plantParam) {
                plantOperateList.add(MapBeanUtil.convert2Bean(param, ManorOperate.class));
            }

        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + "uid="+uid + "  plantParam="+plantParam + "  transNo" + transNo);
            return responseData;
        }

        try {   // 业务逻辑
            // 校验左链是否已种植满2朵
//            manorFlowerService.verifyLeftFlower(uid,plantOperateList);

            ArrayList<ManorFlower> plantFlowerList = manorFlowerService.plantFlowerTask(uid, plantOperateList, transNo);

            responseData.setState(0);
            responseData.setMsg("请求成功!");
            responseData.setResultList(MapBeanUtil.convertMapList(plantFlowerList,
                    "delayDays","actualOperation"));

            return responseData;

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("后台系统出现异常!");
            logger.error("调用Thrift接口[1.20.2 种植花朵]", e);
        }
        return responseData;
    }

    /**
     * 1.20.3 施肥
     * uid : 用户id
     * fertilizeParam : 施肥参数
     *      location : 线路位置 0:走  1:中  2:右
     *      quantity : 种植数量
     *      flowerType : 施肥类型 0:花苗；1:花朵
     * transNo : 交易号
     *  @param uid
     * @param fertilizeParam
     * @param transNo
     */
    @Override
    public ResponsePageList fertilization(int uid, List<Map<String, String>> fertilizeParam, String transNo) throws FailureException, TException {
        logger.info("调用Thrift接口[1.20.3 施肥] uid="+ uid + "  fertilizeParam="+fertilizeParam + "  transNo" + transNo);
        ResponsePageList responseList = new ResponsePageList();
        ResponseData responseData = new ResponseData();
        responseList.setDataInfo(responseData);


        ArrayList<ManorOperate> plantOperateList = new ArrayList<>();
        try {   // 参数校验
            uid = Integer.valueOf(uid);
            for (Map<String, String> param : fertilizeParam) {
                plantOperateList.add(MapBeanUtil.convert2Bean(param, ManorOperate.class));
            }


        } catch (Exception e) {
            logger.error("校验参数失败!", e);
            responseData.setState(2);
            responseData.setMsg("参数错误! paraMap=" + "uid="+uid + "  fertilizeParam="+fertilizeParam + "  transNo" + transNo);

            return responseList;
        }

        try {   // 业务逻辑
//            ArrayList<ManorOperate> operates = manorFlowerService.fertilization(uid, plantOperateList, transNo);
//
//            List<Map<String, String>> resultMapList = new ArrayList<>();
//            for (ManorOperate operate : operates) {
//                resultMapList.add(MapBeanUtil.convertMap(operate,
//                        "location", "quantity", "flowerType"));
//            }
//            responseData.setState(0);
//            responseData.setMsg("请求成功!");
//            responseList.setPageList(resultMapList);

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("后台系统出现异常!");
            logger.error("调用Thrift接口[1.20.3 施肥出现异常!!!]", e);
        }

        return responseList;

    }

    /**
     * 1.20.4 兑换优惠券
     * uid : 用户id
     * number : 兑换张数
     * cid : 优惠券编号
     * sunshine : 兑换阳光数
     *
     * @param params
     * @resut ResponseData
     */
    @Override
	public ResponseData convertReward(Map<String, String> params)
			throws FailureException, TException {
    	logger.info("黄金庄园兑换优惠券奖励，传入参数:"+params);
    	try{
    		manorEarningService.convertCoupon(params);
    	}catch(FailureException e){
    		logger.error("兑换异常",e);
    		manorEarningService.updateOperateRecord(params.get("transNo"), 2, e.info);
    		return new ResponseData(e.getState(),e.info,null);
    	}catch (Exception e) {
    		logger.error("兑换异常",e);
    		manorEarningService.updateOperateRecord(params.get("transNo"), 2, "兑换异常");
    		return new ResponseData(3,"兑换异常",null);
    	}
    	return new ResponseData(0,"兑换成功",null);
	}

    /**
     * 1.20.5 领取每日收益
     * uid : 用户id
     * transNo : 交易号
     * number : 收益数
     *
     * @resut ResponseData
     */
	@Override
	public ResponseData receiveDailyEarnings(Map<String, String> params)
			throws FailureException, TException {
		logger.info("领取黄金庄园每日收益:"+JSON.toJSONString(params));
		try{
			String uid = params.get("uid");
			String transNo = params.get("transNo");
			return manorEarningService.receiveDailyEarnings(Integer.parseInt(uid), transNo);
		}catch(Exception e){
			logger.error("领取异常啦。。。",e);
		}
		return new ResponseData(1,"领取失败",null);
	}

    /**
     * 1.20.6 查询用户花田开启资格
     * <p>
     * params:
     * uid : 用户id
     * location :  2 右花田
     *
     * @param params
     */
    @Override
    public ThriftResult filedPlantable(Map<String, String> params) throws FailureException, TException {
        logger.info("黄金庄园-查询用户花田开启资格 请求参数:" + JSON.toJSON(params));
        ThriftResult result = new ThriftResult();

        Integer uid;
        Integer location;
        try {   // 参数校验
            uid = Integer.valueOf(params.get("uid"));
            location = Integer.valueOf(params.get("location"));
        } catch (Exception e) {
            logger.error("黄金庄园-查询用户花田开启资格 解析请求参数失败:", e);
            result.setState(2);
            result.setMsg("黄金庄园-查询用户花田开启资格 参数格式错误:" + JSON.toJSON(params));
            return result;
        }


        try {   // 业务逻辑

            // 查询是否可种植花朵
            boolean plantable = manorFlowerService.fieldPlantable(uid,location);

            result.setState(0);
            result.setMsg("请求成功!");
            HashMap<String, String> resultMap = new HashMap<>();
            resultMap.put("plantable",plantable+"");
            result.setResultMap(resultMap);
            return result;

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setState(ex.getCode());
        } catch (Exception e) {
            result.setState(1);
            result.setMsg("后台系统出现异常!");
            logger.error("黄金庄园-查询用户花田开启资格 系统异常!!!" + JSON.toJSON(params), e);
        }
        return result;

    }

    /**
     * 1.20.7 统计用户花朵数量
     * <p>
     * params:
     * uid : 用户id
     *
     * @param params
     */
    @Override
    public ThriftResult statFlower(Map<String, String> params) throws FailureException, TException {
        logger.info("黄金庄园-统计用户各花田花朵 请求参数:" + JSON.toJSON(params));
        ThriftResult result = new ThriftResult();

        Integer uid;
        Integer location;
        try {   // 参数校验
            uid = Integer.valueOf(params.get("uid"));
//            location = Integer.valueOf(params.get("location"));
        } catch (Exception e) {
            logger.error("黄金庄园-统计用户各花田花朵 解析请求参数失败:", e);
            result.setState(2);
            result.setMsg("黄金庄园-统计用户各花田花朵 参数格式错误:" + JSON.toJSON(params));
            return result;
        }


        try {   // 业务逻辑

            // 统计用户花朵数量
            List<ManorFlowerCount> flowerCounts = manorFlowerService.countAliveFlower(uid);
            List<ManorFlowerCount> flowerCountsByPerish = manorFlowerService.countFlowerByPerishTime(uid);

            HashMap<String, String> resultMap = new HashMap<>();


            ArrayList<Map<String, String>> resultList = new ArrayList<>();
            for (ManorFlowerCount flowerCount : flowerCounts) {
                resultList.add(MapBeanUtil.convertMap(flowerCount,"location","perishType","count"));
            }
            for (ManorFlowerCount flowerCount : flowerCountsByPerish) {
                resultList.add(MapBeanUtil.convertMap(flowerCount,"location","perishType","count"));
            }



//            // 用户现有花朵数
//            for (ManorFlowerCount flowerCount : flowerCounts) {
//                switch (flowerCount.getLocation()) {
//                    case FLOWER_LOCATION_LEFT:      resultMap.put("alive-flower-left",flowerCount.getCount()+"");   break;
//                    case FLOWER_LOCATION_MIDDLE:    resultMap.put("alive-flower-middle",flowerCount.getCount()+""); break;
//                    case FLOWER_LOCATION_RIGHT:     resultMap.put("alive-flower-right",flowerCount.getCount()+"");  break;
//                    default: logger.error("用户" + uid + "花田位置错误, 不能为"+flowerCount.getLocation());            break;
//                }
//
//            }
//
//            // 用户枯萎 / 即将枯萎花朵数
//            for (ManorFlowerCount flowerCount : flowerCountsByPerish) {
//                Integer local = flowerCount.getLocation();
//                if(flowerCount.getPerishType() == 3){
//                    switch (local){
//                        case FLOWER_LOCATION_LEFT:      resultMap.put("perish-flower-left",flowerCount.getCount()+"");   break;
//                        case FLOWER_LOCATION_MIDDLE:    resultMap.put("perish-flower-middle",flowerCount.getCount()+""); break;
//                        case FLOWER_LOCATION_RIGHT:     resultMap.put("perish-flower-right",flowerCount.getCount()+"");  break;
//                        default: logger.error("用户" + uid + "花田位置错误, 不能为"+flowerCount.getLocation());            break;
//                    }
//                }else if(flowerCount.getPerishType() == 2){
//                    switch (local){
//                        case FLOWER_LOCATION_LEFT:      resultMap.put("dying-flower-left",flowerCount.getCount()+"");   break;
//                        case FLOWER_LOCATION_MIDDLE:    resultMap.put("dying-flower-middle",flowerCount.getCount()+""); break;
//                        case FLOWER_LOCATION_RIGHT:     resultMap.put("dying-flower-right",flowerCount.getCount()+"");  break;
//                        default: logger.error("用户" + uid + "花田位置错误, 不能为"+flowerCount.getLocation());            break;
//                    }
//
//                }


//            }

            result.setState(0);
            result.setMsg("请求成功!");
            result.setResultList(resultList);
            return result;

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setState(ex.getCode());
        } catch (Exception e) {
            result.setState(1);
            result.setMsg("后台系统出现异常!");
            logger.error("黄金庄园-统计用户各花田花朵 系统异常!!!" + JSON.toJSON(params), e);
        }
        return result;

    }

    /**
     * 1.20.8 种植园友赠送的花苗
     * uid : 用户id
     * plantParam: 种植参数每个花田一条数据, 如果有3个花田都要种植 则list有3条数据
     * location : 线路位置 0:走  1:中  2:右
     * giveUid  : 花苗赠送人uid
     * transNo : 交易号
     *
     * @param uid
     * @param plantParam
     * @param transNo
     */
    @Override
    public ThriftResult plantFlowerWithGive(int uid, List<Map<String, String>> plantParam, String transNo) throws FailureException, TException {
        logger.info("黄金庄园-种植园友赠送的花苗 请求参数 uid:"+uid +"  交易号:"+transNo + " 种植参数:" + JSON.toJSON(plantParam));
        ThriftResult responseData = new ThriftResult();

        ArrayList<ManorOperate> plantOperateList = new ArrayList<>();
        try {   // 参数校验
            uid = Integer.valueOf(uid);
            for (Map<String, String> param : plantParam) {
                plantOperateList.add(MapBeanUtil.convert2Bean(param, ManorOperate.class));
            }

        } catch (Exception e) {
            logger.error("黄金庄园-种植园友赠送的花苗  解析参数异常 请求参数 uid:"+uid +"  交易号:"+transNo + " 种植参数:" + JSON.toJSON(plantParam),e);
            responseData.setState(2);
            responseData.setMsg("参数解析异常! 请求参数 uid:"+uid +"  交易号:"+transNo + " 种植参数:" + JSON.toJSON(plantParam));
            return responseData;
        }

        try {   // 业务逻辑

            manorFlowerService.plantFlowerWithGiveTask(uid,plantOperateList,transNo);

            responseData.setState(0);
            responseData.setMsg("请求成功!");
            return responseData;

        } catch (CustomException ex) {
            logger.info(ex.getMessage());
            responseData.setMsg(ex.getMessage());
            responseData.setState(ex.getCode());
        } catch (Exception e) {
            responseData.setState(1);
            responseData.setMsg("后台系统出现异常!");
            logger.error("黄金庄园-种植园友赠送的花苗  后台报错 请求参数!!! uid:"+uid +"  交易号:"+transNo + " 种植参数:" + JSON.toJSON(plantParam),e);
        }
        return responseData;

    }
    
    
    /**
     * 
     * 方法描述：绑定庄园用户关系链父级
     * 创建人： jianming <br/>
     * 创建时间：2017年9月7日上午9:48:29 <br/>
     * @param childId
     * @param parentId
     * @return
     * @throws FailureException
     * @throws TException
     */
    public ThriftResult usrChainBindingParent(Map<String,String> paramMap) throws FailureException, TException{
    	ThriftResult thriftResult = new ThriftResult();
    	if(!ValidateUtil.validateNull(paramMap, "childId","parentId")){
    		thriftResult.setState(2);
    		thriftResult.setMsg("参数错误");
    		return thriftResult;
    	}
    	Integer childId =Integer.valueOf(paramMap.get("childId"));
    	Integer parentId =Integer.valueOf(paramMap.get("parentId"));
    	UrsEarningsRelation child=manorInfoService.getManorByUid(childId);
    	UrsEarningsRelation parent=manorInfoService.getManorByUid(parentId);
    	if(child!=null){
    		thriftResult.setState(2);
    		thriftResult.setMsg("会员已有上级,不能绑定");
    		return thriftResult;
		}
		if(parent==null){
			thriftResult.setState(2);
    		thriftResult.setMsg("父级没有庄园信息,不能绑定");
    		return thriftResult;
		}
		manorInfoService.usrChainBindingParent(childId,parentId);
		thriftResult.setState(0);
    	thriftResult.setMsg("操作成功");
    	return thriftResult;
    }
    
    
    public ThriftResult flowerChainBindingParent(Map<String,String> paramMap) throws FailureException, TException{
    	ThriftResult thriftResult = new ThriftResult();
    	if(!ValidateUtil.validateNull(paramMap, "childId","parentId","location")||paramMap.get("childId").equals(paramMap.get("parentId"))){
    		thriftResult.setState(2);
    		thriftResult.setMsg("参数错误");
    		return thriftResult;
    	}
    	Integer childId =Integer.valueOf(paramMap.get("childId"));
    	Integer parentId =Integer.valueOf(paramMap.get("parentId"));
    	Integer location =Integer.valueOf(paramMap.get("location"));
    	
    	//检查子级是否有下级
    	if(manorFlowerService.havParentBranch(childId)){
    		thriftResult.setState(2);
    		thriftResult.setMsg("修改的下级自己有下级");
    		return thriftResult;
    	}
    	
    	//检查目标父级坑下是否有子级
    	if(manorFlowerService.havChildBranch(parentId,location)){
    		thriftResult.setState(2);
    		thriftResult.setMsg("目标父级已经有下级");
    		return thriftResult;
    	}
    	
    	//迁移操作
    	manorFlowerService.BindingParent(childId,parentId,location);
    	manorInfoService.BindingParent(childId,parentId,location);
    	thriftResult.setState(0);
    	thriftResult.setMsg("操作成功");
    	return thriftResult;
    	
    }
    
    
}
