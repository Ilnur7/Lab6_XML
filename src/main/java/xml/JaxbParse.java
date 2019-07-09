package xml;

import osmclass.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class JaxbParse {
    private String file;

    public String getFile() {
        return file;
    }

    public JaxbParse(String file) {
        this.file = file;
    }

    public TreeMap<String, StreetData> readStreet() {
        boolean isHighway;
        TreeMap<String,StreetData> streetMap = new TreeMap<>();
        String typeRoad = null;
        String nameStreet;

        try {
            Osm osm = createUnmarshaller();

            for (Object o : osm.getBoundOrUserOrPreferences()) {
                if (o instanceof Way) {
                    isHighway = false;
                    for (int i = 0; i < ((Way) o).getRest().size(); i++) {
                        Object tag = ((Way) o).getRest().get(i);
                        if (tag instanceof Tag){
                            Tag tagStreet = (Tag) tag;
                            if (tagStreet.getK().equals("highway")){
                                typeRoad = tagStreet.getV();
                                isHighway = true;
                            }
                            if (tagStreet.getK().equals("name") && isHighway){
                                nameStreet = tagStreet.getV();
                                StaxParse.addStreet(streetMap, typeRoad, nameStreet);
                            }
                        }

                    }
                }
            }
            streetMap.forEach((k, v) -> System.out.println(v));
            System.out.println(streetMap.size());
        }catch(JAXBException e){
            e.printStackTrace();
        }finally {
            return streetMap;
        }
    }

    public Osm createUnmarshaller() throws JAXBException {
        File fileName = new File(getFile());
        JAXBContext context = JAXBContext.newInstance(Osm.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Osm osm = (Osm) unmarshaller.unmarshal(fileName);
        return osm;
    }

    public HashSet<String> readBusStop() {

        HashSet<String> setBusStop = new HashSet<>();
        try {
            Osm osm = createUnmarshaller();

            for (Object o : osm.getBoundOrUserOrPreferences()) {
                if (o instanceof Node) {
                    boolean isBusStop = false;
                    List<Tag> tags = ((Node) o).getTag();
                    String name=null;
                    for (int i = 0; i < tags.size(); i++) {
                        Tag tag = tags.get(i);
                        String key = tag.getK();
                        if (key.equals("highway") &&
                                tag.getV().equals("bus_stop")) {
                            isBusStop = true;
                        }
                        if (key.equals("name") ) {
                           name=tag.getV();
                        }
                    }
                    if (isBusStop && name!=null) setBusStop.add( name );
                }
            }
            setBusStop.forEach(System.out::println);
            System.out.println(setBusStop.size());
        }catch(JAXBException e){
                e.printStackTrace();
        }finally {
            return setBusStop;
        }
    }
}