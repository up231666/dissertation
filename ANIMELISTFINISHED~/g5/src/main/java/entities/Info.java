package entities;

import javax.xml.bind.annotation.*;
import javax.ejb.Stateless;

@Stateless
@XmlRootElement(name = "info")
public class Info {

    private String src;

    @XmlAttribute(name = "src")
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Info(String src) {
        this.src = src;
    }

    public Info() {
        super();
    }
}
