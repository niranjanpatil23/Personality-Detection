<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <title>PERSONALITY PREDICTION</title>

        <!-- Compatibility with Older Versions of Web Browsers -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <!-- Custom fonts -->
        <link href="http://fonts.googleapis.com/css?family=Roboto:300,100" rel="stylesheet">
        
        <!-- CSS -->
        <link href="css/main.css" rel="stylesheet">
    
        <!-- facebook login -->
        <script type="text/javascript" src="js/facebook-login.js"></script>    
    </head>

    <body>
        <style>
            #centerimage{
                background: url(images/bg.jpg);
		padding: 16em 0 13em 0;
		background-attachment: fixed;
		background-position: center top;
		background-size: cover;
		line-height: 1.75;
		text-align: center;
                }
        </style>      

        <header>           
		<div class="active">
			<nav id="e-nav">
				<ul id="f-nav">
					<li style="float:left"><a href="index.jsp">Major Project</a></li>			
					<li style="float:right"><a href="index.jsp">Home</a></li>
				</ul>
			</nav>
		</div>        
        </header>      
        
        <section id="centerimage">
        <div id="maincentral">
            <p id="mainbigfont"><font color="#fffff">Personality Prediction</font></p>                 
        </div>
      
        <div id="fbbuttoncentral">
                <a href="javascript:loginFacebook()"><img src="images/fb-icon.png"></a>
        </div>
        <div id="getpbuttoncentral">                                                                        
                <div style="height: 125px">
                    <form  action="GetPersonality.jsp" method="get">                                         
                        <button style="width: 200px; height: 45px; background-color: #D44032; border:0"><font color="white">Get Personality</font></button>                           
                </form>                                       
            </div>                                  
        </div>
            
        </section>
    
    <section>
    <div>
        <h1>OCEAN MODEL<h1>
    </div>
    </section>
    </body>
</html>


