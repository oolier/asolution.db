package asolution.db.exception;

public class CheckError extends asolution.db.exception.DBError {

	public CheckError(String sqlID,
			asolution.db.dictionary.DBErrorType dBErrorType, int idx,
			String originalMsg, String expandMsg, String paramName,
			String paramName2) {
		super(sqlID, dBErrorType, idx, originalMsg, expandMsg);
		ParamName = paramName;
		ParamName2 = paramName2;
	}

	private String ParamName;
	private String ParamName2;

	public String getParamName() {
		return ParamName;
	}

	public String getParamName2() {
		return ParamName2;
	}

	public void setParamName(String paramName) {
		ParamName = paramName;
	}

	public void setParamName2(String paramName2) {
		ParamName2 = paramName2;
	}
}
