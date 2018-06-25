package pers.congcong.myBatis2.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by 聪聪 on 2018/4/24 0024.
 */
public class SysRole implements Serializable {

    private Long id;
    private String roleName;
    private int enabled;
    private Long createBy;
    private Date createTime;

    List<SysPrivilege> privileges;

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<SysPrivilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<SysPrivilege> privileges) {
        this.privileges = privileges;
    }

    public SysRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getEnable() {
        return enabled;
    }

    public void setEnable(int enable) {
        this.enabled = enable;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
