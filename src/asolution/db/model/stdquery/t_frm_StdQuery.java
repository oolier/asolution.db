package asolution.db.model.stdquery;

import java.io.Serializable;


public class t_frm_StdQuery implements Serializable {
	

	private String ModelName;
	private String ProcedureName;
	private String ParentModel;
	private String SQL;
	private String SumFields;
	private int FixCount;
	private int AutoExec;
	private String HideFields;
	private String SumSQL;
	private String QueryName;
	private String ChartSetting;
	private String JumpModules;
	private String Context;
	private int Property;
	private String SubQuery;
	private String PowerColumns;
	private String InCharts;
	private String HideFieldsSQL;
	private String PivotQuery;
	private String GroupFields;

	public String getModelName() {
		return ModelName;
	}

	public String getProcedureName() {
		return ProcedureName;
	}

	public String getParentModel() {
		return ParentModel;
	}

	public String getSQL() {
		return SQL;
	}

	public String getSumFields() {
		return SumFields;
	}

	public int getFixCount() {
		return FixCount;
	}

	public int getAutoExec() {
		return AutoExec;
	}

	public String getHideFields() {
		return HideFields;
	}

	public String getSumSQL() {
		return SumSQL;
	}

	public String getQueryName() {
		return QueryName;
	}

	public String getChartSetting() {
		return ChartSetting;
	}

	public String getJumpModules() {
		return JumpModules;
	}

	public String getContext() {
		return Context;
	}

	public int getProperty() {
		return Property;
	}

	public String getSubQuery() {
		return SubQuery;
	}

	public String getPowerColumns() {
		return PowerColumns;
	}

	public String getInCharts() {
		return InCharts;
	}

	public String getHideFieldsSQL() {
		return HideFieldsSQL;
	}

	

	public String getPivotQuery() {
		return PivotQuery;
	}

	public String getGroupFields() {
		return GroupFields;
	}

	public void setModelName(String modelName) {
		ModelName = modelName;
	}

	public void setProcedureName(String procedureName) {
		ProcedureName = procedureName;
	}

	public void setParentModel(String parentModel) {
		ParentModel = parentModel;
	}

	public void setSQL(String sQL) {
		SQL = sQL;
	}

	public void setSumFields(String sumFields) {
		SumFields = sumFields;
	}

	public void setFixCount(int fixCount) {
		FixCount = fixCount;
	}

	public void setAutoExec(int autoExec) {
		AutoExec = autoExec;
	}

	public void setHideFields(String hideFields) {
		HideFields = hideFields;
	}

	public void setSumSQL(String sumSQL) {
		SumSQL = sumSQL;
	}

	public void setQueryName(String queryName) {
		QueryName = queryName;
	}

	public void setChartSetting(String chartSetting) {
		ChartSetting = chartSetting;
	}

	public void setJumpModules(String jumpModules) {
		JumpModules = jumpModules;
	}

	public void setContext(String context) {
		Context = context;
	}

	public void setProperty(int property) {
		Property = property;
	}

	public void setSubQuery(String subQuery) {
		SubQuery = subQuery;
	}

	public void setPowerColumns(String powerColumns) {
		PowerColumns = powerColumns;
	}

	public void setInCharts(String inCharts) {
		InCharts = inCharts;
	}

	public void setHideFieldsSQL(String hideFieldsSQL) {
		HideFieldsSQL = hideFieldsSQL;
	}

	public void setPivotQuery(String pivotQuery) {
		PivotQuery = pivotQuery;
	}

	public void setGroupFields(String groupFields) {
		GroupFields = groupFields;
	}
}
