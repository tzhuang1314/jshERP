package com.jsh.erp.datasource.mappers;

import com.jsh.erp.datasource.entities.*;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DepotItemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int countByExample(DepotItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int deleteByExample(DepotItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int insert(DepotItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int insertSelective(DepotItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    List<DepotItem> selectByExample(DepotItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    DepotItem selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") DepotItem record, @Param("example") DepotItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") DepotItem record, @Param("example") DepotItemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(DepotItem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table jsh_depotitem
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DepotItem record);



}