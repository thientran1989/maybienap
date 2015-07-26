package com.thtsoft.maybienap.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_Text;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;

public class Loc_MBA extends Composite {
	
	private final IOData2ServerAsync mIodata = GWT
			.create(IOData2Server.class);
	private static Loc_MBAUiBinder uiBinder = GWT.create(Loc_MBAUiBinder.class);
	@UiField Button btn_timkiem;
	@UiField(provided=true) CellTable<Obj_LichSu> cell_MBA = new CellTable<Obj_LichSu>();
	@UiField ListBox cbx_donvi;
	@UiField ListBox cbx_tinhtrang;
	@UiField SimplePanel pane_inds;
	List<Obj_Text> list_tinhtrang;
	List<Obj_LichSu> list_MBA;
	boolean load=false;
	Obj_User mLocal_user;
	List<Obj_donvi> mLocal_LDVI;
	String mDVI = "";
	String mTTRANG = "";

	interface Loc_MBAUiBinder extends UiBinder<Widget, Loc_MBA> {
	}

	public Loc_MBA(Obj_User mUser,final List<Obj_donvi> mLDVI) {
		mLocal_user = mUser;
		mLocal_LDVI = new ArrayList<Obj_donvi>(mLDVI);
		initWidget(uiBinder.createAndBindUi(this));
		setcombo_donvi(mLocal_LDVI);
		set_combo_tinhtrang_sudung();
		
		pane_inds.sinkEvents(Event.ONCLICK);
		pane_inds.addHandler(new ClickHandler(){
	        public void onClick(ClickEvent event) {
	        	if(list_MBA!=null){
	        	if(list_MBA.size()>0){
	        		String url = GWT.getHostPageBaseURL() + "danhsach_MBA?p1="+mDVI+"&p2="+mTTRANG;
		    		final popupContent pp=new popupContent(url);
		    		pp.f.setPixelSize(Window.getClientWidth(), Window.getClientHeight()-60);
		    		pp.setPopupPositionAndShow(new PositionCallback() {
		    			public void setPosition(int offsetWidth, int offsetHeight) {
		    				int left = (Window.getClientWidth() - offsetWidth) / 2;
		    	            int top = (Window.getClientHeight() - offsetHeight) / 2;
		    	            pp.setPopupPosition(left, top);
		    			}
		    		});
	        	}else{
	        		SC.say("Chưa có thông tin để in !");
	        	}
	        	}else{
	        		SC.say("Chưa có thông tin để in !");
	        	}
	        }

	    }, ClickEvent.getType());
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		mDVI = mLocal_LDVI.get(cbx_donvi.getSelectedIndex()).getMa_donvi();
		mTTRANG = list_tinhtrang.get(cbx_tinhtrang.getSelectedIndex()).KEY;
		mIodata.get_DSMBA_LOC(mDVI, mTTRANG,"",new AsyncCallback<CallbackResult>() {

			public void onFailure(Throwable caught) {
				Window.alert("loi "+caught.toString());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				list_MBA = new ArrayList<Obj_LichSu>();
				try {
					list_MBA = (List<Obj_LichSu>) result.getResultObj();
				} catch (Exception e) {
					
				}
				if (list_MBA.size()>0){
					set_list();
				}else{
					SC.say("Không tìm thấy thông tin !");
					list_MBA = new ArrayList<Obj_LichSu>();
					set_list();
				}
				
			}
		});
	}
	public void set_combo_tinhtrang_sudung() {
		list_tinhtrang = Utils.get_list_tinhtrang();
		for (Obj_Text oTT : list_tinhtrang) {
			cbx_tinhtrang.addItem(oTT.NAME);
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
	public void set_list(){
		 
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
	    // don vi quan ly
	    TextColumn<Obj_LichSu> donviColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.get_tendvi(mLocal_LDVI);
	      }
	    };
	    if (load==false){
	    	cell_MBA.addColumn(nameColumn, "Số máy BA");
	    	cell_MBA.addColumn(mstsColumn, "Mã số TS");
	    	cell_MBA.addColumn(donviColumn, "Đơn vị QL");
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
