package com.xmniao.xmn.core.manor.entity;

import com.xmniao.xmn.core.base.BaseEntity;

public class TManorFlowerConvert extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5552128202422437201L;

	private Integer id;

    private Double coin;

    private Integer flowers;

    private Integer lifeCycle;
    
    private String description;
    
    private String producutJson;
    
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public Integer getFlowers() {
        return flowers;
    }

    public void setFlowers(Integer flowers) {
        this.flowers = flowers;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProducutJson() {
		return producutJson;
	}

	public void setProducutJson(String producutJson) {
		this.producutJson = producutJson;
	}
    
	
    
}