package shopping;

import java.sql.DriverManager;

public interface IConnect {
	
	String ORACLE_DRIVER = "oracle.jdbc.OracleDriver";
	String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	void connect();
	void execute();
	void close();
	String scanvalue(String title);
}
