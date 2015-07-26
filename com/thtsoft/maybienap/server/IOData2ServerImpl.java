package com.thtsoft.maybienap.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thtsoft.maybienap.client.IOData2Server;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class IOData2ServerImpl extends RemoteServiceServlet implements
		IOData2Server {
	public List<Obj_LichSu> getMBA() {
		return null;
	}

	public List<Obj_QuyetDinh> getBB() throws IllegalArgumentException {
		return null;
	}

	public List<Obj_donvi> getDVI() throws IllegalArgumentException {
		return null;
	}

	public List<Obj_User> getUSER() throws IllegalArgumentException {
		return null;
	}

	public CallbackResult getMBA2(int lenh) {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list_MBA = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
			if (lenh == Utils.LENH_GET_DSMBA) {
				rs = st.executeQuery("Select * from "
						+ Obj_LichSu.TAG_table_lichsu);
				if (rs != null) {
					list_MBA = new ArrayList<Obj_LichSu>();
					while (rs.next()) {
						Obj_LichSu mBA = Get_Obj.set_result_HTR(rs);
						list_MBA.add(mBA);
					}
					oCB.setResultObj(list_MBA);

				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public CallbackResult tao_lichsu(Obj_User mUS, Obj_LichSu mMBA)
			throws IllegalArgumentException {
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString("Thêm không thành công !");
		mMBA.setUser_suamba(mUS.getUsername_mba());
		try {
			mCB = InsertLICHSU(mMBA);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
		}
		return mCB;
	}

	public CallbackResult InsertLICHSU(Obj_LichSu mLS) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String insertTableSQL = DB_SQL.get_sql_insert_LS();
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			Statement st = dbConnection.createStatement();
			ResultSet rs = st.executeQuery(DB_SQL.get_SQL_SEQ_LICHSU());
			int ID = -1;
			if (rs != null) {
				if (rs.next()) {
					ID = rs.getInt("id");
				}
			}
			mLS.setID(ID);
			DB_SQL.set_preparedStatement_LS(preparedStatement, mLS);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			dbConnection.rollback();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return mCB;

	}

	public CallbackResult Insert_USER(Obj_User mLS) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String insertTableSQL = DB_SQL.get_sql_insert_USER();
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			DB_SQL.set_preparedStatement_USER(preparedStatement, mLS);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			dbConnection.rollback();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return mCB;

	}

	public CallbackResult UpdateLICHSU(Obj_LichSu mLS) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String updateSQL = DB_SQL.get_sql_update_MBA(mLS);
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			DB_SQL.set_preparedStatement_updateLS(preparedStatement, mLS);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			dbConnection.rollback();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return mCB;

	}

	// tao quyet dinh
	public String InsertQUYETDINH(Obj_QuyetDinh mLS) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String kq = "LOI";
		String insertTableSQL = DB_SQL.get_sql_insert_QD();
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			dbConnection.setAutoCommit(false);
			DB_SQL.set_preparedStatement_QD(preparedStatement, mLS);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			kq = Utils.CB_OK;
		} catch (SQLException e) {
			kq = e.toString();
			dbConnection.rollback();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return kq;

	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@"
					+ DB_CONFIG.IP + ":1521:" + DB_CONFIG.DATA_NAME,
					DB_CONFIG.DATA_USER, DB_CONFIG.DATA_PASS);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;

	}

	public CallbackResult getDVI2(int lenh) throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_donvi> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
			if (lenh == Utils.LENH_GET_DSDVI) {
				rs = st.executeQuery("Select * from "
						+ Obj_donvi.TAG_table_donvi + " order by "
						+ Obj_donvi.TAG_ma_donvi + " asc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_donvi>();
					while (rs.next()) {
						Obj_donvi mBA = Get_Obj.set_result_DONVI(rs);
						list_DVI.add(mBA);
					}
					oCB.setResultObj(list_DVI);

				}
			}

			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public Obj_User login(Obj_User mUS) throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		Obj_User kq = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_User.TAG_table_user
					+ " where " + Obj_User.TAG_username_mba + " ='"
					+ mUS.getUsername_mba() + "' and " + Obj_User.TAG_password
					+ " ='" + mUS.getPassword() + "'");
			if (rs != null) {
				while (rs.next()) {
					kq = Get_Obj.set_result_USER(rs);
				}
			} else {
				kq = null;
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return kq;
	}

	public Obj_LichSu get_MBA(String so_may, String DVI)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		Obj_LichSu kq = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_LichSu.TAG_table_lichsu
					+ " where " + Obj_LichSu.TAG_so_may + " ='" + so_may
					+ "' and " + Obj_LichSu.TAG_kho + " ='" + DVI + "'");
			if (rs != null) {
				while (rs.next()) {
					kq = Get_Obj.set_result_HTR(rs);
				}
			} else {
				kq = null;
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return kq;
	}

	// update may bien ap
	public CallbackResult update_MBA(Obj_LichSu mLS) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String updateSQL = DB_SQL.get_sql_update_MBA(mLS);
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			DB_SQL.set_preparedStatement_updateLS(preparedStatement, mLS);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return mCB;

	}

	public String check_tontai_MBA(String mBA) throws IllegalArgumentException {
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		String kq = Utils.CB_OK;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_LichSu.TAG_table_lichsu
					+ " where " + Obj_LichSu.TAG_so_may + " ='" + mBA + "'");
			if (rs != null) {
				while (rs.next()) {
					kq = Utils.CB_TONTAI;
				}
			} else {
				kq = null;
			}
		} catch (SQLException e) {
			kq = "loi : " + e.toString();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {

			}

		}
		return kq;
	}

	public CallbackResult check_tontai_QD(String QD)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from "
					+ Obj_QuyetDinh.TAG_table_QuyetDinh + " where "
					+ Obj_QuyetDinh.TAG_QD_so + " ='" + QD + "'");
			if (rs != null) {
				while (rs.next()) {
					mCB.setResultString(Utils.CB_TONTAI);
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return mCB;
	}

	public CallbackResult tao_quyetdinh(Obj_User mUS, Obj_QuyetDinh mQD)
			throws IllegalArgumentException {
		String kq = "Thêm không thành công !";
		CallbackResult mCB = new CallbackResult();
		try {
			kq = InsertQUYETDINH(mQD);
		} catch (SQLException e) {
			kq = e.toString();
		}
		mCB.setResultString(kq);
		return mCB;
	}

	public CallbackResult xoa_lichsu(Obj_User mUS, Obj_LichSu oLS)
			throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String deleteTableSQL = "delete from CSKH."
				+ Obj_LichSu.TAG_table_lichsu;
		deleteTableSQL = deleteTableSQL + " where " + Obj_LichSu.TAG_ID + "="
				+ oLS.getID();
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteTableSQL);
			dbConnection.setAutoCommit(false);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {

			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (Exception e2) {

			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {

			}
		}
		return mCB;
	}

	public CallbackResult getBB_SEARCH(String sobb, String DVI)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_QuyetDinh> list_QD = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from "
					+ Obj_QuyetDinh.TAG_table_QuyetDinh + " where "
					+ Obj_QuyetDinh.TAG_QD_so + " like'%" + sobb + "%' order by "+Obj_QuyetDinh.TAG_thoi_gian_tao+" desc");

			if (rs != null) {
				list_QD = new ArrayList<Obj_QuyetDinh>();
				while (rs.next()) {
					Obj_QuyetDinh mQD = Get_Obj.set_result_QD(rs);
					list_QD.add(mQD);
				}
				oCB.setResultObj(list_QD);
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public CallbackResult update_DD_MBA(Obj_LichSu oMBA)
			throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String updateTableSQL = "update CSKH." + Obj_LichSu.TAG_table_lichsu;
		updateTableSQL = updateTableSQL + " set " + Obj_LichSu.TAG_kho + " = '"
				+ oMBA.getKho() + "'," + Obj_LichSu.TAG_tram + " = '"
				+ oMBA.getTram() + "'," + Obj_LichSu.TAG_tinhtrang_sudung
				+ " = '" + oMBA.getTinhtrang_sudung() + "'";
		updateTableSQL = updateTableSQL + " where " + Obj_LichSu.TAG_so_may
				+ "='" + oMBA.getSo_may() + "'";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(updateTableSQL);
			dbConnection.setAutoCommit(false);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {

			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (Exception e2) {

			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {

			}

		}
		return mCB;
	}

	public List<Obj_LichSu> getLS() throws IllegalArgumentException {
		return null;
	}

	public CallbackResult get_LICHSU_OF_QD(String QD)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list_MBA = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_LichSu.TAG_table_lichsu
					+ " where " + Obj_LichSu.TAG_QD_so + " ='" + QD
					+ "' order by " + Obj_LichSu.TAG_loai_history + " asc");
			if (rs != null) {
				list_MBA = new ArrayList<Obj_LichSu>();
				while (rs.next()) {
					Obj_LichSu mBA = Get_Obj.set_result_HTR(rs);
					list_MBA.add(mBA);
				}
				oCB.setResultObj(list_MBA);

			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public CallbackResult xoa_QD(Obj_User mUS, Obj_QuyetDinh oQD)
			throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String deleteTableSQL = "delete from CSKH."
				+ Obj_QuyetDinh.TAG_table_QuyetDinh;
		deleteTableSQL = deleteTableSQL + " where " + Obj_QuyetDinh.TAG_QD_so
				+ "='" + oQD.getQD_so() + "'";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteTableSQL);
			dbConnection.setAutoCommit(false);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {

			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (Exception e2) {

			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {

			}
		}
		return mCB;
	}

	public CallbackResult check_tontai_LS_OF_QD(String QD)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_LichSu.TAG_table_lichsu
					+ " where " + Obj_LichSu.TAG_QD_so + " ='" + QD + "'");
			if (rs != null) {
				while (rs.next()) {
					mCB.setResultString(Utils.CB_TONTAI);
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return mCB;
	}

	public CallbackResult get_DSMBA_LOC(String donvi, String tinhtrang,
			String key) throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list_MBA = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_DS_MBA_OF_DONVI(donvi,
					tinhtrang, key));
			if (rs != null) {
				list_MBA = new ArrayList<Obj_LichSu>();
				while (rs.next()) {
					Obj_LichSu mBA = Get_Obj.set_result_HTR(rs);
					list_MBA.add(mBA);
				}
				oCB.setResultObj(list_MBA);
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public CallbackResult get_LICHSU_OF_MBA(Obj_LichSu mMBA)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list_MBA = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_GET_LS_OF_MBA(mMBA));
			if (rs != null) {
				list_MBA = new ArrayList<Obj_LichSu>();
				while (rs.next()) {
					Obj_LichSu mBA = Get_Obj.set_result_HTR(rs);
					list_MBA.add(mBA);
				}
				oCB.setResultObj(list_MBA);
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public CallbackResult hoantat_quyetdinh(Obj_User mUS, Obj_QuyetDinh oQD,
			List<Obj_LichSu> list_LS) throws IllegalArgumentException {
		CallbackResult mCB = new CallbackResult();
		try {
			mCB = dieu_dong(oQD, list_LS);
		} catch (Exception e) {
			mCB.setResultString("Không xác định lỗi !\n" + e.toString());
		}
		return mCB;
	}

	public CallbackResult dieu_dong(Obj_QuyetDinh oQD, List<Obj_LichSu> list_LS)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement_QD = null;
		PreparedStatement preparedStatement_LS = null;
		String insertTableSQL_QD = DB_SQL.get_sql_insert_QD();
		String insertTableSQL_LS = DB_SQL.get_sql_insert_LS();
		CallbackResult mCB = new CallbackResult();
		int ID = -1;
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			// Quyet dinh
			if (oQD != null) {
				preparedStatement_QD = dbConnection
						.prepareStatement(insertTableSQL_QD);
				DB_SQL.set_preparedStatement_QD(preparedStatement_QD, oQD);
				preparedStatement_QD.addBatch();
				preparedStatement_QD.executeBatch();
			}
			// lich su
			if (list_LS != null) {
				preparedStatement_LS = dbConnection
						.prepareStatement(insertTableSQL_LS);
				for (Obj_LichSu mLS : list_LS) {
					Statement st = dbConnection.createStatement();
					ResultSet rs = st.executeQuery(DB_SQL.get_SQL_SEQ_LICHSU());
					if (rs != null) {
						if (rs.next()) {
							ID = rs.getInt("id");
						}
					}
					mLS.setID(ID);
					DB_SQL.set_preparedStatement_LS(preparedStatement_LS, mLS);
					preparedStatement_LS.addBatch();
				}
				preparedStatement_LS.executeBatch();
			}

			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);

		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			dbConnection.rollback();

		} finally {
			if (preparedStatement_QD != null) {
				preparedStatement_QD.close();
			}
			if (preparedStatement_LS != null) {
				preparedStatement_LS.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return mCB;
	}

	public Obj_LichSu get_last_MBA(String SM) {
		Obj_LichSu oLS = null;
		Connection con;
		ResultSet rs = null;
		Statement st;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from MBA_LICHSU MBA where SO_MAY ='"
					+ SM
					+ "' "
					+ "and ID = (select max(ID) from MBA_LICHSU where SO_MAY =MBA.SO_MAY)");
			if (rs != null) {
				if (rs.next()) {
					oLS = Get_Obj.set_result_HTR(rs);
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oLS;
	}

	public CallbackResult huy_quyetdinh(Obj_User mUS, Obj_QuyetDinh mQD,
			List<Obj_LichSu> mL_LS) throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement_QD = null;
		PreparedStatement preparedStatement_LS = null;
		CallbackResult mCB = new CallbackResult();
		ResultSet rs = null;
		Statement st = null;
		boolean not_delete = false;
		String so_may_EX = "";
		String QD_EX = "";
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);

			// lich su
			if (mL_LS != null) {
				for (Obj_LichSu mLS : mL_LS) {
					String SOQD = "";
					st = dbConnection.createStatement();
					rs = st.executeQuery(DB_SQL.get_SQL_GET_LAST_MBA(mLS));
					if (rs != null) {
						if (rs.next()) {
							SOQD = rs.getString(Obj_LichSu.TAG_QD_so);
						}
					}
					if (!SOQD.equals(mQD.getQD_so())) {
						so_may_EX = mLS.getSo_may();
						QD_EX = SOQD;
						not_delete = true;
						break;
					}
				}
				if (not_delete == false) {
					// co the xoa QD LS
					if (mQD != null) {
						String delete_QD_SQL = "delete from CSKH."
								+ Obj_QuyetDinh.TAG_table_QuyetDinh;
						delete_QD_SQL = delete_QD_SQL + " where "
								+ Obj_QuyetDinh.TAG_QD_so + "='"
								+ mQD.getQD_so() + "'";
						preparedStatement_QD = dbConnection
								.prepareStatement(delete_QD_SQL);
						preparedStatement_QD.addBatch();
						preparedStatement_QD.executeBatch();
					}
					// lich su
					if (mL_LS != null) {
						for (Obj_LichSu mLS : mL_LS) {
							String delete_LS_SQL = "delete from CSKH."
									+ Obj_LichSu.TAG_table_lichsu;
							delete_LS_SQL = delete_LS_SQL + " where "
									+ Obj_LichSu.TAG_ID + "=" + mLS.getID();
							preparedStatement_LS = dbConnection
									.prepareStatement(delete_LS_SQL);
							preparedStatement_LS.addBatch();
						}
						preparedStatement_LS.executeBatch();
					}
					dbConnection.commit();
					mCB.setResultString(Utils.CB_OK);
				} else {
					mCB.setResultString("Không thể xoá vì " + so_may_EX
							+ " đang nằm trong quyết định " + QD_EX
							+ " mới hơn !");
				}
			}
		} catch (SQLException e) {
			try {
				dbConnection.rollback();
				mCB.setResultString(e.toString());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			if (preparedStatement_QD != null) {
				try {
					preparedStatement_QD.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement_LS != null) {
				try {
					preparedStatement_LS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return mCB;
	}

	public CallbackResult get_DS_USER(Obj_User donvi, List<Obj_donvi> lDONVI)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_User> list_USER = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_GET_DS_USER(donvi, lDONVI));
			if (rs != null) {
				list_USER = new ArrayList<Obj_User>();
				while (rs.next()) {
					Obj_User oUSER = Get_Obj.set_result_USER(rs);
					list_USER.add(oUSER);
				}
				oCB.setResultObj(list_USER);
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}

	public CallbackResult tao_user(Obj_User mUS)
			throws IllegalArgumentException {
		String kq = "Thêm không thành công !";
		CallbackResult mCB = new CallbackResult();
		try {
			mCB = Insert_USER(mUS);
		} catch (SQLException e) {
			kq = e.toString();
			mCB.setResultString(kq);
		}
		return mCB;
	}

	public CallbackResult get_QD_CONFIG(String LOAI_QD)
			throws IllegalArgumentException {
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		CallbackResult oCB = new CallbackResult();
		Obj_Config oCONFIG = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_GET_CONFIG());
			if (rs != null) {
				while (rs.next()) {
					oCONFIG = Get_Obj.set_result_Config(rs);
				}
				oCB.setoCONFIG(oCONFIG);
			}
		} catch (Exception e) {
			oCB.setResultString("Lỗi : " + e.toString());
		} finally {
			try {
				if(st!=null){
					st.close();
				}
				if(rs!=null){
					rs.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				oCB.setResultString("Lỗi : " + e.toString());
			}
		}
		return oCB;
	}

	public CallbackResult update_CONFIG(Obj_Config oCONFIG)
			throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String updateSQL = DB_SQL.get_sql_update_CONFIG(oCONFIG);
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			DB_SQL.set_preparedStatement_update_CONFIG(preparedStatement, oCONFIG);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return mCB;
	}

	public CallbackResult update_USER(Obj_User oUSER)
			throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String updateSQL = DB_SQL.get_sql_update_USER(oUSER);
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			preparedStatement = dbConnection.prepareStatement(updateSQL);
			DB_SQL.set_preparedStatement_update_USER(preparedStatement, oUSER);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {
			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return mCB;
	}
	public boolean TONTAI_MAYBIENAP(Obj_LichSu mLS){
		boolean kq =false;
		Connection con;
		ResultSet rs = null;
		Statement st;
		int count =0;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_COUNT_MAYBIENAP(mLS));
				while (rs.next()) {
					count = rs.getInt("SL");
				}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		if(count>0){
			kq = true;
		}
		return kq;
	}

	public CallbackResult check_tontai_USER(Obj_User oUSER)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		int count =0;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_COUNT_USER(oUSER));
				while (rs.next()) {
					count = rs.getInt("SL");
				}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		if(count>0){
			mCB.setResultString(Utils.CB_TONTAI);
		}
		return mCB;
	}

	public CallbackResult xoa_user(Obj_User mUS)
			throws IllegalArgumentException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		CallbackResult mCB = new CallbackResult();
		String deleteTableSQL = DB_SQL.get_SQL_DELETE_USER(mUS);
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteTableSQL);
			dbConnection.setAutoCommit(false);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			mCB.setResultString(Utils.CB_OK);
		} catch (SQLException e) {

			mCB.setResultString(e.toString());
			try {
				dbConnection.rollback();
			} catch (Exception e2) {

			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {

			}
		}
		return mCB;
	}

	public CallbackResult check_tontai_LS_OF_USER(Obj_User oUSER)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		int count =0;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery(DB_SQL.get_SQL_COUNT_LS_OF_USER(oUSER));
				while (rs.next()) {
					count = rs.getInt("SL");
				}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		if(count>0){
			mCB.setResultString(Utils.CB_TONTAI);
		}
		return mCB;
	}

}
