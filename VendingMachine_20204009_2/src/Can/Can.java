package Can;

//음료 클래스

public class Can {

	String canName;  // 음료 이름
	int canNum;  // 음료 개수
	int canPrice;  // 음료 가격

	public Can(String canName, int canNum, int canPrice) {
		super();
		this.canName = canName;
		this.canNum = canNum;
		this.canPrice = canPrice;

	}

	@Override
	public String toString() {
		return "[음료 이름 : " + canName + ", 음료 개수 : " + canNum + " 음료 가격 : " + canPrice + "]";
	}

	public String getCanName() {
		return canName;
	}

	public void setCanName(String canName) {
		this.canName = canName;
	}

	public int getCanNum() {
		return canNum;
	}

	public void setCanNum(int canNum) {
		this.canNum = canNum;
	}

	public int getCanPrice() {
		return canPrice;
	}

	public void setCanPrice(int canPrice) {
		this.canPrice = canPrice;
	}
}
