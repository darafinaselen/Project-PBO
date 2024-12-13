public class WorkoutVideoList {
    private String title;
    private int duration;
    private int calories;
    private String imagePath;
    private String videoPath;

    public WorkoutVideoList(String title, int duration, String imagePath, String videoPath) {
        this.title = title;
        this.duration = duration;
        this.calories = (duration / 25) * 5;
        this.imagePath = imagePath;
        this.videoPath = videoPath;
    }

    public WorkoutVideoList(String title, int duration, String imagePath) {
        this.title = title;
        this.duration = duration;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getCalories() {
        return calories;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getVideoPath() {
        return videoPath;
    }
}
