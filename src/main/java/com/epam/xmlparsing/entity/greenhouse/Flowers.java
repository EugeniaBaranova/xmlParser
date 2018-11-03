package com.epam.xmlparsing.entity.greenhouse;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Flowers")
public class Flowers {


@XmlElements({
        @XmlElement(name="Flower", type = Flower.class),
        @XmlElement(name="Rose", type = Rose.class),
        @XmlElement(name="Peony", type = Peony.class)
})


    private List<Flower> flowers;

    public List<Flower> getFlowers() {
        return flowers;
    }

    public void setFlowers(List<Flower> flowers) {
        this.flowers = flowers;
    }
}
