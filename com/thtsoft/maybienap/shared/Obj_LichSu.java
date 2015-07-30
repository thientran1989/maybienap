package com.thtsoft.maybienap.shared;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Obj_LichSu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String kho ="";
	public String nha_sanxuat ="";
	public java.sql.Timestamp ngay_vanhanh;
	public String pha ="";
	public String soxu_socap ="";
	public String dien_ap_nganmach ="";
	public String tonthat_khongtai ="";
	public String nac_vanhanh ="";
	public String loai_dau ="";
	public String chu_sohuu ="";
	public String trong_luong ="";
	public String kich_thuoc ="";
	public String tram ="";
	public String MSTS ="";
	public String nuoc_sanxuat ="";
	public String cong_suat ="";
	public String dienap_dinhmuc ="";
	public String soxu_thucap ="";
	public String dongdien_khongtai ="";
	public String tonthat_cotai ="";
	public String to_dauday ="";
	public String khoiluong_dau ="";
	public String tinhtrang_sudung ="";
	public java.sql.Timestamp thoi_gian_tao ;
	public java.sql.Timestamp thoi_gian_thaydoi ;
	public String tuyen ="";
	public String tru ="";
	public String user_taomba ="";
	public String user_suamba ="";
	public String so_may ="";
	public String dientro_cachdien_tn ="";
	public String tonthat_khongtai_tn ="";
	public String tonthat_nganmach_tn ="";
	public String x_chuan ="";
	public String y_chuan ="";
	public String x_tam ="";
	public String y_tam ="";
	public String QD_so ="";
	public String madv_from ="";
	public String madv_to ="";
	public String loai_history ="";
	public int da_tra =0;
	public String ten_tscd ="";
	public String bb_tnghiem ="";
	public String loai_dieudong ="";
