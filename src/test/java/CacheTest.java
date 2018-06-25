import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import pers.congcong.myBatis2.mappers.RoleMapper;
import pers.congcong.myBatis2.mappers.UserMapper;
import pers.congcong.myBatis2.pojos.SysRole;
import pers.congcong.myBatis2.pojos.SysUser;


/**
 * Created by 聪聪 on 2018/6/23 0023.
 */
public class CacheTest extends BaseMapperTest {
    @Test
    public void testL1Cache() {
        SqlSession sqlSession = getSqlSession();
        SysUser sysUser1 = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            sysUser1 = userMapper.selectById(1l);
            sysUser1.setUserName("new name");
            SysUser sysUser2 = userMapper.selectById(1l);
            //虽然没有更新数据库，但是用户名跟user1的相同
            Assert.assertEquals("new name", sysUser2.getUserName());
            //user1跟user2完全就是同一个对象
            Assert.assertEquals(sysUser1, sysUser2);

            System.out.println("开始一个新的session");

            SqlSession newSqlSession = getSqlSession();
            try {
                UserMapper userMapper1 = newSqlSession.getMapper(UserMapper.class);
                SysUser sysUser3 = userMapper1.selectById(1l);
                //这里的sysUser3跟上面的sysUser1不是同一个实例，
                Assert.assertNotEquals("new name", sysUser3.getUserName());
                Assert.assertNotEquals(sysUser3, sysUser1);

                //执行delete、update、insert后都会清空一级缓存，所以下面两个对象不相同。
                userMapper1.deleteById(2l);
                SysUser sysUser4 = userMapper1.selectById(1l);
                Assert.assertNotEquals(sysUser3, sysUser4);

            } finally {
                newSqlSession.close();
            }

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testL2Cache() {
        SqlSession sqlSession = getSqlSession();
        SysRole sysRole = null;
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            sysRole = roleMapper.selectById(1l);
            sysRole.setRoleName("new name");

            SysRole sysRole1 = roleMapper.selectById(1l);

            Assert.assertEquals("new name", sysRole1.getRoleName());
            //一级缓存，两个查询相同的对象是相同的
            Assert.assertEquals(sysRole, sysRole1);
        } finally {
            sqlSession.close();
        }
        System.out.println("开始另一个查询");

        sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            SysRole sysRole1 = roleMapper.selectById(1l);

            Assert.assertEquals("new name", sysRole1.getRoleName());

            Assert.assertNotEquals(sysRole, sysRole1);

            SysRole sysRole2 = roleMapper.selectById(1l);

            // sysRole1 sysRole2是两个反序列化得到的结果，是不相同的实例
            Assert.assertNotEquals(sysRole1, sysRole2);
        } finally {
            sqlSession.close();
        }
    }
}
