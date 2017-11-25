package hackathon.artk;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by thtuan on 11/25/17.
 * ------------------------------
 */

public class ARTKApplication  extends Application{

  @Override
  public void onCreate() {
    super.onCreate();
    FirebaseApp.initializeApp(this);
  }
}
