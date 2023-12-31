package service.structure;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Character {
    @XmlElement(name = "name")
    String name;
    @XmlElement(name = "age")
    int age;
}
