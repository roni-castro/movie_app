package android.example.com.moviesapp.tools;

/**
 * Created by Cesar on 12/02/2017.
 */

public interface ResponseError {
    void onExceptionResponse(String message, int statusCode);
}
