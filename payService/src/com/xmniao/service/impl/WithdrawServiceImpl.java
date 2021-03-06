package com.xmniao.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.common.StateCodeUtil;
import com.xmniao.common.UtilString;
import com.xmniao.dao.ExpensesMpper;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.service.WalletService;
import com.xmniao.service.WithdrawService;
import com.xmniao.service.msg.ReturnWithdrawalsConsumer;
import com.xmniao.thrift.ledger.FailureException;
/**
 * 提现服务
 * @author Administrator
 *
 */
@Service("withdrawService")
public class WithdrawServiceImpl implements WithdrawService {

	 //初始日志类
	private final Logger log = Logger.getLogger(WithdrawServiceImpl.class);
	
	private final BigDecimal ZERO = new BigDecimal("0.00");

//	private final BigDecimal FEE = new BigDecimal("2.00");
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	public UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;
	
	@Autowired
	private ExpensesMpper expensesMpper;
	
	
	@Override
	@Transactional(rollbackFor = { FailureException.class,RuntimeException.class,Exception.class},timeout=10)
	public Map<String, String> returnWithdrawals(Map<String, String> param) 
			throws FailureException {

		Map<String, String> result = new HashMap<String, String>();
		result.put("wId", param.get("wId"));
		result.put("wType", param.get("wType"));

		// 1.查询对应的打款记录
		String wType = param.get("wType").trim();
		Map<String, Object> selectMap = new HashMap<String, Object>();

		Map<String, Object> resultMap = null;
		selectMap.put("orderNumber", param.get("wId"));
		if (wType.matches("[12356]")) {
			resultMap = updateWithdrawalsRecordStateMapper
					.selectByflowid(selectMap);
		} else if (wType.matches("[4]")) {
			resultMap = updateWithdrawalsRecordStateMapper
					.selectByjointid(selectMap);
		} else {
			log.error("不支持的提现用户类型");
			result.put("state", "1");
			result.put("msg", "不支持的提现用户类型");
			return result;
		}
		if (null == resultMap || resultMap.isEmpty()) {
			log.error("找不到指定的提现记录");
			result.put("state", "1");
			result.put("msg", "找不到指定的提现记录");
			return result;
		}

		if (2 != (int) resultMap.get("status")) {
			
			result.put("state", "1");
			if(4 == (int) resultMap.get("status")){
				log.error("该提现失败记录之前已进行退款到钱包，请勿重复操作");
				result.put("msg", "该提现失败记录之前已进行退款到钱包，请勿重复操作");
			}else{
				log.error("该提现记录是正常的提现记录信息，无须退款到钱包");
				result.put("msg", "该提现记录是正常的提现记录信息，无须退款到钱包");
			}
			return result;
		}
		log.info("查询到的信息：" + resultMap);
		// 2.获取该用户的钱包
		int id = 0;
		int userType = 0;
		if (wType.matches("[12356]")) {
			if ((int) resultMap.get("uid") != 0) {
				id = (int) resultMap.get("uid");
				userType = 1;
			} else if(((int) resultMap.get("sellerid") != 0)){
				id = (int) resultMap.get("sellerid");
				userType = 2;
			}else{
				id = (int) resultMap.get("allianceid");
				userType = 4;
			}
		} else if (wType.matches("[4]")) {
			id = (int) resultMap.get("jointid");
			userType = 3;
		}

		BigDecimal amount=getAmount(resultMap.get("amount"));
		BigDecimal balance=getAmount(resultMap.get("balance"));
		BigDecimal commision=getAmount(resultMap.get("commision"));
		BigDecimal seller_amount=getAmount(resultMap.get("income"));
		BigDecimal zbalance=getAmount(resultMap.get("zbalance"));
		
		
		Map<String,Object> expensesMap = getExpensesType(param.get("wId"));
		if(expensesMap==null){
			
		}else{
			BigDecimal fee = (BigDecimal)expensesMap.get("expenses");
			if(expensesMap.get("type")==null){
				if(ZERO.compareTo(amount) < 0){
					amount = amount.add(fee);
				}else if(ZERO.compareTo(balance) < 0){
					balance = balance.add(fee);
				}else if(ZERO.compareTo(commision) < 0){
					commision = commision.add(fee);
				}else if(ZERO.compareTo(seller_amount) < 0){
					seller_amount = seller_amount.add(fee);
				}else if(ZERO.compareTo(zbalance) < 0){
					zbalance = zbalance.add(fee);
				}else{
					log.error("提现失败的金额错误，找不到要返回的金额类型");
					result.put("state", "1");
					result.put("msg", "提现失败的金额错误，找不到要返回的金额类型");
					return result;
				}
			}else{
				Integer feeType = (Integer)expensesMap.get("type");
				//1 钱包 2 返利 3佣金 4营收
				if(feeType==1){
					amount = amount.add(fee);
				}else if(feeType==2){
					balance = balance.add(fee);
				}else if(feeType==3){
					commision = commision.add(fee);
				}else if(feeType==4){
					seller_amount = seller_amount.add(fee);
				}else{
					if(ZERO.compareTo(amount) < 0){
						amount = amount.add(fee);
					}else if(ZERO.compareTo(balance) < 0){
						balance = balance.add(fee);
					}else if(ZERO.compareTo(commision) < 0){
						commision = commision.add(fee);
					}else if(ZERO.compareTo(seller_amount) < 0){
						seller_amount = seller_amount.add(fee);
					}else if(ZERO.compareTo(zbalance) < 0){
						zbalance = zbalance.add(fee);
					}else{
						log.error("提现失败的金额错误，找不到要返回的金额类型");
						result.put("state", "1");
						result.put("msg", "提现失败的金额错误，找不到要返回的金额类型");
						return result;
					}
				}
			}
		}
		// 3.将该记录金额更新到钱包余额中。
		Map<String, Object> updateWalletMap = new HashMap<String, Object>();
		updateWalletMap.put("uId", id);
		updateWalletMap.put("userType", userType);
		updateWalletMap.put("amount", amount);
		updateWalletMap.put("balance", balance);
		updateWalletMap.put("commision",commision);
		updateWalletMap.put("zbalance",zbalance);
		updateWalletMap.put("integral", resultMap.get("integral") == null ? ZERO
				: resultMap.get("integral"));
		updateWalletMap.put("seller_amount",seller_amount);
		updateWalletMap.put("remarks", param.get("wId"));
		updateWalletMap.put("description", "打款失败，退回钱包");
		updateWalletMap.put("rtype", 8);
		Map<String, String> map = walletService
				.updateWalletAmount(updateWalletMap);

		// 4.更新提现记录状态为平台退款
		if (map.get("code").equals(StateCodeUtil.PR_SUCCESS)) {
			updateWRState(userType, param.get("wId"));
			result.put("state", "0");
			result.put("msg", "平台退款成功");
			return result;
		}else if(map.get("code").equals(StateCodeUtil.PR_REFUND_FAIL)){
			result.put("state", "-1");
			result.put("msg", map.get("Msg"));
			return result;
		}else{
			result.put("state", "1");
			result.put("msg", map.get("Msg"));
			return result;
		}
	}

