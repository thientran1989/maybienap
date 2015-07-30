package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.My_function;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_Text;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.ThongBao;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.Label;

public class DieuDong extends Composite {

	private static DieuDongUiBinder uiBinder = GWT
			.create(DieuDongUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField
	ListBox cbx_dieudong_from;
	@UiField
	ListBox cbx_dieudong_to;
	@UiField
	TextBox edt_QD_SO;
	@UiField
	TextBox edt_CAN_CU;
	@UiField
	TextBox edt_LYDO;
	@UiField
	SimplePanel pane_hoantat;
	@UiField
	DateBox mdate_box;
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_TimKiem = new CellTable<Obj_LichSu>();
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_LichSu = new CellTable<Obj_LichSu>();
	@UiField
	TextBox edt_timkiem_dieu;
	@UiField
	Button btn_timkiem_dieu;
	@UiField
	SimplePager pager_timkiem;
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_timkiem_cap = new CellTable<Obj_LichSu>();
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_lichsu_cap = new CellTable<Obj_LichSu>();
	@UiField
	Button btn_timkiem_cap;
	@UiField
	TextBox edt_timkiem_cap;
	@UiField
	SimplePager pager_cap;
	@UiField
	VerticalPanel ver_dieudong;
	@UiField
	TextBox edt_tram;
	@UiField
	ListBox cbx_tinhtrang;
	@UiField
	Button btn_xacnhan_QD;
	@UiField
	Button btn_Reset_QD;
	@UiField Label tv_cap;
	@UiField TextBox edt_nguongocts;

	List<Obj_donvi> ls_donvi_from = null;
	List<Obj_donvi> ls_donvi_to = null;
	Obj_User mLocal_User = null;

	boolean isvalid_DIEUDONG = true;
	PopupPanel popup;
	boolean load_timkiem_dieu = false;
	boolean load_timkiem_cap = false;
	boolean load_lichsu_dieu = false;
	boolean load_lichsu_cap = false;
	MyResources resources;
	ListDataProvider<Obj_LichSu> dataProvider_LS;
	public static List<Obj_LichSu> list_LS_dieuve;
	public static List<Obj_LichSu> list_LS_capcho;
	public static List<Obj_LichSu> list_TK_dieuve;
	public static List<Obj_LichSu> list_TK_capcho;
	List<Obj_Text> list_tinhtrang;
	List<Obj_LichSu> list_LS_tam;

	Obj_QuyetDinh oQD_tam;
	final int LOAI_CAP = 1, LOAI_DIEU = 2;
	boolean khoa_QD = false;
	List<Obj_donvi> mLLOCAL_DVI=null;

	interface DieuDongUiBinder extends UiBinder<Widget, DieuDong> {
	}

	public DieuDong(Obj_User mUS, List<Obj_donvi> mL_DVI) {
		mLocal_User = mUS;
		mLLOCAL_DVI = new ArrayList<Obj_donvi>(mL_DVI);
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		btn_Reset_QD.setEnabled(false);
		setcombo_donvi(mL_DVI);
		set_combo_tinhtrang_sudung();
		list_LS_dieuve = new ArrayList<Obj_LichSu>();
		list_LS_capcho = new ArrayList<Obj_LichSu>();
		list_TK_capcho = new ArrayList<Obj_LichSu>();
		list_TK_dieuve = new ArrayList<Obj_LichSu>();
		oQD_tam = new Obj_QuyetDinh();
		list_LS_tam = new ArrayList<Obj_LichSu>();
		edt_nguongocts.setText("TSPCCT (Hiện đặt tại kho ADB)");

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

		pane_hoantat.sinkEvents(Event.ONCLICK);
		pane_hoantat.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(list_LS_capcho.size()>0 || list_LS_dieuve.size()>0){
					show_confirm_dieudong("Bạn có muốn hoàn tất điều động không ?");
				}else{
					SC.say("Phải điều động ít nhất 1 máy !");
				}
				
			}

		}, ClickEvent.getType());

	}

	public void setcombo_donvi(List<Obj_donvi> my_list) {
		if (my_list != null) {
			ls_donvi_from = new ArrayList<Obj_donvi>();
			ls_donvi_to = new ArrayList<Obj_donvi>();
			for (Obj_donvi dv : my_list) {
				if (dv.getCap().equals(Utils.CAP_DIENLUC)) {
					ls_donvi_from.add(dv);
					cbx_dieudong_from.addItem(dv.getTen_donvi());
				} else if (dv.getCap().equals(Utils.CAP_CONGTY)) {
					ls_donvi_to.add(dv);
					cbx_dieudong_to.addItem(dv.getTen_donvi());
				}
			}
		}
	}

	public Obj_LichSu get_lichSu(Obj_LichSu mMBA, Obj_QuyetDinh mQD) {
		Obj_LichSu mLS = mMBA.copy_object();
		if (mQD != null) {
			mLS.setQD_so(mQD.getQD_so());
			mLS.setLoai_history(Utils.LS_LUANCHUYEN_BINHTHUONG);
			mLS.setDa_tra(Utils.CHUA_TRA);
		}
		return mLS;
	}

	public boolean isvalid_quyetdinh() {
		Obj_QuyetDinh mQD = get_QD();
		boolean kq = true;
		if (mQD.getQD_so().length() == 0 || mQD.getCan_cu().length() == 0
				|| mQD.getLy_do().length() < 10
				|| mQD.getTram().length() < 10) {
			kq = false;
		}
		return kq;
	}

	public Obj_QuyetDinh get_QD() {
		String mDVI_FROM = "";
		String mDVI_TO = "PB11";
		String mCAN_CU = "";
		String mLY_DO = "";
		String mQD_SO = "";
		String mTTRANG = "";
		String mTRAM = "";
		Obj_QuyetDinh mQD = new Obj_QuyetDinh();
		try {
			Date date = mdate_box.getValue();
			mQD.setThoi_gian_tao(Client_function.date2timestamp(date));
		} catch (Exception e) {
			SC.say("Lỗi : " + e.toString());
		}
		try {
			mDVI_FROM = ls_donvi_from.get(cbx_dieudong_from.getSelectedIndex()).ma_donvi;
			mDVI_TO = ls_donvi_to.get(cbx_dieudong_to.getSelectedIndex()).ma_donvi;
			mTRAM = edt_tram.getText().toString();
			mTTRANG = list_tinhtrang.get(cbx_tinhtrang.getSelectedIndex()).KEY;
		} catch (Exception e) {
			SC.say("Lỗi : " + e.toString());
		}
		try {
			mQD_SO = edt_QD_SO.getText().toString();
			mCAN_CU = edt_CAN_CU.getText().toString();
			mLY_DO = edt_LYDO.getText().toString();
			mTRAM = edt_tram.getText().toString();
		} catch (Exception e) {
			SC.say("Lỗi : " + e.toString());
		}
		
		mQD.setQD_so(mQD_SO);
		mQD.setCan_cu(mCAN_CU);
		mQD.setLy_do(mLY_DO);
		mQD.setMa_history(Utils.LS_LUANCHUYEN_BINHTHUONG);
		mQD.setMadv_from(mDVI_FROM);
		mQD.setMadv_to(mDVI_TO);
		mQD.setTram(mTRAM);
		mQD.setTinhtrang_dieuve(mTTRANG);
		mQD.setNguongoc_ts(edt_nguongocts.getText().toString());
		return mQD;
	}

	public void set_list_timkiem(final int LOAI,
			final CellTable<Obj_LichSu> mycell, final List<Obj_LichSu> mylist) {
		mycell.setPageSize(10);
		// so may
		TextColumn<Obj_LichSu> nameColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.getSo_may();
			}
		};
		// ma so tai san
		TextColumn<Obj_LichSu> mstsColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.getMSTS();
			}
		};
		// don vi quan ly
		TextColumn<Obj_LichSu> donviColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.get_tendvi(mLLOCAL_DVI);
			}
		};
		// cap hay dieu
		Column<Obj_LichSu, String> btnDieuCap = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				if (LOAI == LOAI_DIEU) {
					return "Điều về";
				} else {
					return "Cấp cho";
				}
			}
		};
		btnDieuCap.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(int index, final Obj_LichSu object, String value) {
//				final Obj_LichSu mLS = get_lichSu(object,oQD_tam);
//				if (LOAI == LOAI_DIEU) {
//					mLS.setLoai_history(Utils.DD_VE);
//					object.setLoai_dieudong(Utils.DD_VE);
//				} else {
//					mLS.setLoai_history(Utils.DD_CAP);
//					mLS.setTram(oQD_tam.getTram());
//					object.setLoai_dieudong(Utils.DD_CAP);
//				}
				if(object.getDa_tra()==Utils.DA_TRA){
				if (LOAI == LOAI_DIEU) {
					if (can_add(object, list_LS_dieuve)) {
						object.setLoai_history(Utils.LS_LUANCHUYEN_BINHTHUONG);
						object.setLoai_dieudong(Utils.DD_VE);
						object.setKho(oQD_tam.getMadv_to());
						object.setTinhtrang_sudung(oQD_tam.getTinhtrang_dieuve());
						object.setMadv_from(oQD_tam.getMadv_from());
						object.setMadv_to(oQD_tam.getMadv_to());
						object.setTram("");
						object.setQD_so(oQD_tam.getQD_so());
						object.setDa_tra(Utils.CHUA_TRA);
						list_LS_tam.add(object);
						list_LS_dieuve.add(0, object);
						set_data_list_lichsu(LOAI, cell_LichSu, list_LS_dieuve);
					} else {
						SC.say(object.getSo_may() + " đã được chọn !");
					}
				} else if (LOAI == LOAI_CAP){
					if (can_add(object, list_LS_capcho)) {
						object.setLoai_history(Utils.LS_LUANCHUYEN_BINHTHUONG);
						object.setTram(oQD_tam.getTram());
						object.setLoai_dieudong(Utils.DD_CAP);
						object.setKho(oQD_tam.getMadv_from());
						object.setMadv_from(oQD_tam.getMadv_to());
						object.setMadv_to(oQD_tam.getMadv_from());
						object.setQD_so(oQD_tam.getQD_so());
						object.setDa_tra(Utils.DA_TRA);
						list_LS_tam.add(object);
						list_LS_capcho.add(0, object);
						set_data_list_lichsu(LOAI, cell_lichsu_cap,
								list_LS_capcho);
					} else {
						SC.say(object.getSo_may() + " đã được chọn !");
					}
				}
				}else{
					SC.say(object.getSo_may() + " thực tế chưa trả trong Quyết định "+object.getQD_so()+" nên không thể điều động, Vui lòng xác nhận đã trả máy !");
				}
			}
		});
		mycell.setColumnWidth(btnDieuCap, 20, Unit.PCT);
		if (LOAI == LOAI_DIEU) {
			if (load_timkiem_dieu == false) {
				mycell.addColumn(nameColumn, "Số máy BA");
				mycell.addColumn(mstsColumn, "Mã số TS");
				mycell.addColumn(donviColumn, "Đơn vị QL");
				mycell.addColumn(btnDieuCap, "");
			}
		} else if (LOAI == LOAI_CAP) {
			if (load_timkiem_cap == false) {
				mycell.addColumn(nameColumn, "Số máy BA");
				mycell.addColumn(mstsColumn, "Mã số TS");
				mycell.addColumn(donviColumn, "Đơn vị QL");
				mycell.addColumn(btnDieuCap, "");
			}
		}

		AsyncDataProvider<Obj_LichSu> provider = new AsyncDataProvider<Obj_LichSu>() {
			@Override
			protected void onRangeChanged(HasData<Obj_LichSu> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= mylist.size() ? mylist.size() : end;
				List<Obj_LichSu> sub = mylist.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(mycell);
		provider.updateRowCount(mylist.size(), true);
		if (LOAI == LOAI_DIEU) {
			load_timkiem_dieu = true;
			pager_timkiem.setDisplay(mycell);
		} else if (LOAI == LOAI_CAP) {
			load_timkiem_cap = true;
			pager_cap.setDisplay(mycell);
		}
	}

	public void set_data_list_lichsu(final int LOAI,
			final CellTable<Obj_LichSu> mycell, List<Obj_LichSu> list) {
		mycell.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		// Create name column.
		TextColumn<Obj_LichSu> TenSPColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu contact) {
				return contact.getSo_may();
			}
		};
		mycell.setColumnWidth(TenSPColumn, 20, Unit.PCT);

		TextColumn<Obj_LichSu> DonviColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu contact) {
				return String.valueOf(contact.getMSTS());
			}
		};
		mycell.setColumnWidth(DonviColumn, 20, Unit.PCT);

		TextColumn<Obj_LichSu> DaungayColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu contact) {
				return contact.getNha_sanxuat();
			}
		};
		mycell.setColumnWidth(DaungayColumn, 20, Unit.PCT);
		// Add a ButtonCell as column to the CellTable
		Column<Obj_LichSu, String> deleteBtn = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				return "Xoá";
			}
		};
		deleteBtn.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(final int index, Obj_LichSu object, String value) {
				if (LOAI == LOAI_DIEU) {
					list_LS_dieuve.remove(index);
					set_data_list_lichsu(LOAI, cell_LichSu, list_LS_dieuve);
				} else {
					list_LS_capcho.remove(index);
					set_data_list_lichsu(LOAI, cell_lichsu_cap, list_LS_capcho);
				}
				list_LS_tam.remove(object);
			}
		});
		mycell.setColumnWidth(deleteBtn, 20, Unit.PCT);
		// Add the columns.
		if (LOAI == LOAI_DIEU) {
			if (load_lichsu_dieu == false) {
				mycell.addColumn(TenSPColumn, "Số máy");
				mycell.addColumn(DonviColumn, "MSTS");
				mycell.addColumn(DaungayColumn, "Hiệu máy");
				mycell.addColumn(deleteBtn, "");
			}
		} else if (LOAI == LOAI_CAP) {
			if (load_lichsu_cap == false) {
				mycell.addColumn(TenSPColumn, "Số máy");
				mycell.addColumn(DonviColumn, "MSTS");
				mycell.addColumn(DaungayColumn, "Hiệu máy");
				mycell.addColumn(deleteBtn, "");
			}
		}

		dataProvider_LS = new ListDataProvider<Obj_LichSu>();
		dataProvider_LS.addDataDisplay(mycell);
		dataProvider_LS.setList(list);

		ListHandler<Obj_LichSu> sortHandler = new ListHandler<Obj_LichSu>(
				dataProvider_LS.getList());
		mycell.addColumnSortHandler(sortHandler);

		mycell.getColumnSortList().push(TenSPColumn);
		mycell.setPageSize(dataProvider_LS.getList().size());
		if (LOAI == LOAI_DIEU) {
			load_lichsu_dieu = true;
		} else if (LOAI == LOAI_CAP) {
			load_lichsu_cap = true;
		}

	}

	@UiHandler("btn_timkiem_dieu")
	void onBtn_timkiem_dieuClick(ClickEvent event) {
		String SM = edt_timkiem_dieu.getText().toString();
		if (SM.length() > 0) {
			show_loading();
			mIodata.get_DSMBA_LOC(oQD_tam.getMadv_from(), "ALL", SM,
					new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							popup.hide();
							SC.say("Loi " + caught.toString());
						}

						@SuppressWarnings("unchecked")
						public void onSuccess(CallbackResult result) {
							list_TK_dieuve = (List<Obj_LichSu>) result
									.getResultObj();
							set_list_timkiem(LOAI_DIEU, cell_TimKiem,
									list_TK_dieuve);
							popup.hide();
						}
					});
		} else {
			SC.say("Từ khoá quá ngắn");
		}
	}

	@UiHandler("btn_timkiem_cap")
	void onBtn_timkiem_capClick(ClickEvent event) {
		if (khoa_QD) {
			String SM = edt_timkiem_cap.getText().toString();
			if (SM.length() > 1) {
				show_loading();
				mIodata.get_DSMBA_LOC(oQD_tam.getMadv_to(), "DDK", SM,
						new AsyncCallback<CallbackResult>() {
							public void onFailure(Throwable caught) {
								popup.hide();
								SC.say("loi " + caught.toString());
							}

							@SuppressWarnings("unchecked")
							public void onSuccess(CallbackResult result) {
								list_TK_capcho = (List<Obj_LichSu>) result
										.getResultObj();
								set_list_timkiem(LOAI_CAP, cell_timkiem_cap,
										list_TK_capcho);
								popup.hide();
							}
						});
			} else {
				SC.say("Từ khoá quá ngắn");
			}
		} else {
			SC.say("Chưa xác nhận quyết định");
		}
	}
	
	@UiHandler("edt_timkiem_dieu")
	void onEdt_timkiem_dieuKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem_dieu.click();
		}
	}

	@UiHandler("edt_timkiem_cap")
	void onEdt_timkiem_capKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem_cap.click();
		}
	}

	public boolean can_add(Obj_LichSu mMBA, List<Obj_LichSu> mLS) {
		boolean kq = true;
		for (Obj_LichSu obj_LichSu : mLS) {
			if (mMBA.getSo_may().equals(obj_LichSu.getSo_may())) {
				kq = false;
				break;
			}
		}
		return kq;
	}

	public void set_combo_tinhtrang_sudung() {
		list_tinhtrang = new ArrayList<Obj_Text>(Utils.get_list_tinhtrang());
		for (Obj_Text oTT : list_tinhtrang) {
			cbx_tinhtrang.addItem(oTT.NAME);
		}
	}

	public void show_confirm_dieudong(String message) {
		SC.confirm(message, new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
					if(list_LS_tam.size()>0){
						if(oQD_tam.getQD_so().length()>0){
					mIodata.check_tontai_QD(oQD_tam.getQD_so(),
							new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("Lỗi kiểm tra quyết định : "
											+ caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if (result.getResultString().equals(
											Utils.CB_OK)) {
										// co the hoan tat quyet dinh
										oQD_tam.setLoai_QD(Utils.QD_LUANCHUYEN);
										mIodata.hoantat_quyetdinh(
												mLocal_User,
												oQD_tam,
												list_LS_tam,
												new AsyncCallback<CallbackResult>() {

													public void onFailure(
															Throwable caught) {
														SC.say("error"
																+ caught.toString());

													}

													public void onSuccess(
															CallbackResult result) {
														if (result
																.getResultString()
																.equals(Utils.CB_OK)) {
															SC.say(ThongBao.TB_DIEUDONG_THANHCONG);
															reset_QD();
														} else {
															SC.say("sucess"
																	+ result.getResultString());
														}
													}
												});
									} else {
										SC.say("Quyết Định đã tồn tại !");
									}
								}
							});
				}
			}else{
				SC.say("Chưa có danh sách điều động");
			}
			}else{
				
			}
			}
			
		});
	}

	public void lock_QD() {
		if (khoa_QD == false) {
			edt_QD_SO.setEnabled(false);
			edt_LYDO.setEnabled(false);
			edt_CAN_CU.setEnabled(false);
			edt_tram.setEnabled(false);
			mdate_box.setEnabled(false);
			cbx_dieudong_from.setEnabled(false);
			cbx_dieudong_to.setEnabled(false);
			cbx_tinhtrang.setEnabled(false);
			btn_xacnhan_QD.setEnabled(false);
			btn_Reset_QD.setEnabled(true);
			khoa_QD = true;
			edt_timkiem_dieu.setFocus(true);
		}
	}

	public void reset_QD() {
		if (khoa_QD == true) {
			edt_QD_SO.setEnabled(true);
			edt_QD_SO.setText("");
			// edt_QD_SO.setFocus(true);
			edt_LYDO.setEnabled(true);
			edt_LYDO.setText("");
			edt_CAN_CU.setEnabled(true);
			edt_CAN_CU.setText("");
			edt_tram.setEnabled(true);
			mdate_box.setEnabled(true);
			cbx_dieudong_from.setEnabled(true);
			cbx_dieudong_to.setEnabled(true);
			cbx_tinhtrang.setEnabled(true);
			btn_xacnhan_QD.setEnabled(true);
			btn_Reset_QD.setEnabled(false);
			list_LS_dieuve = new ArrayList<Obj_LichSu>();
			set_data_list_lichsu(LOAI_DIEU, cell_LichSu, list_LS_dieuve);
			list_LS_capcho = new ArrayList<Obj_LichSu>();
			set_data_list_lichsu(LOAI_CAP, cell_lichsu_cap, list_LS_capcho);
			list_TK_capcho = new ArrayList<Obj_LichSu>();
			set_list_timkiem(LOAI_CAP, cell_timkiem_cap, list_TK_capcho);
			list_TK_dieuve = new ArrayList<Obj_LichSu>();
			set_list_timkiem(LOAI_DIEU, cell_TimKiem, list_TK_dieuve);
			list_LS_tam = new ArrayList<Obj_LichSu>();
			khoa_QD = false;
		}
	}

	@UiHandler("btn_xacnhan_QD")
	void onBtn_taoQDClick(ClickEvent event) {
		oQD_tam = get_QD();
		if (My_function.QD_OK(oQD_tam)) {
			tv_cap.setText("4. Điều động tài sản cấp cho "+" "+oQD_tam.get_tendvi_from(
							ls_donvi_from));
			String message = "Bạn có chắc xác nhận quyết định này không ?\n "
					+ "Quyết định "
					+ oQD_tam.getQD_so()
					+ ", từ "
					+ oQD_tam.get_tendvi_from(
							ls_donvi_from)
					+ " đến "
					+ oQD_tam.get_tendvi_to(ls_donvi_to)
					+ "\nViệc xác nhận sẽ không cho thay đổi thông tin quyết định !";
			SC.confirm(message, new BooleanCallback() {
				public void execute(Boolean value) {
					if (value != null && value) {
						mIodata.check_tontai_QD(oQD_tam.getQD_so(),
								new AsyncCallback<CallbackResult>() {
									public void onFailure(Throwable caught) {
										Window.alert("Lỗi kiểm tra quyết định : "
												+ caught.toString());
									}
									public void onSuccess(CallbackResult result) {
										if (result.getResultString().equals(
												Utils.CB_OK)) {
											// co the hoan tat quyet dinh
											lock_QD();
										} else {
											SC.say("Quyết Định đã tồn tại !");
										}
									}
								});
					}
				}
			});
		} else {
			SC.say("Quyết định hoặc đơn vị điều đồng chưa chính xác !"
					+ "\nQuyết định "
					+ oQD_tam.getQD_so()
					+ "\ntừ "
					+ oQD_tam.get_tendvi_from(
							ls_donvi_from) + " đến "
					+ oQD_tam.get_tendvi_to(ls_donvi_to));
		}
	}

	@UiHandler("btn_Reset_QD")
	void onButtonClick(ClickEvent event) {
		reset_QD();
	}

	public void show_loading() {
		popup = new PopupPanel(false, true);
		popup.setGlassEnabled(true);
		VerticalPanel ver = new VerticalPanel();
		ver.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		Image im = new Image(resources.getPreloader());
		im.setWidth("120px");
		im.setHeight("120px");
		ver.add(im);
		popup.setWidget(ver);
		popup.setStyleName(resources.mystyle().gwt_PopupPanel());
		popup.center();
	}
}
