package com.xmniao.xmn.core.reward_dividends.entity;

import com.xmniao.xmn.core.base.BaseEntity;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BursEarningsRelation
 * 
 * 类描述： 会员收益关系链实体类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-15 下午4:21:10 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class BursEarningsRelation extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3715450623691975486L;

	private Integer id;//业务主键

    private Integer uid;//会员UID

    private Integer objectOriented;//会员渠道来源 1.VIP 2.商家 3.直销 4.v客 5.常规寻蜜客 6.主播寻蜜客(V客赠送) 7.V客寻密客 8.脉客寻密客
    
    private String objectOrientedStr;//会员渠道值
    
    private String nname;//会员昵称
    
    private String phone;//会员手机号

    private String uidRelationChain;//会员UID关系链
    
    private String uidRelationChainNname;//会员昵称关系链
    
    private Integer uidRelationChainLevel;//会员层级，顶级为1，下级依次

    private Integer indirectUid;//会员绑定间接上级
    
    private String indirectName;//间接上级名称
    
    private String indirectUidAndName;//间接上级UID|name
    
    private Integer superiorUid;//上级会员UID
    
    private String superiorName;//上级名称(非商家会员)
    
    private String superiorInfo;//上级信息(superiorUid|superiorName)
    
    private Integer referrerSellerid;//(商家)推荐商家
    
    private String sellerName;//上级名称(商家会员)
    
    private String sellerInfo;//上级信息(sellerId|sellerName)

    private Integer referrerSellertype;//(商家)推荐商家类型 1普通商家 2连锁总店 3区域代理

    private Date createTime;//创建时间
    
    private String superiorMixInfo;//上级信息
    
    private long juniors;//当前会员渠道下线人数

    private String superiorIdChosen;//注册会员选择的上级id
    
    private Integer manorLevel;
    
    private Integer manorNectar;


	private Long parentUid;
	private String uname;

    public Integer getManorLevel() {
		return manorLevel;
	}

	public void setManorLevel(Integer manorLevel) {
		this.manorLevel = manorLevel;
	}

	public Integer getManorNectar() {
		return manorNectar;
	}

	public void setManorNectar(Integer manorNectar) {
		this.manorNectar = manorNectar;
	}

	public Long getParentUid() {
        return parentUid;
    }

    public void setParentUid(Long parentUid) {
        this.parentUid = parentUid;
    }
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getObjectOriented() {
        return objectOriented;
    }

    public void setObjectOriented(Integer objectOriented) {
        this.objectOriented = objectOriented;
    }
    
    

    /**
     * 会员渠道来源 1.VIP 2.商家 3.直销 4.V客
	 * @return the objectOrientedStr
	 */
	public String getObjectOrientedStr() {
		if(objectOriented==null){
			return null;
		}
		
		switch (objectOriented) {
		case 1:
			objectOrientedStr="VIP会员";
			break;
		case 2:
			objectOrientedStr="商家会员";
			break;
		case 3:
			objectOrientedStr="直销会员";
			break;
		case 4:
			objectOrientedStr="V客会员";
			break;
		default:
			break;
		}
		return objectOrientedStr;
	}

	/**
	 * @param objectOrientedStr the objectOrientedStr to set
	 */
	public void setObjectOrientedStr(String objectOrientedStr) {
		this.objectOrientedStr = objectOrientedStr;
	}

	/**
	 * @return the nname
	 */
	public String getNname() {
		return nname;
	}

	/**
	 * @param nname the nname to set
	 */
	public void setNname(String nname) {
		this.nname = nname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the uidRelationChain
	 */
	public String getUidRelationChain() {
		return uidRelationChain;
	}

	/**
	 * @param uidRelationChain the uidRelationChain to set
	 */
	public void setUidRelationChain(String uidRelationChain) {
		this.uidRelationChain = uidRelationChain;
	}
	
	

	/**
	 * @return the uidRelationChainNname
	 */
	public String getUidRelationChainNname() {
		return uidRelationChainNname;
	}

	/**
	 * @param uidRelationChainNname the uidRelationChainNname to set
	 */
	public void setUidRelationChainNname(String uidRelationChainNname) {
		this.uidRelationChainNname = uidRelationChainNname;
	}

	public Integer getUidRelationChainLevel() {
        return uidRelationChainLevel;
    }

    public void setUidRelationChainLevel(Integer uidRelationChainLevel) {
        this.uidRelationChainLevel = uidRelationChainLevel;
    }

    public Integer getIndirectUid() {
        return indirectUid;
    }

    public void setIndirectUid(Integer indirectUid) {
        this.indirectUid = indirectUid;
    }
    
    

    /**
	 * @return the indirectName
	 */
	public String getIndirectName() {
		return indirectName;
	}

	/**
	 * @param indirectName the indirectName to set
	 */
	public void setIndirectName(String indirectName) {
		this.indirectName = indirectName;
	}
	
	

	/**
	 * @return the indirectUidAndName
	 */
	public String getIndirectUidAndName() {
		if(indirectUid==null){
			return null;
		}
		
		if(indirectUid!=null){
			indirectUidAndName=indirectUid.toString();
		}
		
		if(StringUtils.isNotBlank(indirectName)){
			indirectUidAndName = indirectUid+"|"+indirectName;
		}
		return indirectUidAndName;
	}

	/**
	 * @param indirectUidAndName the indirectUidAndName to set
	 */
	public void setIndirectUidAndName(String indirectUidAndName) {
		this.indirectUidAndName = indirectUidAndName;
	}
	
	

	/**
	 * @return the superiorUid
	 */
	public Integer getSuperiorUid() {
		try {
			if(StringUtils.isNotBlank(uidRelationChain)){
				String[] uids = uidRelationChain.split(",", -1);
				int length = uids.length;
				if(length>1){
					String superiorUidStr = uids[length-2];
					String superiorUidTemp = superiorUidStr.replaceAll("^(0+)", "");//去掉开头拼接的0
					if(StringUtils.isNotBlank(superiorUidTemp)){
						superiorUid=new Integer(superiorUidTemp);
					}
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return superiorUid;
	}

	/**
	 * @param superiorUid the superiorUid to set
	 */
	public void setSuperiorUid(Integer superiorUid) {
		this.superiorUid = superiorUid;
	}

	/**
	 * @return the superiorName
	 */
	public String getSuperiorName() {
		return superiorName;
	}

	/**
	 * @param superiorName the superiorName to set
	 */
	public void setSuperiorName(String superiorName) {
		this.superiorName = superiorName;
	}
	

	/**
	 * @return the superiorInfo
	 */
	public String getSuperiorInfo() {
		return superiorInfo;
	}

	/**
	 * @param superiorInfo the superiorInfo to set
	 */
	public void setSuperiorInfo(String superiorInfo) {
		this.superiorInfo = superiorInfo;
	}

	/**
	 * @return the sellerName
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * @param sellerName the sellerName to set
	 */
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	

	/**
	 * @return the sellerInfo
	 */
	public String getSellerInfo() {
		return sellerInfo;
	}

	/**
	 * @param sellerInfo the sellerInfo to set
	 */
	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public Integer getReferrerSellerid() {
        return referrerSellerid;
    }

    public void setReferrerSellerid(Integer referrerSellerid) {
        this.referrerSellerid = referrerSellerid;
    }

    public Integer getReferrerSellertype() {
        return referrerSellertype;
    }

    public void setReferrerSellertype(Integer referrerSellertype) {
        this.referrerSellertype = referrerSellertype;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    

	/**
	 * @return the superiorMixInfo
	 */
	public String getSuperiorMixInfo() {
		if(StringUtils.isNotBlank(superiorInfo)){
			superiorMixInfo=superiorInfo;
		}
		
		if(StringUtils.isNotBlank(sellerInfo)){
			superiorMixInfo=sellerInfo;
		}
		return superiorMixInfo;
	}

	/**
	 * @param superiorMixInfo the superiorMixInfo to set
	 */
	public void setSuperiorMixInfo(String superiorMixInfo) {
		this.superiorMixInfo = superiorMixInfo;
	}
	
	

	/**
	 * @return the juniors
	 */
	public long getJuniors() {
		return juniors;
	}

	/**
	 * @param juniors the juniors to set
	 */
	public void setJuniors(long juniors) {
		this.juniors = juniors;
	}


	/**
	 * @return the superiorIdChosen
	 */
	public String getSuperiorIdChosen() {
		return superiorIdChosen;
	}

	/**
	 * @param superiorIdChosen the superiorIdChosen to set
	 */
	public void setSuperiorIdChosen(String superiorIdChosen) {
		this.superiorIdChosen = superiorIdChosen;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BursEarningsRelation [id=" + id + ", uid=" + uid
				+ ", objectOriented=" + objectOriented + ", objectOrientedStr="
				+ objectOrientedStr + ", nname=" + nname + ", phone=" + phone
				+ ", uidRelationChain=" + uidRelationChain
				+ ", uidRelationChainNname=" + uidRelationChainNname
				+ ", uidRelationChainLevel=" + uidRelationChainLevel
				+ ", indirectUid=" + indirectUid + ", indirectName="
				+ indirectName + ", indirectUidAndName=" + indirectUidAndName
				+ ", superiorUid=" + superiorUid + ", superiorName="
				+ superiorName + ", superiorInfo=" + superiorInfo
				+ ", referrerSellerid=" + referrerSellerid + ", sellerName="
				+ sellerName + ", sellerInfo=" + sellerInfo
				+ ", referrerSellertype=" + referrerSellertype
				+ ", createTime=" + createTime + ", superiorMixInfo="
				+ superiorMixInfo + ", juniors=" + juniors
				+ ", superiorIdChosen=" + superiorIdChosen + "]";
	}


	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUname() {
		return uname;
	}
}