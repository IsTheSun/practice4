package service;

import service.converters.XmlToJson;
import service.converters.JsonToXml;

public class Main {
    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("Нужно указать 2 файла с расширениями!");
        }
        if (args[0].endsWith(".xml") && args[1].endsWith(".json")){
            XmlToJson converter = new XmlToJson();
            converter.convert(args[0], args[1]);
        }
        if (args[0].endsWith(".json") && args[1].endsWith(".xml")){
            JsonToXml converter = new JsonToXml();
            converter.convert(args[0], args[1]);
        }
    }
}