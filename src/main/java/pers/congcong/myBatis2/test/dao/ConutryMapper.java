package pers.congcong.myBatis2.test.dao;

import java.util.List;
import pers.congcong.myBatis2.test.model.Conutry;

public interface ConutryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conutry
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conutry
     *
     * @mbg.generated
     */
    int insert(Conutry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conutry
     *
     * @mbg.generated
     */
    Conutry selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conutry
     *
     * @mbg.generated
     */
    List<Conutry> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table conutry
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Conutry record);
}