import netscape.security.Privilege;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import pers.congcong.myBatis2.Provider.PrivilegeProvider;
import pers.congcong.myBatis2.mappers.PrivilegeMapper;
import pers.congcong.myBatis2.pojos.SysPrivilege;

/**
 * Created by 聪聪 on 2018/4/27 0027.
 */
public class PrivilegeMapperTest extends BaseMapperTest{
    @Test
    public void SelectByIdTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege = privilegeMapper.selectById(1l);
            Assert.assertNotNull(privilege);
        } finally {
            sqlSession.close();
        }
    }

}
