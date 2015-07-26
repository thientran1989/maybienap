package com.thtsoft.maybienap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class Popup_XacNhan extends PopupPanel {

	private static Popup_XacNhanUiBinder uiBinder = GWT
			.create(Popup_XacNhanUiBinder.class);
	@UiField Label tv_message;
	@UiField Button btn_OK;
	@UiField Button btn_CANCEL;

	interface Popup_XacNhanUiBinder extends UiBinder<Widget, Popup_XacNhan> {
	}

	public Popup_XacNhan(String message) {
		super(false,true);
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		tv_message.setText(message);
	}

	@UiHandler("btn_CANCEL")
	void onBtn_CANCELClick(ClickEvent event) {
		Popup_XacNhan.this.hide();
	}
}
