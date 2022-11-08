package shopping;

import java.sql.Types;

public class DeleteShop extends IConnectImpl{
	
	public DeleteShop() {
		super();
	}

	@Override
	public void execute() {
		try {
			csmt = con.prepareCall("{call ShopDeleteGoods(?,?)}");
			
			csmt.setString(1, scanvalue("상품 일련번호"));
			csmt.registerOutParameter(2, Types.NUMERIC);
			csmt.execute();
			
			System.out.print("삭제한 상품 갯수 : "+csmt.getString(2));
			
		}catch (Exception e) {
			System.out.println("예외발생");
		}
	}


	public static void main(String[] args) {
		
		System.out.println("=========상품 삭제=========");
		new DeleteShop().execute();
	}
}
