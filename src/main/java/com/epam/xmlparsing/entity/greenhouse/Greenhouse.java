package com.epam.xmlparsing.entity.greenhouse;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "greenhouse")
public class Greenhouse {


@XmlElements({
        @XmlElement(name="flower", type = Flower.class),
        @XmlElement(name="rose", type = Rose.class),
        @XmlElement(name="peony", type = Peony.class)
})


    private List<Flower> flowers;

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }
}
