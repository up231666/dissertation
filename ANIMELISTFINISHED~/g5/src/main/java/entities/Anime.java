package entities;

import javax.xml.bind.annotation.*;
import java.util.*;
import javax.ejb.Stateless;

@Stateless
@XmlRootElement(name = "anime")
public class Anime {

    private String id;
    private String name;
    private List<Info> Anime = new ArrayList<Info>();

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    @XmlAttribute(name = "name")
    public String getName() {
        return name;
    }

    @XmlElement(name = "info")
    public List<Info> getAnime() {
        return Anime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnime(List<Info> Anime) {
        this.Anime = Anime;
    }

    public Anime(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Anime() {
        super();
    }

}
