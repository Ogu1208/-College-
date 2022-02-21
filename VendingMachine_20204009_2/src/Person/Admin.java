package Person;

// 관리자 클래스
public class Admin {
	
	public static int totalMoney = 10000;  // 총 매출액 기본값 설정(전역변수)
	public static String password;  // 비밀번호(전역변수)
	
	public Admin(){}

	public static int getTotalMoney() {
		return totalMoney;
	}

	public static void setTotalMoney(int totalMoney) {
		Admin.totalMoney = totalMoney;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static void setPassword(String password) {
		// 비밀번호 설정
		Admin.password = password;
	}
}
