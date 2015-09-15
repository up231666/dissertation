package entities;

import javax.xml.bind.annotation.*;
import java.util.*;
import javax.ejb.Stateless;

@Stateless
@XmlRootElement(name = "ann")
public class Ann {

    private List<Anime> Ann = new ArrayList<Anime>();

    @XmlElement(name = "anime")
    public List<Anime> getAnn() {
        return Ann;
    }

    public void setAnn(List<Anime> Ann) {
        this.Ann = Ann;
    }

    public Ann() {
        super();
    }
}
