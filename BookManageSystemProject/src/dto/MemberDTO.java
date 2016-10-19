package dto;

public class MemberDTO {
	private String name;
	private String address;
	private String phoneNumber;
	private String birthday;
	private String comment;
	private int memberNo;
	
	public MemberDTO(){
		
	}
	
	public MemberDTO(String name, String address, String phoneNumber, String birthday, String comment) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.comment = comment;
		
	}
	public MemberDTO(int memberNo,String name, String address, String phoneNumber, String birthday, String comment) {
		this.memberNo = memberNo;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.comment = comment;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	
}
