package asolution.db.model.business;

import java.io.Serializable;

public class t_frm_BusinessParams implements Serializable {
	
	private String BusinessID;
	private int Idx;
	private String ParamName;
	private boolean Putout;
	private String ParamType;
	private boolean Repeated;
	public String getBusinessID() {
		return BusinessID;
	}
	public int getIdx() {
		return Idx;
	}
	public String getParamName() {
		return ParamName;
	}
	public boolean isPutout() {
		return Putout;
	}
	public String getParamType() {
		return ParamType;
	}
	public boolean isRepeated() {
		return Repeated;
	}
	public void setBusinessID(String businessID) {
		BusinessID = businessID;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public void setParamName(String paramName) {
		ParamName = paramName;
	}
	public void setPutout(boolean putout) {
		Putout = putout;
	}
	public void setParamType(String paramType) {
		ParamType = paramType;
	}
	public void setRepeated(boolean repeated) {
		Repeated = repeated;
	}
}
