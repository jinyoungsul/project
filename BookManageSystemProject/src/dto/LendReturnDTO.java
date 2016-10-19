package dto;

public class LendReturnDTO {
	private int LendReturnNo;
	private int memberNo;
	private int bookNo;
	private String LendDate;
	private String ReturnDate;
	private String isOverdue;
	private int money;
	
	public LendReturnDTO(){
		
	}

	public LendReturnDTO(int memberNo, int bookNumber, String lendDate, String returnDate, int money) {
		this.memberNo = memberNo;
		this.bookNo = bookNumber;
		this.LendDate = lendDate;
		this.ReturnDate = returnDate;
		this.money = money;
	}

	public int getLendReturnNo() {
		return LendReturnNo;
	}

	public void setLendReturnNo(int lendReturnNo) {
		LendReturnNo = lendReturnNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getLendDate() {
		return LendDate;
	}

	public void setLendDate(String lendDate) {
		LendDate = lendDate;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}

	public String getIsOverdue() {
		return isOverdue;
	}

	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
