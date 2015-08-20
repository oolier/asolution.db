package asolution.db.model.dataobject;

import java.io.Serializable;

public class t_frm_DataObject implements Serializable {
	private String ObjectName;
	private String ObjectDescription;
	private String RelativeTables;

	public String getObjectName() {
		return ObjectName;
	}

	public String getObjectDescription() {
		return ObjectDescription;
	}

	public String getRelativeTables() {
		return RelativeTables;
	}

	public void setObjectName(String objectName) {
		ObjectName = objectName;
	}

	public void setObjectDescription(String objectDescription) {
		ObjectDescription = objectDescription;
	}

	public void setRelativeTables(String relativeTables) {
		RelativeTables = relativeTables;
	}
}
