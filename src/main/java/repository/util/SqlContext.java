package repository.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UsersDto;
import entity.UsersEntity;

public class SqlContext {
	
	private DBAccess access;
	
	/* SqlContext init */
	public SqlContext() {
		access = DBAccess.getInstance();
	}
	
	/* INSERT, UPDATE, DELETE 외부 제공 람다식 */
	public void executeUpdate(StatementStrategy pstmt) {
		this.updateStatement(pstmt);
	}
	
	/* SELECT 외부 제공 람다식 */
	public UsersEntity executeQuery(StatementStrategy ps, ResultSetStrategy rs) {
		return this.queryStatement(ps, rs);
	}
	
	public static void main(String[] args) {
		SqlContext sc = new SqlContext();
		UsersDto dto = sc.getDto("user01", "pass001");
		System.out.println(dto.info());
	}
	
	/* repository method test */
	public UsersDto getDto(final String userid, final String password) {
		UsersEntity dto = this.executeQuery(
			new StatementStrategy() {
				public PreparedStatement work(Connection c) throws SQLException {
					PreparedStatement ps = c.prepareStatement("select * from users where userid = ? and password = ?");
					ps.setString(1, userid);
					ps.setString(2, password);
					return ps;
				}
			},
			new ResultSetStrategy() {
				public ResultSet work(PreparedStatement pstmt) throws SQLException {
					ResultSet rs = pstmt.executeQuery();
					
					return rs;
				}
			}
		);
		
		return dto.toDto();
	}
	
	/* INSERT, UPDATE, DELETE => 람다식 메소드 */
	private void updateStatement(StatementStrategy pstmt) {
		Connection con = access.getConnection();
		PreparedStatement ps = null;
		
		try {
			ps = pstmt.work(con);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQLException SqlContext: " + e.getMessage());
		} finally {
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {}
		}
	}
	
	/* SELECT => 람다식 메소드 */
	private UsersEntity queryStatement(StatementStrategy pstmt, ResultSetStrategy resultSet) {
		Connection con = access.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		UsersEntity entity = null;
		
		try {
			ps = pstmt.work(con);
			
			rs = resultSet.work(ps);
			
			if(rs.next()) {
				entity = new UsersEntity(
					rs.getString("userid"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getDate("birthday"),
					rs.getDate("regdate"),
					rs.getInt("flag")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQLException SqlContext: " + e.getMessage());
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {}
		}
		
		
		return entity;
	}
	
}
