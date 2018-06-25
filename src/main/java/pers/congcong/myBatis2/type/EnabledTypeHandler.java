package pers.congcong.myBatis2.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 聪聪 on 2018/6/21 0021.
 */
public class EnabledTypeHandler implements TypeHandler<Enabled> {
    private Map<Integer, Enabled> enabledMap = new HashMap<Integer, Enabled>();

    public EnabledTypeHandler() {
        for (Enabled enabled : Enabled.values()) {
            enabledMap.put(enabled.getValue(),enabled);
        }
    }
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Enabled enabled, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, enabled.getValue());
    }

    @Override
    public Enabled getResult(ResultSet resultSet, String s) throws SQLException {
        Integer value = resultSet.getInt(s);
        return enabledMap.get(s);
    }

    @Override
    public Enabled getResult(ResultSet resultSet, int i) throws SQLException {
        Integer v = resultSet.getInt(i);
        return enabledMap.get(v);
    }

    @Override
    public Enabled getResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer v = callableStatement.getInt(i);
        return enabledMap.get(v);
    }
}
