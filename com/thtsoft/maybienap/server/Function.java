package com.thtsoft.maybienap.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;


public class Function {
	
	// send 2 server
	public static String alldata2server(CallbackResult mCB,
			List<Obj_LichSu> mL_khachhang) {
		String KQ = "[]";
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		JsonObject JO = new JsonObject();
		try {
			JO.addProperty("CB",gson.toJson(mCB));
			if (mL_khachhang!=null){
				JO.addProperty(Obj_LichSu.TAG_table_lichsu,gson.toJson(mL_khachhang));
			}
			
			KQ = JO.toString();
		} catch (Exception e) {

		}
		return KQ;
	}
	
	public static String byte_to_String(DataInputStream dis) {
		String s = "";
		int size_json = 0;
		byte[] data = null;
		try {
			size_json = dis.readInt();
			data = new byte[size_json];
			dis.readFully(data);
			s = new String(data, "UTF-8");
		} catch (Exception e) {
			
		}
		return s;
	}

	public static void write_String_to_byte(DataOutputStream dos, String json) {
		byte[] data;
		try {
			data = json.getBytes("UTF-8");
			try {
				dos.writeInt(data.length);
				dos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
