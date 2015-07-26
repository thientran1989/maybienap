package com.thtsoft.maybienap.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.thtsoft.maybienap.shared.Obj_date;

public class Client_function {
	
	public static Obj_date get_mydate(Date date){
		Obj_date mydate = new Obj_date();
		int mNGAY = 0;
		int mTHANG = 0;
		int mNAM = 0;
		try {
			String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(date);
			mNGAY = Integer.parseInt(dateString.split("/")[0]);
			mTHANG = Integer.parseInt(dateString.split("/")[1]);
			mNAM = Integer.parseInt(dateString.split("/")[2]);
		} catch (Exception e) {
			
		}
		mydate.setNgay(mNGAY);
		mydate.setThang(mTHANG);
		mydate.setNam(mNAM);
		return mydate;
	}
	public static java.sql.Timestamp date2timestamp(Date date){
		return (new java.sql.Timestamp(date.getTime())) ;
	}
	public static java.sql.Date date2sqldate(Date date){
		return (new java.sql.Date(date.getTime())) ;
	}
	public static java.sql.Date timestamp2sqldate(java.sql.Timestamp timestamp){
		return (new java.sql.Date(timestamp.getTime())) ;
	}
	public static java.util.Date timestamp2date(java.sql.Timestamp timestamp){
		return (new java.util.Date(timestamp.getTime())) ;
	}
	public static String timestamp2string(java.sql.Timestamp timestamp){
		String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(new java.util.Date(timestamp.getTime()));
		return dateString;
	}
	public static Obj_date String2ngaythangnam(String mytime){
		Obj_date mydate = new Obj_date();
		int mNGAY = 0;
		int mTHANG = 0;
		int mNAM = 0;
		try {
			mNGAY = Integer.parseInt(mytime.split("/")[0]);
			mTHANG = Integer.parseInt(mytime.split("/")[1]);
			mNAM = Integer.parseInt(mytime.split("/")[2]);
		} catch (Exception e) {
			
		}
		mydate.setNgay(mNGAY);
		mydate.setThang(mTHANG);
		mydate.setNam(mNAM);
		return mydate;
	}
	public static String date2string(Date mytime){
		String dateString="dd/MM/yyyy";
		try {
			dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(mytime);
		} catch (Exception e) {
			
		}
		return dateString;
	}

}
