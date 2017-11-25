package hackathon.artk;

import java.util.ArrayList;

/**
 * Created by huuth on 11/25/2017.
 */

public class ModelParkings {
    String name;
    ArrayList<LatLngObj> latLng;
    String space;
    String full;

    public ModelParkings() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LatLngObj> getLatLng() {
        return latLng;
    }

    public void setLatLng(ArrayList<LatLngObj> latLng) {
        this.latLng = latLng;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }
}
