package com.thtsoft.maybienap.shared;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CallbackResult_in implements Serializable {
	private String Command;
	private String ResultString="";
	public List<?> ResultObj=null;
	public Obj_QuyetDinh oQD;
	public Obj_Config oCF;
	
	public CallbackResult_in() {
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
	public Obj_QuyetDinh getoQD() {
		return oQD;
	}
	public void setoQD(Obj_QuyetDinh oQD) {
		this.oQD = oQD;
	}
	public Obj_Config getoCF() {
		return oCF;
	}
	public void setoCF(Obj_Config oCF) {
		this.oCF = oCF;
	}
}
