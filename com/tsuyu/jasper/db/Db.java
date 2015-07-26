package com.tsuyu.jasper.db;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author tsuyu_7
 */
public class Db {

    private static Properties prop;
    private static Connection connection = null;

    public static Connection connectMysql() throws FileNotFoundException, IOException{
        FileInputStream in = new FileInputStream(" C:\\glassfish3\\glassfish\\domains\\domain1\\config\\database.xml");       
        prop = new Properties();
        prop.loadFromXML(in);

        try {

        	Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            
        }

        try {
            connection = (Connection) DriverManager.getConnection(
					"jdbc:oracle:thin:@"+prop.getProperty("HOST")+":"+prop.getProperty("PORT")+":"+prop.getProperty("DATABASE"), prop.getProperty("USER"), prop.getProperty("PASSWORD"));
        } catch (SQLException e) {
           
        }
        if (connection != null) {
		return connection;
	} else {
                return null;
	}
       
    }
    
    public JasperPrint getReportPDF4(String reportName, List<?> lstObj, Map<String,Object> param) {
    	InputStream is = this.getClass().getResourceAsStream(reportName);
        try {
        	return JasperFillManager.fillReport(is, param, new JRBeanCollectionDataSource(lstObj));
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return null;
    }
    public JasperPrint getReportPDF3(String reportName, Map<String,Object> param) {
    	InputStream is = this.getClass().getResourceAsStream(reportName);
        try {
        	return JasperFillManager.fillReport(is, param, new JREmptyDataSource());
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return null;
    }
    public InputStream getInputReport(String reportName) {
    	   return this.getClass().getResourceAsStream(reportName);
    	   }
}
