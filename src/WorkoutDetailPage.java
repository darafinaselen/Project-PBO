import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WorkoutDetailPage extends Application {

    private String workoutTitle;

    // Konstruktor untuk menerima data dari HomePage
    public WorkoutDetailPage(String workoutTitle) {
        this.workoutTitle = workoutTitle;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(workoutTitle + " Detail");

        // Root layout
        VBox root = new VBox(15); // Spasi antar elemen lebih besar agar proporsional
        root.setPadding(new Insets(30)); // Padding lebih besar agar terlihat rapi
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #ffffff;");

        // Gambar workout
        ImageView imageView = new ImageView(new Image("file:asetttt/Workout/" + workoutTitle + ".jpg"));
        imageView.setFitWidth(250); // Ukuran gambar disesuaikan
        imageView.setFitHeight(250);

        // Label untuk nama workout
        Label titleLabel = new Label(workoutTitle);
        titleLabel.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-text-fill: #333333;"); // Ukuran font lebih besar

        // Deskripsi workout
        Label descriptionLabel = new Label(getDescriptionForWorkout(workoutTitle));
        descriptionLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #555555;"); // Ukuran font lebih besar untuk kenyamanan
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(350); // Batas lebar deskripsi agar teks terbungkus

        // Tombol untuk memulai workout
        Button startButton = new Button("Mulai Workout");
        startButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 16; -fx-padding: 10 20; -fx-border-radius: 5; -fx-background-radius: 5;");
        startButton.setOnAction(e -> {
            try {
                new WorkoutListApp().start(new Stage()); // Membuka WorkoutListApp
                primaryStage.close(); // Menutup WorkoutDetailPage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Menambahkan elemen ke layout
        root.getChildren().addAll(imageView, titleLabel, descriptionLabel, startButton);

        // Scene setup dengan ukuran 390x700
        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method untuk mendapatkan deskripsi berdasarkan workout
    private String getDescriptionForWorkout(String workoutTitle) {
        switch (workoutTitle) {
            case "ABS":
                return "Waktu terbatas? Kamu tetap bisa melatih otot perut secara efektif hanya dalam 7 menit! Perkuat abs, tingkatkan stabilitas, dan rasakan hasilnya—cocok untuk jadwal padatmu!";
            case "Upper Body":
                return "Tingkatkan kekuatan tubuh bagian atas dengan latihan yang dirancang untuk melatih otot bahu, dada, dan lengan. Cocok untuk semua level.";
            case "Buns n' Legs":
                return "Latihan intensif untuk mengencangkan dan memperkuat otot kaki dan bokong. Dapatkan bentuk tubuh ideal dengan rutinitas ini.";
            default:
                return "Latihan ini dirancang untuk membantu meningkatkan kesehatan dan kebugaran secara keseluruhan.";
        }
    }

}
