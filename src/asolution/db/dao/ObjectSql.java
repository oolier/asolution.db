package asolution.db.dao;

import java.io.Serializable;

public class ObjectSql implements Serializable {
	private int Idx;
	public ObjectSql(int idx, boolean repeated, String sQLText, String sqlID,
			boolean interActive, String affectedParam, boolean enabled,
			int expectedRows) {
		super();
		Idx = idx;
		Repeated = repeated;
		SQLText = sQLText;
		SqlID = sqlID;
		InterActive = interActive;
		AffectedParam = affectedParam;
		Enabled = enabled;
		ExpectedRows = expectedRows;
	}
	private boolean Repeated;
	private String SQLText;
	private String SqlID;
	private boolean InterActive;
	private String AffectedParam;
	private boolean Enabled;
	private int ExpectedRows;
	public int getIdx() {
		return Idx;
	}
	public boolean isRepeated() {
		return Repeated;
	}
	public String getSQLText() {
		return SQLText;
	}
	public String getSqlID() {
		return SqlID;
	}
	public boolean isInterActive() {
		return InterActive;
	}
	public String getAffectedParam() {
		return AffectedParam;
	}
	public boolean isEnabled() {
		return Enabled;
	}
	public int getExpectedRows() {
		return ExpectedRows;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public void setRepeated(boolean repeated) {
		Repeated = repeated;
	}
	public void setSQLText(String sQLText) {
		SQLText = sQLText;
	}
	public void setSqlID(String sqlID) {
		SqlID = sqlID;
	}
	public void setInterActive(boolean interActive) {
		InterActive = interActive;
	}
	public void setAffectedParam(String affectedParam) {
		AffectedParam = affectedParam;
	}
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}
	public void setExpectedRows(int expectedRows) {
		ExpectedRows = expectedRows;
	}
}
