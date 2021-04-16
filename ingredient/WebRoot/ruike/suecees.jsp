<%@page import="java.util.*,bean.*" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=utf-8" />
		<link href="css/main/style.css"
			type="text/css" rel="stylesheet" />
		<style>
			ul{
				list-style-type:none;
			}
			ul li{
				float:left;
				height:0px;
				line-height:30px;
				color:white;
				font-weight:100;
			}
		</style>
	</head>

	<body topMargin="10">
		<div id="append_parent"></div>
		<table cellSpacing="6" cellPadding="2" width="100%" border="0">
			<tbody>
				<tr>
					<td>
						<table class="guide" cellSpacing="0" cellPadding="0" width="100%"
							border="0">
							
						</table>
						<br/>
						<table class="tableborder" cellSpacing="0" cellPadding="0"
							width="100%" border="0">
							<tbody>
								<tr class="header">
									<td class="altbg2" colspan="6">
										申购信息提交成功
									</td>
								</tr>
						</table>
              
                        <center>
                        	<table>
                        	   <tr>
                        	   		<td>请购单编码:</td><td>${purchase_id}</td>
                        	   </tr>
                        	    <tr>
                        	   		<td>请购日期:</td><td>${opdate}</td>
                        	   </tr>
                        	</table>
                        </center>
						<br/>
						<center>
							<input class="button" type="button" value="确认"
								name="settingsubmit"
								onclick="location = '${cmd}';">
							<input class="button" type="button" value="返回列表"
								name="settingsubmit"
								onclick="location = '${cmd}';">
						</center>
					
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>



