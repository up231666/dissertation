package mainClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class UploadController {

    public static String getImgurContent(String generatedImageFilePath)
            throws Exception {

        // Registered application authentication ID
        
        String clientID = "b290a88ad882073";

        URL url;

        url = new URL("https://api.imgur.com/3/image");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Instantiate a file object of our passed in image file
        
        File file = new File(generatedImageFilePath);

        FileInputStream imageInFile = new FileInputStream(file);
        byte imageData[] = new byte[(int) file.length()];
        imageInFile.read(imageData);
        
        // Fetch the Base64 value of our file and pass this data to the Imgur upload API

        String a1 = Base64.encodeBase64String(imageData);

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID " + clientID);

        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        conn.connect();
        StringBuilder stb = new StringBuilder();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(a1);
        wr.flush();

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        wr.close();
        rd.close();
        
        // Return the JSON Imgur response as a string
        // This reponse contains a variety of information about the file we just uploaded
        // The key information we want from it is the URL so that we can pass this to 
        // our end user and allow them to acces it
        
        return stb.toString();
    }
}
