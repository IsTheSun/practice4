import org.junit.jupiter.api.Test;
import service.readers.ReaderXml;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReaderXmlTest {
    @Test
    void  testReadFile(){
        try{
            String filePath = "src\\test\\resources\\doramas.xml";

            ReaderXml readerXml = new ReaderXml();

            Object result = readerXml.readFile(filePath);
            assertNotNull(result);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
