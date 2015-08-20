package asolution.db.model.plugin;

import java.io.Serializable;

public class t_dtl_Modules  implements Serializable {
	private String ModuleID;
	private String Business;
	private String DataObjects;
	private String StdQueryes;
	private String Dailys;
	private String SmartLookups;
	public String getModuleID() {
		return ModuleID;
	}
	public String getBusiness() {
		return Business;
	}
	public String getDataObjects() {
		return DataObjects;
	}
	public String getStdQueryes() {
		return StdQueryes;
	}
	public String getDailys() {
		return Dailys;
	}
	public String getSmartLookups() {
		return SmartLookups;
	}
	public void setModuleID(String moduleID) {
		ModuleID = moduleID;
	}
	public void setBusiness(String business) {
		Business = business;
	}
	public void setDataObjects(String dataObjects) {
		DataObjects = dataObjects;
	}
	public void setStdQueryes(String stdQueryes) {
		StdQueryes = stdQueryes;
	}
	public void setDailys(String dailys) {
		Dailys = dailys;
	}
	public void setSmartLookups(String smartLookups) {
		SmartLookups = smartLookups;
	}
}
