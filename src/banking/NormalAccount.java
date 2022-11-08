package banking;

public class NormalAccount extends Account{
	
	int interest;

	public NormalAccount(String account, String name, int balance, int interest) {
		super(account, name, balance);
		this.interest = interest;
	}
	
	@Override
	public void showActData() {
		super.showActData();
		System.out.println("기본이자: " + interest+ "%");
	}
	
	@Override
	public int balanceValue(int addMoney) {
		balance = (int) (balance+(balance*(interest*0.01))+addMoney);
		return balance;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", 기본이자: " + interest+ "%";
	}
}
