package com.thtsoft.maybienap.shared;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
	public static final String version = "Phiên bản : 1.8";
	public static final String CAP_PHAN_XUONG ="PHANXUONG";
	public static final String CAP_CONGTY ="CONGTY";
	public static final String CAP_DIENLUC ="DIENLUC";
	
	public static final int CHUA_TRA =0;
	public static final int DA_TRA =1;
	
	public static final int SUA =0;
	public static final int TAO =1;
	
	public static final String CB_TONTAI ="TONTAI";
	public static final String CB_OK ="OK";
	
	public static final String DD_VE ="DD_VE";
	public static final String DD_CAP ="DD_CAP";
	
	public static final String table_MAYBIENAP ="MBA_MAYBIENAP";
	public static final String table_user ="MBA_USER";
	public static final String table_history ="MBA_HISTORY";
	public static final String table_donvi ="MBA_DONVI";
	
	public static final int LENH_GET_DSMBA =1;
	public static final int LENH_GET_DSDVI =2;
	
	public static final String LS_SUA ="SUA";
	public static final String LS_TAO ="TAO";
	public static final String LS_THANHLY ="THANHLY";
	public static final String LS_LUANCHUYEN_BINHTHUONG ="LCBT";
	public static final String LS_LUANCHUYEN_SUACHUA ="LCSC";
	public static final String LS_LUANCHUYEN_NOIBO ="LCNB";
	public static final String LS_LUANCHUYEN_THANHLY ="LCTL";
	
	public static final String QD_LUANCHUYEN ="QDLUANCHUYEN";
	public static final String QD_THANHLY ="QDTHANHLY";
	public static final String QD_SUACHUA ="QDSUACHUA";
	
	// MBA
	public static final String user_mba ="user_mba";
	public static final String ma_donvi="ma_donvi";
	public static final String cong_suat="cong_suat";
	public static final String dien_ap="dien_ap";
	// sơ hoặc thứ
	public static final String so_may="so_may";
	public static final String nam_sanxuat="nam_sanxuat";
	public static final String MSTS="MSTS";
	public static final String trong_luong="trong_luong";
	public static final String dung_tich_dau="dung_tich_dau";
	public static final String dientro_cachdien_sx="dientro_cachdien_sx";
	public static final String tonthat_khongtai_sx="tonthat_khongtai_sx";
	public static final String tonthat_nganmach_sx="tonthat_nganmach_sx";
	public static final String dientro_cachdien_tn="dientro_cachdien_tn";
	public static final String tonthat_khongtai_tn="tonthat_khongtai_tn";
	public static final String tonthat_nganmach_tn="tonthat_nganmach_tn";
	public static final String nguongoc_taisan="nguongoc_taisan";
	// ĐL , Vốn ĐTXD ,KH bàn giao;
	public static final String nam_hinhthanh_ts="nam_hinhthanh_ts";
	public static final String nguyen_gia="nguyen_gia";
	public static final String tinhtrang_sudung="tinhtrang_sudung";
	public static final String thoi_gian_tao="thoi_gian_tao";
	public static final String thoi_gian_thaydoi="thoi_gian_thaydoi";
	
	// HISTORY
//	public static final String so_may;
	public static final String noi_cu="noi_cu";
	public static final String noi_moi="noi_moi";
	public static final String ma_histoty="ma_histoty";
	public static final String ly_do="ly_do";
	public static final String ghi_chu="ghi_chu";
	public static final String tinhtrang_hientai="tinhtrang_hientai";
//	public static final String user_mba="";
	public static final String thoi_gian="thoi_gian";
	
	public static final String ten_tram="ten_tram";
	public static final String tuyen="tuyen";
	public static final String tru="tru";
	public static final String TSVH_ngay_vanhanh="TSVH_ngay_vanhanh";
	public static final String TSVH_nac_phanap="TSVH_nac_phanap";
	public static final String TSVH_dong_tai="TSVH_dong_tai";
	public static final String QD_suachua_so="QD_suachua_so";
	public static final String QD_suachua_scan="QD_suachua_scan";
	public static final String BB_so="BB_so";
	public static final String BB_scan="BB_scan";
	
	// USER
	public static final String username_mba="username_mba";
	public static final String password="password";
	public static final String ten_nhanvien="ten_nhanvien";
//	public static final String ma_donvi="";
	
	// DON VI
//	public static final String ma_donvi="username_mba";
	public static final String ten_donvi="ten_donvi";
	
	// list
	
	public static final String TT_DUDIEUKIEN="DDK";
	public static final String TT_CHOSUACHUA="CSCBD";
	public static final String TT_DANGSUACHUA="DSCBD";
	public static final String TT_CHOTHANHLY="CTL";
	public static final String TT_DATHANHLY="DTL";
	public static final List<Obj_Text> get_list_tinhtrang(){
		List<Obj_Text> list_tinhtrang = new ArrayList<Obj_Text>();
		list_tinhtrang.add(new Obj_Text("DDK", "Đủ điều kiện vận hành"));
		list_tinhtrang
				.add(new Obj_Text("CSCBD", "Chờ sữa chữa bảo dưỡng"));
		list_tinhtrang
				.add(new Obj_Text("DSCBD", "Đang sữa chữa bảo dưỡng"));
		list_tinhtrang.add(new Obj_Text("CTL", "Chờ thanh lý"));
		list_tinhtrang.add(new Obj_Text("DTL", "Đã thanh lý"));
		return list_tinhtrang;
	}
	public static final List<Obj_Text> get_list_nac_vanhanh(){
		List<Obj_Text> list_nac_vanhanh = new ArrayList<Obj_Text>();
		list_nac_vanhanh.add(new Obj_Text("-5","1 -5%"));
		list_nac_vanhanh.add(new Obj_Text("-2.5","2 -2.5%"));
		list_nac_vanhanh.add(new Obj_Text("100","3 100%"));
		list_nac_vanhanh.add(new Obj_Text("+2.5","4 +2.5 %"));
		list_nac_vanhanh.add(new Obj_Text("+5","5 +5%"));
		return list_nac_vanhanh;
	}
	public static final List<Obj_Text> get_list_lichsu(){
		List<Obj_Text> list_lichsu = new ArrayList<Obj_Text>();
		list_lichsu.add(new Obj_Text("SUA","Đã sửa"));
		list_lichsu.add(new Obj_Text("TAO","Đã tạo"));
		list_lichsu.add(new Obj_Text("THANHLY","Đã thanh lý"));
		list_lichsu.add(new Obj_Text("LCBT","Luân chuyển bình thường"));
		list_lichsu.add(new Obj_Text("LCSC","Luân chuyển sữa chữa"));
		list_lichsu.add(new Obj_Text("LCNB","Luân chuyển nội bộ"));
		return list_lichsu;
	}
	

}
