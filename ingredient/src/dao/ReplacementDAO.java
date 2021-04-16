package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import util.SysTime;

import entity.Replacement;

public class ReplacementDAO {
	public void save(Replacement replace) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO ingre_replacement(purchase_id, material_id, material_name, sort, material_spec, unit, num, material_provider, equipment_use, reason, opdate, dept_name, worktype_name, operator, worktype_belong, remark)" + 
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		prep.setString(1, replace.getPurchaseId());
		prep.setString(2, replace.getMaterialId());
		prep.setString(3, replace.getMaterialName());
		prep.setString(4, replace.getSort());
		prep.setString(5, replace.getMaterialSpec());
		prep.setString(6, replace.getUnit());
		prep.setInt(7, replace.getNum());
		prep.setString(8, replace.getMaterialProvider());
		prep.setString(9, replace.getEquipmentUse());
		prep.setString(10, replace.getReason());
		prep.setString(11, replace.getOpdate());
		prep.setString(12, replace.getDeptNname());
		prep.setString(13, replace.getWorktypeName());
		prep.setString(14, replace.getOperator());
		prep.setString(15, replace.getWorkTypeBelong());
		prep.setString(16, replace.getReason());
		prep.executeUpdate();
		DBUtil.close(conn);
	}
	public List<Replacement> findAll(String dept,String userName) throws Exception{
		List<Replacement> lists = new ArrayList<Replacement>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_replacement.*, ingre_order.`status` FROM ingre_replacement INNER JOIN ingre_order ON ingre_replacement.purchase_id = ingre_order.purchase_id where ingre_replacement.dept_name=? and ingre_replacement.operator =? and ingre_replacement.opdate=?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, SysTime.GetCurDate());
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			Replacement replacement = new Replacement();
			replacement.setPurchaseId(rst.getString("purchase_id"));
			replacement.setMaterialId(rst.getString("material_id"));
			replacement.setMaterialName(rst.getString("material_name"));
			replacement.setSort(rst.getString("sort"));
			replacement.setMaterialSpec(rst.getString("material_spec"));
			replacement.setUnit(rst.getString("unit"));
			replacement.setNum(rst.getInt("num"));
			replacement.setMaterialProvider(rst.getString("material_provider"));
			replacement.setEquipmentUse(rst.getString("equipment_use"));
			replacement.setReason(rst.getString("reason"));
			replacement.setOpdate(rst.getString("opdate"));
			replacement.setDeptNname(rst.getString("dept_name"));
			replacement.setWorktypeName(rst.getString("worktype_name"));
			replacement.setOperator(rst.getString("operator"));
			replacement.setWorkTypeBelong(rst.getString("worktype_belong"));
			replacement.setRemark(rst.getString("remark"));
			replacement.setStatus(rst.getString("status"));
			lists.add(replacement);
			
		}
		DBUtil.close(conn);
 		return lists;
	}
	public List<Replacement> findAllByDate(String dept, String userName, String startDate, String endDate) throws Exception{
		List<Replacement> lists = new ArrayList<Replacement>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_replacement.*, ingre_order.`status` FROM ingre_replacement INNER JOIN ingre_order ON ingre_replacement.purchase_id = ingre_order.purchase_id where ingre_replacement.dept_name=? and ingre_replacement.operator =? and ingre_replacement.opdate between ? and ?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, startDate);
		prep.setString(4, endDate);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			Replacement replacement = new Replacement();
			replacement.setPurchaseId(rst.getString("purchase_id"));
			replacement.setMaterialId(rst.getString("material_id"));
			replacement.setMaterialName(rst.getString("material_name"));
			replacement.setSort(rst.getString("sort"));
			replacement.setMaterialSpec(rst.getString("material_spec"));
			replacement.setUnit(rst.getString("unit"));
			replacement.setNum(rst.getInt("num"));
			replacement.setMaterialProvider(rst.getString("material_provider"));
			replacement.setEquipmentUse(rst.getString("equipment_use"));
			replacement.setReason(rst.getString("reason"));
			replacement.setOpdate(rst.getString("opdate"));
			replacement.setDeptNname(rst.getString("dept_name"));
			replacement.setWorktypeName(rst.getString("worktype_name"));
			replacement.setOperator(rst.getString("operator"));
			replacement.setWorkTypeBelong(rst.getString("worktype_belong"));
			replacement.setRemark(rst.getString("remark"));
			replacement.setStatus(rst.getString("status"));
			lists.add(replacement);
		}
		DBUtil.close(conn);
 		return lists;
	}
	
	public Replacement findAllByPurcheseID(String purcheseId) throws Exception{
		Replacement replacement = new Replacement();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_replacement.*, ingre_order.`status` FROM ingre_replacement INNER JOIN ingre_order ON ingre_replacement.purchase_id = ingre_order.purchase_id where ingre_replacement.purchase_id=? ");
		prep.setString(1, purcheseId);
		ResultSet rst = prep.executeQuery();
		if(rst.next()){
			replacement.setPurchaseId(rst.getString("purchase_id"));
			replacement.setMaterialId(rst.getString("material_id"));
			replacement.setMaterialName(rst.getString("material_name"));
			replacement.setSort(rst.getString("sort"));
			replacement.setMaterialSpec(rst.getString("material_spec"));
			replacement.setUnit(rst.getString("unit"));
			replacement.setNum(rst.getInt("num"));
			replacement.setMaterialProvider(rst.getString("material_provider"));
			replacement.setEquipmentUse(rst.getString("equipment_use"));
			replacement.setReason(rst.getString("reason"));
			replacement.setOpdate(rst.getString("opdate"));
			replacement.setDeptNname(rst.getString("dept_name"));
			replacement.setWorktypeName(rst.getString("worktype_name"));
			replacement.setOperator(rst.getString("operator"));
			replacement.setWorkTypeBelong(rst.getString("worktype_belong"));
			replacement.setRemark(rst.getString("remark"));
			replacement.setStatus(rst.getString("status"));
		}
		DBUtil.close(conn);
 		return replacement;
	}
	
	public void deleteByPurcheseID(String purcheseId) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM ingre_replacement where purchase_id = ?");
		prep.setString(1, purcheseId);
		prep.execute();
	}
}
