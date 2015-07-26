final popupContent pp=new popupContent(GWT.getHostPageBaseURL()+"downloadpdf?param1=rptVatTuQuyetToanTheoNV&param2="+Ksctadmin.UL.MA_DVIQLY+"&param3="+dsHoSo);
			pp.f.setPixelSize(Window.getClientWidth(), Window.getClientHeight()-60);
			pp.setPopupPositionAndShow(new PositionCallback() {
				public void setPosition(int offsetWidth, int offsetHeight) {
					int left = (Window.getClientWidth() - offsetWidth) / 2;
		            int top = (Window.getClientHeight() - offsetHeight) / 2;
		            pp.setPopupPosition(left, top);
				}
			});