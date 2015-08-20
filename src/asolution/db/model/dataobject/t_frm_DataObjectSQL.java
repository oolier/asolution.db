package asolution.db.model.dataobject;

import java.io.Serializable;

public class t_frm_DataObjectSQL implements Serializable {
	private String ObjectName ;
	private int Idx ;
	private String SQL ;
    private String Firmware ;
	public String getObjectName() {
		return ObjectName;
	}
	public int getIdx() {
		return Idx;
	}
	public String getSQL() {
		return SQL;
	}
	public String getFirmware() {
		return Firmware;
	}
	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public void setSQL(String sQL) {
		SQL = sQL;
	}
	public void setFirmware(String firmware) {
		Firmware = firmware;
	}
}
