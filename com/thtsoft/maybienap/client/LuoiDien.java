package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_Text;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Utils;

public class LuoiDien extends PopupPanel {

	private static LuoiDienUiBinder uiBinder = GWT
			.create(LuoiDienUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField
	TextBox edt_LYDO;
	@UiField
	SimplePanel pane_hoantat;
	@UiField
	TextBox edt_SOMAY;
	@UiField
	TextBox edt_TRAM;
	@UiField
	TextBox edt_TUYEN;
	@UiField
	TextBox edt_TRU;
	@UiField
	TextBox edt_DONGTAI;
	@UiField
	VerticalPanel ver_THONGTIN;
	@UiField
	DateBox mDatebox_ngay_vanhanh;
	@UiField
	ListBox cbx_nac_vanhanh;
	@UiField
	SimplePanel pane_huybo;
	Obj_User mLocal_user;
	List<Obj_Text> list_nac_vanhanh;

	interface LuoiDienUiBinder extends UiBinder<Widget, LuoiDien> {
	}

	public LuoiDien(Obj_User mUser, final Obj_LichSu mMBA) {
		mLocal_user = mUser;
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		set_combo_nac_vanhanh();
		set_value(mMBA);

		// date picker
		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				mDatebox_ngay_vanhanh.setValue(date);
			}
		});
		datePicker.setValue(new Date(), true);
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		mDatebox_ngay_vanhanh.setFormat(new DateBox.DefaultFormat(dateFormat));

		pane_hoantat.sinkEvents(Event.ONCLICK);
		pane_hoantat.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				show_confirm_dieudong("Bạn có muốn cập nhật thông tin ?",
						mMBA);
			}
		}, ClickEvent.getType());
		pane_huybo.sinkEvents(Event.ONCLICK);
		pane_huybo.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				LuoiDien.this.hide();
			}

		}, ClickEvent.getType());
	}

	public void get_value(Obj_LichSu mMBA) {
		try {
			Date date = mDatebox_ngay_vanhanh.getValue();
			mMBA.setNgay_vanhanh(Client_function.date2timestamp(date));
		} catch (Exception e) {

		}
		mMBA.setMadv_from(mLocal_user.getMa_donvi());
		mMBA.setMadv_to(mLocal_user.getMa_donvi());
		mMBA.setTram(edt_TRAM.getText().toString());
		mMBA.setTuyen(edt_TUYEN.getText().toString());
		mMBA.setTru(edt_TRU.getText().toString());
		mMBA.setDongdien_khongtai(edt_DONGTAI.getText().toString());
		mMBA.setNac_vanhanh(list_nac_vanhanh.get(cbx_nac_vanhanh.getSelectedIndex()).KEY);
	}

	public void set_value(Obj_LichSu mMBA) {
		try {
			for (int i = 0; i < list_nac_vanhanh.size(); i++) {
				if ((list_nac_vanhanh.get(i).KEY).equals(mMBA.getNac_vanhanh())) {
					cbx_nac_vanhanh.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		edt_SOMAY.setText(mMBA.getSo_may());
		edt_SOMAY.setEnabled(false);
		edt_TRAM.setText(mMBA.getTram());
		edt_TUYEN.setText(mMBA.getTuyen());
		edt_TRU.setText(mMBA.getTru());
		edt_DONGTAI.setText(mMBA.getDongdien_khongtai());
	}

	public void set_combo_nac_vanhanh() {
		list_nac_vanhanh = Utils.get_list_nac_vanhanh();
		for (Obj_Text oTT : list_nac_vanhanh) {
			cbx_nac_vanhanh.addItem(oTT.NAME);
		}
	}

	public void show_confirm_dieudong(String message, final Obj_LichSu mMBA) {
		SC.confirm(message, new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
					LuoiDien.this.hide();
					mMBA.setLoai_history(Utils.LS_LUANCHUYEN_NOIBO);
					mMBA.setUser_suamba(mLocal_user.getUsername_mba());
					get_value(mMBA);
					mMBA.setUser_suamba(mLocal_user.getUsername_mba());
					List<Obj_LichSu> list_LS = new ArrayList<Obj_LichSu>();
					list_LS.add(mMBA);
					mIodata.hoantat_quyetdinh(mLocal_user, null,
							list_LS, new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("lỗi gắn lên lưới :\n "
											+ caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if(result.getResultString().equals(Utils.CB_OK)){
										SC.say("Cập nhật dữ liệu thành công !");
									}else{
										SC.say(result.getResultString());
									}
									
								}
							});
				}
			}
		});
	}
}
