package pers.congcong.myBatis2.mappers;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import pers.congcong.myBatis2.pojos.SysRole;
import pers.congcong.myBatis2.pojos.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by 聪聪 on 2018/4/24 0024.
 */
public interface UserMapper {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查询全部用户
     *
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserId(Long userId);

    /**
     * 插入数据
     *
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 返回自增长的主键值
     *
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * 使用selectKey方式----新增用户
     *
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    int deleteById(Long id);

    /**
     * 根据用户的ID和角色的enable获取用户的角色
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(
            @Param("userId") Long userId,
            @Param("enabled") int enabled
    );

    /**
     * 根据动态条件查询用户信息
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 用if动态更新user
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 使用choose，选择ID查询或者用户名查询或者不查询
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户id集合查询
     * @param idArray
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idArray);

    /**
     * 批量插入
     * @param sysUsers
     * @return
     */
    int insertList(List<SysUser> sysUsers);

    /**
     * 通过map更新数据
     * @param map
     * @return
     */
    int updateByMap(Map<String, Object> map);

    /**
     * 一对一映射
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * association标签的嵌套选择
     * @param id
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);

    /**
     * 选择role的List
     * @return
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 三层嵌套查询
     * @param id
     * @return
     */
    List<SysUser> selectAllUserAndRoleSelect(Long id);

}
