package asolution.db.model.dataobject;

public class t_frm_DataObjectParams {
	 private String ObjectName;
	 private int Idx;
	 private String ParamName;
	 private String ParamType;
	 private Boolean Repeated;
	public String getObjectName() {
		return ObjectName;
	}
	public int getIdx() {
		return Idx;
	}
	public String getParamName() {
		return ParamName;
	}
	public String getParamType() {
		return ParamType;
	}
	public Boolean getRepeated() {
		return Repeated;
	}
	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public void setParamName(String paramName) {
		ParamName = paramName;
	}
	public void setParamType(String paramType) {
		ParamType = paramType;
	}
	public void setRepeated(Boolean repeated) {
		Repeated = repeated;
	}
}
