package org.lanqiao.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.entity.User;

public class UserDao {
	private static final String DRIVER="oracle.jdbc.OracleDriver";
	public static final String URL= "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USERNAME = "scott";
	public static final String PASSWORD = "tiger";
	public User findUserByUsername(String username) {
		Connection conn =null;
		PreparedStatement pstmt =null;
		ResultSet rs= null;
		
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			String sql ="select * from userInformation where username=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String uname=rs.getString(1);
				String password=rs.getString(2);
				
				User user=new User();
				user.setUsername(uname);
				user.setPassword(password);
				return user;
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return null;
	}

}
