import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import pers.congcong.myBatis2.mappers.RoleMapper;
import pers.congcong.myBatis2.pojos.SysPrivilege;
import pers.congcong.myBatis2.pojos.SysRole;

import java.util.List;


/**
 * Created by 聪聪 on 2018/4/27 0027.
 */
public class RoleMapperTest extends BaseMapperTest{
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(1l);

            Assert.assertNotNull(role);

        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAll();
            Assert.assertNotNull(roleList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole roleList = roleMapper.selectById(1l);
            roleMapper.updateById(roleList);
            Assert.assertNotNull(roleList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllRoleAndPrivilege() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles= roleMapper.selectAllRoleAndPrivilege();
            Assert.assertNotNull(sysRoles);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoles= roleMapper.selectRoleByUserId(1l);
            Assert.assertNotNull(sysRoles);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserIdChoose() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole role = roleMapper.selectById(2l);
            role.setEnable(0);
            roleMapper.updateById(role);
            List<SysRole> sysRoles = roleMapper.selectRoleByUserIdChoose(1l);
//一个改了enabled
            for (SysRole r :
                    sysRoles) {
                if (r.getId().equals(1l))
                    Assert.assertNotNull(r.getPrivileges());
                if (r.getId().equals(2l)) {
                    Assert.assertNull(r.getPrivileges());
                    continue;
                }
                for (SysPrivilege sysPrivilege : r.getPrivileges()) {
                    System.out.println(sysPrivilege.getPrivilegeName());
                }
            }
            Assert.assertNotNull(sysRoles);

        } finally {
            sqlSession.close();
        }
    }

}
