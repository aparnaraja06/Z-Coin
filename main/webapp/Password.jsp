<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PASSWORD</title>
<link rel = "icon" href = 
"A-letter.jpg" 
        type = "image/x-icon">
<link rel="stylesheet" href="Login.css">
</head>
<body>
<div class="container">
<form>
<img src="z coin.jpg" alt="Coin" width="100" height="80"><br>
<h2>PASSWORD</h2><br><br>
<input type="password" placeholder="Enter Password" id= "pass" name="password" required>
<p id="result"></p><br><br>
<input type="button" id="loginbtn" value="LOGIN" onclick="welcome()"><br><br>
</form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">

	function welcome()
	{

		
		var pass=$('#pass').val();
		
		$.ajax({
			
			type : 'POST',
			url: 'password',
			data :{pass : pass},
			success:function(result)
			{
				
				
				if(result=="Incorrect password")
				{
					 $("#result").empty();
					 
				    $('#result').html("Incorrect Password");
				    
				    $("#pass").click(function(){
						  $("#result").empty();
						}); 
				}
				else if(result=="admin")
				{
					var successUrl = "Details.jsp?type=details";
				    window.location.href = successUrl;
				}
				else
				{
					var successUrl = "Details.jsp?type=account";
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
						throw "Username should not be empty!"; // No I18N
					}
					else if(xhr.status==404)
					{
						throw "Password should not be empty!";  // No I18N
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
					 	 
					 
					 $("#pass").click(function()
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