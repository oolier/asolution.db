package asolution.db.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import asolution.db.dao.*;
import asolution.db.dictionary.DBErrorType;
import asolution.db.exception.DBError;
import asolution.db.source.AbstractBaseDao;

public class DefinedSQL<T> extends AbstractBaseDao<T> {
	// 默认数据源
	public final static String frmDataSourceName = "frmDataSource";
	private String SqlID;
	private ArrayList<ObjectSqlParam> ObjectSQLParam = new ArrayList<ObjectSqlParam>();
	private ArrayList<ObjectSql> ObjectSQL = new ArrayList<ObjectSql>();
	private ConnectionDaoImpl mutilyConnection;

	public void SetParam(String ParamName, Object ParamValue) throws DBError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		ObjectSqlParam par = FindParamByName(ParamName);
		if (par != null) {
			par.setParamValue(ParamValue);
			return;
		}
		DBErrorType et = DBErrorType.Business;
		throw new DBError(getSqlID(), et, 0, et.toString() + "：" + SqlID
				+ "，SetParam：" + ParamName + "，对象未定义", "加载框架库失败");
	}

	public ObjectSqlParam FindParamByName(String ParamName) {
		for (ObjectSqlParam p : this.ObjectSQLParam) {
			if (p.getParamName().equals(ParamName)) {
				return p;
			}
		}
		return null;
	}

	public String getSqlID() throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {
		return SqlID;
	}

	public void setSqlID(String sqlID) {
		SqlID = sqlID;
	}

	public ArrayList<ObjectSqlParam> getObjectSQLParam()
			throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		return ObjectSQLParam;
	}

	public void setObjectSQLParam(ArrayList<ObjectSqlParam> objectSQLParam) {
		ObjectSQLParam = objectSQLParam;
	}

	public ArrayList<ObjectSql> getObjectSQL() {
		return ObjectSQL;
	}

	public void setObjectSQL(ArrayList<ObjectSql> objectSQL) {
		ObjectSQL = objectSQL;
	}

	public ConnectionDaoImpl getMutilyConnection() {
		return mutilyConnection;
	}

	public void setMutilyConnection(ConnectionDaoImpl mutilyConnection) {
		this.mutilyConnection = mutilyConnection;
	}

}
