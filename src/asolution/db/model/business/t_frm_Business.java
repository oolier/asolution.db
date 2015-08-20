package asolution.db.model.business;

import java.io.Serializable;

public class t_frm_Business implements Serializable {
	private String BusinessID;
	private String BusinessDesc;
	private boolean NeedTranscation;
	private String BusinessName;
	private String RelativeTables;
	private int Property;

	public String getBusinessID() {
		return BusinessID;
	}

	public String getBusinessDesc() {
		return BusinessDesc;
	}

	public boolean isNeedTranscation() {
		return NeedTranscation;
	}

	public String getBusinessName() {
		return BusinessName;
	}

	public String getRelativeTables() {
		return RelativeTables;
	}

	public int getProperty() {
		return Property;
	}

	public void setBusinessID(String businessID) {
		BusinessID = businessID;
	}

	public void setBusinessDesc(String businessDesc) {
		BusinessDesc = businessDesc;
	}



	public void setNeedTranscation(boolean needTranscation) {
		NeedTranscation = needTranscation;
	}



	public void setBusinessName(String businessName) {
		BusinessName = businessName;
	}



	public void setRelativeTables(String relativeTables) {
		RelativeTables = relativeTables;
	}



	public void setProperty(int property) {
		Property = property;
	}

}
