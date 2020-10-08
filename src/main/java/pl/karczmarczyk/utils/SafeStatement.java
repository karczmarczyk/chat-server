package pl.karczmarczyk.utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SafeStatement {

    private PreparedStatement preparedStatement = null;
    private Connection connection = null;

    public SafeStatement(PreparedStatement preparedStatement) {

    }

    public SafeStatement(Connection connection, String insertTableSQL) throws SQLException {
	this.connection = connection;
	preparedStatement = connection.prepareStatement(insertTableSQL);
    }

    public int execute() throws SQLException {
	return preparedStatement.executeUpdate();
    }

    public void close() throws SQLException {
	preparedStatement.close();
    }

    public Long getSequence(String sequenceName) throws SQLException {
	Long retVal = null;
	PreparedStatement ps = connection.prepareStatement("SELECT nextval('" + sequenceName + "');");
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
	    retVal = rs.getLong(1);
	}
	rs.close();
	ps.close();
	return retVal;
    }

    public void setLong(int index, Long value) throws SQLException {
	if (null != value) {
	    preparedStatement.setLong(index, value);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setInt(int index, Integer value) throws SQLException {
	if (null != value) {
	    preparedStatement.setInt(index, value);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}

    }

    public void setTimestamp(int index, Timestamp value) throws SQLException {
	if (null != value) {
	    preparedStatement.setTimestamp(index, value);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }
    
    public void setLocalDateTime(int index, LocalDateTime value) throws SQLException {
	if (null != value) {
	    Timestamp timestamp = Timestamp.valueOf(value);
	    preparedStatement.setTimestamp(index, timestamp);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setBigDec(int index, BigDecimal value) throws SQLException {
	if (null != value) {
	    preparedStatement.setBigDecimal(index, value);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setDouble(int index, Double value) throws SQLException {
	if (null != value) {
	    preparedStatement.setDouble(index, value);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setDouble(int index, Integer value) throws SQLException {
	if (null != value) {
	    preparedStatement.setDouble(index, value.doubleValue());
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setBoolean(int index, boolean value) throws SQLException {
	preparedStatement.setBoolean(index, value);
    }

    public void setString(int index, String value) throws SQLException {
	if (null != value) {
	    preparedStatement.setString(index, value);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setDate(int index, java.util.Date value) throws SQLException {
	if (null != value) {
	    java.sql.Date date = new java.sql.Date(value.getTime());
	    preparedStatement.setDate(index, date);
	} else {
	    preparedStatement.setNull(index, java.sql.Types.NULL);
	}
    }

    public void setNull(int index) throws SQLException {
	preparedStatement.setNull(index, java.sql.Types.NULL);

    }

    public void show() {
	System.out.println(preparedStatement.toString());

    }

    public String toString() {
	return preparedStatement.toString();
    }

}
