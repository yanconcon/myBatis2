package pers.congcong.myBatis2.Provider;


import org.apache.ibatis.jdbc.SQL;

/**
 * Created by 聪聪 on 2018/4/27 0027.
 */
public class PrivilegeProvider {
    public String selectById(final Long id) {
        return "select id, privilege_name, privilege_url from sys_privilege where id = #{id}";
    }
}
