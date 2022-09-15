<%@page import="user.ZCoin"%>
<%@ page import="operation.CoinOperation" %>
<%@ page import="operation.CreateInstance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SIGN UP</title>
<link rel="stylesheet" href="Signup.css">
</head>
<body>
<%
CoinOperation coin = CreateInstance.COINOPERATION.getCoinInstance();

String typeName = request.getParameter("type");

if(typeName.equals("signup"))
{
%>
<div class="total">
<div class="container">
<form id="login_form">
<h2>SIGN UP </h2><br>
<p id="msg"></p>
<label>NAME</label><br>
<input type="text" placeholder="Name" name="username" id="name" required><br>
<label>MAIL ID</label><br>
<input type="text" placeholder="Email address" name="mail" id="mail" required><br>
<label>MOBILE NUMBER</label><br>
<input type="text" placeholder="Mobile Number" name="mobile" id="mobile"  required><br>
<label>HUMAN ID</label><br>
<input type="text" placeholder="Human Id" name="human_id" id="human_id" required><br>
<label>PASSWORD</label><br>
<input type="password" placeholder="Password" name="password" id="pass" required><br>
<label>AMOUNT</label><br>
<input type="text" placeholder="Amount" name="amount" id="amount" required><br><br>
<input type="button" id="loginbtn" value="SIGNUP" onclick="signup()"><br><br>
</form>
</div>
</div>
<%
}
else
{
	int id = (int)session.getAttribute("user_id");
	
	
	user.ZCoin.User.Builder user = coin.getUser(id);

%>		
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<div class="total">
<div class="container">
<form id="login_form">
<h2>PROFILE</h2><br>
<p id="msg"></p>
<label>NAME</label><br>
<input type="text" id="name" value=<%=user.getName()%> readonly>
<input type="button" id="btn" value="EDIT" onclick="point_cursor()">
<label>MAIL ID</label><br>
<input type="text" id="mail" value=<%=user.getMail()%> readonly><br>
<label>MOBILE NUMBER</label><br>
<input type="text"  id="mobile" value=<%=user.getMobile()%> readonly><br>
<label>HUMAN ID</label><br>
<input type="text" id="human_id" value=<%=user.getHumanId()%> readonly><br>
<label>AMOUNT</label><br>
<input type="text" id="amount" value=<%=user.getRcAmount()%> readonly><br><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="edit()" disabled><br>
</form>
</div>
</div>
<%} %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

		function signup()
		{
	
			var name=$('#name').val();
			var mail=$('#mail').val();
			var mobile=$('#mobile').val();
			var human_id=$('#human_id').val();
			var password=$('#pass').val();
			var amount=$('#amount').val();
			var type="signup";
			
			$.ajax({
				
				type : 'POST',
				url: 'signUp',
				data :{name : name, mail : mail,mobile : mobile, human_id : human_id,
					password : password, amount : amount,type : type},
					
				success:function(result)
				{
		
					if(result=="User already exists") 
					{
						 $("#msg").empty();
						 
					 $('#msg').append("User already exists");
					 
					 var successUrl = "Login.jsp";
					    window.location.href = successUrl;
					  
					}
					else
					{
						 $("#msg").empty();
						 
						 $('#msg').append("Successfully added!");
						 
						 var successUrl = "Login.jsp";
						    window.location.href = successUrl;
			
					}
					
				},
				
				error: function(xhr)
				{
        
					try
					{
					if(xhr.status==401)
					{
						throw "Oops! Connection failed! "; // No I18N
					}
					
					else if(xhr.status==402)
					{
						throw "Error! couldn't close Connection! "; // No I18N
					}
					
					else if(xhr.status==403)
					{
						throw "Username should not be empty"; // No I18N
					}
					else if(xhr.status==404)
					{
						throw "Password should not be empty"; // No I18N
					}
					else if(xhr.status==405)
					{
						throw "Mail id already exists"; // No I18N
					}
					else if(xhr.status==406)
					{
						throw "Password should contain 8 characters"; // No I18N
					}
					else if(xhr.status==408)
					{
						throw "Invalid type in amount"; // No I18N
					}
					else if(xhr.status==410)
					{
						throw "Invalid mail id"; // No I18N
					}
					else if(xhr.status==411)
					{
						throw "Invalid Human Id"; // No I18N
					}
					else if(xhr.status==412)
					{
						throw "Invalid mobile number"; // No I18N
					}
					else
					{
						throw "Error! Something went wrong"; // No I18N
					}
					}
					catch(err)
					{
					$("#msg").empty();
					  
			        
					 $('#msg').append(err);
					 	 
					 
					 $(".total").click(function()
						{
						  $("#msg").empty();
						});
					}
					}
				}); 
		}
		
		function point_cursor()
		{
			
			document.getElementById('name').removeAttribute("readonly");
			
			document.getElementById('loginbtn').removeAttribute("disabled");
			
			var text = $('#name');
			
			var str_length = text.val().length*2;
			
			text.focus();
			
			text[0].setSelectionRange(str_length,str_length);
			
		}
		
		function edit()
		{
		
			
			var name=$('#name').val();
			
			var type = "edit";
			
	       $.ajax({
				
				type : 'POST',
				url: 'signUp',
				data :{name : name,type : type},
					
				success:function(result)
				{
		
					if(result=="Error! Update failed") 
					{
						 $("#msg").empty();
						 
					 $('#msg').append(result);
					 
					  
					}
					else
					{
						 $("#msg").empty();
						 
						 $('#msg').append("Successfully updated!");
						 
						 document.getElementById('name').setAttribute("readonly", "");
						 document.getElementById('loginbtn').setAttribute("disabled", "");
			
					}
					
				},
				
				error: function(xhr)
				{
        
					try
					{
					
					if(xhr.status >= 400)
					{
						throw "Error! Something went wrong"; // No I18N
					}
					}
					catch(err)
					{
					$("#msg").empty();
					  
			        
					 $('#msg').append(err);
					 	 
					 
					 $(".total").click(function()
						{
						  $("#msg").empty();
						});
					}
					}
				}); 
		}
</script>	
</body>
</html>