package tc2.mamendez.top20movies2017;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    // URL Address
    String url = "http://www.imdb.com/list/ls064079588/";
    ProgressDialog mProgressDialog;
    static List<Pelicula> Peliculas = new ArrayList<>();
    int cantidad_peliculas = 20;
    private GridView gridView;
    private AdaptadorDePeliculas adaptador;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new MoviesDownloader().execute();

    }


    private class MoviesDownloader extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Top 20 Movies of 2017");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(url).get();

                Elements titulos = document.select("div .lister-item-content h3 a");
                Elements estrellas = document.select("div .inline-block strong");
                Elements metascores = document.select("div .inline-block.ratings-metascore span");
                Elements portadas = document.select("div .lister-item-image a img");
                for(int i=0; i<cantidad_peliculas; i++){
                    URL url = new URL(portadas.get(i).attr("loadlate"));
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Peliculas.add(new Pelicula(bitmap,//imageDownloader.execute(portadas.get(i).attr("src")).get(),
                            titulos.get(i).text(), estrellas.get(i).text(),metascores.get(i).text()));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        gridView = (GridView) findViewById(R.id.grid);
                        adaptador = new AdaptadorDePeliculas(getApplicationContext());
                        gridView.setAdapter(adaptador);

                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
        }
    }
}
