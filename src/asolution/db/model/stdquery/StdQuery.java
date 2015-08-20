package asolution.db.model.stdquery;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import asolution.db.dao.ObjectSqlParam;
import asolution.db.dao.impl.DefinedSQL;
import asolution.db.exception.DBError;

public class StdQuery implements Serializable {
	
	private t_frm_StdQuery StdQuery;
	private List<t_frm_StdQueryParams> StdQueryParams;
	
	private HashMap<String, ArrayList<String>> GroupParams;
	
	public t_frm_StdQuery getStdQuery() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException, DBError {
		return StdQuery;
	}

	public List<t_frm_StdQueryParams> getStdQueryParams() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException {
		return StdQueryParams;
	}

	public void setStdQuery(t_frm_StdQuery stdQuery) {
		StdQuery = stdQuery;
	}

	public void setStdQueryParams(List<t_frm_StdQueryParams> stdQueryParams) {
		StdQueryParams = stdQueryParams;
	}

	

	public HashMap<String, ArrayList<String>> getGroupParams() throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException {
		return GroupParams;
	}

	public void setGroupParams(HashMap<String, ArrayList<String>> groupParams) {
		GroupParams = groupParams;
	}

	
}
