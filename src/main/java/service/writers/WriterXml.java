package service.writers;

import service.structure.Dorama;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class WriterXml implements Writer {
    @Override
    public void write(Object object, String filename) {
        ArrayList<Dorama> doramas = (ArrayList<Dorama>) object;
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        try {
            writer = output.createXMLStreamWriter(new FileOutputStream(filename), "UTF-8");
            writer.writeStartDocument("utf-8", "1.0");
            writer.writeCharacters("\n");
            writer.writeStartElement("doramas");

            for (int i = 0; i < doramas.size(); i++) {
                writeDorama(writer, doramas.get(i), i);
            }
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();

        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void writeDorama(XMLStreamWriter writer, Dorama dorama, int id) {
        try {
            writer.writeCharacters("\n\t");
            writer.writeStartElement("dorama");
            writer.writeAttribute("id", String.valueOf(id));

            writeElement(writer, "name", dorama.getName());
            writeElement(writer, "date", dorama.getData());
            writeElement(writer, "country", dorama.getCountry());
            writeGenres(writer, dorama);
            writer.writeCharacters("\n\t");
            writer.writeEndElement(); // Close the "dorama" element
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeGenres(XMLStreamWriter writer, Dorama dorama)  {
        try {
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement("genres");
            for (String genre : dorama.getGenres()) {
                writer.writeCharacters("\n\t\t\t");
                writer.writeStartElement("genre");
                writer.writeCharacters(genre);
                writer.writeEndElement();
            }
            writer.writeCharacters("\n\t\t");
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
    private void writeElement(XMLStreamWriter writer, String elementName, String value) {
        try {
            writer.writeCharacters("\n\t\t");
            writer.writeStartElement(elementName);
            writer.writeCharacters(value);
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
