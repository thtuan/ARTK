package hackathon.artk.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import hackathon.artk.R;

public class ManagementActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_management);
    ImageView imageView = (ImageView) findViewById(R.id.imageView);
    imageView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ManagementActivity.this, StreamActivity.class);
        startActivity(intent);
      }
    });
  }
}
