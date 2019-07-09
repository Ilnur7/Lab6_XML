package xml;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StaxParse {
    private String file;

    public String getFile() {
        return file;
    }

    public StaxParse(String file)   {
        this.file = file;
    }


    /*public void processStax() {
        schemaCheckerTest();
        readBusStop(file);
        readGroupStreet(file);
    }*/

    public HashSet<String> readBusStop() {
        boolean isNode = false;
        boolean isBusStop = false;
        boolean isReady = false;
        String nameStreet = null;
        HashSet<String> setBusStop = new HashSet<>();

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader eReader = xmlInputFactory.createXMLEventReader(new FileReader(getFile())); // инициализируем reader и передаем ему xml файл

            while (eReader.hasNext()) {
                //eReader.peek() - подсмотреть следущее событие без перехода на него
                XMLEvent xmlEvent = eReader.nextEvent();// получаем событие (элемент) и разбираем его
                switch(xmlEvent.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement = xmlEvent.asStartElement();

                        Attribute attributeKey = startElement.getAttributeByName(new QName("k"));
                        Attribute attributeValue = startElement.getAttributeByName(new QName("v"));
                        if (startElement.getName().getLocalPart().equals("node")){
                            isNode = true;
                            isBusStop = false;
                        }
                        if (attributeKey != null && attributeKey.getValue().equals("highway") &&
                                attributeValue != null && attributeValue.getValue().equals("bus_stop") && isNode){
                            isBusStop = true;
                        }
                        if (attributeKey != null && isNode && attributeKey.getValue().equals("name")){
                            nameStreet = attributeValue.getValue();
                            isReady = true;
                        }
                        break;

                    case XMLEvent.END_ELEMENT:
                        EndElement endElement = xmlEvent.asEndElement();
                        if (isReady && isBusStop && endElement.getName().getLocalPart().equals("node")){
                            setBusStop.add(nameStreet);
                            isNode = false;
                            isBusStop = false;
                            isReady =false;
                        }
                        break;
                }
            }
            setBusStop.forEach(System.out::println);
            System.out.println(setBusStop.size());
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
            return setBusStop;


    }

    public TreeMap<String, StreetData> readGroupStreet() {

        boolean isWay = false;
        boolean isHighway = false;
        boolean isReady = false;
        TreeMap<String,StreetData> mapStreet = new TreeMap<>();
        String typeRoad = null;
        String nameStreet = null;

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader eReader = xmlInputFactory.createXMLEventReader(new FileReader(getFile()));

            while (eReader.hasNext()) {
                XMLEvent xmlEvent = eReader.nextEvent();
                switch(xmlEvent.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement = xmlEvent.asStartElement();
                        Attribute attributeKey = startElement.getAttributeByName(new QName("k"));
                        Attribute attributeValue = startElement.getAttributeByName(new QName("v"));
                        if (startElement.getName().getLocalPart().equals("way")){
                            isWay = true;
                            isHighway = false;
                            isReady = false;
                            typeRoad = null;
                        }
                        if (attributeKey != null && attributeKey.getValue().equals("highway") && isWay){
                            isHighway = true;
                            typeRoad = attributeValue.getValue();
                        }
                        if (attributeKey != null && attributeKey.getValue().equals("name") && isWay ){
                            nameStreet = attributeValue.getValue();
                            isReady = true;
                        }
                        break;

                    case XMLEvent.END_ELEMENT:
                        EndElement endElement = xmlEvent.asEndElement();
                        if (isReady && isHighway && endElement.getName().getLocalPart().equals("way") ){
                            addStreet(mapStreet, typeRoad, nameStreet);
                            isWay = false;
                            isHighway = false;
                            isReady = false;
                            typeRoad = null;
                        }
                        break;
                }
            }
            mapStreet.forEach((k, v) -> System.out.println(v));
            System.out.println(mapStreet.size());
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
        return mapStreet;

    }

    public static void addStreet(TreeMap<String, StreetData> mapStreet, String typeRoad, String nameStreet) {
        StreetData streetData=mapStreet.get(nameStreet);

        if (mapStreet.containsKey(nameStreet)){
            streetData.incrementCountPartsStreet(mapStreet, nameStreet);
        }else{
            streetData= new StreetData();
            streetData.setNameStreet(nameStreet);
            mapStreet.put(nameStreet, streetData);
        }
        streetData.getSetTypesRoad().add(typeRoad);
    }



    // простая проверка схемы
    void schemaCheckerTest() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("osm.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("UfaCenter.xml")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
