package dto;

import java.sql.Date;

public class UsersDto implements Dto {
	/* Users Table DTO */
	private String userId;
	private String password;
	private String name;
	private String email;
	private Date birthDay;
	private Date regDate;
	
	public UsersDto() {}
	
	public UsersDto(String userId, String password, String name, String email, Date birthDay, Date regDate) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.birthDay = birthDay;
		this.regDate = regDate;
	}
	
	/* Getter Start */
	public String getUserId() {
		return this.userId;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Date getBirthDay() {
		return this.birthDay;
	}
	
	public Date getRegDate() {
		return this.regDate;
	}
	/* Getter End */
	
	/* Setter Start */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	/* Setter End */

	@Override
	public String info() {
		return "UsersDto [userId=" + userId + ", name=" + name + ", email=" + email
				+ ", birthDay=" + birthDay + ", regDate=" + regDate + "]";
	}
	
	
	
}