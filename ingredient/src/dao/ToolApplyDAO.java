package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import util.SysTime;

import entity.ToolApply;

public class ToolApplyDAO {
	public void save(ToolApply tool) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = 
			conn.prepareStatement("INSERT INTO ingre_tool_apply(purchase_id, material_id, material_name, brand_name, material_spec, unit, num, frequency, single_use, multi_use, reason, opdate, dept_name, worktype_name, operator, worktype_belong, remark) "+
				                                                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		prep.setString(1, tool.getPurchaseId());
		prep.setString(2, tool.getMaterialId());
		prep.setString(3, tool.getMaterialName());
		prep.setString(4, tool.getBrandName());
		prep.setString(5, tool.getMaterialSpec());
		prep.setString(6, tool.getUnit());
		prep.setInt(7, tool.getNum());
		prep.setString(8, tool.getFrequency());
		prep.setString(9, tool.getSingleUse());
		prep.setString(10, tool.getMultiUse());
		prep.setString(11, tool.getReason());
		prep.setString(12, tool.getOpdate());
		prep.setString(13, tool.getDeptName());
		prep.setString(14, tool.getWorkTypeName());
		prep.setString(15, tool.getOperator());
		prep.setString(16, tool.getWorkTypeBelong());
		prep.setString(17, tool.getRemark());
		prep.executeUpdate();
		DBUtil.close(conn);
	}
	
	public List<ToolApply> findAll(String dept,String userName) throws Exception{
		List<ToolApply> lists = new ArrayList<ToolApply>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_tool_apply.*, ingre_order.`status` FROM ingre_tool_apply INNER JOIN ingre_order ON ingre_tool_apply.purchase_id = ingre_order.purchase_id where ingre_tool_apply.dept_name=? and ingre_tool_apply.operator =? and ingre_tool_apply.opdate=?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, SysTime.GetCurDate());
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			ToolApply toolApply = new ToolApply();
			toolApply.setPurchaseId(rst.getString("purchase_id"));
			toolApply.setMaterialId(rst.getString("material_id"));
			toolApply.setMaterialName(rst.getString("material_name"));
			toolApply.setBrandName(rst.getString("brand_name"));
			toolApply.setMaterialSpec(rst.getString("material_spec"));
			toolApply.setUnit(rst.getString("unit"));
			toolApply.setNum(rst.getInt("num"));
			toolApply.setFrequency(rst.getString("frequency"));
			toolApply.setSingleUse(rst.getString("single_use"));
			toolApply.setMultiUse(rst.getString("multi_use"));
			toolApply.setReason(rst.getString("reason"));
			toolApply.setOpdate(rst.getString("opdate"));
			toolApply.setDeptName(rst.getString("dept_name"));
			toolApply.setWorkTypeName(rst.getString("worktype_name"));
			toolApply.setOperator(rst.getString("operator"));
			toolApply.setWorkTypeBelong(rst.getString("worktype_belong"));
			toolApply.setRemark(rst.getString("remark"));
			toolApply.setStatus(rst.getString("status"));
			lists.add(toolApply);
			
		}
		DBUtil.close(conn);
 		return lists;
	}
	public List<ToolApply> findAllByDate(String dept,String userName,String startDate, String endDate) throws Exception{
		List<ToolApply> lists = new ArrayList<ToolApply>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_tool_apply.*, ingre_order.`status` FROM ingre_tool_apply INNER JOIN ingre_order ON ingre_tool_apply.purchase_id = ingre_order.purchase_id where ingre_tool_apply.dept_name=? and ingre_tool_apply.operator =? and ingre_tool_apply.opdate between ? and ?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, startDate);
		prep.setString(4, endDate);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			ToolApply toolApply = new ToolApply();
			toolApply.setPurchaseId(rst.getString("purchase_id"));
			toolApply.setMaterialId(rst.getString("material_id"));
			toolApply.setMaterialName(rst.getString("material_name"));
			toolApply.setBrandName(rst.getString("brand_name"));
			toolApply.setMaterialSpec(rst.getString("material_spec"));
			toolApply.setUnit(rst.getString("unit"));
			toolApply.setNum(rst.getInt("num"));
			toolApply.setFrequency(rst.getString("frequency"));
			toolApply.setSingleUse(rst.getString("single_use"));
			toolApply.setMultiUse(rst.getString("multi_use"));
			toolApply.setReason(rst.getString("reason"));
			toolApply.setOpdate(rst.getString("opdate"));
			toolApply.setDeptName(rst.getString("dept_name"));
			toolApply.setWorkTypeName(rst.getString("worktype_name"));
			toolApply.setOperator(rst.getString("operator"));
			toolApply.setWorkTypeBelong(rst.getString("worktype_belong"));
			toolApply.setRemark(rst.getString("remark"));
			toolApply.setStatus(rst.getString("status"));
			lists.add(toolApply);
			
		}
		DBUtil.close(conn);
 		return lists;
	}
	public ToolApply findAllByPurcheseID(String purcheseId) throws Exception{
		ToolApply toolApply = new ToolApply();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_tool_apply.*, ingre_order.`status` FROM ingre_tool_apply INNER JOIN ingre_order ON ingre_tool_apply.purchase_id = ingre_order.purchase_id where ingre_tool_apply.purchase_id=? ");
		prep.setString(1, purcheseId);

		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			toolApply.setPurchaseId(rst.getString("purchase_id"));
			toolApply.setMaterialId(rst.getString("material_id"));
			toolApply.setMaterialName(rst.getString("material_name"));
			toolApply.setBrandName(rst.getString("brand_name"));
			toolApply.setMaterialSpec(rst.getString("material_spec"));
			toolApply.setUnit(rst.getString("unit"));
			toolApply.setNum(rst.getInt("num"));
			toolApply.setFrequency(rst.getString("frequency"));
			toolApply.setSingleUse(rst.getString("single_use"));
			toolApply.setMultiUse(rst.getString("multi_use"));
			toolApply.setReason(rst.getString("reason"));
			toolApply.setOpdate(rst.getString("opdate"));
			toolApply.setDeptName(rst.getString("dept_name"));
			toolApply.setWorkTypeName(rst.getString("worktype_name"));
			toolApply.setOperator(rst.getString("operator"));
			toolApply.setWorkTypeBelong(rst.getString("worktype_belong"));
			toolApply.setRemark(rst.getString("remark"));
			toolApply.setStatus(rst.getString("status"));
		}
		DBUtil.close(conn);
 		return toolApply;
	}
	public void deleteByPurcheseID(String purcheseId) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM ingre_tool_apply where purchase_id = ?");
		prep.setString(1, purcheseId);
		prep.execute();
	}
}
