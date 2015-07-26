package com.thtsoft.maybienap.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.thtsoft.maybienap.shared.Obj_LichSu;
public class Get_List {
	
	public 	List<Obj_LichSu> get_dsmba(String TAG_SQL) throws IllegalArgumentException {
		final String IP = "10.179.0.23";
		final String DATA_NAME = "CSKH";
		final String DATA_USER = "cskh";
		final String DATA_PASS = "cskh";
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@" + IP
					+ ":1521:" + DATA_NAME, DATA_USER, DATA_PASS);
			st = con.createStatement();
			rs = st.executeQuery(TAG_SQL);
			if (rs != null) {
				list = new ArrayList<Obj_LichSu>();
				while (rs.next()) {
					Obj_LichSu mBA = Get_Obj.set_result_HTR(rs);
					list.add(mBA);
				}

			} else {
				list = null;
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return list;
	}


}
