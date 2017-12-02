package android.example.com.moviesapp.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import static android.content.ContentValues.TAG;

/**
 * Created by Cesar on 12/02/2017.
 */

public class VolleyManager {
    private static VolleyManager instance;
    private Context context;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private VolleyManager(Context context){
        this.context = context;
        requestQueue = getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized VolleyManager getInstance(Context context){
        if(instance == null){
            instance = new VolleyManager(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Adiciona uma requisição na Request Queue com a tag designada. Usa a tag default se não for definida.
     * @param req Requisição a ser adicionada na fila.
     * @param tag Chave da requisição.
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    /**
     * Cancela todas as requisições com a tag designada.
     * @param tag Chave das requisições.
     */
    public void cancelPendingRequests(Object tag, ProgressBar progressBar) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
        progressBar.setVisibility(View.GONE);
    }
}
