<%@page import="java.util.*,bean.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<link href="css/main/style.css"
			type="text/css" rel="stylesheet" />
	</head>

	<body topMargin="10">
		<div id="append_parent"></div>
		<table cellSpacing="6" cellPadding="2" width="100%" border="0">
			<tbody>
				<tr>
					<td>
						<table class="guide" cellSpacing="0" cellPadding="0" width="100%"
							border="0">
							<tbody>
								<tr>
									<td>
										<a href='#'>t主页</a>&nbsp;/&nbsp;
										<a href='computer_list.html'>低值易耗(WEB007)</a>&nbsp;/&nbsp;请购信息
									</td>
								</tr>
							</tbody>
						</table>
						<br />



						<table class="tableborder" cellSpacing="0" cellPadding="0"
							width="100%" border="0">
							<tbody>
								<tr class="header">
									<td class="altbg2" colspan="6">
										请购信息
									</td>
								</tr>
							<tbody>
								<tr>
									<td class="altbg1" width="20%">
										<b>型号</b>
									</td>
									<td class="altbg1" width="20%">
										<b>价格</b>
									</td>
									<td class="altbg1" width="10%">
										<b>数量</b>
									</td>
									<td class="altbg1" width="30">
										&nbsp;
									</td>
									<td class="altbg1" width="10%">
										&nbsp;
									</td>
									<td class="altbg1">
										&nbsp;
									</td>
								</tr>
							</tbody>
							
							<tbody>
								<%
									MaterialCart cart = (MaterialCart)session.getAttribute("cart");
									if(cart!=null && cart.list().size() > 0){
										List<BaseMaterialItem> items = cart.list();
										for(int i=0;i<items.size();i++){
											BaseMaterialItem item = items.get(i);
											%>
											<tr>
												<td class="altbg2">
													<%=item.getMaterial().getMaterial_name()%>
												</td>
												<td class="altbg2">
													<%=item.getMaterial().getPrice()%>
												</td>
												<td class="altbg2">
													<%=item.getQty()%>
												</td>
												<td class="altbg2">
													<input type="text" size="3" 
														id="num_<%=item.getMaterial().getMaterial_id()%>" />
												</td>
												<td class="altbg2">
													<a href="javascript:;" 
													onclick="location='modify.do?id=<%=item.getMaterial().getMaterial_id()%>&num=' + document.getElementById('num_<%=item.getMaterial().getMaterial_id()%>').value;">更改数量</a>
												</td>
												<td class="altbg2">
													<a href="delete.do?id=<%=item.getMaterial().getMaterial_id()%>">删除</a>
												</td>
											</tr>
											<%
											}
											%>
											<tr>
												<td class="altbg1" colspan="6">
													<b>总价格：￥<%=cart.cost()%></b>
												</td>
											</tr>
											<%
										}else{
										%>
										<tr>
											<td class="altbg2" colspan="6">
												<b>还没有选购产品</b>
											</td>
										</tr>
										<%
									}
								 %>
							</tbody>
						
							
						
						</table>

						<br />
						<center>
							<input class="button" type="button" value="返回请购列表"
								name="settingsubmit" onclick="location = 'list.do';">
							<input class="button" type="button" value="清空请购列表"
								name="settingsubmit"
								onclick="location = 'clear.do';">
						</center>
					</td>
				</tr>
			</tbody>
		</table>

	</body>
</html>



