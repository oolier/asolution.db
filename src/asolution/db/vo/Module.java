package asolution.db.vo;

import java.util.List;

public class Module {
	private List<String> StdQueryList;
	private List<String> BusinessList;
	private List<String> DataObjectList;
	private List<String> SmartLookupList;
	public List<String> getStdQueryList() {
		return StdQueryList;
	}
	public List<String> getBusinessList() {
		return BusinessList;
	}
	public List<String> getDataObjectList() {
		return DataObjectList;
	}
	public List<String> getSmartLookupList() {
		return SmartLookupList;
	}
	public void setStdQueryList(List<String> stdQueryList) {
		StdQueryList = stdQueryList;
	}
	public void setBusinessList(List<String> businessList) {
		BusinessList = businessList;
	}
	public void setDataObjectList(List<String> dataObjectList) {
		DataObjectList = dataObjectList;
	}
	public void setSmartLookupList(List<String> smartLookupList) {
		SmartLookupList = smartLookupList;
	}
}
