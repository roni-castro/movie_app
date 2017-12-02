package android.example.com.moviesapp.activities;

import android.example.com.moviesapp.R;
import android.example.com.moviesapp.adapters.GridViewAdapter;
import android.example.com.moviesapp.model.Film;
import android.example.com.moviesapp.requests.FilmRequest;
import android.example.com.moviesapp.tools.ResponseError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<Film> filmArrayList;
    private GridViewAdapter filmAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filmArrayList = new ArrayList<>();
        // Grid View setup
        gridView = (GridView) findViewById(R.id.grid_view);
        filmAdapter = new GridViewAdapter(filmArrayList, this);
        gridView.setAdapter(filmAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fillFilmArrayList();
    }

    public void fillFilmArrayList(){
        FilmRequest filmRequest = new FilmRequest(this);
        filmRequest.getFilms("top_rated", new Response.Listener<ArrayList<Film>>() {
            @Override
            public void onResponse(ArrayList<Film> response) {
                filmArrayList.clear();
                filmArrayList.addAll(response);
                filmAdapter.notifyDataSetChanged();
            }
        }, new ResponseError() {
            @Override
            public void onExceptionResponse(String message, int statusCode) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
