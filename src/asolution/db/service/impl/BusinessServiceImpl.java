package asolution.db.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import asolution.db.dao.ObjectSql;
import asolution.db.dao.ObjectSqlParam;
import asolution.db.dictionary.ConnectionDBObjectType;
import asolution.db.dictionary.DBErrorType;
import asolution.db.exception.CheckError;
import asolution.db.exception.DBError;
import asolution.db.model.business.Bueinsess;
import asolution.db.model.business.t_frm_BusinessChecks;
import asolution.db.service.BusinessService;

public class BusinessServiceImpl implements BusinessService {

	private asolution.db.dao.impl.BusinessDaoImpl businessDaoImpl;
	private asolution.db.model.business.Bueinsess business;
	@Override
	public asolution.db.model.business.Bueinsess queryById(Serializable id)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {
		business = businessDaoImpl.queryById(id);
		return businessDaoImpl.queryById(id);
	}

	@Override
	public Object GetParam(String ParamName)
			throws InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		for (asolution.db.dao.ObjectSqlParam osp : businessDaoImpl
				.getObjectSQLParam()) {
			if (osp.getParamName().equals(ParamName)) {
				return osp.getParamValue();
			}
		}
		return null;

	}

	@Override
	public void SetParam(String ParamName, Object ParamValue) throws DBError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException {
		businessDaoImpl.SetParam(ParamName, ParamValue);

	}
	@Override
	public void AddParam(String ParamName, Object ParamValue)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, DBError,
			SQLException {

		ObjectSqlParam par = businessDaoImpl.FindParamByName(ParamName);
		if (par != null) {
			if (!par.getRepeated()) {
				DBErrorType et = DBErrorType.Business;
				throw new DBError(businessDaoImpl.getSqlID(), et, 0,
						et.toString() + "��" + businessDaoImpl.getSqlID()
								+ "��AddParam��" + ParamName + "���ⲻ��һ���ظ�����",
						"������ʼ���쳣");
			}
			if (par.getParamValue() == null) {
				par.setParamValue(new ArrayList());
			}
			ArrayList listData = (ArrayList) par.getParamValue();
			listData.add(ParamValue);
			return;
		}

		DBErrorType et = DBErrorType.Business;
		throw new DBError(businessDaoImpl.getSqlID(), et, 0, et.toString()
				+ "��" + businessDaoImpl.getSqlID() + "��AddParam��" + ParamName
				+ "������δ����", "���ؿ�ܿ�ʧ��");
	}
	@Override
	public boolean ExecuteSQL() throws CheckError, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {
		if (!Check(business))
			return false;

		businessDaoImpl.getMutilyConnection().getConnection(
				businessDaoImpl.getSqlID(), ConnectionDBObjectType.Business);

		TransactionTemplate transactionTemplate = new TransactionTemplate(
				businessDaoImpl.getTransactionManager());
		final JdbcTemplate jdbcTemplate = businessDaoImpl.getJdbcTemplate();
		Object asdf = transactionTemplate
				.execute(new TransactionCallbackWithoutResult() {

					@Override
					protected void doInTransactionWithoutResult(
							TransactionStatus arg0) {
						int rows = 0;
						// TODO Auto-generated method stub
						for (ObjectSql s : businessDaoImpl.getObjectSQL()) {

							if (!s.isEnabled())
								continue;
							if (s.getSQLText().trim().length() == 0)
								continue;
							ArrayList<ObjectSqlParam> filterParams = null;
							try {
								filterParams = businessDaoImpl
										.FilterParamsToObjectSqlParamList(s
												.getSQLText(), businessDaoImpl
												.getObjectSQLParam());
							} catch (InstantiationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IllegalAccessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (NoSuchMethodException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SecurityException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IllegalArgumentException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InvocationTargetException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							// var filterParams =
							// dbHelper.FilterParamsForSqlTextByObjectSQLParam(s.SQLText,
							// ObjectSQLParam);
							// ���ظ�
							if (!s.isRepeated()) {
								// �����ҳ�SQL�е����в���
								ArrayList<ObjectSqlParam> noRepeatedParam = new ArrayList<ObjectSqlParam>();
								for (int i = 0; i < filterParams.size(); i++) {
									if (filterParams.get(i).getRepeated()) {
										noRepeatedParam.add(filterParams.get(i));
									}
								}

								if (noRepeatedParam.size() != 0) {
									try {
										throw new DBError(businessDaoImpl
												.getSqlID(),
												DBErrorType.BusinessProc, s
														.getIdx(),
												"������ʼ���쳣������ϵ����Ա��",
												"���ظ������а����ظ����������ڹ����й�ѡ�ظ�ѡ����ڲ���������ȡ���ظ�ѡ��������£�\r\n" // +
																									// errorParam.ToJson()
										);
									} catch (NoSuchMethodException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SecurityException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InstantiationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (DBError e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
								// asolution.log.SqlMonitor.Write(s.SQLText,
								// this.GetType().Name, SqlID + "_" + s.Idx,
								// filterParams);
								// �ǽ���ģʽ
								// Param.Where(p => fields.Where(f =>
								// f.IndexOf(":" +
								// p.ParamName) != -1).Any());
								ArrayList<ObjectSqlParam> noSqlParam = new ArrayList<ObjectSqlParam>();
								try {
									for (ObjectSqlParam p : businessDaoImpl
											.getObjectSQLParam()) {
										for (ObjectSqlParam p1 : filterParams) {
											if (!p1.getParamName().equals(
													p.getParamName())
													&& !p.getRepeated()
													&& p.getOutPut()) {

											}
										}
									}
								} catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								filterParams.addAll(noSqlParam);

								try {
									rows += ExecuteSQL(s, filterParams);
								} catch (NoSuchMethodException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SecurityException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (InstantiationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IllegalAccessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IllegalArgumentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (InvocationTargetException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (DBError e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								for (ObjectSqlParam op : filterParams) {
									if (op.getOutPut()) {
										try {
											for (ObjectSqlParam p1 : businessDaoImpl
													.getObjectSQLParam()) {
												if (p1.getParamName().equals(
														op.getParamName())) {
													p1.setParamValue(op
															.getParamValue());
												}
											}
										} catch (InstantiationException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (NoSuchMethodException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (SecurityException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (IllegalArgumentException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										} catch (SQLException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}

								}
								continue;
							}// ���ظ�����
								// �ҳ��ظ����������ڼ��㳤��
								// ArrayList<ObjectSqlParam> repeatedParam
								// =FilterParamsToObjectSqlParamList(s.getSQLText(),
								// business.getObjectSQLParam());
							int repeatedParam_length = RepeatedParamLength(filterParams);
							// var repeatedParam = filterParams.Where(p =>
							// p.Repeated).ToList();
							if (repeatedParam_length == -1) {
								try {
									throw new DBError(businessDaoImpl
											.getSqlID(),
											DBErrorType.BusinessProc, s
													.getIdx(),
											"������ʼ���쳣������ϵ����Ա��",
											"�ظ������У�û���ҵ�������\r\n");
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DBError e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

							// ִ��SQL�Ǹ����ظ�������������Ϊ����
							for (int i = 0; i < repeatedParam_length; i++) {
								ArrayList<ObjectSqlParam> curSqlParam = null;
								try {
									curSqlParam = FilterRepeatedParam(business,
											s.getSQLText(), i);
								} catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DBError e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								try {
									rows += ExecuteSQL(s, curSqlParam);
								} catch (NoSuchMethodException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SecurityException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InstantiationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DBError e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								//
								// var outPuts = curSqlParam.Where(p =>
								// p.OutPut);
								// if (outPuts.Any())
								// {
								// foreach (var p in outPuts)
								// {
								// var objectParam = ObjectSQLParam.Where(pp =>
								// pp.ParamName == p.ParamName);
								// if (objectParam.Any())
								// {
								// var paramValue =
								// objectParam.First().ParamValue;
								// if (paramValue is ArrayList)
								// (paramValue as ArrayList)[i] = p.ParamValue;
								// else paramValue = p.ParamValue;
								// }
								// }
								// }
							}
						}

					}
				});

		return false;

	}

	int RepeatedParamLength(ArrayList<ObjectSqlParam> filterParams) {
		int maxSize = -1;
		for (ObjectSqlParam p : filterParams) {
			if (p.getRepeated()) {
				int curParSize = ((ArrayList) p.getParamValue()).size();
				if (curParSize > maxSize) {
					maxSize = curParSize;
				}
			}
		}
		return maxSize;
	}

	int ExecuteSQL(ObjectSql s, ArrayList<ObjectSqlParam> filterParams)
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, DBError,
			SQLException {
		int row = 0;
		if (!s.isInterActive()) {
			row = businessDaoImpl.Execute(s.getSQLText(), filterParams);

			if (s.getExpectedRows() != 0 && row != s.getExpectedRows()) {
				throw new DBError(businessDaoImpl.getSqlID(),
						DBErrorType.BusinessProc, s.getIdx(), "ִ�й����쳣������ϵ����Ա��",
						"����������" + row + "������Ԥ��ֵ��" + s.getExpectedRows());

			}
		} else {

			List<Map<String, Object>> data = businessDaoImpl.queryForList(
					s.getSQLText(), filterParams);

			row = data.size();

			if (data.size() != 1) {
				throw new DBError(businessDaoImpl.getSqlID(),
						DBErrorType.BusinessProc, s.getIdx(), "ִ�й����쳣������ϵ����Ա��",
						"�����������˽���ģʽ��������������Ϊ1�С�");
			}

			for (ObjectSqlParam p : businessDaoImpl.getObjectSQLParam()) {
				for (Entry<String, Object> k : data.get(0).entrySet()) {
					if (p.getOutPut()
							&& k.getKey().equalsIgnoreCase(p.getParamName())) {
						p.setParamValue(k.getValue());
					}
				}
			}
		}
		return row;
	}

	private boolean Check(Bueinsess business) throws CheckError,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SQLException, DBError

	{

		if (business.getBusinessCheck() == null
				|| business.getBusinessCheck().size() == 0)
			return true;
		for (t_frm_BusinessChecks b : business.getBusinessCheck()) {

			if (!b.isEnabled())
				continue;

			ArrayList arrParamValue = new ArrayList();
			ObjectSqlParam param = businessDaoImpl.FindParamByName(b
					.getParamToValidate());
			if (param == null) {
				if (b.getValidateType().toUpperCase().equals("REQUIRED")) {
					throw new CheckError(
							business.getBusiness().getBusinessID(),
							DBErrorType.BusinessCheck, 0, b.getSummary(),
							"ҵ�������ͣ�" + b.getValidateType() + "\r\n"
									+ b.getParamToValidate() + "��"
									+ b.getSummary(), b.getParamToValidate(),
							"");
				}
				throw new DBError(b.getBusinessID(), DBErrorType.BusinessCheck,
						0, "������ʼ���쳣������ϵ����Ա��", "ҵ�������ͣ�" + b.getValidateType()
								+ "\r\n" + b.getParamToValidate() + "�����������ڡ�");

			}
			if (param.getRepeated()) {
				if (param.getParamValue() == null) {
					arrParamValue.add(null);
				} else {

					if (!(param.getParamValue() instanceof ArrayList)) {
						throw new CheckError(b.getBusinessID(),
								DBErrorType.BusinessCheck, 0, "������ʼ���쳣",
								"ҵ�������ͣ�" + b.getValidateType() + "\r\n�ظ�������"
										+ param.getParamName()
										+ "������ת��ʧ�ܣ�����Ӧʹ�� AddParam ��������ֵ��",
								param.getParamName(), "");

					}
					arrParamValue = (ArrayList) param.getParamValue();
				}
			} else {
				arrParamValue.add(param.getParamValue());
			}

			if (b.getValidateType().toUpperCase().equals("REQUIRED")) {
				for (int i = 0; i < arrParamValue.size(); i++) {
					if (arrParamValue.get(i) == null
							|| arrParamValue.get(i).toString().isEmpty()) {

						throw new CheckError(b.getBusinessID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + "��"
										+ b.getSummary(), param.getParamName(),
								"");

					}
				}
			}

			else if (b.getValidateType().toUpperCase().equals("QUERY")) {
				for (int i = 0; i < arrParamValue.size(); i++) {
					ArrayList<ObjectSqlParam> listQueryParam = FilterRepeatedParam(
							business, b.getSQL(), i);
					int row = 0;

					businessDaoImpl.getMutilyConnection().getConnection(
							businessDaoImpl.getSqlID(),
							ConnectionDBObjectType.Business);
					row = businessDaoImpl.queryForList(b.getSQL(),
							listQueryParam).size();

					if (row == 0) {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessParam, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + "��"
										+ b.getSummary(), param.getParamName(),
								"");

					}
				}

			}

			else if (b.getValidateType().toUpperCase().equals("SQL")) {
				for (int i = 0; i < arrParamValue.size(); i++) {
					ArrayList<ObjectSqlParam> listQueryParam = FilterRepeatedParam(
							business, b.getSQL(), i);
					businessDaoImpl.getMutilyConnection().getConnection(
							businessDaoImpl.getSqlID(),
							ConnectionDBObjectType.Business);
					List<Map<String, Object>> sqlValidate = businessDaoImpl
							.queryForList(b.getSQL(), listQueryParam);

					if (sqlValidate.size() == 0) {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + "����������=0",
								param.getParamName(), "");

					} else if (!sqlValidate.get(0).containsKey("ResultCode")) {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + b.getSummary()
										+ "\r\n��������ΪSQL����û����  ResultCode �ֶ�",
								param.getParamName(), "");
					} else if (sqlValidate.get(0).get("ResultCode").toString() != "0") {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + ","
										+ b.getSummary(), param.getParamName(),
								"");

					}
				}
			}

			else if (b.getValidateType().toUpperCase().equals("COMPARETO")) {
				ObjectSqlParam param2 = businessDaoImpl.FindParamByName(b
						.getParamToCompare());
				if (param2 == null) {
					throw new CheckError(businessDaoImpl.getSqlID(),
							DBErrorType.BusinessCheck, 0, "������ʼ���쳣", "ҵ�������ͣ�"
									+ b.getValidateType() + "\r\n"
									+ b.getParamToCompare() + "�����������ڡ�",
							param.getParamName(), b.getParamToCompare());

				}

				if (param.getRepeated() != param2.getRepeated()) {
					throw new CheckError(businessDaoImpl.getSqlID(),
							DBErrorType.BusinessCheck, 0, "������ʼ���쳣", "ҵ�������ͣ�"
									+ b.getValidateType() + "\r\n"
									+ b.getParamToCompare() + "����������������ͬ���͡�",
							param.getParamName(), b.getParamToCompare());

				}
				ArrayList arrParamValue2 = new ArrayList();
				if (param2.getRepeated())
					arrParamValue2 = (ArrayList) param2.getParamValue();
				else
					arrParamValue2.add(param2.getParamValue());

				if (arrParamValue.size() != arrParamValue2.size()) {
					throw new CheckError(businessDaoImpl.getSqlID(),
							DBErrorType.BusinessCheck, 0, "������ʼ���쳣", "ҵ�������ͣ�"
									+ b.getValidateType() + "\r\n"
									+ param.getParamName() + "��ֵ�ĸ�����"
									+ arrParamValue.size() + "����"
									+ param2.getParamName() + "��ֵ�ĸ�����"
									+ arrParamValue2.size() + "����һ�¡�\r\n",
							param.getParamName(), param2.getParamName());

				}

				String dct = b.getCompareType().toLowerCase().trim();
				for (int i = 0; i < arrParamValue.size(); i++) {
					if (dct == "equal"
							&& !param.getParamValue().equals(
									param2.getParamValue())) {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + "��"
										+ b.getSummary(), param.getParamName(),
								param2.getParamName());
					}

					else if (dct == "notequal"
							&& param.getParamValue().equals(
									param2.getParamValue())) {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + "��"
										+ b.getSummary(), param.getParamName(),
								param2.getParamName());
					}

					double dp1 = Double.valueOf(param.getParamValue()
							.toString());
					double dp2 = Double.valueOf(param2.getParamValue()
							.toString());

					if (dct == "lessthanorequal" && dp1 >= dp2
							|| dct == "lessthan" && dp1 > dp2
							|| dct == "morethan" && dp1 < dp2
							|| dct == "morethanorequal" && dp1 <= dp2) {
						throw new CheckError(businessDaoImpl.getSqlID(),
								DBErrorType.BusinessCheck, param.getRepeated()
										? i
										: -1, b.getSummary(), "ҵ�������ͣ�"
										+ b.getValidateType() + "\r\n"
										+ param.getParamName() + "��"
										+ b.getSummary(), param.getParamName(),
								param2.getParamName());
					}
				}
			}

		}
		return true;
	}

	private ArrayList<ObjectSqlParam> FilterRepeatedParam(Bueinsess business,
			String strSQL, int idx) throws InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException, SQLException,
			DBError {

		ArrayList<ObjectSqlParam> pars = businessDaoImpl
				.FilterParamsToObjectSqlParamList(strSQL,
						businessDaoImpl.getObjectSQLParam());

		ArrayList<ObjectSqlParam> noSqlParam = new ArrayList<ObjectSqlParam>();

		for (ObjectSqlParam p1 : businessDaoImpl.getObjectSQLParam()) {
			for (ObjectSqlParam p : pars) {
				if (!p1.getParamName().equals(p.getParamName())
						&& p1.getOutPut()) {
					noSqlParam.add(p1);
					break;
				}
			}
		}
		pars.addAll(noSqlParam);
		ArrayList<ObjectSqlParam> par = new ArrayList<ObjectSqlParam>();
		for (ObjectSqlParam p : pars) {
			try {
				if (p.getRepeated() && p.getParamValue() != null
						&& !(p.getParamValue() instanceof ArrayList)) {

					throw new DBError(businessDaoImpl.getSqlID(),
							DBErrorType.BusinessParam, idx, "������ô�������ϵ����Ա��",
							"������" + p.getParamName() + "���ܲ���һ���ظ�������������������á�");

				}
				par.add(new ObjectSqlParam(
						p.getParamName(),
						p.getParamType(),
						p.getRepeated()
								? (p.getParamValue() == null
										? null
										: (((ArrayList) p.getParamValue())
												.get(idx))) : p.getParamValue(),
						0, p.getRepeated(), p.getOutPut()));

			} catch (Exception ex) {
				throw new DBError(businessDaoImpl.getSqlID(),
						DBErrorType.BusinessParam, idx, ex.getMessage(),
						"��ִ����ţ�" + idx + " �����г���\r\n�ظ�������" + p.getParamName()
								+ "��" + p.getParamValue());

			}
		}
		return par;
	}

	public void setBusinessDaoImpl(
			asolution.db.dao.impl.BusinessDaoImpl businessDaoImpl) {
		this.businessDaoImpl = businessDaoImpl;
	}

}
