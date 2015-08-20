package asolution.db.model.plugin;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PagingParseSQL {

	public static final String EndRegex = "--ENDPAGING";
	public static final String StartRegex = "--STARTPAGING";
	public String COLUMNS = "--COLUMNS";
	String strSQL;
	int pageIndex;
	int pageRowCount;

	public PagingParseSQL(String strSQL, int pageIndex, int pageRowCount) {
		this.strSQL = strSQL;

		this.pageRowCount = pageIndex + pageRowCount;
		this.pageIndex = ++pageIndex;
	}

	// / <summary>
	// / 指定文本，指定头尾，取内容
	// / </summary>
	// / <param name="text">匹配文本</param>
	// / <param name="start">段落头</param>
	// / <param name="end">段落尾</param>
	// / <returns>匹配到的内容</returns>
	public ArrayList<String> GetItems(String text, String start, String end) {
		Pattern p = Pattern.compile("(" + start + "(.+?)" + end + ")",
				Pattern.DOTALL);
		Matcher m = p.matcher(text);
		ArrayList<String> matchs=new ArrayList<String>();
		while (m.find()) {
			String s0 = m.group();
			matchs.add(s0);
		}
		return matchs;
	}

	public String MSSQL05(String sort) {
		String pagingSql = strSQL;
		ArrayList<String> pagingSqls = GetItems(pagingSql, StartRegex, EndRegex);
		for (int i = 0; i < pagingSqls.size(); i++) {
			String oldSql = pagingSqls.get(i);
			String newSql = pagingSqls.get(i);

			String strBefore = newSql.substring(0, newSql.toUpperCase()
					.indexOf(COLUMNS));
			String strFromAfter = newSql.substring(newSql.toUpperCase()
					.lastIndexOf(COLUMNS));
			String orderySql = ParseOrderCols(strFromAfter);
			boolean hasDistinct = false;
			int distindex = strBefore.toLowerCase().indexOf("distinct");
			if (distindex != -1) {
				hasDistinct = true;
			}
			if (!hasDistinct && orderySql != "") {
				newSql = newSql.replace(orderySql, "\r\n--ENDPAGING");
			} else {
				orderySql = "ORDER BY (SELECT 0)";
			}

			if (ParseGroupCols(newSql) != "") {
				newSql = newSql
						.replace(
								newSql,
								"\r\nSELECT * FROM (SELECT ROW_NUMBER() OVER (\r\n"
										+ orderySql
										+ "\r\n) AS [ROW_NUMBER],* FROM ("
										+ newSql.replace(orderySql, "")
										+ "\r\n) AS [tempPaging_1]) AS [tempPaging_2] WHERE [tempPaging_2].[ROW_NUMBER] BETWEEN "
										+ pageIndex + " AND "
										+ pageRowCount + " \r\n");
			} else {
				String column = GetItems(newSql, "--COLUMNS", "--COLUMNS").get(0);
				String content = newSql;
				if (hasDistinct) {
					newSql = newSql
							.replace(
									newSql,
									"\r\n select * from (select *,ROW_NUMBER() OVER (\r\n"
											+ orderySql
											+ "\r\n) AS [ROW_NUMBER]  from ( \r\n"
											+ newSql.substring(0,
													distindex + 8)
											+ " top 100000000 "
											+ newSql.substring(distindex + 8)
											+ "\r\n) AS [tempPaging_1])as [tempPaging_2] WHERE [ROW_NUMBER] BETWEEN "
											+ pageIndex + " AND "
											+ pageRowCount + "\r\n ");
				} else {

					// newSql = newSql.Replace(newSql,
					// "\r\nSELECT * FROM (SELECT ROW_NUMBER() OVER(\r\n" +
					// orderySql + "\r\n) AS [ROW_NUMBER],* FROM ( "
					// + newSql.Substring(pagingSqls[i].IndexOf("select",
					// StringComparison.OrdinalIgnoreCase) + 6) +
					// "\r\n) AS [tempPaging_1]) AS [tempPaging_2] WHERE [tempPaging_2].[ROW_NUMBER] BETWEEN "
					// + pageIndex + " AND " + pageRowCount + " \r\n");

					newSql = newSql
							.replace(
									newSql,
									"\r\nSELECT * FROM (SELECT ROW_NUMBER() OVER(\r\n"
											+ orderySql
											+ "\r\n) AS [ROW_NUMBER],* FROM ( "
											+ newSql.substring(pagingSqls.get(i)
													.toLowerCase().indexOf(
															"select"))
											+ "\r\n) AS [tempPaging_1]) AS [tempPaging_2] WHERE [tempPaging_2].[ROW_NUMBER] BETWEEN "
											+ pageIndex + " AND "
											+ pageRowCount + " \r\n");
				}
			}
			pagingSql = pagingSql.replace(oldSql, newSql);
		}
		return pagingSql;
	}

	public String MSSQL05RowsCount() {
		String pagingSql = strSQL;
		ArrayList<String> pagingSqls = GetItems(pagingSql, StartRegex, EndRegex);
		for (int i = 0; i < pagingSqls.size(); i++) {
			String strBefore = pagingSqls.get(i).substring(0,
					pagingSqls.get(i).indexOf(COLUMNS));
			int distindex = strBefore.indexOf("distinct");
			String strFromAfter = pagingSqls.get(i).substring(pagingSqls.get(i)
					.toUpperCase().lastIndexOf("FROM"));
			String orderySql = ParseOrderCols(strFromAfter);
			String newSQL = "";
			if (orderySql.trim().length() != 0) {
				if (orderySql.indexOf(EndRegex) != -1) {
					newSQL = pagingSqls.get(i).replace(orderySql, EndRegex
							+ "\r\n");
				} else {
					newSQL = pagingSqls.get(i).replace(orderySql, "");
				}
			} else
				newSQL = pagingSqls.get(i);

			if (ParseGroupCols(newSQL) != "") {
				pagingSql = pagingSql.replace(pagingSqls.get(i),
						"\r\nSELECT RowsCount = count(1) FROM (" + newSQL
								+ " \r\n) AS [tempPaging_1] ");
				continue;
			}
			if (distindex != -1) {
				pagingSql = pagingSql.replace(pagingSqls.get(i),
						" \r\n SELECT RowsCount = COUNT(1) FROM (\r\n"
								+ newSQL + "\r\n) AS [tempPaging_1]");
				continue;
			}
			String column = GetItems(newSQL, COLUMNS, COLUMNS).get(0);
			newSQL = newSQL.replace(column,
					"\r\n RowsCount = count(1) \r\n");
			pagingSql = pagingSql.replace(pagingSqls.get(i), "\r\n " + newSQL
					+ " \r\n");

		}
		return pagingSql;
	}

	private boolean isContains(String sql, String regex) {
		return Pattern.matches(regex, sql);
	}

	private String MatchString(String sql, String regex) {
		Pattern p = Pattern.compile(regex,Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
		Matcher match = p.matcher(sql);
	
		if (match.find()) {
			return match.group();
		}
		return "";
	}

	private String ParseCols(String sql) {
		return this.MatchString(sql, "(SELECT)([\\s\\S]*)(FROM)");
	}

	private String ParseConditions(String sql) {
		String regex = "";
		if (!this.isContains(sql, "\\s+WHERE\\s+")) {
			return "";
		}
		if (this.isContains(sql, "GROUP\\s+BY")) {
			regex = "(WHERE)([\\s\\S]*)(GROUP\\s+BY)";
		} else if (this.isContains(sql, "order\\s+by")) {
			regex = "(WHERE)([\\s\\S]*)(ORDER\\s+BY)";
		} else {
			regex = "(WHERE)([\\s\\S]*)($)";
		}
		return this.MatchString(sql, regex).replace("(GROUP\\s+BY)", "")
				.replace("(ORDER\\s+BY)", "");
	}

	private String ParseGroupCols(String sql) {
		String regex = "";
		if (!this.isContains(sql, "GROUP\\s+BY")) {
			return "";
		}
		if (this.isContains(sql, "ORDER\\s+BY")) {
			regex = "(GROUP\\s+BY)([\\s\\S]*)(ORDER\\s+BY)";
		} else {
			regex = "(GROUP\\s+BY)([\\s\\S]*)($)";
		}
		return this.MatchString(sql, regex).replace("(ORDER\\s+BY)", "");
	}

	private String ParseOrderCols(String sql) {
		String regex = "(ORDER\\s+BY)([\\s\\S]*)($)";
		return this.MatchString(sql, regex);
	}
}
