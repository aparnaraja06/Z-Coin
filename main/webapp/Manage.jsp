<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MANAGE</title>
<link rel="stylesheet" href="Manage.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<%
String typeName = request.getParameter("type");

if(typeName.equals("set"))
{
%>
<p id="result"></p>
<div class="container">
<h1>SET Z COIN RATE</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="set()"><br><br>
</div>
<%}

else
{
%>
<p id="result"></p>
<div class="container">
<h1>BUY Z COIN</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="buy()"><br><br>
</div>
<%}%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function set()
	{
			  
		var name=$('#amount').val();
		
		var type="set";
		
		$.ajax({
			
			type : 'POST',
			url: 'manage',
			data :{name : name, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! unable to set");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$('#result').append("Successfully updated");
					
				}
				
			},
			    
			
			 error: function(xhr)
			{

				try
				{
				
				if(xhr.status==414)
				{
					throw "Invalid input type";  // No I18N
				}
				else if(xhr.status==408)
				{
					throw "Invalid input type in amount";  // No I18N
				}
				else if(xhr.status==413)
				{
					throw "Input should not be empty";
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
				 	 
				 
				 $("#amount").click(function()
					{
					  $("#result").empty();
					});
				}
				}
			}); 
			
	}
	
	function buy()
	{
         var name=$('#amount').val();
         
         var type = "buy";
		
		$.ajax({
			
			type : 'POST',
			url: 'manage',
			data :{name : name, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! unable to Buy");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$('#result').append("Successfully Bought");
					
				}
				
			},
			    
			
			 error: function(xhr)
			{

				try
				{
				
				if(xhr.status==414)
				{
					throw "Invalid input type";  // No I18N
				}
				else if(xhr.status==408)
				{
					throw "Invalid input type in amount";  // No I18N
				}
				else if(xhr.status==413)
				{
					throw "Input should not be empty";
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
				 	 
				 
				 $("#amount").click(function()
					{
					  $("#result").empty();
					});
				}
				}
			}); 
	}
</script>	
</body>
</html>