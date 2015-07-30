package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;

public class TimKiem_BienBan extends Composite {

	private static TimKiem_BienBanUiBinder uiBinder = GWT
			.create(TimKiem_BienBanUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField(provided = true)
	CellTable<Obj_QuyetDinh> cell_bienban = new CellTable<Obj_QuyetDinh>();
	@UiField
	TextBox edt_timkiem;
	@UiField
	Button btn_timkiem;
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_chitiet = new CellTable<Obj_LichSu>();
	@UiField
	Label tv_bb_chitiet;
	@UiField
	Button btn_in;
	@UiField
	Button btn_HUY;
	@UiField
	Button btn_CONFIG;
	boolean load = false;
	boolean load_chitiet = false;
	PopupPanel popup;
	List<Obj_QuyetDinh> list_BB;
	List<Obj_LichSu> list_CHITIET;
	Obj_User mLocal_user;
	List<Obj_donvi> lLocal_donvi;
	MyResources resources;
	String mQD = "";
	Obj_QuyetDinh oQD_chon;

	interface TimKiem_BienBanUiBinder extends UiBinder<Widget, TimKiem_BienBan> {
	}

	public TimKiem_BienBan(Obj_User mUS, List<Obj_donvi> mL_donvi) {
		mLocal_user = mUS;
		lLocal_donvi = new ArrayList<Obj_donvi>(mL_donvi);
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		tv_bb_chitiet.setText("Chưa có chi tiết biên bản");
		
		show_loading();
		mIodata.getLAST_BB(5,
				new AsyncCallback<CallbackResult>() {

					public void onFailure(Throwable caught) {
						popup.hide();
						Window.alert("loi " + caught.toString());

					}
					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						list_BB = (List<Obj_QuyetDinh>) result
								.getResultObj();
						if(list_BB.size()>0){
							set_list();
						}
						
						popup.hide();

					}
				});
	}

