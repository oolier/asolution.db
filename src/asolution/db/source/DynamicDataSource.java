package asolution.db.source;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 获取数据源(依赖SPRING框架)
 * 
 * @author shadow
 * @create 2013.04.03
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private DataSourceEntryImpl dataSourceEntry;

	@Override
	protected Object determineCurrentLookupKey() {
		return this.dataSourceEntry.get();
	}

	@Resource
	public void setDataSourceEntry(DataSourceEntryImpl dataSourceEntry) {
		this.dataSourceEntry = dataSourceEntry;
	}

	

}
