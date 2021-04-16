package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import util.DBUtil;
import util.SysTime;
import entity.EazyConsume;

public class EazyConsumeDAO {
	public void save(EazyConsume consume) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = 
			conn.prepareStatement("INSERT INTO ingre_easy_consume(purchase_id, material_id, material_name, sort, material_spec, unit, num, `use`, opdate, dept_name, worktype_name, operator, material_provider, addr, deathline, worktype_belong, remark)"+
					" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		prep.setString(1, consume.getPurchaseId());
		prep.setString(2, consume.getMaterialId());
		prep.setString(3, consume.getMaterialName());
		prep.setString(4, consume.getSort());
		prep.setString(5, consume.getMaterialSpec());
		prep.setString(6, consume.getUnit());
		prep.setInt(7, consume.getNum());
		prep.setString(8, consume.getUse());
		prep.setString(9, consume.getOpdate());
		prep.setString(10, consume.getDeptName());
		prep.setString(11, consume.getWorktypeName());
		prep.setString(12, consume.getOperator());
		prep.setString(13, consume.getMaterialProvider());
		prep.setString(14, consume.getAddr());
		prep.setString(15, consume.getDeathLine());
		prep.setString(16, consume.getWorkTypeBelong());
		prep.setString(17, consume.getRemark());
		prep.executeUpdate();
		DBUtil.close(conn);
	}
	public List<EazyConsume> findAll(String dept,String userName) throws Exception{
		List<EazyConsume> lists = new ArrayList<EazyConsume>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT ingre_easy_consume.*, ingre_order.`status` FROM ingre_easy_consume INNER JOIN ingre_order ON ingre_easy_consume.purchase_id = ingre_order.purchase_id where ingre_easy_consume.dept_name=? and ingre_easy_consume.operator =? and ingre_easy_consume.opdate=?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, SysTime.GetCurDate());
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			EazyConsume eazyConsume = new EazyConsume();
			eazyConsume.setPurchaseId(rst.getString("purchase_id"));
			eazyConsume.setMaterialId(rst.getString("material_id"));
			eazyConsume.setMaterialName(rst.getString("material_name"));
			eazyConsume.setSort(rst.getString("sort"));
			eazyConsume.setMaterialSpec(rst.getString("material_spec"));
			eazyConsume.setUnit(rst.getString("unit"));
			eazyConsume.setNum(rst.getInt("num"));
			eazyConsume.setUse(rst.getString("use"));
			eazyConsume.setOpdate(rst.getString("opdate"));
			eazyConsume.setDeptName(rst.getString("dept_name"));
			eazyConsume.setWorktypeName(rst.getString("worktype_name"));
			eazyConsume.setOperator(rst.getString("operator"));
			eazyConsume.setMaterialProvider(rst.getString("material_provider"));
			eazyConsume.setAddr(rst.getString("addr"));
			eazyConsume.setDeathLine(rst.getString("deathline"));
			eazyConsume.setWorkTypeBelong(rst.getString("worktype_belong"));
			eazyConsume.setRemark(rst.getString("remark"));
			eazyConsume.setStatus(rst.getString("status"));
			lists.add(eazyConsume);
		}
		return lists;
	}
	public List<EazyConsume> findAllByDate(String dept,String userName, String startDate, String endDate) throws Exception{
		List<EazyConsume> lists = new ArrayList<EazyConsume>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT ingre_easy_consume.*, ingre_order.`status` FROM ingre_easy_consume INNER JOIN ingre_order ON ingre_easy_consume.purchase_id = ingre_order.purchase_id where ingre_easy_consume.dept_name=? and ingre_easy_consume.operator =? and ingre_easy_consume.opdate between ? and ?");
		prep.setString(1, dept);
		prep.setString(2, userName);
		prep.setString(3, startDate);
		prep.setString(4, endDate);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			EazyConsume eazyConsume = new EazyConsume();
			eazyConsume.setPurchaseId(rst.getString("purchase_id"));
			eazyConsume.setMaterialId(rst.getString("material_id"));
			eazyConsume.setMaterialName(rst.getString("material_name"));
			eazyConsume.setSort(rst.getString("sort"));
			eazyConsume.setMaterialSpec(rst.getString("material_spec"));
			eazyConsume.setUnit(rst.getString("unit"));
			eazyConsume.setNum(rst.getInt("num"));
			eazyConsume.setUse(rst.getString("use"));
			eazyConsume.setOpdate(rst.getString("opdate"));
			eazyConsume.setDeptName(rst.getString("dept_name"));
			eazyConsume.setWorktypeName(rst.getString("worktype_name"));
			eazyConsume.setOperator(rst.getString("operator"));
			eazyConsume.setMaterialProvider(rst.getString("material_provider"));
			eazyConsume.setAddr(rst.getString("addr"));
			eazyConsume.setDeathLine(rst.getString("deathline"));
			eazyConsume.setWorkTypeBelong(rst.getString("worktype_belong"));
			eazyConsume.setRemark(rst.getString("remark"));
			eazyConsume.setStatus(rst.getString("status"));
			lists.add(eazyConsume);
		}
		return lists;
	}
	public EazyConsume findAllByPurcheseID(String purcheseId) throws Exception{
		EazyConsume eazyConsume = new EazyConsume();
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT ingre_easy_consume.*, ingre_order.`status` FROM ingre_easy_consume INNER JOIN ingre_order ON ingre_easy_consume.purchase_id = ingre_order.purchase_id where ingre_easy_consume.purchase_id=?");
		prep.setString(1, purcheseId);

		ResultSet rst = prep.executeQuery();
		if(rst.next()){
			
			eazyConsume.setPurchaseId(rst.getString("purchase_id"));
			eazyConsume.setMaterialId(rst.getString("material_id"));
			eazyConsume.setMaterialName(rst.getString("material_name"));
			eazyConsume.setSort(rst.getString("sort"));
			eazyConsume.setMaterialSpec(rst.getString("material_spec"));
			eazyConsume.setUnit(rst.getString("unit"));
			eazyConsume.setNum(rst.getInt("num"));
			eazyConsume.setUse(rst.getString("use"));
			eazyConsume.setOpdate(rst.getString("opdate"));
			eazyConsume.setDeptName(rst.getString("dept_name"));
			eazyConsume.setWorktypeName(rst.getString("worktype_name"));
			eazyConsume.setOperator(rst.getString("operator"));
			eazyConsume.setMaterialProvider(rst.getString("material_provider"));
			eazyConsume.setAddr(rst.getString("addr"));
			eazyConsume.setDeathLine(rst.getString("deathline"));
			eazyConsume.setWorkTypeBelong(rst.getString("worktype_belong"));
			eazyConsume.setRemark(rst.getString("remark"));
			eazyConsume.setStatus(rst.getString("status"));
			
		}
		return eazyConsume;
	}
	
	public void deleteByPurcheseID(String purcheseId) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM ingre_easy_consume where purchase_id = ?");
		prep.setString(1, purcheseId);
		prep.execute();
	}
}
