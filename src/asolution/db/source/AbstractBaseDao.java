package asolution.db.source;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidPooledConnection;

import asolution.db.dao.ObjectSqlParam;
import asolution.db.model.stdquery.t_frm_StdQuery;
import asolution.db.model.stdquery.t_frm_StdQueryParams;
import asolution.db.util.NamedParameterStatement;

public abstract class AbstractBaseDao<T> {

	private DataSourceEntryImpl dataSourceEntry;
	private DynamicDataSource dynamicDataSource;

	// SPRING JDBC妯℃澘鎺ュ彛
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> queryForList(String sql,
			ArrayList<ObjectSqlParam> params) {
		ArrayList<Object> pars = FilterParams(sql, params);

		sql = (String) pars.get(pars.size() - 1);
		pars.remove(pars.size() - 1);
		return this.jdbcTemplate.queryForList(sql, pars.toArray());
		// return new SimpleJdbcTemplate(dynamicDataSource).queryForList(sql,
		// pars.toArray());
	}

	public Map<String, Object> queryForMap(String sql,
			ArrayList<ObjectSqlParam> params) {
		return queryForList(sql, params).get(0);
	}

	public <T> List<T> queryForListObject(String sql,
			ArrayList<ObjectSqlParam> params, final Class<T> c) {
		final Method[] methods = c.getMethods();
		RowMapper<T> rm = new RowMapper<T>() {
			@Override
			public T mapRow(ResultSet rs, int arg1) throws SQLException {
				try {
					ResultSetMetaData rsmd = rs.getMetaData();

					T pojo = (T) c.newInstance();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						String setMethodName = "set" + rsmd.getColumnName(i);

						for (int j = 0; j < methods.length; j++) {
							if (methods[j].getName().equalsIgnoreCase(
									setMethodName)) {

								int dbType = rsmd.getColumnType(i);

								Method method = null;

								if (dbType == Types.TINYINT) {
									method = c.getMethod(setMethodName,
											byte.class);
									method.invoke(pojo, rs.getByte(i));
								} else if (dbType == Types.SMALLINT) {
									method = c.getMethod(setMethodName,
											short.class);
									method.invoke(pojo, rs.getShort(i));
								} else if (dbType == Types.INTEGER) {
									method = c.getMethod(setMethodName,
											int.class);
									method.invoke(pojo, rs.getInt(i));
								} else if (dbType == Types.BIGINT) {
									method = c.getMethod(setMethodName,
											long.class);
									method.invoke(pojo, rs.getLong(i));
								} else if (dbType == Types.FLOAT
										|| dbType == Types.REAL) {
									method = c.getMethod(setMethodName,
											float.class);
									method.invoke(pojo, rs.getFloat(i));
								} else if (dbType == Types.DOUBLE) {
									method = c.getMethod(setMethodName,
											double.class);
									method.invoke(pojo, rs.getDouble(i));
								} else if (dbType == Types.DECIMAL
										|| dbType == Types.NUMERIC) {
									method = c.getMethod(setMethodName,
											BigDecimal.class);
									method.invoke(pojo, rs.getBigDecimal(i));
								} else if (dbType == Types.BIT) {
									method = c.getMethod(setMethodName,
											boolean.class);
									method.invoke(pojo, rs.getBoolean(i));
								} else if (dbType == Types.CHAR
										|| dbType == Types.VARCHAR
										|| dbType == Types.LONGVARCHAR
										|| dbType == Types.CLOB) {
									method = c.getMethod(setMethodName,
											String.class);
									method.invoke(pojo, rs.getString(i));
								} else if (dbType == Types.DATE) { // 继承于
																	// java.util.Date
																	// 类
									method = c.getMethod(setMethodName,
											Date.class);
									method.invoke(pojo, rs.getDate(i));
								} else if (dbType == Types.TIME) { // 继承于
																	// java.util.Date
																	// 类
									method = c.getMethod(setMethodName,
											Time.class);
									method.invoke(pojo, rs.getTime(i));
								} else if (dbType == Types.TIMESTAMP) { // 继承于
																		// java.util.Date
																		// 类
									method = c.getMethod(setMethodName,
											Timestamp.class);
									method.invoke(pojo, rs.getTimestamp(i));
								} else if (dbType == Types.BINARY
										|| dbType == Types.VARBINARY
										|| dbType == Types.LONGVARBINARY
										|| dbType == Types.BLOB) {
									method = c.getMethod(setMethodName,
											byte[].class);
									method.invoke(pojo, rs.getBytes(i));
								} else {
									throw new Exception("数据库中包含不支持的自动映射数据类型："
											+ dbType);
								}
							}
						}
					}
					return pojo;
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;

			}
		};
		ArrayList<Object> pars = FilterParams(sql, params);
		sql = (String) pars.get(pars.size() - 1);
		pars.remove(pars.size() - 1);
		List<T> aaa = this.jdbcTemplate.query(sql, rm, pars.toArray());
		// List<T> aaa = new SimpleJdbcTemplate(dynamicDataSource).query(sql,
		// rm,
		// pars.toArray());
		return aaa;
	}

