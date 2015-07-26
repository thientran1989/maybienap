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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_Text;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class Tao_MBA extends PopupPanel {
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);

	private static Tao_MBAUiBinder uiBinder = GWT.create(Tao_MBAUiBinder.class);
	@UiField
	SimplePanel pane_trolai;
	@UiField
	SimplePanel pane_hoantat;
	@UiField
	TextBox edt_SOMAY;
	@UiField
	TextBox edt_NHA_SANXUAT;
	@UiField
	TextBox edt_DIENAP_NGANMACH;
	@UiField
	TextBox edt_TONTHAT_KHONGTAI;
	@UiField
	TextBox edt_LOAIDAU;
	@UiField
	TextBox edt_CHU_SOHUU;
	@UiField
	TextBox edt_TRONGLUONG;
	@UiField
	TextBox edt_KICHTHUOC;
	@UiField
	TextBox edt_NGUYENGIA;
	@UiField
	TextBox edt_TRAM;
	@UiField
	TextBox edt_MSTS;
	@UiField
	TextBox edt_NUOC_SANXUAT;
	@UiField
	TextBox edt_SOSU_THUCAP;
	@UiField
	TextBox edt_DONGDIEN_KHONGTAI;
	@UiField
	TextBox edt_TONTHAT_COTAI;
	@UiField
	TextBox edt_KHOILUONG_DAU;
	@UiField
	TextBox edt_NGUONGOC_TAISAN;
	@UiField
	ListBox cbx_kho;
	@UiField
	ListBox cbx_pha;
	@UiField
	ListBox cbx_tinhtrang_sudung;
	@UiField
	Label tv_hoantat;
	@UiField
	ListBox cbx_congsuat;
	@UiField
	ListBox cbx_sosu_socap;
	@UiField
	ListBox cbx_nac_vanhanh;
	@UiField
	ListBox cbx_dienap_dinhmuc;
	@UiField
	ListBox cbx_to_dauday;
	@UiField
	TextBox edt_ten_tscd;
	@UiField
	TextBox edt_bb_tnghiem;
	@UiField
	DateBox mdate_box;
	Obj_User mLocal_user;
	List<Obj_Text> list_tinhtrang;
	List<Obj_Text> list_pha;
	List<Obj_Text> list_cs;
	List<Obj_Text> list_dienap;
	List<Obj_Text> list_sosu_socap;
	List<Obj_Text> list_nac_vanhanh;
	List<Obj_Text> list_to_dauday;

	interface Tao_MBAUiBinder extends UiBinder<Widget, Tao_MBA> {
	}

	public Tao_MBA(Obj_User mUser, final int lenh,
			final List<Obj_donvi> mL_DVI, final Obj_LichSu mMBA) {
		mLocal_user = mUser;
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		setcombo_donvi(mUser,mL_DVI);
		set_combo_pha();
		set_combo_tinhtrang_sudung();
		set_combo_congsuat();
		set_combo_dienap();
		set_combo_sosu_socap();
		set_combo_nac_vanhanh();
		set_combo_to_dauday();
		edt_SOSU_THUCAP.setText("4");
		edt_SOSU_THUCAP.setEnabled(false);
		if(mUser.get_cap_dvi(mL_DVI).equals(Utils.CAP_CONGTY)){
			cbx_kho.setEnabled(true);
		}else{
			cbx_kho.setEnabled(false);
		}
		
		// date picker
		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				mdate_box.setValue(date);
			}
		});
		datePicker.setValue(new Date(), true);
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		mdate_box.setFormat(new DateBox.DefaultFormat(dateFormat));
		if (lenh == Utils.SUA) {
			tv_hoantat.setText("Cập Nhật");
			edt_SOMAY.setEnabled(false);
			if (mMBA != null) {
				set_value(mMBA);
			}
			cbx_kho.setEnabled(false);

		}
		pane_trolai.sinkEvents(Event.ONCLICK);
		pane_trolai.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Tao_MBA.this.hide();
			}

		}, ClickEvent.getType());

		pane_hoantat.sinkEvents(Event.ONCLICK);
		pane_hoantat.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (lenh == Utils.TAO) {
					show_confirm("Bạn có muốn tạo máy biến áp không ?",
							lenh, mL_DVI,null);
				} else {
					if (lenh == Utils.SUA) {
						show_confirm(
								"Bạn có muốn cập nhật thông tin máy biến áp không ?",
								lenh, mL_DVI,mMBA);
					}
				}

			}

		}, ClickEvent.getType());
	}

	public Obj_LichSu get_maybienap_tao(List<Obj_donvi> mL_DVI, int lenh) {
		Obj_LichSu mMBA = new Obj_LichSu();
		try {
			mMBA.setKho(mL_DVI.get(cbx_kho.getSelectedIndex()).getMa_donvi());
			mMBA.setSo_may(edt_SOMAY.getText().toString());
			mMBA.setNha_sanxuat(edt_NHA_SANXUAT.getText().toString());
			// mMBA.setNgay_vanhanh(edt_NGAY_VANHANH.getText().toString());
			try {
				Date date = mdate_box.getValue();
				mMBA.setNgay_vanhanh(Client_function.date2timestamp(date));
			} catch (Exception e) {
				SC.say("Lỗi : " + e.toString());
			}
			mMBA.setPha(list_pha.get(cbx_pha.getSelectedIndex()).KEY);
			mMBA.setSoxu_socap(list_sosu_socap.get(cbx_sosu_socap
					.getSelectedIndex()).KEY);
			mMBA.setDien_ap_nganmach(edt_DIENAP_NGANMACH.getText().toString());
			mMBA.setTonthat_khongtai(edt_TONTHAT_KHONGTAI.getText().toString());
			mMBA.setNac_vanhanh(list_nac_vanhanh.get(cbx_nac_vanhanh
					.getSelectedIndex()).KEY);
			mMBA.setLoai_dau(edt_LOAIDAU.getText().toString());
			mMBA.setChu_sohuu(edt_CHU_SOHUU.getText().toString());
			mMBA.setTrong_luong(edt_TRONGLUONG.getText().toString());
			mMBA.setKich_thuoc(edt_KICHTHUOC.getText().toString());
			mMBA.setTram(edt_TRAM.getText().toString());
			mMBA.setMSTS(edt_MSTS.getText().toString());
			mMBA.setNuoc_sanxuat(edt_NUOC_SANXUAT.getText().toString());
			mMBA.setCong_suat(list_cs.get(cbx_congsuat.getSelectedIndex()).KEY);
			mMBA.setDienap_dinhmuc(list_dienap.get(cbx_dienap_dinhmuc
					.getSelectedIndex()).KEY);
			mMBA.setSoxu_thucap(edt_SOSU_THUCAP.getText().toString());
			mMBA.setDongdien_khongtai(edt_DONGDIEN_KHONGTAI.getText()
					.toString());
			mMBA.setTonthat_cotai(edt_TONTHAT_COTAI.getText().toString());
			mMBA.setTo_dauday(list_to_dauday.get(cbx_to_dauday
					.getSelectedIndex()).KEY);
			mMBA.setKhoiluong_dau(edt_KHOILUONG_DAU.getText().toString());
			mMBA.setTinhtrang_sudung(list_tinhtrang.get(cbx_tinhtrang_sudung
					.getSelectedIndex()).KEY);
			mMBA.setTen_tscd(edt_ten_tscd.getText().toString());
			mMBA.setBb_tnghiem(edt_bb_tnghiem.getText().toString());
			for (Obj_donvi obj_donvi : mL_DVI) {
				if (obj_donvi.getMa_donvi().equals(mLocal_user.getMa_donvi())) {
					mMBA.setX_chuan(obj_donvi.getX_chuan());
					mMBA.setY_chuan(obj_donvi.getY_chuan());
					mMBA.setX_tam(obj_donvi.getX_chuan());
					mMBA.setY_tam(obj_donvi.getY_chuan());
					break;
				}
			}
			if (lenh == Utils.TAO) {
				mMBA.setMadv_from(mL_DVI.get(cbx_kho.getSelectedIndex())
						.getMa_donvi());
				mMBA.setMadv_to(mL_DVI.get(cbx_kho.getSelectedIndex())
						.getMa_donvi());
				mMBA.setLoai_dieudong(Utils.LS_TAO);
				mMBA.setQD_so("");
			} else {
				mMBA.setLoai_dieudong(Utils.LS_SUA);
			}
		} catch (Exception e) {

		}

		return mMBA;
	}

	public Obj_LichSu get_maybienap_sua(List<Obj_donvi> mL_DVI, Obj_LichSu mMBA) {
		try {
			mMBA.setKho(mL_DVI.get(cbx_kho.getSelectedIndex()).getMa_donvi());
			mMBA.setSo_may(edt_SOMAY.getText().toString());
			mMBA.setNha_sanxuat(edt_NHA_SANXUAT.getText().toString());
			// mMBA.setNgay_vanhanh(edt_NGAY_VANHANH.getText().toString());
			try {
				Date date = mdate_box.getValue();
				mMBA.setNgay_vanhanh(Client_function.date2timestamp(date));
			} catch (Exception e) {
				SC.say("Lỗi : " + e.toString());
			}
			mMBA.setPha(list_pha.get(cbx_pha.getSelectedIndex()).KEY);
			mMBA.setSoxu_socap(list_sosu_socap.get(cbx_sosu_socap
					.getSelectedIndex()).KEY);
			mMBA.setDien_ap_nganmach(edt_DIENAP_NGANMACH.getText().toString());
			mMBA.setTonthat_khongtai(edt_TONTHAT_KHONGTAI.getText().toString());
			mMBA.setNac_vanhanh(list_nac_vanhanh.get(cbx_nac_vanhanh
					.getSelectedIndex()).KEY);
			mMBA.setLoai_dau(edt_LOAIDAU.getText().toString());
			mMBA.setChu_sohuu(edt_CHU_SOHUU.getText().toString());
			mMBA.setTrong_luong(edt_TRONGLUONG.getText().toString());
			mMBA.setKich_thuoc(edt_KICHTHUOC.getText().toString());
			mMBA.setTram(edt_TRAM.getText().toString());
			mMBA.setMSTS(edt_MSTS.getText().toString());
			mMBA.setNuoc_sanxuat(edt_NUOC_SANXUAT.getText().toString());
			mMBA.setCong_suat(list_cs.get(cbx_congsuat.getSelectedIndex()).KEY);
			mMBA.setDienap_dinhmuc(list_dienap.get(cbx_dienap_dinhmuc
					.getSelectedIndex()).KEY);
			mMBA.setSoxu_thucap(edt_SOSU_THUCAP.getText().toString());
			mMBA.setDongdien_khongtai(edt_DONGDIEN_KHONGTAI.getText()
					.toString());
			mMBA.setTonthat_cotai(edt_TONTHAT_COTAI.getText().toString());
			mMBA.setTo_dauday(list_to_dauday.get(cbx_to_dauday
					.getSelectedIndex()).KEY);
			mMBA.setKhoiluong_dau(edt_KHOILUONG_DAU.getText().toString());
			mMBA.setTinhtrang_sudung(list_tinhtrang.get(cbx_tinhtrang_sudung
					.getSelectedIndex()).KEY);
			mMBA.setTen_tscd(edt_ten_tscd.getText().toString());
			mMBA.setBb_tnghiem(edt_bb_tnghiem.getText().toString());
			for (Obj_donvi obj_donvi : mL_DVI) {
				if (obj_donvi.getMa_donvi().equals(mLocal_user.getMa_donvi())) {
					mMBA.setX_chuan(obj_donvi.getX_chuan());
					mMBA.setY_chuan(obj_donvi.getY_chuan());
					mMBA.setX_tam(obj_donvi.getX_chuan());
					mMBA.setY_tam(obj_donvi.getY_chuan());
					break;
				}
			}
			mMBA.setLoai_dieudong(Utils.LS_SUA);
		} catch (Exception e) {

		}

		return mMBA;
	}

	public void set_combo_pha() {
		list_pha = new ArrayList<Obj_Text>();
		list_pha.add(new Obj_Text("1", "1 Pha"));
		list_pha.add(new Obj_Text("3", "3 Pha"));
		for (Obj_Text oPha : list_pha) {
			cbx_pha.addItem(oPha.NAME);
		}
	}

	public void set_combo_tinhtrang_sudung() {
		list_tinhtrang = new ArrayList<Obj_Text>();
		list_tinhtrang.add(new Obj_Text("DDK", "Đủ điều kiện vận hành"));
		list_tinhtrang
				.add(new Obj_Text("CSCBD", "Chờ sữa chữa bảo dưỡng"));
		list_tinhtrang
				.add(new Obj_Text("DSCBD", "Đang sữa chữa bảo dưỡng"));
		list_tinhtrang.add(new Obj_Text("CTL", "Chờ thanh lý"));
		list_tinhtrang.add(new Obj_Text("DTL", "Đã thanh lý"));
		for (Obj_Text oTT : list_tinhtrang) {
			cbx_tinhtrang_sudung.addItem(oTT.NAME);
		}
	}

	public void set_combo_congsuat() {
		list_cs = new ArrayList<Obj_Text>();
		list_cs.add(new Obj_Text("5", "5kVA"));
		list_cs.add(new Obj_Text("10", "10kVA"));
		list_cs.add(new Obj_Text("15", "15kVA"));
		list_cs.add(new Obj_Text("25", "25kVA"));
		list_cs.add(new Obj_Text("37.5", "37.5kVA"));
		list_cs.add(new Obj_Text("50", "50kVA"));
		list_cs.add(new Obj_Text("75", "75kVA"));
		list_cs.add(new Obj_Text("100", "100kVA"));
		list_cs.add(new Obj_Text("160", "160kVA"));
		list_cs.add(new Obj_Text("180", "180kVA"));
		list_cs.add(new Obj_Text("250", "250kVA"));
		list_cs.add(new Obj_Text("320", "320kVA"));
		list_cs.add(new Obj_Text("400", "400kVA"));
		list_cs.add(new Obj_Text("560", "560kVA"));
		list_cs.add(new Obj_Text("630", "630kVA"));
		list_cs.add(new Obj_Text("750", "750kVA"));
		list_cs.add(new Obj_Text("800", "800kVA"));
		list_cs.add(new Obj_Text("1000", "1000kVA"));
		list_cs.add(new Obj_Text("1250", "1250kVA"));
		list_cs.add(new Obj_Text("1600", "1600kVA"));
		for (Obj_Text oTT : list_cs) {
			cbx_congsuat.addItem(oTT.NAME);
		}

	}

	public void set_combo_dienap() {
		list_dienap = new ArrayList<Obj_Text>();
		list_dienap.add(new Obj_Text("12.7/0.23-0.46", "12.7/0.23-0.46kV"));
		list_dienap.add(new Obj_Text("22/04", "22/04 kV"));
		for (Obj_Text oTT : list_dienap) {
			cbx_dienap_dinhmuc.addItem(oTT.NAME);
		}
	}

	public void set_combo_sosu_socap() {
		list_sosu_socap = new ArrayList<Obj_Text>();
		list_sosu_socap.add(new Obj_Text("1", "1"));
		list_sosu_socap.add(new Obj_Text("2", "2"));
		list_sosu_socap.add(new Obj_Text("3", "3"));
		for (Obj_Text oTT : list_sosu_socap) {
			cbx_sosu_socap.addItem(oTT.NAME);
		}
	}

	public void set_combo_nac_vanhanh() {
		list_nac_vanhanh = new ArrayList<Obj_Text>();
		list_nac_vanhanh.add(new Obj_Text("-5", "1 -5%"));
		list_nac_vanhanh.add(new Obj_Text("-2.5", "2 -2.5%"));
		list_nac_vanhanh.add(new Obj_Text("100", "3 100%"));
		list_nac_vanhanh.add(new Obj_Text("+2.5", "4 +2.5 %"));
		list_nac_vanhanh.add(new Obj_Text("+5", "5 +5%"));
		for (Obj_Text oTT : list_nac_vanhanh) {
			cbx_nac_vanhanh.addItem(oTT.NAME);
		}
	}

	public void set_combo_to_dauday() {
		list_to_dauday = new ArrayList<Obj_Text>();
		list_to_dauday.add(new Obj_Text("Dyn11", "Dyn11"));
		list_to_dauday.add(new Obj_Text("I/i-0", "I/i-0"));
		list_to_dauday.add(new Obj_Text("I/i-6", "I/i-6"));
		for (Obj_Text oTT : list_to_dauday) {
			cbx_to_dauday.addItem(oTT.NAME);
		}
	}

	public void setcombo_donvi(Obj_User oUSER,List<Obj_donvi> ls_dvi) {
		if (ls_dvi != null) {
			for (Obj_donvi dv : ls_dvi) {
				cbx_kho.addItem(dv.getTen_donvi());
			}
			try {
				for (int i = 0; i < ls_dvi.size(); i++) {
					if ((ls_dvi.get(i).ma_donvi).equals(oUSER.getMa_donvi())) {
						cbx_kho.setItemSelected(i, true);
						break;
					}
				}
			} catch (Exception e) {

			}
		}
		
	}

	public void set_value(Obj_LichSu mmBA) {
		cbx_kho.setEnabled(false);
		try {
			for (int i = 0; i < list_pha.size(); i++) {
				if ((list_pha.get(i).KEY).equals(mmBA.getPha())) {
					cbx_pha.setItemSelected(i, true);
					set_congsuat_sua(mmBA, mmBA.getPha());
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			for (int i = 0; i < list_tinhtrang.size(); i++) {
				if ((list_tinhtrang.get(i).KEY).equals(mmBA
						.getTinhtrang_sudung())) {
					cbx_tinhtrang_sudung.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			for (int i = 0; i < list_cs.size(); i++) {
				if ((list_cs.get(i).KEY).equals(mmBA.getCong_suat())) {
					cbx_congsuat.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			for (int i = 0; i < list_dienap.size(); i++) {
				if ((list_dienap.get(i).KEY).equals(mmBA.getDienap_dinhmuc())) {
					cbx_dienap_dinhmuc.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			for (int i = 0; i < list_sosu_socap.size(); i++) {
				if ((list_sosu_socap.get(i).KEY).equals(mmBA.getSoxu_socap())) {
					cbx_sosu_socap.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			for (int i = 0; i < list_nac_vanhanh.size(); i++) {
				if ((list_nac_vanhanh.get(i).KEY).equals(mmBA.getNac_vanhanh())) {
					cbx_nac_vanhanh.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			for (int i = 0; i < list_to_dauday.size(); i++) {
				if ((list_to_dauday.get(i).KEY).equals(mmBA.getTo_dauday())) {
					cbx_to_dauday.setItemSelected(i, true);
					break;
				}
			}
		} catch (Exception e) {

		}
		try {
			edt_SOMAY.setText(mmBA.getSo_may());
			edt_NHA_SANXUAT.setText(mmBA.getNha_sanxuat());
			mdate_box.setValue(new Date(mmBA.getNgay_vanhanh().getTime()));
			edt_DIENAP_NGANMACH.setText(mmBA.getDien_ap_nganmach());
			edt_TONTHAT_KHONGTAI.setText(mmBA.getTonthat_khongtai());
			edt_LOAIDAU.setText(mmBA.getLoai_dau());
			edt_CHU_SOHUU.setText(mmBA.getChu_sohuu());
			edt_TRONGLUONG.setText(mmBA.getTrong_luong());
			edt_KICHTHUOC.setText(mmBA.getKich_thuoc());
			edt_TRAM.setText(mmBA.getTram());
			edt_MSTS.setText(mmBA.getMSTS());
			edt_NUOC_SANXUAT.setText(mmBA.getNuoc_sanxuat());
			edt_SOSU_THUCAP.setText(mmBA.getSoxu_thucap());
			edt_DONGDIEN_KHONGTAI.setText(mmBA.getDongdien_khongtai());
			edt_TONTHAT_COTAI.setText(mmBA.getTonthat_cotai());
			edt_KHOILUONG_DAU.setText(mmBA.getKhoiluong_dau());
			edt_ten_tscd.setText(mmBA.getTen_tscd());
			edt_bb_tnghiem.setText(mmBA.getBb_tnghiem());
		} catch (Exception e) {

		}
	}

	public void set_congsuat_sua(Obj_LichSu mMBA, String pha) {
		for (int i = 0; i < list_cs.size(); i++) {
			if ((list_cs.get(i).KEY).equals(mMBA.getCong_suat())) {
				cbx_congsuat.setItemSelected(i, true);
				break;
			}
		}
	}
	public void show_confirm(String message, final int lenh,
			final List<Obj_donvi> mL_DVI,final Obj_LichSu oLS) {
		SC.confirm(message, new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
					final Obj_LichSu mMBA = get_maybienap_tao(mL_DVI, lenh);
					if (mMBA.getSo_may().length() > 0
							|| mMBA.getTen_tscd().length() > 0
							|| mMBA.getNha_sanxuat().length() > 0) {
						if (lenh == Utils.TAO) {
							mMBA.setUser_taomba(mLocal_user.getUsername_mba());
							mMBA.setUser_suamba(mLocal_user.getUsername_mba());
							mMBA.setLoai_history(Utils.LS_TAO);
							mIodata.check_tontai_MBA(mMBA.getSo_may(),
									new AsyncCallback<String>() {
										public void onFailure(Throwable caught) {
											SC.say("Lỗi check tồn tại :\n"
													+ caught.toString());
										}
										public void onSuccess(String result) {
											if (result.equals(Utils.CB_OK)) {
												mIodata.tao_lichsu(
														mLocal_user,
														mMBA,
														new AsyncCallback<CallbackResult>() {
															public void onSuccess(
																	CallbackResult result) {
																if (result
																		.getResultString()
																		.equals(Utils.CB_OK)) {
																	SC.say("Thêm dữ liệu thành công !");
																	Tao_MBA.this
																			.hide();
																} else {
																	SC.say("Lỗi : "
																			+ result.getResultString());
																}
															}

															public void onFailure(
																	Throwable caught) {
																SC.say("Lỗi tạo máy :\n"
																		+ caught.toString());
															}
														});
											} else {
												SC.say("Số máy này đã tồn tại !");
											}
										}
									});
						} else if (lenh == Utils.SUA) {
							oLS.setUser_suamba(mLocal_user.getUsername_mba());
							oLS.setLoai_history(Utils.LS_SUA);
							mIodata.update_MBA(get_maybienap_sua(mL_DVI,oLS),
									new AsyncCallback<CallbackResult>() {
										public void onFailure(Throwable caught) {
											SC.say("Lỗi sửa máy biến áp :\n"
													+ caught.toString());
										}
										public void onSuccess(
												CallbackResult result) {
											if(result.getResultString().equals(Utils.CB_OK)){
												SC.say("Đã cập nhật thông tin thành công !");
												Tao_MBA.this
												.hide();
											}else{
												SC.say(result.getResultString());
											}
											
										}
									});
						}
					} else {
						Window.alert("Phải nhập thông tin số máy !");
					}
				}
			}
		});
	}
	
}
