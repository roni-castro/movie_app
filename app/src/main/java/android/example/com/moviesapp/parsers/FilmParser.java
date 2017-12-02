package android.example.com.moviesapp.parsers;

import android.example.com.moviesapp.model.Film;
import android.example.com.moviesapp.tools.MoviesApp;

import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Cesar on 12/02/2017.
 */

public class FilmParser {

    public Film parseToFilm(JSONObject jsonObject) {
        Film film = new Film();
        try {
            if (jsonObject.has("poster_path")) {
                film.setImagePath(MoviesApp.safeString(jsonObject.get("poster_path")));
            }
            if (jsonObject.has("adult")) {
                film.setAdult(MoviesApp.safeBoolean(jsonObject.get("adult")));
            }
            if (jsonObject.has("overview")) {
                film.setOverview(MoviesApp.safeString(jsonObject.get("overview")));
            }
            if (jsonObject.has("release_date")) {
                film.setReleaseDate(MoviesApp.safeString(jsonObject.get("release_date")));
            }
            if (jsonObject.has("genre_ids")) {
                JSONArray jsonArray = jsonObject.getJSONArray("genre_ids");
                for (int i = 0; i < jsonArray.length(); i++) {
                    film.getGenreIds().add(MoviesApp.safeInteger(jsonArray.get(i)));
                }
            }
            if (jsonObject.has("id")) {
                film.setId(MoviesApp.safeInteger(jsonObject.get("id")));
            }
            if (jsonObject.has("original_title")) {
                film.setOriginalTitle(MoviesApp.safeString(jsonObject.get("original_title")));
            }
            if (jsonObject.has("original_language")) {
                film.setOriginalLanguage(MoviesApp.safeString(jsonObject.get("original_language")));
            }
            if (jsonObject.has("title")) {
                film.setTitle(MoviesApp.safeString(jsonObject.get("title")));
            }
            if (jsonObject.has("popularity")) {
                film.setPopularity(MoviesApp.safeDouble(jsonObject.get("popularity")));
            }
            if (jsonObject.has("vote_count")) {
                film.setVoteCount(MoviesApp.safeInteger(jsonObject.get("vote_count")));
            }
            if (jsonObject.has("video")) {
                film.setHasVideo(MoviesApp.safeBoolean(jsonObject.get("video")));
            }
            if (jsonObject.has("vote_average")) {
                film.setVoteAverage(MoviesApp.safeDouble(jsonObject.get("vote_average")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return film;
    }

}
