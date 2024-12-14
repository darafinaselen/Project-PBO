import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private MediaPlayer mediaPlayer;

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
        Slider progressBar = new Slider(0, 1, 0);
        progressBar.setPrefWidth(200); // Memperlebar slider
        progressBar.setStyle("-fx-accent: blue;");

        Label progressLabel = new Label("00:00 / 00:00");
        progressLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");

        if (videoFile.exists()) {
            Media media = new Media(videoFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);
        } else {
            mediaView = new MediaView();
            Label errorLabel = new Label("Video tidak ditemukan: " + videoPath);
            errorLabel.setStyle("-fx-text-fill: red;");
            mediaView.setMediaPlayer(null);
        }

        mediaView.setFitWidth(350);
        mediaView.setFitHeight(200);

        if (mediaPlayer != null) {
            MediaPlayer finalMediaPlayer = mediaPlayer;

            // Thread untuk Sinkronisasi Slider dan Label Progres
            Thread progressUpdater = new Thread(() -> {
                while (true) {
                    try {
                        if (finalMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING && finalMediaPlayer.getTotalDuration() != null) {
                            double progress = finalMediaPlayer.getCurrentTime().toSeconds() / finalMediaPlayer.getTotalDuration().toSeconds();
                            double currentSeconds = finalMediaPlayer.getCurrentTime().toSeconds();
                            double totalSeconds = finalMediaPlayer.getTotalDuration().toSeconds();

                            // Sinkronisasi dengan JavaFX Thread
                            javafx.application.Platform.runLater(() -> {
                                progressBar.setValue(progress);
                                progressLabel.setText(formatTime(currentSeconds) + " / " + formatTime(totalSeconds));
                            });
                        }
                        Thread.sleep(100); // Perbarui setiap 100ms
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            progressUpdater.setDaemon(true);
            progressUpdater.start();

            progressBar.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (progressBar.isValueChanging() && finalMediaPlayer.getTotalDuration() != null) {
                    double seekTime = newVal.doubleValue() * finalMediaPlayer.getTotalDuration().toSeconds();
                    finalMediaPlayer.seek(javafx.util.Duration.seconds(seekTime));
                }
            });

            // Control Play and Pause with Icon
            ImageView playIcon = new ImageView(new Image("file:assets/icon/play.png"));
            playIcon.setFitWidth(30);
            playIcon.setFitHeight(30);

            Button playButton = new Button();
            playButton.setGraphic(playIcon);
            playButton.setStyle("-fx-background-color: transparent;");

            playButton.setOnAction(event -> {
                if (finalMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    finalMediaPlayer.pause();
                } else {
                    finalMediaPlayer.play();
                }
            });

            // Reset video on end
            finalMediaPlayer.setOnEndOfMedia(() -> {
                finalMediaPlayer.pause();
                finalMediaPlayer.seek(finalMediaPlayer.getStartTime());
            });

            // Layout for ProgressBar and Control Button
            HBox controlBox = new HBox(10, playButton, progressBar, progressLabel);
            controlBox.setAlignment(Pos.CENTER);
            controlBox.setPadding(new Insets(10));

            // Duration controls
            Label durationLabel = new Label("DURASI:");
            durationLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            TextField durationField = new TextField(String.format("%02d detik", duration));
            durationField.setEditable(false);
            durationField.setMaxWidth(80);
            durationField.setStyle("-fx-font-size: 14px; -fx-alignment: center;");

            Button addButton = new Button("+");
            addButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 40px; -fx-pref-height: 30px; -fx-background-color: #B9C4A0;");

            Button subtractButton = new Button("-");
            subtractButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 40px; -fx-pref-height: 30px; -fx-background-color: #B9C4A0;");

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

            HBox durationControlBox = new HBox(10, subtractButton, durationField, addButton);
            durationControlBox.setAlignment(Pos.CENTER_RIGHT);
            HBox durationBox = new HBox(10, durationLabel, durationControlBox);
            durationBox.setAlignment(Pos.CENTER_LEFT);
            HBox.setMargin(durationControlBox, new Insets(0, 0, 0, 10));

            // Back button setup
            ImageView backIcon = new ImageView(new Image("file:assets/icon/icon_button_back.png"));
            backIcon.setFitWidth(30);
            backIcon.setFitHeight(30);

            Button backButton = new Button();
            backButton.setGraphic(backIcon);
            backButton.setStyle("-fx-background-color: transparent;");
            backButton.setOnAction(event -> {
                // Close current stage and open WorkoutListApp
                primaryStage.close();
                new WorkoutListApp().start(new Stage());
            });

            // Layout for back button
            HBox backButtonBox = new HBox(backButton);
            backButtonBox.setAlignment(Pos.TOP_LEFT);
            backButtonBox.setPadding(new Insets(10));

            // Instructions and description
            Label instructionsLabel = new Label("INSTRUKSI:");
            instructionsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
            Label descriptionLabel = new Label(description);
            descriptionLabel.setWrapText(true);
            descriptionLabel.setStyle("-fx-font-size: 14px; -fx-line-spacing: 1.5; -fx-text-alignment: justify;");

            // Main layout
            VBox root = new VBox(20, backButtonBox, mediaView, controlBox, durationBox, instructionsLabel, descriptionLabel);
            root.setAlignment(Pos.TOP_CENTER);
            root.setPadding(new Insets(15));

            Scene scene = new Scene(root, 390, 700);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Stop video when window is closed
            primaryStage.setOnCloseRequest(event -> {
                if (mediaPlayer != null) {
                    mediaPlayer.stop(); // Stop the video when the window is closed
                }
            });
        }
    }

    private String formatTime(double seconds) {
        int minutes = (int) seconds / 60;
        int secs = (int) seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }
}
