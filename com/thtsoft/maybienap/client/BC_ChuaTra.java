package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.event.dom.client.ClickEvent;

public class BC_ChuaTra extends Composite{

	private static BC_ChuaTraUiBinder uiBinder = GWT
			.create(BC_ChuaTraUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT
			.create(IOData2Server.class);
	@UiField(provided=true) CellTable<Obj_LichSu> cell_MBA = new CellTable<Obj_LichSu>();
	@UiField ListBox cbx_donvi;
	@UiField Button btn_timkiem;
	Obj_User mLocal_user;
	List<Obj_donvi> mLocal_LDVI;
	boolean load=false;

	interface BC_ChuaTraUiBinder extends UiBinder<Widget, BC_ChuaTra> {
	}

	public BC_ChuaTra(Obj_User mUser,final List<Obj_donvi> mLDVI) {
		mLocal_user = mUser;
		mLocal_LDVI = new ArrayList<Obj_donvi>(mLDVI);
		initWidget(uiBinder.createAndBindUi(this));
		setcombo_donvi(mLocal_LDVI);
		
		if(mUser.get_cap_dvi(mLocal_LDVI).equals(Utils.CAP_DIENLUC)){
			int pos = mUser.get_donvi_pos(mLocal_LDVI);
			cbx_donvi.setEnabled(false);
			if(pos>-1){
				cbx_donvi.setItemSelected(pos, true);
			}
		}
		
		
	}
	public void setcombo_donvi(List<Obj_donvi> ls_dvi) {
		if (ls_dvi != null) {
			Obj_donvi mDV = new Obj_donvi("ALL", "Tất cả", "", "", "", "");
			ls_dvi.add(0,mDV);
			for (Obj_donvi dv : ls_dvi) {
				cbx_donvi.addItem(dv.getTen_donvi());
			}
		}
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		String mDVI = mLocal_LDVI.get(cbx_donvi.getSelectedIndex()).getMa_donvi();
		mIodata.get_DSMBA_CHUATRA(mDVI,new AsyncCallback<CallbackResult>() {

			public void onFailure(Throwable caught) {
				Window.alert("loi "+caught.toString());
			}
			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				List<Obj_LichSu> list_MBA = new ArrayList<Obj_LichSu>();
				try {
					list_MBA = (List<Obj_LichSu>) result.getResultObj();
				} catch (Exception e) {
					
				}
				if (list_MBA.size()>0){
					set_list(list_MBA);
				}else{
					SC.say("Không tìm thấy thông tin !");
					list_MBA = new ArrayList<Obj_LichSu>();
					set_list(list_MBA);
				}
				
			}
		});
	}
	public void set_list(final List<Obj_LichSu> list_MBA){
		 
	    // Display 3 rows in one page
//		cell_MBA.setPageSize(20);

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
	 // don vi chua tra
	    TextColumn<Obj_LichSu> donvichuatraColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.get_tendvi_from(mLocal_LDVI);
	      }
	    };
	    // don vi quan ly
	    TextColumn<Obj_LichSu> donviColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.get_tendvi(mLocal_LDVI);
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
	    if (load==false){
	    	cell_MBA.addColumn(nameColumn, "SỐ MÁY");
	    	cell_MBA.addColumn(mstsColumn, "MÃ SỐ TS");
	    	cell_MBA.addColumn(donvichuatraColumn, "ĐƠN VỊ CHƯA TRẢ");
	    	cell_MBA.addColumn(donviColumn, "ĐƠN VỊ QL HIỆN TẠI");
	    	cell_MBA.addColumn(nhasxColumn, "NHÀ SX");
	    	cell_MBA.addColumn(congsuatColumn, "CÔNG SUẤT");
	    	cell_MBA.addColumn(tramColumn, "TRẠM");
	    	cell_MBA.addColumn(bbtnColumn, "BB THÍ NGHIỆM");
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
	    cell_MBA.setPageSize(list_MBA.size());
//	    pager.setDisplay(cell_MBA);
	    
	    load = true;
	  }
}
