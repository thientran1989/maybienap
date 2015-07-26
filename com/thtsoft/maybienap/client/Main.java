package com.thtsoft.maybienap.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;

public class Main extends Composite {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

	@UiField
	MenuBar menu_bar;
	@UiField VerticalPanel ver_main;

	interface MainUiBinder extends UiBinder<Widget, Main> {
	}

	public Main(final Obj_User mUser,final List<Obj_donvi> lsdv) {
		initWidget(uiBinder.createAndBindUi(this));
		
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
				ver_main.clear();
				ver_main.add(new DanhSach_User(mUser, lsdv));
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
				ver_main.clear();
				ver_main.add(new DanhSach_MBA(mUser, lsdv));
				
			}
		};
		
		// menu loc du lieu
		Command menuCommand_QD_luanchuyen = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new DieuDong(mUser, lsdv));
			}
		};
		Command menuCommand_QD_suachua = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new DieuDong_SuaChua(mUser, lsdv));
			}
		};
		Command menuCommand_QD_thanhly = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new ThanhLy(mUser, lsdv));
			}
		};
		Command menuCommand_QD_timkiem = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new TimKiem_BienBan(mUser, lsdv));
			}
		};
		
		// menu bao cao
		Command menuCommand_bc_dsMBA = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new Loc_MBA(mUser, lsdv));
			}
		};
		Command menuCommand_bc_DSCHUATRA = new Command() {
			private int curPhrase = 5;
			public void execute() {
				Window.alert("" + curPhrase);
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
		MenuBar locMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Quyết định", locMenu));
		locMenu.addItem("QĐ Điều động", menuCommand_QD_luanchuyen);
		locMenu.addItem("QĐ Sữa chữa", menuCommand_QD_suachua);
		locMenu.addItem("QĐ Thanh lý", menuCommand_QD_thanhly);
		locMenu.addItem("Tìm kiếm QĐ", menuCommand_QD_timkiem);
		
		// menu bao cao
		MenuBar baocaoMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Báo cáo", baocaoMenu));
		baocaoMenu.addItem("BC Danh sách máy", menuCommand_bc_dsMBA);
		baocaoMenu.addItem("BC Máy chưa trả", menuCommand_bc_DSCHUATRA);
		
	}
}
