package asolution.db.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import asolution.db.dao.ObjectSql;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.model.business.t_frm_BusinessChecks;
import asolution.db.model.business.t_frm_BusinessProcs;

public class Business implements Serializable {
	public String SqlID;
	public ArrayList<ObjectSqlParam> ObjectSQLParam;
	public ArrayList<ObjectSql> ObjectSQL;
	public List<t_frm_BusinessChecks> businessCheck;
	public List<t_frm_BusinessProcs> businessProc;
}
