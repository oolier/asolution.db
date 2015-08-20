package asolution.db.model.dataobject;

public class t_frm_DataObjectFields {
	private String ObjectName ;
	private String FieldName ;
	private String DefaultValue ;
	public String getObjectName() {
		return ObjectName;
	}
	public String getFieldName() {
		return FieldName;
	}
	public String getDefaultValue() {
		return DefaultValue;
	}
	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}
	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}
	public void setDefaultValue(String defaultValue) {
		DefaultValue = defaultValue;
	}
}
