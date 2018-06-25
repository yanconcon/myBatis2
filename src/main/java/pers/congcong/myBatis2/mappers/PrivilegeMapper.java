package pers.congcong.myBatis2.mappers;

import org.apache.ibatis.annotations.SelectProvider;
import pers.congcong.myBatis2.Provider.PrivilegeProvider;
import pers.congcong.myBatis2.pojos.SysPrivilege;

/**
 * Created by 聪聪 on 2018/4/27 0027.
 */
public interface PrivilegeMapper {
    /**
     * Provider 方法
     * @param id
     * @return
     */
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);
}
