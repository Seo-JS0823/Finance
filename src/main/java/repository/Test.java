package repository;

import java.sql.Date;

import dto.UsersDto;

public class Test {
	public static void main(String[] args) {
		UsersRepository test = new UsersRepository();
		UsersDto dto = new UsersDto();
		
		dto.setPassword("pass02");
		dto.setName("name02");
		dto.setEmail("user02@naver.com");
		dto.setBirthDay(Date.valueOf("2017-01-25"));
		
		test.findUserid(dto);
		
		test.login(dto);
	}
}
