package restaurant_AOL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class Connect {
	
	
	private Connection con;
	private Statement st;
	public ResultSet rs;
	public ResultSetMetaData rsm;
	
	private static Connect conobj;
	
	public static Connect getinstance() {
		 if(conobj==null) { return new Connect();
		 }else {
			 return conobj;
		 }
	 }
	
	public ResultSet executequery(String query) {
		 
		 try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		 
		 return rs;
	 }
	
	public void executeupdate(String query) {
		
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
			private Connect() {
					 try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						con= DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
						st = con.createStatement();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}


}
