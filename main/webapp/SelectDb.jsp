<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<title>SELECT DB</title>
</head>
<body>
<p id="result"></p>
<div class="container">
<h1>SELECT YOUR CHOICE OF DATABASE</h1><br><br>
<button class="btn-grp" onclick="mysql()">MYSQL</button><br><br>
<button class="btn-grp" onclick="psql()">PSQL</button>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
	function mysql()
	{
			  
		var name="mysql";
		
		$.ajax({
			
			type : 'POST',
			url: 'select',
			data :{name : name},
			success:function(result)
			{
				
				if(result=="error") 
				{
					 $("#result").empty();
					 
				 $('#result').append("Error! Request failed");
				  
				}
				else
				{
		
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
				else
				{
					throw "Error! Something went wrong"; // No I18N
				}
				}
				catch(err)
				{
				$("#result").empty();
				  
		        
				 $('#result').append(err);
				 	 
				 
				 $("#container").click(function()
					{
					  $("#result").empty();
					});
				}
				}
			}); 
	}
		
		function psql()
		{
				  
			var name="psql";
			
			$.ajax({
				
				type : 'POST',
				url: 'select',
				data :{name : name},
				success:function(result)
				{
					
					if(result=="error") 
					{
						 $("#result").empty();
						 
					 $('#result').append("Error! Request failed");
					  
					}
					else
					{
			
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
					else
					{
						throw "Error! Something went wrong"; // No I18N
					}
					}
					catch(err)
					{
					$("#result").empty();
					  
			        
					 $('#result').append(err);
					 	 
					 
					 $("#container").click(function()
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