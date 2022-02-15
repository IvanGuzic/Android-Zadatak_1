package guzic.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.InputStream;

public class DetaljiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);

        Intent intent = getIntent();
        Album album = (Album) intent.getSerializableExtra("Album");

        TextView strAlbum = findViewById(R.id.strAlbum);
        strAlbum.setText(String.valueOf(album.getStrAlbum()));

        ((TextView) findViewById(R.id.strGenre)).setText(String.valueOf(album.getStrGenre()));

        Button nazad = findViewById(R.id.nazad);
        nazad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();

        }});

        new DownloadImageTask(findViewById(R.id.strAlbumThumb)).execute(album.getStrAlbumThumb());


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {

            this.bmImage = bmImage;

        }

        protected Bitmap doInBackground(String... urls) {

            String urldisplay = urls[0];
            Bitmap mIcon11 = null;

            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            }
            catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;

        }

        protected void onPostExecute(Bitmap result) {

            bmImage.setImageBitmap(result);

}}}