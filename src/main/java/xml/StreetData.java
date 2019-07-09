package xml;

import java.util.HashSet;
import java.util.TreeMap;

public class StreetData {

    private String nameStreet;
    private int countPartsStreet = 1;
    private HashSet<String> setTypesRoad = new HashSet<>();

    @Override
    public String toString() {
        return "StreetData{" +
                "nameStreet='" + nameStreet + '\'' +
                ", countPartsStreet=" + countPartsStreet +
                ", setTypesRoad=" + setTypesRoad +
                '}';
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    public int getCountPartsStreet() {
        return countPartsStreet;
    }

    public void setCountPartsStreet(int countPartsStreet) {
        this.countPartsStreet = countPartsStreet;
    }

    public HashSet<String> getSetTypesRoad() {
        return setTypesRoad;
    }

    public void addTypesRoad(String typeRoad) {
        setTypesRoad.add(typeRoad);
    }

    public void incrementCountPartsStreet(TreeMap<String, StreetData> mapStreet, String nameStreet){
        countPartsStreet++;
    }





}
