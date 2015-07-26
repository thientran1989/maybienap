package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.Label;

public class Tao_NhanVien extends PopupPanel {

	private static Tao_NhanVienUiBinder uiBinder = GWT
			.create(Tao_NhanVienUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);

	interface Tao_NhanVienUiBinder extends UiBinder<Widget, Tao_NhanVien> {
	}
	List<Obj_donvi> lLOCAL_DONVI=null;
	Obj_User oLOCAL_USER =null;
	int LOCAL_lenh=0;
	public Tao_NhanVien(int lenh,List<Obj_donvi> lDONVI,Obj_User oUSER) {
		lLOCAL_DONVI = new ArrayList<Obj_donvi>(lDONVI);
		oLOCAL_USER = oUSER;
		LOCAL_lenh = lenh;
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		setcombo_donvi(lLOCAL_DONVI);
		if(lenh==Utils.SUA){
			tv_title.setText("SỬA NGƯỜI DÙNG");
			btn_xacnhan.setText("CẬP NHẬT");
			edt_USERNAME.setEnabled(false);
			edt_USERNAME.setText(oUSER.getUsername_mba());
			edt_HOTEN.setText(oUSER.getTen_nhanvien());
			edt_MATKHAU.setText(oUSER.getPassword());
			
			for (int i=0;i<lLOCAL_DONVI.size();i++) {
				if(lLOCAL_DONVI.get(i).getMa_donvi().equals(oLOCAL_USER.getMa_donvi())){
					cbx_DONVI.setSelectedIndex(i);
					break;
				}
			}
		}
	}

	@UiField
	Button btn_huy;
	@UiField Button btn_xacnhan;
	@UiField TextBox edt_HOTEN;
	@UiField TextBox edt_USERNAME;
	@UiField TextBox edt_MATKHAU;
	@UiField ListBox cbx_DONVI;
	@UiField Label tv_title;

	@UiHandler("btn_huy")
	void onClick(ClickEvent e) {
		Tao_NhanVien.this.hide();
	}

	@UiHandler("btn_xacnhan")
	void onBtn_xacnhanClick(ClickEvent event) {
		final Obj_User oUSER = new Obj_User();
		oUSER.setUsername_mba(edt_USERNAME.getText().toString());
		oUSER.setTen_nhanvien(edt_HOTEN.getText().toString());
		oUSER.setPassword(edt_MATKHAU.getText().toString());
		oUSER.setMa_donvi(lLOCAL_DONVI.get(cbx_DONVI.getSelectedIndex()).ma_donvi);
		if(LOCAL_lenh==Utils.SUA){
			oLOCAL_USER.setTen_nhanvien(edt_HOTEN.getText().toString());
			oLOCAL_USER.setPassword(edt_MATKHAU.getText().toString());
			mIodata.update_USER(oLOCAL_USER, new AsyncCallback<CallbackResult>() {
				public void onFailure(Throwable caught) {
					SC.say("Lỗi sửa user : \n"+caught.toString());
				}
				public void onSuccess(CallbackResult result) {
					if(result.getResultString().equals(Utils.CB_OK)){
						SC.say("Sửa user thành công !");
						Tao_NhanVien.this.hide();
					}else{
						SC.say(result.getResultString());
					}
					
				}
			});
		}else if(LOCAL_lenh==Utils.TAO){
			SC.confirm("Bạn có muốn tạo user không ?", new BooleanCallback() {
				public void execute(Boolean value) {
					if (value != null && value) {
						mIodata.check_tontai_USER(oUSER, new AsyncCallback<CallbackResult>() {
							public void onFailure(Throwable caught) {
								SC.say("Lỗi check user : \n"+caught.toString());
							}
							public void onSuccess(CallbackResult result) {
								if(result.getResultString().equals(Utils.CB_OK)){
									mIodata.tao_user(oUSER, new AsyncCallback<CallbackResult>() {
										public void onFailure(Throwable caught) {
											SC.say("Lỗi tạo user : \n"+caught.toString());
										}
										public void onSuccess(CallbackResult result) {
											if(result.getResultString().equals(Utils.CB_OK)){
												SC.say("Tạo user thành công !");
												Tao_NhanVien.this.hide();
											}else{
												SC.say(result.getResultString());
											}
										}
									});
								}else if(result.getResultString().equals(Utils.CB_TONTAI)){
									SC.say(oUSER.getUsername_mba()+" đã tồn tại !");
								}else{
									SC.say("success \n "+result.getResultString());
								}
								
							}
						});
					}
				}
			});
		}
	}
	public void setcombo_donvi(List<Obj_donvi> ls_dvi) {
		if (ls_dvi != null) {
			for (Obj_donvi dv : ls_dvi) {
				cbx_DONVI.addItem(dv.getTen_donvi());
			}
		}
	}
}
