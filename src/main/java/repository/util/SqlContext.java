package repository.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlContext {
	
	private DBAccess access;
	
	/* SqlContext init */
	public SqlContext() {
		access = DBAccess.getInstance();
	}
	
	/**
	 * <ul>
	 * <li>INSERT, UPDATE, DELETE 메소드용 람다식
	 * sql.executeUpdate(new StatementStrategy() {
	 *    public PreparedStatement work(Connection c) throws SQLException {
	 *    }
	 * });
	 * </ul>
	 * @param StatementStrategy Interface
	 * 
	 * @throws SQLException
	 */
	public void executeUpdate(StatementStrategy pstmt) {
		this.updateStatement(pstmt);
	}
	
	/* SELECT 외부 제공 람다식 */
	public void executeQuery(StatementStrategy ps, ResultSetStrategy rs) {
		this.queryStatement(ps, rs);
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
	private void queryStatement(StatementStrategy pstmt, ResultSetStrategy resultSet) {
		Connection con = access.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = pstmt.work(con);
			
			rs = resultSet.work(ps);
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
	}
	
}
