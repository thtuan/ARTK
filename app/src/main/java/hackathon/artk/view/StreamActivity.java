package hackathon.artk.view;

import android.Manifest;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import com.opentok.android.BaseVideoRenderer;
import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;
import com.opentok.android.SubscriberKit;
import hackathon.artk.R;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class StreamActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks,
    Session.SessionListener,
    PublisherKit.PublisherListener,
    SubscriberKit.SubscriberListener {
  private static final int RC_VIDEO_APP_PERM = 124;
  private static final String LOG_TAG = StreamActivity.class.getSimpleName();
  private Session mSession;
  private Publisher mPublisher;
  private Subscriber mSubscriber;

  private FrameLayout mPublisherViewContainer;
  private FrameLayout mSubscriberViewContainer;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_stream);
    requestPermissions();

  }


  @Override
  protected void onPause() {

    Log.d(LOG_TAG, "onPause");

    super.onPause();

    if (mSession != null) {
      mSession.onPause();
    }

  }

  @Override
  protected void onResume() {

    Log.d(LOG_TAG, "onResume");

    super.onResume();

    if (mSession != null) {
      mSession.onResume();
    }
  }
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @AfterPermissionGranted(RC_VIDEO_APP_PERM)
  private void requestPermissions() {
    String[] perms = { Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO };
    if (EasyPermissions.hasPermissions(this, perms)) {
      // initialize view objects from your layout
      mSubscriberViewContainer = (FrameLayout)findViewById(R.id.subscriber_container);
      mSession = new Session.Builder(this, "46007972", "1_MX40NjAwNzk3Mn5-MTUxMTY1OTY3Njk2MX5sTHZSWmZaSytpWlhDOHE2RnJLMHllMGd-fg").build();
      mSession.setSessionListener(this);
      mSession.connect("T1==cGFydG5lcl9pZD00NjAwNzk3MiZzaWc9OWY2OWY5Mzk3ZjBjNGY0YTZkMGFlZDkzNmQ5ZWNhYTA4MTE3NzgyYjpzZXNzaW9uX2lkPTFfTVg0ME5qQXdOemszTW41LU1UVXhNVFkxT1RZM05qazJNWDVzVEhaU1dtWmFTeXRwV2xoRE9IRTJSbkpMTUhsbE1HZC1mZyZjcmVhdGVfdGltZT0xNTExNjU5NzE3Jm5vbmNlPTAuNTY0OTMzNTA5OTE2MDg1MyZyb2xlPXN1YnNjcmliZXImZXhwaXJlX3RpbWU9MTUxNDI1MTcxNiZpbml0aWFsX2xheW91dF9jbGFzc19saXN0PQ==");

      // initialize and connect to the session


    } else {
      EasyPermissions.requestPermissions(this, "This app needs access to your camera and mic to make video calls", RC_VIDEO_APP_PERM, perms);
    }
  }

  @Override
  public void onConnected(Session session)
  {
    Log.i(LOG_TAG, "Session Connected");
    Log.d(LOG_TAG, "onConnected: Connected to session: "+session.getSessionId());

    // initialize Publisher and set this object to listen to Publisher events
    mPublisher = new Publisher.Builder(this).build();
    mPublisher.setPublisherListener(this);

    // set publisher video style to fill view
    mPublisher.getRenderer().setStyle(BaseVideoRenderer.STYLE_VIDEO_SCALE,
        BaseVideoRenderer.STYLE_VIDEO_FILL);
    mPublisherViewContainer.addView(mPublisher.getView());
    if (mPublisher.getView() instanceof GLSurfaceView) {
      ((GLSurfaceView) mPublisher.getView()).setZOrderOnTop(true);
    }

    mSession.publish(mPublisher);
  }

  @Override
  public void onDisconnected(Session session) {
    Log.i(LOG_TAG, "Session Disconnected");
  }

  @Override
  public void onStreamReceived(Session session, Stream stream) {
    if (mSubscriber == null) {
      mSubscriber = new Subscriber.Builder(this, stream).build();
      mSession.subscribe(mSubscriber);
      mSubscriberViewContainer.addView(mSubscriber.getView());
    }
    Log.i(LOG_TAG, "Stream Received");
  }

  @Override
  public void onStreamDropped(Session session, Stream stream) {
    Log.i(LOG_TAG, "Stream Dropped");
    if (mSubscriber != null) {
      mSubscriber = null;
      mSubscriberViewContainer.removeAllViews();
    }
  }

  @Override
  public void onError(Session session, OpentokError opentokError) {
    Log.e(LOG_TAG, "Session error: " + opentokError.getMessage());
  }

  @Override
  public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

  }

  @Override
  public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

  }

  @Override
  public void onError(PublisherKit publisherKit, OpentokError opentokError) {

  }

  @Override
  public void onConnected(SubscriberKit subscriberKit) {

  }

  @Override
  public void onDisconnected(SubscriberKit subscriberKit) {

  }

  @Override
  public void onError(SubscriberKit subscriberKit, OpentokError opentokError) {

  }

  @Override
  public void onPermissionsGranted(int requestCode, List<String> perms) {

  }

  @Override
  public void onPermissionsDenied(int requestCode, List<String> perms) {

  }
}