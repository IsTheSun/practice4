package service.readers;

import service.structure.Dorama;
import service.structure.Character;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import lombok.val;

public class ReaderXml{
    public Object readFile(String filename){
        try {
            val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            val document = documentBuilder.parse(filename);
            return getDoramas(document);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Dorama> getDoramas(Document document){
        List<Dorama> doramas = new ArrayList<>();
        val root = document.getDocumentElement();
        val doramaNodes = root.getChildNodes();
        for (int i = 0; i < doramaNodes.getLength(); i++) {
            val doramaNode = doramaNodes.item(i);
            if (doramaNode.getNodeType() != Node.TEXT_NODE) {
                val dorama = getDorama(doramaNode);
                doramas.add(dorama);
            }
        }
        return doramas;
    }
    private Dorama getDorama(Node doramaNode){
        val dorama = new Dorama();
        val doramaInfo = doramaNode.getChildNodes();
        for (int i = 0; i < doramaInfo.getLength(); i++){
            val info = doramaInfo.item(i);
            if (info.getNodeType() != Node.TEXT_NODE){
                switchInfo(info, dorama);
            }
        }
        return dorama;
    }
    private void switchInfo(Node info, Dorama dorama){
        switch (info.getNodeName()){
            case "name" -> dorama.setName(info.getTextContent());
            case "date" -> dorama.setDate(info.getTextContent());
            case "country" -> dorama.setCountry(info.getTextContent());
            case "genres" -> setGenresDorama(info, dorama);
            case "characters" -> setCharacters(info, dorama);
        }
    }
    private void setGenresDorama(Node info, Dorama dorama){
        List<String> genres = new ArrayList<>();
        val genresNode = info.getChildNodes();
        for (int i = 0; i < genresNode.getLength(); i++){
            val genre = genresNode.item(i);
            if (genre.getNodeType() != Node.TEXT_NODE)
                genres.add(genre.getTextContent());
        }
        dorama.setGenres(genres);
    }
    private void setCharacters(Node info, Dorama dorama) {
        List<Character> characters = new ArrayList<>();
        val charactersNode = info.getChildNodes();
        for (int i = 0; i < charactersNode.getLength(); i++) {
            val characterNode = charactersNode.item(i);
            if (characterNode.getNodeType() != Node.TEXT_NODE) {
                characters.add(getCharacter(characterNode));
            }
        }
        dorama.setCharacters(characters);
    }
    private Character getCharacter(Node characterNode) {
        val character = new Character();
        val characterInfo = characterNode.getChildNodes();
        for (int i = 0; i < characterInfo.getLength(); i++) {
            val info = characterInfo.item(i);
            if (info.getNodeType() != Node.TEXT_NODE) {
                switchCharacterInfo(info, character);
            }
        }
        return character;
    }

    private void switchCharacterInfo(Node info, Character character) {
        val nodeName = info.getNodeName();
        switch (nodeName) {
            case "name" -> character.setName(info.getTextContent());
            case "age" -> character.setAge(Integer.parseInt(info.getTextContent()));
        }
    }
}
