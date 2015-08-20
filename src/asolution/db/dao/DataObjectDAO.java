package asolution.db.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import asolution.db.exception.DBError;
import asolution.db.model.dataobject.DataObject;

public interface DataObjectDAO{

	public DataObject queryById(Serializable id) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError;
}
