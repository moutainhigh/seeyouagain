package com.xmniao.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.entity.WalletExpansion;
import com.xmniao.entity.WalletExpansionRecord;

public interface WalletExpansionRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WalletExpansionRecord record);

    int insertSelective(WalletExpansionRecord record);

    WalletExpansionRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WalletExpansionRecord record);

    int updateByPrimaryKey(WalletExpansionRecord record);

	/**
	 * 
	 * 方法描述：获得扩展钱包操作列表
	 * 创建人：jianming  
	 * 创建时间：2017年4月8日 下午2:45:36   
	 * @param string2Object
	 * @return
	 */
	List<WalletExpansionRecord> getWalletExpansionRecordList(Map<String, Object> string2Object);
	
	/**
	 * 
	 * 方法描述：根据数据源查询
	 * 创建人：jianming  
	 * 创建时间：2017年4月10日 下午3:16:53   
	 * @param source
	 * @return
	 */
	List<WalletExpansionRecord> getBySource(String source);
	
	/**
	 * 
	 * 方法描述：统计总收益
	 * 创建人：jianming  
	 * 创建时间：2017年4月17日 下午6:48:32   
	 * @param walletMap
	 * @return
	 */
	BigDecimal sumExpansion(Map<String, String> walletMap);

	Long countUserWalletRecordList3(Map<String, String> walletMap);

	List<Map<String, Object>> getUserWalletRecordList3(Map<String, String> walletMap);
	
	/**
	 * 
	 * 方法描述：获取寻蜜客收益记录
	 * 创建人：jianming  
	 * 创建时间：2017年5月9日 上午11:35:22   
	 * @param paramMap
	 * @return
	 */
	List<WalletExpansionRecord> getXmrRecord(Map<String, Object> paramMap);
	
	/**
	 * 
	 * 方法描述：获取寻蜜客收益总数
	 * 创建人：jianming  
	 * 创建时间：2017年5月9日 上午11:51:31   
	 * @param paramMap
	 * @return
	 */
	BigDecimal getXmrTotalAmount(Map<String, Object> paramMap);

	BigDecimal getTodayRecord(WalletExpansion wa);

	/**
	 * 
	 * 方法描述：获得指定主播贡献给V客的收入
	 * 创建人：jianming  
	 * 创建时间：2017年8月1日 上午11:27:20   
	 * @param uid
	 * @param liverIds
	 * @return
	 */
	List<Map<String, Object>> getIncomeByLivers(@Param("uid")Integer uid,@Param("liverIds") List<String> liverIds);

	long countBySource(String source);

	List<WalletExpansionRecord> getBySaasSource(String source);
}