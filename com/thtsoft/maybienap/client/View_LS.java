package com.thtsoft.maybienap.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_donvi;

public class View_LS extends PopupPanel {

	private static View_LSUiBinder uiBinder = GWT.create(View_LSUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT.create(IOData2Server.class);
	@UiField(provided = true)
	CellTable<Obj_LichSu> cell_lichsu = new CellTable<Obj_LichSu>();
	@UiField
	SimplePager pager;
	@UiField
	Button btn_close;
	@UiField Label tv_somay;
	@UiField Label tv_msts;
	boolean load_chitiet = false;

	interface View_LSUiBinder extends UiBinder<Widget, View_LS> {
	}

	public View_LS(Obj_LichSu mMBA, final List<Obj_donvi> mL_donvi) {
		super(false, true);
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		tv_somay.setText("Số máy : " + mMBA.getSo_may());
		tv_msts.setText("MSTS : " + mMBA.getMSTS());
		mIodata.get_LICHSU_OF_MBA(mMBA, new AsyncCallback<CallbackResult>() {

			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				List<Obj_LichSu> my_list = null;
				try {
					my_list = (List<Obj_LichSu>) result.getResultObj();
				} catch (Exception e) {
					SC.say("succes but set list error :\n" + e.toString());
				}
				if (my_list != null) {
					set_list_chitiet(my_list, mL_donvi);
				} else {
					Window.alert("list null");
				}
			}

			public void onFailure(Throwable caught) {
				SC.say("error get list :\n" + caught.toString());

			}
		});
	}

	@UiHandler("btn_close")
	void onBtnCloseClick(ClickEvent event) {
		View_LS.this.hide();
	}

	public void set_list_chitiet(final List<Obj_LichSu> list_ChiTiet,
			final List<Obj_donvi> mL_donvi) {
		// tinh trang
		TextColumn<Obj_LichSu> tinhtrangColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return Obj_LichSu.get_tinhtrang_show(object
						.getTinhtrang_sudung());
			}
		};
		// tu dau
		TextColumn<Obj_LichSu> fromColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.get_tendvi_from(mL_donvi);
			}
		};
		// tu dau
		TextColumn<Obj_LichSu> toColumn = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.get_tendvi_to(mL_donvi);
			}
		};
		// thoi gian
		TextColumn<Obj_LichSu> thoi_gian = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return object.get_time_display();
			}
		};

		// dieu hay cap
		TextColumn<Obj_LichSu> lOAI_LC_Column = new TextColumn<Obj_LichSu>() {
			@Override
			public String getValue(Obj_LichSu object) {
				return 
				Obj_LichSu.get_loailichsu_show(object.loai_history);
			}
		};
		if (load_chitiet == false) {
			cell_lichsu.addColumn(tinhtrangColumn, "Tình trạng");
			cell_lichsu.addColumn(fromColumn, "từ");
			cell_lichsu.addColumn(toColumn, "Đến");
			cell_lichsu.addColumn(thoi_gian, "thời gian");
			cell_lichsu.addColumn(lOAI_LC_Column, "Thao tác");
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
		provider.addDataDisplay(cell_lichsu);
		provider.updateRowCount(list_ChiTiet.size(), true);

		// pager.setDisplay(cell_bienban);
		cell_lichsu.setPageSize(list_ChiTiet.size());

		load_chitiet = true;
	}

}
