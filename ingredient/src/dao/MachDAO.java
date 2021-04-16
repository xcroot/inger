package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import util.SysTime;

import entity.Mach;

public class MachDAO {
	public void save(Mach mach) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO ingre_mach(purchase_id, material_id, material_name, material_spec, unit, num, opdate, dept_name, wroktype_name, related_pe, material_provider, operator, ischecked, `use`, worktype_belong, remark)" 
				            +" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		prep.setString(1, mach.getPurchaseId());
		prep.setString(2, mach.getMaterialId());
		prep.setString(3, mach.getMaterialName());
		prep.setString(4, mach.getMaterialSpec());
		prep.setString(5, mach.getUnit());
		prep.setInt(6, mach.getNum());
		prep.setString(7, mach.getOpdate());
		prep.setString(8, mach.getDeptNname());
		prep.setString(9, mach.getWorktypeName());
		prep.setString(10, mach.getRelatedPe());
		prep.setString(11, mach.getMaterialProvider());
		prep.setString(12, mach.getOperator());
		prep.setString(13, mach.getIsChecked());
		prep.setString(14, mach.getUse());
		prep.setString(15, mach.getWorkTypeBelong());
		prep.setString(16, mach.getRemark());
		prep.executeUpdate();
		DBUtil.close(conn);
 	}
	
	public List<Mach> findAll(String dept,String userName) throws Exception{
		List<Mach> lists = new ArrayList<Mach>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_mach.*, ingre_order.`status` FROM ingre_mach INNER JOIN ingre_order ON ingre_mach.purchase_id = ingre_order.purchase_id where ingre_mach.dept_name=? and ingre_mach.operator =? and ingre_mach.opdate=?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, SysTime.GetCurDate());
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			Mach mach = new Mach();
			mach.setPurchaseId(rst.getString("purchase_id"));
			mach.setMaterialId(rst.getString("material_id"));
			mach.setMaterialName(rst.getString("material_name"));
			mach.setMaterialSpec(rst.getString("material_spec"));
			mach.setUnit(rst.getString("unit"));
			mach.setNum(rst.getInt("num"));
			mach.setOpdate(rst.getString("opdate"));
			mach.setDeptNname(rst.getString("dept_name"));
			mach.setWorktypeName(rst.getString("wroktype_name"));
			mach.setRelatedPe(rst.getString("related_pe"));
			mach.setMaterialProvider(rst.getString("material_provider"));
			mach.setOperator(rst.getString("operator"));
			mach.setIsChecked(rst.getString("ischecked"));
			mach.setUse(rst.getString("use"));
			mach.setWorkTypeBelong(rst.getString("worktype_belong"));
			mach.setRemark(rst.getString("remark"));
			mach.setStatus(rst.getString("status"));
			lists.add(mach);
			
		}
		DBUtil.close(conn);
 		return lists;
	}
	public List<Mach> findAllByDate(String dept,String userName,String startDate, String endDate) throws Exception{
		List<Mach> lists = new ArrayList<Mach>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_mach.*, ingre_order.`status` FROM ingre_mach INNER JOIN ingre_order ON ingre_mach.purchase_id = ingre_order.purchase_id where ingre_mach.dept_name=? and ingre_mach.operator =? and ingre_mach.opdate between ? and ?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, startDate);
		prep.setString(4, endDate);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			Mach mach = new Mach();
			mach.setPurchaseId(rst.getString("purchase_id"));
			mach.setMaterialId(rst.getString("material_id"));
			mach.setMaterialName(rst.getString("material_name"));
			mach.setMaterialSpec(rst.getString("material_spec"));
			mach.setUnit(rst.getString("unit"));
			mach.setNum(rst.getInt("num"));
			mach.setOpdate(rst.getString("opdate"));
			mach.setDeptNname(rst.getString("dept_name"));
			mach.setWorktypeName(rst.getString("wroktype_name"));
			mach.setRelatedPe(rst.getString("related_pe"));
			mach.setMaterialProvider(rst.getString("material_provider"));
			mach.setOperator(rst.getString("operator"));
			mach.setIsChecked(rst.getString("ischecked"));
			mach.setUse(rst.getString("use"));
			mach.setWorkTypeBelong(rst.getString("worktype_belong"));
			mach.setRemark(rst.getString("remark"));
			mach.setStatus(rst.getString("status"));
			lists.add(mach);
			
		}
		DBUtil.close(conn);
 		return lists;
	}
	
	public Mach findAllByPurcheseID(String purcheseId) throws Exception{

		Connection conn = DBUtil.getConnection();
		Mach mach = new Mach();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_mach.*, ingre_order.`status` FROM ingre_mach INNER JOIN ingre_order ON ingre_mach.purchase_id = ingre_order.purchase_id where ingre_mach.purchase_id=?");
		prep.setString(1, purcheseId);
		ResultSet rst = prep.executeQuery();
		if(rst.next()){
			
			mach.setPurchaseId(rst.getString("purchase_id"));
			mach.setMaterialId(rst.getString("material_id"));
			mach.setMaterialName(rst.getString("material_name"));
			mach.setMaterialSpec(rst.getString("material_spec"));
			mach.setUnit(rst.getString("unit"));
			mach.setNum(rst.getInt("num"));
			mach.setOpdate(rst.getString("opdate"));
			mach.setDeptNname(rst.getString("dept_name"));
			mach.setWorktypeName(rst.getString("wroktype_name"));
			mach.setRelatedPe(rst.getString("related_pe"));
			mach.setMaterialProvider(rst.getString("material_provider"));
			mach.setOperator(rst.getString("operator"));
			mach.setIsChecked(rst.getString("ischecked"));
			mach.setUse(rst.getString("use"));
			mach.setWorkTypeBelong(rst.getString("worktype_belong"));
			mach.setRemark(rst.getString("remark"));
			mach.setStatus(rst.getString("status"));	
		}
		DBUtil.close(conn);
 		return mach;
	}
	public void deleteByPurcheseID(String purcheseId) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM ingre_mach where purchase_id = ?");
		prep.setString(1, purcheseId);
		prep.execute();
	}
}
