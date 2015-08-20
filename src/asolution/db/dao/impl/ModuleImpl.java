package asolution.db.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import asolution.db.dao.ModuleDAO;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.exception.DBError;
import asolution.db.model.dataobject.t_frm_DataObjectFields;
import asolution.db.model.plugin.t_dtl_Modules;

public class ModuleImpl extends DefinedSQL<t_dtl_Modules> implements ModuleDAO {

	@Override
	public t_dtl_Modules queryById(Serializable id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {

		ArrayList<ObjectSqlParam> lisPars = new ArrayList<ObjectSqlParam>();
		lisPars.add(new ObjectSqlParam("ModuleID", "String", id, 0, false,
				false));

		this.getDataSourceEntry().set("frmDataSource");

		String sql = "select * from t_dtl_Modules where ModuleID = ?";

		t_dtl_Modules module = this.queryForObject(sql, lisPars,
				t_dtl_Modules.class);

		// TODO Auto-generated method stub
		return module;
	}

	@Override
	public int insert(t_dtl_Modules model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertForBatch(List<t_dtl_Modules> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(t_dtl_Modules model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateForBatch(List<t_dtl_Modules> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByIdForBatch(List<Serializable> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<t_dtl_Modules> queryForAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable queryForPageTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<t_dtl_Modules> queryForPageList(Serializable offset,
			Serializable total) {
		// TODO Auto-generated method stub
		return null;
	}
}
