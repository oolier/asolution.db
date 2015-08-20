package asolution.db.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import asolution.db.exception.DBError;
import asolution.db.model.dataobject.DataObject;

public interface DataObjectService {
	
	public DataObject queryById(Serializable id) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError;
	
	public void SetParam(String ParamName, Object ParamValue) throws DBError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException;
	
	public List<List<Map<String, Object>>> ExecuteSQL()
			throws SQLException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, DBError;
}
