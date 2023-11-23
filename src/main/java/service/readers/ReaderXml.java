package service.readers;

import service.structure.Dorama;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderXml implements Reader {
    @Override
    public Object readFile(String filename){
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentBuilder.parse(filename);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        return getDoramas(document);
    }
    private ArrayList<Dorama> getDoramas(Document document){
        ArrayList<Dorama> doramas = new ArrayList<>();
        Node root = document.getDocumentElement();
        NodeList doramaNodes = root.getChildNodes();
        for (int i = 0; i < doramaNodes.getLength(); i++) {
            Node doramaNode = doramaNodes.item(i);
            if (doramaNode.getNodeType() != Node.TEXT_NODE) {
                Dorama dorama = getDorama(doramaNode);
                doramas.add(dorama);
            }
        }
        return doramas;
    }
    private Dorama getDorama(Node doramaNode){
        Dorama dorama = new Dorama();
        NodeList doramaInfo = doramaNode.getChildNodes();
        for (int i = 0; i < doramaInfo.getLength(); i++){
            Node info = doramaInfo.item(i);
            if (info.getNodeType() != Node.TEXT_NODE){
                switchInfo(info, dorama);
            }
        }
        return dorama;
    }
    private void switchInfo(Node info, Dorama dorama){
        switch (info.getNodeName()){
            case "name" -> dorama.setName(info.getTextContent());
            case "date" -> dorama.setData(info.getTextContent());
            case "country" -> dorama.setCountry(info.getTextContent());
            case "genres" -> setGenresDorama(info, dorama);
        }
    }
    private void setGenresDorama(Node info, Dorama dorama){
        ArrayList<String> genres = new ArrayList<>();
        NodeList genresNode = info.getChildNodes();
        for (int i = 0; i < genresNode.getLength(); i++){
            Node genre = genresNode.item(i);
            if (genre.getNodeType() != Node.TEXT_NODE)
                genres.add(genre.getTextContent());
        }
        dorama.setGenres(genres);
    }
}
