package com.thtsoft.maybienap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class popupContent extends PopupPanel {

	private static popupContentUiBinder uiBinder = GWT
			.create(popupContentUiBinder.class);
	@UiField Frame f;
	@UiField Button btnClose;
	interface popupContentUiBinder extends UiBinder<Widget, popupContent> {
	}

	public popupContent(String link) {
		super(false,true);
		setWidget(uiBinder.createAndBindUi(this));
		f.setUrl(link);
		super.setGlassEnabled(true);
		super.center();
	}

	@UiHandler("btnClose")
	void onBtnCloseClick(ClickEvent event) {
		popupContent.this.hide();
	}
}
