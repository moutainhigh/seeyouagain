package com.xmn.designer.entity.address;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DesigberAddress {
    private Integer id;

    private Integer sellerid;

    private String nname;
    
    private Integer sex;

    private Integer provinceId;

    private String province;

    private String city;

    private Integer cityId;

    private String areaName;

    private Integer areaId;

    private String address;

    private Integer zipCode;

    private String phone;

    private Integer status;

    private Integer isDefault;

    private Date createDate;

    private Date updateDate;
    
    public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname == null ? null : nname.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	
    public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public Map<String,Object> transformRsponse(){
    	Map<String,Object> map=new HashMap<String, Object>();
		map.put("id", this.getId());
		map.put("nname", this.getNname());
		map.put("phone", this.getPhone());
		map.put("province", this.getProvince());
		map.put("city", this.getCity());
		map.put("areaName", this.getAreaName());
		map.put("address", this.getAddress());
		map.put("isDefault",this.getIsDefault());
		map.put("sex",this.getSex());
		map.put("provinceId", this.getProvinceId());
		map.put("cityId", this.getCityId());
		map.put("areaId", this.getAreaId());
		return map;
    }
}