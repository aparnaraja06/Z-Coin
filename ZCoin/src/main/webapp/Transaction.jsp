<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TRANSACTION</title>
<link rel="stylesheet" href="Manage.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<%
String typeName=(String)request.getParameter("type");
if(typeName.equals("withdraw"))  
{
%>
<p id="result"></p>
<div class="container">
<h1>WITHDRAW RC</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="withdraw()"><br><br>
</div>
<%}
else if(typeName.equals("deposit"))
{
%>
<p id="result"></p>
<div class="container">
<h1>DEPOSIT RC</h1><br><br>
<h2>ENTER AMOUNT</h2>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="deposit()"><br><br>
</div>
<%}
else
{
%>
<p id="result"></p>
<div class="container">
<h1>TRANSFER ZC</h1>
<label><b>ENTER ACCOUNT NUMBER</b></label>
<input type="text" placeholder="Account Number" name="account" id="account1" required><br>
<label><b>RE ENTER ACCOUNT NUMBER</b></label>
<input type="text" placeholder="Account Number" name="account" id="account2" required><br>
<label><b>ENTER AMOUNT</b></label>
<input type="text" placeholder="Amount" name="amount" id="amount" required>
<br><br><input type="button" id="loginbtn" value="SUBMIT" onclick="transfer()">
</div>
<%}%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

	function withdraw()
	{
			
		var name=$('#amount').val();
		
          var type="withdraw";
		
		
		$.ajax({
			
			type : 'POST',
			url: 'transaction',
			data :{name : name, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! Request failed");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$("#result").empty();
					 
					 $('#result').append("Successfully withdrawn");
					
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
				else if(xhr.status==409)
				{
					throw "Your balance is less than amount entered";  // No I18N
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
	function deposit()
	{
			  
		var name=$('#amount').val();
		
		var type="deposit";
		
		
		$.ajax({
			
			type : 'POST',
			url: 'transaction',
			data :{name : name, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! Request failed");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$("#result").empty();
					 
					 $('#result').append("Successfully desposited");
					
				}
				
			},
			    
			
			 error: function(xhr)
			{

				try
				{
				 if(xhr.status==414)
				{
					throw "Invalid input";  // No I18N
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
	function transfer()
	{

		var account1=$('#account1').val();
		
		var account2=$('#account2').val();
		
		var amount=$('#amount').val()
		
		var type="transfer";
		
		
		$.ajax({
			
			type : 'POST',
			url: 'transaction',
			data :{account1 : account1, account2 : account2, amount : amount, type : type},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! Request failed");
				 
				 $("#amount").click(function(){
					  $("#result").empty();
					});
				  
				}
				else
				{
					$("#result").empty();
					 
					 $('#result').append("Successfully transferred");
					
				}
				
			},
			    
			
			 error: function(xhr)
			{
				
				 alert("Status : "+xhr.status);
				try
				{
				
				if(xhr.status==414)
				{
					throw "Invalid Account number";  // No I18N
				}
				else if(xhr.status==408)
				{
					throw "Invalid input type in amount";  // No I18N
				}
				else if(xhr.status==409)
				{
					throw "Your balance is less than amount entered";  // No I18N
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
				  
		        
				 $("#result").append(err);
				 	 
				 
				 $("#amount").click(function()
					{
					  $("#result").empty();
					});
				 $("#account1").click(function()
							{
							  $("#result").empty();
							});
				 $("#account2").click(function()
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