	public void set_list() {
		// Display 3 rows in one page
		cell_bienban.setPageSize(20);
		// so quyet dinh
		TextColumn<Obj_QuyetDinh> SoQDColumn = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return object.getQD_so();
			}
		};
		// can cu
		TextColumn<Obj_QuyetDinh> cancuColumn = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return object.getCan_cu();
			}
		};
		// ly do
		TextColumn<Obj_QuyetDinh> lydoColumn = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return object.getLy_do();
			}
		};
		// loai QD
		TextColumn<Obj_QuyetDinh> loaiQDColumn = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return object.getLoai_QD();
			}
		};
		// don vi from
		TextColumn<Obj_QuyetDinh> donvi_fromColumn = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return object.get_tendvi_from(lLocal_donvi);
			}
		};
		// don vi to
		TextColumn<Obj_QuyetDinh> donvi_toColumn = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return object.get_tendvi_to(lLocal_donvi);
			}
		};
		// thoi gian
		TextColumn<Obj_QuyetDinh> thoi_gian = new TextColumn<Obj_QuyetDinh>() {
			@Override
			public String getValue(Obj_QuyetDinh object) {
				return (Client_function.timestamp2string(object
						.getThoi_gian_tao()));
			}
		};
		// Add a Button chi tiet
		Column<Obj_QuyetDinh, String> ChiTietBtn = new Column<Obj_QuyetDinh, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_QuyetDinh c) {
				return "Chi tiết";
			}
		};
		ChiTietBtn.setFieldUpdater(new FieldUpdater<Obj_QuyetDinh, String>() {
			public void update(int index, final Obj_QuyetDinh object,
					String value) {
				oQD_chon = object;
				if (mLocal_user.getMa_donvi().length() == 4) {
					mIodata.get_LICHSU_OF_QD(object.getQD_so(),
							new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									popup.hide();
									SC.say("loi " + caught.toString());
								}
								@SuppressWarnings("unchecked")
								public void onSuccess(CallbackResult result) {
									list_CHITIET = (List<Obj_LichSu>) result
											.getResultObj();
									if (list_CHITIET != null) {
										if (list_CHITIET.size() > 0) {
											tv_bb_chitiet
													.setText("Chi tiết quyết định "
															+ object.getQD_so());
											set_list_chitiet(list_CHITIET);
											mQD = object.getQD_so();
										} else {
											SC.say("Không có chi tiết điều động cho Quyết Định đang chọn !");
										}

									} else {
										SC.say("List null !");
									}
									popup.hide();

								}
							});
				} else {
					SC.say("User này không được sửa !");
				}
			}
		});
		cell_bienban.setColumnWidth(ChiTietBtn, 20, Unit.PCT);

		if (load == false) {
			cell_bienban.addColumn(SoQDColumn, "Số QĐ");
			cell_bienban.setColumnWidth(cancuColumn, 40, Unit.PCT);
			cell_bienban.addColumn(cancuColumn, "Căn cứ");
			cell_bienban.setColumnWidth(lydoColumn, 40, Unit.PCT);
			cell_bienban.addColumn(lydoColumn, "Lý do");
			cell_bienban.addColumn(loaiQDColumn, "Loại QĐ");
			cell_bienban.addColumn(donvi_fromColumn, "Từ");
			cell_bienban.addColumn(donvi_toColumn, "Đến");
			cell_bienban.addColumn(thoi_gian, "Thời gian");
			cell_bienban.addColumn(ChiTietBtn, "");
		}

		AsyncDataProvider<Obj_QuyetDinh> provider = new AsyncDataProvider<Obj_QuyetDinh>() {
			@Override
			protected void onRangeChanged(HasData<Obj_QuyetDinh> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= list_BB.size() ? list_BB.size() : end;
				List<Obj_QuyetDinh> sub = list_BB.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_bienban);
		provider.updateRowCount(list_BB.size(), true);

		// pager.setDisplay(cell_bienban);

		load = true;
	}

	public void set_list_chitiet(final List<Obj_LichSu> list_ChiTiet) {
		// so may
		TextColumn<Obj_LichSu> soMayColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.getSo_may();
			}
		};

		// msts
		TextColumn<Obj_LichSu> mSTSColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.getMSTS();
			}
		};

		// dieu hay cap
		TextColumn<Obj_LichSu> lOAI_LC_Column = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				String kq = "";
				if (object.getLoai_dieudong().equals(Utils.DD_CAP)) {
					kq = "Cấp cho";
				} else if (object.getLoai_dieudong().equals(Utils.DD_VE)) {
					kq = "Điều về";
				} else {
					kq = "Không xác định";
				}
				return kq;
			}
		};
		// Add a ButtonCell as column to the CellTable
		Column<Obj_LichSu, String> traBtn = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				String kq = "";
				if (c.getDa_tra() == Utils.CHUA_TRA) {
					kq = "Chưa trả";
				} else if (c.getDa_tra() == Utils.DA_TRA) {
					kq = "Đã trả";
				}
				return kq;
			}
		};
		traBtn.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(final int index, Obj_LichSu object, String value) {
				if(object.getLoai_dieudong().equals(Utils.DD_VE)){
					show_confirm_datra(object);
				}else{
					SC.say("Chỉ được thay đổi trạng thái cho máy điều về !");
				}
				
			}
		});
		cell_chitiet.setColumnWidth(traBtn, 20, Unit.PCT);

		if (load_chitiet == false) {
			cell_chitiet.addColumn(soMayColumn, "Số máy");
			cell_chitiet.addColumn(mSTSColumn, "MSTS");
			cell_chitiet.addColumn(lOAI_LC_Column, "Loại ĐĐ");
			cell_chitiet.addColumn(traBtn, "");
		}

		AsyncDataProvider<Obj_LichSu> provider = new AsyncDataProvider<Obj_LichSu>() {
			@Override
			protected void onRangeChanged(HasData<Obj_LichSu> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= list_ChiTiet.size() ? list_ChiTiet.size() : end;
				List<Obj_LichSu> sub = list_ChiTiet.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_chitiet);
		provider.updateRowCount(list_ChiTiet.size(), true);

		// pager.setDisplay(cell_bienban);
		cell_chitiet.setPageSize(list_ChiTiet.size());

		load_chitiet = true;
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {

		String SM = edt_timkiem.getText().toString();
		if (SM.length() > 1) {
			show_loading();
			mIodata.getBB_SEARCH(SM, mLocal_user.getMa_donvi(),
					new AsyncCallback<CallbackResult>() {

						public void onFailure(Throwable caught) {
							popup.hide();
							Window.alert("loi " + caught.toString());

						}

						@SuppressWarnings("unchecked")
						public void onSuccess(CallbackResult result) {
							list_BB = (List<Obj_QuyetDinh>) result
									.getResultObj();
							set_list();
							popup.hide();

						}
					});
		} else {
			SC.say("Từ khoá quá ngắn");

		}

	}

	@UiHandler("edt_timkiem")
	void onEdt_timkiemKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem.click();
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

	@UiHandler("btn_in")
	void onBtn_inClick(ClickEvent event) {
		if (list_CHITIET != null) {
			if (list_CHITIET.size() > 0) {
				if (oQD_chon.getLoai_QD().equals(Utils.QD_LUANCHUYEN)) {
					String time = Client_function.date2string(Client_function.timestamp2date(oQD_chon.getThoi_gian_tao()));
					String url = GWT.getHostPageBaseURL() + "inqd?p1="
							+ oQD_chon.getQD_so()+"&p2="+time;
					final popupContent pp = new popupContent(url);
					pp.f.setPixelSize(Window.getClientWidth(),
							Window.getClientHeight() - 60);
					pp.setPopupPositionAndShow(new PositionCallback() {
						public void setPosition(int offsetWidth,
								int offsetHeight) {
							int left = (Window.getClientWidth() - offsetWidth) / 2;
							int top = (Window.getClientHeight() - offsetHeight) / 2;
							pp.setPopupPosition(left, top);
						}
					});
				} else {
					SC.say("Chưa có mẫu in cho loại quyết định này !\n"
							+ oQD_chon.getLoai_QD());
				}
			} else {
				SC.say("Không có thông tin để in !");
			}
		} else {
			SC.say("Không có thông tin để in !");
		}

	}

	@UiHandler("btn_HUY")
	void onBtn_HUYClick(ClickEvent event) {
		show_confirm("Bạn có muốn huỷ quyết định " + oQD_chon.getQD_so()
				+ " không ?");
	}

	public void show_confirm(String message) {
		SC.confirm(message, new BooleanCallback() {
			public void execute(Boolean value) {
				if (value != null && value) {
					if (oQD_chon != null) {
						mIodata.huy_quyetdinh(mLocal_user, oQD_chon,
								list_CHITIET,
								new AsyncCallback<CallbackResult>() {
									public void onFailure(Throwable caught) {
										SC.say("Lỗi huỷ quyết định : \n"
												+ caught.toString());
									}

									public void onSuccess(CallbackResult result) {
										if (result.getResultString().equals(
												Utils.CB_OK)) {
											list_CHITIET = new ArrayList<Obj_LichSu>();
											set_list_chitiet(list_CHITIET);
											tv_bb_chitiet
													.setText("Chưa có chi tiết biên bản");
											oQD_chon = null;
											SC.say("Đã huỷ thành công "
													+ oQD_chon.getQD_so()
													+ " !");
										} else {
											SC.say(result.getResultString());
										}
									}
								});
					} else {
						SC.say("Không có thông tin để huỷ !");
					}
				}
			}
		});
	}
	public void show_confirm_datra(final Obj_LichSu oLS) {
					if(oLS.getDa_tra()==Utils.CHUA_TRA){
						oLS.setDa_tra(Utils.DA_TRA);
					}else{
						oLS.setDa_tra(Utils.CHUA_TRA);
					}
					mIodata.update_MBA(oLS, new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							SC.say("Lỗi cập nhật : \n"
									+ caught.toString());
						}
						public void onSuccess(CallbackResult result) {
							if(result.getResultString().equals(Utils.CB_OK)){
								SC.say("Đã cập nhật thông tin thành công !");
								set_list_chitiet(list_CHITIET);
							}else{
								SC.say(result.getResultString());
							}
						}
					});
	}

	@UiHandler("btn_CONFIG")
	void onBtn_CONFIGClick(ClickEvent event) {
		final Config_QDLC pp = new Config_QDLC();
		pp.setPixelSize(Window.getClientWidth() - 100,
				Window.getClientHeight() - 100);
		pp.setPopupPositionAndShow(new PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = (Window.getClientHeight() - offsetHeight) / 2;
				pp.setPopupPosition(left, top);
			}
		});
	}
}
