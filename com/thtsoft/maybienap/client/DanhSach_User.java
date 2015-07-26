package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class DanhSach_User extends Composite{

	private static DanhSach_UserUiBinder uiBinder = GWT
			.create(DanhSach_UserUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField(provided=true) CellTable<Obj_User> cell_NHANVIEN = new CellTable<Obj_User>();
	@UiField Button btn_themuser;
	@UiField Button btn_lammoi;
	boolean load =false;
	Obj_User oLOCAL_USER=null;
	List<Obj_donvi> lLOCAL_DONVI=null;
	interface DanhSach_UserUiBinder extends UiBinder<Widget, DanhSach_User> {
	}

	public DanhSach_User(Obj_User oUSER,List<Obj_donvi> lDONVI) {
		initWidget(uiBinder.createAndBindUi(this));
		oLOCAL_USER = oUSER;
		lLOCAL_DONVI = new ArrayList<Obj_donvi>(lDONVI);
		mIodata.get_DS_USER(oLOCAL_USER,lLOCAL_DONVI, new AsyncCallback<CallbackResult>() {
			
			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				List<Obj_User> my_list = new ArrayList<Obj_User>();
				try {
					my_list = (List<Obj_User>)result.getResultObj();	
				} catch (Exception e) {
					SC.say("Lỗi ép DS : \n"+e.toString());
				}
				if(my_list.size()>0){
					set_list(my_list);
				}else{
					SC.say("Không có nhân viên !");
				}
			}
			
			public void onFailure(Throwable caught) {
				SC.say("Lỗi lấy DS : \n"+caught.toString());
				
			}
		});
		
	}
	public void set_list(final List<Obj_User> my_list) {
		// so may
		TextColumn<Obj_User> userColumn = new TextColumn<Obj_User>() {
			@Override
			public String getValue(Obj_User object) {
				return object.getUsername_mba();
			}
		};
		// ma so tai san
		TextColumn<Obj_User> tenColumn = new TextColumn<Obj_User>() {
			@Override
			public String getValue(Obj_User object) {
				return object.getTen_nhanvien();
			}
		};
		// don vi quan ly
		TextColumn<Obj_User> donviColumn = new TextColumn<Obj_User>() {
			@Override
			public String getValue(Obj_User object) {
				return object.get_tendvi(lLOCAL_DONVI);
			}
		};
		// Add a ButtonCell sua
		Column<Obj_User, String> editSLBtn = new Column<Obj_User, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_User c) {
				return "Sửa";
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_User, String>() {
			public void update(int index, final Obj_User object, String value) {
				if(!object.getUsername_mba().equals(oLOCAL_USER.getUsername_mba())){
				final Tao_NhanVien pp = new Tao_NhanVien(Utils.SUA,lLOCAL_DONVI,object);
				pp.setPopupPositionAndShow(new PositionCallback() {
					public void setPosition(int offsetWidth,
							int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / 2;
						int top = (Window.getClientHeight() - offsetHeight) / 2;
						pp.setPopupPosition(left, top);
					}
				});
				}else{
					SC.say("Không thể thao tác trên user đang sử dụng !");
				}
			}
		});

		// Add a ButtonCell xoa
		Column<Obj_User, String> btn_XOA = new Column<Obj_User, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_User c) {
				return "Xoá";
			}
		};
		btn_XOA.setFieldUpdater(new FieldUpdater<Obj_User, String>() {
			public void update(final int index, final Obj_User object, String value) {
				SC.confirm("Bạn có chắc muốn xoá user này không ?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value != null && value) {
							if(!object.getUsername_mba().equals(oLOCAL_USER.getUsername_mba())){
							mIodata.check_tontai_LS_OF_USER(object, new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("Lỗi xoá user : "+caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if(result.getResultString().equals(Utils.CB_OK)){
										mIodata.xoa_user(object, new AsyncCallback<CallbackResult>() {
											public void onFailure(Throwable caught) {
												SC.say("Lỗi xoá user : "+caught.toString());
											}
											public void onSuccess(CallbackResult result) {
												if(result.getResultString().equals(Utils.CB_OK)){
													my_list.remove(index);
													set_list(my_list);
													SC.say("Đã xoá thành công "+object.getUsername_mba()+" !");
												}else{
													SC.say(result.getResultString());
												}
												
											}
										});
									}else if(result.getResultString().equals(Utils.CB_TONTAI)){
										SC.say("User này đã thao tác ít nhất 1 máy biến áp, không thể xoá !");
									}else{
										SC.say(result.getResultString());
									}
									
								}
							});
							}else{
								SC.say("Không thể thao tác trên user đang sử dụng !");
							}
						}
					}
				});
				
			}
		});
		cell_NHANVIEN.setColumnWidth(btn_XOA, 20, Unit.PCT);

		if (load == false) {
			cell_NHANVIEN.addColumn(userColumn, "USERNAME");
			cell_NHANVIEN.addColumn(tenColumn, "TÊN NHÂN VIÊN");
			cell_NHANVIEN.addColumn(donviColumn, "ĐƠN VỊ");
			cell_NHANVIEN.addColumn(editSLBtn, "");
			cell_NHANVIEN.addColumn(btn_XOA, "");
		}

		AsyncDataProvider<Obj_User> provider = new AsyncDataProvider<Obj_User>() {
			@Override
			protected void onRangeChanged(HasData<Obj_User> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_User> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_NHANVIEN);
		provider.updateRowCount(my_list.size(), true);
		cell_NHANVIEN.setPageSize(my_list.size());
		load = true;
	}
	@UiHandler("btn_themuser")
	void onBtn_themuserClick(ClickEvent event) {
		final Tao_NhanVien pp = new Tao_NhanVien(Utils.TAO,lLOCAL_DONVI,null);
//		pp.setPixelSize(Window.getClientWidth() - 100,
//				Window.getClientHeight() - 60);
		pp.setPopupPositionAndShow(new PositionCallback() {
			public void setPosition(int offsetWidth,
					int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = (Window.getClientHeight() - offsetHeight) / 2;
				pp.setPopupPosition(left, top);
			}
		});
	}
	@UiHandler("btn_lammoi")
	void onBtn_lammoiClick(ClickEvent event) {
		mIodata.get_DS_USER(oLOCAL_USER,lLOCAL_DONVI, new AsyncCallback<CallbackResult>() {
			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				List<Obj_User> my_list = new ArrayList<Obj_User>();
				try {
					my_list = (List<Obj_User>)result.getResultObj();	
				} catch (Exception e) {
					SC.say("Lỗi ép DS : \n"+e.toString());
				}
				if(my_list.size()>0){
					set_list(my_list);
				}else{
					SC.say("Không có nhân viên !");
				}
			}
			
			public void onFailure(Throwable caught) {
				SC.say("Lỗi lấy DS : \n"+caught.toString());
				
			}
		});
	}
}
