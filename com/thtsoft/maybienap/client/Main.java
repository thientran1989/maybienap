package com.thtsoft.maybienap.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_QuyetDinh;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;

public class Main extends Composite {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	@UiField
	MenuBar menu_bar;
	@UiField VerticalPanel ver_main;
	@UiField Label tv_TITLE;
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	PopupPanel popup;
	MyResources resources;

	interface MainUiBinder extends UiBinder<Widget, Main> {
	}

	public Main(final Obj_User mUser,final List<Obj_donvi> lsdv) {
		initWidget(uiBinder.createAndBindUi(this));
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		tv_TITLE.setText("CHƯƠNG TRÌNH QUẢN LÝ MÁY BIẾN ÁP");
			ver_main.clear();
			ver_main.add(new DanhSach_MBA(mUser, lsdv));
		
		// menu them
		Command menuCommand_themuser = new Command() {
			public void execute() {
//				final Tao_TRAM pp = new Tao_TRAM();
//				pp.setPopupPositionAndShow(new PositionCallback() {
//					public void setPosition(int offsetWidth,
//							int offsetHeight) {
//						int left = (Window.getClientWidth() - offsetWidth) / 2;
//						int top = (Window.getClientHeight() - offsetHeight) / 2;
//						pp.setPopupPosition(left, top);
//					}
//				});
				if(mUser.get_cap_dvi(lsdv).equals(Utils.CAP_CONGTY)){
					ver_main.clear();
					ver_main.add(new DanhSach_User(mUser, lsdv));
					tv_TITLE.setText("TẠO NGƯỜI DÙNG");
				}else{
					SC.say("Bạn không có quyền thao tác này !");
				}
				
			}
		};
		Command menuCommand_themMBA = new Command() {
			public void execute() {
//				greetingService.getTRAM_USE(new AsyncCallback<CallbackResult>() {
//					public void onFailure(Throwable caught) {
//						Window.alert("LỖI LẤY TRẠM "+caught.toString());
//					}
//					@SuppressWarnings("unchecked")
//					public void onSuccess(CallbackResult result) {
//						List<Obj_TRAM> list_tram = (List<Obj_TRAM>) result.getResultObj();
//						if (list_tram!=null){
//							final Tao_TUYEN pp = new Tao_TUYEN(list_tram);
//							pp.setPopupPositionAndShow(new PositionCallback() {
//								public void setPosition(int offsetWidth,
//										int offsetHeight) {
//									int left = (Window.getClientWidth() - offsetWidth) / 2;
//									int top = (Window.getClientHeight() - offsetHeight) / 2;
//									pp.setPopupPosition(left, top);
//								}
//							});
//						}else{
//							Window.alert("TRẠM NULL");
//						}
//					}
//				});	
				if(mUser.get_cap_dvi(lsdv).equals(Utils.CAP_CONGTY)){
					final Tao_MBA pp = new Tao_MBA(mUser, Utils.TAO,
							lsdv, null);
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
					tv_TITLE.setText("TẠO MÁY BIẾN ÁP");
				}else{
					SC.say("Bạn không có quyền thao tác này !");
				}
			}
		};
		
		// menu loc du lieu
		Command menuCommand_QD_luanchuyen = new Command() {
			public void execute() {
				show_loading();
				mIodata.getLAST_BB(1,
						new AsyncCallback<CallbackResult>() {
							public void onFailure(Throwable caught) {
								popup.hide();
								Window.alert("loi " + caught.toString());
							}
							@SuppressWarnings("unchecked")
							public void onSuccess(CallbackResult result) {
								String sLAST_QD ="";
								try {
									List<Obj_QuyetDinh> list_BB = (List<Obj_QuyetDinh>) result
											.getResultObj();
									if(list_BB.size()>0){
										sLAST_QD = " - Quyết định cuối cùng : "+list_BB.get(0).getQD_so();
									}
								} catch (Exception e) {
									
								}
								
								popup.hide();
								ver_main.clear();
								ver_main.add(new DieuDong(mUser, lsdv));
								tv_TITLE.setText("ĐIỀU ĐỘNG LUÂN CHUYỂN"+sLAST_QD);

							}
						});
			}
		};
		Command menuCommand_QD_suachua = new Command() {
			public void execute() {
				show_loading();
				mIodata.getLAST_BB(1,
						new AsyncCallback<CallbackResult>() {
							public void onFailure(Throwable caught) {
								popup.hide();
								Window.alert("loi " + caught.toString());
							}
							@SuppressWarnings("unchecked")
							public void onSuccess(CallbackResult result) {
								String sLAST_QD ="";
								try {
									List<Obj_QuyetDinh> list_BB = (List<Obj_QuyetDinh>) result
											.getResultObj();
									if(list_BB.size()>0){
										sLAST_QD = " - Quyết định cuối cùng : "+list_BB.get(0).getQD_so();
									}
								} catch (Exception e) {
									
								}
								popup.hide();
								ver_main.clear();
								ver_main.add(new DieuDong_SuaChua(mUser, lsdv));
								tv_TITLE.setText("ĐIỀU ĐỘNG SỮA CHỮA"+sLAST_QD);

							}
						});
				
			}
		};
		Command menuCommand_QD_thanhly = new Command() {
			public void execute() {
				show_loading();
				mIodata.getLAST_BB(1,
						new AsyncCallback<CallbackResult>() {
							public void onFailure(Throwable caught) {
								popup.hide();
								Window.alert("loi " + caught.toString());
							}
							@SuppressWarnings("unchecked")
							public void onSuccess(CallbackResult result) {
								String sLAST_QD ="";
								try {
									List<Obj_QuyetDinh> list_BB = (List<Obj_QuyetDinh>) result
											.getResultObj();
									if(list_BB.size()>0){
										sLAST_QD = " - Quyết định cuối cùng : "+list_BB.get(0).getQD_so();
									}
								} catch (Exception e) {
									
								}
								popup.hide();
								ver_main.clear();
								ver_main.add(new ThanhLy(mUser, lsdv));
								tv_TITLE.setText("THANH LÝ"+sLAST_QD);
							}
						});
				
			}
		};
		Command menuCommand_QD_timkiem = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new TimKiem_BienBan(mUser, lsdv));
				tv_TITLE.setText("TÌM KIẾM QUYẾT ĐỊNH");
			}
		};
		
		// menu bao cao
		Command menuCommand_tracuu = new Command() {
			public void execute() {
				tv_TITLE.setText("TÌM KIẾM MÁY BIẾN ÁP");
				ver_main.clear();
				ver_main.add(new DanhSach_MBA(mUser, lsdv));
			}
		};
		
		Command menuCommand_bc_dsMBA = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new Loc_MBA(mUser, lsdv));
				tv_TITLE.setText("DANH SÁCH MÁY BIẾN ÁP");
			}
		};
		Command menuCommand_bc_DSCHUATRA = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new BC_ChuaTra(mUser, lsdv));
				tv_TITLE.setText("DANH SÁCH MÁY BIẾN ÁP CHƯA TRẢ");
			}
		};

		menu_bar.setAutoOpen(true);
		// menu_bar.setWidth("500px");
		menu_bar.setAnimationEnabled(true);
		
		// menu them
		MenuBar themMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Thêm mới", themMenu));
		themMenu.addItem("Thêm tài khoản", menuCommand_themuser);
		themMenu.addItem("Thêm máy biến áp", menuCommand_themMBA);
		
		// menu loc
		if(mUser.get_cap_dvi(lsdv).equals(Utils.CAP_CONGTY)){
			MenuBar locMenu = new MenuBar(true);
			menu_bar.addItem(new MenuItem("Quyết định", locMenu));
			locMenu.addItem("QĐ Điều động", menuCommand_QD_luanchuyen);
			locMenu.addItem("QĐ Sữa chữa", menuCommand_QD_suachua);
			locMenu.addItem("QĐ Thanh lý", menuCommand_QD_thanhly);
			locMenu.addItem("Tìm kiếm QĐ", menuCommand_QD_timkiem);
		}
		
		
		// menu bao cao
		MenuBar baocaoMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Báo cáo", baocaoMenu));
		baocaoMenu.addItem("Tra cứu máy", menuCommand_tracuu);
		baocaoMenu.addItem("BC Danh sách máy", menuCommand_bc_dsMBA);
		baocaoMenu.addItem("BC Máy chưa trả", menuCommand_bc_DSCHUATRA);
		
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
}
