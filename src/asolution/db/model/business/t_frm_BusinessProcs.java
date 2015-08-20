package asolution.db.model.business;

import java.io.Serializable;

public class t_frm_BusinessProcs implements Serializable{
	private String BusinessID;
    private int Idx;
    private String Summary;
    private String SQL;
    private boolean Repeated;
    private int ExpectedRows;
    private String AffectedParam;
    private boolean NeedAuthentication;
    private boolean Enabled;
    private String BusinessProcID;
    private boolean InterActive;
	public String getBusinessID() {
		return BusinessID;
	}
	public int getIdx() {
		return Idx;
	}
	public String getSummary() {
		return Summary;
	}
	public String getSQL() {
		return SQL;
	}
	public boolean isRepeated() {
		return Repeated;
	}
	public int getExpectedRows() {
		return ExpectedRows;
	}
	public String getAffectedParam() {
		return AffectedParam;
	}
	public boolean isNeedAuthentication() {
		return NeedAuthentication;
	}
	public boolean isEnabled() {
		return Enabled;
	}
	public String getBusinessProcID() {
		return BusinessProcID;
	}
	public boolean isInterActive() {
		return InterActive;
	}
	public void setBusinessID(String businessID) {
		BusinessID = businessID;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public void setSQL(String sQL) {
		SQL = sQL;
	}
	public void setRepeated(boolean repeated) {
		Repeated = repeated;
	}
	public void setExpectedRows(int expectedRows) {
		ExpectedRows = expectedRows;
	}
	public void setAffectedParam(String affectedParam) {
		AffectedParam = affectedParam;
	}
	public void setNeedAuthentication(boolean needAuthentication) {
		NeedAuthentication = needAuthentication;
	}
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}
	public void setBusinessProcID(String businessProcID) {
		BusinessProcID = businessProcID;
	}
	public void setInterActive(boolean interActive) {
		InterActive = interActive;
	}
}
