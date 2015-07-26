package com.thtsoft.maybienap.shared;

import java.util.List;

public class My_function {

	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return name.length() > 3;
	}

	
	public static Obj_donvi get_KHO_ADB(List<Obj_donvi> list){
		Obj_donvi oDV = null;
		try {
			if(list!=null){
				for (Obj_donvi obj_donvi : list) {
					if (obj_donvi.getCap().equals(Utils.CAP_CONGTY)){
						oDV = obj_donvi;
						break;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return oDV;
	}
	public static String N2S(String in){
		String out ="";
		try {
			if(in!=null && !in.equals("null")){
				out =in;
			}
		} catch (Exception e) {
			
		}
		return out;
	}
	public static boolean QD_OK (Obj_QuyetDinh oQD){
		boolean mKQ = true;
		try {
			if(oQD!=null){
				if((oQD.getMadv_from().equals(oQD.getMadv_to()))
						|| oQD.getQD_so().length()==0
						|| oQD.getLy_do().length()==0){
					mKQ =false;
				}
			}else{
				mKQ =false;
			}
		} catch (Exception e) {
			
		}
		return mKQ;
	}
	public static boolean QD_THANHLY_OK (Obj_QuyetDinh oQD){
		boolean mKQ = true;
		try {
			if(oQD!=null){
				if(oQD.getQD_so().length()==0
						|| oQD.getLy_do().length()==0){
					mKQ =false;
				}
			}else{
				mKQ =false;
			}
		} catch (Exception e) {
			
		}
		return mKQ;
	}
	
}
