package com.thtsoft.maybienap.shared;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ObjectClient implements Serializable {
	public String Command="";
	public String ma_khachhang ="";
	public String Param2="";
	public String Param3="";
	public String Param4="";
	public String Param5="";

	public String getCommand() {
		return Command;
	}

	public void setCommand(String command) {
		Command = command;
	}

	public String getMa_khachhang() {
		return ma_khachhang;
	}

	public void setMa_khachhang(String ma_khachhang) {
		this.ma_khachhang = ma_khachhang;
	}

	public String getParam2() {
		return Param2;
	}

	public void setParam2(String param2) {
		Param2 = param2;
	}

	public String getParam3() {
		return Param3;
	}

	public void setParam3(String param3) {
		Param3 = param3;
	}

	public String getParam4() {
		return Param4;
	}

	public void setParam4(String param4) {
		Param4 = param4;
	}

	public String getParam5() {
		return Param5;
	}

	public void setParam5(String param5) {
		Param5 = param5;
	}


	
	
}
