<%@page import="java.util.*,entity.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>

<head>
   <meta http-equiv=Content-Type content="text/html;charset=UTF-8"/>
</head>
<body>
<div class="layuimini-container layui-anim layui-anim-upbit">
    <div class="layuimini-main">
    <div class="splayui-container">
    <div class="splayui-main">
        <!--查询参数-->
           <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
        <form id="js-search-form" class="layui-form" lay-filter="js-q-form-filter">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">开始时间：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="startdate" name="startdate" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">结束时间：</label>
                    <div class="layui-input-inline">
                        <input type="text" id="enddate" name="enddate" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <a class="layui-btn" lay-submit lay-filter="js-search-filter"><i
                                class="layui-icon layui-icon-search layuiadmin-button-btn"></i></a>
                </div>
            </div>
        </form>
          </fieldset>

        <!--表格-->
        <table class="layui-hide" id="js-record-table" lay-filter="js-record-table-filter"></table>
    </div>
</div>

       <!--   <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table> -->
        <table class="layui-table" id="currentTableId"  lay-filter="currentTableFilter">
           <thead>
           		<tr>
           		   <th lay-data="{field:'purchaseId', width:180}">请购编码</th>
           		   <th lay-data="{field:'materialId', width:180}">产品编码</th>
           		   <th lay-data="{field:'materialName', width:190}">物品名称</th>
           		   <th lay-data="{field:'brandName', width:190}">品牌</th>
           		   <th lay-data="{field:'materialSpec', width:280}">产品规格</th>
           		   <th lay-data="{field:'unit', width:180}">单位</th>
           		   <th lay-data="{field:'num', width:180}">申购数量</th>
           		   <th lay-data="{field:'frequency', width:180}">使用频率</th>
           		   <th lay-data="{field:'singleUse', width:180}">单独使用</th>
           		   <th lay-data="{field:'multiUse', width:180}">共同使用</th>
           		   <th lay-data="{field:'reason', width:180}">请购原因</th>
           		   <th lay-data="{field:'opdate', width:180}">申购日期</th>
           		   <th lay-data="{field:'deptName', width:180}">部门</th>
           		   <th lay-data="{field:'worktypeName', width:180}">岗位</th>
           		   <th lay-data="{field:'operator', width:180}">申请人</th>
           		   <th lay-data="{field:'workTypeBelong', width:180}">挂靠班组</th>
           		   <th lay-data="{field:'remark', width:180}">备注</th>
           		   <th lay-data="{field:'status', width:180}">审核状态</th>
           		   <th lay-data="{title:'操作',templet: '#currentTableBar', width:160}">操作</th>
           		   
           		</tr>
           </thead>
         <tbody>
			<%
				
					List<ToolApply> items = (List<ToolApply>)request.getAttribute("toolorderall");
					for(int i=0;i<items.size();i++){
						ToolApply item = items.get(i);
						%>
						<tr>
							<td class="altbg2">
								<%=item.getPurchaseId()%>
							</td>
							<td class="altbg2">
								<%=item.getMaterialId()%>
							</td>
							<td class="altbg2">
								<%=item.getMaterialName()%>
							</td>
							<td class="altbg2">
								<%=item.getBrandName()%>
							</td>
							<td class="altbg2">
								<%=item.getMaterialSpec()%>
							</td>
							<td class="altbg2">
								<%=item.getUnit()%>
							</td>
						    <td class="altbg2">
								<%=item.getNum()%>
							</td>
							<td class="altbg2">
								<%=item.getFrequency() %>
							</td>
							<td class="altbg2">
								<%=item.getSingleUse() %>
							</td>
		                    <td class="altbg2">
								<%=item.getMultiUse()%>
							</td>
							<td class="altbg2">
								<%=item.getReason()%>
							</td>
							<td class="altbg2">
								<%=item.getOpdate()%>
							</td>
							<td class="altbg2">
								<%=item.getDeptName()%>
							</td>
							<td class="altbg2">
								<%=item.getWorkTypeName()%>
							</td>
							 <td class="altbg2">
								<%=item.getOperator()%>
							</td>
							<td class="altbg2">
								<%=item.getWorkTypeBelong()== null? "":item.getWorkTypeBelong()%>
							</td>
							<td class="altbg2">
								<%=item.getRemark() == null? "":item.getRemark()%>
							</td>
							<td class="altbg2">
								<%=item.getStatus() == null? "":item.getStatus()%>
							</td>
						</tr>
						<%
						}
			 %>
		</tbody>
		</table>
		
		  <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">撤回</a>
        </script>
    </div>
</div>
</body>
<script>
    layui.use(['form', 'table','layuimini','element','laydate'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            layuimini = layui.layuimini,
            laydate = layui.laydate;
         
	        laydate.render({
	            elem: '#startdate', //指定元素
	            theme: '#393D49'
	        });
	        laydate.render({
	            elem: '#enddate', //指定元素
	            theme: '#393D49'
	        });
            
            table.init('currentTableFilter', {
                     height: 600, //设置高度
                     limit: 200, //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
                     toolbar: '#toolbarDemo',
                     defaultToolbar: ['filter', 'exports', 'print', {
                     title: '提示',
                     layEvent: 'LAYTABLE_TIPS',
                     icon: 'layui-icon-tips'
                     }]
            });


         /**
         * 搜索按钮事件
         */
        form.on('submit(js-search-filter)', function (data) {
            table.reload('currentTableId',{
             method:'post',
             url:'ruike/ordeazyconsumesearchbydate.do?&'+'startdate='+data.field.startdate+'&'+'enddate='+data.field.enddate
             });
            // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
            return true;
        });


        table.on('tool(currentTableFilter)', function (obj) {
            console.log('表格监听');
            var data = obj.data;
             console.log(data.material_id);
            if (obj.event === 'edit') {

                var content = layuimini.getHrefContent('ordertoolsearchbydate.do?id='+data.material_id);
                var openWH = layuimini.getOpenWidthHeight();

                var index = layer.open({
                    title: '请购清单',
                    type: 1,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: [openWH[0] + 'px', openWH[1] + 'px'],
                    offset: [openWH[2] + 'px', openWH[3] + 'px'],
                    content: content,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                 
                layer.confirm('真的删除行么', function (index) {
                    console.log(data.purchaseId)
                     $.ajax({
                          url:'/ingredient/ruike/toolorderquit.do',//发出请求
                          type:'post',
                          data:{"id":data.purchaseId},
	                          success:function(data){
	                          if(data=="ok"){//ok说明登录成功
	                              obj.del();
	                              layer.close(index);
	                              layer.msg('撤回成功');
	
	                          }else if(data=="fail"){
	                            layer.msg('撤回失败,该申购已经被审核无法撤回');
	                   
	                          }
                          }
                    });
                   
                    
                });
            }
        });

    });
</script>
</html>
