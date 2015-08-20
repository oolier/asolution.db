package asolution.db.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import asolution.db.exception.DBError;
import asolution.db.model.stdquery.t_frm_StdQuery;

public interface StdQueryDAO {

	public asolution.db.model.stdquery.StdQuery queryById(Serializable id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError;

}