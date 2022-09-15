<%@ page import="operation.CoinOperation" %>
<%@ page import="operation.CreateInstance"%>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HISTORY</title>
<link rel="stylesheet" href="Details.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<%
CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();

String typeName = request.getParameter("type");
%>
<p id="result"></p>
<%
if(typeName.equals("transaction"))
{
%>
<h1>HISTORY</h1>
<table class="center">
<tr>
<th>USER ID</th>
<th>FROM ACCOUNT</th>
<th>TO ACCOUNT</th>
<th>AMOUNT</th>
<th>TYPE</th>
<th>DATE</th>
</tr>
<%
 Map<Integer,List<transaction.ZCoin.Transaction.Builder>> transferMap = coin.getAllHistory();

if(transferMap.isEmpty())
{
%>
<tr><td colspan="5" class="record"><b>*** No records found ***</b></td></tr>
<% 
}
else
{
 for(Entry<Integer,List<transaction.ZCoin.Transaction.Builder>> entry : transferMap.entrySet())
 {
	 int user_id = entry.getKey();
	 
	 List<transaction.ZCoin.Transaction.Builder> list = entry.getValue();
	 
	 for(int i=0;i<list.size();i++)
	 {
		 transaction.ZCoin.Transaction.Builder transfer = list.get(i);
%>
<tr>
<td><%=user_id%></td>
<td><%=transfer.getFromAccount()%></td>
<td><%=transfer.getToAccount()%></td>
<td><%=transfer.getAmount()%></td>
<td><%=transfer.getType()%></td>
<td><%=transfer.getDate()%></td>
</tr>
<%}}}%>
</table>
<% 
}
else
{%>
<h1>HISTORY</h1>
<table class="center">
<tr>
<th>TO ACCOUNT</th>
<th>AMOUNT</th>
<th>TYPE</th>
<th>DATE</th>
</tr>
<%
int id = (int)session.getAttribute("user_id");

 List<transaction.ZCoin.Transaction.Builder> list = coin.getHistoryByUserId(id);
	 
 if(list.isEmpty())
 {
%>
<tr><td colspan="5" class="record"><b>*** No records found ***</b></td></tr>
<%
}
 else
 {
	 for(int i=0;i<list.size();i++)
	 {
		 transaction.ZCoin.Transaction.Builder transfer = list.get(i);
%>
<tr>
<td><%=transfer.getToAccount()%></td>
<td><%=transfer.getAmount()%></td>
<td><%=transfer.getType()%></td>
<td><%=transfer.getDate()%></td>
</tr>
<%}}}%>
</table>
</body>
</html>