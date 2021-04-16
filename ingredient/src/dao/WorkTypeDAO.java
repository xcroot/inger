package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class WorkTypeDAO {
	public List<String> getAllWorkType(String deptName) throws Exception{
		List<String> lists = new ArrayList<String>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("select worktype_name from ingre_worktype where dept_name=?");
		prep.setString(1,deptName);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			lists.add(rst.getString("worktype_name"));
		}
		DBUtil.close(conn);
		return lists;
	}
}
