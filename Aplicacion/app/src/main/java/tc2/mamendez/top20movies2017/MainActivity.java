package tc2.mamendez.top20movies2017;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // URL Address
    String url = "http://www.imdb.com/list/ls064079588/";
    ProgressDialog mProgressDialog;
    List<String> Titulos = new ArrayList<>();
    List<String> Estrellas = new ArrayList<>();
    List<String> Metascores = new ArrayList<>();
    List<String> Portadas = new ArrayList<>();
    int cantidad_peliculas = 20;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MoviesDownloader().execute();
    }

    // Title AsyncTask
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
                // Connect to the web site
                Document document = Jsoup.connect(url).get();

                Elements titulos = document.select("div .lister-item-content h3 a");
                Elements estrellas = document.select("div .inline-block strong");
                Elements metascores = document.select("div .inline-block.ratings-metascore span");
                Elements portadas = document.select("div .lister-item-image img[src~=(?i)\\.(png|jpe?g|gif)]");
                for(int i=0; i<cantidad_peliculas; i++){
                    Titulos.add(titulos.get(i).text());
                    Estrellas.add(estrellas.get(i).text());
                    Metascores.add(metascores.get(i).text());
                    Portadas.add(portadas.get(i).attr("src"));
                }
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

//    ImageView iv = new ImageView(this.getApplicationContext());
//            iv.setImageResource(R.drawable.boxie_gris);
//    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
//    params.rowSpec = GridLayout.spec(r);
//    params.columnSpec = GridLayout.spec(c);
//            iv.setLayoutParams(params);
//            grid.addView(iv);



}
