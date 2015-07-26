package com.thtsoft.maybienap.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Maybienap implements EntryPoint {
	public void onModuleLoad() {
		RootPanel.get().add(new Login());
	}
}
