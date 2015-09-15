
package mainClasses;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

@WebServlet(name = "ImageGenerationServlet", urlPatterns = {"/generate"})
public class ImageGenerationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ImageGenerationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImageGenerationServlet at "
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

        List<String> imageURLs = new ArrayList<String>();
        try {
            BufferedReader reader = request.getReader();
            String line;
            do {
                line = reader.readLine();

                // For each read line, if the line is not null 
                // add that line to an ArrayList 
                // (these lines consists of the src attributes of elements passed from submitList Ajax
                // and are URLs)
                // until no more URLs
                
                if (line != null) {
                    imageURLs.add(line);
                }

            } while (line != null);
        } catch (Exception e) {
        }

        try {
            ImageController imageController = new ImageController();

            // Instantiate our image controller, pass in the ArrayList of image URLs
            // Return the path of our generated image
            
            String filePath = imageController.controlImage((ArrayList<String>) imageURLs);

            // Pass the path of our generated image to our upload service
            // Return the JSON response from Imgur upload API as a String
            
            String imgurImageJson = UploadController.getImgurContent(filePath);

            // Parse this as a JSON Object in order to extract the JSON 
            // element corresponding to the link identifier of the object           
            
            JSONObject obj = new JSONObject(imgurImageJson);
            String finalImageUrl = obj.getJSONObject("data").getString("link");

            response.setCharacterEncoding("UTF-8");
            ;
            
            // Send this value back to our submitList Ajax as a JSON object
            
            response.getWriter().write(new Gson().toJson(finalImageUrl));

        } catch (Exception ex) {
            Logger.getLogger(ImageGenerationServlet.class.getName()).log(
                    Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
