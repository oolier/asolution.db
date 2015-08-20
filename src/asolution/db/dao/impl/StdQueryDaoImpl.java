package asolution.db.dao.impl;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import asolution.db.dao.ObjectSqlParam;
import asolution.db.dao.StdQueryDAO;
import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.dictionary.DBErrorType;
import asolution.db.exception.DBError;
import asolution.db.model.stdquery.*;
import asolution.db.source.AbstractBaseDao;
import asolution.db.source.DataSourceEntryImpl;
import asolution.db.source.DynamicDataSource;

public class StdQueryDaoImpl extends DefinedSQL<StdQuery>
		implements
			StdQueryDAO {
	
	@Override
	public StdQuery queryById(final Serializable id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {

		ArrayList<ObjectSqlParam> lisPars = new ArrayList<ObjectSqlParam>();
		lisPars.add(new ObjectSqlParam("SqlID", "String", id, 0, false, false));

		this.getDataSourceEntry().set("frmDataSource");

		// this.getDynamicDataSource()
		StdQuery std = new StdQuery() {
			
			public String getSqlID() throws NoSuchMethodException,
					SecurityException, InstantiationException,
					IllegalAccessException, IllegalArgumentException,
					InvocationTargetException, SQLException, DBError {
				// TODO Auto-generated method stub
				return getStdQuery().getQueryName();
			}

			@Override
			public HashMap<String, ArrayList<String>> getGroupParams()
					throws InstantiationException, IllegalAccessException,
					NoSuchMethodException, SecurityException,
					IllegalArgumentException, InvocationTargetException,
					SQLException {
				return super.getGroupParams();
			}
		};
		String sql = "select ModelName,ProcedureName,ParentModel,SQL,SumFields,isnull(FixCount,0),isnull(AutoExec,0),HideFields,SumSQL,QueryName,ChartSetting,JumpModules,Context,Property,SubQuery,PowerColumns,InCharts,HideFieldsSQL,PivotQuery,GroupFields from t_frm_StdQuery where QueryName = :SqlID";

		std.setStdQuery(this.queryForObject(sql, lisPars, t_frm_StdQuery.class));

		if (std.getStdQuery() == null) {
			throw new asolution.db.exception.DBError(id, DBErrorType.StdQuery,
					0, "对象未定义", "加载框架库失败");
		}
		sql = "select QueryName,ParamID,ParamName,ParamType,DisplayLabel,DefaultValue,SQL,PickList,Groups,BlockTo,Validator,ParamWidth,PlaceHolder,VisibleSQL from t_frm_StdQueryParams where QueryName = :SqlID";
		std.setStdQueryParams(queryForListObject(sql, lisPars,
				asolution.db.model.stdquery.t_frm_StdQueryParams.class));

		ArrayList<ObjectSqlParam> objectSQLParam = new ArrayList<ObjectSqlParam>();
		HashMap<String, ArrayList<String>> gps = new HashMap<String, ArrayList<String>>();
		for (t_frm_StdQueryParams p : std.getStdQueryParams()) {

			objectSQLParam.add(new ObjectSqlParam(p.getParamName(), p
					.getParamType(), null, p.getParamID(), false, false));

			if (p.getParamID() < 0)
				continue;
			String[] glabs = p.getGroups().split(";");
			for (String g : glabs) {
				String labTxt = g;
				if (g.trim().length() == 0) {
					labTxt = "查询条件";
				}
				ArrayList<String> listParam;
				if (!gps.containsKey(labTxt)) {
					listParam = new ArrayList<String>();
					gps.put(labTxt, listParam);
				} else {
					listParam = gps.get(labTxt);
				}
				listParam.add(p.getParamName());
			}
		}
		this.setObjectSQLParam(objectSQLParam);
		std.setGroupParams(gps);

		return std;
	}

}
