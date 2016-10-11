<%@page import="Getpersonality.Algorithm"%>
<%@ page  import="java.awt.*" %>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.axis.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import="org.jfree.chart.labels.*" %>
<%@ page  import="org.jfree.chart.plot.*" %>
<%@ page  import="org.jfree.chart.renderer.category.*" %>
<%@ page  import="org.jfree.chart.urls.*" %>
<%@ page  import="org.jfree.data.category.*" %>
<%@ page  import="org.jfree.data.general.*" %>

<%@page import="java.io.PrintWriter"%>
<%@page import="com.facebook.connect.pojo.Feed"%>
<%@page import="java.util.Set"%>
<%@page import="java.io.File"%>
<%@page import="com.facebook.connect.pojo.Data"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.facebook.connect.FacebookAPI"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/main.css" rel="stylesheet">
        <script type="text/javascript" src="js/facebook-login.js"></script>
        <script type="text/javascript" src="js/facebook-logout.js"></script>
        <script src="js/Plot.js"></script>
       
         <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
        <title>User Info</title>
    </head>
    <body>
        <header>           
            <div class="active">
		<nav id="e-nav">
                    <ul id="f-nav">
			<li style="float:left"><a href="index.jsp">Major Project</a></li>    
                        <li style="float:right"><a href="traits.html">OCEAN Model</a></li>
			<li style="float:right"><a href="index.jsp">Home</a></li>
                        <li style="float:right"><a href="GetUserInfo.jsp"><img src="images/profile-icon.png"></a></li>
                    </ul>
		</nav>
            </div>        
        </header>
        <%!
        
        double a1;
        double b1;
        double c1;
        double d1;
        double e1;
        double t1;
        %>
        
        
        
        <%
            response.setContentType("text/html;charset=UTF-8");
            
            
            FacebookAPI f = null;
            String[] Ocean_Score= new String[2];
            String[] OCEAN= new String[5];
            Algorithm a = new Algorithm();
            %>
            
            <font color = "#404040">
            
            <%
            
            Ocean_Score = a.str();
            OCEAN = a.getFin_arr();
            try {
                f = (FacebookAPI) request.getServletContext().getAttribute("apiObject");
                out.println("<h1 align=center>Personality Prediction </h1>");
                out.println("\n");
                out.println("<h2 align=center>Welcome " + f.getFp().getName() + "</h2>");
                out.println("");
              
            %>
                <h1 style = "margin:10;">-------------------------------------------------------------------------------------------------------------------------------</h1>
               <h2 align=center style = "margin: 0;">Big 5 Personality Model :- </h2>
               <h3 align=center style = "margin: 0;"><font color = red>Openness</font> <font color = white>........</font> <font color ="#A8CD18">  Conscientiousness</font>  <font color = white>........</font> <font color = "#6DBDD6">Extraversion </font>  <font color = white>........</font> <font color = "#118C4E">Agreeableness </font> <font color = white>........</font> <font color = "#FF9009">Neuroticism </font><font color = "#7e8f7c"></h3>;
               <%
                   out.print("<h3 align=center>" + OCEAN[0] + "<font color = white>...............................</font>" + OCEAN[1] + "<font color = white>...................................</font>" + OCEAN[2] + "<font color = white>..............................</font>" + OCEAN[3] + "<font color = white>.............................</font>" + OCEAN[4] + "</h3>");                   
                   
               %>
               <font color = "#404040">
               <h1 style = "margin: 0; padding : 0;">-------------------------------------------------------------------------------------------------------------------------------</h1>

                <h3 align=left style = "margin: 0;">Description :-</h3>
            <%  
                out.println("\n");
                out.println("<h3 align=left>"+ Ocean_Score[1] + "</h3>");
            %>
            
            <%    
                Feed feed = f.getFd();

                Data data[] = feed.getData();

                //out.println("<table align=center>");
               // out.println("<tr><td>Status Updates Considered: " + (data.length -1) + "</td></tr>");
              //  out.println("<tr><td>Status Updates Found: " + (data.length -1) + "</td></tr>");
               int i=1;
                for (Data str : data) {
                      
                    if (str.getMessage() != null) {
                        try {
                          
                        // out.println("<tr>td>"+ "Status Updates:" + i + str.getMessage() + "</td>");
                            //out.println("<tr><td>"+i +":"+" " + str.getMessage() + "</td>");
                        i++;
                         } catch (Exception e) {
                        }
                    }

                }

                //out.println("</table>");

            } catch (Exception e) {
               
            }
 
            
       
      %>
  

    </body>
</html>
