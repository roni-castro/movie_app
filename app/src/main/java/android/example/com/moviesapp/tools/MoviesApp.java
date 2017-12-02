package android.example.com.moviesapp.tools;

import android.app.Application;
import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Cesar on 12/02/2017.
 */

public class MoviesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String safeString(Object object) {
        String s = (object instanceof String) ? (String)object : "";
        return s;
    }

    public static Boolean safeBoolean(Object object) {
        Boolean bool = (object instanceof Boolean) ? (Boolean)object : false;
        return bool;
    }

    public static Double safeDouble(Object object) {
        Double doubleNumber = (object instanceof Double) ? (Double) object : 0.0;
        return doubleNumber;
    }

    public static Integer safeInteger(Object object) {
        Integer integerNumber = (object instanceof Integer) ? (Integer) object : 0;
        return integerNumber;
    }

    public static int retrieveErrorStatusCode(VolleyError error) {
        int statusCode = -1;
        try {
            statusCode = error.networkResponse.statusCode;
        } catch (Exception e) {

        }
        return statusCode;
    }

    public static String retrieveErrorMessage(VolleyError error) {
        JSONObject errorObject;

        try {
            errorObject = new JSONObject(new String(error.networkResponse.data));

            if(errorObject.get("error").getClass() != String.class) {
                errorObject = errorObject.getJSONObject("error");
            }

            // retrieve the error message from JSONObject
            String message;
            Iterator iterator = errorObject.keys();
            String key = (String) iterator.next();

            // remove unwanted char
            message = errorObject.getString(key);
            message = message.replace("[", "");
            message = message.replace("]", "");
            message = message.replace("\"", "");

            return message;

        } catch (Exception e) {
            return "An error occurred, please try again";
        }
    }
}
