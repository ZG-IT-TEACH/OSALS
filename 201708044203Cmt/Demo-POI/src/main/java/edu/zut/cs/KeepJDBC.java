package edu.zut.cs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class KeepJDBC {
 
	public static int insert(String[] excelCell,int mark) {
		Connection conn=JDBC.getConn();
		String sql="insert into softwarestu(stu_no, grade, major,stu_class,num,stu_id,stu_name,sex) values(?,?,?,?,?,?,?,?)";
		PreparedStatement psmt;
		try {
			psmt=(PreparedStatement)conn.prepareStatement(sql);
			psmt.setInt(1,Integer.parseInt(excelCell[mark++].trim()));
			psmt.setInt(2,Integer.parseInt(excelCell[mark++].trim()));
			psmt.setString(3, excelCell[mark++].trim());
			psmt.setString(4, excelCell[mark++].trim());
			if((excelCell[mark])==null||(excelCell[mark]).equals("")||(excelCell[mark]).equals("null")) {
			    psmt.setInt(5,0);
			    mark++;
			}else {
				psmt.setInt(5,Integer.parseInt(excelCell[mark++].trim()));
			}
			psmt.setString(6,excelCell[mark++].trim());
			psmt.setString(7, excelCell[mark++].trim());
			psmt.setString(8, excelCell[mark++].trim());
			psmt.executeUpdate();
			psmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mark;
	}
	
}
