package mainClasses;

import com.google.gson.Gson;
import entities.Anime;
import entities.Ann;
import entities.Info;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @EJB
    private AnnJAXB annj;

    @EJB
    private Ann ann;

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at "
                    + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String searchQuery = request.getParameter("searchQuery");

        returnResults(response, searchQuery);

    }

    private void returnResults(HttpServletResponse response, String searchQuery)
            throws IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        ArrayList<Map> arrayOfMap = new ArrayList<Map>();

        try {
            
            // Call the Unmarshalling method of an instance annj of our AnnJAXB service, 
            // passing in searchQuery received from our JSP submission form via Ajax
            // and returning the object Anime of the returned instantiated object Ann...
            
            for (Anime anime : annj.Unmarshalling(searchQuery).getAnn()) {

                Map<String, String> map = new HashMap<String, String>();

                // ...for each instance of object Anime, map attributes to a hashmap...
                
                map.put("name", anime.getName());
                map.put("id", anime.getId());

                for (Info temp : anime.getAnime()) {
                    
                    //...including the src attribute if one is present...
                    
                    if (temp.getSrc() != null) {

                        map.put("url", temp.getSrc());
                    }
                }
                
                //...and add to an ArrayList
                
                arrayOfMap.add(map);

            }
            
            // Use Gson to parse this to a json object and send as response to Ajax (basic.js)

            response.getWriter().write(new Gson().toJson(arrayOfMap));

        } catch (Exception e) {
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
