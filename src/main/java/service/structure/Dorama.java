package service.structure;

import service.readers.LocalDateAdapter;

import lombok.Data;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Dorama {
    @XmlAttribute(name = "id")
    int id;
    @XmlElement(name = "name")
    String name;
    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class) // Добавлен адаптер для сериализации/десериализации LocalDate
    LocalDate date;
    @XmlElement(name = "country")
    String country;
    @XmlElementWrapper(name = "genres")
    @XmlElement(name = "genre")
    List<String> genres;

    @XmlElement(name = "characters")
    Character characters;
}
