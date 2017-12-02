package android.example.com.moviesapp.requests;

import android.content.Context;
import android.content.SharedPreferences;
import android.example.com.moviesapp.config.Constants;
import android.example.com.moviesapp.config.Urls;
import android.example.com.moviesapp.model.Film;
import android.example.com.moviesapp.parsers.FilmParser;
import android.example.com.moviesapp.tools.MoviesApp;
import android.example.com.moviesapp.tools.ResponseError;
import android.example.com.moviesapp.tools.VolleyManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.squareup.picasso.Downloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cesar on 12/02/2017.
 */

public class FilmRequest {
    private Context context;

    public FilmRequest(Context context){
        this.context = context;
    }

    public void getFilms(String movieEndPoint, final Response.Listener<ArrayList<Film>> result, final ResponseError responseError) {

        String path = Urls.URL_MOVIE_BASE + movieEndPoint + "/?" + "api_key=" + "6f39cb0b90a8f73c852e182e43c1bbca";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray resultsJsonArray = response.getJSONArray("results");
                    ArrayList<Film> filmArrayList = new ArrayList<>();
                    FilmParser filmParser = new FilmParser();
                    for (int i = 0; i < resultsJsonArray.length(); i++) {
                        Film film = filmParser.parseToFilm(resultsJsonArray.getJSONObject(i));
                        filmArrayList.add(film);
                    }
                    result.onResponse(filmArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String message = MoviesApp.retrieveErrorMessage(error);
                int statusCode = MoviesApp.retrieveErrorStatusCode(error);
                responseError.onExceptionResponse(message, statusCode);
                error.printStackTrace();
            }
        });
        VolleyManager.getInstance(context).addToRequestQueue(jsonObjectRequest, Constants.GET_FILMS_ID_TAG);
    }
}
