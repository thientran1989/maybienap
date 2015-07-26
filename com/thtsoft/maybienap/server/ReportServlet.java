package com.thtsoft.maybienap.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Utils;
import com.tsuyu.jasper.db.Db;

@SuppressWarnings("serial")
public class ReportServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

	}
	int mNGAY;
	int mTHANG;
	int mNAM;
	String ngay;
	String title="";
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Db mDB = new Db();
		String mDVI = request.getParameter("p1");
		String mTT = request.getParameter("p2");
		String TITLE =mTT;
		get_date();
		if (mTT.equals(Utils.TT_DUDIEUKIEN)){
			TITLE = "DANH SÁCH MÁY BIẾN ÁP ĐỦ ĐIỀU KIỆN VẬN HÀNH";
		}else if (mTT.equals(Utils.TT_CHOSUACHUA)){
			TITLE = "DANH SÁCH MÁY BIẾN ÁP ĐANG BẢO DƯỠNG SỮA CHỮA";
		}else if (mTT.equals(Utils.TT_DANGSUACHUA)){
			TITLE = "DANH SÁCH MÁY BIẾN ÁP ĐANG BẢO DƯỠNG SỮA CHỮA";
		}else if (mTT.equals(Utils.TT_CHOTHANHLY)){
			TITLE = "DANH SÁCH MÁY BIẾN ÁP CHỜ THANH LÝ";
		}else if (mTT.equals(Utils.TT_DATHANHLY)){
			TITLE = "DANH SÁCH MÁY BIẾN ÁP ĐÃ THANH LÝ";
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TEN_DVI", "CÔNG TY ĐIỆN LỰC TP CẦN THƠ");
		parameters.put("NGAY",ngay);
		parameters.put("TITLE",TITLE);
		List<Obj_LichSu> list = new ArrayList<Obj_LichSu>();
		try {
			list = get_dsmba(mDVI,mTT);
		} catch (Exception e) {
			
		}
		JasperPrint jp1 = mDB.getReportPDF4("rptDSMBA.jasper", list,
				parameters);
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jp1, output);
			byte[] bytes = output.toByteArray();
			ServletOutputStream stream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength((int) bytes.length);
			stream.write(bytes);
			stream.flush();
			stream.close();
		} catch (Exception ex) {
			try {
				response.getWriter().write("LOI: " + ex.getMessage()+"\nsize"+list.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public 	List<Obj_LichSu> get_dsmba(String donvi,String ttrang) throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list_MBA = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			con = getDBConnection();
			st = con.createStatement();
			String SQL = DB_SQL.get_SQL_DS_MBA_OF_DONVI(donvi, ttrang, "");
				rs = st.executeQuery(SQL);
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
		return list_MBA;
	}
	public void get_date(){
		java.sql.Timestamp ts = Get_Obj.getCurrentTimeStamp();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(ts.getTime()));
		ngay = "";
		try {
			mNGAY = cal.get(Calendar.DAY_OF_MONTH);
			mTHANG = cal.get(Calendar.MONTH)+1;
			mNAM = cal.get(Calendar.YEAR);
			ngay = "Cần Thơ, ngày "+mNGAY+" tháng "+mTHANG+" năm "+mNAM;
		} catch (Exception e) {

		}
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

}