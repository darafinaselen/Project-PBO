import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public class WorkoutAppWithVideo extends Application {

    private int duration = 20; // Durasi awal dalam detik

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Workout App");

        // Media player
        String videoPath = "aset/How to Do_ JUMPING JACKS.mp4"; // Sesuaikan path file Anda
        File videoFile = new File(videoPath);
        if (!videoFile.exists()) {
            System.out.println("File video tidak ditemukan: " + videoPath);
            return;
        }

        Media media = new Media(videoFile.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(350);
        mediaView.setFitHeight(200);

        // Tambahkan event handling untuk memutar/menjeda video
        mediaView.setOnMouseClicked(event -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
            } else {
                mediaPlayer.play();
            }
        });

        // Set ulang pemutaran video setelah selesai, pengguna harus mengklik video
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.pause(); // Pastikan video berhenti
            mediaPlayer.seek(mediaPlayer.getStartTime()); // Reset ke awal
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

        // Instruksi
        Label instructionsLabel = new Label("INSTRUKSI:");
        instructionsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        instructionsLabel.setAlignment(Pos.CENTER_LEFT);
        TextArea instructionsArea = new TextArea();
        instructionsArea.setText(
                "Mulai dari posisi berdiri dengan kaki rapat, lalu lompat dengan kaki terbuka dan telapak tangan bersentuhan di atas kepala. "
                        + "Kembali ke posisi awal dan lakukan babak berikutnya.\n\n"
                        + "Latihan ini memberikan olahraga seluruh tubuh dan menggerakkan semua kelompok otot besar Anda."
        );
        instructionsArea.setWrapText(true);
        instructionsArea.setEditable(false);
        instructionsArea.setStyle("-fx-font-size: 12px;");
        instructionsArea.setPrefHeight(150);

        // Layout utama
        VBox root = new VBox(15, mediaView, durationBox, instructionsLabel, instructionsArea);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #F5F5F5;");

        // Scene
        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
