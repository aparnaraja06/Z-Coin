<%@ page import="operation.CoinOperation" %>
<%@ page import="operation.CreateInstance"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DETAILS</title>
<link rel="stylesheet" href="Signup.css">
</head>
<body>
<jsp:include page='Menu.jsp'>
<jsp:param name="TRANSFER" value=" " />
</jsp:include>
<p id="msg"></p>
<div class="container">
<h2>CHANGE PASSWORD</h2><br><br> <!-- No I18N -->
<label>OLD PASSWORD</label><br><br><br> <!-- No I18N -->
<input type="password" placeholder="Old Password" name="password" id="old" required><br><br>
<p id="resultt"></p>
<label>NEW PASSWORD</label><br><br><br>  <!-- No I18N -->
<input type="password" placeholder="New Password" name="password" id="new1" required><br><br><br>
<label>RE ENTER NEW PASSWORD</label><br><br><br> <!-- No I18N -->
<input type="password" placeholder="New Password" name="password" id="new2" required><br><br><br>
<p id="result"></p>
<div id="msgbox"></div><br>
<input type="button" id="loginbtn" value="SUBMIT" onclick="change()"><br><br>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function change()
	{
		var old = $('#old').val();
		var new1 = $('#new1').val();
		var new2 = $('#new2').val();
		
        $.ajax({
			
			type : 'POST', // No I18N
			url: 'change', // No I18N
			data :{old : old, new1 : new1, new2 : new2},
			success:function(message)
			{
				if(message=="Password doesn't match")
				{
					$("#result").empty();
					 
					 $('#result').append("Password doesn't match"); // No I18N
					 
					 $("#new1").click(function(){
						  $("#result").empty();
						});
				}
				else if(message=="Incorrect password")
				{
					$("#resultt").empty();
					 
					 $('#resultt').append("Incorrect password"); // No I18N
					 
					 $("#old").click(function(){
						  $("#resultt").empty();
						});
				}
				else
				{
					$("#msg").empty();
					 
					 $('#msg').append("Updated successfully"); // No I18N
					 
					 var successUrl = "logout";
					    window.location.href = successUrl;
					 
				}
			},
			error: function(xhr)
			{

				try
				{
				 if(xhr.status==403)
				{
					throw "Username should not be empty!"; // No I18N
				}
				else if(xhr.status==404)
				{
					throw "Password should not be empty!";  // No I18N
				}
				else if(xhr.status==406)
				{
					
					throw "Password should contain 8 characters";
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
				 	 
				 
				 $("#old").click(function()
					{
					  $("#result").empty();
					});
				 $("#new1").click(function()
							{
							  $("#result").empty();
							});
				 $("#new2").click(function()
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