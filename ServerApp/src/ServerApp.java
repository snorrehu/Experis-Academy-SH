import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.*;

public class ServerApp extends HttpServlet{
    PrintWriter out;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // Set the response MIME type of the response message
        response.setContentType("text/html");
        // Allocate a output writer to write the response message into the network socket
        out = response.getWriter();

        // Write the response message, in an HTML page
        try {
            JSONObject object = jsonObjectMaker("https://anapioficeandfire.com/api/characters/583");
            out.println("<html>");
            out.println("<head><title>Hello, this is "+ object.getString("name")+"</title></head>");
            out.println("<body>");
            out.println("<h1>"+object.getString("name")+"</h1>");  // says Hello
            out.println("<p>Name: " + object.getString("name")  + "</p>");
            out.println("<p>Gender: " + object.getString("gender")  + "</p>");
            out.println("<p>Born: " + object.getString("born")  + "</p>");
            out.println("<p>Culture: " + object.getString("culture")  + "</p>");
         /*// Echo client's request information
         out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
         out.println("<p>Protocol: " + request.getProtocol() + "</p>");
         out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
         out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
         */
            out.println("</body></html>");
        } catch(JSONException e){

        } finally {
            out.close();  // Always close the output writer
        }
    }

}
