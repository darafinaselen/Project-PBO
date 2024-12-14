import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
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

        // Tombol kembali di pojok kiri atas
        Button backButton = new Button();
        ImageView backIcon = new ImageView(new Image("file:assets/icon/icon_button_back.png"));
        backIcon.setFitWidth(30); // Ukuran ikon
        backIcon.setFitHeight(30);
        backButton.setGraphic(backIcon);
        backButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand;"); // Tampilan tombol transparan
        backButton.setOnAction(e -> {
            try {
                new HomePage().start(new Stage()); // Kembali ke HomePage
                primaryStage.close(); // Menutup WorkoutDetailPage
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

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

        // Membuat ScrollPane agar konten dapat digulir
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true); // Menyesuaikan lebar konten
        scrollPane.setPannable(true); // Mengaktifkan pengguliran horizontal
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Menampilkan scrollbar horizontal jika diperlukan
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Menampilkan scrollbar vertikal jika diperlukan

        // Layout utama dengan tombol kembali
        StackPane mainLayout = new StackPane();
        StackPane.setAlignment(backButton, Pos.TOP_LEFT); // Posisi tombol kembali di pojok kiri atas
        mainLayout.getChildren().addAll(scrollPane, backButton);

        // Scene setup dengan ukuran 390x700
        Scene scene = new Scene(mainLayout, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method untuk mendapatkan deskripsi berdasarkan workout
    private String getDescriptionForWorkout(String workoutTitle) {
        switch (workoutTitle) {
            case "ABS":
                return "ABS adalah singkatan dari abdominal muscles atau otot perut, yang merujuk pada kelompok otot yang terletak di area perut bagian depan. Otot-otot ini memiliki beberapa bagian, yang masing-masing memiliki fungsi yang berbeda.";
            case "Upper Body":
                return "Upper body merujuk pada bagian tubuh manusia yang terletak di bagian atas, mulai dari leher hingga pinggang, yang mencakup kepala, leher, dada, punggung atas, bahu, dan lengan atas. Latihan upper body bertujuan untuk memperkuat dan membentuk otot-otot di bagian tubuh ini, yang meliputi otot dada (pectoralis), otot punggung (latissimus dorsi dan trapezius), otot bahu (deltoid), serta otot lengan (bisep dan trisep).";
            case "Buns n' Trights":
                return "Buns and Thighs adalah latihan yang berfokus pada penguatan dan pembentukan otot gluteus (bokong) serta paha. Latihan ini sering melibatkan gerakan seperti squats, lunges, dan bridges untuk melatih otot secara maksimal.";
            case "HIIT":
                return "HIIT (High-Intensity Interval Training) adalah jenis latihan kardio yang melibatkan periode latihan intensitas tinggi yang diikuti oleh periode istirahat singkat. Metode ini dirancang untuk membakar kalori secara efisien dan meningkatkan kebugaran kardiovaskular.";
            case "Full Body":
                return "Latihan Full Body dirancang untuk melatih semua kelompok otot utama dalam satu sesi latihan. Cocok untuk meningkatkan kekuatan tubuh secara keseluruhan dan membakar kalori dalam waktu singkat.";
            default:
                return "Latihan ini dirancang untuk membantu meningkatkan kesehatan dan kebugaran secara keseluruhan.";
        }
    }
}
