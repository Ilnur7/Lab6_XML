package xml;

import java.util.HashSet;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        new DomParse("clouds.svg").processDom();

        /*StaxParse staxParse = new StaxParse("center.xml");
        staxParse.schemaCheckerTest();
        HashSet<String> setBusStopStax = staxParse.readBusStop();
        TreeMap<String, StreetData> mapStreetStax = staxParse.readGroupStreet();

        JaxbParse jaxbParse = new JaxbParse("center.xml");
        HashSet<String> setBusStopJaxb = jaxbParse.readBusStop();
        TreeMap<String, StreetData> mapStreetJaxb = jaxbParse.readStreet();
        System.out.println(setBusStopStax.equals(setBusStopJaxb));
        System.out.println(mapStreetStax.size() == mapStreetJaxb.size());*/
    }


}

