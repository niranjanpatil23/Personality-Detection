<%@page import="edu.stanford.nlp.tagger.maxent.MaxentTagger"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="com.facebook.connect.FacebookAPI"%>
<%@page import="com.facebook.connect.pojo.Data"%>
<%@page import="com.facebook.connect.pojo.FacebookPojo"%>
<%@page import="com.facebook.connect.pojo.Feed"%>
<%@page import="com.facebook.connect.FBGraph"%>
<%@page import="com.facebook.connect.FBConnection"%>

<%@page import="java.util.List"%>
<%@page import="Getpersonality.Algorithm"%>
<%@page import="Getpersonality.MyFirst"%>
<%@page import= "com.opencsv.CSVReader"%>
<%@page import= "com.opencsv.CSVWriter"%>
<html>
    <head>
           <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script type="text/javascript">
            function closeWindow() {
            <%!
                private String code = "";
                private static final String COMMA_DELIMITER = ",";    
                private static final String NEW_LINE_SEPARATOR = "\n";
               // private static final String FILE_HEADER = "id,status";
               String[] FILE_HEADER = {"id","status"};
               String h;
               String[] strid; //= new String[] {fp.getId()};
              // String[] strstatus; 
            %>
            <%
                              
             
             code = request.getParameter("code");
                if (code == null || code.equals("")) {
                    throw new RuntimeException(
                            "ERROR: Didn't get code parameter in callback.");
                }
              /*  FBConnection fbConnection = new FBConnection();
                String accessToken = "CAACEdEose0cBAKWaaFLNiobP7h8Ln0G75ovxxjahu5AzePlZAogiC5C5CEQrLZApjqnK0FI95WarOvYfqtmlZBSZAq16nEoATVXFFdP6Qzp1yQ6cSheEJzFxfA89TKgeVy9gIWMgjSztqxSG45DupQ95E02NZCe61RISa8bMKTqw9DJRewXSwfvPWP83MNQs2oR2BkGqLNQZDZD";
// fbConnection.getAccessToken(code);

                System.out.println("--------------------------");
                System.out.println("accessToken=" + accessToken);
                System.out.println("--------------------------");*/
                
                FBConnection fbConnection = new FBConnection();
                String accessToken = fbConnection.getAccessToken(code);
                System.out.println("Code=" + code);
                System.out.println("--------------------------");
                System.out.println("accessToken=" + accessToken);
                System.out.println("--------------------------");


                
                FBGraph fbGraph = new FBGraph(accessToken);
                String graph = fbGraph.getFBGraph();

                fbGraph.getGraphData(graph);

                Feed fd = fbGraph.getFd();
                FacebookPojo fp = fbGraph.getFcp();
             
               // FileWriter writer = new FileWriter("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\fbdata.csv");
                
               // writer.append(FILE_HEADER.toString() + "\n");
                
             //  CSVWriter writer = new CSVWriter("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\fbdata.csv");
               CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Aakash\\Documents\\NetBeansProjects\\FBP\\fbdata.csv"));
               writer.writeNext(FILE_HEADER);
                int i=1;
                //System.out.println("<h1>Facebook Login using Java</h1>");
                //System.out.println("<h2>Application Main Menu</h2>");
                //System.out.println("<div>Welcome " + fp.getName());
                //System.out.println("<div>Your FBID " + fp.getId());
                //System.out.println("<div>Your Email: " + fp.getEmail());
                //System.out.println("<div>Your feed ");
               // System.out.println("<div>Your feed: " + fp.getFeed());
                
                
                
                System.out.println("Welcome:\t\t " + fp.getName());
                System.out.println("Your USERID:\t\t " + fp.getId());
                System.out.println("Your Email Address:\t " + fp.getEmail());
                System.out.println("Your Status Updates are:\n ");
              //  strid = new String[] {fp.getId()};
                 
                try {
                    for (Data data : fd.getData()) {
                     //   System.out.println("\n <div> FEED ID     :" + data.getId());
                      //  System.out.println("\n <div> FEED MESSAGE:" + data.getMessage());
                       //  strstatus =new String[]{data.getMessage()};
                       
                        if(data.getMessage()!=null)
                        {
                            strid = new String[] {fp.getId(),data.getMessage()};
                            System.out.println("\n STATUS UPDATE "+ i +":"  + data.getMessage());
                 //         writer.append(fp.getId());
                         
                            writer.writeNext(strid);
                            // writer.append(",");
                            // writer.append(data.getMessage());
                            //  writer.append("\n");
                            //  writer.writeNext(strstatus);
                        }
                        i++;
                    // System.out.println("\n <div>FEED STORY   : " + data.getStory());
                    //System.out.println("\n <div>FEED CREATED TIME:" + data.getCreated_time());
                    }
                } 
                catch (Exception e) {
                }
                
                writer.flush();
                writer.close();             
                
                
                FacebookAPI fapi = new FacebookAPI();
                fapi.setFd(fd);
                fapi.setFp(fp);
                
                
                application.setAttribute("apiObject", fapi);             
                   
            %>

           // window.open("http://www.facebook.com/dialog/oauth?client_id=100123217048446&redirect_uri=http://localhost:8080/FBP/intermediate.jsp");
        //window.location.replace="http://localhost:8080/FBP/intermediate.jsp";
        self.close();
                
            }
        </script>
    </head>

    <body onload="javascript:closeWindow()">


    </body>

</html>



	

