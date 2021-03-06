package com.xmn.designer.dao.material;

import com.xmn.designer.entity.material.OrderMaterialCarousel;

public interface OrderMaterialCarouselDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderMaterialCarousel record);

    int insertSelective(OrderMaterialCarousel record);

    OrderMaterialCarousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderMaterialCarousel record);

    int updateByPrimaryKey(OrderMaterialCarousel record);
}