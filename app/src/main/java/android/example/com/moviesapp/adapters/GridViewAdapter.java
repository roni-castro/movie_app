package android.example.com.moviesapp.adapters;

import android.content.Context;
import android.example.com.moviesapp.R;
import android.example.com.moviesapp.config.Urls;
import android.example.com.moviesapp.model.Film;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Cesar on 12/02/2017.
 */

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<Film> filmArrayList;
    private Context context;

    public GridViewAdapter(ArrayList<Film> filmList, Context context){
        this.filmArrayList = filmList;
        this.context = context;
    }

    @Override
    public int getCount(){
        return filmArrayList.size();
    }

    @Override
    public Object getItem(int position){
        return filmArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cell_film, parent, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.film_image_view);
        String imageUrl = makeImageUrl(filmArrayList.get(position).getImagePath());
        Picasso.with(context).load(imageUrl).into(imageView);

        return view;
    }

    private String makeImageUrl(String imagePath){
        return Urls.URL_GET_SPECIFIC_IMAGE_185_PX + imagePath;
    }
}