	public <T> T queryForObject(String sql, ArrayList<ObjectSqlParam> params,
			Class<T> c) {
		List<T> lis = queryForListObject(sql, params, c);
		if (lis == null || lis.size() == 0)
			return null;
		return lis.get(0);

	}

	/**
	 * 
	 * @param conn
	 *            可传入 conn,conn.setAutoCommit(false);启动事务； conn.commit(); 提交
	 * @param sql
	 * @param sqlParam
	 * @return
	 * @throws SQLException
	 */

	public int Execute(String sql, ArrayList<ObjectSqlParam> params)
			throws SQLException {
		ArrayList<Object> pars = FilterParams(sql, params);

		sql = (String) pars.get(pars.size() - 1);
		pars.remove(pars.size() - 1);
		return	jdbcTemplate.update(sql,pars.toArray());
	//	return new SimpleJdbcTemplate(dynamicDataSource).update(sql,
		//		pars.toArray());
	}
	
	
	public ArrayList<ObjectSqlParam> FilterParamsToObjectSqlParamList(
			String sql, ArrayList<ObjectSqlParam> sqlParam) {
		String[] fields = sql.replace("\r", " ").replace("\n", " ")
				.replace(",", " ").replace("[", " ").replace("]", " ")
				.replace("=", " ").replace("(", " ").replace(")", " ")
				.replace("<", " ").replaceAll(">", " ").replace("*", " ")
				.replace("/", " ").replace("\\", " ").split(" ");
		ArrayList<ObjectSqlParam> newPars = new ArrayList<ObjectSqlParam>();

		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < sqlParam.size(); j++) {
				ObjectSqlParam param = sqlParam.get(j);
				if (!fields[i].equals(":" + param.getParamName())) {
					continue;
				}
				newPars.add(sqlParam.get(j));
				break;
			}
		}

		return newPars;

	}

	public ArrayList<Object> FilterParams(String sql,
			ArrayList<ObjectSqlParam> sqlParam) {

		// HashMap<String, Object> pars = new HashMap<String, Object>();

		String[] fields = sql.replace("\r", " ").replace("\n", " ")
				.replace(",", " ").replace("[", " ").replace("]", " ")
				.replace("=", " ").replace("(", " ").replace(")", " ")
				.replace("<", " ").replaceAll(">", " ").replace("*", " ")
				.replace("/", " ").replace("\\", " ").split(" ");
		ArrayList<Object> pars = new ArrayList<Object>();
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < sqlParam.size(); j++) {
				ObjectSqlParam param = sqlParam.get(j);
				if (fields[i].equals(":" + param.getParamName())) {

					sql = sql.replace(":" + param.getParamName(), "?");

					String paramType = param.getParamType().toLowerCase();
					Object value = param.getParamValue();

					if (paramType.indexOf("%") != -1 && value == null)
						value = "";
					if (paramType.indexOf("%") == 0)
						value = "%" + value;
					if (paramType.lastIndexOf("%") == paramType.length() - 1)
						value = value + "%";

					if ((value == null || value.toString().length() == 0)
							&& (paramType == "string" || paramType == "list")) {
						value = "";
					} else if ((paramType.indexOf("time") != -1 || paramType
							.indexOf("date") != -1)
							&& (value == null || value.toString() == "")) {
						value = null;
					} else if (paramType == "boolean") {
						if (value == null)
							value = "";
						value = value.toString() == "1";
					} else {
						value = value == null || value.toString().length() == 0 ? null
								: value;
					}

					pars.add(value);

					break;
				}
			}
		}
		pars.add(sql);

		// for (ObjectSqlParam param : sqlParam) {
		// String paramType = param.getParamType().toLowerCase();
		// Object value = param.getParamValue();
		//
		// if (paramType.indexOf("%") != -1 && value == null)
		// value = "";
		// if (paramType.indexOf("%") == 0)
		// value = "%" + value;
		// if (paramType.lastIndexOf("%") == paramType.length() - 1)
		// value = value + "%";
		//
		// if ((value == null || value.toString().length() == 0)
		// && (paramType == "string" || paramType == "list")) {
		// value = "";
		// } else if ((paramType.indexOf("time") != -1 || paramType
		// .indexOf("date") != -1)
		// && (value == null || value.toString() == "")) {
		// value = null;
		// } else if (paramType == "boolean") {
		// if (value == null)
		// value = "";
		// value = value.toString() == "1";
		// } else if (paramType.equals("integer")) {
		// if (value == null)
		// value = 0;
		// } else {
		// value = value == null || value.toString().length() == 0 ? null
		// : value;
		// }
		//
		// pars.put(param.getParamName(), value);
		//
		// }

		return pars;
	}

	public DataSourceEntryImpl getDataSourceEntry() {
		return dataSourceEntry;
	}

	public void setDataSourceEntry(DataSourceEntryImpl dataSourceEntry) {
		this.dataSourceEntry = dataSourceEntry;
	}

	public DynamicDataSource getDynamicDataSource() {
		return dynamicDataSource;
	}

	public void setDynamicDataSource(DynamicDataSource dynamicDataSource) {
		this.dynamicDataSource = dynamicDataSource;
	}
}
