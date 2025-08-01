package repository.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetStrategy {
	ResultSet work(PreparedStatement pstmt) throws SQLException;
}
