package asolution.db.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import asolution.db.dao.DataObjectDAO;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.dao.StdQueryDAO;
import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.dictionary.DBErrorType;
import asolution.db.exception.DBError;
import asolution.db.model.dataobject.*;
import asolution.db.model.stdquery.StdQuery;
import asolution.db.model.stdquery.t_frm_StdQueryParams;

public class DataObjectDaoImpl extends DefinedSQL<DataObject> implements
		DataObjectDAO {

	@Override
	public DataObject queryById(Serializable id) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {
		ArrayList<ObjectSqlParam> lisPars = new ArrayList<ObjectSqlParam>();
		lisPars.add(new ObjectSqlParam("SqlID", "String", id, 0, false, false));
		
		this.getDataSourceEntry().set("frmDataSource");
		
		DataObject dataObject = new DataObject();
		String sql = "select ObjectName,ObjectDescription,RelativeTables from t_frm_DataObject where ObjectName = :SqlID";
		dataObject.setDataObject(this.queryForObject(sql, lisPars,
				t_frm_DataObject.class));
		if (dataObject.getDataObject() == null) {
			throw new asolution.db.exception.DBError(id,
					DBErrorType.DataObject, 0, "对象未定义", "加载框架库失败");
		}
		sql = "select ObjectName,Idx,ParamName,ParamType from t_frm_DataObjectParams where ObjectName = :SqlID order by Idx";
		dataObject.setDataObjectParams(this.queryForListObject(sql, lisPars,
				t_frm_DataObjectParams.class));

		sql = "select ObjectName,Idx,SQL,Firmware from t_frm_DataObjectSQL where ObjectName = :SqlID order by Idx";
		dataObject.setDataObjectSQL(this.queryForListObject(sql, lisPars,
				t_frm_DataObjectSQL.class));

		sql = "select ObjectName,FieldName,DefaultValue from t_frm_DataObjectFields where ObjectName = :SqlID";
		dataObject.setDataObjectFields(this.queryForListObject(sql, lisPars,
				t_frm_DataObjectFields.class));

		ArrayList<ObjectSqlParam> objectSQLParam = new ArrayList<ObjectSqlParam>();
		for (t_frm_DataObjectParams p : dataObject.getDataObjectParams()) {

			objectSQLParam.add(new ObjectSqlParam(p.getParamName(), p
					.getParamType(), null, p.getIdx(), false, false));
		}
		dataObject.setObjectSQLParam(objectSQLParam);
		return dataObject;
	}
}
