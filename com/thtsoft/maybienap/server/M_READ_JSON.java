package com.thtsoft.maybienap.server;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thtsoft.maybienap.shared.ObjectClient;

public class M_READ_JSON {

	public static ObjectClient read_client(JsonObject mJO) {
		ObjectClient mCB = null;
		try {
			Gson gson = new Gson();
			mCB = gson.fromJson(mJO.get("CL").getAsString(),ObjectClient.class);
		} catch (Exception e) {

		}
		return mCB;
	}

	// read list
	// 1canvas
//	public static List<Obj_CANVAS> read_list_canvas(JsonObject mJO) {
//		List<Obj_CANVAS> yourList = null;
//		try {
//			Type listType = new TypeToken<List<Obj_CANVAS>>() {
//			}.getType();
//			yourList = new Gson().fromJson(mJO.get(LabelFull.TABLE_CANVAS).getAsString(), listType);
//		} catch (Exception e) {
//
//		}
//
//		return yourList;
//	}
}
