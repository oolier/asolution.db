package asolution.db.model.plugin;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SortParseSQL
{
    public static final String EndRegex = "--ENDSORT";
    public static final String StartRegex = "--STARTSORT";
    private String sortSql = "";

    public SortParseSQL(String strSQL)
    {
        sortSql = strSQL;
    }

    public String MSSQL05(String columnName, boolean desc)
    {
    	ArrayList<String> sortSqls = GetItems(sortSql, StartRegex, EndRegex);
        String newSql = sortSql;

        for (int i = 0; i < sortSqls.size(); i++)
        {
            newSql = newSql.replace(sortSqls.get(i), "\r\norder by " + columnName + (desc ? " desc " : "") + "\r\n");
        }
        return newSql;
    }


    public ArrayList<String> GetItems(String text, String start, String end) {
		Pattern p = Pattern.compile("(" + start + "(.+?)" + end + ")",
				Pattern.DOTALL);
		Matcher m = p.matcher(text);
		String[] strs=new String[m.groupCount()];
		ArrayList<String> matchs=new ArrayList<String>();
		while (m.find()) {
			String s0 = m.group();
			matchs.add(s0);
		}
		return matchs;
	}
}
