package asolution.db.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.exception.DBError;
import asolution.db.model.plugin.PagingParseSQL;
import asolution.db.model.plugin.SortParseSQL;
import asolution.db.model.stdquery.StdQuery;
import asolution.db.service.StdQueryService;

public class StdQueryServiceImpl implements StdQueryService {
	asolution.db.dao.impl.StdQueryDaoImpl stdQueryDaoImpl;
	private asolution.db.model.stdquery.StdQuery stdQuery;
	@Override
	public StdQuery queryById(Serializable id) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {
		stdQuery =  stdQueryDaoImpl.queryById(id);
		return stdQuery;
	}

	public void SetParam(String ParamName, Object ParamValue) throws DBError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		stdQueryDaoImpl.SetParam(ParamName, ParamValue);
	}

	@Override
	public List<Map<String, Object>> ExecuteSQL( int start,
			int max, String sort) throws SQLException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, DBError {

		stdQueryDaoImpl.getMutilyConnection().getConnection(
				stdQuery.getStdQuery().getQueryName(),
				ConnectionDBObjectType.StdQuery);

		String sumSQL = stdQuery.getStdQuery().getSumSQL();
		String sql = stdQuery.getStdQuery().getSQL();

		if (sort != null && sort.trim().length() != 0
				&& sql.indexOf(SortParseSQL.StartRegex) != -1) {
			boolean s = sort.indexOf("-") == -1;// -ColumnName µ¹Ðò

			String newSortSql = new SortParseSQL(sql).MSSQL05(
					s ? sort : sort.substring(1), !s);
			stdQuery.getStdQuery().setSQL(newSortSql);
		}

		if ((sql.indexOf(PagingParseSQL.StartRegex) != -1) && max != 0) {
			String newSQL = new PagingParseSQL(sql, start, max).MSSQL05("");

			return stdQueryDaoImpl.queryForList(newSQL,
					stdQueryDaoImpl.getObjectSQLParam());
		}
		return stdQueryDaoImpl.queryForList(stdQuery.getStdQuery().getSQL(),
				stdQueryDaoImpl.getObjectSQLParam());
	}

	@Override
	public List<Map<String, Object>> ExecuteSumSQL()
			throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException, SQLException, DBError {

		String sumSQL = stdQuery.getStdQuery().getSumSQL();
		String sql = stdQuery.getStdQuery().getSQL();
		stdQueryDaoImpl.getMutilyConnection().getConnection(
				stdQuery.getStdQuery().getQueryName(),
				ConnectionDBObjectType.StdQuery);

		if (sumSQL == null || sumSQL.trim().length() == 0) {
			if (sql.indexOf(PagingParseSQL.StartRegex) != -1) {
				String newSQL = new PagingParseSQL(sql, 0, 0)
						.MSSQL05RowsCount();
				return stdQueryDaoImpl.queryForList(newSQL,
						stdQueryDaoImpl.getObjectSQLParam());

			}
		}
		return stdQueryDaoImpl.queryForList(sumSQL,
				stdQueryDaoImpl.getObjectSQLParam());
	}
	public void setStdQueryDaoImpl(
			asolution.db.dao.impl.StdQueryDaoImpl stdQueryDaoImpl) {
		this.stdQueryDaoImpl = stdQueryDaoImpl;
	}

}