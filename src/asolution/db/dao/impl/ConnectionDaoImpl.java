package asolution.db.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import asolution.db.dao.ConnectionDAO;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.model.plugin.tConnections;
import asolution.db.model.plugin.t_frm_Connections;
import asolution.db.source.AbstractBaseDao;

public class ConnectionDaoImpl extends AbstractBaseDao<tConnections> implements
		ConnectionDAO {

	public void getConnection(final Serializable id,
			ConnectionDBObjectType dbObjectType) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException {

		String DBObjectType;
		switch (dbObjectType) {
		case StdQuery:
			DBObjectType = "1";
			break;
		case DataObject:
			DBObjectType = "2";
		default:
			DBObjectType = "0";
			break;
		}

		ArrayList<ObjectSqlParam> lisPars = new ArrayList<ObjectSqlParam>();
		lisPars.add(new ObjectSqlParam("DBObjectName", "String", id, 0, false,
				false));
		lisPars.add(new ObjectSqlParam("DBObjectType", "String", DBObjectType,
				0, false, false));

		getDataSourceEntry().set("frmDataSource");
		String sql = "select * from t_frm_Connections where DBObjectName = :DBObjectName and DBObjectType = :DBObjectType";

		asolution.db.model.plugin.t_frm_Connections conEntity = queryForObject(
				sql, lisPars, asolution.db.model.plugin.t_frm_Connections.class);

		getDataSourceEntry().set("misDataSource");
		if (conEntity != null) {
			ArrayList<ObjectSqlParam> param2 = new ArrayList<ObjectSqlParam>();
			param2.add(new ObjectSqlParam("DBID", "String",
					conEntity.getDBID(), 0, false, false));
			sql = "select ConnName = sConnName, Description = sDescription,BSDBName = sBSDBName from tConnections where sConnName = :DBID ";

			tConnections misConnEntity = queryForObject(sql, param2,
					tConnections.class);
			if (misConnEntity != null) {
				getDataSourceEntry().set(misConnEntity.getBSDBName());
			}
		}
	}


}
