package com.xue.sell.pojo.mapper;

import com.xue.sell.pojo.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by miller on 2018/5/30
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name, category_type) values(#{category_name,jdbcType= VARCHAR}, #{category_type, jdbcType=INTEGER})")
    int  insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name, category_type) values(#{categoryName,jdbcType= VARCHAR}, #{categoryType, jdbcType=INTEGER})")
    int  insertByObject(ProductCategory category);

    @Select("select * from product_category where category_type = #{categoryType, jdbcType=INTEGER}")
    @Results(value = {
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_id", property = "categoryId")
    })
    ProductCategory findByCategoryType(Integer categoryType);


    @Select("select * from product_category where category_name = #{categoryName, jdbcType=VARCHAR}")
    @Results(value = {
            @Result(column = "category_type", property = "categoryType"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_id", property = "categoryId")
    })
    List<ProductCategory> findByCategoryName(String categoryName);


    @Update("update product_category set category_name = #{categoryName, jdbcType=VARCHAR} where category_type = #{categoryType, jdbcType=INTEGER} ")
    int updateByCategoryType(@Param("categoryType") Integer categoryType ,
                             @Param("categoryName") String categoryName);

    @Update("update product_category set category_name = #{categoryName, jdbcType=VARCHAR} where category_type = #{categoryType, jdbcType=INTEGER} ")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType, jdbcType=INTEGER}")
    int deleteByCategoryType(Integer categoryType);


    /*通过XML*/
    ProductCategory selectByCategoryType(Integer categoryType);
}
