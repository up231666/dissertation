
package mainClasses;

import entities.*;
import javax.xml.bind.*;
import java.net.URL;
import java.net.MalformedURLException;
import javax.ejb.Stateless;

@Stateless
public class AnnJAXB {

    public Ann Unmarshalling(String searchString) throws JAXBException,
            MalformedURLException {

        // When method Unmarshalling is called, 
        // Use JAXB unmarshaller to unmarshal an XML response from ANN API 
        // using the passed in search string and returning the response to an Ann object
        // Return the Ann object
        // Called by our search servlet 
        
        JAXBContext jc = JAXBContext.newInstance(Ann.class);
        Unmarshaller ums = jc.createUnmarshaller();
        URL url = new URL(
                "http://cdn.animenewsnetwork.com/encyclopedia/api.xml?title=~"
                + searchString);

        Ann ann = (Ann) ums.unmarshal(url);

        return ann;
    }
}
