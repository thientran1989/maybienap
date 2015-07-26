package com.thtsoft.maybienap.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Utils;
import com.tsuyu.jasper.db.Db;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@SuppressWarnings("serial")
public class Report_HistotyServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Db mDB = new Db();
//		mDB.connectMysql();
		String so_may = request.getParameter("somay");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TEN_CTY", "Công Ty Điện Lực TP Cần Thơ");
		parameters.put("TEN_DVI", "Điện Lực Ninh Kiều");
		parameters.put("SO_MAY", so_may);
		parameters.put("MSTS", "MSTS003");
		parameters.put("CONG_SUAT", "CS500");
		parameters.put("MA_DONVI", "Điện Lực Ninh Kiều");
		List<Obj_LichSu> list = new ArrayList<Obj_LichSu>();
		try {
			list = get_histoty(so_may);
		} catch (Exception e) {
			
		}
		JasperPrint jp1 = mDB.getReportPDF4("rpt_MBA_History.jasper", list,
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
				response.getWriter().write("LOI: " + ex.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public 	List<Obj_LichSu> get_histoty(String so_may) throws IllegalArgumentException {
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
			String mSQL = "Select * from " + Utils.table_history+" where "+Utils.so_may+" ='"
					+so_may+"' order by "+Utils.thoi_gian+" desc";
			rs = st.executeQuery(mSQL);
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