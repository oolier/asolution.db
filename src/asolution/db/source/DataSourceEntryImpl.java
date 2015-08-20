package asolution.db.source;

import org.aopalliance.intercept.Joinpoint;

public class DataSourceEntryImpl {

	private final static ThreadLocal<String> local = new ThreadLocal<String>();

	/*
	 * �Ժ�ʹ�� AOP after ����Ĭ�����ݿ�
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
