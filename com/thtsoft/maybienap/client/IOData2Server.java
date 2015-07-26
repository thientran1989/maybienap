package com.thtsoft.maybienap.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;

@RemoteServiceRelativePath("d2s")
public interface IOData2Server extends RemoteService {
//	UserLogin checkLogin(UserLogin UL);
//	List<ObjDonVi> getDSDonVi();
	Obj_User login(Obj_User mUS) throws IllegalArgumentException;
	List<Obj_LichSu> getMBA() throws IllegalArgumentException;//Co Method nay thi CallbackResult moi Serialize duoc Doi tuong List<?>
	List<Obj_QuyetDinh> getBB() throws IllegalArgumentException;
	List<Obj_LichSu> getLS() throws IllegalArgumentException;
	List<Obj_donvi> getDVI() throws IllegalArgumentException;
	List<Obj_User> getUSER() throws IllegalArgumentException;
	CallbackResult getMBA2(int lenh) throws IllegalArgumentException;
	CallbackResult getDVI2(int lenh) throws IllegalArgumentException;
	CallbackResult tao_lichsu(Obj_User mUS,Obj_LichSu mLS) throws IllegalArgumentException;
	CallbackResult tao_user(Obj_User mUS) throws IllegalArgumentException;
	CallbackResult tao_quyetdinh(Obj_User mUS,Obj_QuyetDinh mQD) throws IllegalArgumentException;
	// object may
	Obj_LichSu get_MBA(String so_may,String DVI) throws IllegalArgumentException;
	CallbackResult update_MBA(Obj_LichSu mBA) throws IllegalArgumentException;
	String check_tontai_MBA(String mBA) throws IllegalArgumentException;
	CallbackResult check_tontai_QD(String QD) throws IllegalArgumentException;
	CallbackResult xoa_lichsu(Obj_User mUS,Obj_LichSu mLS) throws IllegalArgumentException;
	CallbackResult getBB_SEARCH(String sobb,String DVI) throws IllegalArgumentException;
	CallbackResult update_DD_MBA(Obj_LichSu oMBA) throws IllegalArgumentException;
	CallbackResult get_LICHSU_OF_QD(String QD) throws IllegalArgumentException;
	CallbackResult xoa_QD(Obj_User mUS,Obj_QuyetDinh oQD) throws IllegalArgumentException;
	CallbackResult check_tontai_LS_OF_QD(String QD) throws IllegalArgumentException;
	CallbackResult get_DSMBA_LOC(String donvi,String tinhtrang,String key) throws IllegalArgumentException;
	CallbackResult get_LICHSU_OF_MBA(Obj_LichSu mMBA) throws IllegalArgumentException;
	CallbackResult hoantat_quyetdinh(Obj_User mUS,Obj_QuyetDinh mQD,List<Obj_LichSu> mL_MBA) throws IllegalArgumentException;
	CallbackResult huy_quyetdinh(Obj_User mUS,Obj_QuyetDinh mQD,List<Obj_LichSu> mL_MBA) throws IllegalArgumentException;
	CallbackResult get_DS_USER(Obj_User donvi,List<Obj_donvi> lDONVI) throws IllegalArgumentException;
	CallbackResult get_QD_CONFIG(String LOAI_QD) throws IllegalArgumentException;
	CallbackResult update_CONFIG(Obj_Config oCONFIG) throws IllegalArgumentException;
	CallbackResult update_USER(Obj_User oUSER) throws IllegalArgumentException;
	CallbackResult check_tontai_USER(Obj_User oUSER) throws IllegalArgumentException;
	CallbackResult xoa_user(Obj_User mUS) throws IllegalArgumentException;
	CallbackResult check_tontai_LS_OF_USER(Obj_User oUSER) throws IllegalArgumentException;
}
