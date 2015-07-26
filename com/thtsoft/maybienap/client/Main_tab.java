package com.thtsoft.maybienap.client;


import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.Label;

public class Main_tab extends Composite {

	private static Main_tabUiBinder uiBinder = GWT
			.create(Main_tabUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT
			.create(IOData2Server.class);
	@UiField TabPanel tab_main;
	@UiField Label tv_user;
	List<Obj_donvi> ls_donvi=null;

	interface Main_tabUiBinder extends UiBinder<Widget, Main_tab> {
	}

	public Main_tab(final Obj_User mUser) {
		initWidget(uiBinder.createAndBindUi(this));
		
			mIodata.getDVI2(Utils.LENH_GET_DSDVI, new AsyncCallback<CallbackResult>() {
				public void onFailure(Throwable caught) {
					Window.alert("loi "+caught.toString());
				}
				@SuppressWarnings("unchecked")
				public void onSuccess(CallbackResult result) {
					ls_donvi = (List<Obj_donvi>) result.getResultObj();
					if (ls_donvi!=null){
						add_tab(mUser,ls_donvi);
						tv_user.setText("Xin chào "+mUser.getTen_nhanvien());
					}
				}
			});	
	}
	public void add_tab(Obj_User mUS,List<Obj_donvi> lsdv){
		tab_main.add(new DanhSach_MBA(mUS,lsdv),"Máy Biến Áp");
		if (mUS.get_cap_dvi(lsdv).equals(Utils.CAP_CONGTY)){
			tab_main.add(new DanhSach_User(mUS,lsdv),  "Người dùng");
			tab_main.add(new DieuDong(mUS,lsdv),  "Điều động");
			tab_main.add(new DieuDong_SuaChua(mUS,lsdv),  "Điều động sữa chữa");
			tab_main.add(new ThanhLy(mUS,lsdv),"Thanh Lý");
			tab_main.add(new Loc_MBA(mUS,lsdv),"Lọc MBA");
			tab_main.add(new TimKiem_BienBan(mUS,lsdv),    "Tìm kiếm QĐ ");
		}
		tab_main.selectTab(0);
	}


}
