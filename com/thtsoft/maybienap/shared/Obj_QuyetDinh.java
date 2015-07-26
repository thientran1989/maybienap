package com.thtsoft.maybienap.shared;

import java.io.Serializable;
import java.util.List;

public class Obj_QuyetDinh implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String QD_so="";
	public byte[] QD_scan;
	public String can_cu="";
	public String ly_do="";
	public String madv_from="";
	public String madv_to="";
	public String ma_history="";
	public String tram="";
	public String nguongoc_ts="";
	public String loai_QD="";
	public java.sql.Timestamp thoi_gian_tao ;
	public String tinhtrang_dieuve="";
	public String tram_se_gan="";
	
	public java.sql.Timestamp getThoi_gian_tao() {
		return thoi_gian_tao;
	}
	public void setThoi_gian_tao(java.sql.Timestamp thoi_gian_tao) {
		this.thoi_gian_tao = thoi_gian_tao;
	}
	public String getTinhtrang_dieuve() {
		return tinhtrang_dieuve;
	}
	public void setTinhtrang_dieuve(String tinhtrang_dieuve) {
		this.tinhtrang_dieuve = tinhtrang_dieuve;
	}
	public String getLoai_QD() {
		return loai_QD;
	}
	public void setLoai_QD(String loai_QD) {
		this.loai_QD = loai_QD;
	}
	public String getNguongoc_ts() {
		return nguongoc_ts;
	}
	public void setNguongoc_ts(String nguongoc_ts) {
		this.nguongoc_ts = nguongoc_ts;
	}
	public String getTram() {
		return tram;
	}
	public void setTram(String tram) {
		this.tram = tram;
	}
	public String getQD_so() {
		return QD_so;
	}
	public void setQD_so(String qD_so) {
		QD_so = qD_so;
	}
	public byte[] getQD_scan() {
		return QD_scan;
	}
	public void setQD_scan(byte[] qD_scan) {
		QD_scan = qD_scan;
	}
	public String getCan_cu() {
		return can_cu;
	}
	public void setCan_cu(String can_cu) {
		this.can_cu = can_cu;
	}
	public String getLy_do() {
		return ly_do;
	}
	public void setLy_do(String ly_do) {
		this.ly_do = ly_do;
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
	public String getMa_history() {
		return ma_history;
	}
	public void setMa_history(String ma_history) {
		this.ma_history = ma_history;
	}
	
	public static final String TAG_table_QuyetDinh="MBA_QUYETDINH";
	
	public static final String QD_LOAI_QD_LUANCHUYEN="QDLC";
	public static final String QD_LOAI_QD_SUACHUA="QDSC";
	public static final String QD_LOAI_QD_THANHLY="QDTL";
	public static final String QD_LOAI_QD_LUANCHUYEN_NOIBO="QDLCNB";
	
	public static final String TAG_QD_so="QD_so";
	public static final String TAG_QD_scan="QD_scan";
	public static final String TAG_can_cu="can_cu";
	public static final String TAG_ly_do="ly_do";
	public static final String TAG_madv_from="madv_from";
	public static final String TAG_madv_to="madv_to";
	public static final String TAG_ma_history="ma_history";
	public static final String TAG_tram="tram";
	public static final String TAG_nguongoc_ts="nguongoc_ts";
	public static final String TAG_loai_QD="loai_QD";
	public static final String TAG_thoi_gian_tao="thoi_gian_tao";

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
	
}
