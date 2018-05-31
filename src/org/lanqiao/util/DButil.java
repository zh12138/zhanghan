package org.lanqiao.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class DButil {
	//C3p0手动方法
	//获取C3P0数据源对象
	 public static DataSource getDataSourceWithC3p0 (){
	  ComboPooledDataSource cpds = new ComboPooledDataSource();
	  try{
	   //设置数据库信息
	   cpds.setDriverClass("oracle.jdbc.OracleDriver");
	   cpds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	   cpds.setUser("scott");
	   cpds.setPassword("tiger");
	   //设置连接池信息
	   cpds.setInitialPoolSize(10);
	   cpds.setMaxPoolSize(20);
	   
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return cpds;
	 }
	//C3p0自动方法
	 public static DataSource getDataSourceWithC3p0ByXML (){
		  ComboPooledDataSource cpds = new ComboPooledDataSource("lanqiao");
		  return cpds ; 
		 }
	
	
	
	//
	public static  Connection conn = null;
	public static  PreparedStatement pstmt = null;
	public static  Statement stmt = null;
	private static Context ctx = null; 
	 private static DataSource ds = null ;
	public static DataSource getDataSource() {
		try {
			 ctx=new InitialContext();
			 ds=(DataSource)ctx.lookup("java:comp/env/student");
			 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
		
	}
	
	//手动
	public static DataSource getDataSourceWithDBCP(){
		  BasicDataSource basicDataSource = new BasicDataSource() ;
		  //配置数据源中的数据库信息
		      basicDataSource
		.setDriverClassName("oracle.jdbc.OracleDriver");
		  basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		  basicDataSource.setUsername("scott");
		  basicDataSource.setPassword("tiger");
		  //设置数据源中的连接池参数
		  basicDataSource.setInitialSize(10);
		  //basicDataSource.setMaxActive(8);
		  basicDataSource.setMinIdle(2);
		  return basicDataSource ;
		  }
	
	//自动
	public  static DataSource getDataSourceWithDBCPByProperties () {
		  DataSource basicDataSource = null ; 
		  //创建一个配置文件对象props
		  Properties props = new Properties();
		  try{
		  //将配置文件中的信息读取到输入流中
		  InputStream input =new DButil().getClass().getClassLoader()
		.getResourceAsStream("dbcpconfig.properties") ;
		  //将配置文件中的信息，从输入流加载到props中
		  props.load(input);
		  //根据props中的配置信息，创建数据源对象
		   basicDataSource = BasicDataSourceFactory
		.createDataSource(props) ;
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		  return basicDataSource;
		 }
	
	
	
	
	// 通用的增加、删除、修改方法
		public static boolean executeAddOrUpdateOrDelete(String sql ,
	Object[] os)
		{
			// flag用来标记是否增加成功，若增加成功则返回true，若增加失败则返回false
			boolean flag = true;
			try
			{
				// 获取Statement对象
				pstmt = createPreparedStatement(sql,os);
				pstmt.executeUpdate();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				flag = false;
			}
			finally
			{
				closeAll(null, pstmt, conn);
			}
			return flag;
		}
	// 通用的查询方法，返回查询的结果集ResultSet对象
		// 通用的查询方法，返回查询的结果集ResultSet对象
		public static ResultSet executeQuery(String sql, Object[] os) 
	{
			ResultSet rs = null; 
			try 
	{
				pstmt = createPreparedStatement(sql,os);
				rs = pstmt.executeQuery();
			} catch (SQLException e) 
	{
				System.out.println("SQLException：" + e);
			}catch (Exception e) 
	{
				System.out.println("查询发生异常：" + e);
			}
			return rs;
		}
		

		  //连接数据库
		public static Connection getConnection()
		{
			try {
				//conn=getDataSource().getConnection();
				         
				   //conn = DButil.getDataSourceWithDBCP().getConnection();
				//conn=DButil.getDataSourceWithDBCPByProperties().getConnection();
				//conn=DButil.getDataSourceWithC3p0().getConnection();
				conn=DButil.getDataSourceWithC3p0ByXML().getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
		}
		
		
		// 通用的，获取PreparedStatement对象的方法
		public static PreparedStatement
	 createPreparedStatement(String sql,Object[] os) 
		{
			try
			{
				pstmt = getConnection().prepareStatement(sql);
				if (os != null)
				{
					for (int i = 0; i < os.length; i++)
					{
						pstmt.setObject(i + 1, os[i]);
					}
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return pstmt;	}
		// 通用的，关闭访问数据库相关对象的方法(注意PreparedStatement继承自Statement)
		public static void closeAll(ResultSet rs, Statement stmt, 
	Connection conn)
		{
			try
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		
		// 查询数据总数
		 public static int getTotalCount(String sql)
		 {
		  int count = -1;
		  ResultSet rs = null;
		  try
		  {
		   pstmt = createPreparedStatement(sql,null);
		   rs = pstmt.executeQuery();
		   if (rs.next())
		   {
		    count = rs.getInt(1);
		   }
		  }
		  catch (Exception e)
		  {
		   e.printStackTrace();
		  }
		  finally
		  {
		   closeAll(rs, pstmt, conn);
		  }
		  return count;
		 }


}
