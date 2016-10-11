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
        
        <h1 align=center><font color = "404040">Personality Prediction </h1>
        <%
            response.setContentType("text/html;charset=UTF-8");
          
            FacebookAPI f = null;
            String Ocean_Score= null;
             String[] OCEAN= new String[5];
            Algorithm a = new Algorithm();
            OCEAN = a.getFin_arr();
            try {
                f = (FacebookAPI) request.getServletContext().getAttribute("apiObject");
                
                out.println("");
                out.println("<h3 align=center>Welcome " + f.getFp().getName() + "</h3>");
                out.println("<h3 align=center>User Id :- " + f.getFp().getId() + "</h3>");
                out.println("<h3 align=center>Email Id :- " + f.getFp().getEmail() + "</h3>");
                //out.println("<h3 align=center>Personality Score :- " + Ocean_Score + "</h3>");
                out.println("");
            %>
            <h1 align=center><font color = "#118C4E">Your Status Updates :-</font></h1>
            <%   
                Feed feed = f.getFd();

                Data data[] = feed.getData();

                out.println("<table align=center>");
               // out.println("<tr><td>Status Updates Considered: " + (data.length -1) + "</td></tr>");
              //  out.println("<tr><td>Status Updates Found: " + (data.length -1) + "</td></tr>");
               int i=1;
                for (Data str : data) {
                      
                    if (str.getMessage() != null) {
                        try {
                          
                        // out.println("<tr>td>"+ "Status Updates:" + i + str.getMessage() + "</td>");
                            out.println("<tr><td>"+i +":"+" " + str.getMessage() + "</td>");
                        i++;
                         } catch (Exception e) {
                        }
                    }

                }

                out.println("</table>");

            } catch (Exception e) {
               
            }
        %>
    </body>
</html>
