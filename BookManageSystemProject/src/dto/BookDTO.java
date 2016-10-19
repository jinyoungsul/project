package dto;

public class BookDTO {
	// 멤버변수
	private int bookNumber;
	private String title;
	private String lending;
	private int lendingNumber;
	private String author;
	private String publicationDate;
	private String type;
	private int money;
	private String comment;

	// 생성자
	public BookDTO() {
	}
	public BookDTO(String title,String lending) {
		this.title = title;
		this.lending = lending;
	}
	public BookDTO(int bookNumber, String title, String lending, int lendingNumber, String author,
			String publicationDate, String type, int money,String comment) {
		this.bookNumber = bookNumber;
		this.title = title;
		this.lending = lending;
		this.lendingNumber = lendingNumber;
		this.author = author;
		this.publicationDate = publicationDate;
		this.type = type;
		this.money = money;
		this.comment = comment;
	}
	public BookDTO(int bookNumber, String title, String author,
			String publicationDate, String type, int money,String comment) {
		this.bookNumber = bookNumber;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.type = type;
		this.money = money;
		this.comment = comment;
	}
	public BookDTO(int bookNumber, String title, String author,String publicationDate,String type, String comment) {
		this.bookNumber = bookNumber;
		this.title = title;
		this.author = author;
		this.publicationDate = publicationDate;
		this.type = type;
		this.comment = comment;
	}


	

	public BookDTO(int bookNumber, int money) {
		// TODO Auto-generated constructor stub
		this.bookNumber = bookNumber;
		this.money = money;
	}

	// Getter/Setter
	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLending() {
		return lending;
	}

	public void setLending(String lending) {
		this.lending = lending;
	}

	public int getLendingNumber() {
		return lendingNumber;
	}

	public void setLendingNumber(int lendingNumber) {
		this.lendingNumber = lendingNumber;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
