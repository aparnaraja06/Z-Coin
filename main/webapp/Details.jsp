
<%@ page import="operation.CoinOperation" %>
<%@ page import="operation.CreateInstance"%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DETAILS</title>
<link rel="styleshee<%@page import="user.ZCoin"%>t" href="Details.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<p id="result"></p>
<%
CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();

String typeName = request.getParameter("type");

%>
<div id="coin">
<h2>Z COIN</h2>
<h2 id="rate">Rate ~ <%= coin.zCoinRate()%></h2>
<h2 id="date"><%= coin.getDate()%></h2>
</div>
<p id="result"></p>
<%

if(typeName.equals("details"))
{
%>
<h1>USER DETAILS</h1>
<table id="center">
<tr>
<th>MAIL</th>
<th>NAME</th>
<th>MOBILE</th>
<th>HUMAN_ID</th>
<th>RC AMOUNT</th>
<th>APPROVED</th>
<th>ROLE AS USER</th>
<th>ROLE AS ADMIN</th>
</tr>
<%

List<user.ZCoin.User.Builder> list = coin.showWaitingList();

if(list.isEmpty())
{
%>
<tr><td colspan="8" class="record"><b>*** No records found ***</b></td></tr>
<%
}
else
{
for(int i=0;i<list.size();i++)
{
	user.ZCoin.User.Builder user=list.get(i);
%>
<tr>
<td class="mail"><%=user.getMail()%></td>
<td><%=user.getName() %></td>
<td><%=user.getMobile()%></td>
<td><%=user.getHumanId()%></td>
<td><%=user.getRcAmount()%></td>
<td><%=user.getRole() %></td>
<td><button class="add" value="user">ADD USER</button></td>
<td><button class="add" value="admin">ADD ADMIN</button></td>
</tr>
<%} }%>
</table>
<%
}
else
{
%>
<h1>ACCOUNT DETAILS</h1>
<table class="center">
<tr>
<th>ACCOUNT NUMBER</th>
<th>RC AMOUNT</th>
<th>ZC AMOUNT</th>
</tr>
<%
int id = (int)session.getAttribute("user_id");

 account.ZCoin.Account.Builder account=coin.accountDetails(id);

%>
<tr>
<td><%=account.getAccountNum()%></td>
<td><%=account.getRcAmount()%></td>
<td><%=account.getZcAmount()%></td>
</tr>
<%}%>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

$("#center").on('click', '.add', function() {

	
		var currentRow = $(this).closest("tr");

		  var id = currentRow.find(".mail").html(); // get current row 1st table cell TD value
		  
		var temp = $(this).attr("value");
		
		var role;
		if(temp==="user")
		{
			 role="user";
		}
		else
		{
			role = "admin";
		}
		
       $.ajax({
			
			type : 'POST',
			url: 'add',
			data :{id : id,role : role},
			success:function(result)
			{
				if(result=="Invalid username") 
				{
					 $("#result").empty();
					 
				 $('#result').append(result);
				 				  
				}
				else
				{
					 $("#result").empty();
					 
					 $('#result').append("Successfully added!");
				}
				
			},
			 error: function(xhr)
				{

					try
					{
				
				    if(xhr.status==404)
				    {
				    	throw "Page not found";
				    }
					else
					{
						throw "Error! Something went wrong"; // No I18N
					}
					}
					catch(err)
					{
					$("#result").empty();
					  
			        
					 $('#result').append(err);
					 	 
					 
					}
					}
				}); 
				
		});
	
});
</script>	
</body>
</html>