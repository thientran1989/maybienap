package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.My_function;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.ThongBao;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class ThanhLy extends Composite {
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_timkiem = new CellTable<Obj_LichSu>();
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_thanhly = new CellTable<Obj_LichSu>();
	@UiField
	SimplePager pager;
	@UiField
	SimplePager pager_thanhly;
	@UiField
	Button btn_timkiem;
	@UiField
	TextBox edt_timkiem;
	@UiField
	TextBox edt_QD;
	@UiField
	DateBox mdate_box;
	@UiField
	TextBox edt_LY_DO;
	@UiField
	Button btn_taoQD;
	@UiField
	Button btn_Reset_QD;
	@UiField Button btn_hoantat;
	List<Obj_LichSu> list_timkiem;
	List<Obj_LichSu> list_thanhly;
	Obj_User mLocal_user;
	List<Obj_donvi> mLocal_LDVI;

	boolean load_timkiem = false;
	boolean load_thanhly = false;
	MyResources resources;

	PopupPanel popup;
	boolean khoa_QD = false;
	Obj_QuyetDinh oQD_tam=null;

	private static ThanhLyUiBinder uiBinder = GWT.create(ThanhLyUiBinder.class);

	interface ThanhLyUiBinder extends UiBinder<Widget, ThanhLy> {
	}

	public ThanhLy(Obj_User mUser, final List<Obj_donvi> mLDVI) {
		mLocal_user = mUser;
		mLocal_LDVI = new ArrayList<Obj_donvi>(mLDVI);
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		list_thanhly = new ArrayList<Obj_LichSu>();
		list_timkiem = new ArrayList<Obj_LichSu>();
		btn_Reset_QD.setEnabled(false);

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

	}

	public void set_list_timkiem(final List<Obj_LichSu> my_list) {
		// Display 3 rows in one page
		cell_timkiem.setPageSize(20);
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
				return object.get_tendvi(mLocal_LDVI);
			}
		};
		// Add a ButtonCell as column to the CellTable
		Column<Obj_LichSu, String> btn_thanhly = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				return "Thanh lý";
			}
		};
		btn_thanhly.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(int index, final Obj_LichSu object, String value) {
				if(can_add(object,list_thanhly)){
					if (object.getTinhtrang_sudung().equals(Utils.TT_CHOTHANHLY)) {
						object.setQD_so(oQD_tam.getQD_so());
						object.setTinhtrang_sudung(Utils.TT_DATHANHLY);
						object.setLoai_dieudong(Utils.LS_THANHLY);
						object.setMadv_from(mLocal_user.getMa_donvi());
						object.setMadv_to(mLocal_user.getMa_donvi());
						list_thanhly.add(object);
						set_list_thanhly(list_thanhly);
					} else {
						SC.say("Máy đủ điều kiện vận hành, không thể thanh lý !");
					}
				}else{
					SC.say("Đã được chọn !");
				}
				
			}
		});
		cell_timkiem.setColumnWidth(btn_thanhly, 20, Unit.PCT);
		if (load_timkiem == false) {
			cell_timkiem.addColumn(nameColumn, "Số máy BA");
			cell_timkiem.addColumn(mstsColumn, "Mã số TS");
			cell_timkiem.addColumn(donviColumn, "Đơn vị QL");
			cell_timkiem.addColumn(btn_thanhly, "");
		}
		AsyncDataProvider<Obj_LichSu> provider = new AsyncDataProvider<Obj_LichSu>() {
			@Override
			protected void onRangeChanged(HasData<Obj_LichSu> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_LichSu> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_timkiem);
		provider.updateRowCount(my_list.size(), true);
		pager.setDisplay(cell_timkiem);
		load_timkiem = true;
	}
	public void set_list_thanhly(final List<Obj_LichSu> my_list) {
		// Display 3 rows in one page
		cell_thanhly.setPageSize(20);
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
				return object.get_tendvi(mLocal_LDVI);
			}
		};
		// Add a ButtonCell as column to the CellTable
		Column<Obj_LichSu, String> btn_thanhly = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				return "Xoá";
			}
		};
		btn_thanhly.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(int index, final Obj_LichSu object, String value) {
				list_thanhly.remove(index);
				set_list_thanhly(list_thanhly);
			}
		});
		cell_thanhly.setColumnWidth(btn_thanhly, 20, Unit.PCT);
		if (load_thanhly == false) {
			cell_thanhly.addColumn(nameColumn, "Số máy BA");
			cell_thanhly.addColumn(mstsColumn, "Mã số TS");
			cell_thanhly.addColumn(donviColumn, "Đơn vị QL");
			cell_thanhly.addColumn(btn_thanhly, "");
		}
		AsyncDataProvider<Obj_LichSu> provider = new AsyncDataProvider<Obj_LichSu>() {
			@Override
			protected void onRangeChanged(HasData<Obj_LichSu> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_LichSu> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_thanhly);
		provider.updateRowCount(my_list.size(), true);
		pager_thanhly.setDisplay(cell_thanhly);
		load_thanhly = true;
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		String SM = edt_timkiem.getText().toString();
		if (SM.length() > 1) {
			show_loading();
			mIodata.get_DSMBA_LOC(mLocal_user.getMa_donvi(),Utils.TT_CHOTHANHLY,SM,
					new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							popup.hide();
							SC.say("loi " + caught.toString());
						}
						@SuppressWarnings("unchecked")
						public void onSuccess(CallbackResult result) {
							list_timkiem = new ArrayList<Obj_LichSu>();
							try {
								list_timkiem = (List<Obj_LichSu>) result
										.getResultObj();
							} catch (Exception e) {
								SC.say("loi lay DS " + e.toString());
							}
							if(list_timkiem!=null){
								if (list_timkiem.size() > 0) {
									set_list_timkiem(list_timkiem);
								} else {
									SC.say(ThongBao.TB_KHONG_TIM_THAY_DULIEU);
								}
							}else{
								SC.say(ThongBao.TB_KHONG_TIM_THAY_DULIEU);
							}
							popup.hide();
						}
					});
		} else {
			SC.say("Từ khoá quá ngắn");

		}
	}

	public void show_loading() {
		popup = new PopupPanel(false, true);
		popup.setGlassEnabled(true);
		VerticalPanel ver = new VerticalPanel();
		ver.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		Image im = new Image(resources.getPreloader());
		ver.add(im);
		popup.setWidget(ver);
		popup.setStyleName(resources.mystyle().gwt_PopupPanel());
		popup.center();
	}

	@UiHandler("edt_timkiem")
	void onEdt_timkiemKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem.click();
		}
	}

	public void lock_QD() {
		if (khoa_QD == false) {
			edt_QD.setEnabled(false);
			edt_LY_DO.setEnabled(false);
			mdate_box.setEnabled(false);
			btn_taoQD.setEnabled(false);
			btn_Reset_QD.setEnabled(true);
			khoa_QD = true;
			edt_timkiem.setFocus(true);
		}
	}

	public void reset_QD() {
		if (khoa_QD == true) {
			edt_QD.setEnabled(true);
			edt_QD.setText("");
			edt_LY_DO.setEnabled(true);
			edt_LY_DO.setText("");
			mdate_box.setEnabled(true);
			btn_taoQD.setEnabled(true);
			btn_Reset_QD.setEnabled(false);
			list_timkiem = new ArrayList<Obj_LichSu>();
			set_list_timkiem(list_timkiem);
			list_thanhly = new ArrayList<Obj_LichSu>();
			set_list_thanhly(list_thanhly);
			edt_timkiem.setText("");
			khoa_QD = false;
		}
	}
	public Obj_QuyetDinh get_quyetdinh() {
		Obj_QuyetDinh mQD = new Obj_QuyetDinh();
		mQD.setQD_so(edt_QD.getText().toString());
		mQD.setLy_do(edt_LY_DO.getText().toString());
		mQD.setMa_history(Utils.LS_LUANCHUYEN_THANHLY);
		mQD.setLoai_QD(Utils.QD_THANHLY);
		mQD.setMadv_from(mLocal_user.getMa_donvi());
		mQD.setMadv_to(mLocal_user.getMa_donvi());
		Date date = mdate_box.getValue();
		mQD.setThoi_gian_tao(Client_function.date2timestamp(date));
		return mQD;
	}
	@UiHandler("btn_taoQD")
	void onBtn_taoQDClick(ClickEvent event) {
		oQD_tam = get_quyetdinh();
		oQD_tam.setLoai_QD(Utils.QD_THANHLY);
		if(My_function.QD_THANHLY_OK(oQD_tam)){
			String message = "Bạn có chắc xác nhận quyết định thanh lý này không ?\n "
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
					+"\nQuyết định "+oQD_tam.getQD_so());
		}
	}

	@UiHandler("btn_Reset_QD")
	void onBtn_Reset_QDClick(ClickEvent event) {
		reset_QD();
	}
	@UiHandler("btn_hoantat")
	void onBtn_hoantatClick(ClickEvent event) {
		if(list_thanhly.size()>0){
			show_confirm_thanhly("Bạn có hoàn tất thanh lý không ?");
		}else{
			SC.say("Phải điều động ít nhất 1 máy !");
		}
		
	}
	public void show_confirm_thanhly(String message) {
		SC.confirm(message, new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
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
												mLocal_user,
												oQD_tam,
												list_thanhly,
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
															SC.say("Thanh lý thành công !");
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
}
