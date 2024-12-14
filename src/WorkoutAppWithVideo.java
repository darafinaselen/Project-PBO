import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class WorkoutAppWithVideo extends Application {
    private final String name;
    private final String description;
    private final String videoPath;
    private int duration = 20;

    public WorkoutAppWithVideo(String name, String description, String videoPath) {
        this.name = name;
        this.description = description;
        this.videoPath = videoPath;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(name);

        // Video player setup
        File videoFile = new File(videoPath);
        MediaView mediaView;
        MediaPlayer mediaPlayer = null;
        if (videoFile.exists()) {
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);
            mediaPlayer.play();
        } else {
            mediaView = new MediaView();
            Label errorLabel = new Label("Video tidak ditemukan: " + videoPath);
            errorLabel.setStyle("-fx-text-fill: red;");
            mediaView.setMediaPlayer(null);
        }

        mediaView.setFitWidth(350);
        mediaView.setFitHeight(200);

        // Tambahkan event handling untuk memutar/menjeda video
        if (mediaPlayer != null) {
            MediaPlayer finalMediaPlayer = mediaPlayer;
        mediaView.setOnMouseClicked(event -> {
            if (finalMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                finalMediaPlayer.pause();
            } else {
                finalMediaPlayer.play();
            }
        });

        // Set ulang pemutaran video setelah selesai, pengguna harus mengklik video
        finalMediaPlayer.setOnEndOfMedia(() -> {
            finalMediaPlayer.pause(); // Pastikan video berhenti
            finalMediaPlayer.seek(finalMediaPlayer.getStartTime()); // Reset ke awal
            System.out.println("Video selesai. Klik video untuk memulai ulang.");
        });

        // Label durasi
        Label durationLabel = new Label("DURASI:");
        durationLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");


        // TextField untuk menampilkan durasi
        TextField durationField = new TextField(String.format("%02d detik", duration));
        durationField.setEditable(false);
        durationField.setMaxWidth(80);
        durationField.setStyle("-fx-font-size: 14px; -fx-alignment: center;");

        // Tombol tambah dan kurangi durasi
        Button addButton = new Button("+");
        addButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 40px; -fx-pref-height: 30px; -fx-background-color: #B9C4A0;");
        Button subtractButton = new Button("-");
        subtractButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 40px; -fx-pref-height: 30px; -fx-background-color: #B9C4A0;");

        // Event handler untuk tombol
        addButton.setOnAction(e -> {
            duration += 10;
            durationField.setText(String.format("%02d detik", duration));
        });

        subtractButton.setOnAction(e -> {
            if (duration > 10) {
                duration -= 10;
                durationField.setText(String.format("%02d detik", duration));
            }
        });

        // Layout untuk pengaturan durasi (horizontal)
        HBox durationControlBox = new HBox(10, subtractButton, durationField, addButton);
        durationControlBox.setAlignment(Pos.CENTER_RIGHT);

        // Layout untuk label durasi dan kontrol durasi
        HBox durationBox = new HBox(10, durationLabel, durationControlBox);
        durationBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(durationControlBox, new Insets(0, 0, 0, 10));  // Mengurangi margin di sebelah kiri

        // Description label
        Label instructionsLabel = new Label("INSTRUKSI:");
        instructionsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        instructionsLabel.setAlignment(Pos.TOP_LEFT);
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-line-spacing: 1.5;");

        descriptionLabel.setStyle("-fx-font-size: 14px; -fx-line-spacing: 1.5; -fx-text-alignment: justify;");
        // Layout
        VBox root = new VBox(20, mediaView, durationBox, instructionsLabel, descriptionLabel);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(15));

        // Scene
        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        }
    }

}
