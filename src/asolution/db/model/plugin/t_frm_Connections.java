package asolution.db.model.plugin;

import java.io.Serializable;

public class t_frm_Connections implements Serializable {
	
	private String DBObjectName;
	private String DBID;
	private int DBObjectType;
	private String BSConnStr;

	public String getDBObjectName() {
		return DBObjectName;
	}

	public String getDBID() {
		return DBID;
	}

	public int getDBObjectType() {
		return DBObjectType;
	}

	public String getBSConnStr() {
		return BSConnStr;
	}

	public void setDBObjectName(String dBObjectName) {
		DBObjectName = dBObjectName;
	}

	public void setDBID(String dBID) {
		DBID = dBID;
	}

	public void setDBObjectType(int dBObjectType) {
		DBObjectType = dBObjectType;
	}

	public void setBSConnStr(String bSConnStr) {
		BSConnStr = bSConnStr;
	}
}
