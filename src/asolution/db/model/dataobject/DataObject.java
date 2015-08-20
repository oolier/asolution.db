package asolution.db.model.dataobject;

import java.io.Serializable;
import java.util.List;

import asolution.db.dao.impl.DefinedSQL;

public class DataObject extends DefinedSQL implements Serializable{
	private t_frm_DataObject DataObject;
	private List<t_frm_DataObjectParams> DataObjectParams;
	private List<t_frm_DataObjectSQL> DataObjectSQL;
	private List<t_frm_DataObjectFields> DataObjectFields;
	
	public t_frm_DataObject getDataObject() {
		return DataObject;
	}
	public List<t_frm_DataObjectParams> getDataObjectParams() {
		return DataObjectParams;
	}
	public List<t_frm_DataObjectSQL> getDataObjectSQL() {
		return DataObjectSQL;
	}
	public List<t_frm_DataObjectFields> getDataObjectFields() {
		return DataObjectFields;
	}
	public void setDataObject(t_frm_DataObject dataObject) {
		DataObject = dataObject;
	}
	public void setDataObjectParams(List<t_frm_DataObjectParams> dataObjectParams) {
		DataObjectParams = dataObjectParams;
	}
	public void setDataObjectSQL(List<t_frm_DataObjectSQL> dataObjectSQL) {
		DataObjectSQL = dataObjectSQL;
	}
	public void setDataObjectFields(List<t_frm_DataObjectFields> dataObjectFields) {
		DataObjectFields = dataObjectFields;
	}
}
