package xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DomParse {

    private String fileName;
    public DomParse(String fileName)   {
        this.fileName = fileName;
    }

    Document readXmlToDOMDocument() throws SAXException {
        File file = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false); // не поддерживать пространства имен
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return builder.parse(file); // собственно читаем документ - ВЕСЬ СРАЗУ В ПАМЯТЬ!
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void processDom()  {
        try {
            System.out.println("Считываемем документ");
            Document document = readXmlToDOMDocument();
            System.out.println("1. Окружности и прямоугольники ====================");
            viewRectCircle(document);
            System.out.println("\n2. Перекрасить прямоугольники и кривые : ====================");
            updateRectCircle(document);
            System.out.println("\n3. Добавить круги : ====================");
            addCircle(document);
            System.out.println("\n4. Сохраняем под другим именем =====================================");
            saveDocument(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCircle(Document document) {
        NodeList circleNodeList = document.getElementsByTagName("circle");
        int length = circleNodeList.getLength();
        for (int i = 0; i < length; i++) {
            if (circleNodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
                Element circle = (Element) circleNodeList.item(i*2);
                Element newCircle = document.createElement("circle");
                newCircle.setAttribute("cx",circle.getAttribute("cx"));
                newCircle.setAttribute("cy",circle.getAttribute("cy"));
                newCircle.setAttribute("r", "10");
                newCircle.setAttribute("stroke", "red");
                newCircle.setAttribute("stroke-width", "1");
                newCircle.setAttribute("fill", "cyan");
                Node node = circle.getParentNode();
                node.insertBefore(newCircle, circle.getNextSibling());
            }
        }
        saveDocument(document);
    }

    public void updateRectCircle(Document document) {
        NodeList rectNodeList = document.getElementsByTagName("rect");
        NodeList pathNodeList = document.getElementsByTagName("path");
        changeColorElements(rectNodeList);
        changeColorElements(pathNodeList);
        saveDocument(document);
    }

    public void viewRectCircle(Document document){
        NodeList rectNodeList = document.getElementsByTagName("rect");
        NodeList circleNodeList = document.getElementsByTagName("circle");
        printElements(rectNodeList);
        printElements(circleNodeList);
    }

    public void printElements(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                Element rectElement = (Element) nodeList.item(i);
                String name = rectElement.getTagName();
                if (name.equals("rect")){
                    double width = Double.parseDouble(rectElement.getAttribute("width"));
                    double height = Double.parseDouble(rectElement.getAttribute("height"));
                    System.out.println(i + " - " + name + " width=" + width + " height=" + height);
                }else if (name.equals("circle")){
                    double cx = Double.parseDouble(rectElement.getAttribute("cx"));
                    double cy = Double.parseDouble(rectElement.getAttribute("cy"));
                    double r = Double.parseDouble(rectElement.getAttribute("r"));
                    System.out.println(i + " - " + name + " cx=" + cx + " cy=" + cy + " r=" + r);
                }else {
                    System.out.println(i + " - " + name);
                }
                /*if (nodeList.item(i).hasChildNodes()) {
                    printElements(nodeList.item(i).getChildNodes());
                }*/
            }
        }
    }

    public void changeColorElements(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nodeList.item(i);
                if (element.hasAttribute("style")){
                    if (element.getTagName().equals("path")){
                        element.setAttribute("style", "fill: rgb(255, 105, 180);");
                    }else if (element.getTagName().equals("rect")){
                        element.setAttribute("style", "fill: rgb(0, 100, 100);");
                    }
                }
            }
        }
    }

    public void saveDocument(Document document) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // отступы
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            DOMSource source = new DOMSource(document);
            //StreamResult result = new StreamResult(new File(fileName));
            StreamResult result = new StreamResult(new File("updateClouds.svg"));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
            System.out.println("Ошибка, сохранение не произошло");
        }
        System.out.println("Изменения сохранены");
    }


    /*public static void removeWhitespaceNodes(Element e) {
        NodeList children = e.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child instanceof Text && ((Text) child).getData().trim().length() == 0) {
                e.removeChild(child);
            }
            else if (child instanceof Element) {
                removeWhitespaceNodes((Element) child);
            }
        }
    }*/
}
