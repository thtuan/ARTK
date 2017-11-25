package hackathon.artk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huuth on 11/25/2017.
 */

public class ModelParkings {

  private List<ParkingObject> parking = new ArrayList<>();

  public ModelParkings() {
  }

  public List<ParkingObject> getParking() {
    return parking;
  }

  public void setParking(List<ParkingObject> parking) {
    this.parking = parking;
  }
}
