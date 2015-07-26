package com.thtsoft.maybienap.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Obj_Config implements Serializable {
	private String cancu_title_1;
	private String cancu_title_2;
	private String cancu_title_3;
	private String cancu_dieu_4;
	private String luu_ten;
	private String chuc_vu_2;
	private String ho_ten_2;

	public Obj_Config() {
		super();
	}

	public String getCancu_title_1() {
		return cancu_title_1;
	}

	public void setCancu_title_1(String cancu_title_1) {
		this.cancu_title_1 = cancu_title_1;
	}

	public String getCancu_title_2() {
		return cancu_title_2;
	}

	public void setCancu_title_2(String cancu_title_2) {
		this.cancu_title_2 = cancu_title_2;
	}

	public String getCancu_title_3() {
		return cancu_title_3;
	}

	public void setCancu_title_3(String cancu_title_3) {
		this.cancu_title_3 = cancu_title_3;
	}

	public String getCancu_dieu_4() {
		return cancu_dieu_4;
	}

	public void setCancu_dieu_4(String cancu_dieu_4) {
		this.cancu_dieu_4 = cancu_dieu_4;
	}
	
	
	public String getLuu_ten() {
		return luu_ten;
	}

	public void setLuu_ten(String luu_ten) {
		this.luu_ten = luu_ten;
	}

	public String getChuc_vu_2() {
		return chuc_vu_2;
	}

	public void setChuc_vu_2(String chuc_vu_2) {
		this.chuc_vu_2 = chuc_vu_2;
	}

	public String getHo_ten_2() {
		return ho_ten_2;
	}

	public void setHo_ten_2(String ho_ten_2) {
		this.ho_ten_2 = ho_ten_2;
	}


	public static final String TAG_table_config="MBA_CONFIG_QD";
	
	public static final String TAG_cancu_title_1="cancu_title_1";
	public static final String TAG_cancu_title_2="cancu_title_2";
	public static final String TAG_cancu_title_3="cancu_title_3";
	public static final String TAG_cancu_dieu_4="cancu_dieu_4";
	public static final String TAG_luu_ten="LUU_TEN";
	public static final String TAG_chuc_vu_2="CHUC_VU_2";
	public static final String TAG_ho_ten_2="HO_TEN_2";
}
