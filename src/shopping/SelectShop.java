package shopping;

import java.util.Scanner;

public class SelectShop extends IConnectImpl{

	public SelectShop() {
		super();
	}

	
	@Override
	public void execute() {
		try {
			stmt = con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.print("\n조회할 상품명을 입력하세요 : ");
			String gName = sc.nextLine();
			String query = "SELECT "
				   +" g_idx, goods_name, trim(to_char(goods_price,'999,999,000')), "
				   +" to_char(regidate,'yyyy-mm-dd hh24:mi'), p_code "
				   +" FROM sh_goods WHERE goods_name like '%"+ gName +"%'";
			
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String g_id = rs.getString(1);
				String g_name = rs.getString(2);
				String g_price = rs.getString(3);
				String g_date = rs.getString(4);
				String g_code = rs.getString(5);
				
				System.out.println("-----"+gName+"의 정보-----");
				System.out.printf("일련번호: %s \n상품명: %s \n가격: %s \n등록일: %s \n제품코드: %s\n",
						g_id, g_name, g_price, g_date, g_code);
						
			}
		}
		catch (Exception e) {
			System.out.println("쿼리오류발생");
		}
		finally {
			close();
		}
	}

	public static void main(String[] args) {
		System.out.println("=========상품 조회=========");
		new SelectShop().execute();
	}
}