//	public int namhinhthanhts;
	public int ID =0;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLoai_dieudong() {
		return loai_dieudong;
	}

	public void setLoai_dieudong(String loai_dieudong) {
		this.loai_dieudong = loai_dieudong;
	}

	public String getTen_tscd() {
		if(ten_tscd==null || ten_tscd.equals("null")){
			return "";
		}else{
			return ten_tscd;
		}
		
	}

	public void setTen_tscd(String ten_tscd) {
		this.ten_tscd = ten_tscd;
	}
	

	public String getBb_tnghiem() {
		if(bb_tnghiem==null || bb_tnghiem.equals("null")){
			return "";
		}else{
			return bb_tnghiem;
		}
	}

	public void setBb_tnghiem(String bb_tnghiem) {
		this.bb_tnghiem = bb_tnghiem;
	}

	public String getTinhtrang_sudung() {
		return tinhtrang_sudung;
	}

	public void setTinhtrang_sudung(String tinhtrang_sudung) {
		this.tinhtrang_sudung = tinhtrang_sudung;
	}

	public int getDa_tra() {
		return da_tra;
	}

	public void setDa_tra(int da_tra) {
		this.da_tra = da_tra;
	}

	public String getKho() {
		return kho;
	}

	public void setKho(String kho) {
		this.kho = kho;
	}

	public String getNha_sanxuat() {
		return nha_sanxuat;
	}

	public void setNha_sanxuat(String nha_sanxuat) {
		this.nha_sanxuat = nha_sanxuat;
	}

	public java.sql.Timestamp getNgay_vanhanh() {
		return ngay_vanhanh;
	}

	public void setNgay_vanhanh(java.sql.Timestamp ngay_vanhanh) {
		this.ngay_vanhanh = ngay_vanhanh;
	}

	public String getPha() {
		return pha;
	}

	public void setPha(String pha) {
		this.pha = pha;
	}

	public String getSoxu_socap() {
		return soxu_socap;
	}

	public void setSoxu_socap(String soxu_socap) {
		this.soxu_socap = soxu_socap;
	}

	public String getDien_ap_nganmach() {
		return dien_ap_nganmach;
	}

	public void setDien_ap_nganmach(String dien_ap_nganmach) {
		this.dien_ap_nganmach = dien_ap_nganmach;
	}

	public String getTonthat_khongtai() {
		return tonthat_khongtai;
	}

	public void setTonthat_khongtai(String tonthat_khongtai) {
		this.tonthat_khongtai = tonthat_khongtai;
	}

	public String getNac_vanhanh() {
		return nac_vanhanh;
	}

	public void setNac_vanhanh(String nac_vanhanh) {
		this.nac_vanhanh = nac_vanhanh;
	}

	public String getLoai_dau() {
		return loai_dau;
	}

	public void setLoai_dau(String loai_dau) {
		this.loai_dau = loai_dau;
	}

	public String getChu_sohuu() {
		return chu_sohuu;
	}

	public void setChu_sohuu(String chu_sohuu) {
		this.chu_sohuu = chu_sohuu;
	}

	public String getTrong_luong() {
		return trong_luong;
	}

	public void setTrong_luong(String trong_luong) {
		this.trong_luong = trong_luong;
	}

	public String getKich_thuoc() {
		return kich_thuoc;
	}

	public void setKich_thuoc(String kich_thuoc) {
		this.kich_thuoc = kich_thuoc;
	}

	public String getTram() {
		if(tram==null || tram.equals("null")){
			return "";
		}else{
			return tram;
		}
	}

	public void setTram(String tram) {
		this.tram = tram;
	}

	public String getMSTS() {
		return MSTS;
	}

	public void setMSTS(String mSTS) {
		MSTS = mSTS;
	}

	public String getNuoc_sanxuat() {
		return nuoc_sanxuat;
	}

	public void setNuoc_sanxuat(String nuoc_sanxuat) {
		this.nuoc_sanxuat = nuoc_sanxuat;
	}

	public String getCong_suat() {
		return cong_suat;
	}

	public void setCong_suat(String cong_suat) {
		this.cong_suat = cong_suat;
	}

	public String getDienap_dinhmuc() {
		return dienap_dinhmuc;
	}

	public void setDienap_dinhmuc(String dienap_dinhmuc) {
		this.dienap_dinhmuc = dienap_dinhmuc;
	}

	public String getSoxu_thucap() {
		return soxu_thucap;
	}

	public void setSoxu_thucap(String soxu_thucap) {
		this.soxu_thucap = soxu_thucap;
	}

	public String getDongdien_khongtai() {
		return dongdien_khongtai;
	}

	public void setDongdien_khongtai(String dongdien_khongtai) {
		this.dongdien_khongtai = dongdien_khongtai;
	}

	public String getTonthat_cotai() {
		return tonthat_cotai;
	}

	public void setTonthat_cotai(String tonthat_cotai) {
		this.tonthat_cotai = tonthat_cotai;
	}

	public String getTo_dauday() {
		return to_dauday;
	}

	public void setTo_dauday(String to_dauday) {
		this.to_dauday = to_dauday;
	}

	public String getKhoiluong_dau() {
		return khoiluong_dau;
	}

	public void setKhoiluong_dau(String khoiluong_dau) {
		this.khoiluong_dau = khoiluong_dau;
	}

	public java.sql.Timestamp getThoi_gian_tao() {
		return thoi_gian_tao;
	}

	public void setThoi_gian_tao(java.sql.Timestamp thoi_gian_tao) {
		this.thoi_gian_tao = thoi_gian_tao;
	}

	public java.sql.Timestamp getThoi_gian_thaydoi() {
		return thoi_gian_thaydoi;
	}

	public void setThoi_gian_thaydoi(java.sql.Timestamp thoi_gian_thaydoi) {
		this.thoi_gian_thaydoi = thoi_gian_thaydoi;
	}

	public String getTuyen() {
		return tuyen;
	}

	public void setTuyen(String tuyen) {
		this.tuyen = tuyen;
	}

	public String getTru() {
		return tru;
	}

	public void setTru(String tru) {
		this.tru = tru;
	}

	public String getUser_taomba() {
		return user_taomba;
	}

	public void setUser_taomba(String user_taomba) {
		this.user_taomba = user_taomba;
	}

	public String getUser_suamba() {
		return user_suamba;
	}

	public void setUser_suamba(String user_suamba) {
		this.user_suamba = user_suamba;
	}

	public String getSo_may() {
		return so_may;
	}

	public void setSo_may(String so_may) {
		this.so_may = so_may;
	}

	public String getDientro_cachdien_tn() {
		return dientro_cachdien_tn;
	}

	public void setDientro_cachdien_tn(String dientro_cachdien_tn) {
		this.dientro_cachdien_tn = dientro_cachdien_tn;
	}

	public String getTonthat_khongtai_tn() {
		return tonthat_khongtai_tn;
	}

	public void setTonthat_khongtai_tn(String tonthat_khongtai_tn) {
		this.tonthat_khongtai_tn = tonthat_khongtai_tn;
	}

	public String getTonthat_nganmach_tn() {
		return tonthat_nganmach_tn;
	}

	public void setTonthat_nganmach_tn(String tonthat_nganmach_tn) {
		this.tonthat_nganmach_tn = tonthat_nganmach_tn;
	}

	public String getQD_so() {
		return QD_so;
	}

	public void setQD_so(String qD_so) {
		QD_so = qD_so;
	}

	public String getMadv_from() {
		return madv_from;
	}

	public void setMadv_from(String madv_from) {
		this.madv_from = madv_from;
	}

	public String getMadv_to() {
		return madv_to;
	}

	public void setMadv_to(String madv_to) {
		this.madv_to = madv_to;
	}

	public String getX_chuan() {
		return x_chuan;
	}

	public void setX_chuan(String x_chuan) {
		this.x_chuan = x_chuan;
	}

	public String getY_chuan() {
		return y_chuan;
	}

	public void setY_chuan(String y_chuan) {
		this.y_chuan = y_chuan;
	}

	public String getX_tam() {
		return x_tam;
	}

	public void setX_tam(String x_tam) {
		this.x_tam = x_tam;
	}

	public String getY_tam() {
		return y_tam;
	}

	public void setY_tam(String y_tam) {
		this.y_tam = y_tam;
	}

	public String getLoai_history() {
		return loai_history;
	}

	public void setLoai_history(String loai_history) {
		this.loai_history = loai_history;
	}
	public String get_time_display(){
		String dateString ="Không xác định";
		try {
			dateString = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm").format(new java.util.Date(getThoi_gian_thaydoi().getTime()));
		} catch (Exception e) {
			
		}
		return dateString;
	}

	public static final String TAG_table_lichsu = "MBA_LICHSU";

	public static final String TAG_kho = "kho";
	public static final String TAG_nha_sanxuat = "nha_sanxuat";
	public static final String TAG_ngay_vanhanh = "ngay_vanhanh";
	public static final String TAG_pha = "pha";
	public static final String TAG_soxu_socap = "soxu_socap";
	public static final String TAG_dien_ap_nganmach = "dien_ap_nganmach";
	public static final String TAG_tonthat_khongtai = "tonthat_khongtai";
	public static final String TAG_nac_vanhanh = "nac_vanhanh";
	public static final String TAG_loai_dau = "loai_dau";
	public static final String TAG_chu_sohuu = "chu_sohuu";
	public static final String TAG_trong_luong = "trong_luong";
	public static final String TAG_kich_thuoc = "kich_thuoc";
	public static final String TAG_tram = "tram";
	public static final String TAG_MSTS = "MSTS";
	public static final String TAG_nuoc_sanxuat = "nuoc_sanxuat";
	public static final String TAG_cong_suat = "cong_suat";
	public static final String TAG_dienap_dinhmuc = "dienap_dinhmuc";
	public static final String TAG_soxu_thucap = "soxu_thucap";
	public static final String TAG_dongdien_khongtai = "dongdien_khongtai";
	public static final String TAG_tonthat_cotai = "tonthat_cotai";
	public static final String TAG_to_dauday = "to_dauday";
	public static final String TAG_khoiluong_dau = "khoiluong_dau";
	public static final String TAG_tinhtrang_sudung = "tinhtrang_sudung";
	public static final String TAG_thoi_gian_tao = "thoi_gian_tao";
	public static final String TAG_thoi_gian_thaydoi = "thoi_gian_thaydoi";
	public static final String TAG_tuyen = "tuyen";
	public static final String TAG_tru = "tru";
	public static final String TAG_user_taomba = "user_taomba";
	public static final String TAG_user_suamba = "user_suamba";
	public static final String TAG_so_may = "so_may";
	public static final String TAG_dientro_cachdien_tn = "dientro_cachdien_tn";
	public static final String TAG_tonthat_khongtai_tn = "tonthat_khongtai_tn";
	public static final String TAG_tonthat_nganmach_tn = "tonthat_nganmach_tn";
	public static final String TAG_x_chuan = "x_chuan";
	public static final String TAG_y_chuan = "y_chuan";
	public static final String TAG_x_tam = "x_tam";
	public static final String TAG_y_tam = "y_tam";

	public static final String TAG_QD_so = "QD_so";
	public static final String TAG_madv_from = "madv_from";
	public static final String TAG_madv_to = "madv_to";
	public static final String TAG_loai_history = "loai_history";
	public static final String TAG_da_tra = "da_tra";
	public static final String TAG_ten_tscd="TEN_TSCD";
	public static final String TAG_bb_tnghiem="BB_TNGHIEM";
	public static final String TAG_ID="ID";
	public static final String TAG_loai_dieudong="loai_dieudong";
	
	public static String get_tinhtrang_show(String KEY){
		String TT = "Không xác định";
		List<Obj_Text> listTT = Utils.get_list_tinhtrang();
		for (Obj_Text obj_Text : listTT) {
			if(obj_Text.KEY.equals(KEY)){
				TT = obj_Text.NAME;
				break;
			}
		}
		return TT;
	}
	public static String get_loailichsu_show(String KEY){
		String TT = "Không xác định";
		List<Obj_Text> listTT = Utils.get_list_lichsu();
		for (Obj_Text obj_Text : listTT) {
			if(obj_Text.KEY.equals(KEY)){
				TT = obj_Text.NAME;
				break;
			}
		}
		return TT;
	}

	public String get_tendvi(List<Obj_donvi> list){
		String ten = this.kho;
		try {
			if(list!=null){
				for (Obj_donvi obj_donvi : list) {
					if (obj_donvi.getMa_donvi().equals(this.kho)){
						ten = obj_donvi.getTen_rutgon();
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return ten;
	}
	public String get_tendvi_from(List<Obj_donvi> list){
		String ten = this.madv_from;
		try {
			if(list!=null){
				for (Obj_donvi obj_donvi : list) {
					if (obj_donvi.getMa_donvi().equals(this.madv_from)){
						ten = obj_donvi.getTen_rutgon();
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return ten;
	}
	public String get_tendvi_to(List<Obj_donvi> list){
		String ten = this.madv_to;
		try {
			if(list!=null){
				for (Obj_donvi obj_donvi : list) {
					if (obj_donvi.getMa_donvi().equals(this.madv_to)){
						ten = obj_donvi.getTen_rutgon();
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return ten;
	}
	
	public Obj_LichSu copy_object (){
		Obj_LichSu mHTR = new Obj_LichSu();
		try {
			mHTR.setChu_sohuu(getChu_sohuu()+"");
			mHTR.setCong_suat(getCong_suat()+"");
			mHTR.setDien_ap_nganmach(getDien_ap_nganmach()+"");
			mHTR.setDienap_dinhmuc(getDienap_dinhmuc()+"");
			mHTR.setDientro_cachdien_tn(getDientro_cachdien_tn()+"");
			mHTR.setDongdien_khongtai(getDongdien_khongtai()+"");
			mHTR.setKho(getKho()+"");
			mHTR.setKhoiluong_dau(getKhoiluong_dau()+"");
			mHTR.setKich_thuoc(getKich_thuoc()+"");
			mHTR.setLoai_dau(getLoai_dau()+"");
			mHTR.setMSTS(getMSTS()+"");
			mHTR.setNac_vanhanh(getNac_vanhanh()+"");
			mHTR.setNgay_vanhanh(getNgay_vanhanh());
			mHTR.setNha_sanxuat(getNha_sanxuat()+"");
			mHTR.setNuoc_sanxuat(getNuoc_sanxuat()+"");
			mHTR.setPha(getPha()+"");
			mHTR.setSo_may(getSo_may()+"");
			mHTR.setSoxu_socap(getSoxu_socap()+"");
			mHTR.setSoxu_thucap(getSoxu_thucap()+"");
			mHTR.setThoi_gian_tao(getThoi_gian_tao());
			mHTR.setThoi_gian_thaydoi(getThoi_gian_thaydoi());
			mHTR.setTo_dauday(getTo_dauday()+"");
			mHTR.setTonthat_cotai(getTonthat_cotai()+"");
			mHTR.setTonthat_khongtai(getTonthat_khongtai()+"");
			mHTR.setTonthat_khongtai_tn(getTonthat_khongtai_tn()+"");
			mHTR.setTonthat_nganmach_tn(getTonthat_nganmach_tn()+"");
			mHTR.setTram(getTram()+"");
			mHTR.setTrong_luong(getTrong_luong()+"");
			mHTR.setTru(getTru()+"");
			mHTR.setTuyen(getTuyen()+"");
			mHTR.setUser_suamba(getUser_suamba()+"");
			mHTR.setUser_taomba(getUser_taomba()+"");
			mHTR.setX_chuan(getX_chuan()+"");
			mHTR.setX_tam(getX_tam()+"");
			mHTR.setY_chuan(getY_chuan()+"");
			mHTR.setY_tam(getY_tam()+"");
			mHTR.setTinhtrang_sudung(getTinhtrang_sudung()+"");
			mHTR.setBb_tnghiem(getBb_tnghiem()+"");
			mHTR.setTen_tscd(getTen_tscd()+"");
			mHTR.setQD_so(getQD_so()+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mHTR;
		
	}
	
	

}
