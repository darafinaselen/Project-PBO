public class Workout {
    private String title;
    private String duration;
    private int calories;
    private String imagePath;

    public Workout(String title, String duration, int calories, String imagePath) {
        this.title = title;
        this.duration = duration;
        this.calories = calories;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public int getCalories() {
        return calories;
    }

    public String getImagePath() {
        return imagePath;
    }
}
