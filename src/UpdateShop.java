import java.sql.SQLException;
import java.sql.Types;

import shopping.IConnectImpl;

public class UpdateShop extends IConnectImpl{
	
	public UpdateShop() {
		super();
	}
	
	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{call ShopUpdateGoods(?,?,?,?,?)}");
			
			csmt.setString(1, scanvalue("상품명"));
			csmt.setString(2, scanvalue("상품가격"));
			csmt.setString(3, scanvalue("제품코드"));
			csmt.setString(4, scanvalue("상품 일련번호"));
			csmt.registerOutParameter(5, Types.NUMERIC);
			csmt.execute();
			
			System.out.print("수정한 상품 갯수 : "+csmt.getString(5));
			
		}catch (Exception e) {
			System.out.println("예외발생");
		}
		
	}
	public static void main(String[] args) {
		System.out.println("=========상품 수정=========");
		new UpdateShop().execute();
	}

}
