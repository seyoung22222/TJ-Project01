package banking;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	
	private HashSet<Account> actSet;
	
	public AccountManager(int num) {
		actSet = new HashSet<Account>();
	}
	
	void makeAccount(){ 
		
		System.out.println("**************신규계좌개설***************");
		System.out.println("---------------계 좌 선 택---------------");
		System.out.println("1. 보통계좌  2. 신용신뢰계좌 ");
		System.out.println("-----------------------------------------");
		System.out.print("개설할 계좌종류의 번호를 선택하시오 >> ");
		
		Scanner scan = new Scanner(System.in);
		int choice =0;
		try {	
			choice = scan.nextInt();
			
			if(!(choice<1 || choice>2)) {
				String iAccount, iName, iGrade;
				int iBalance,iInterest; 
				
				scan.nextLine();
				System.out.print("계좌번호: "); iAccount = scan.nextLine();
				 
				System.out.print("고객이름: "); iName = scan.nextLine();
				System.out.print("잔고: "); iBalance = scan.nextInt();
				
				if(choice==1) {
					System.out.print("기본이자%(정수형태로입력): "); iInterest = scan.nextInt();
					NormalAccount nAct= 
							new NormalAccount(iAccount, iName, iBalance,iInterest);
					
					if(actSet.add(nAct)) {
						actSet.add(nAct);
						System.out.println("계좌개설이 완료되었습니다.");
						System.out.println(" ");
					}
					else {
						System.out.println("중복계좌발견됨. 덮어쓸까요?");
						System.out.println("1 : 덮어쓰기  2: 기존정보 유지");
						System.out.print("번호를 입력하시오 >> ");
						try {
							int cover = scan.nextInt();
							if(cover==1) {
								actSet.remove(nAct);
								actSet.add(nAct);
								System.err.println("계좌를 덮어쓰기 합니다.");
								System.out.println(" ");
							}
							else if(cover==2) {
								System.err.println("입력정보는 무시되며 기존 정보를 유지합니다.");
							}
							else {
								MenuSelectException ex = new MenuSelectException();
								throw ex;
							}
						}
						catch (MenuSelectException e) {
							scan.nextLine();
							System.err.println("1 또는 2을 입력하시오");
							System.out.println(" ");
						}
					}
				}
				else if(choice==2) {
					System.out.print("기본이자%(정수형태로입력): "); iInterest = scan.nextInt();
					scan.nextLine();
					System.out.print("신용등급(A,B,C등급): "); 
					try{
						iGrade = scan.nextLine();
						if(iGrade.equalsIgnoreCase("a")) {}
						else if(iGrade.equalsIgnoreCase("b")) {}
						else if(iGrade.equalsIgnoreCase("c")) {}
						else {
							MenuSelectException ex = new MenuSelectException();
							throw ex;
						}
					}catch (MenuSelectException e) {
						System.err.println("A,B,C로 입력하시오");
						System.out.println(" ");
						return;
					}
					HighCreditAccount hAct= 
							new HighCreditAccount(iAccount, iName, iBalance,iInterest, iGrade);
					if(actSet.add(hAct)) {
						actSet.add(hAct);
						System.out.println("계좌개설이 완료되었습니다.");
						System.out.println(" ");
					}
					else {
						System.out.println("중복계좌발견됨. 덮어쓸까요?");
						System.out.println("1 : 덮어쓰기  2: 기존정보 유지");
						System.out.print("번호를 입력하시오 >> ");
						try {
							int cover = scan.nextInt();
							if(cover==1) {
								actSet.remove(hAct);
								actSet.add(hAct);
								System.err.println("계좌를 덮어쓰기 합니다.");
								System.out.println(" ");
							}
							else if(cover==2) {
								System.err.println("입력정보는 무시되며 기존 정보를 유지합니다.");
							}
							else {
								MenuSelectException ex = new MenuSelectException();
								throw ex;
							}
						}
						catch (MenuSelectException e) {
							scan.nextLine();
							System.err.println("1 또는 2을 입력하시오");
							System.out.println(" ");
						}
					}
				}
			}
			else {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
		}
		catch (MenuSelectException e) {
			System.err.println("입력오류] 1또는 2를 입력하시오");
			System.out.println(" ");
		}
		catch(InputMismatchException e){
			scan.nextLine();
			System.err.println("입력오류] 정수를 입력하시오.");
			System.out.println(" ");
		}
	}
	void depositMoney() {
		
		System.out.println("**************입 금***************");
		
		Scanner scan = new Scanner(System.in);
		boolean isFind = false;
		
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호: "); String searchAct = scan.nextLine();
		for(Account act : actSet) {
			if(act.account.equals(searchAct)) {
				System.out.print("입금액: "); 
				try {
					int addMoney = scan.nextInt();
					
					if(addMoney<0) {
						isFind =true;
						System.err.println("마이너스 금액은 입금이 불가능합니다.");
						MenuSelectException ex = new MenuSelectException();
						throw ex;
					}
					else if(!((addMoney%500)==0)) {
						isFind =true;
						System.err.println("입금은 500원단위로 가능합니다.");
						MenuSelectException ex = new MenuSelectException();
						throw ex;	
					}
					else{
						act.balanceValue(addMoney);
						System.out.println(addMoney+ "원이 정상적으로 입금되었습니다.");
						System.out.println(" ");
						isFind =true;
					}
				}
				catch (MenuSelectException e) {
					System.out.println(" ");
				}
				catch(InputMismatchException e){
					scan.nextLine();
					System.err.println("입력오류] 숫자만 입력하시오.");
					isFind =true;
					System.out.println(" ");
				}
			}
		}
		if(isFind==false) {
			System.err.println("입력하신 계좌번호가 없습니다.");
			System.out.println(" ");
		}
		
	}
	void withdrawMoney() {
		
		System.out.println("**************출 금***************");
		
		Scanner scan = new Scanner(System.in);
		boolean isFind = false;
		
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호: "); String searchAct = scan.nextLine();
		for(Account act : actSet) {
			if(act.account.equals(searchAct)) {
				System.out.print("출금액: "); 
				try {
					int wdrMoney = scan.nextInt();
					
					if(wdrMoney<0) {
						isFind =true;
						System.err.println("마이너스 금액은 출금이 불가능합니다.");
						MenuSelectException ex = new MenuSelectException();
						throw ex;
					}	
					
					else if(wdrMoney>act.balance){
						System.err.println("잔고가 부족합니다. 금액 전체를 출금할까요?");
						System.out.println("1 : 금액전체 출금처리");
						System.out.println("2 : 출금요청취소");
						int ys =scan.nextInt();
						if(ys==1) {
							isFind =true;
							System.out.println("1. 금액전체를 출금합니다.");
							act.balance -= act.balance;
						}
						else if(ys==2) {
							isFind =true;
							System.err.println("2. 출금요청을 취소하였습니다.");
						}
					}
					else if(!((wdrMoney%1000)==0)) {
						isFind =true;
						System.err.println("출금은 1000원단위로 가능합니다.");
						MenuSelectException ex = new MenuSelectException();
						throw ex;
					}	
					else {
						act.balance -= wdrMoney;
						System.out.println(wdrMoney+ "원이 정상적으로 출금되었습니다.");
						System.out.println(" ");
						isFind =true;
					}
				}
				catch (MenuSelectException e) {
					System.out.println(" ");
				}
				catch(InputMismatchException e){
					scan.nextLine();
					isFind =true;
					System.err.println("입력오류] 숫자만 입력하시오.");
					System.out.println(" ");
				}
			}
		}
		if(isFind==false) {
			System.err.println("입력하신 계좌번호가 없습니다.");
			System.out.println(" ");
		}
		
	}
	void showAccInfo() {
		System.out.println("************계 좌 정 보 출 력*************");
		
		for(Account act : actSet) {
		System.out.println("----------------------");
		act.showActData();
		}
		System.out.println("----------------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println(" ");
	} 
	
	void saveAccount(){
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(
							new FileOutputStream("src/banking/AccountInfo.obj"));
			for(Account act : actSet) {
				out.writeObject(act);
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("계좌정보 직렬화 중 예외발생");
		}
		System.out.println("AccountInfo.obj 파일에 저장되었습니다.");
	}
	
	void readAccount(){
		try {
			ObjectInputStream in = 
					new ObjectInputStream(
							new FileInputStream("src/banking/AccountInfo.obj"));
			while(true) {
				Account act = (Account)in.readObject();
				actSet.add(act);
				if(act==null) {
					break;
				}
			} 
			in.close();
		}
		catch (ClassNotFoundException e) {
			System.out.println("클래스없음");
		}
		catch (FileNotFoundException e) {
			System.out.println("파일없음");
		}
		catch (IOException e) {
		}
		System.out.println("AccountInfo.obj 파일에서 정보가 복원되었습니다.");
	}
	
	void autoSave(AutoSaver save) {
		Scanner scan = new Scanner(System.in);
		System.out.println("==저장옵션==");
		System.out.println("1.자동저장 On");
		System.out.println("2.자동저장 Off");
		try {
			int thread=scan.nextInt();
			if(thread==1) {
				if(Thread.activeCount()==2) {
					System.err.println("이미 자동저장이 실행중입니다.");
				}
				else if(Thread.activeCount()==1) {
				save.start();
				}
			}
			else if(thread==2) {
					save.interrupt();
					System.err.println("자동저장을 종료합니다.");
			}else {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
		}catch (MenuSelectException e) {
			System.err.println("입력오류] 1 또는 2를 입력하시오");
		}
	}
	void txtsaver() {
		try {
			PrintWriter out =
					new PrintWriter(
							new FileWriter("src/banking/AutoSaveAccount.txt"));
			for(Account act : actSet) {
				out.println(act);
			}
			out.close();
		}
		catch (Exception e) {
			System.out.println("계좌정보 직렬화 중 예외발생");
		}
	}
}
