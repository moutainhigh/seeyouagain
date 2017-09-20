package com.xmniao.xmn.core.vstar.entity;

import java.util.Date;

import com.xmniao.xmn.core.base.BaseEntity;

public class Dynamic extends BaseEntity{
    /**
     * 主键
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 跳转地址
     */
    private String jumpUrl;

    /**
     * 状态 01 正常 02 已删除
     */
    private Integer state;

    /**
     * 排序号 排序值越大排序越前
     */
    private Integer sort;

    /**
     * 适用省ID
     */
    private Integer provinceId;

    /**
     * 适用城市ID
     */
    private Integer cityId;

    /**
     * 1:主  2:次
     */
    private Integer location;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 内容
     */
    private String content;
    
    private String provinceName;
    
    private String locationStr;
    
    private String cityName;
    public String getLocationStr() {
    	if(location==null){
    		return null;
    	}
		switch (location) {
		case 1:
			return "主";
		case 2:
			return "次";
		default:
			return null;
		}
	}

	public void setLocationStr(String locationStr) {
		this.locationStr = locationStr;
	}

    
    public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
     * 主键
     * @return id 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 标题
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 图片地址
     * @return image_url 图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 图片地址
     * @param imageUrl 图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * 跳转地址
     * @return jump_url 跳转地址
     */
    public String getJumpUrl() {
        return jumpUrl;
    }

    /**
     * 跳转地址
     * @param jumpUrl 跳转地址
     */
    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl == null ? null : jumpUrl.trim();
    }

    /**
     * 状态 01 正常 02 已删除
     * @return state 状态 01 正常 02 已删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 状态 01 正常 02 已删除
     * @param state 状态 01 正常 02 已删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 排序号 排序值越大排序越前
     * @return sort 排序号 排序值越大排序越前
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序号 排序值越大排序越前
     * @param sort 排序号 排序值越大排序越前
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 适用省ID
     * @return province_id 适用省ID
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 适用省ID
     * @param provinceId 适用省ID
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 适用城市ID
     * @return city_id 适用城市ID
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 适用城市ID
     * @param cityId 适用城市ID
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 1:主  2:次
     * @return location 1:主  2:次
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * 1:主  2:次
     * @param location 1:主  2:次
     */
    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}