package android.example.com.moviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Cesar on 12/02/2017.
 */

public class Film implements Parcelable {
    // JSON id
    private int id;
    // JSON poster_path
    private String imagePath;
    // JSON adult
    private boolean isAdult;
    // JSON overview
    private String overview;
    // JSON release_date
    private String releaseDate;
    // JSON original_title
    private String originalTitle;
    // JSON original_language
    private String originalLanguage;
    // JSON title
    private String title;
    // JSON popularity
    private double popularity;
    // JSON vote_count
    private int voteCount;
    // JSON video
    private boolean hasVideo;
    // JSON vote_average
    private double voteAverage;
    // JSON genre_ids
    private ArrayList<Integer> genreIds;

    public Film(){
        id = -1;
        imagePath="";
        isAdult = false;
        overview = "";
        releaseDate = "";
        originalTitle = "";
        originalLanguage = "";
        title = "";
        popularity = 0.000000;
        voteCount = 0;
        hasVideo = false;
        voteAverage = 0.0f;
        genreIds = new ArrayList<>();
    }

    private Film(Parcel in){
        id = in.readInt();
        imagePath= in.readString();
        isAdult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
        originalTitle = in.readString();
        originalLanguage = in.readString();
        title = in.readString();
        popularity = in.readDouble();
        voteCount = in.readInt();
        hasVideo = in.readByte() != 0;
        voteAverage = in.readDouble();
        if(genreIds == null) {
            genreIds = new ArrayList<>();
        }
        in.readList(genreIds, Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>(){

        @Override
        public Film createFromParcel(Parcel parcel) {
            return new Film(parcel);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imagePath);
        dest.writeByte((byte) (isAdult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(originalLanguage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeInt(voteCount);
        dest.writeByte((byte) (hasVideo ? 1 : 0));
        dest.writeDouble(voteAverage);
        dest.writeList(genreIds);
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public ArrayList<Integer> getGenreIds() {
        return genreIds;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setGenreIds(ArrayList<Integer> genreIds) {
        this.genreIds = genreIds;
    }
}
