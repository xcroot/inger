package web;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import util.SysTime;
import util.UUIDGenerator;
import bean.MaterialCart;
import dao.ApplyOrderDAO;
import dao.BaseMaterialDAO;
import dao.EazyConsumeDAO;
import dao.MachDAO;
import dao.ReplacementDAO;
import dao.ResistanceDAO;
import dao.ToolApplyDAO;
import dao.UserDAO;
import dao.WorkTypeDAO;
import entity.ApplyOrder;
import entity.BaseMaterial;
import entity.EazyConsume;
import entity.Mach;
import entity.Replacement;
import entity.Resistance;
import entity.ToolApply;
import entity.User;

public class ShoppingCart extends HttpServlet {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			String uri = request.getRequestURI();
			String path = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));

			//System.out.println(path);
			if(path.equals("/list")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
	
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findAll(user.getDept(),"");
					request.setAttribute("basematerials", basematerials);
					request.getRequestDispatcher("computer_list.jsp")
					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				
			}
			if(path.equals("/toollist")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findAll(user.getDept(),"工具类");
					request.setAttribute("basematerials", basematerials);
					request.getRequestDispatcher("toollist.jsp")
					.forward(request, response);
					session.setAttribute("type", "工具类");
					session.setAttribute("index", "toollist.jsp");
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/easyconsumelist")){
				HttpSession session = request.getSession();
		
			    User user = new User();
			    JsonObject json = new JsonObject();
			    user = (User)session.getAttribute("user");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findAll(user.getDept(),"低值易耗类");
					
					request.setAttribute("basematerials", basematerials);
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", basematerials.size());
					
					
					request.setAttribute("dataName", json);
				
				
				
					request.getRequestDispatcher("easyconsumelist.jsp")
					.forward(request, response);
					session.setAttribute("type", "低值易耗类");
					session.setAttribute("index", "easyconsumelist.jsp");
				}catch(Exception e){
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/machlist")){
				HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findAll(user.getDept(),"机加工件类");
					request.setAttribute("basematerials", basematerials);
					request.getRequestDispatcher("machlist.jsp")
					.forward(request, response);
					session.setAttribute("type", "机加工件类");
					session.setAttribute("index", "machlist.jsp");
				}catch(Exception e){
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/repalcementlist")){
				HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findAll(user.getDept(),"备件类");
					request.setAttribute("basematerials", basematerials);
					request.getRequestDispatcher("replacementlist.jsp")
					.forward(request, response);
					session.setAttribute("type", "备件类");
					session.setAttribute("index", "replacementlist.jsp");
				}catch(Exception e){
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/resistancelist")){
				HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findAll(user.getDept(),"低值耐耗");
					request.setAttribute("basematerials", basematerials);
					request.getRequestDispatcher("resistancelist.jsp")
					.forward(request, response);
					session.setAttribute("type", "低值耐耗");
					session.setAttribute("index", "resistancelist.jsp");
				
				}catch(Exception e){
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/buyresistance")){
				String id = request.getParameter("id");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					BaseMaterial material = dao.findById(id);
					HttpSession session = request.getSession();
					MaterialCart cart = (MaterialCart)session.getAttribute("cart");
					session.setAttribute("purchase_id", UUIDGenerator.getUUID());
					
					if(cart == null){
						cart = new MaterialCart();
						session.setAttribute("cart", cart);
						session.setAttribute("curtime", SysTime.GetCurDate());
					}
					if (cart==null){
						request.setAttribute("buy_err_" + id, "位找到该产品任何信息");
						request.getRequestDispatcher("resistancelist.do").forward(request, response);
					}else{
						request.setAttribute("material",material);
						request.getRequestDispatcher("cartresistance.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/buyresistanceorder")){
				 String purchaseId = request.getParameter("purchase_id");
				 String materialId = request.getParameter("material_id");
				 String materialName = request.getParameter("material_name");
				 String sort = request.getParameter("sort");
				 String materialSpec = request.getParameter("material_spec");
				 String unit = request.getParameter("unit");
				 int num = Integer.parseInt(request.getParameter("num"));
				 String type = request.getParameter("type");
				 String manufacturer = request.getParameter("manufacturer");
				 String frequency    = request.getParameter("frequency");
				 String prePrice    = request.getParameter("pre_price");
				 String reason  = request.getParameter("reason");
				 String use  = request.getParameter("use");
				 String operator = request.getParameter("operator");
				 String deptName = request.getParameter("dept_name");
				 String workTypeName = request.getParameter("worktype_name");
				 String keeper = request.getParameter("keeper");
				 String deathLine = request.getParameter("deathline");
				 String storeLoc = request.getParameter("store_loc");
				 String opdate = request.getParameter("opdate");
				 String workTypeBelong = request.getParameter("worktype_belong");
				 String remark = request.getParameter("remark");
				 System.out.println(use);
				 
				 Resistance apply = new Resistance();
				 apply.setPurchaseId(purchaseId);
				 apply.setMaterialId(materialId);
				 apply.setMaterialName(materialName);
				 apply.setSort(sort);
				 apply.setMaterialSpec(materialSpec);
				 apply.setUnit(unit);
				 apply.setNum(num);
				 apply.setType(type);
				 apply.setManufacturer(manufacturer);
				 apply.setFrequency(frequency);
				 apply.setPrePrice(prePrice);
				 apply.setReason(reason);
				 apply.setUse(use);
				 apply.setOperator(operator);
				 apply.setDeptName(deptName);
				 apply.setWorktypeName(workTypeName);
				 apply.setKeeper(keeper);
				 apply.setDeathline(deathLine);
				 apply.setStoreLoc(storeLoc);
				 apply.setOpdate(opdate);
				 apply.setWorkTypeBelong(workTypeBelong);
				 apply.setRemark(remark);
                
				 //请购单
				 ApplyOrder order = new ApplyOrder();
				 order.setPurchaseId(purchaseId);
				 order.setMaterialId(materialId);
				 order.setMaterialName(materialName);
				 order.setMaterialSpec(materialSpec);
				 order.setUnit(unit);
				 order.setNum(num);
				 order.setWorkTypeBelong(workTypeBelong);
				 order.setDeptName(deptName);
				 order.setWorkTypeName(workTypeName);
				 order.setOpdate(opdate);
				 order.setStatus("发起");
				 order.setOperator(operator);
				 order.setDealDate("");
				 order.setDealer("");
				 order.setReason("");
				 try {
					ResistanceDAO dao = new ResistanceDAO();
					dao.save(apply);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				try {
					ApplyOrderDAO orderDAO = new ApplyOrderDAO();
					orderDAO.save(order);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("purchase_id", purchaseId);
				request.setAttribute("opdate", opdate);
				request.setAttribute("cmd", "resistancelist.do");
				response.getWriter().print("sucess");
				//request.getRequestDispatcher("suecees.jsp").forward(request, response);
			}
			if(path.equals("/buyeasyconsume")){
				String id = request.getParameter("id");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					BaseMaterial material = dao.findById(id);
					HttpSession session = request.getSession();
					MaterialCart cart = (MaterialCart)session.getAttribute("cart");
					session.setAttribute("purchase_id", UUIDGenerator.getUUID());
					
					if(cart == null){
						cart = new MaterialCart();
						session.setAttribute("cart", cart);
						session.setAttribute("curtime", SysTime.GetCurDate());
					}
					if (cart==null){
						request.setAttribute("buy_err_" + id, "位找到该产品任何信息");
						request.getRequestDispatcher("easyconsumelist.do").forward(request, response);
					}else{
						request.setAttribute("material",material);
						request.getRequestDispatcher("carteazyconsume.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/buymach")){
				String id = request.getParameter("id");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					BaseMaterial material = dao.findById(id);
					HttpSession session = request.getSession();
					MaterialCart cart = (MaterialCart)session.getAttribute("cart");
					session.setAttribute("purchase_id", UUIDGenerator.getUUID());
					
					if(cart == null){
						cart = new MaterialCart();
						session.setAttribute("cart", cart);
						session.setAttribute("curtime", SysTime.GetCurDate());
					}
					if (cart==null){
						request.setAttribute("buy_err_" + id, "位找到该产品任何信息");
						request.getRequestDispatcher("mach.do").forward(request, response);
					}else{
						request.setAttribute("material",material);
						request.getRequestDispatcher("cartmach.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/buyeazyconsumeorder")){
				 String purchaseId = request.getParameter("purchase_id");
				 String materialId = request.getParameter("material_id");
				 String materialName = request.getParameter("material_name");
				 String sort = request.getParameter("sort");
				 String materialSpec = request.getParameter("material_spec");
				 String unit = request.getParameter("unit");
				 int num = Integer.parseInt(request.getParameter("num"));
				 String use = request.getParameter("use");
				 String opdate = request.getParameter("opdate");
				 String deptName = request.getParameter("dept_name");
				 String workTypeName = request.getParameter("worktype_name");
				 String operator = request.getParameter("operator");
				 String materialProvider = request.getParameter("material_provider");
				 String addr = request.getParameter("addr");
			     String deathLine = request.getParameter("deathline");
			     String workTypeBelong = request.getParameter("worktype_belong");
				 String remark = request.getParameter("remark");
				 EazyConsume apply = new EazyConsume();
				 apply.setPurchaseId(purchaseId);
				 apply.setMaterialId(materialId);
				 apply.setMaterialName(materialName);
				 apply.setSort(sort);
				 apply.setMaterialSpec(materialSpec);
				 apply.setUnit(unit);
				 apply.setNum(num);
				 apply.setUse(use);
				 apply.setOpdate(opdate);
				 apply.setDeptName(deptName);
				 apply.setWorktypeName(workTypeName);
				 apply.setOperator(operator);
				 apply.setMaterialProvider(materialProvider);
				 apply.setAddr(addr);
				 apply.setDeathLine(deathLine);
				 apply.setWorkTypeBelong(workTypeBelong);
				 apply.setRemark(remark);
				 
				 //请购单
				 ApplyOrder order = new ApplyOrder();
				 order.setPurchaseId(purchaseId);
				 order.setMaterialId(materialId);
				 order.setMaterialName(materialName);
				 order.setMaterialSpec(materialSpec);
				 order.setUnit(unit);
				 order.setNum(num);
				 order.setWorkTypeBelong(workTypeBelong);
				 order.setDeptName(deptName);
				 order.setWorkTypeName(workTypeName);
				 order.setOpdate(opdate);
				 order.setOperator(operator);
				 order.setStatus("发起");
				 order.setDealDate("");
				 order.setDealer("");
				 order.setReason("");
				 try {
					EazyConsumeDAO dao = new EazyConsumeDAO();
					dao.save(apply);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				try {
					ApplyOrderDAO orderDAO = new ApplyOrderDAO();
					orderDAO.save(order);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("purchase_id", purchaseId);
				request.setAttribute("opdate", opdate);
				request.setAttribute("cmd", "easyconsumelist.do");
				response.getWriter().print("sucess");
				//request.getRequestDispatcher("suecees.jsp").forward(request, response);
			}
			if(path.equals("/buyreplacement")){
				String id = request.getParameter("id");

				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					BaseMaterial material = dao.findById(id);
					HttpSession session = request.getSession();
					MaterialCart cart = (MaterialCart)session.getAttribute("cart");
					session.setAttribute("purchase_id", UUIDGenerator.getUUID());
					
					if(cart == null){
						cart = new MaterialCart();
						session.setAttribute("cart", cart);
						session.setAttribute("curtime", SysTime.GetCurDate());
					}
					if (cart==null){
						request.setAttribute("buy_err_" + id, "位找到该产品任何信息");
						request.getRequestDispatcher("repalcementlist.do").forward(request, response);
					}else{
						request.setAttribute("material",material);
						request.getRequestDispatcher("cartreplacement.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/buytool")){
				String id = request.getParameter("id");
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					BaseMaterial material = dao.findById(id);
					HttpSession session = request.getSession();
					MaterialCart cart = (MaterialCart)session.getAttribute("cart");
					session.setAttribute("purchase_id", UUIDGenerator.getUUID());
					if(cart == null){
						cart = new MaterialCart();
						session.setAttribute("cart", cart);
						session.setAttribute("curtime", SysTime.GetCurDate());
					}
					if (cart==null){
						request.setAttribute("buy_err_" + id, "位找到该产品任何信息");
						request.getRequestDispatcher("toollist.do").forward(request, response);
					}else{
						request.setAttribute("material",material);
						request.getRequestDispatcher("carttool.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/buytoolorder")){
				 
				 String purchaseId = request.getParameter("purchase_id");
				 String materialId = request.getParameter("material_id");
				 String materialName = request.getParameter("material_name");
				 String brandName = request.getParameter("brand_name");
				 String materialSpec = request.getParameter("material_spec");
				 String unit = request.getParameter("unit");
				 int num = Integer.parseInt(request.getParameter("num"));
				 String frequency = request.getParameter("frequency");
				 String singleUse = request.getParameter("single_use");
				 String multiUse = request.getParameter("multi_use");
				 String reason = request.getParameter("reason");
				 String opdate = request.getParameter("opdate");
				 String deptName = request.getParameter("dept_name");
				 String workTypeName = request.getParameter("worktype_name");
				 String operator = request.getParameter("operator");
				 String workTypeBelong = request.getParameter("worktype_belong");
				 String remark = request.getParameter("remark");
				 ToolApply apply = new ToolApply();
				 apply.setPurchaseId(purchaseId);
				 apply.setMaterialId(materialId);
				 apply.setMaterialName(materialName);
				 apply.setBrandName(brandName);
				 apply.setMaterialSpec(materialSpec);
				 apply.setUnit(unit);
				 apply.setNum(num);
				 apply.setFrequency(frequency);
				 apply.setSingleUse(singleUse);
				 apply.setMultiUse(multiUse);
				 apply.setReason(reason);
				 apply.setOpdate(opdate);
				 apply.setDeptName(deptName);
				 apply.setWorkTypeName(workTypeName);
				 apply.setOperator(operator);
				 apply.setWorkTypeBelong(workTypeBelong);
				 apply.setRemark(remark);
				 //请购单
				 ApplyOrder order = new ApplyOrder();
				 order.setPurchaseId(purchaseId);
				 order.setMaterialId(materialId);
				 order.setMaterialName(materialName);
				 order.setMaterialSpec(materialSpec);
				 order.setUnit(unit);
				 order.setNum(num);
				 order.setWorkTypeBelong(workTypeBelong);
				 order.setDeptName(deptName);
				 order.setWorkTypeName(workTypeName);
				 order.setOpdate(opdate);
				 order.setOperator(operator);
				 order.setStatus("发起");
				 order.setDealDate("");
				 order.setDealer("");
				 order.setReason("");
				 try {
					ToolApplyDAO toolDao = new ToolApplyDAO();
					toolDao.save(apply);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				try {
					ApplyOrderDAO orderDAO = new ApplyOrderDAO();
					orderDAO.save(order);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("purchase_id", purchaseId);
				request.setAttribute("opdate", opdate);
				request.setAttribute("cmd", "toollist.do");
				response.getWriter().print("sucess");
				//request.getRequestDispatcher("suecees.jsp").forward(request, response);
			}
			if(path.equals("/buyreplacementorder")){
				 String purchaseId = request.getParameter("purchase_id");
				 String materialId = request.getParameter("material_id");
				 String materialName = request.getParameter("material_name");
				 String sort = request.getParameter("sort");
				 String materialSpec = request.getParameter("material_spec");
				 String unit = request.getParameter("unit");
				 int num = Integer.parseInt(request.getParameter("num"));
				 String materialProvider = request.getParameter("material_provider");
				 String equipmentUse = request.getParameter("equipment_use");
				 String reason = request.getParameter("reason");
				 String opdate = request.getParameter("opdate");
				 String deptName = request.getParameter("dept_name");
				 String workTypeName = request.getParameter("worktype_name");
				 String operator = request.getParameter("operator");
				 String workTypeBelong = request.getParameter("worktype_belong");
				 String remark = request.getParameter("remark");
				 Replacement apply = new Replacement();
				 apply.setPurchaseId(purchaseId);
				 apply.setMaterialId(materialId);
				 apply.setMaterialName(materialName);
				 apply.setSort(sort);
				 apply.setMaterialSpec(materialSpec);
				 apply.setUnit(unit);
				 apply.setNum(num);
				 apply.setMaterialProvider(materialProvider);
				 apply.setEquipmentUse(equipmentUse);
				 apply.setReason(reason);
				 apply.setOpdate(opdate);
				 apply.setDeptNname(deptName);
				 apply.setWorktypeName(workTypeName);
				 apply.setOperator(operator);
				 apply.setWorkTypeBelong(workTypeBelong);
				 apply.setRemark(remark);
               
				 //请购单
				 ApplyOrder order = new ApplyOrder();
				 order.setPurchaseId(purchaseId);
				 order.setMaterialId(materialId);
				 order.setMaterialName(materialName);
				 order.setMaterialSpec(materialSpec);
				 order.setUnit(unit);
				 order.setNum(num);
				 order.setWorkTypeBelong(workTypeBelong);
				 order.setDeptName(deptName);
				 order.setWorkTypeName(workTypeName);
				 order.setOpdate(opdate);
				 order.setOperator(operator);
				 order.setStatus("发起");
				 order.setDealDate("");
				 order.setDealer("");
				 order.setReason("");
				 try {
					ReplacementDAO dao = new ReplacementDAO();
					dao.save(apply);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				try {
					ApplyOrderDAO orderDAO = new ApplyOrderDAO();
					orderDAO.save(order);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("purchase_id", purchaseId);
				request.setAttribute("opdate", opdate);
				request.setAttribute("cmd", "repalcementlist.do");
				response.getWriter().print("sucess");
//				request.getRequestDispatcher("suecees.jsp").forward(request, response);
			}
			if(path.equals("/buymachorder")){
				 String purchaseId = request.getParameter("purchase_id");
				 String materialId = request.getParameter("material_id");
				 String materialName = request.getParameter("material_name");
				 String materialSpec = request.getParameter("material_spec");
				 String unit = request.getParameter("unit");
				 int num = Integer.parseInt(request.getParameter("num"));
				 String opdate = request.getParameter("opdate");
				 String deptName = request.getParameter("dept_name");
				 String workTypeName = request.getParameter("worktype_name");
				 String relatedPe = request.getParameter("relatedPE");
				 String materialProvider = request.getParameter("material_provider");
				 String operator = request.getParameter("operator");
				 String isChecked = request.getParameter("ischecked");
				 String use = request.getParameter("use");
				 String workTypeBelong = request.getParameter("worktype_belong");
				 String remark = request.getParameter("remark");
				 
				 Mach apply = new Mach();
				 apply.setPurchaseId(purchaseId);
				 apply.setMaterialId(materialId);
				 apply.setMaterialName(materialName);
				 apply.setMaterialSpec(materialSpec);
				 apply.setUnit(unit);
				 apply.setNum(num);
				 apply.setOpdate(opdate);
				 apply.setDeptNname(deptName);
				 apply.setWorktypeName(workTypeName);
				 apply.setRelatedPe(relatedPe);
				 apply.setMaterialProvider(materialProvider);
				 apply.setOperator(operator);
				 apply.setIsChecked(isChecked);
				 apply.setUse(use);
				 apply.setWorkTypeBelong(workTypeBelong);
				 apply.setRemark(remark);
               
				 
				 //请购单
				 ApplyOrder order = new ApplyOrder();
				 order.setPurchaseId(purchaseId);
				 order.setMaterialId(materialId);
				 order.setMaterialName(materialName);
				 order.setMaterialSpec(materialSpec);
				 order.setUnit(unit);
				 order.setNum(num);
				 order.setDeptName(deptName);
				 order.setWorkTypeBelong(workTypeBelong);
				 order.setWorkTypeName(workTypeName);
				 order.setOpdate(opdate);
				 order.setOperator(operator);
				 order.setStatus("发起");
				 order.setDealDate("");
				 order.setDealer("");
				 order.setReason("");
				 
				try {
					MachDAO dao = new MachDAO();
					dao.save(apply);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				
				try {
					ApplyOrderDAO orderDAO = new ApplyOrderDAO();
					orderDAO.save(order);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
				request.setAttribute("purchase_id", purchaseId);
				request.setAttribute("opdate", opdate);
				request.setAttribute("cmd", "machlist.do");
				response.getWriter().print("sucess");
//				request.getRequestDispatcher("suecees.jsp").forward(request, response);
			}
			if(path.equals("/delete")){
				String id = request.getParameter("id");
				HttpSession session = request.getSession();
				MaterialCart cart = (MaterialCart)session.getAttribute("cart");
				cart.delete(id);
				request.getRequestDispatcher("cart.jsp")
				.forward(request, response);
			}
			if(path.equals("/clear")){
				HttpSession session = request.getSession();
				MaterialCart cart = (MaterialCart)session.getAttribute("cart");
				cart.clear();
				request.getRequestDispatcher("cart.jsp")
				.forward(request, response);
			}
			if(path.equals("/modify")){
				String id = request.getParameter("id");
				String qty = request.getParameter("num");
				HttpSession session = request.getSession();
				MaterialCart cart = (MaterialCart)session.getAttribute("cart");
				cart.modify(id, Integer.parseInt(qty));
				request.getRequestDispatcher("cart.jsp")
				.forward(request, response);
			}
			if(path.equals("/search")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				String key = request.getParameter("mainkey");
				//System.out.println(key);
				String index = (String)session.getAttribute("index");
				String type  = (String)session.getAttribute("type");
				//System.out.println(index);
				BaseMaterialDAO dao = new BaseMaterialDAO();
				try {
					List<BaseMaterial> basematerials = 
						dao.findByKey(key,user.getDept(),type);
				
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", basematerials.size());
					
					JsonArray jArray = new JsonArray();
				
					
					
	                for (BaseMaterial baseMaterial : basematerials) {
	                	jArray.add(gson.toJsonTree(baseMaterial));
					}
	                
	                json.add("data", jArray);
					String str = json.toString();
					
					//System.out.println(jArray);
					//System.out.println(json);
					//System.out.println(str);
				    
					response.getWriter().print(str);
					/*request.setAttribute("basematerials", basematerials);
					request.getRequestDispatcher("ruike/"+index)
					.forward(request, response);*/
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/renewpwd")){
				String email = request.getParameter("email");
				String pwd   = request.getParameter("pwd");
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
			    User newUser = new User();
			    UserDAO dao = new UserDAO();
			    try {
					newUser = dao.findByEmail(email);
					if(user.getUserId().equals(newUser.getUserId())){
						dao.renewpwd(user.getUserId(), pwd);
						request.setAttribute("msg", "修改成功");
						request.setAttribute("err", "您的密码已经修改成功");
						request.setAttribute("cmd", "renewpwd.jsp");
						response.getWriter().print("sucess");
						//request.getRequestDispatcher("pwdrenewsuecees.jsp").forward(request, response);
						
					}else{
						request.setAttribute("msg", "修改失败");
						request.setAttribute("err", "您填写的信息有误，密码修改失败");
						request.setAttribute("cmd", "main.jsp");
						response.getWriter().print("fail");
						//request.getRequestDispatcher("pwdrenewsuecees.jsp").forward(request, response);
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/getworktype")){
				HttpSession session = request.getSession();
				User user = new User();
				user = (User)session.getAttribute("user");
				WorkTypeDAO dao = new WorkTypeDAO();
				try {
					List<String> lists = dao.getAllWorkType(user.getDept());
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println(lists);
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/orderall")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				ApplyOrderDAO dao = new ApplyOrderDAO();
				try {
					List<ApplyOrder> orderall = 
						dao.findAll(user.getDept(), user.getUserName());
					request.setAttribute("orderall", orderall);
					request.getRequestDispatcher("order.jsp")
					.forward(request, response);
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/ordersearchbydate")){
			    HttpSession session = request.getSession();
			    String startDate =  request.getParameter("startdate");
			    String endDate =  request.getParameter("enddate");
			    User user = new User();
			    user = (User)session.getAttribute("user");
				ApplyOrderDAO dao = new ApplyOrderDAO();
				try {
					
					List<ApplyOrder> orderall = 
						dao.findAllByTime(user.getDept(), user.getUserName(),startDate,endDate);
					
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", orderall.size());
					
					JsonArray jArray = new JsonArray();
					 for (ApplyOrder applyOrder : orderall) {
		                	jArray.add(gson.toJsonTree(applyOrder));
						}
					 json.add("data", jArray);
					 response.getWriter().print(json.toString());
					
//					request.setAttribute("orderall", orderall);
//					request.getRequestDispatcher("order.jsp")
//					.forward(request, response);
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/machorder")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				MachDAO dao = new MachDAO();
				try {
					List<Mach> machorder = 
						dao.findAll(user.getDept(), user.getUserName());
					request.setAttribute("machorderall", machorder);
					request.getRequestDispatcher("machorder.jsp")
					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/ordermachsearchbydate")){
			    HttpSession session = request.getSession();
			    String startDate =  request.getParameter("startdate");
			    String endDate =  request.getParameter("enddate");
			    User user = new User();
			    user = (User)session.getAttribute("user");
				MachDAO dao = new MachDAO();
				try {
					List<Mach> machorder = 
						dao.findAllByDate(user.getDept(), user.getUserName(), startDate, endDate);
					
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", machorder.size());
					
					JsonArray jArray = new JsonArray();
	                for (Mach mach : machorder) {
						jArray.add(gson.toJsonTree(mach));
					}
	                json.add("data", jArray);
	                response.getWriter().print(json.toString());
	                
//					request.setAttribute("machorderall", machorder);
//					request.getRequestDispatcher("machorder.jsp")
//					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/machorderquit")){
				 String purcheseId =  request.getParameter("id");
				 purcheseId = purcheseId.replaceAll("\r\n|\r|\n|", "");
				 purcheseId = purcheseId.trim();
				 MachDAO dao = new MachDAO();
				 try {
					Mach mach = dao.findAllByPurcheseID(purcheseId);
					if(mach.getStatus().equals("发起")){
						//可以删除
						dao.deleteByPurcheseID(purcheseId);
						ApplyOrderDAO orderdao = new ApplyOrderDAO();
						orderdao.deleteByPurcheseID(purcheseId);
						request.setAttribute("msg", "撤回成功");
						request.setAttribute("err", "该订单已经成功被撤回");
						request.setAttribute("cmd", "machorder.do");
						response.getWriter().print("ok");
					}else{
						request.setAttribute("msg", "撤回失败");
						request.setAttribute("err", "该订单已经审核无法被撤回");
						request.setAttribute("cmd", "machorder.do");
						response.getWriter().print("fail");
					}
//					request.getRequestDispatcher("orderquit.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/eazyconsumeorderall")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				EazyConsumeDAO dao = new EazyConsumeDAO();
				try {
					List<EazyConsume> machorder = 
						dao.findAll(user.getDept(), user.getUserName());
					request.setAttribute("eazyconsumeorderall", machorder);
					request.getRequestDispatcher("easyconsumeorder.jsp")
					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/ordeazyconsumesearchbydate")){
			    HttpSession session = request.getSession();
			    String startDate =  request.getParameter("startdate");
			    String endDate =  request.getParameter("enddate");
			    User user = new User();
			    user = (User)session.getAttribute("user");
			    //System.out.println(startDate);
			    EazyConsumeDAO dao = new EazyConsumeDAO();
				try {
					List<EazyConsume> machorder = 
						dao.findAllByDate(user.getDept(), user.getUserName(), startDate, endDate);
				
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", machorder.size());
					
					JsonArray jArray = new JsonArray();
	                for (EazyConsume eazyConsume : machorder) {
						jArray.add(gson.toJsonTree(eazyConsume));
					}
	                json.add("data", jArray);
	                response.getWriter().print(json.toString());
//					request.setAttribute("eazyconsumeorderall", machorder);
//					request.getRequestDispatcher("easyconsumeorder.jsp")
//					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/eazyconsumeorderquit")){
				 String purcheseId =  request.getParameter("id");
				 purcheseId = purcheseId.replaceAll("\r\n|\r|\n|", "");
				 purcheseId = purcheseId.trim();
				 //System.out.println("id="+purcheseId);
				 EazyConsumeDAO dao = new EazyConsumeDAO();
				 try {
					 EazyConsume erazyConsume = dao.findAllByPurcheseID(purcheseId);
					 //System.out.println("状态"+erazyConsume.getStatus());
					if(erazyConsume.getStatus().equals("发起")){
						//可以删除
						dao.deleteByPurcheseID(purcheseId);
						ApplyOrderDAO orderdao = new ApplyOrderDAO();
						orderdao.deleteByPurcheseID(purcheseId);
						request.setAttribute("msg", "撤回成功");
						request.setAttribute("err", "该订单已经成功被撤回");
						request.setAttribute("cmd", "eazyconsumeorderall.do");
						response.getWriter().print("ok");
					}else{
						request.setAttribute("msg", "撤回失败");
						request.setAttribute("err", "该订单已经审核无法被撤回");
						request.setAttribute("cmd", "eazyconsumeorderall.do");
						response.getWriter().print("fail");
					}
					//request.getRequestDispatcher("orderquit.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/toolorderall")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				ToolApplyDAO dao = new ToolApplyDAO();
				try {
					List<ToolApply> toolList = 
						dao.findAll(user.getDept(), user.getUserName());
					request.setAttribute("toolorderall", toolList);
					request.getRequestDispatcher("toolorder.jsp")
					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/ordertoolsearchbydate")){
			    HttpSession session = request.getSession();
			    String startDate =  request.getParameter("startdate");
			    String endDate =  request.getParameter("enddate");
			    User user = new User();
			    user = (User)session.getAttribute("user");
			    ToolApplyDAO dao = new ToolApplyDAO();
				try {
					List<ToolApply> toolList = 
						dao.findAllByDate(user.getDept(), user.getUserName(), startDate, endDate);
					
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", toolList.size());
					
					JsonArray jArray = new JsonArray();
	                for (ToolApply toolApply : toolList) {
						jArray.add(gson.toJsonTree(toolApply));
					}
	                json.add("data", jArray);
	                response.getWriter().print(json.toString());
//					request.setAttribute("toolorderall", toolList);
//					request.getRequestDispatcher("toolorder.jsp")
//					.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/toolorderquit")){
				 String purcheseId =  request.getParameter("id");
				 purcheseId = purcheseId.replaceAll("\r\n|\r|\n|", "");
				 purcheseId = purcheseId.trim();
				 ToolApplyDAO dao = new ToolApplyDAO();
				 try {
					 ToolApply toolApply = dao.findAllByPurcheseID(purcheseId);
					if(toolApply.getStatus().equals("发起")){
						//可以删除
						dao.deleteByPurcheseID(purcheseId);
						ApplyOrderDAO orderdao = new ApplyOrderDAO();
						orderdao.deleteByPurcheseID(purcheseId);
						request.setAttribute("msg", "撤回成功");
						request.setAttribute("err", "该订单已经成功被撤回");
						request.setAttribute("cmd", "toolorderall.do");
						response.getWriter().print("ok");
					}else{
						request.setAttribute("msg", "撤回失败");
						request.setAttribute("err", "该订单已经审核无法被撤回");
						request.setAttribute("cmd", "toolorderall.do");
						response.getWriter().print("fail");
					}
					//request.getRequestDispatcher("orderquit.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/replacementorderall")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				ReplacementDAO dao = new ReplacementDAO();
				try {
					List<Replacement> reList = 
						dao.findAll(user.getDept(), user.getUserName());
					request.setAttribute("replacementorderall", reList);
					request.getRequestDispatcher("replacementorder.jsp")
					.forward(request, response);
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/orderreplacementsearchbydate")){
			    HttpSession session = request.getSession();
			    String startDate =  request.getParameter("startdate");
			    String endDate =  request.getParameter("enddate");
			    User user = new User();
			    user = (User)session.getAttribute("user");
			    ReplacementDAO dao = new ReplacementDAO();
				try {
					List<Replacement> machorder = 
						dao.findAllByDate(user.getDept(), user.getUserName(), startDate, endDate);
					
					
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", machorder.size());
					
					JsonArray jArray = new JsonArray();
	                for (Replacement replacement : machorder) {
						jArray.add(gson.toJsonTree(replacement));
					}
	                json.add("data", jArray);
	                response.getWriter().print(json.toString());
	                
//					request.setAttribute("replacementorderall", machorder);
//					request.getRequestDispatcher("replacementorder.jsp")
//					.forward(request, response);
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/replacementorderquit")){
				 String purcheseId =  request.getParameter("id");
				 purcheseId = purcheseId.replaceAll("\r\n|\r|\n|", "");
				 purcheseId = purcheseId.trim();
				 ReplacementDAO dao = new ReplacementDAO();
				 try {
					 Replacement replacement = dao.findAllByPurcheseID(purcheseId);
					if(replacement.getStatus().equals("发起")){
						//可以删除
						dao.deleteByPurcheseID(purcheseId);
						ApplyOrderDAO orderdao = new ApplyOrderDAO();
						orderdao.deleteByPurcheseID(purcheseId);
						request.setAttribute("msg", "撤回成功");
						request.setAttribute("err", "该订单已经成功被撤回");
						request.setAttribute("cmd", "replacementorderall.do");
						response.getWriter().print("ok");
					}else{
						request.setAttribute("msg", "撤回失败");
						request.setAttribute("err", "该订单已经审核无法被撤回");
						request.setAttribute("cmd", "replacementorderall.do");
						response.getWriter().print("ok");
					}
					//request.getRequestDispatcher("orderquit.jsp").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/resistanceorderall")){
			    HttpSession session = request.getSession();
			    User user = new User();
			    user = (User)session.getAttribute("user");
				ResistanceDAO dao = new ResistanceDAO();
				try {
					List<Resistance> resList = 
						dao.findAll(user.getDept(), user.getUserName());
					request.setAttribute("resistanceorderall", resList);
					request.getRequestDispatcher("resistanceorder.jsp")
					.forward(request, response);
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/orderresistancesearchbydate")){
			    HttpSession session = request.getSession();
			    String startDate =  request.getParameter("startdate");
			    String endDate =  request.getParameter("enddate");
			    User user = new User();
			    user = (User)session.getAttribute("user");
			    ResistanceDAO dao = new ResistanceDAO();
				try {
					List<Resistance> resList = 
						dao.findAllByDate(user.getDept(), user.getUserName(), startDate, endDate);
					
					Gson gson = new Gson();
					JsonObject json = new JsonObject();
					json.addProperty("code", 0);
					json.addProperty("msg", "");
					json.addProperty("count", resList.size());
					
					JsonArray jArray = new JsonArray();
	                for (Resistance resistance : resList) {
						jArray.add(gson.toJsonTree(resistance));
					}
	                json.add("data", jArray);
	                response.getWriter().print(json.toString());
	                
//					request.setAttribute("resistanceorderall", resList);
//					request.getRequestDispatcher("resistanceorder.jsp")
//					.forward(request, response);
				
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e);
				}
			}
			if(path.equals("/resistanceorderquit")){
				 String purcheseId =  request.getParameter("id");
				 purcheseId = purcheseId.replaceAll("\r\n|\r|\n|", "");
				 purcheseId = purcheseId.trim();
				 ResistanceDAO dao = new ResistanceDAO();
				 try {
					    Resistance resistance = dao.findAllByPurcheseID(purcheseId);
						if(resistance.getStatus().equals("发起")){
							//可以删除
							dao.deleteByPurcheseID(purcheseId);
							ApplyOrderDAO orderdao = new ApplyOrderDAO();
							orderdao.deleteByPurcheseID(purcheseId);
							request.setAttribute("msg", "撤回成功");
							request.setAttribute("err", "该订单已经成功被撤回");
							request.setAttribute("cmd", "resistanceorderall.do");
							response.getWriter().print("ok");
						}else{
							request.setAttribute("msg", "撤回失败");
							request.setAttribute("err", "该订单已经审核无法被撤回");
							request.setAttribute("cmd", "resistanceorderall.do");
							response.getWriter().print("ok");
						}
					        //request.getRequestDispatcher("orderquit.jsp").forward(request, response);
				    } catch (Exception e) {
					   e.printStackTrace();
					   throw new ServletException(e);
				    }
			}

	}

}
