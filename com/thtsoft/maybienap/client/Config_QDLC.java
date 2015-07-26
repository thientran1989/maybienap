package com.thtsoft.maybienap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextArea;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_Config;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.TextBox;

public class Config_QDLC extends PopupPanel {

	private static Config_QDLCUiBinder uiBinder = GWT
			.create(Config_QDLCUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);

	interface Config_QDLCUiBinder extends UiBinder<Widget, Config_QDLC> {
	}
	public Config_QDLC() {
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		mIodata.get_QD_CONFIG("", new AsyncCallback<CallbackResult>() {
			public void onSuccess(CallbackResult result) {
				Obj_Config oCONFIG = result.getoCONFIG();
				if(oCONFIG!=null){
					edt_CANCU1.setText(oCONFIG.getCancu_title_1());
					edt_CANCU2.setText(oCONFIG.getCancu_title_2());
					edt_DIEU3.setText(oCONFIG.getCancu_title_3());
					edt_DIEU4.setText(oCONFIG.getCancu_dieu_4());
					edt_luuten.setText(oCONFIG.getLuu_ten());
					edt_chucvu_2.setText(oCONFIG.getChuc_vu_2());
					edt_ten_2.setText(oCONFIG.getHo_ten_2());
				}
			}
			public void onFailure(Throwable caught) {
				SC.say("Lỗi : "+caught.toString());
				
			}
		});
	}

	@UiField
	Button btn_XACNHAN;
	@UiField TextArea edt_CANCU1;
	@UiField TextArea edt_CANCU2;
	@UiField TextArea edt_DIEU3;
	@UiField TextArea edt_DIEU4;
	@UiField Button btn_HUY;
	@UiField TextBox edt_luuten;
	@UiField TextArea edt_chucvu_2;
	@UiField TextBox edt_ten_2;


	@UiHandler("btn_XACNHAN")
	void onClick(ClickEvent e) {
		final Obj_Config oCONFIG = new Obj_Config();
		oCONFIG.setCancu_title_1(edt_CANCU1.getText().toString());
		oCONFIG.setCancu_title_2(edt_CANCU2.getText().toString());
		oCONFIG.setCancu_title_3(edt_DIEU3.getText().toString());
		oCONFIG.setCancu_dieu_4(edt_DIEU4.getText().toString());
		oCONFIG.setLuu_ten(edt_luuten.getText().toString());
		oCONFIG.setHo_ten_2(edt_ten_2.getText().toString());
		oCONFIG.setChuc_vu_2(edt_chucvu_2.getText().toString());
		SC.confirm("Bạn có muốn thay đổi cấu hình mẫu biểu in không ?", new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
					mIodata.update_CONFIG(oCONFIG, new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							SC.say("Lỗi cập nhật mẫu in : \n"+caught.toString());
						}

						public void onSuccess(CallbackResult result) {
							if(result.getResultString().equals(Utils.CB_OK)){
								SC.say("Sửa mẫu in thành công !");
								Config_QDLC.this.hide();
							}else{
								SC.say(result.getResultString());
							}
							
						}
					});
				}
			}
		});
	}

	@UiHandler("btn_HUY")
	void onBtn_HUYClick(ClickEvent event) {
		Config_QDLC.this.hide();
	}
}
