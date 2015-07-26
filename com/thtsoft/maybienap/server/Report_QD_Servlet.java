package com.thtsoft.maybienap.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.thtsoft.maybienap.client.Client_function;
import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_date;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.tsuyu.jasper.db.Db;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings("serial")
public class Report_QD_Servlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

	}

	private String cancu_title_1 = "";
	private String cancu_title_2 = "";
//	private String cancu_title_3 = "";
	private String cancu_dieu_4 = "";
	private String ngaythangnam = "";
	private String cancu = "";
	private String dieu_1 = "";
	private String dieu_2 = "";
	private String dieu_3 = "";
	private String ghi_chu = "";
	private String nguon_goc = "";
	private String luu_ten = "";
	private String chuc_vu_2 = "";
	private String ho_ten_2 = "";
	String mQD;
	String sTime="";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Db mDB = new Db();
		// mDB.connectMysql();
		List<Obj_LichSu> list_dieu = null;
		List<Obj_LichSu> list_cap = null;
		if (request.getCharacterEncoding() == null)
			request.setCharacterEncoding("UTF-8");
		mQD = request.getParameter("p1");
		sTime = request.getParameter("p2");
		
		get_value();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TEN_CTY", "Công Ty Điện Lực TP Cần Thơ");
		parameters.put("TEN_DVI", "CÔNG TY ĐIỆN LỰC TP CẦN THƠ");
		parameters.put("QD_SO", "Số : " + mQD);
		parameters.put("QD_NGAY", ngaythangnam);
		parameters.put("CANCU_TITLE_1", cancu_title_1);
		parameters.put("CANCU_TITLE_2", cancu_title_2);
		parameters.put("CANCU", cancu);
		parameters.put("CAN_CU_DIEU_4", cancu_dieu_4);
		parameters.put("DIEU_1", dieu_1);
		parameters.put("DIEU_2", dieu_2);
		parameters.put("DIEU_3", dieu_3);
		parameters.put("GHI_CHU", ghi_chu);
		parameters.put("CHUC_VU_2", chuc_vu_2);
		parameters.put("HO_TEN_2", ho_ten_2);
		parameters.put("FOOT_LUU",luu_ten);
		parameters.put("NGUON_GOC", nguon_goc);
		List<Obj_LichSu> list = new ArrayList<Obj_LichSu>();
		try {
			list = get_dsLS(mQD);
		} catch (Exception e) {

		}
		if (list != null) {
			list_cap = new ArrayList<Obj_LichSu>();
			list_dieu = new ArrayList<Obj_LichSu>();
			for (Obj_LichSu obj_LichSu : list) {
				if (obj_LichSu.getLoai_dieudong().equals(Utils.DD_VE)) {
					list_dieu.add(obj_LichSu);
				} else if (obj_LichSu.getLoai_dieudong().equals(Utils.DD_CAP)) {
					list_cap.add(obj_LichSu);
				}
			}
		}
		InputStream sub_dieu =null;
		InputStream sub_cap=null;
		if(list_dieu.size()>0){
			sub_dieu = mDB.getInputReport("rpt_sub_dieuMBA.jasper");
			parameters.put("data_Dieu", new JRBeanCollectionDataSource(list_dieu));
			parameters.put("sub_Dieu", sub_dieu);
		}
		if(list_cap.size()>0){
			sub_cap = mDB.getInputReport("rpt_sub_capMBA.jasper");
			parameters.put("data_Cap", new JRBeanCollectionDataSource(list_cap));
			parameters.put("sub_Cap", sub_cap);
		}
		JasperPrint jp1=null;
		if(list_cap.size()>0 && list_dieu.size()>0){
			jp1 = mDB.getReportPDF3("rQD2.jasper", parameters);
		} else if(list_cap.size()>0){
			jp1 = mDB.getReportPDF3("rQD2_CAP.jasper", parameters);
		} else if(list_dieu.size()>0){
			jp1 = mDB.getReportPDF3("rQD2_DIEU.jasper", parameters);
		}
		

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
				response.getWriter().write(
						"LOI: " + ex.getMessage() + "\nlist size : "
								+ list.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Obj_LichSu> get_dsLS(String mQD)
			throws IllegalArgumentException {
		String loi = "";
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_LichSu> list_LS = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_LichSu.TAG_table_lichsu
					+ " where " + Obj_LichSu.TAG_QD_so + " = '" + mQD + "' and "+Obj_LichSu.TAG_loai_history+"='"+Utils.LS_LUANCHUYEN_BINHTHUONG+"'");
			if (rs != null) {
				list_LS = new ArrayList<Obj_LichSu>();
				while (rs.next()) {
					Obj_LichSu mBA = Get_Obj.set_result_HTR(rs);
					list_LS.add(mBA);
				}
			} else {
				loi = loi + "rs null";
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			loi = loi + e.toString();
		}
		return list_LS;
	}

	public Obj_QuyetDinh get_QD(String mQD) throws IllegalArgumentException {
		String loi = "";
		Connection con;
		ResultSet rs = null;
		Statement st;
		Obj_QuyetDinh oQD = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from "
					+ Obj_QuyetDinh.TAG_table_QuyetDinh + " where "
					+ Obj_QuyetDinh.TAG_QD_so + " = '" + mQD + "'");
			if (rs != null) {
				oQD = new Obj_QuyetDinh();
				while (rs.next()) {
					oQD = Get_Obj.set_result_QD(rs);
				}
			} else {
				loi = loi + "rs null";
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			loi = loi + e.toString();
		}
		return oQD;
	}

	public Obj_Config get_CF() throws IllegalArgumentException {
		String loi = "";
		Connection con;
		ResultSet rs = null;
		Statement st;
		Obj_Config oQD = null;
		try {
			con =getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_Config.TAG_table_config);
			if (rs != null) {
				oQD = new Obj_Config();
				while (rs.next()) {
					oQD = Get_Obj.set_result_Config(rs);
				}
			} else {
				loi = loi + "rs null";
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			loi = loi + e.toString();
		}
		return oQD;
	}
	public Obj_donvi get_DVI(String MA_DVI) throws IllegalArgumentException {
		String loi = "";
		Connection con;
		ResultSet rs = null;
		Statement st;
		Obj_donvi oDV = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_donvi.TAG_table_donvi+" where "+Obj_donvi.TAG_ma_donvi+" ='"+MA_DVI+"'");
			if (rs != null) {
				oDV = new Obj_donvi();
				while (rs.next()) {
					oDV = Get_Obj.set_result_DONVI(rs);
				}
			} else {
				loi = loi + "rs null";
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			loi = loi + e.toString();
		}
		return oDV;
	}

	public void get_value() {
		Obj_Config oCF = null;
		Obj_QuyetDinh oQD = null;
		Obj_donvi oDV = null;
		try {
			oCF = get_CF();
			oQD = get_QD(mQD);
			oDV = get_DVI(oQD.madv_from);
			dieu_1 = "Điều động tài sản cố định từ "+oDV.getTen_donvi()+" về Công ty:";
			dieu_2 = "Điều động tài sản cố định sau cấp cho "+oDV.getTen_donvi()+":";
			dieu_3 = "Phòng KH, P.VT và "+oDV.getTen_donvi()+" lập thủ tục giao nhận TSCĐ và thực hiện công tác. ";
			ghi_chu = oDV.getTen_donvi()+" phải  bàn  giao cho Phòng Kế Hoạch máy biến áp thu hồi sau khi thực hiện công tác không quá 07 ngày .";
			
			
		} catch (Exception e) {

		}
		try {
			if (oCF != null) {
				cancu_title_1 = oCF.getCancu_title_1();
				cancu_title_2 = oCF.getCancu_title_2();
//				cancu_title_3 = oCF.getCancu_title_3();
				cancu_dieu_4 =  oDV.getTen_donvi()+" có  "
						+ "trách  nhiệm  quản  lý , vận  hành  tài  sản  cố  định  theo"
						+ "  quyết  định số: "+oCF.getCancu_dieu_4()
						+" của Tổng Công ty Điện lực Miền Nam.";
				luu_ten = oCF.getLuu_ten();
				ho_ten_2 = oCF.getHo_ten_2();
				chuc_vu_2 = oCF.getChuc_vu_2();
			}
			if (oQD != null) {
				Obj_date my_date = Client_function.String2ngaythangnam(sTime);
				ngaythangnam = "Cần Thơ, ngày "+my_date.getNgay()+" tháng "+my_date.getThang()+" năm "+my_date.getNam();
				cancu = "          Căn cứ : "+oQD.getCan_cu()+" của "+oDV.getTen_donvi()+" V/v "+oQD.getLy_do();
				nguon_goc = " Nguồn gốc TSCĐ : "+oQD.getNguongoc_ts();
			}
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