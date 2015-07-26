package com.thtsoft.maybienap.server;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.thtsoft.maybienap.client.Client_function;
import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;

public class DB_SQL {
	
	// Quyet dinh
	public static String get_sql_insert_QD(){
		String insertTableSQL = "Insert into CSKH."
				+ Obj_QuyetDinh.TAG_table_QuyetDinh + " (";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_QD_so + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_can_cu + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_ly_do + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_madv_from + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_madv_to + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_ma_history + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_tram + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_nguongoc_ts + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_loai_QD + ",";
		insertTableSQL = insertTableSQL + Obj_QuyetDinh.TAG_thoi_gian_tao + "";
		insertTableSQL = insertTableSQL + ") Values "
				+ "(?,?,?,?,?,?,?,?,?,?)";
		return insertTableSQL;
	}
	public static void set_preparedStatement_QD(PreparedStatement preparedStatement,Obj_QuyetDinh oQD){
		try {
			preparedStatement.setString(1, oQD.getQD_so() + "");
			preparedStatement.setString(2, oQD.getCan_cu() + "");
			preparedStatement.setString(3, oQD.getLy_do() + "");
			preparedStatement.setString(4, oQD.getMadv_from() + "");
			preparedStatement.setString(5, oQD.getMadv_to() + "");
			preparedStatement.setString(6, oQD.getMa_history() + "");
			preparedStatement.setString(7, oQD.getTram() + "");
			preparedStatement.setString(8, oQD.getNguongoc_ts() + "");
			preparedStatement.setString(9, oQD.getLoai_QD() + "");
			preparedStatement.setDate(10,  Client_function.timestamp2sqldate(oQD.getThoi_gian_tao()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// user
	public static String get_sql_insert_USER(){
		String mSQL = "Insert into CSKH."
				+ Obj_User.TAG_table_user + " (";
		mSQL = mSQL + Obj_User.TAG_username_mba + ",";
		mSQL = mSQL + Obj_User.TAG_password + ",";
		mSQL = mSQL + Obj_User.TAG_ten_nhanvien + ",";
		mSQL = mSQL + Obj_User.TAG_ma_donvi + ",";
		mSQL = mSQL + Obj_User.TAG_ma_bophan + ",";
		mSQL = mSQL + Obj_User.TAG_khoa + "";
		mSQL = mSQL + ") Values "
				+ "(?,?,?,?,?,?)";
		return mSQL;
	}
	public static void set_preparedStatement_USER(PreparedStatement preparedStatement,Obj_User oQD){
		try {
			preparedStatement.setString(1, oQD.getUsername_mba() + "");
			preparedStatement.setString(2, oQD.getPassword() + "");
			preparedStatement.setString(3, oQD.getTen_nhanvien() + "");
			preparedStatement.setString(4, oQD.getMa_donvi() + "");
			preparedStatement.setString(5, oQD.getMa_bophan() + "");
			preparedStatement.setString(6, oQD.getKhoa() + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// update
	public static String get_sql_update_USER(Obj_User oUSER){
		String SQL = "update CSKH."+ Obj_User.TAG_table_user;
		SQL = SQL + " set " + Obj_User.TAG_ten_nhanvien+ " = ?,";
		SQL = SQL + Obj_User.TAG_password+ " = ?";
		SQL = SQL + " where " + Obj_User.TAG_username_mba
				+ "='" + oUSER.getUsername_mba() + "'";
		return SQL;
	}
	public static void set_preparedStatement_update_USER(PreparedStatement preparedStatement,Obj_User mUS){
		try {
			preparedStatement.setString(1, mUS.getTen_nhanvien());
			preparedStatement.setString(2, mUS.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// lich su
	public static String get_sql_insert_LS(){
		String insertTableSQL = "Insert into CSKH."
				+ Obj_LichSu.TAG_table_lichsu + " (";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_kho + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_nha_sanxuat + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_ngay_vanhanh + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_pha + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_soxu_socap + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_dien_ap_nganmach + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tonthat_khongtai + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_nac_vanhanh + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_loai_dau + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_chu_sohuu + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_trong_luong + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_kich_thuoc + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tram + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_MSTS + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_nuoc_sanxuat + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_cong_suat + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_dienap_dinhmuc + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_soxu_thucap + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_dongdien_khongtai+ ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tonthat_cotai + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_to_dauday + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_khoiluong_dau + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tinhtrang_sudung + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_thoi_gian_tao + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_thoi_gian_thaydoi+ ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tuyen + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tru + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_user_taomba + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_user_suamba + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_so_may + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_dientro_cachdien_tn+ ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tonthat_khongtai_tn+ ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_tonthat_nganmach_tn+ ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_x_chuan + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_y_chuan + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_x_tam + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_y_tam + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_QD_so + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_madv_from + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_madv_to + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_loai_history + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_da_tra + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_ten_tscd + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_bb_tnghiem + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_ID + ",";
		insertTableSQL = insertTableSQL + Obj_LichSu.TAG_loai_dieudong + "";
		insertTableSQL = insertTableSQL + ") Values " + "(?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?,?)";
		return insertTableSQL;
	}
	
	public static void set_preparedStatement_LS(PreparedStatement preparedStatement,Obj_LichSu mLS){
		try {
			preparedStatement.setString(1, mLS.getKho() + "");
			preparedStatement.setString(2, mLS.getNha_sanxuat() + "");
			preparedStatement.setDate(3, Client_function.timestamp2sqldate(mLS.getNgay_vanhanh()));
			preparedStatement.setString(4, mLS.getPha() + "");
			preparedStatement.setString(5, mLS.getSoxu_socap() + "");
			preparedStatement.setString(6, mLS.getDien_ap_nganmach() + "");
			preparedStatement.setString(7, mLS.getTonthat_khongtai() + "");
			preparedStatement.setString(8, mLS.getNac_vanhanh() + "");
			preparedStatement.setString(9, mLS.getLoai_dau() + "");
			preparedStatement.setString(10, mLS.getChu_sohuu() + "");
			preparedStatement.setString(11, mLS.getTrong_luong() + "");
			preparedStatement.setString(12, mLS.getKich_thuoc() + "");
			preparedStatement.setString(13, mLS.getTram() + "");
			preparedStatement.setString(14, mLS.getMSTS() + "");
			preparedStatement.setString(15, mLS.getNuoc_sanxuat() + "");
			preparedStatement.setString(16, mLS.getCong_suat() + "");
			preparedStatement.setString(17, mLS.getDienap_dinhmuc() + "");
			preparedStatement.setString(18, mLS.getSoxu_thucap() + "");
			preparedStatement.setString(19, mLS.getDongdien_khongtai() + "");
			preparedStatement.setString(20, mLS.getTonthat_cotai() + "");
			preparedStatement.setString(21, mLS.getTo_dauday() + "");
			preparedStatement.setString(22, mLS.getKhoiluong_dau() + "");
			preparedStatement.setString(23, mLS.getTinhtrang_sudung() + "");
			preparedStatement.setTimestamp(24, Get_Obj.getCurrentTimeStamp());
			preparedStatement.setTimestamp(25, Get_Obj.getCurrentTimeStamp());
			preparedStatement.setString(26, mLS.getTuyen() + "");
			preparedStatement.setString(27, mLS.getTru() + "");
			preparedStatement.setString(28, mLS.getUser_taomba() + "");
			preparedStatement.setString(29, mLS.getUser_suamba() + "");
			preparedStatement.setString(30, mLS.getSo_may() + "");
			preparedStatement.setString(31, mLS.getDientro_cachdien_tn() + "");
			preparedStatement.setString(32, mLS.getTonthat_khongtai_tn() + "");
			preparedStatement.setString(33, mLS.getTonthat_nganmach_tn() + "");
			preparedStatement.setString(34, mLS.getX_chuan() + "");
			preparedStatement.setString(35, mLS.getY_chuan() + "");
			preparedStatement.setString(36, mLS.getX_tam() + "");
			preparedStatement.setString(37, mLS.getY_tam() + "");
			preparedStatement.setString(38, mLS.getQD_so() + "");
			preparedStatement.setString(39, mLS.getMadv_from() + "");
			preparedStatement.setString(40, mLS.getMadv_to() + "");
			preparedStatement.setString(41, mLS.getLoai_history() + "");
			preparedStatement.setString(42, mLS.getDa_tra() + "");
			preparedStatement.setString(43, mLS.getTen_tscd() + "");
			preparedStatement.setString(44, mLS.getBb_tnghiem() + "");
			preparedStatement.setInt(45, mLS.getID());
			preparedStatement.setString(46, mLS.getLoai_dieudong());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static String get_SQL_SEQ_LICHSU(){
		String SQL ="";
		SQL = "select CSKH.SEQ_MBA_LICHSU.nextval id from dual";
		return SQL;
	}
	// may bien ap

	public static String get_sql_update_MBA(Obj_LichSu oMBA){
		String SQL = "update CSKH."+ Obj_LichSu.TAG_table_lichsu;
		SQL = SQL + " set " + Obj_LichSu.TAG_kho+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_nha_sanxuat+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_ngay_vanhanh+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_pha + " = ?,";
		SQL = SQL + Obj_LichSu.TAG_soxu_socap + " = ?,";
		SQL = SQL + Obj_LichSu.TAG_dien_ap_nganmach+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tonthat_khongtai+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_nac_vanhanh+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_loai_dau+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_chu_sohuu+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_trong_luong+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_kich_thuoc+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tram+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_MSTS+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_nuoc_sanxuat+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_cong_suat+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_dienap_dinhmuc+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_soxu_thucap+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_dongdien_khongtai+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tonthat_cotai+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_to_dauday+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_khoiluong_dau+ " = ?,";
		SQL = SQL+Obj_LichSu.TAG_thoi_gian_thaydoi+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tuyen+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tru+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_user_suamba+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_dientro_cachdien_tn+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tonthat_khongtai_tn+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tonthat_nganmach_tn+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_tinhtrang_sudung+ " = ?,";	
		SQL = SQL + Obj_LichSu.TAG_x_chuan+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_y_chuan+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_x_tam+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_y_tam+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_da_tra+ " = ?,";
		SQL = SQL + Obj_LichSu.TAG_ten_tscd + " = ?,";
		SQL = SQL + Obj_LichSu.TAG_bb_tnghiem + " = ?";
		SQL = SQL + " where " + Obj_LichSu.TAG_so_may
				+ "='" + oMBA.getSo_may() + "' and ID="+oMBA.getID();
		return SQL;
	}
	public static void set_preparedStatement_updateLS(PreparedStatement preparedStatement,Obj_LichSu mLS){
		try {
			preparedStatement.setString(1, mLS.getKho() + "");
			preparedStatement.setString(2, mLS.getNha_sanxuat() + "");
			preparedStatement.setDate(3, Client_function.timestamp2sqldate(mLS.getNgay_vanhanh()));
			preparedStatement.setString(4, mLS.getPha() + "");
			preparedStatement.setString(5, mLS.getSoxu_socap() + "");
			preparedStatement.setString(6, mLS.getDien_ap_nganmach() + "");
			preparedStatement.setString(7, mLS.getTonthat_khongtai() + "");
			preparedStatement.setString(8, mLS.getNac_vanhanh() + "");
			preparedStatement.setString(9, mLS.getLoai_dau() + "");
			preparedStatement.setString(10, mLS.getChu_sohuu() + "");
			preparedStatement.setString(11, mLS.getTrong_luong() + "");
			preparedStatement.setString(12, mLS.getKich_thuoc() + "");
			preparedStatement.setString(13, mLS.getTram() + "");
			preparedStatement.setString(14, mLS.getMSTS() + "");
			preparedStatement.setString(15, mLS.getNuoc_sanxuat() + "");
			preparedStatement.setString(16, mLS.getCong_suat() + "");
			preparedStatement.setString(17, mLS.getDienap_dinhmuc() + "");
			preparedStatement.setString(18, mLS.getSoxu_thucap() + "");
			preparedStatement.setString(19, mLS.getDongdien_khongtai() + "");
			preparedStatement.setString(20, mLS.getTonthat_cotai() + "");
			preparedStatement.setString(21, mLS.getTo_dauday() + "");
			preparedStatement.setString(22, mLS.getKhoiluong_dau() + "");
			preparedStatement.setTimestamp(23, Get_Obj.getCurrentTimeStamp());
			preparedStatement.setString(24, mLS.getTuyen() + "");
			preparedStatement.setString(25, mLS.getTru() + "");
			preparedStatement.setString(26, mLS.getUser_suamba() + "");
			preparedStatement.setString(27, mLS.getDientro_cachdien_tn() + "");
			preparedStatement.setString(28, mLS.getTonthat_khongtai_tn() + "");
			preparedStatement.setString(29, mLS.getTonthat_nganmach_tn() + "");
			preparedStatement.setString(30, mLS.getTinhtrang_sudung() + "");
			preparedStatement.setString(31, mLS.getX_chuan() + "");
			preparedStatement.setString(32, mLS.getY_chuan() + "");
			preparedStatement.setString(33, mLS.getX_tam() + "");
			preparedStatement.setString(34, mLS.getY_tam() + "");
			preparedStatement.setInt(35, mLS.getDa_tra());
			preparedStatement.setString(36, mLS.getTen_tscd() + "");
			preparedStatement.setString(37, mLS.getBb_tnghiem() + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// CONFIG
	public static String get_sql_update_CONFIG(Obj_Config oCONFIG){
		String SQL = "update CSKH."+ Obj_Config.TAG_table_config;
		SQL = SQL + " set " + Obj_Config.TAG_cancu_title_1+ " = ?,";
		SQL = SQL + Obj_Config.TAG_cancu_title_2+ " = ?,";
		SQL = SQL + Obj_Config.TAG_cancu_title_3+ " = ?,";
		SQL = SQL + Obj_Config.TAG_cancu_dieu_4 + " = ?,";
		SQL = SQL + Obj_Config.TAG_luu_ten + " = ?,";
		SQL = SQL + Obj_Config.TAG_chuc_vu_2 + " = ?,";
		SQL = SQL + Obj_Config.TAG_ho_ten_2 + " = ?";
//		SQL = SQL + " where " + Obj_LichSu.TAG_so_may
//				+ "='" + oMBA.getSo_may() + "' and ID="+oMBA.getID();
		return SQL;
	}
	public static void set_preparedStatement_update_CONFIG(PreparedStatement preparedStatement,Obj_Config mLS){
		try {
			preparedStatement.setString(1, mLS.getCancu_title_1() + "");
			preparedStatement.setString(2, mLS.getCancu_title_2() + "");
			preparedStatement.setString(3, mLS.getCancu_title_3());
			preparedStatement.setString(4, mLS.getCancu_dieu_4() + "");
			preparedStatement.setString(5, mLS.getLuu_ten() + "");
			preparedStatement.setString(6, mLS.getChuc_vu_2() + "");
			preparedStatement.setString(7, mLS.getHo_ten_2()+ "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String get_SQL_DS_MBA_OF_DONVI(String DVI,String TT,String KEY){
		String SQL ="";
		String SQL_SELECT="select * from "+DB_CONFIG.DATA_NAME+"."+Obj_LichSu.TAG_table_lichsu+" MBA ";
		String SQL_KEY=Obj_LichSu.TAG_so_may+" like '%"+KEY+"%' and ";
		String SQL_MAXID ="ID = (select max(ID) from "+Obj_LichSu.TAG_table_lichsu+" where SO_MAY =MBA.SO_MAY)";
		String SQL_ORDER_ID =" order by ID desc";
		// lay het don vi
		if(DVI.equals("ALL")){
			// lay het tinh trang
			if(TT.equals("ALL")){
				if(KEY.length()==0){
					SQL=SQL_SELECT+"where "+
							SQL_MAXID+SQL_ORDER_ID;
				}else{
					SQL=SQL_SELECT+"where "+SQL_KEY+
							SQL_MAXID+SQL_ORDER_ID;
				}
			}else{
				if(KEY.length()==0){
					SQL=SQL_SELECT+"where "+Obj_LichSu.TAG_tinhtrang_sudung+"='"+TT+"' and "+
							SQL_MAXID+SQL_ORDER_ID;
				}else{
					SQL=SQL_SELECT+"where "+Obj_LichSu.TAG_tinhtrang_sudung+"='"+TT+"' and "+
							Obj_LichSu.TAG_so_may+" like '%"+KEY+"%' and "+
							SQL_MAXID+SQL_ORDER_ID;
				}
			}
			// lay 1 don vi
		}else{
			if(TT.equals("ALL")){
				if(KEY.length()==0){
					SQL=SQL_SELECT+"where KHO = '"+DVI+"' and "+
							SQL_MAXID+SQL_ORDER_ID;
				}else{
					SQL=SQL_SELECT+"where KHO = '"+DVI+"' and "+
							Obj_LichSu.TAG_so_may+" like '%"+KEY+"%' and "+
							SQL_MAXID+SQL_ORDER_ID;
				}
			}else{
				if(KEY.length()==0){
					SQL=SQL_SELECT+"where KHO = '"+DVI+"' and "
							+Obj_LichSu.TAG_tinhtrang_sudung+"='"+TT+"' and "+
							SQL_MAXID+SQL_ORDER_ID;
				}else{
					SQL=SQL_SELECT+"where KHO = '"+DVI+"' and "
							+Obj_LichSu.TAG_tinhtrang_sudung+"='"+TT+"' and "+SQL_KEY+
							SQL_MAXID+SQL_ORDER_ID;
				}
			}
		}
		return SQL;
	}
	public static String get_SQL_GET_LS_OF_MBA(Obj_LichSu mMBA){
		String SQL ="Select * from " + Obj_LichSu.TAG_table_lichsu
				+ " where " + Obj_LichSu.TAG_so_may + " ='"
				+ mMBA.getSo_may() + "' order by "
				+ Obj_LichSu.TAG_ID + " desc";
		return SQL;
	}
	
	public static String get_SQL_GET_LAST_MBA(Obj_LichSu mMBA){
		String SQL ="select * from MBA_LICHSU MBA where SO_MAY ='"+mMBA.getSo_may()+"' "
				+ "and ID = (select max(ID) from MBA_LICHSU where SO_MAY =MBA.SO_MAY)";
		return SQL;
	}
	public static String get_SQL_GET_DS_USER(Obj_User oUSER,List<Obj_donvi> lDONVI){
		String SQL ="Select * from " + Obj_User.TAG_table_user
				+ " where " + Obj_User.TAG_ma_donvi + " ='"
				+ oUSER.getMa_donvi() + "' order by "
				+ Obj_User.TAG_ma_donvi + " desc";
		if(oUSER.get_cap_dvi(lDONVI).equals(Utils.CAP_CONGTY)){
			SQL ="Select * from " + Obj_User.TAG_table_user
					+ " order by "
					+ Obj_User.TAG_ma_donvi + " desc";
		}
		return SQL;
	}
	public static String get_SQL_GET_CONFIG(){
		String SQL ="Select * from " + Obj_Config.TAG_table_config;
		return SQL;
	}
	public static String get_SQL_COUNT_MAYBIENAP(Obj_LichSu mLS){
		String SQL ="Select count(*) as SL from " + Obj_LichSu.TAG_table_lichsu+" where "
				+Obj_LichSu.TAG_so_may+"='"+mLS.getSo_may()+"'";
		return SQL;
	}
	public static String get_SQL_COUNT_USER(Obj_User mUS){
		String SQL ="Select count(*) as SL from " + Obj_User.TAG_table_user+" where "
				+Obj_User.TAG_username_mba+"='"+mUS.getUsername_mba()+"'";
		return SQL;
	}
	public static String get_SQL_COUNT_QD(Obj_QuyetDinh mQD){
		String SQL ="Select count(*) as SL from " + Obj_QuyetDinh.TAG_table_QuyetDinh+" where "
				+Obj_QuyetDinh.TAG_QD_so+"='"+mQD.getQD_so()+"'";
		return SQL;
	}
	public static String get_SQL_COUNT_LS_OF_USER(Obj_User mUS){
		String SQL ="Select count(*) as SL from " + Obj_LichSu.TAG_table_lichsu+" where "
				+Obj_LichSu.TAG_user_suamba+"='"+mUS.getUsername_mba()+"'";
		return SQL;
	}
	public static String get_SQL_DELETE_USER(Obj_User mUS){
		String SQL ="delete from CSKH."
				+ Obj_User.TAG_table_user+ " where " + Obj_User.TAG_username_mba + "='"
				+ mUS.getUsername_mba()+"'";
		return SQL;
	}
	
	
}
