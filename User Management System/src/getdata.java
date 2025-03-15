package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getdata {
	private String uname ,email,place;
	private int age;
	public getdata(String un) throws SQLException {
		uname=un;
		String url = "jdbc:mysql://localhost:3306/userdata";
		String user = "root";
		String pass = "1234";
		String str = "SELECT * FROM users WHERE name = '" + un + "'";
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		if(rst.next()) {
		email=rst.getString("email");
		place=rst.getString("place");
		age=rst.getInt("age");
	}
	}
	public String getage() {
		
		return  String.valueOf(age);
		
	}
public String getplace() {
	return place;
		
	}
public String getemail() {
	return email;
	
}

}
