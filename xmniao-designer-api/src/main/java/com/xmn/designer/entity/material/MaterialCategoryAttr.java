package com.xmn.designer.entity.material;

import java.util.List;

public class MaterialCategoryAttr {
    private Long id;


    private String name;

    private Integer sortVal;

    private String picUrl;

    private String isCustomize;

    private String isMultiple;

    private String isCustomizable;

    private List<String> vals;

    private boolean customizable;
    private boolean multipleChoice;


    public boolean isMultipleChoice() {
        return multipleChoice;
    }

    public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    public boolean isCustomizable() {
        return customizable;
    }

    public void setCustomizable(boolean customizable) {
        this.customizable = customizable;
    }

    public List<String> getVals() {
        return vals;
    }

    public void setVals(List<String> vals) {
        this.vals = vals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSortVal() {
        return sortVal;
    }

    public void setSortVal(Integer sortVal) {
        this.sortVal = sortVal;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getIsCustomize() {
        return isCustomize;
    }

    public void setIsCustomize(String isCustomize) {
        this.isCustomize = isCustomize == null ? null : isCustomize.trim();
    }

    public String getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(String isMultiple) {
        this.isMultiple = isMultiple == null ? null : isMultiple.trim();
    }

    public String getIsCustomizable() {
        return isCustomizable;
    }

    public void setIsCustomizable(String isCustomizable) {
        this.isCustomizable = isCustomizable == null ? null : isCustomizable.trim();
    }
}