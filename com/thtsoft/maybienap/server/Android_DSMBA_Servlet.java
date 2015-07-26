package com.thtsoft.maybienap.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thtsoft.maybienap.shared.CallbackResult;
import com.thtsoft.maybienap.shared.Obj_LichSu;
import com.thtsoft.maybienap.shared.ObjectClient;
import com.thtsoft.maybienap.shared.Utils;

@SuppressWarnings("serial")
public class Android_DSMBA_Servlet extends HttpServlet {
	List<Obj_LichSu> list_MBA;
	String json="first error";

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		DataInputStream  is = new DataInputStream(req.getInputStream());
		String android = Function.byte_to_String(is);
		JsonParser jp = new JsonParser();
		JsonObject mJO = jp.parse(android).getAsJsonObject();
		ObjectClient mCL = M_READ_JSON.read_client(mJO);
		String lenh = "Chua xac dinh";
		list_MBA = new ArrayList<Obj_LichSu>();
		if (mCL!=null){
			try {
				 lenh = mCL.getCommand();
				 list_MBA  = greetServer();
				 json = Utils.CB_OK;
			} catch (Exception e) {
				
			}
		}
			DataOutputStream dos = new DataOutputStream(res.getOutputStream());
			CallbackResult mCB = new CallbackResult();
			mCB.setCommand(lenh);
			mCB.setResultString(json);
			String kq = Function.alldata2server(mCB, list_MBA);
			Function.write_String_to_byte(dos, kq);
			dos.flush();
			dos.close();
	}
	public static void write_String_to_byte(DataOutputStream dos,String json){
		byte[] data;
		try {
			data = json.getBytes("UTF-8");
			try {
				dos.writeInt(data.length);
				dos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public List<Obj_LichSu> greetServer() throws IllegalArgumentException {
		   List<Obj_LichSu> list_MBA=null;
		   
			Connection con;
			ResultSet rs =null;
			Statement st;
				try {
					con = getDBConnection();
					st = con.createStatement();
					json=("connection established successfully...!!");
					rs = st.executeQuery(DB_SQL.get_SQL_DS_MBA_OF_DONVI("ALL", "ALL", ""));
					if (rs!=null){
						list_MBA = new ArrayList<Obj_LichSu>();
						while (rs.next()) {
							Obj_LichSu mMBA = Get_Obj.set_result_HTR(rs);
							list_MBA.add(mMBA);
						}
					}
					st.close();
					rs.close();
					con.close();
				} catch (Exception e) {
					json = e.toString();
				}
				return list_MBA;
			}
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@"
					+ DB_CONFIG.IP + ":1521:" + DB_CONFIG.DATA_NAME,
					DB_CONFIG.DATA_USER, DB_CONFIG.DATA_PASS);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;

	}
}
