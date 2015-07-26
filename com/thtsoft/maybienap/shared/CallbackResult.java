package com.thtsoft.maybienap.shared;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CallbackResult implements Serializable {
	private String Command;
	private String ResultString="";
	private int ResultInt=0;
	public List<?> ResultObj=null;
	public Obj_Config oCONFIG=null;
	public String kq2="";
	
	
	public Obj_Config getoCONFIG() {
		return oCONFIG;
	}
	public void setoCONFIG(Obj_Config oCONFIG) {
		this.oCONFIG = oCONFIG;
	}
	public int getResultInt() {
		return ResultInt;
	}
	public void setResultInt(int resultInt) {
		ResultInt = resultInt;
	}
	public CallbackResult() {
		super();
	}
	public String getCommand() {
		return Command;
	}
	public void setCommand(String command) {
		Command = command;
	}
	public String getResultString() {
		return ResultString;
	}
	public void setResultString(String resultString) {
		ResultString = resultString;
	}
	public List<?> getResultObj() {
		return ResultObj;
	}
	public void setResultObj(List<?> resultObj) {
		ResultObj = resultObj;
	}

}
