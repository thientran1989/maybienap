package com.thtsoft.maybienap.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MyResources  extends ClientBundle {
    public static final MyResources INSTANCE = GWT.create(MyResources.class);

    @Source("css/login_style.css")
    MyStyle_login mystyle();
    
    @Source("css/loading_cat.gif")
    ImageResource getPreloader();
}
