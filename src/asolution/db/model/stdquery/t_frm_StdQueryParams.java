package asolution.db.model.stdquery;

import java.util.Dictionary;

public class t_frm_StdQueryParams {

	private String QueryName ;
    private int ParamID ;
    private String ParamName ;
    private String ParamType ;
    private String DisplayLabel ;
    private String DefaultValue ;
    private String SQL ;
    private String PickList ;
    private String Groups ;
    private String BlockTo ;
    private String Validator ;
    private int ParamWidth ;
    private String PlaceHolder ;
    private String VisibleSQL ;

    /// <summary>
    /// 保存下拉，包括SQL和数据列表
    /// </summary>
    private Dictionary<String, String> List ;

	public String getQueryName() {
		return QueryName;
	}

	public int getParamID() {
		return ParamID;
	}

	public String getParamName() {
		return ParamName;
	}

	public String getParamType() {
		return ParamType;
	}

	public String getDisplayLabel() {
		return DisplayLabel;
	}

	public String getDefaultValue() {
		return DefaultValue;
	}

	public String getSQL() {
		return SQL;
	}

	public String getPickList() {
		return PickList;
	}

	public String getGroups() {
		return Groups;
	}

	public String getBlockTo() {
		return BlockTo;
	}

	public String getValidator() {
		return Validator;
	}

	public int getParamWidth() {
		return ParamWidth;
	}

	public String getPlaceHolder() {
		return PlaceHolder;
	}

	public String getVisibleSQL() {
		return VisibleSQL;
	}

	public Dictionary<String, String> getList() {
		return List;
	}

	public void setQueryName(String queryName) {
		QueryName = queryName;
	}

	public void setParamID(int paramID) {
		ParamID = paramID;
	}

	public void setParamName(String paramName) {
		ParamName = paramName;
	}

	public void setParamType(String paramType) {
		ParamType = paramType;
	}

	public void setDisplayLabel(String displayLabel) {
		DisplayLabel = displayLabel;
	}

	public void setDefaultValue(String defaultValue) {
		DefaultValue = defaultValue;
	}

	public void setSQL(String sQL) {
		SQL = sQL;
	}

	public void setPickList(String pickList) {
		PickList = pickList;
	}

	public void setGroups(String groups) {
		Groups = groups;
	}

	public void setBlockTo(String blockTo) {
		BlockTo = blockTo;
	}

	public void setValidator(String validator) {
		Validator = validator;
	}

	public void setParamWidth(int paramWidth) {
		ParamWidth = paramWidth;
	}

	public void setPlaceHolder(String placeHolder) {
		PlaceHolder = placeHolder;
	}

	public void setVisibleSQL(String visibleSQL) {
		VisibleSQL = visibleSQL;
	}

	public void setList(Dictionary<String, String> list) {
		List = list;
	}
}
