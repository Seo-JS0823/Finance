package entity;

import java.sql.Date;

import dto.UsersDto;

public class UsersEntity {
	/* Users Table Field */
	private String userId;
	private String password;
	private String name;
	private String email;
	private Date birthDay;
	private Date regDate;
	private int flag;
	
	/* NoArgsConstructor */
	public UsersEntity() {}
	
	/* AllArgsConstructor */
	public UsersEntity(String userId, String password, String name, String email, Date birthDay, Date regDate, int flag) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.birthDay = birthDay;
		this.regDate = regDate;
		this.flag = flag;
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
	
	public int getFlag() {
		return this.flag;
	}
	/* Getter End */
	
	public UsersDto toDto() {
		return new UsersDto(userId, password, name, email, birthDay, regDate);
	}
	
}
