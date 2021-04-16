<%@page import="java.util.*,entity.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>

<head>
   <meta http-equiv=Content-Type content="text/html;charset=UTF-8"/>
    <style type="text/css">
	.layui-table-cell{
		text-align:center;
		height: auto;
		white-space: normal;
		width:200px;
	}
	.layui-table img{
	   max-width:170px;
	   max-height:110px;
	}
	</style>
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
                    <label class="layui-form-label">关键字查询</label>
                    <div class="layui-input-inline">
                        <input type="text" name="tableName" autocomplete="off" class="layui-input">
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
           		   <th lay-data="{field:'material_id', width:180}">产品编码</th>
           		   <th lay-data="{field:'filename', templet:'#imgtmp'}">产品图片</th>
           		   <th lay-data="{field:'material_name', width:190}">产品描述</th>
           		   <th lay-data="{field:'material_spec', width:280}">产品规格</th>
           		   <th lay-data="{field:'uint', width:180}">单位</th>
           		   <th lay-data="{field:'material_attr', width:180}">物料属性</th>
           		   <th lay-data="{title:'操作',templet: '#currentTableBar', width:160}">操作</th>
           		   
           		</tr>
           </thead>
           <tbody>
								<%
									List<BaseMaterial> materials = 
									(List<BaseMaterial>)request.getAttribute("basematerials");
									for(int i=0;i<materials.size();i++){
										BaseMaterial curr = materials.get(i);
										%>
										<tr>
									<td>
										<%=curr.getMaterial_id()%>
									</td>
									<td>
										<%=curr.getFilename()%>
									</td>
									<td>
										<%=curr.getMaterial_name()%>
									</td>
									<td>
										<%=curr.getMaterial_spec()%>
									</td>
									<td>
									<%=curr.getUint()%>
									</td>
									<td>
									<%=curr.getMaterial_attr()%>
									</td>
								</tr>
										<%
									}
								 %>
							</tbody>
		</table>
		
		  <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit">购买</a>
        </script>
        <script type="text/html" id="imgtmp">

                <img src="{{'img/d007/'+d.filename}}" style="width:200px;height:250px;"/>
        </script>

    </div>
</div>
</body>
<script>
    layui.use(['form', 'table','layuimini','element'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            layuimini = layui.layuimini;
            
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
            console.log(data.field.tableName);
            console.log(table);
            table.reload('currentTableId',{
             method:'post',
             url:'search.do?&mainkey='+data.field.tableName
             });
             
            
            // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
            return true;
        });
        table.on('tool(currentTableFilter)', function (obj) {
            console.log('表格监听');
            var data = obj.data;
             console.log(data.material_id);
            if (obj.event === 'edit') {

                var content = layuimini.getHrefContent('ruike/buyresistance.do?id='+data.material_id);
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
                    obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>
</html>
