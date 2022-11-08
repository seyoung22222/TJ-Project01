package banking;

public class HighCreditAccount extends Account {
	
	int interest;
	String grade;
	
	public HighCreditAccount(String account, String name, int balance, int interest, String grade) {
		super(account, name, balance);
		this.interest = interest;
		this.grade = grade;
	}



	@Override
	public void showActData() {
		super.showActData();
		System.out.println("기본이자: " + interest+ "%");
		System.out.println("신용등급: " + grade);
	}
	
	@Override
	public int balanceValue(int addMoney) {
		
		if(grade.equalsIgnoreCase("A")) {
			balance = (int) (balance+(balance*(interest*0.01))+(balance*ICustomDefine.A*0.01)+addMoney);
		}
		else if(grade.equalsIgnoreCase("B")) {
			balance = (int) (balance+(balance*(interest*0.01))+(balance*ICustomDefine.B*0.01)+addMoney);
		}
		else if(grade.equalsIgnoreCase("C")) {
			balance = (int) (balance+(balance*(interest*0.01))+(balance*ICustomDefine.C*0.01)+addMoney);
		}
		return balance;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", 기본이자: " + interest+ "%" + ", 신용등급: " + grade;
	}
}
