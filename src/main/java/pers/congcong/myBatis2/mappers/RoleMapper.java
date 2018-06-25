package pers.congcong.myBatis2.mappers;

import org.apache.ibatis.annotations.*;
import org.omg.PortableInterceptor.INACTIVE;
import pers.congcong.myBatis2.pojos.SysRole;

import java.util.List;

/**
 * Created by 聪聪 on 2018/4/26 0026.
 */
public interface RoleMapper {
    /**
     * 通过id选择全部
     * @param id
     * @return
     */
    SysRole selectById(Long id);


    /**
     * 选择全部，用ResultMap引用id
     * @return
     */
    List<SysRole> selectAll();

    /**
     * 插入一个sys_role值
     * @param sysRole
     * @return
     */
    int insert(SysRole sysRole);

    /**
     * 返回自增主键
     * @param sysRole 插入值
     * @return
     */

    int insert2(SysRole sysRole);


    /**
     * 返回非自增式主键
     * @param sysRole
     * @return
     */
    int insert3(SysRole sysRole);

    /**
     * 通过id更新
     * @param sysRole
     * @return
     */

    int updateById(
            @Param("sysRole") SysRole sysRole
    );

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @Delete(value = "delete from sys_role where id = #{id}")
    int deleteById(Long id);

    /**
     *
     * @return
     */
    List<SysRole> selectAllRoleAndPrivilege();

    /**
     * 通过id选择SysRole
     * @param id
     * @return
     */
    List<SysRole> selectRoleByUserId(Long id);

    /**
     * 鉴别器映射，当角色的enabled不满足时，无法获得用户的权限。
     * @param id
     * @return
     */
    List<SysRole> selectRoleByUserIdChoose(Long id);

}
