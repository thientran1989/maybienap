package com.thtsoft.maybienap.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;

public class Get_Obj {
	
	public static Obj_donvi set_result_DONVI (ResultSet rs){
		Obj_donvi mDV = new Obj_donvi();
		try {
			mDV.setMa_donvi(rs.getString(Obj_donvi.TAG_ma_donvi+""));
			mDV.setTen_donvi(rs.getString(Obj_donvi.TAG_ten_donvi+""));
			mDV.setX_chuan(rs.getString(Obj_donvi.TAG_x_chuan+""));
			mDV.setY_chuan(rs.getString(Obj_donvi.TAG_y_chuan+""));
			mDV.setX_tam(rs.getString(Obj_donvi.TAG_x_tam+""));
			mDV.setY_tam(rs.getString(Obj_donvi.TAG_y_tam+""));
			mDV.setCap(rs.getString(Obj_donvi.TAG_cap+""));
			mDV.setTen_full(rs.getString(Obj_donvi.TAG_ten_full+""));
			mDV.setTen_rutgon(rs.getString(Obj_donvi.TAG_ten_rutgon+""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mDV;
		
	}
	
	public static Obj_User set_result_USER (ResultSet rs){
		Obj_User mUS = new Obj_User();
		try {
			mUS.setMa_donvi(rs.getString(Obj_User.TAG_ma_donvi+""));
			mUS.setPassword(rs.getString(Obj_User.TAG_password+""));
			mUS.setTen_nhanvien(rs.getString(Obj_User.TAG_ten_nhanvien+""));
			mUS.setUsername_mba(rs.getString(Obj_User.TAG_username_mba+""));
			mUS.setMa_bophan(rs.getString(Obj_User.TAG_ma_bophan+""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mUS;
		
	}
	
	public static Obj_LichSu set_result_HTR (ResultSet rs){
		Obj_LichSu mHTR = new Obj_LichSu();
		try {
			mHTR.setChu_sohuu(rs.getString(Obj_LichSu.TAG_chu_sohuu+""));
			mHTR.setCong_suat(rs.getString(Obj_LichSu.TAG_cong_suat+""));
			mHTR.setDien_ap_nganmach(rs.getString(Obj_LichSu.TAG_dien_ap_nganmach+""));
			mHTR.setDienap_dinhmuc(rs.getString(Obj_LichSu.TAG_dienap_dinhmuc+""));
			mHTR.setDientro_cachdien_tn(rs.getString(Obj_LichSu.TAG_dientro_cachdien_tn+""));
			mHTR.setDongdien_khongtai(rs.getString(Obj_LichSu.TAG_dongdien_khongtai+""));
			mHTR.setKho(rs.getString(Obj_LichSu.TAG_kho+""));
			mHTR.setKhoiluong_dau(rs.getString(Obj_LichSu.TAG_khoiluong_dau+""));
			mHTR.setKich_thuoc(rs.getString(Obj_LichSu.TAG_kich_thuoc+""));
			mHTR.setLoai_dau(rs.getString(Obj_LichSu.TAG_loai_dau+""));
			mHTR.setMSTS(rs.getString(Obj_LichSu.TAG_MSTS+""));
			mHTR.setNac_vanhanh(rs.getString(Obj_LichSu.TAG_nac_vanhanh+""));
			mHTR.setNgay_vanhanh(rs.getTimestamp(Obj_LichSu.TAG_ngay_vanhanh));
			mHTR.setNha_sanxuat(rs.getString(Obj_LichSu.TAG_nha_sanxuat+""));
			mHTR.setNuoc_sanxuat(rs.getString(Obj_LichSu.TAG_nuoc_sanxuat+""));
			mHTR.setPha(rs.getString(Obj_LichSu.TAG_pha+""));
			mHTR.setSo_may(rs.getString(Obj_LichSu.TAG_so_may+""));
			mHTR.setSoxu_socap(rs.getString(Obj_LichSu.TAG_soxu_socap+""));
			mHTR.setSoxu_thucap(rs.getString(Obj_LichSu.TAG_soxu_thucap+""));
			mHTR.setThoi_gian_tao(rs.getTimestamp(Obj_LichSu.TAG_thoi_gian_tao+""));
			mHTR.setThoi_gian_thaydoi(rs.getTimestamp(Obj_LichSu.TAG_thoi_gian_thaydoi+""));
			mHTR.setTinhtrang_sudung(rs.getString(Obj_LichSu.TAG_tinhtrang_sudung+""));
			mHTR.setTo_dauday(rs.getString(Obj_LichSu.TAG_to_dauday+""));
			mHTR.setTonthat_cotai(rs.getString(Obj_LichSu.TAG_tonthat_cotai+""));
			mHTR.setTonthat_khongtai(rs.getString(Obj_LichSu.TAG_tonthat_khongtai+""));
			mHTR.setTonthat_khongtai_tn(rs.getString(Obj_LichSu.TAG_tonthat_khongtai_tn+""));
			mHTR.setTonthat_nganmach_tn(rs.getString(Obj_LichSu.TAG_tonthat_nganmach_tn+""));
			
			String tram = (rs.getString(Obj_LichSu.TAG_tram));
			if(tram==null || tram.equals("null")){
				mHTR.setTram("");
			}else{
				mHTR.setTram(tram);
			}
			
			mHTR.setTrong_luong(rs.getString(Obj_LichSu.TAG_trong_luong+""));
			mHTR.setTru(rs.getString(Obj_LichSu.TAG_tru+""));
			mHTR.setTuyen(rs.getString(Obj_LichSu.TAG_tuyen+""));
			mHTR.setUser_suamba(rs.getString(Obj_LichSu.TAG_user_suamba+""));
			mHTR.setUser_taomba(rs.getString(Obj_LichSu.TAG_user_taomba+""));
			mHTR.setX_chuan(rs.getString(Obj_LichSu.TAG_x_chuan+""));
			mHTR.setX_tam(rs.getString(Obj_LichSu.TAG_x_tam+""));
			mHTR.setY_chuan(rs.getString(Obj_LichSu.TAG_y_chuan+""));
			mHTR.setY_tam(rs.getString(Obj_LichSu.TAG_y_tam+""));
			mHTR.setQD_so(rs.getString(Obj_LichSu.TAG_QD_so+""));
			mHTR.setMadv_from(rs.getString(Obj_LichSu.TAG_madv_from+""));
			mHTR.setMadv_to(rs.getString(Obj_LichSu.TAG_madv_to+""));
			mHTR.setLoai_history(rs.getString(Obj_LichSu.TAG_loai_history+""));
			mHTR.setTinhtrang_sudung(rs.getString(Obj_LichSu.TAG_tinhtrang_sudung+""));
			String bb_tnghiem=rs.getString(Obj_LichSu.TAG_bb_tnghiem);
			if(bb_tnghiem==null || bb_tnghiem.equals("null")){
				mHTR.setBb_tnghiem("");
			}else{
				mHTR.setBb_tnghiem(bb_tnghiem);
			}
			
			mHTR.setTen_tscd(rs.getString(Obj_LichSu.TAG_ten_tscd+""));
			mHTR.setID(rs.getInt(Obj_LichSu.TAG_ID+""));
			mHTR.setLoai_dieudong(rs.getString(Obj_LichSu.TAG_loai_dieudong+""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mHTR;		
	}
	public static Obj_QuyetDinh set_result_QD(ResultSet rs){
		Obj_QuyetDinh mHTR = new Obj_QuyetDinh();
		try {
			mHTR.setQD_so(rs.getString(Obj_QuyetDinh.TAG_QD_so+""));
			mHTR.setQD_scan(rs.getBytes(Obj_QuyetDinh.TAG_QD_scan+""));
			mHTR.setCan_cu(rs.getString(Obj_QuyetDinh.TAG_can_cu+""));
			mHTR.setLy_do(rs.getString(Obj_QuyetDinh.TAG_ly_do+""));
			mHTR.setMadv_from(rs.getString(Obj_QuyetDinh.TAG_madv_from+""));
			mHTR.setMadv_to(rs.getString(Obj_QuyetDinh.TAG_madv_to+""));
			mHTR.setMa_history(rs.getString(Obj_QuyetDinh.TAG_ma_history+""));
			mHTR.setTram(rs.getString(Obj_QuyetDinh.TAG_tram+""));
			mHTR.setNguongoc_ts(rs.getString(Obj_QuyetDinh.TAG_nguongoc_ts+""));
			mHTR.setLoai_QD(rs.getString(Obj_QuyetDinh.TAG_loai_QD+""));
			mHTR.setThoi_gian_tao(rs.getTimestamp(Obj_QuyetDinh.TAG_thoi_gian_tao+""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mHTR;
	}
	public static Obj_Config set_result_Config(ResultSet rs){
		Obj_Config mHTR = new Obj_Config();
		try {
			mHTR.setCancu_dieu_4(rs.getString(Obj_Config.TAG_cancu_dieu_4+""));
			mHTR.setCancu_title_1(rs.getString(Obj_Config.TAG_cancu_title_1+""));
			mHTR.setCancu_title_2(rs.getString(Obj_Config.TAG_cancu_title_2+""));
			mHTR.setCancu_title_3(rs.getString(Obj_Config.TAG_cancu_title_3+""));
			mHTR.setLuu_ten(rs.getString(Obj_Config.TAG_luu_ten));
			mHTR.setChuc_vu_2(rs.getString(Obj_Config.TAG_chuc_vu_2));
			mHTR.setHo_ten_2(rs.getString(Obj_Config.TAG_ho_ten_2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mHTR;
	}
	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	public static java.sql.Date getCurrentSQLdate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());

	}
	public static Date getCurrent_Date() {
		java.util.Date today = new java.util.Date();
		return today;
	}
}
