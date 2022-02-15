package guzic.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListe.ItemClickInterface {

    private RecyclerView recyclerView;
    private static final String ALBUMS = "albums";
    private AdapterListe adapterListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterListe = new AdapterListe(this);
        adapterListe.setItemClickInterface(this);
        recyclerView.setAdapter(adapterListe);

        String url = getString(R.string.REST_API); //+ ALBUMS + "?" + getString(R.string.REST_API_KEY); + getString(R.string.REST_API_VALUE)
        Log.d("URL", url);
        RESTTask task = new RESTTask();
        task.execute(url);

    }

    private class RESTTask extends AsyncTask<String, Void, List<Album>> {

        @Override
        protected List<Album> doInBackground(String... strings) {

            String adresa = strings[0];

            try {
                URL url = new URL(adresa);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
//              httpURLConnection.setRequestProperty("x-rapidapi-key", "e86e45f5a2mshb6e4fa9e6a43bedp1fcbe8jsn9ad79d164749"); // dodano
                httpURLConnection.setRequestProperty(getString(R.string.REST_API_KEY), getString(R.string.REST_API_VALUE)); // dodano
                httpURLConnection.setReadTimeout(15000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.connect();

                InputStreamReader inputStreamReader = new InputStreamReader((httpURLConnection.getInputStream()));
                BufferedReader bufferedReader = new BufferedReader((inputStreamReader));
                Odgovor odgovor = new Gson().fromJson(bufferedReader, Odgovor.class);
                bufferedReader.close();
                inputStreamReader.close();

/*                if(odgovor.isGreska()) {
                    return null;
                  }*/

                return odgovor.getAlbum();
            }
            catch (MalformedURLException e) {
                Log.e("Problem adresa", e.getMessage());
            }
            catch(IOException e) {
                Log.e("Problem pristupa", e.getMessage());
            }

            return null;

        }

        @Override
        protected void onPostExecute(List<Album> albums) {

            adapterListe.setAlbums(albums);
            adapterListe.notifyDataSetChanged();

    }}

    @Override
    public void onItemClick(Album album) {

        Intent intent = new Intent(this, DetaljiActivity.class);
        intent.putExtra("Album", album);
        startActivity(intent);

}}