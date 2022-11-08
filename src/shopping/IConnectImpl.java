package shopping;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class IConnectImpl implements IConnect{
	public Connection con;
	public PreparedStatement psmt;
	public Statement stmt;
	public CallableStatement csmt;
	public ResultSet rs;
	
	public IConnectImpl() {
		try {
			Class.forName(ORACLE_DRIVER);
			connect();
		}
		catch(Exception e) {
			System.err.println("드라이브 로딩 실패");
		}
	}
	@Override
	public void connect(){
		try {
			String id = "education";
			String pw = "1234";
			con = DriverManager.getConnection(ORACLE_URL, id, pw);
			System.out.println("(education 계정 연결 성공)");
		}
		catch(Exception e) {
			System.err.println("DB 연결실패");
		}
		
	}
	@Override
	public void execute() {
		
	}
	@Override
	public void close() {
		try {
			if(con!=null) con.close();
			if(psmt!=null)psmt.close();
			if(stmt!=null)stmt.close();
			if(csmt!=null)csmt.close();
			if(rs!=null)rs.close();
		}
		catch (Exception e) {
			System.err.println("자원반납시 오류발생");
		}
		
	}
	@Override
	public String scanvalue(String title) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print(title+ " 입력 : ");
		String inputStr = scan.nextLine();
		return inputStr;
	}
	
	
	


}
