package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UsersDto;
import repository.util.ResultSetStrategy;
import repository.util.SqlContext;
import repository.util.StatementStrategy;

public class UsersRepository {
	private SqlContext sql = new SqlContext();
	
	/**
	 * 회원가입 메소드
	 * <ul>
	 * <li>UsersDto 에서 필요한 필드
	 * <li>String userid
	 * <li>String password
	 * <li>String name
	 * <li>String email
	 * <li>java.sql.Date birthDay
	 * </ul>
	 * @param usersDto
	 * @throws SQLException
	 * 
	 * @return void
	 */
	public void signin(final UsersDto usersDto) {
		sql.executeUpdate(new StatementStrategy() {
			public PreparedStatement work(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("insert into users (userid, password, name, email, birthday) values (?, ?, ?, ?, ?)");
				
				ps.setString(1, usersDto.getUserId());
				ps.setString(2, usersDto.getPassword());
				ps.setString(3, usersDto.getName());
				ps.setString(4, usersDto.getEmail());
				ps.setDate(5, usersDto.getBirthDay());
				
				return ps;
			}
		});
		
		System.out.println("회원가입 완료: " + usersDto.info());
	}
	
	/**
	 * 로그인 메소드
	 * <ul>
	 * <li>UsersDto 에서 필요한 필드
	 * <li>String userid
	 * <li>String password
	 * </ul>
	 * @param usersDto
	 * @throws SQLException
	 * 
	 * @return void
	 */
	public void login(final UsersDto usersDto) {
		sql.executeQuery(new StatementStrategy() {
			public PreparedStatement work(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM users WHERE userid = ? AND password = ?");
				
				ps.setString(1, usersDto.getUserId());
				ps.setString(2, usersDto.getPassword());
				
				return ps;
			}
		}, new ResultSetStrategy() {
			public ResultSet work(PreparedStatement pstmt) throws SQLException {
				ResultSet rs = pstmt.executeQuery();
				UsersDto selected = null;
				while(rs.next()) {
					selected = new UsersDto(
						rs.getString("userid"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getDate("birthday"),
						rs.getDate("regdate")
					);
					System.out.println("로그인 완료: " + selected.info());
				}
				return rs;
			}
		});
	}

	/**
	 * 비밀번호 찾기 메소드
	 * <ul>
	 * <li>UsersDto 에서 필요한 필드
	 * <li>String userid
	 * <li>String name
	 * <li>String email
	 * </ul>
	 * 
	 * @param usersDto
	 * @throws SQLException
	 * 
	 * @return void
	 */
	public void findPassword(final UsersDto usersDto) {
		sql.executeQuery(new StatementStrategy() {
			public PreparedStatement work(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("select password from users where userid = ? and name = ? and email = ?");
				
				ps.setString(1, usersDto.getUserId());
				ps.setString(2, usersDto.getName());
				ps.setString(3, usersDto.getEmail());
				
				return ps;
			}
		}, new ResultSetStrategy() {
			public ResultSet work(PreparedStatement pstmt) throws SQLException {
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					String password = rs.getString("password");
					usersDto.setPassword(password);
					System.out.println("비밀번호: " + password);
				}
				return rs;
			}
		}); 
	}
	
	/**
	 * 아이디 찾기 메소드
	 * <ul>
	 * <li>UsersDto 에서 필요한 필드
	 * <li>String password
	 * <li>String name
	 * <li>String email
	 * </ul>
	 * 
	 * @param usersDto
	 * @throws SQLException
	 * 
	 * @return void
	 */
	public void findUserid(final UsersDto usersDto) {
		sql.executeQuery(new StatementStrategy() {
			public PreparedStatement work(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("select userid from users where password = ? and name = ? and email = ?");
				
				ps.setString(1, usersDto.getPassword());
				ps.setString(2, usersDto.getName());
				ps.setString(3, usersDto.getEmail());
				
				return ps;
			}
		}, new ResultSetStrategy() {
			public ResultSet work(PreparedStatement pstmt) throws SQLException {
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					String userid = rs.getString("userid");
					usersDto.setUserId(userid);
					System.out.println("아이디: " + userid);
				}
				return rs;
			}
		});
	}

	/**
	 * 유저 정보 변경
	 * 
	 * @param usersDto
	 */
	public void userInfoChange(final UsersDto usersDto) {
		sql.executeUpdate(new StatementStrategy() {
			public PreparedStatement work(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("");
				
				System.out.println(ps);
				
				return null;
			}
			
		});
	}
	
	
}
