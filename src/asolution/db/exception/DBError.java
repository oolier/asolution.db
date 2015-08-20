package asolution.db.exception;

import java.io.Serializable;

import asolution.db.dictionary.DBErrorType;

public class DBError extends Exception{

    public DBError(Serializable id,
			asolution.db.dictionary.DBErrorType dBErrorType, int idx,
			String originalMsg, String expandMsg) {
		super();
		SqlID = (String) id;
		DBErrorType = dBErrorType;
		Idx = idx;
		OriginalMsg = originalMsg;
		ExpandMsg = expandMsg;
	}
	private String SqlID ;
    private DBErrorType DBErrorType ;
    //private String Summary ;
    //private String ParamName ;
    //private String ParamName2 ;
    private int Idx ;

    private String OriginalMsg ;
    private String ExpandMsg ;
	public String getSqlID() {
		return SqlID;
	}
	public DBErrorType getDBErrorType() {
		return DBErrorType;
	}
	public int getIdx() {
		return Idx;
	}
	public String getOriginalMsg() {
		return OriginalMsg;
	}
	public String getExpandMsg() {
		return ExpandMsg;
	}
	public void setSqlID(String sqlID) {
		SqlID = sqlID;
	}
	public void setDBErrorType(DBErrorType dBErrorType) {
		DBErrorType = dBErrorType;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public void setOriginalMsg(String originalMsg) {
		OriginalMsg = originalMsg;
	}
	public void setExpandMsg(String expandMsg) {
		ExpandMsg = expandMsg;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return getExpandMsg();
	}
}
