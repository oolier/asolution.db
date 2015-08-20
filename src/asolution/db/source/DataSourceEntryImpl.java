package asolution.db.source;

import org.aopalliance.intercept.Joinpoint;

public class DataSourceEntryImpl {

	private final static ThreadLocal<String> local = new ThreadLocal<String>();

	/*
	 * 以后使用 AOP after 设置默认数据库
	 */
	public void restore(Joinpoint join) {

		local.set(null);
	}

	public void set(String source) {
		// TODO Auto-generated method stub
		local.set(source);
	}

	public String get() {
		// TODO Auto-generated method stub
		return local.get();
	}

	public void clear() {
		// TODO Auto-generated method stub
		local.remove();
	}

}
