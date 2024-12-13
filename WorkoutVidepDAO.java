import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutVidepDAO {
    public static boolean isVideoExist(String title, int duration) {
        String query = "SELECT COUNT(*) FROM workout_video WHERE TRIM(title) = ? AND duration = ?";
        try (Connection conn = Koneksi.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, title.trim());
            stmt.setInt(2, duration);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengecek data: " + e.getMessage());
        }
        return false;
    }

    public static void save(WorkoutVideoList video) {
        if (isVideoExist(video.getTitle(), video.getDuration())) {
            System.out.println("Video dengan judul " + video.getTitle() + " dan durasi " + video.getDuration()
                    + " sudah ada di database.");
            return;
        }

        String query = "INSERT INTO workout_video (title, duration, calories, imagePath, videoPath) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Koneksi.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, video.getTitle());
            stmt.setInt(2, video.getDuration());
            stmt.setInt(3, video.getCalories());
            stmt.setString(4, video.getImagePath());
            stmt.setString(5, video.getVideoPath());
            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan ke database.");
        } catch (SQLException e) {
            System.out.println("Error saat menyimpan data: " + e.getMessage());
        }
    }

    public static List<WorkoutVideoList> fetchAll() {
        List<WorkoutVideoList> videoList = new ArrayList<>();
        String query = "SELECT title, duration, imagePath FROM workout_video";

        try (Connection conn = Koneksi.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String title = rs.getString("title");
                int duration = rs.getInt("duration");
                // int calories = rs.getInt("calories");
                String imagePath = rs.getString("imagePath");
                // String videoPath = rs.getString("videoPath");

                WorkoutVideoList video = new WorkoutVideoList(title, duration, imagePath);
                videoList.add(video);
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengambil data: " + e.getMessage());
        }

        return videoList;
    }

}
