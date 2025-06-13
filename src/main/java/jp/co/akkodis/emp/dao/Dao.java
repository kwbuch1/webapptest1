package jp.co.akkodis.emp.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Dao {
	protected Connection connection;
	
	public Dao()  throws ClassNotFoundException, SQLException {

		//String url = "jdbc:mysql://localhost/db";
		//String user = "root";
		//String password = "VSOLuser123456";
		//connection = DriverManager.getConnection(url, user, password);
		try {
			//初期コンテキストを構築する(JNDIを使用するために生成)
			InitialContext ic = new InitialContext();
			//XMLファイルに記載されている情報を参照し、取得する
			DataSource ds = (DataSource)ic.lookup("java:/comp/env/jdbc/db");
			//Datasourceオブジェクトに設定された接続情報で接続を試みる
			connection = ds.getConnection();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
		    e.printStackTrace();
        }

	}

		//データベースとの切断をするメソッド
	public void close(){
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
