package com.thtsoft.maybienap.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.smartgwt.client.util.SC;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_User;
import com.thtsoft.maybienap.shared.Obj_donvi;
import com.thtsoft.maybienap.shared.Utils;

public class Login extends Composite {
	
	private static final int COOKIE_TIMEOUT = 1000 * 60 * 60 * 24;
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	private final IOData2ServerAsync mIodata = GWT
			.create(IOData2Server.class);
	MyResources resources;
	

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	public Login() {
		resources = GWT.create(MyResources.class);
		MyResources.INSTANCE.mystyle().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		tv_version.setText(Utils.version);
		String sessionID = Cookies.getCookie("sid");
		if (sessionID!=null){
			edt_username.setText(sessionID);
		}
	}

	@UiField TextBox edt_username;
	@UiField PasswordTextBox edt_password;
	@UiField Button btn_login;
	@UiField Label tv_version;

	public Login(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	@UiHandler("btn_login")
	void onBtn_loginClick(ClickEvent event) {
		String user ="";
		String password="";
		try {
			user=edt_username.getText().toString();
		} catch (Exception e) {
			
		}
		try {
			password=edt_password.getText().toString();
		} catch (Exception e) {
			
		}
		if (user.length()==0 || password.length()==0){
			SC.say("Chưa có username hay password");
		}else{
			Obj_User mUS = new Obj_User();
			mUS.setUsername_mba(user);
			mUS.setPassword(password);
			
			 Date expires = new Date((new Date()).getTime() + COOKIE_TIMEOUT);
		     // Set the cookie value
		     Cookies.setCookie(user, password, expires);
		     Cookies.setCookie("sid", user, expires, null, "/", false);
				
			mIodata.login(mUS,
					new AsyncCallback<Obj_User>() {
						public void onFailure(Throwable caught) {
							Window.alert("loi "+caught.toString());
						}
						public void onSuccess(final Obj_User oUSER) {
							if (oUSER!=null){
								mIodata.getDVI2(Utils.LENH_GET_DSDVI, new AsyncCallback<CallbackResult>() {
									public void onFailure(Throwable caught) {
										Window.alert("loi "+caught.toString());
									}
									@SuppressWarnings("unchecked")
									public void onSuccess(CallbackResult result) {
										List<Obj_donvi> ls_donvi = (List<Obj_donvi>) result.getResultObj();
										if (ls_donvi!=null){
											RootPanel.get().clear();
											RootPanel.get().add(new Main(oUSER, ls_donvi));
										}else{
											SC.say("Không lấy được đơn vị !");
										}
									}
								});	
							}else{
								SC.say("sai username hoặc mật khẩu !");
							}
							
						}
					});
		}
		
	}
	@UiHandler("edt_password")
	void onEdt_passwordKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_login.click();
        }
	}
	@UiHandler("edt_username")
	void onEdt_usernameKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_login.click();
        }
	}
}
