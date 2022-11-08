package banking;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

abstract class Account implements Serializable{
	
	String account;
	String name;
	int balance;
	
	public Account(String account, String name, int balance) {
		super();
		this.account = account;
		this.name = name;
		this.balance = balance;
	}
	
	public void showActData() {
		System.out.println("계좌번호: " + account);
		System.out.println("고객이름: " + name);
		System.out.println("잔고: " + balance);
	}


	public int balanceValue(int addMoney) {
		return balance;
	}

	@Override
	public int hashCode() {
		int returnCode = this.account.hashCode();
		return returnCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		Account account = (Account)obj;
		if(account.account.equals(this.account)) {
			return true;
		}
		else {
			return false;
		}
	}
	//쓰레드 텍스트파일로 갈때 필요함
	@Override
	public String toString() {
		return "계좌번호: " + account+ ", 고객이름: " + name + ", 잔고: " + balance ;
	}
}