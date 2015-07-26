package com.thtsoft.maybienap.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;

public interface IOData2ServerAsync {
	void getMBA( AsyncCallback<List<Obj_LichSu>> callback)throws IllegalArgumentException;
	void getLS(AsyncCallback<List<Obj_LichSu>> callback);
	void getMBA2(int lenh,AsyncCallback<CallbackResult> callback)throws IllegalArgumentException;
	void tao_lichsu(Obj_User mUS, Obj_LichSu mLS,
			AsyncCallback<CallbackResult> callback);

	void getDVI(AsyncCallback<List<Obj_donvi>> callback);

	void getDVI2(int lenh, AsyncCallback<CallbackResult> callback);

	void login(Obj_User mUS, AsyncCallback<Obj_User> callback);
	// may bien ap
	void get_MBA(String so_may,String DVI, AsyncCallback<Obj_LichSu> callback);
	void update_MBA(Obj_LichSu mBA, AsyncCallback<CallbackResult> callback)
			throws IllegalArgumentException;

	void check_tontai_MBA(String mBA, AsyncCallback<String> callback);

	void check_tontai_QD(String QD, AsyncCallback<CallbackResult> callback);

	void tao_quyetdinh(Obj_User mUS, Obj_QuyetDinh mQD,
			AsyncCallback<CallbackResult> callback);
	
	void xoa_lichsu(Obj_User mUS, Obj_LichSu mLS,
			AsyncCallback<CallbackResult> callback);

	void getBB_SEARCH(String sobb, String DVI,
			AsyncCallback<CallbackResult> callback);

	void getBB(AsyncCallback<List<Obj_QuyetDinh>> callback);

	void update_DD_MBA(Obj_LichSu oMBA,
			AsyncCallback<CallbackResult> callback);
	void get_LICHSU_OF_QD(String QD, AsyncCallback<CallbackResult> callback);
	void xoa_QD(Obj_User mUS, Obj_QuyetDinh oQD,
			AsyncCallback<CallbackResult> callback);
	void check_tontai_LS_OF_QD(String QD, AsyncCallback<CallbackResult> callback);
	void get_DSMBA_LOC(String donvi, String tinhtrang,String key,
			AsyncCallback<CallbackResult> callback);
	void get_LICHSU_OF_MBA(Obj_LichSu mMBA,
			AsyncCallback<CallbackResult> callback);
	void hoantat_quyetdinh(Obj_User mUS, Obj_QuyetDinh mQD,
			List<Obj_LichSu> mL_MBA,
			AsyncCallback<CallbackResult> callback);
	void huy_quyetdinh(Obj_User mUS, Obj_QuyetDinh mQD,
			List<Obj_LichSu> mL_MBA, AsyncCallback<CallbackResult> callback);
	void getUSER(AsyncCallback<List<Obj_User>> callback);
	void get_DS_USER(Obj_User oLOCAL_USER,List<Obj_donvi> lDONVI, AsyncCallback<CallbackResult> callback);
	void tao_user(Obj_User mUS, AsyncCallback<CallbackResult> callback);
	void get_QD_CONFIG(String LOAI_QD, AsyncCallback<CallbackResult> callback);
	void update_CONFIG(Obj_Config oCONFIG,
			AsyncCallback<CallbackResult> callback);
	void update_USER(Obj_User oUSER, AsyncCallback<CallbackResult> callback);
	void check_tontai_USER(Obj_User oUSER,AsyncCallback<CallbackResult> callback);
	void xoa_user(Obj_User mUS, AsyncCallback<CallbackResult> callback);
	void check_tontai_LS_OF_USER(Obj_User oUSER,
			AsyncCallback<CallbackResult> callback);
}
