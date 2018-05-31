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
	//C3p0�ֶ�����
	//��ȡC3P0����Դ����
	 public static DataSource getDataSourceWithC3p0 (){
	  ComboPooledDataSource cpds = new ComboPooledDataSource();
	  try{
	   //�������ݿ���Ϣ
	   cpds.setDriverClass("oracle.jdbc.OracleDriver");
	   cpds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	   cpds.setUser("scott");
	   cpds.setPassword("tiger");
	   //�������ӳ���Ϣ
	   cpds.setInitialPoolSize(10);
	   cpds.setMaxPoolSize(20);
	   
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  return cpds;
	 }
	//C3p0�Զ�����
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
	
	//�ֶ�
	public static DataSource getDataSourceWithDBCP(){
		  BasicDataSource basicDataSource = new BasicDataSource() ;
		  //��������Դ�е����ݿ���Ϣ
		      basicDataSource
		.setDriverClassName("oracle.jdbc.OracleDriver");
		  basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		  basicDataSource.setUsername("scott");
		  basicDataSource.setPassword("tiger");
		  //��������Դ�е����ӳز���
		  basicDataSource.setInitialSize(10);
		  //basicDataSource.setMaxActive(8);
		  basicDataSource.setMinIdle(2);
		  return basicDataSource ;
		  }
	
	//�Զ�
	public  static DataSource getDataSourceWithDBCPByProperties () {
		  DataSource basicDataSource = null ; 
		  //����һ�������ļ�����props
		  Properties props = new Properties();
		  try{
		  //�������ļ��е���Ϣ��ȡ����������
		  InputStream input =new DButil().getClass().getClassLoader()
		.getResourceAsStream("dbcpconfig.properties") ;
		  //�������ļ��е���Ϣ�������������ص�props��
		  props.load(input);
		  //����props�е�������Ϣ����������Դ����
		   basicDataSource = BasicDataSourceFactory
		.createDataSource(props) ;
		  }catch(Exception e){
		   e.printStackTrace();
		  }
		  return basicDataSource;
		 }
	
	
	
	
	// ͨ�õ����ӡ�ɾ�����޸ķ���
		public static boolean executeAddOrUpdateOrDelete(String sql ,
	Object[] os)
		{
			// flag��������Ƿ����ӳɹ��������ӳɹ��򷵻�true��������ʧ���򷵻�false
			boolean flag = true;
			try
			{
				// ��ȡStatement����
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
	// ͨ�õĲ�ѯ���������ز�ѯ�Ľ����ResultSet����
		// ͨ�õĲ�ѯ���������ز�ѯ�Ľ����ResultSet����
		public static ResultSet executeQuery(String sql, Object[] os) 
	{
			ResultSet rs = null; 
			try 
	{
				pstmt = createPreparedStatement(sql,os);
				rs = pstmt.executeQuery();
			} catch (SQLException e) 
	{
				System.out.println("SQLException��" + e);
			}catch (Exception e) 
	{
				System.out.println("��ѯ�����쳣��" + e);
			}
			return rs;
		}
		

		  //�������ݿ�
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
		
		
		// ͨ�õģ���ȡPreparedStatement����ķ���
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
		// ͨ�õģ��رշ������ݿ���ض���ķ���(ע��PreparedStatement�̳���Statement)
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
		
		
		// ��ѯ��������
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
