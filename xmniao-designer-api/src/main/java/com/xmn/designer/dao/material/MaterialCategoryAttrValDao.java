package com.xmn.designer.dao.material;


import com.xmn.designer.entity.material.MaterialCategoryAttrVal;

import java.util.List;

public interface MaterialCategoryAttrValDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialCategoryAttrVal record);

    int insertSelective(MaterialCategoryAttrVal record);

    MaterialCategoryAttrVal selectByPrimaryKey(Long id);
    
    List<MaterialCategoryAttrVal> findByCategoryAttrId(Long id);

    int updateByPrimaryKeySelective(MaterialCategoryAttrVal record);

    int updateByPrimaryKey(MaterialCategoryAttrVal record);

    List<MaterialCategoryAttrVal> selectByCategoryAttrId(Long id);
}