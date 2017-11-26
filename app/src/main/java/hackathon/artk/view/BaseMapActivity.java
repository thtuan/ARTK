package hackathon.artk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import hackathon.artk.R;

/**
 * Created by thtuan on 11/25/17.
 * ------------------------------
 */

public abstract class BaseMapActivity extends FragmentActivity implements OnMapReadyCallback,
    OnNavigationItemSelectedListener {

  private GoogleMap mMap;
  private DrawerLayout drawerLayout;
  NavigationView navigationView;

  protected int getLayoutId() {
    return R.layout.activity_maps;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    setUpMap();

  }

  @Override
  protected void onResume() {
    super.onResume();
    setUpMap();
  }

  @Override
  public void onMapReady(GoogleMap map) {
    if (mMap != null) {
      return;
    }
    mMap = map;
    startDemo();
  }

  private void setUpMap() {
    ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
  }

  /**
   * Run the demo-specific code.
   */
  protected abstract void startDemo();

  protected GoogleMap getMap() {
    return mMap;
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.location) {

      // Handle the camera action
    } else if (id == R.id.charge) {
      Intent intent = new Intent(this, DecoderActivity.class);
      startActivity(intent);
    } else if (id == R.id.management) {
      Intent intent = new Intent(this, StreamActivity.class);
      startActivity(intent);
    }

    drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }
}
