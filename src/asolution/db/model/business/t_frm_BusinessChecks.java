package asolution.db.model.business;

import java.io.Serializable;

public class t_frm_BusinessChecks implements Serializable {
	private String BusinessID;
	private int Idx;
	private String Summary;
	private String ValidateType;
	private String CompareType;
	private String MaximumValue;
	private String MinimumValue;
	private String ParamToValidate;
	private String ParamToCompare;
	private String SQL;
	private boolean Enabled;
	private String Summary_900;
	private String ParamToRepeat;
	private int CheckType;
	private String CheckName;

	public String getBusinessID() {
		return BusinessID;
	}

	public int getIdx() {
		return Idx;
	}

	public String getSummary() {
		return Summary;
	}

	public String getValidateType() {
		return ValidateType;
	}

	public String getCompareType() {
		return CompareType;
	}

	public String getMaximumValue() {
		return MaximumValue;
	}

	public String getMinimumValue() {
		return MinimumValue;
	}

	public String getParamToValidate() {
		return ParamToValidate;
	}

	public String getParamToCompare() {
		return ParamToCompare;
	}

	public String getSQL() {
		return SQL;
	}

	public boolean isEnabled() {
		return Enabled;
	}

	public String getSummary_900() {
		return Summary_900;
	}

	public String getParamToRepeat() {
		return ParamToRepeat;
	}

	public int getCheckType() {
		return CheckType;
	}

	public String getCheckName() {
		return CheckName;
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

	public void setValidateType(String validateType) {
		ValidateType = validateType;
	}

	public void setCompareType(String compareType) {
		CompareType = compareType;
	}

	public void setMaximumValue(String maximumValue) {
		MaximumValue = maximumValue;
	}

	public void setMinimumValue(String minimumValue) {
		MinimumValue = minimumValue;
	}

	public void setParamToValidate(String paramToValidate) {
		ParamToValidate = paramToValidate;
	}

	public void setParamToCompare(String paramToCompare) {
		ParamToCompare = paramToCompare;
	}

	public void setSQL(String sQL) {
		SQL = sQL;
	}

	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}

	public void setSummary_900(String summary_900) {
		Summary_900 = summary_900;
	}

	public void setParamToRepeat(String paramToRepeat) {
		ParamToRepeat = paramToRepeat;
	}

	public void setCheckType(int checkType) {
		CheckType = checkType;
	}

	public void setCheckName(String checkName) {
		CheckName = checkName;
	}
}
