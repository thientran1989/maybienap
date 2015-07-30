package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;

public class DanhSach_MBA extends Composite {

	private static DanhSach_MBAUiBinder uiBinder = GWT
			.create(DanhSach_MBAUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_MBA = new CellTable<Obj_LichSu>();
	@UiField
	SimplePager pager;
	@UiField
	VerticalPanel ver_ds;
	@UiField
	SimplePanel pane_them;
	@UiField
	Button btn_timkiem;
	@UiField
	TextBox edt_timkiem;
	List<Obj_LichSu> list_MBA;
	Obj_User mLocal_user;
	List<Obj_donvi> mLocal_LDVI;

	boolean load = false;
	MyResources resources;

	PopupPanel popup;
	int left = 0;
	int top = 0;

	interface DanhSach_MBAUiBinder extends UiBinder<Widget, DanhSach_MBA> {
	}

	public DanhSach_MBA(Obj_User mUser, final List<Obj_donvi> mLDVI) {
		mLocal_user = mUser;
		mLocal_LDVI = new ArrayList<Obj_donvi>(mLDVI);
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));

		pane_them.sinkEvents(Event.ONCLICK);
		pane_them.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
					final Tao_MBA pp = new Tao_MBA(mLocal_user, Utils.TAO,
							mLDVI, null);
					pp.setPixelSize(Window.getClientWidth() - 100,
							Window.getClientHeight() - 60);
					pp.setPopupPositionAndShow(new PositionCallback() {
						public void setPosition(int offsetWidth,
								int offsetHeight) {
							int left = (Window.getClientWidth() - offsetWidth) / 2;
							int top = (Window.getClientHeight() - offsetHeight) / 2;
							pp.setPopupPosition(left, top);
						}
					});

			}

		}, ClickEvent.getType());

	}

	public DanhSach_MBA(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void set_list() {

		// Display 3 rows in one page
		cell_MBA.setPageSize(20);
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

		// tinh trang may
		TextColumn<Obj_LichSu> tinhtrangColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return Obj_LichSu.get_tinhtrang_show(object
						.getTinhtrang_sudung());
			}
		};
		// nha san xuat
	    TextColumn<Obj_LichSu> nhasxColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getNha_sanxuat();
	      }
	    };
	 // cong suat
	    TextColumn<Obj_LichSu> congsuatColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getCong_suat();
	      }
	    };
	    // tram
	    TextColumn<Obj_LichSu> tramColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getTram();
	      }
	    };
	    // bien ban thi nghiem
	    TextColumn<Obj_LichSu> bbtnColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getBb_tnghiem();
	      }
	    };
		// Add a ButtonCell sua
		Column<Obj_LichSu, String> editSLBtn = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				if (mLocal_user.getMa_donvi().length() == 4) {
					return "Sửa";
				} else {
					return "Gắn lưới";
				}
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(int index, final Obj_LichSu object, String value) {
				if (mLocal_user.getMa_donvi().length() == 4) {
					if (object.getTinhtrang_sudung().equals(Utils.TT_DATHANHLY)) {
						SC.say("Máy đã thanh lý không được sửa !");
					} else {
						final Tao_MBA pp = new Tao_MBA(mLocal_user, Utils.SUA,
								mLocal_LDVI, object);
						pp.setPixelSize(Window.getClientWidth() - 100,
								Window.getClientHeight() - 60);
						pp.setPopupPositionAndShow(new PositionCallback() {
							public void setPosition(int offsetWidth,
									int offsetHeight) {
								int left = (Window.getClientWidth() - offsetWidth) / 2;
								int top = (Window.getClientHeight() - offsetHeight) / 2;
								pp.setPopupPosition(left, top);
							}
						});
					}
				} else {
					final LuoiDien pp = new LuoiDien(mLocal_user, object);
					left = (Window.getClientWidth() - pp.getOffsetWidth()) / 2;
					top = (Window.getClientHeight() - pp.getOffsetHeight()) / 2;
					pp.setPopupPosition(left, top);
				}
			}
		});
		
		// Add a ButtonCell lich su
		Column<Obj_LichSu, String> LICHSU_btn = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				return "Lịch sử";
			}
		};
		LICHSU_btn.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(int index, final Obj_LichSu object, String value) {
				final View_LS pp = new View_LS(object, mLocal_LDVI);																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																													
				pp.setPixelSize(Window.getClientWidth() - 60,
						Window.getClientHeight() - 60);
				pp.setPopupPositionAndShow(new PositionCallback() {
					public void setPosition(int offsetWidth, int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / 2;
						int top = (Window.getClientHeight() - offsetHeight) / 2;
						pp.setPopupPosition(left, top);
					}
				});
			}
		});

		// Add a ButtonCell xoa
		Column<Obj_LichSu, String> btn_XOA = new Column<Obj_LichSu, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_LichSu c) {
				return "Xoá";
			}
		};
		btn_XOA.setFieldUpdater(new FieldUpdater<Obj_LichSu, String>() {
			public void update(final int index, final Obj_LichSu object, String value) {
				if(object.getQD_so() !=null && object.getQD_so().length()>0){
					SC.say("Máy đã thao tác trong quyết định "+object.getQD_so()+"\n Không thể xoá !");
				}else{
					SC.confirm("Bạn có chắc muốn xoá máy "+object.getSo_may()+" không ?\n", new BooleanCallback() {
						public void execute(Boolean value) {
							if (value != null && value) {
								mIodata.xoa_lichsu(mLocal_user, object, new AsyncCallback<CallbackResult>() {
									public void onFailure(Throwable caught) {
										SC.say("loi " + caught.toString());
									}
									public void onSuccess(CallbackResult result) {
										if(result.getResultString().equals(Utils.CB_OK)){
											list_MBA.remove(index);
											set_list();
											SC.say("Đã xoá thành công "+object.getSo_may()+" !");
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
		});

		if (load == false) {
			cell_MBA.addColumn(nameColumn, "SỐ MÁY");
	    	cell_MBA.addColumn(mstsColumn, "MÃ SỐ TS");
	    	cell_MBA.addColumn(donviColumn, "ĐƠN VỊ QL");
	    	cell_MBA.addColumn(nhasxColumn, "NHÀ SX");
	    	cell_MBA.addColumn(congsuatColumn, "CÔNG SUẤT");
	    	cell_MBA.addColumn(tramColumn, "TRẠM");
	    	cell_MBA.addColumn(bbtnColumn, "BB THÍ NGHIỆM");
			cell_MBA.addColumn(tinhtrangColumn, "Tình trạng");
			cell_MBA.addColumn(editSLBtn, "");
			cell_MBA.addColumn(LICHSU_btn, "");
			cell_MBA.addColumn(btn_XOA, "");
		}

		AsyncDataProvider<Obj_LichSu> provider = new AsyncDataProvider<Obj_LichSu>() {
			@Override
			protected void onRangeChanged(HasData<Obj_LichSu> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= list_MBA.size() ? list_MBA.size() : end;
				List<Obj_LichSu> sub = list_MBA.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_MBA);
		provider.updateRowCount(list_MBA.size(), true);

		pager.setDisplay(cell_MBA);

		load = true;
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		String SM = edt_timkiem.getText().toString();
		if (SM.length() > 1) {
			show_loading();
			String mDVI = mLocal_user.getMa_donvi();
			if (mLocal_user.get_cap_dvi(mLocal_LDVI).equals(
					Utils.CAP_CONGTY)) {
				mDVI = "ALL";
			}
			mIodata.get_DSMBA_LOC(mDVI, "ALL", SM,
					new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							popup.hide();
							SC.say("loi " + caught.toString());
						}

						@SuppressWarnings("unchecked")
						public void onSuccess(CallbackResult result) {
							list_MBA = (List<Obj_LichSu>) result.getResultObj();
							set_list();
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

}
