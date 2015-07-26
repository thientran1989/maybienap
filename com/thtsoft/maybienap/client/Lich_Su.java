package com.thtsoft.maybienap.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.Obj_User;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.SimplePanel;

public class Lich_Su extends Composite {

	private static Lich_SuUiBinder uiBinder = GWT.create(Lich_SuUiBinder.class);
	@UiField(provided=true) CellTable<Obj_LichSu> cell_lichsu = new CellTable<Obj_LichSu>();
	boolean load=false;
	@UiField Label tv_somay;
	@UiField Button button;
	@UiField SimplePanel pane_trolai;
	Obj_LichSu mMBA;
	Obj_User mLocal_user;

	interface Lich_SuUiBinder extends UiBinder<Widget, Lich_Su> {
	}

	public Lich_Su() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public Lich_Su(Obj_User mUser,Obj_LichSu obj,List<Obj_LichSu> list) {
		mLocal_user = mUser;
		mMBA = obj;
		initWidget(uiBinder.createAndBindUi(this));
		tv_somay.setText(obj.getSo_may());
		
		pane_trolai.sinkEvents(Event.ONCLICK);
		pane_trolai.addHandler(new ClickHandler(){
	        public void onClick(ClickEvent event) {
	        	RootPanel.get().clear();RootPanel.get().add(new Main_tab(mLocal_user));	
	        }

	    }, ClickEvent.getType());
		
		set_list(list);
	}
	
	public void set_list(final List<Obj_LichSu> list_ls){

	    // so may
	    TextColumn<Obj_LichSu> nameColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getSo_may();
	      }
	    };
	    if (load==false){
	    	cell_lichsu.addColumn(nameColumn, "Số máy BA");
	    }
	    
	    
	    // noi cu
	    TextColumn<Obj_LichSu> noicusColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getMadv_from();
	      }
	    };
	    if (load==false){
	    	cell_lichsu.addColumn(noicusColumn, "Nơi cũ");
	    }
	    // don vi quan ly
	    TextColumn<Obj_LichSu> noimoiColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getMadv_to();
	      }
	    };
	    if (load==false){
	    	cell_lichsu.addColumn(noimoiColumn, "Nơi mới");
	    }
	    
	    // thoi gian
	    TextColumn<Obj_LichSu> TGtaoColumn = new TextColumn<Obj_LichSu>() {
	      @Override
	      public String getValue(Obj_LichSu object) {
	        return object.getThoi_gian_thaydoi().toString().substring(0, 16);
	      }
	    };
	    if (load==false){
	    	cell_lichsu.addColumn(TGtaoColumn, "Thời gian");
	    }
	    
	    AsyncDataProvider<Obj_LichSu> provider = new AsyncDataProvider<Obj_LichSu>() {
	      @Override
	      protected void onRangeChanged(HasData<Obj_LichSu> display) {
	        int start = display.getVisibleRange().getStart();
	        int end = start + display.getVisibleRange().getLength();
	        end = end >= list_ls.size() ? list_ls.size() : end;
	        List<Obj_LichSu> sub = list_ls.subList(start, end);
	        updateRowData(start, sub);
	      }
	    };
	    provider.addDataDisplay(cell_lichsu);
	    provider.updateRowCount(list_ls.size(), true);
	    
	    load = true;
	  }
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
//		String url = GWT.getHostPageBaseURL() + "lichsuview?somay="+mMBA.getSo_may();
//  	  	Window.open(url, "_blank", "");
	}
//	@UiHandler("button_1")
//	void onButton_1Click(ClickEvent event) {
//		RootPanel.get().clear();RootPanel.get().add(new Main_tab(mLocal_user));	
//	}
}
