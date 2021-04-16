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
            <legend>信息填写</legend>
        <form class="layui-form" lay-filter="js-q-form-filter">
                  <div class="layui-form-item">
                
                    <label class="layui-form-label required">邮箱：</label>
                    <div class="layui-input-block">
                        <input type="text" name="email" lay-verify="email" class="layui-input">
                   </div>
               
                <div class="layui-form-item">
                    <label class="layui-form-label required">密码：</label>
                        <div class="layui-input-block">
                        <input type="password" name="pwd" lay-verify="required|password"  autocomplete="off" class="layui-input" >
                        </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label required">密码：</label>
                        <div class="layui-input-block">
                        <input type="password" name="pwd_again" lay-verify="required|password"  autocomplete="off" class="layui-input" >
                        </div >
                </div>
                <div class="layui-form-item">
                         <div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="saveBtn">提交</button>
						</div>
                </div>
            </div>
        </form>
        </fieldset>

    </div>
</div>

      

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
         /*
        form.on('submit(js-search-filter)', function (data) {
            table.reload('currentTableId',{
             method:'post',
             url:'ruike/ordersearchbydate.do?&'+'startdate='+data.field.startdate+'&'+'enddate='+data.field.enddate
             });
            // 阻止表单跳转。如果需要表单跳转，去掉这段即可。
            return true;
        });*/
           form.on('submit(saveBtn)',function(data){
       
             data = data.field;
             if(data.email == ''){
                 layer.msg('邮件不能为空');
                 return false;
             }
             if(data.pwd == ''){
                  layer.msg('密码不能为空');
                  return false;
             }
             if(data.pwd_again == ''){
                  layer.msg('第二次输入的密码不能为空');
                  return false;
              }
             if(data.pwd == data.pwd_again){
                  layer.msg('两次密码输入不一致');
                  return false;
             }
             
              $.ajax({
                  url:'/ingredient/ruike/renewpwd.do',//发出请求
                  type:'post',
                  data:{"email":data.email,"pwd":data.pwd,"pwd_again":data.pwd_again
                        },
                        success:function(data){
                        console.log(data);
                        if(data=="sucess"){//ok说明登录成功
                           layer.msg('请购提交成功');
                        }else{
                           layer.msg('密码修改错误,请您检查邮件是否填写正确');
                        }
                        
                        return true;
                    }
                    
                   
             });
             
              return false;
         
           
         });

        table.on('tool(currentTableFilter)', function (obj) {
            console.log('表格监听');
            var data = obj.data;
             console.log(data.material_id);
            if (obj.event === 'edit') {

                var content = layuimini.getHrefContent('buyeasyconsume.do?id='+data.material_id);
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
                          url:'/ingredient/ruike/eazyconsumeorderquit.do',//发出请求
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
