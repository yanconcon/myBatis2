package pers.congcong.myBatis2.pojos;

/**
 * Created by 聪聪 on 2018/4/24 0024.
 */
public class SysRolePrivilege {
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    private Long privilegeId;
}
