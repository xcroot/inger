package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import util.SysTime;
import entity.ApplyOrder;

public class ApplyOrderDAO {
	public void save(ApplyOrder order) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =
			conn.prepareStatement("INSERT INTO ingre_order(purchase_id, material_id, material_name, material_spec, unit, num, worktype_belong, remark, dept_id, worktype_id, creat_time, operator, `status`)" + 
					                                       " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		    prep.setString(1, order.getPurchaseId());
		    prep.setString(2, order.getMaterialId());
		    prep.setString(3, order.getMaterialName());
		    prep.setString(4, order.getMaterialSpec());
		    prep.setString(5, order.getUnit());
		    prep.setInt(6, order.getNum());
		    prep.setString(7, order.getWorkTypeBelong());
		    prep.setString(8, order.getRemark());
		    prep.setString(9, order.getDeptName());
		    prep.setString(10, order.getWorkTypeName());
		    prep.setString(11, order.getOpdate());
		    prep.setString(12, order.getOperator());
		    prep.setString(13, order.getStatus());
		    prep.executeUpdate();
		    DBUtil.close(conn);
	}
	
	public List<ApplyOrder> findAll(String dept,String userName) throws Exception{
		List<ApplyOrder> lists = new ArrayList<ApplyOrder>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_order where dept_id=? and operator=? and creat_time =?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, SysTime.GetCurDate());
		
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			ApplyOrder order = new ApplyOrder();
			order.setPurchaseId(rst.getString("purchase_id"));
			order.setMaterialId(rst.getString("material_id"));
			order.setMaterialName(rst.getString("material_name"));
			order.setMaterialSpec(rst.getString("material_spec"));
			order.setUnit(rst.getString("unit"));
			order.setNum(rst.getInt("num"));
			order.setWorkTypeBelong(rst.getString("worktype_belong"));
			order.setRemark(rst.getString("remark"));
			order.setDeptName(rst.getString("dept_id"));
			order.setWorkTypeName(rst.getString("worktype_id"));
			order.setOpdate(rst.getString("creat_time"));
			order.setOperator(rst.getString("operator"));
			order.setStatus(rst.getString("status"));
			order.setDealDate(rst.getString("deal_time"));
			order.setDealer(rst.getString("dealer"));
			order.setReason(rst.getString("reason"));
			lists.add(order);
		}
		DBUtil.close(conn);
		return lists;
	}
	public List<ApplyOrder> findAllByTime(String dept, String userName, String startTime, String endTime) throws Exception{
		List<ApplyOrder> lists = new ArrayList<ApplyOrder>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_order where dept_id=? and operator=? and creat_time between ? and ?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, startTime);
		prep.setString(4, endTime);
		
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			ApplyOrder order = new ApplyOrder();
			order.setPurchaseId(rst.getString("purchase_id"));
			order.setMaterialId(rst.getString("material_id"));
			order.setMaterialName(rst.getString("material_name"));
			order.setMaterialSpec(rst.getString("material_spec"));
			order.setUnit(rst.getString("unit"));
			order.setNum(rst.getInt("num"));
			order.setWorkTypeBelong(rst.getString("worktype_belong"));
			order.setRemark(rst.getString("remark"));
			order.setDeptName(rst.getString("dept_id"));
			order.setWorkTypeName(rst.getString("worktype_id"));
			order.setOpdate(rst.getString("creat_time"));
			order.setOperator(rst.getString("operator"));
			order.setStatus(rst.getString("status"));
			order.setDealDate(rst.getString("deal_time"));
			order.setDealer(rst.getString("dealer"));
			order.setReason(rst.getString("reason"));
			lists.add(order);
		}
		DBUtil.close(conn);
		return lists;
	}
	
	public void deleteByPurcheseID(String purcheseId) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM ingre_order where purchase_id = ?");
		prep.setString(1, purcheseId);
		prep.execute();
	}
}
