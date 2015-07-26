package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.My_function;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_Text;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class DieuDong_SuaChua extends Composite {

	private static DieuDong_SuaChuaUiBinder uiBinder = GWT
			.create(DieuDong_SuaChuaUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField
	ListBox cbx_dieudong_from;
	@UiField
	TextBox edt_QD;
	@UiField
	TextBox edt_LY_DO;
	@UiField
	Button btn_taoQD;
	@UiField
	TextBox edt_timkiem;
	@UiField
	Button btn_timkiem;
	@UiField
	DateBox mdate_box;
	@UiField
	CellTable<Obj_LichSu> cell_timkiem = new CellTable<Obj_LichSu>();
	@UiField
	CellTable<Obj_LichSu> cell_LichSu = new CellTable<Obj_LichSu>();
	@UiField
	VerticalPanel ver_dieu_dong;
	@UiField
	SimplePanel pane_hoan_tat;
	@UiField
	ListBox cbx_tinhtrang;
	@UiField
	Button button;
	@UiField
	ListBox cbx_dieudong_to;;
	List<Obj_donvi> ls_donvi = null;
	Obj_User mLocal_User = null;
	public static List<Obj_LichSu> list_timkiem;
	public static List<Obj_LichSu> list_dieuve;
	ListDataProvider<Obj_LichSu> dataProvider_LS;
	boolean load_timkiem_dieu = false;
	boolean load_lichsu_dieu = false;
	boolean khoa_QD = false;
	MyResources resources;
	List<Obj_Text> list_tinhtrang;
	Obj_QuyetDinh oQD_tam=null;

	interface DieuDong_SuaChuaUiBinder extends
			UiBinder<Widget, DieuDong_SuaChua> {
	}

	public DieuDong_SuaChua(Obj_User mUS, List<Obj_donvi> mL_DVI) {
		mLocal_User = mUS;
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		setcombo_donvi(mL_DVI);
		list_dieuve = new ArrayList<Obj_LichSu>();
		set_combo_tinhtrang_sudung();
		button.setEnabled(false);
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

		pane_hoan_tat.sinkEvents(Event.ONCLICK);
		pane_hoan_tat.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(list_dieuve.size()>0){
					show_confirm_dieudong("Bạn có muốn điều động từ "+oQD_tam.get_tendvi_from(ls_donvi)+" đến "
							+oQD_tam.get_tendvi_to(ls_donvi)+" ?");
				}else{
					SC.say("Phải điều động ít nhất 1 máy !");
				}
				
			}
		}, ClickEvent.getType());
	}

	@UiHandler("cbx_dieudong_from")
	void onCbx_dieudong_fromChange(ChangeEvent event) {

	}

	public void setcombo_donvi(List<Obj_donvi> mL_DVI) {
		if (mL_DVI != null) {
			ls_donvi = new ArrayList<Obj_donvi>();
			for (Obj_donvi obj_donvi : mL_DVI) {
				if (obj_donvi.getCap().equals(Utils.CAP_CONGTY)
						|| obj_donvi.getCap().equals(Utils.CAP_PHAN_XUONG)) {
					ls_donvi.add(obj_donvi);
				}
			}
		}
		if (ls_donvi != null) {
			for (Obj_donvi dv : ls_donvi) {
				cbx_dieudong_from.addItem(dv.getTen_donvi());
				cbx_dieudong_to.addItem(dv.getTen_donvi());
			}
		}
	}

	public void set_list_timkiem(final CellTable<Obj_LichSu> mycell,
			final List<Obj_LichSu> mylist) {
		// Display 3 rows in one page
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
				return object.get_tendvi(ls_donvi);
			}
		};

		// Add a ButtonCell as column to the CellTable
		Column<Obj_LichSu, String> editSLBtn = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				return "Điều đi";

			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(int index, final Obj_LichSu object,
					String value) {
				if (can_add(object, list_dieuve)) {
					object.setKho(oQD_tam.getMadv_to());
					object.setTinhtrang_sudung(oQD_tam.getTinhtrang_dieuve());
					final Obj_LichSu mLS = get_lichSu(object, get_quyetdinh());
					mLS.setLoai_dieudong(Utils.LS_LUANCHUYEN_SUACHUA);
					list_dieuve.add(0, mLS);
					set_data_list_lichsu(cell_LichSu, list_dieuve);
				} else {
					SC.say(object.getSo_may() + " đã được chọn !");
				}

			}
		});
		mycell.setColumnWidth(editSLBtn, 20, Unit.PCT);
		if (load_timkiem_dieu == false) {
			mycell.addColumn(nameColumn, "Số máy BA");
			mycell.addColumn(mstsColumn, "Mã số TS");
			mycell.addColumn(donviColumn, "Đơn vị QL");
			mycell.addColumn(editSLBtn, "");
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
		load_timkiem_dieu = true;
		// pager_timkiem.setDisplay(mycell);

	}

	public void set_data_list_lichsu(final CellTable<Obj_LichSu> mycell,
			List<Obj_LichSu> list) {
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
				list_dieuve.remove(index);
				set_data_list_lichsu(cell_LichSu, list_dieuve);
			}
		});
		mycell.setColumnWidth(deleteBtn, 20, Unit.PCT);

		if (load_lichsu_dieu == false) {
			mycell.addColumn(TenSPColumn, "Số máy");
			mycell.addColumn(DonviColumn, "MSTS");
			mycell.addColumn(DaungayColumn, "Hiệu máy");
			mycell.addColumn(deleteBtn, "");
		}

		dataProvider_LS = new ListDataProvider<Obj_LichSu>();
		dataProvider_LS.addDataDisplay(mycell);
		dataProvider_LS.setList(list);

		ListHandler<Obj_LichSu> sortHandler = new ListHandler<Obj_LichSu>(
				dataProvider_LS.getList());
		mycell.addColumnSortHandler(sortHandler);
		mycell.getColumnSortList().push(TenSPColumn);
		mycell.setPageSize(dataProvider_LS.getList().size());
		load_lichsu_dieu = true;

	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		final String SM = edt_timkiem.getText().toString();
		if (SM.length() > 0 && oQD_tam.getMadv_from().length() > 0) {
			mIodata.get_DSMBA_LOC(oQD_tam.getMadv_from(), "ALL",SM,
					new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							SC.say("Loi " + caught.toString());

						}
						@SuppressWarnings("unchecked")
						public void onSuccess(CallbackResult result) {
							list_timkiem = (List<Obj_LichSu>) result
									.getResultObj();
							set_list_timkiem(cell_timkiem, list_timkiem);
							if(list_timkiem!=null & list_timkiem.size()==0){
								SC.say("Không tìm thấy \nsố máy : " + SM
										+ "\nĐơn vị : " + oQD_tam.getMadv_from());
							}

						}
					});
		} else {
			SC.say("Không thể tìm kiếm với \nsố máy : " + SM
					+ "\nĐơn vị : " + oQD_tam.getMadv_from());
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
					oQD_tam.setLoai_QD(Utils.QD_SUACHUA);
					mIodata.check_tontai_QD(oQD_tam.getQD_so(),
							new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("Lỗi kiểm tra quyết định : "
											+ caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if (result.getResultString().equals(
											Utils.CB_OK)) {
										oQD_tam = get_quyetdinh();
										mIodata.hoantat_quyetdinh(
												mLocal_User,
												oQD_tam,
												list_dieuve,
												new AsyncCallback<CallbackResult>() {

													public void onFailure(
															Throwable caught) {
														Window.alert("error"
																+ caught.toString());

													}

													public void onSuccess(
															CallbackResult result) {
														if (result
																.getResultString()
																.equals(Utils.CB_OK)) {
															SC.say("Điều động thành công !");
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
			}
		});
	}

	public Obj_QuyetDinh get_quyetdinh() {
		Obj_QuyetDinh mQD = new Obj_QuyetDinh();
		String mDVI_FROM="";
		String mDVI_TO="";
		String mTTRANG="";
		String mQD_SO = "" ;
		String mLY_DO ="";
		try {
			Date date = mdate_box.getValue();
			mQD.setThoi_gian_tao(Client_function.date2timestamp(date));
		} catch (Exception e) {

		}
		try {
			Obj_donvi oDV_FROM = ls_donvi.get(cbx_dieudong_from
					.getSelectedIndex());
			Obj_donvi oDV_TO = ls_donvi.get(cbx_dieudong_to.getSelectedIndex());
			mDVI_FROM = oDV_FROM.getMa_donvi();
			mDVI_TO = oDV_TO.getMa_donvi();
			mTTRANG = list_tinhtrang.get(cbx_tinhtrang.getSelectedIndex()).KEY;
		} catch (Exception e) {

		}
		try {
			mQD_SO = edt_QD.getText().toString();
			mLY_DO = edt_LY_DO.getText().toString();
		} catch (Exception e) {

		}
		
		mQD.setQD_so(mQD_SO);
		mQD.setLy_do(mLY_DO);
		mQD.setMa_history(Utils.LS_LUANCHUYEN_SUACHUA);
		mQD.setLoai_QD(Utils.QD_SUACHUA);
		mQD.setMadv_from(mDVI_FROM);
		mQD.setMadv_to(mDVI_TO);
		mQD.setTinhtrang_dieuve(mTTRANG);
		return mQD;
	}
	public Obj_LichSu get_lichSu(Obj_LichSu mMBA, Obj_QuyetDinh mQD) {
		Obj_LichSu mLS = mMBA.copy_object();
		if (mQD != null) {
			mLS.setQD_so(mQD.getQD_so());
			mLS.setLoai_history(Utils.LS_LUANCHUYEN_SUACHUA);
			mLS.setMadv_from(mQD.getMadv_from());
			mLS.setMadv_to(mQD.getMadv_to());
		}
		return mLS;
	}

	public void lock_QD() {
		if (khoa_QD == false) {
			edt_QD.setEnabled(false);
			edt_LY_DO.setEnabled(false);
			mdate_box.setEnabled(false);
			cbx_dieudong_from.setEnabled(false);
			cbx_dieudong_to.setEnabled(false);
			cbx_tinhtrang.setEnabled(false);
			btn_taoQD.setEnabled(false);
			button.setEnabled(true);
			khoa_QD = true;
			edt_timkiem.setFocus(true);
		}
	}

	public void reset_QD() {
		if (khoa_QD == true) {
			edt_QD.setEnabled(true);
			edt_QD.setText("");
//			edt_QD.setFocus(true);
			edt_LY_DO.setEnabled(true);
			edt_LY_DO.setText("");
			mdate_box.setEnabled(true);
			cbx_dieudong_from.setEnabled(true);
			cbx_dieudong_to.setEnabled(true);
			cbx_tinhtrang.setEnabled(true);
			btn_taoQD.setEnabled(true);
			button.setEnabled(false);
			edt_timkiem.setText("");
			list_timkiem = new ArrayList<Obj_LichSu>();
			set_list_timkiem(cell_timkiem, list_timkiem);
			list_dieuve = new ArrayList<Obj_LichSu>();
			set_data_list_lichsu(cell_LichSu, list_dieuve);
			khoa_QD = false;
		}
	}

	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		reset_QD();
	}

	@UiHandler("btn_taoQD")
	void onBtn_taoQDClick(ClickEvent event) {
		oQD_tam = get_quyetdinh();
		if(My_function.QD_OK(oQD_tam)){
			String message = "Bạn có chắc xác nhận quyết định này không ?\n "
					+"Quyết định "+oQD_tam.getQD_so()+", từ "+oQD_tam.get_tendvi_from(ls_donvi)+" đến "+oQD_tam.get_tendvi_to(ls_donvi)
					+ "Việc xác nhận sẽ không cho thay đổi thông tin quyết định !";
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
		}else{
			SC.say("Quyết định hoặc đơn vị điều đồng chưa chính xác !"
					+"\nQuyết định "+oQD_tam.getQD_so()+", từ "
					+oQD_tam.get_tendvi_from(ls_donvi)+" đến "
					+oQD_tam.get_tendvi_to(ls_donvi));
		}
		
	}
	@UiHandler("edt_timkiem")
	void onEdt_timkiemKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem.click();
        }
	}
}
