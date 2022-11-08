package shopping;

import java.util.Scanner;

public class InsertShop extends IConnectImpl{
	
	public InsertShop() {
		super();
	}
	
	@Override
	public void execute() {
		try {
			String query ="INSERT INTO sh_goods VALUES (seq_total_idx2.nextval,?,?,?,?)";
			
			psmt = con.prepareStatement(query);
			
			psmt.setString(1, scanvalue("상품명"));
			psmt.setString(2, scanvalue("상품가격"));
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			psmt.setDate(3, sqlDate);
			
			psmt.setString(4, scanvalue("상품코드"));
			
			int count = psmt.executeUpdate();
			System.out.println("입력된 행의 갯수: "+ count);
			
		}
		catch (Exception e) {
			System.out.println("예외발생");
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		
		System.out.println("=========상품 입력=========");
		new InsertShop().execute();
	}
	
	

}