	// 更新提现状态为"平台退款"
	private boolean updateWRState(int userType, String flowId) throws FailureException{
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNumber", flowId);
		map.put("status", 4);
		map.put("udate", UtilString.dateFormatNow());
		if (userType == 1 || userType == 2) {
			result = updateWithdrawalsRecordStateMapper
					.updateWithdrawalsRecordState(map);
		} else {
			result = updateWithdrawalsRecordStateMapper
					.updateWithdrawalsRecordStateByjointid(map);
		}
		if (result == 1) {
			return true;
		}else{
			log.error("更新提现记录状态为平台退款失败！");
			throw new FailureException(1,"更新提现记录状态为平台退款失败");
		}
	}

	// 更新提现状态为"取消提现"
	private boolean updateWRStateCancel(int userType, String flowId) throws FailureException{
		int result = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNumber", flowId);
		map.put("status", 5);
		map.put("udate", UtilString.dateFormatNow());
		if (userType == 1 || userType == 2) {
			result = updateWithdrawalsRecordStateMapper
					.updateWithdrawalsRecordState(map);
		} else {
			result = updateWithdrawalsRecordStateMapper
					.updateWithdrawalsRecordStateByjointid(map);
		}
		if (result == 1) {
			return true;
		}else{
			log.error("更新提现记录状态为取消提现失败！");
			throw new FailureException(1,"更新提现记录状态为取消提现失败");
		}
	}
	/**
	 * 获取BigDecimal类型的金额
	 * @param param
	 * @return
	 */
	private BigDecimal getAmount(Object param) {
		if (param == null) {
			return ZERO;
		}
		if (ZERO.compareTo((BigDecimal) param) == 0) {
			return ZERO;
		}
		return (BigDecimal) param;
	}
	
