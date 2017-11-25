package hackathon.artk;

/**
 * Created by thtuan on 11/25/17.
 * ------------------------------
 */

public class ParkingObject {
  private String name;
  private double lat;
  private double lng;
  private int filled;
  private int full;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLng() {
    return lng;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public int getFilled() {
    return filled;
  }

  public void setFilled(int filled) {
    this.filled = filled;
  }

  public int getFull() {
    return full;
  }

  public void setFull(int full) {
    this.full = full;
  }
}
