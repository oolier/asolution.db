package asolution.db.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import asolution.db.exception.DBError;
import asolution.db.model.plugin.t_dtl_Modules;

public interface ModuleDAO
		extends
			BaseDao<asolution.db.model.plugin.t_dtl_Modules> {

	@Override
	public t_dtl_Modules queryById(Serializable id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError;
}
