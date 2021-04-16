<%@page import="java.util.*,bean.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
    <script src="js/jquery-1.4.3.js"></script>

    
<div class="layuimini-main"> 
	    
	    <div class="layui-col-md">
	        <div class="layui-form-item">
               <div>
                  <img src="img/d007/${material.filename}" width="1023" heigth="600" >
               </div>
		    </div>
	    </div>
	    
	    <div class="layui-col-md">
	              <form class="layui-form"  method="">
					        <div class="layui-form-item">
					            <label class="layui-form-label required">请购单编码:</label>
					            <div class="layui-input-block">
					                 <input type="text" id="purchase_id"  class="layui-input" name="purchase_id" size="20" readonly value="${purchase_id}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">物品编码:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="material_id" class="layui-input" size="20" readonly value="${material.material_id}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">物品名称:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="material_name" class="layui-input" size="20" readonly value="${material.material_name}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">品牌:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="brand_name" class="layui-input" size="20">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">规格型号:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="material_spec" class="layui-input" size="20" readonly value="${material.material_spec}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">单位:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="unit" size="20" class="layui-input" readonly value="${material.uint}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">申购数量:</label>
					            <div class="layui-input-block">
					                 <input type="text" id="num" name="num" size="20" class="layui-input" lay-verify="required|number">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">使用频率:</label>
					            <div class="layui-input-block">
					                 <input type="text" id="frequency" class="layui-input" name="frequency" size="20">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">单独使用:</label>
					            <div class="layui-input-block">
					                 <input type="text" id="single_use" class="layui-input" name="single_use" size="20">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">共同使用:</label>
					            <div class="layui-input-block">
					                 <input type="text" id="multi_use" class="layui-input" name="multi_use" size="20">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">请购原因:</label>
					            <div class="layui-input-block">
					                 <input type="text" id="reason" class="layui-input" name="reason" size="20">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">请购时间:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="opdate" size="20" class="layui-input" readonly value="${curtime}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">部门:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="dept_name" size="20" class="layui-input" readonly value="${user.dept}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">岗位:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="worktype_name" size="20" class="layui-input" readonly value="${user.workType}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label required">申请人:</label>
					            <div class="layui-input-block">
					                 <input type="text" name="operator" size="20" class="layui-input" readonly value="${user.userName}">
					            </div>
					        </div>
					        <div class="layui-form-item">
					            <label class="layui-form-label">挂靠班组:</label>
					            <div class="layui-input-block">
					                 <select  id="worktype_belong" name="worktype_belong">
					                        <option value="-1">--岗位选择--</option>
				                     </select>
					            </div>
					        </div>
					        
					        <div class="layui-form-item">
					            <label class="layui-form-label required">备注:</label>
					            <div class="layui-input-block">
					                 <textarea class="layui-textarea"  name="remark" ></textarea>
					            </div>
					        </div>
					        
					        <div class="layui-form-item">
					            <div class="layui-input-block">
								<button class="layui-btn" lay-submit lay-filter="saveBtn">提交</button>
								</div>
					        </div>
	               </form>
	    </div>
</div>
<script>

    layui.use(['form'],function(){
         var form = layui.form,
         layer = layui.layer;
         
          // 当前弹出层，防止ID被覆盖
         var parentIndex = layer.index;
         
         function getXmlHttpRequest(){
		  var xmlHttpRequest = null;
	      if ((typeof XMLHttpRequest) != 'undefined'){
	     	//非ie浏览器
	        xmlHttpRequest = new XMLHttpRequest();
	      }else {
	        //ie浏览器
	        xmlHttpRequest = 
	         new ActiveXObject('Microsoft.XMLHttp');
	      }
	      return xmlHttpRequest;
	    }
	    $(function(){
	   		var xmlReq = getXmlHttpRequest();
	   		xmlReq.open('post','ruike/getworktype.do',true);
	   		xmlReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	   		xmlReq.onreadystatechange=function(){
	   		   if(xmlReq.readyState == 4){
	   		   		var rsTxt = xmlReq.responseText;
	   		   		rsTxt = rsTxt.replace("[","");
	   		   		rsTxt = rsTxt.replace("]","");
	   		   		var list = rsTxt.split(',');
	   		   	
	   		   		for(i=1; i <= list.length; i++){
	   		   			var op = new Option(list[i-1],list[i-1]);
	   		   			var obj = document.getElementById('worktype_belong');
	   		   			obj.options[i] = op;
	   		   		}
	   		   }
	   		   form.render();
	   		}
	   		xmlReq.send(null);
	   		
	   });
         
         
        var lock = false;
        form.on('submit(saveBtn)',function(data){
             if(lock){
                layer.msg('请勿重复提交请购');
                 return false;
             }
             data = data.field;
             if(data.num == ''){
                 layer.msg('申购数量不能为空');
                 return false;
             }
             if(data.frequency == ''){
                  layer.msg('使用频率不能为空');
                  return false;
             }
             if(data.single_use == ''){
                  layer.msg('单独使用不能为空');
                  return false;
             }
             if(data.multi_use == ''){
                  layer.msg('共同使用不能为空');
                  return false;
             }
             if(data.reason == ''){
                  layer.msg('请填写请购原因');
                  return false;
              }
             if(data.worktype_belong == -1){
                  layer.msg('请挂靠班组');
                  return false;
             }
             lock = true;
              $.ajax({
                  url:'/ingredient/ruike/buytoolorder.do',//发出请求
                  type:'post',
                  data:{"purchase_id":data.purchase_id,"material_id":data.material_id,"material_name":data.material_name,
                        "brand_name":data.brand_name,"material_spec":data.material_spec,"unit":data.unit,
                        "num":data.num,"frequency":data.frequency,"single_use":data.single_use,"multi_use":data.multi_use,
                        "reason":data.reason,"opdate":data.opdate,"dept_name":data.dept_name,"worktype_name":data.worktype_name,
                        "operator":data.operator,"worktype_belong":data.worktype_belong,"remark":data.remark},
                        success:function(data){
                        console.log(data);
                        if(data=="sucess"){//ok说明登录成功
                           layer.msg('请购提交成功', function () {
                              // 关闭弹出层
                              //layer.close(index);
                              layer.close(parentIndex);
                              return true;
                           });
                    
                        }
                        
                        return false;
                    }
                    
                   
             });
             
              return false;
         
           
        })
        
    });
</script>




