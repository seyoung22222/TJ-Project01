package banking;

import java.util.HashSet;

public class AutoSaver extends Thread {
	
	AccountManager manager;
	
	public AutoSaver(AccountManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("(..자동저장 중..)");
				manager.txtsaver();
				sleep(5000);
			}
			catch (InterruptedException e) {
				System.err.println("자동저장을 종료합니다.");
				break;
			}
		}
	}
}
