package asolution.db.dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import asolution.db.dao.BusinessDAO;
import asolution.db.dao.DataObjectDAO;
import asolution.db.dao.ObjectSql;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.dictionary.DBErrorType;
import asolution.db.exception.CheckError;
import asolution.db.exception.DBError;
import asolution.db.model.business.Bueinsess;
import asolution.db.model.business.t_frm_Business;
import asolution.db.model.business.t_frm_BusinessChecks;
import asolution.db.model.business.t_frm_BusinessParams;
import asolution.db.model.business.t_frm_BusinessProcs;
import asolution.db.model.dataobject.DataObject;

public class BusinessDaoImpl extends DefinedSQL<Bueinsess> implements BusinessDAO {

	private DataSourceTransactionManager transactionManager;

	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public DataSourceTransactionManager getTransactionManager() {
		return this.transactionManager;
	}

	@Override
	public Bueinsess queryById(Serializable id) throws NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {

		ArrayList<ObjectSqlParam> lisPars = new ArrayList<ObjectSqlParam>();
		lisPars.add(new ObjectSqlParam("SqlID", "String", id, 0, false, false));

		this.getDataSourceEntry().set("frmDataSource");
		Bueinsess buz = new Bueinsess();
		String sql = "select BusinessID,BusinessDesc,NeedTranscation,BusinessName,RelativeTables,Property from t_frm_Business where BusinessID = :SqlID";
		buz.setBusiness(queryForObject(sql, lisPars, t_frm_Business.class));

		sql = "select BusinessID,Idx,Summary,ValidateType,CompareType,MaximumValue,MinimumValue,ParamToValidate,ParamToCompare,SQL,Enabled,Summary_900,ParamToRepeat,CheckType,CheckName from t_frm_BusinessChecks where BusinessID = :SqlID";
		buz.setBusinessCheck(queryForListObject(sql, lisPars,
				t_frm_BusinessChecks.class));

		sql = "select BusinessID,Idx,ParamName,Putout,ParamType,Repeated from t_frm_BusinessParams where BusinessID = :SqlID";
		buz.setBusinessParams(queryForListObject(sql, lisPars,
				t_frm_BusinessParams.class));

		sql = "select BusinessID,Idx,Summary,SQL,Repeated,ExpectedRows,InterActive,AffectedParam,NeedAuthentication,Enabled,BusinessProcID from t_frm_BusinessProcs where BusinessID = :SqlID";
		buz.setBusinessProc(queryForListObject(sql, lisPars,
				t_frm_BusinessProcs.class));

		ArrayList<ObjectSqlParam> objectSQLParam = new ArrayList<ObjectSqlParam>();

		ArrayList<ObjectSql> objectSQL = new ArrayList<ObjectSql>();
		if (buz.getBusinessParams() != null)
			for (t_frm_BusinessParams p : buz.getBusinessParams()) {
				objectSQLParam.add(new ObjectSqlParam(p.getParamName(), p
						.getParamType(), null, p.getIdx(), p.isRepeated(), p
						.isPutout()));

			}

		for (t_frm_BusinessProcs p : buz.getBusinessProc()) {
			objectSQL.add(new ObjectSql(p.getIdx(), p.isRepeated(), p.getSQL(),
					p.getBusinessID(), p.isInterActive(), p.getAffectedParam(),
					p.isEnabled(), p.getExpectedRows()));
		}
		this.setObjectSQLParam(objectSQLParam);
		this.setObjectSQL(objectSQL);
		// TODO Auto-generated method stub
		return buz;
	}

}
