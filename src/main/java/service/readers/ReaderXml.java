package service.readers;

import lombok.experimental.FieldDefaults;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import lombok.val;
import service.structure.Doramas;

@FieldDefaults(makeFinal = true)
public class ReaderXml{
    /**
     * Читает информацию о дорамах из XML-файла.
     *
     * @param filePath Путь к XML-файлу
     * @return Объект класса Doramas, содержащий информацию о дорамах
     */
    public static Doramas readFile(String filePath) {
        try {
            val file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Doramas.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            unmarshaller.setAdapter(new LocalDateAdapter());

            return (Doramas) unmarshaller.unmarshal(file);
        } catch (JAXBException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
