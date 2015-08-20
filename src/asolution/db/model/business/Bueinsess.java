package asolution.db.model.business;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import asolution.db.dao.ObjectSqlParam;
import asolution.db.dao.impl.DefinedSQL;
import asolution.db.dictionary.DBErrorType;
import asolution.db.exception.DBError;

public class Bueinsess implements Serializable {

	private t_frm_Business Business;
	private List<t_frm_BusinessChecks> BusinessCheck;
	private List<t_frm_BusinessParams> BusinessParams;
	private List<t_frm_BusinessProcs> BusinessProc;
	public t_frm_Business getBusiness() {

		return Business;
	}
	public List<t_frm_BusinessChecks> getBusinessCheck() {
		return BusinessCheck;
	}
	public List<t_frm_BusinessParams> getBusinessParams() {
		return BusinessParams;
	}
	public List<t_frm_BusinessProcs> getBusinessProc() {
		return BusinessProc;
	}
	public void setBusiness(t_frm_Business business) {
		Business = business;
	}
	public void setBusinessCheck(List<t_frm_BusinessChecks> businessCheck) {
		BusinessCheck = businessCheck;
	}
	public void setBusinessParams(List<t_frm_BusinessParams> businessParams) {
		BusinessParams = businessParams;
	}
	public void setBusinessProc(List<t_frm_BusinessProcs> businessProc) {
		BusinessProc = businessProc;
	}
}
