package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import util.SysTime;

import entity.Resistance;

public class ResistanceDAO {
	public void save(Resistance resistance) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO ingre_resistance(purchase_id, material_id, material_name, sort, material_spec, unit, num, type, manufacturer, frequency, pre_price, reason, `use`, operator, dept_name, worktype_name, keeper, deathline, store_loc, opdate, worktype_belong, remark)" + 
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		prep.setString(1, resistance.getPurchaseId());
		prep.setString(2, resistance.getMaterialId());
		prep.setString(3, resistance.getMaterialName());
		prep.setString(4, resistance.getSort());
		prep.setString(5, resistance.getMaterialSpec());
		prep.setString(6, resistance.getUnit());
		prep.setInt(7, resistance.getNum());
		prep.setString(8, resistance.getType());
		prep.setString(9, resistance.getManufacturer());
		prep.setString(10, resistance.getFrequency());
		prep.setString(11, resistance.getPrePrice());
		prep.setString(12, resistance.getReason());
		prep.setString(13, resistance.getUse());
		prep.setString(14, resistance.getOperator());
		prep.setString(15, resistance.getDeptName());
		prep.setString(16, resistance.getWorktypeName());
		prep.setString(17, resistance.getKeeper());
		prep.setString(18, resistance.getDeathline());
		prep.setString(19, resistance.getStoreLoc());
		prep.setString(20, resistance.getOpdate());
		prep.setString(21, resistance.getWorkTypeBelong());
		prep.setString(22, resistance.getRemark());
		prep.executeUpdate();
		DBUtil.close(conn);
 	}
	
	public List<Resistance> findAll(String dept,String userName) throws Exception{
		List<Resistance> lists = new ArrayList<Resistance>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_resistance.*, ingre_order.`status` FROM ingre_resistance INNER JOIN ingre_order ON ingre_resistance.purchase_id = ingre_order.purchase_id where ingre_resistance.dept_name=? and ingre_resistance.operator =? and ingre_resistance.opdate=?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, SysTime.GetCurDate());
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			Resistance resistance = new Resistance();
			resistance.setPurchaseId(rst.getString("purchase_id"));
			resistance.setMaterialId(rst.getString("material_id"));
			resistance.setMaterialName(rst.getString("material_name"));
			resistance.setSort(rst.getString("sort"));
			resistance.setMaterialSpec(rst.getString("material_spec"));
			resistance.setUnit(rst.getString("unit"));
			resistance.setNum(rst.getInt("num"));
			resistance.setType(rst.getString("type"));
			resistance.setManufacturer(rst.getString("manufacturer"));
			resistance.setFrequency(rst.getString("frequency"));
			resistance.setPrePrice(rst.getString("pre_price"));
			resistance.setReason(rst.getString("reason"));
			resistance.setUse(rst.getString("use"));
			resistance.setOperator(rst.getString("operator"));
			resistance.setDeptName(rst.getString("dept_name"));
			resistance.setWorktypeName(rst.getString("worktype_name"));
			resistance.setKeeper(rst.getString("keeper"));
			resistance.setDeathline(rst.getString("deathline"));
			resistance.setStoreLoc(rst.getString("store_loc"));
			resistance.setOpdate(rst.getString("opdate"));
			resistance.setWorkTypeBelong(rst.getString("worktype_belong"));
			resistance.setRemark(rst.getString("remark"));
			resistance.setStatus(rst.getString("status"));
			lists.add(resistance);
		}
		DBUtil.close(conn);
 		return lists;
	}
	
	public List<Resistance> findAllByDate(String dept, String userName, String startDate, String endDate) throws Exception{
		List<Resistance> lists = new ArrayList<Resistance>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_resistance.*, ingre_order.`status` FROM ingre_resistance INNER JOIN ingre_order ON ingre_resistance.purchase_id = ingre_order.purchase_id where ingre_resistance.dept_name=? and ingre_resistance.operator =? and ingre_resistance.opdate between ? and ?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, startDate);
		prep.setString(4, endDate);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			Resistance resistance = new Resistance();
			resistance.setPurchaseId(rst.getString("purchase_id"));
			resistance.setMaterialId(rst.getString("material_id"));
			resistance.setMaterialName(rst.getString("material_name"));
			resistance.setSort(rst.getString("sort"));
			resistance.setMaterialSpec(rst.getString("material_spec"));
			resistance.setUnit(rst.getString("unit"));
			resistance.setNum(rst.getInt("num"));
			resistance.setType(rst.getString("type"));
			resistance.setManufacturer(rst.getString("manufacturer"));
			resistance.setFrequency(rst.getString("frequency"));
			resistance.setPrePrice(rst.getString("pre_price"));
			resistance.setReason(rst.getString("reason"));
			resistance.setUse(rst.getString("use"));
			resistance.setOperator(rst.getString("operator"));
			resistance.setDeptName(rst.getString("dept_name"));
			resistance.setWorktypeName(rst.getString("worktype_name"));
			resistance.setKeeper(rst.getString("keeper"));
			resistance.setDeathline(rst.getString("deathline"));
			resistance.setStoreLoc(rst.getString("store_loc"));
			resistance.setOpdate(rst.getString("opdate"));
			resistance.setWorkTypeBelong(rst.getString("worktype_belong"));
			resistance.setRemark(rst.getString("remark"));
			resistance.setStatus(rst.getString("status"));
			lists.add(resistance);
		}
		DBUtil.close(conn);
 		return lists;
	}
	
	public Resistance findAllByPurcheseID(String purcheseId) throws Exception{
		Resistance resistance = new Resistance();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep =  conn.prepareStatement("SELECT ingre_resistance.*, ingre_order.`status` FROM ingre_resistance INNER JOIN ingre_order ON ingre_resistance.purchase_id = ingre_order.purchase_id where ingre_resistance.purchase_id=? ");
		prep.setString(1, purcheseId);
		ResultSet rst = prep.executeQuery();
		if(rst.next()){
			resistance.setPurchaseId(rst.getString("purchase_id"));
			resistance.setMaterialId(rst.getString("material_id"));
			resistance.setMaterialName(rst.getString("material_name"));
			resistance.setSort(rst.getString("sort"));
			resistance.setMaterialSpec(rst.getString("material_spec"));
			resistance.setUnit(rst.getString("unit"));
			resistance.setNum(rst.getInt("num"));
			resistance.setType(rst.getString("type"));
			resistance.setManufacturer(rst.getString("manufacturer"));
			resistance.setFrequency(rst.getString("frequency"));
			resistance.setPrePrice(rst.getString("pre_price"));
			resistance.setReason(rst.getString("reason"));
			resistance.setUse(rst.getString("use"));
			resistance.setOperator(rst.getString("operator"));
			resistance.setDeptName(rst.getString("dept_name"));
			resistance.setWorktypeName(rst.getString("worktype_name"));
			resistance.setKeeper(rst.getString("keeper"));
			resistance.setDeathline(rst.getString("deathline"));
			resistance.setStoreLoc(rst.getString("store_loc"));
			resistance.setOpdate(rst.getString("opdate"));
			resistance.setWorkTypeBelong(rst.getString("worktype_belong"));
			resistance.setRemark(rst.getString("remark"));
			resistance.setStatus(rst.getString("status"));
		}
		DBUtil.close(conn);
 		return resistance;
	}
	
	public void deleteByPurcheseID(String purcheseId) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM ingre_resistance where purchase_id = ?");
		prep.setString(1, purcheseId);
		prep.execute();
	}
}
