package org.peace.allinone.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import org.peace.allinone.R;

public class MainActivity extends AppCompatActivity {

  static final String ABNORMAL_URL = "http://fuss10.elemecdn.com/5/d8/691827c35ccd3c41d0f11faee4d54jpeg.jpeg?imageView2/0/w/200/h/200/format/webp";
  static final String NORMAL_URL = "http://fuss10.elemecdn.com/2/30/dc48e9ee54ad211f952ff5be00268jpeg.jpeg?imageView2/0/w/200/h/200/format/webp";

  @InjectView(R.id.abnormal) ImageView abnormalView;
  @InjectView(R.id.normal) ImageView normalView;

  OkHttpClient okHttpClient = new OkHttpClient();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);

    loadImage(abnormalView, ABNORMAL_URL);
    loadImage(normalView, NORMAL_URL);
  }

  private void loadImage(final ImageView imageView, String url) {
    Request request = new Request.Builder().url(url).build();
    okHttpClient.newCall(request).enqueue(new Callback() {
      @Override public void onFailure(Request request, IOException e) {
        e.printStackTrace();
        runOnUiThread(new Runnable() {
          @Override public void run() {
            Toast.makeText(MainActivity.this, "Request Error, check log please", Toast.LENGTH_SHORT).show();
          }
        });
      }

      @Override public void onResponse(Response response) throws IOException {
        InputStream is = response.body().byteStream();
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        runOnUiThread(new Runnable() {
          @Override public void run() {
            imageView.setImageBitmap(bitmap);
          }
        });
      }
    });
  }
}
