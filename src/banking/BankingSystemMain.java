package banking;


import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("=======================================Menu=======================================");
		System.out.println("1. 계좌개설  2. 입  금  3. 출  금  4. 계좌정보 출력  5. 프로그램 종료  0. 저장옵션");
		System.out.println("----------------------------------------------------------------------------------");
	}

	public static void main(String[] args) {
		
		System.out.println("1차프로젝트(학원)");
		System.out.println("클론 후 작업01");
		System.out.println("pull없이 작업진행함");
		
		Scanner scan = new Scanner(System.in);
		AccountManager manager = new AccountManager(50);
		AutoSaver save = new AutoSaver(manager);
		save.setDaemon(true);
		manager.readAccount();
		
		while(true) {
			showMenu();
			System.out.println("요청하실 번호를 입력하세요 >> ");
			int choice = 0;
			
			try {
				choice = scan.nextInt();
			
				if(!(choice<0 || choice>5)) {
					System.out.println("");
					switch(choice) {
					
					case ICustomDefine.MAKE :
						manager.makeAccount();
						break;
					case ICustomDefine.DEPOSIT :
						manager.depositMoney();
						break;
					case ICustomDefine.WITHDRAW :
						manager.withdrawMoney();
						break;
					case ICustomDefine.INQUIRE :
						manager.showAccInfo();
						break;
					case ICustomDefine.EXIT :
						System.out.println("프로그램종료");
						manager.saveAccount();
						return;
					case 0:
						manager.autoSave(save);
						break;
					}
				}
				else {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
			}
			catch (MenuSelectException e) {
				System.err.println("입력오류] 0~5 사이 숫자를 입력하시오");
				System.out.println(" ");
			}
			catch(InputMismatchException e){
				scan.nextLine();
				System.err.println("입력오류] 0~5 사이 숫자를 입력하시오.");
				System.out.println(" ");
			}
		}
	}
}
