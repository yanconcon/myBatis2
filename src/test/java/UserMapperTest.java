import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import pers.congcong.myBatis2.mappers.UserMapper;
import pers.congcong.myBatis2.pojos.SysPrivilege;
import pers.congcong.myBatis2.pojos.SysRole;
import pers.congcong.myBatis2.pojos.SysUser;
import pers.congcong.myBatis2.proxy.MyMapperProxy;

import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created by 聪聪 on 2018/4/24 0024.
 */
public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user1 = userMapper.selectById(1l);

            Assert.assertNotNull(user1);
            System.out.println(user1.getUserName());
        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> sysUserList = sqlSession.selectList("selectAll");
            Assert.assertNotNull(sysUserList);
            for (SysUser u :
                    sysUserList) {
                System.out.println(u.getUserName());
            }
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRoleByUserId(1l);
            Assert.assertNotNull(roleList);
            for (SysRole r :roleList)
                System.out.println(r.getRoleName());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void testInsertSysUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser("test1", "123456", "111@qq.com", "congeeee", new byte[] {1, 2, 3}, new Date());
            int result = userMapper.insert2(user);

            Assert.assertEquals(1,result);
            Assert.assertNotNull(user.getId());
        }finally {
            sqlSession.close();
        }


    }
    @Test
    public void testUpdate() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1l);

            user.setUserInfo("聪哥");
            userMapper.updateById(user);
        }finally {
            sqlSession.rollback();
//            sqlSession.commit();一般是commit不是回滚！！！！！！！
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user1 = userMapper.selectById(1l);
            Assert.assertNotNull(user1);

            userMapper.deleteById(1l);
            user1 = userMapper.selectById(1l);
            Assert.assertNull(user1);
        }finally {
            sqlSession.rollback();
//            默认sqlSessionFactory.openSession是不自动提交的
            sqlSession.close();
        }
    }
    @Test
    public void testselectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> sysUserList = userMapper.selectRolesByUserIdAndRoleEnabled(1l, 1);

            for (SysRole role :
                    sysUserList) {
                System.out.println(role.getRoleName());
            }
        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testMyMapperProxy() {
        SqlSession sqlSession = getSqlSession();
        MyMapperProxy myMapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] {
                UserMapper.class
        }, myMapperProxy);

        List<SysUser> sysUserList = userMapper.selectAll();
    }

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setUserName("ad");
            List<SysUser> sysUserList = userMapper.selectByUser(sysUser);

            Assert.assertTrue(sysUserList.size()>0);

//            根据邮箱查
            sysUser.setUserEmail("test@mybatis.tk");
            sysUser.setUserName(null);
            sysUserList = userMapper.selectByUser(sysUser);


//            根据用户和邮箱查
            sysUser.setUserName("ad");
            Assert.assertTrue(sysUserList.size()==0);

        } finally {
            sqlSession.close();
        }
    }
    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser sysUser = userMapper.selectById(1l);
            sysUser.setUserName("cccc");


            userMapper.updateByIdSelective(sysUser);

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testselectByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser sysUser = userMapper.selectById(1l);
            sysUser.setId(null);

            SysUser sysUser1 = userMapper.selectByIdOrUserName(sysUser);

            sysUser.setUserName(null);
            sysUser1 = userMapper.selectByIdOrUserName(sysUser);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<Long> idList = new ArrayList<Long>();
            idList.add(1l);
            idList.add(1001l);

            List<SysUser> sysUserList = userMapper.selectByIdList(idList);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> sysUserList = new ArrayList<SysUser>();

            for (int i = 0; i < 2; i++) {
                SysUser sysUser = new SysUser();
                sysUser.setUserName("cc");
                sysUser.setUserPassword("111111");
                sysUser.setUserEmail("testck@qq.com");
                sysUserList.add(sysUser);
            }

            int result = userMapper.insertList(sysUserList);

            for (SysUser s :
                    sysUserList) {
                System.out.println(s.getId());
            }

            Assert.assertEquals(2, result);
        } finally {
            //为了不影响其他测试，这里选择回滚
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("id", 1l);
            map.put("user_name", "1111@qq.com");
            map.put("user_password", "11111");
            userMapper.updateByMap(map);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> sysUserList = userMapper.selectAllUserAndRoles();

            Assert.assertNotNull(sysUserList);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser sysUser = userMapper.selectUserAndRoleByIdSelect(1001l);

            System.out.println("调用user.getSysRole()");

            Assert.assertNotNull(sysUser.getSysRole());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUserRoleMapSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            SysUser sysRole = userMapper.selectUserAndRoleByIdSelect(1001l);

            Assert.assertNotNull(sysRole);
        } finally {

            sqlSession.close();
        }

        int i = 12;
        System.out.println(i+=i-=i*=i);
    }

    @Test
    public void testSelectAllUserAndRoleSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> sysUser = userMapper.selectAllUserAndRoleSelect(1l);


            for (SysUser s :
                    sysUser) {
                System.out.println("用户名：" + s.getUserName());
                for (SysRole r :
                        s.getSysRoleList()) {
                    System.out.println("角色名：" + r.getRoleName());
                    for (SysPrivilege p : r.getPrivileges()
                            ) {
                        System.out.println(p.getPrivilegeName());
                    }
                }
            }

        } finally {
            sqlSession.close();
        }
    }




}
