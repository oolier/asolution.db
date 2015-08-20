package asolution.db.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.exception.DBError;
import asolution.db.model.dataobject.DataObject;
import asolution.db.model.dataobject.t_frm_DataObjectSQL;
import asolution.db.service.DataObjectService;

public class DataObjectServiceImpl implements DataObjectService {

	private asolution.db.dao.impl.DataObjectDaoImpl dataObjectDaoImpl;
	private asolution.db.model.dataobject.DataObject dataObject;
	@Override
	public DataObject queryById(Serializable id) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {
		dataObject = dataObjectDaoImpl.queryById(id);
		return dataObject;
	}
	@Override
	public void SetParam(String ParamName, Object ParamValue) throws DBError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		dataObjectDaoImpl.SetParam(ParamName, ParamValue);
	}

	@Override
	public List<List<Map<String, Object>>> ExecuteSQL()
			throws SQLException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, DBError {

		dataObjectDaoImpl.getMutilyConnection().getConnection(
				dataObject.getDataObject().getObjectName(),
				ConnectionDBObjectType.DataObject);
		List<List<Map<String, Object>>> data = new ArrayList<List<Map<String, Object>>>();
		for (t_frm_DataObjectSQL sql : dataObject.getDataObjectSQL()) {
			data.add(dataObjectDaoImpl.queryForList(sql.getSQL(),
					dataObject.getObjectSQLParam()));
		}
		return data;

	}
	public void setDataObjectDaoImpl(
			asolution.db.dao.impl.DataObjectDaoImpl dataObjectDaoImpl) {
		this.dataObjectDaoImpl = dataObjectDaoImpl;
	}
}
