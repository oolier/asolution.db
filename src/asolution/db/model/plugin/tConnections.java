package asolution.db.model.plugin;

import java.io.Serializable;

public class tConnections implements Serializable {

   

	private String ConnName ;

	private String Description ;

	private String BSDBName ;

	public String getConnName() {
		return ConnName;
	}

	public String getDescription() {
		return Description;
	}

	public String getBSDBName() {
		return BSDBName;
	}

	public void setConnName(String connName) {
		ConnName = connName;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public void setBSDBName(String bSDBName) {
		BSDBName = bSDBName;
	}
}
