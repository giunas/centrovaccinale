package centrovaccinale.controller.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.w3c.dom.Document;

import centrovaccinale.conversion.JDBCUtil;

public class SQLhelp {
	public static final int RESULT = 1;
	public static final int NORESULT = 2;
	public static final int ERROR = 3;
	
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet rs = null;
    private static List<List<String>> res;
    //private static String res = "";
    private static int count = 0;
    
    private static Document doc = null;

	public static void open() {
		try {
			Context ctx = new InitialContext();
			//DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/nasodb");
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/sql11420508");
			connection = ds.getConnection();	
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
	
	public static void exec() {
		try {
			rs = statement.executeQuery();
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			res = new ArrayList<>();
	
			// table header
			List<String> header = new ArrayList<>();
			for (int i = 0; i < columnCount; i++) {
				header.add(rsmd.getColumnLabel(i + 1));
			}
			res.add(header);
			// the data
			while (rs.next()) {
				List<String> row = new ArrayList<>();
				for (int i = 0; i < columnCount; i++) {
					row.add(rs.getString(i + 1));
				}
				res.add(row);
			}
		}
		catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
	
	public static void upd() {
		try {
			count = statement.executeUpdate();
		}
		catch (Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
	
	public static void close() {
		try {
			if(rs != null)
				rs.close();
			statement.close();
			connection.close();
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}
	}
	
	public static void setStatement(PreparedStatement stmt) {
		statement = stmt;
	}
	
	public static PreparedStatement getStatement() {
		return statement;
	}
	
	public static Connection getConnection() {
		return connection;
	}

	public static List<List<String>> getResult() {
		return res;
	}
	
	public static int getCount() {
		return count;
	}
	
	public static void execWithDoc() {
		try {
			rs = statement.executeQuery();
			doc = JDBCUtil.toDocument(rs);
		}
		catch(Exception exc) {
			System.out.println(exc.getMessage());
		}	
	}
	
	public static void validateDoc() {
		JDBCUtil.validate(doc);
	}
	
	public static String getDoc() {
		return JDBCUtil.serialize(doc);
	}
}
