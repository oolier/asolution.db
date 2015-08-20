package asolution.db.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.model.plugin.tConnections;

public interface ConnectionDAO {

	public void getConnection(final Serializable id,
			ConnectionDBObjectType dbObjectType) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException;
}
