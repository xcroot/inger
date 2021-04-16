<%@page import="java.util.*,entity.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html;charset=UTF-8"/>
		<link href="css/main/style.css" type="text/css" rel="stylesheet"/>
	</head>
	<body topMargin="10">
	
	   <div class="xst-form-div">
			<form action="search.do" method="post" id="xst-form" class="xst-form">
				<input type="text" value="关键词" name="mainkey" class="xst-form-t" id="xst-form-t"><input type="submit" value="搜索" class="xst-form-b" id="xst-form-b">
			</form>
		</div>
		<div id="append_parent"></div>
		<table cellSpacing=6 cellPadding=2 width="100%" border="0">
			<tbody>
				<tr>
					<td>
						<table class="guide" cellSpacing="0" cellPadding="0" width="100%"
							border="0">
							<tbody>
								<tr>
									<td>
										<a href='#'>主页</a>&nbsp;/&nbsp;
										<a href='#'>低质易耗请购(WEB005)</a>&nbsp;/&nbsp;产品列表
									</td>
								</tr>
							</tbody>
						</table>
						<br />

						<table class="tableborder" cellSpacing="0" cellPadding="0"
							width="100%" border="0">
							<tbody>
								<tr class="header">
									<td class="altbg1" width="15%">
										<b>产品编码</B>
									</td>
									<td class="altbg1" width="20%">
										<b>产品图片</b>
									</td>
									<td class="altbg1" width="30%">
										<b>产品描述</b>
									</td>

									<td class="altbg1" width="10%">
										<b>产品规格</b>
									</td>
									
									<td class="altbg1" width="10%">
										<b>单位</b>
									</td>
									
									<td class="altbg1" width="10%">
										<b>物料属性</b>
									</td>
									
									<td class="altbg1">
									</td>
								</tr>
							</tbody>
							<tbody>
								<%
									List<BaseMaterial> materials = 
									(List<BaseMaterial>)request.getAttribute("basematerials");
									for(int i=0;i<materials.size();i++){
										BaseMaterial curr = materials.get(i);
										%>
										<tr>
									<td class="altbg2">
										&nbsp;&nbsp;<%=curr.getMaterial_id()%>
									</td>
									<td class="altbg2">
										<img src="img/d007/<%=curr.getFilename()%>" width="190" height="120" />
									</td>
									<td class="altbg2">
										<%=curr.getMaterial_name()%>
									</td>
									<td class="altbg2">
										<%=curr.getMaterial_spec()%>
									</td>
									<td class="altbg2">
									<%=curr.getUint()%>
									</td>
									<td class="altbg2">
									<%=curr.getMaterial_attr()%>
									</td>
									<td class="altbg2">
										<a href="buy.do?id=<%=curr.getMaterial_id()%>">购买</a>
									<%
										String info = (String)request.getAttribute("buy_err_" + curr.getMaterial_id());
									 %>
									<span style="color:red;font-size:12px;">
										<%=(info == null)? "" : info %>
									</span>
									</td>
								</tr>
										<%
									}
								 %>
							</tbody>
						</table>
						<br />
						<center>
							<input class="button" type="button" value="查看购物车"
								name="settingsubmit" onclick="location = 'cart.jsp';">
						</center>
					</td>
				</tr>
			</tbody>
		</table>

	</body>
</html>
