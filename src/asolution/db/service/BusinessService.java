package asolution.db.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import asolution.db.dao.ObjectSql;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.exception.CheckError;
import asolution.db.exception.DBError;
import asolution.db.model.business.Bueinsess;
import asolution.db.vo.Business;

public interface BusinessService {

	public abstract asolution.db.model.business.Bueinsess queryById(
			Serializable id) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError;

	public void SetParam(String ParamName, Object ParamValue) throws DBError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException;

	public void AddParam(String ParamName, Object ParamValue)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, DBError,
			SQLException;

	public Object GetParam(String ParamName)
			throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException, SQLException;

	public boolean ExecuteSQL() throws CheckError, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError;
}
