package javabasic.miniproject.model;

public class ScreenInfo {
    private int screenInfoId;
    private int movieId;
    private int theaterId;
    private String runningTime;

    public int getScreenInpoId() {
        return screenInfoId;
    }

    public void setScreenInpoId(int screenInfoId) {
        this.screenInfoId = screenInfoId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public boolean equals(Object o) {
        if (o instanceof ScreenInfo) {
            ScreenInfo b = (ScreenInfo) o;
            return screenInfoId == b.screenInfoId;
        }
        return false;
    }

    public ScreenInfo(ScreenInfo origin) {
        screenInfoId = origin.screenInfoId;
        movieId = origin.movieId;
        theaterId = origin.theaterId;
        runningTime  = origin.runningTime;
    }

    public ScreenInfo() {

    }

    public ScreenInfo(int id) {
        this.screenInfoId = id;
    }
}
