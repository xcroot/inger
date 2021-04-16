package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import util.DBUtil;
import entity.BaseMaterial;

public class BaseMaterialDAO {
	public void save(BaseMaterial material) throws Exception{
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO ingre_material(material_id, material_type, material_name, material_spec, unit, price, material_attr, dept, remark, operator, opdate, filename) " +
				                                      " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
	    prep.setString(1, material.getMaterial_id());
	    prep.setString(2, material.getMaterial_type());
	    prep.setString(3, material.getMaterial_name());
	    prep.setString(4, material.getMaterial_spec());
	    prep.setString(5, material.getUint());
	    prep.setDouble(6, material.getPrice());
	    prep.setString(7, material.getMaterial_attr());
	    prep.setString(8, material.getDept());
	    prep.setString(9, material.getRemark());
	    prep.setString(10, material.getOperator());
	    prep.setString(11, material.getOpdate());
	    prep.setString(12, material.getFilename());
	    prep.executeUpdate();
	    DBUtil.close(conn);

	}
	
	public List<BaseMaterial> findAll(String dept, String type) throws Exception{
		List<BaseMaterial> materials = new ArrayList<BaseMaterial>();
		Connection conn = DBUtil.getConnection();
		if(type.equals("µÍÖµÄÍºÄ")){
			PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_material where dept =? and material_type = ?");
			prep.setString(1, dept);
			prep.setString(2, type);
			ResultSet rst = prep.executeQuery();
			while(rst.next()){
				BaseMaterial base = new BaseMaterial();
				base.setMaterial_id(rst.getString("material_id"));
				base.setMaterial_type(rst.getString("material_type"));
				base.setMaterial_name(rst.getString("material_name"));
				base.setMaterial_spec(rst.getString("material_spec"));
				base.setUint(rst.getString("unit"));
				base.setPrice(rst.getDouble("price"));
				base.setMaterial_attr(rst.getString("material_attr"));
				base.setDept(rst.getString("dept"));
				base.setRemark(rst.getString("remark"));
				base.setOperator(rst.getString("operator"));
				base.setOpdate(rst.getString("opdate"));
				base.setFilename(rst.getString("filename"));
				materials.add(base);
			}
			
		}else {
			PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_material where dept =? and material_attr = ?");
			prep.setString(1, dept);
			prep.setString(2, type);
			ResultSet rst = prep.executeQuery();
			while(rst.next()){
				BaseMaterial base = new BaseMaterial();
				base.setMaterial_id(rst.getString("material_id"));
				base.setMaterial_type(rst.getString("material_type"));
				base.setMaterial_name(rst.getString("material_name"));
				base.setMaterial_spec(rst.getString("material_spec"));
				base.setUint(rst.getString("unit"));
				base.setPrice(rst.getDouble("price"));
				base.setMaterial_attr(rst.getString("material_attr"));
				base.setDept(rst.getString("dept"));
				base.setRemark(rst.getString("remark"));
				base.setOperator(rst.getString("operator"));
				base.setOpdate(rst.getString("opdate"));
				base.setFilename(rst.getString("filename"));
				materials.add(base);
			}
		}


		DBUtil.close(conn);
		return materials;
	}
	
	public BaseMaterial findById(String materialId) throws Exception{
		Connection conn = DBUtil.getConnection();
		BaseMaterial base = null;
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM ingre_material where material_id = ?");
		prep.setString(1, materialId);
		ResultSet rst = prep.executeQuery();
		while(rst.next()){
			base = new BaseMaterial();
			base.setMaterial_id(rst.getString("material_id"));
			base.setMaterial_type(rst.getString("material_type"));
			base.setMaterial_name(rst.getString("material_name"));
			base.setMaterial_spec(rst.getString("material_spec"));
			base.setUint(rst.getString("unit"));
			base.setPrice(rst.getDouble("price"));
			base.setMaterial_attr(rst.getString("material_attr"));
			base.setDept(rst.getString("dept"));
			base.setRemark(rst.getString("remark"));
			base.setOperator(rst.getString("operator"));
			base.setOpdate(rst.getString("opdate"));
			base.setFilename(rst.getString("filename"));
		}
		DBUtil.close(conn);
		return base;
	}
	
	public List<BaseMaterial> findByKey(String key, String dept, String type) throws Exception{
		List<BaseMaterial> materials = new ArrayList<BaseMaterial>();
		Connection conn = DBUtil.getConnection();
		
		
		if(type.equals("µÍÖµÄÍºÄ")){
			System.out.println("µÍÖµÄÍºÄ");
			PreparedStatement prep = conn.prepareStatement("SELECT a.* from ((SELECT * FROM ingre_material where dept =? and material_type =? )as a) where a.material_id like ? or a.material_name like ? or a.material_attr like ?");
			prep.setString(1, dept);
			prep.setString(2, type);
			prep.setString(3, "%"+key+"%");
			prep.setString(4, "%"+key+"%");
			prep.setString(5, "%"+key+"%");
			ResultSet rst = prep.executeQuery();
			while(rst.next()){
				BaseMaterial base = new BaseMaterial();
				base.setMaterial_id(rst.getString("material_id"));
				base.setMaterial_type(rst.getString("material_type"));
				base.setMaterial_name(rst.getString("material_name"));
				base.setMaterial_spec(rst.getString("material_spec"));
				base.setUint(rst.getString("unit"));
				base.setPrice(rst.getDouble("price"));
				base.setMaterial_attr(rst.getString("material_attr"));
				base.setDept(rst.getString("dept"));
				base.setRemark(rst.getString("remark"));
				base.setOperator(rst.getString("operator"));
				base.setOpdate(rst.getString("opdate"));
				base.setFilename(rst.getString("filename"));
		
				materials.add(base);
			}
			DBUtil.close(conn);
		}else{
			PreparedStatement prep = conn.prepareStatement("SELECT a.* FROM ((SELECT * FROM ingre_material where dept =? and material_attr = ?)as a) where  material_id like ? or material_name like ? or material_attr like ?");
			prep.setString(1, dept);
			prep.setString(2, type);
			prep.setString(3, "%"+key+"%");
			prep.setString(4, "%"+key+"%");
			prep.setString(5, "%"+key+"%");
			ResultSet rst = prep.executeQuery();
			while(rst.next()){
				BaseMaterial base = new BaseMaterial();
				base.setMaterial_id(rst.getString("material_id"));
				base.setMaterial_type(rst.getString("material_type"));
				base.setMaterial_name(rst.getString("material_name"));
				base.setMaterial_spec(rst.getString("material_spec"));
				base.setUint(rst.getString("unit"));
				base.setPrice(rst.getDouble("price"));
				base.setMaterial_attr(rst.getString("material_attr"));
				base.setDept(rst.getString("dept"));
				base.setRemark(rst.getString("remark"));
				base.setOperator(rst.getString("operator"));
				base.setOpdate(rst.getString("opdate"));
				base.setFilename(rst.getString("filename"));
		
				materials.add(base);
			}
			DBUtil.close(conn);
		}
	

		return materials;
	}
}
