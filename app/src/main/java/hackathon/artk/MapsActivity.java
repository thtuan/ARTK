package hackathon.artk;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;
  private ModelParkings modelParkings;
  private LatLngObj latLngObj;
  private LatLngObj latLngObj2;
  LatLng sydney;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);
    modelParkings = new ModelParkings();
    latLngObj = new LatLngObj();
    latLngObj2 = new LatLngObj();
  }


  /**
   * Manipulates the map once available.
   * This callback is triggered when the map is ready to be used.
   * This is where we can add markers or lines, add listeners or move the camera. In this case,
   * we just add a marker near Sydney, Australia.
   * If Google Play services is not installed on the device, the user will be prompted to install
   * it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;


    // circle settings
    int radiusM = 150; // your radius in meters
    double latitude = -34;// your center latitude
    double longitude = 151;// your center longitude

    // draw circle
    int d = 10; // diameter
    Bitmap bm = Bitmap.createBitmap(d, d, Config.ARGB_8888);
    Canvas c = new Canvas(bm);
    Paint p = new Paint();
    p.setColor(getResources().getColor(android.R.color.holo_green_light));
    c.drawCircle(d/2, d/2, d/2, p);

    // generate BitmapDescriptor from circle Bitmap
    BitmapDescriptor bmD = BitmapDescriptorFactory.fromBitmap(bm);

    // Add a marker in Sydney and move the camera
//    LatLng sydney = new LatLng(-34, 151);
//    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in "
//        + "Sydney").icon(bmD));


    ArrayList<LatLngObj> arrLatLng = new ArrayList<>();
    latLngObj.setLat(10.7740504);
    latLngObj.setLng(106.6923693);
    latLngObj2.setLat(10.7767632);
    latLngObj2.setLng(106.693917);
    arrLatLng.add(latLngObj);
    arrLatLng.add(latLngObj2);
    for(int i = 0; i< arrLatLng.size();i++){
//      arrLatLng.add(latLngObj);
      modelParkings.setLatLng(arrLatLng);
      sydney = new LatLng(modelParkings.getLatLng().get(i).getLat(), modelParkings.getLatLng().get(i).getLng());
      mMap.addMarker(new MarkerOptions()
              .position(sydney)
              .title("LinkedIn")
              .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }


    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));

  }
}