	//获取flowId的手续费类型
	private Map<String,Object> getExpensesType(String flowId){
		log.info("flowId:"+flowId);
		return expensesMpper.getExpensesByFlowid(flowId);
//		if(null == resultMap || null == resultMap.get("type")){
//			return -1;
//		}else{
//			return (int)resultMap.get("type");
//		}
	}
	@Transactional(rollbackFor = { FailureException.class,RuntimeException.class,Exception.class},timeout=10)
	public Map<String, String> cancelWithdrawals(Map<String, String> param)
			throws FailureException {

		// log.info("[returnWithdrawals]param:"+param);

		Map<String, String> result = new HashMap<String, String>();
		result.put("wId", param.get("wId"));
		result.put("wType", param.get("wType"));

		// 1.查询对应的打款记录
		String wType = param.get("wType").trim();
		Map<String, Object> selectMap = new HashMap<String, Object>();

		Map<String, Object> resultMap = null;
		selectMap.put("orderNumber", param.get("wId"));
		if (wType.matches("[12356]")) {
			resultMap = updateWithdrawalsRecordStateMapper
					.selectByflowid(selectMap);
		} else if (wType.matches("[4]")) {
			resultMap = updateWithdrawalsRecordStateMapper
					.selectByjointid(selectMap);
		} else {
			log.error("不支持的提现用户类型");
			result.put("state", "1");
			result.put("msg", "不支持的提现用户类型");
			return result;
		}
		if (null == resultMap || resultMap.isEmpty()) {
			log.error("找不到指定的提现记录");
			result.put("state", "1");
			result.put("msg", "找不到指定的提现记录");
			return result;
		}

		if (0 != (int) resultMap.get("status")) {
			
			result.put("state", "1");
			if(5 == (int) resultMap.get("status")){
				log.error("该提现记录之前已成功取消提现，请勿重复操作");
				result.put("msg", "该提现记录之前已成功取消提现，请勿重复操作");
			}else{
				log.error("该提现记录不支持取消提现");
				result.put("msg", "该提现记录不支持取消提现");
			}
			return result;
		}
		log.info("查询到的信息：" + resultMap);
		// 2.获取该用户的钱包
		int id = 0;
		int userType = 0;
		if (wType.matches("[12356]")) {
			if ((int) resultMap.get("uid") != 0) {
				id = (int) resultMap.get("uid");
				userType = 1;
			} else if((int) resultMap.get("sellerid") != 0){
				id = (int) resultMap.get("sellerid");
				userType = 2;
			}else{//区域代理
				id = (int) resultMap.get("allianceid");
				userType = 4;
			}
		} else if (wType.matches("[4]")) {
			id = (int) resultMap.get("jointid");
			userType = 3;
		}

		BigDecimal amount=getAmount(resultMap.get("amount"));
		BigDecimal balance=getAmount(resultMap.get("balance"));
		BigDecimal commision=getAmount(resultMap.get("commision"));
		BigDecimal seller_amount=getAmount(resultMap.get("income"));
		BigDecimal zbalance=getAmount(resultMap.get("zbalance"));
		
		
		Map<String,Object> expensesMap = getExpensesType(param.get("wId"));
		if(expensesMap == null){
			log.info("该提现记录没有收取手续费");
		}else{
			//1 钱包 2 返利 3佣金 4营收
			if(expensesMap.get("type")==null){
				BigDecimal fee = (BigDecimal)expensesMap.get("expenses");
				if(ZERO.compareTo(amount) < 0){
					amount = amount.add(fee);
				}else if(ZERO.compareTo(balance) < 0){
					balance = balance.add(fee);
				}else if(ZERO.compareTo(commision) < 0){
					commision = commision.add(fee);
				}else if(ZERO.compareTo(seller_amount) < 0){
					seller_amount = seller_amount.add(fee);
				}else if(ZERO.compareTo(zbalance) < 0){
					zbalance = zbalance.add(fee);
				}else{
					log.error("取消提现的原订单金额错误，找不到要返回的金额类型");
					result.put("state", "1");
					result.put("msg", "取消提现的原订单金额错误，找不到要返回的金额类型");
					return result;
				}
			}else{
				Integer feeType = (Integer)expensesMap.get("type");
				BigDecimal fee = (BigDecimal)expensesMap.get("expenses");
				if(feeType==1){
					amount = amount.add(fee);
				}else if(feeType==2){
					balance = balance.add(fee);
				}else if(feeType==3){
					commision = commision.add(fee);
				}else if(feeType==4){
					seller_amount = seller_amount.add(fee);
				}else{
					if(ZERO.compareTo(amount) < 0){
						amount = amount.add(fee);
					}else if(ZERO.compareTo(balance) < 0){
						balance = balance.add(fee);
					}else if(ZERO.compareTo(commision) < 0){
						commision = commision.add(fee);
					}else if(ZERO.compareTo(seller_amount) < 0){
						seller_amount = seller_amount.add(fee);
					}else if(ZERO.compareTo(zbalance) < 0){
						zbalance = zbalance.add(fee);
					}else{
						log.error("取消提现的原订单金额错误，找不到要返回的金额类型");
						result.put("state", "1");
						result.put("msg", "取消提现的原订单金额错误，找不到要返回的金额类型");
						return result;
					}
				}
			}
		}
		
		// 3.将该记录金额更新到钱包余额中。
		Map<String, Object> updateWalletMap = new HashMap<String, Object>();
		updateWalletMap.put("uId", id);
		updateWalletMap.put("userType", userType);
		updateWalletMap.put("amount", amount);
		updateWalletMap.put("balance", balance);
		updateWalletMap.put("commision",commision);
		updateWalletMap.put("zbalance",zbalance);
		updateWalletMap.put("integral", resultMap.get("integral") == null ? ZERO
				: resultMap.get("integral"));
		updateWalletMap.put("seller_amount",seller_amount);
		updateWalletMap.put("remarks", param.get("wId"));
		updateWalletMap.put("description", "取消提现，退回钱包");
		updateWalletMap.put("rtype", 15);
		Map<String, String> map = walletService
				.updateWalletAmount(updateWalletMap);

		// 4.更新提现记录状态为平台退款
		if (map.get("code").equals(StateCodeUtil.PR_SUCCESS)) {
			updateWRStateCancel(userType, param.get("wId"));
			result.put("state", "0");
			result.put("msg", "取消提现成功");
			return result;
		}else if(map.get("code").equals(StateCodeUtil.PR_REFUND_FAIL)){
			result.put("state", "1");
			result.put("msg", map.get("Msg"));
			return result;
		}else{
			result.put("state", "1");
			result.put("msg", map.get("Msg"));
			return result;
		}
		

	}
}
