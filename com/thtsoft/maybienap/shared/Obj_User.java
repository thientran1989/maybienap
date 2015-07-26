package com.thtsoft.maybienap.shared;

import java.io.Serializable;
import java.util.List;

public class Obj_User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String username_mba;
	public String password;
	public String ten_nhanvien;
	public String ma_donvi;
	public String ma_bophan;
	public int khoa=0;
	
	
	public int getKhoa() {
		return khoa;
	}

	public void setKhoa(int khoa) {
		this.khoa = khoa;
	}

	public String getUsername_mba() {
		return username_mba;
	}

	public void setUsername_mba(String username_mba) {
		this.username_mba = username_mba;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTen_nhanvien() {
		return ten_nhanvien;
	}

	public void setTen_nhanvien(String ten_nhanvien) {
		this.ten_nhanvien = ten_nhanvien;
	}

	public String getMa_donvi() {
		return ma_donvi;
	}

	public void setMa_donvi(String ma_donvi) {
		this.ma_donvi = ma_donvi;
	}

	public String getMa_bophan() {
		return ma_bophan;
	}

	public void setMa_bophan(String ma_bophan) {
		this.ma_bophan = ma_bophan;
	}
	
	public String get_cap_dvi(List<Obj_donvi> list){
		String ten = this.ma_donvi;
		try {
			if(list!=null){
				for (Obj_donvi obj_donvi : list) {
					if (obj_donvi.getMa_donvi().equals(this.ma_donvi)){
						ten = obj_donvi.getCap();
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return ten;
	}
	
	public String get_tendvi(List<Obj_donvi> list){
		String ten = this.ma_donvi;
		try {
			if(list!=null){
				for (Obj_donvi obj_donvi : list) {
					if (obj_donvi.getMa_donvi().equals(this.ma_donvi)){
						ten = obj_donvi.getTen_rutgon();
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return ten;
	}
	
	public static final String TAG_table_user="MBA_USER";
	
	public static final String TAG_username_mba="username_mba";
	public static final String TAG_password="password";
	public static final String TAG_ten_nhanvien="ten_nhanvien";
	public static final String TAG_ma_donvi="ma_donvi";
	public static final String TAG_ma_bophan="ma_bophan";
	public static final String TAG_khoa="khoa";
	
}
