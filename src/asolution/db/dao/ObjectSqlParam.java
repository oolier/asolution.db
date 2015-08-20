package asolution.db.dao;

public class ObjectSqlParam {

	public ObjectSqlParam(String paramName, String paramType, Object paramValue,
			int idx, Boolean repeated, Boolean outPut) {
		super();
		this.paramName = paramName;
		this.paramType = paramType;
		this.paramValue = paramValue;
		this.idx = idx;
		this.repeated = repeated;
		this.outPut = outPut;
	}

	private String paramName;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String paramType;

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public Object paramValue;
	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}

	public int idx;
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Boolean repeated;
	public Boolean getRepeated() {
		return repeated;
	}

	public void setRepeated(Boolean repeated) {
		this.repeated = repeated;
	}

	public Boolean outPut;

	public Boolean getOutPut() {
		return outPut;
	}

	public void setOutPut(Boolean outPut) {
		this.outPut = outPut;
	}

	
}
