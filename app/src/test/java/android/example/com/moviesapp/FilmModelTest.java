package android.example.com.moviesapp;

import android.example.com.moviesapp.model.Film;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by Cesar on 15/02/2017.
 */

public class FilmModelTest {
    private Film film;

    @Before // Run for every @Test
    public void createFilmInstance() {
        film = new Film();
    }

    @Test
    public void checkCorrectDefaultValues() {
        // Check the default values
        assertEquals(film.getId(), -1);
        assertEquals(film.isHasVideo(), false);
        assertEquals(film.isAdult(), false);
        assertEquals(film.getImagePath(), "");
        assertEquals(film.getOriginalLanguage(), "");
        assertEquals(film.getOriginalTitle(), "");
        assertEquals(film.getOverview(), "");
        assertEquals(film.getReleaseDate(), "");
        assertEquals(film.getTitle(), "");
        assertEquals(film.getVoteCount(), 0);
    }

    @Test
    public void testNullValues() {
        assertNotNull(film.getGenreIds());
        assertNotNull(film.getId());
        assertNotNull(film.getImagePath());
        assertNotNull(film.getOriginalLanguage());
        assertNotNull(film.getOriginalTitle());
        assertNotNull(film.getOverview());
        assertNotNull(film.getPopularity());
        assertNotNull(film.getReleaseDate());
        assertNotNull(film.getTitle());
        assertNotNull(film.getVoteAverage());
        assertNotNull(film.getVoteCount());
    }
    @Test
    public void testDefaultNumericValues() throws InvalidDefaultValue{
        checkInvalidDefaultNumericValue();
    }

    @After
    public void clearVariable(){
        film = null;
    }

    private void checkInvalidDefaultNumericValue() throws InvalidDefaultValue{
        if(film.getVoteCount() > 0 || film.getPopularity() > 0 || film.getVoteAverage() > 0){
            throw new InvalidDefaultValue();
        }
    }

    private class InvalidDefaultValue extends Exception{
        InvalidDefaultValue() {
            super();
        }
    }
}
