package dto;

public class SalesDTO {
	private String salesDate;
	private String name;
	private String memberNo;
	private String bookName;
	private int money;
	
	public SalesDTO(){
		
	}

	public SalesDTO(String salesDate, String name, String memberNo, String bookName, int money) {
		super();
		this.salesDate = salesDate;
		this.name = name;
		this.memberNo = memberNo;
		this.bookName = bookName;
		this.money = money;
	}

	public String getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(String salesDate) {
		this.salesDate = salesDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
