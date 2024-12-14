import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WorkoutListApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Workout List");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));

        // Header
        VBox header = createHeader();
        root.setTop(header);

        // List of workouts
        VBox workoutList = new VBox(10);
        workoutList.setPadding(new Insets(10));
        workoutList.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Fetch workouts from the database
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT name, description, video_path, image_path, duration FROM Workouts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String videoPath = resultSet.getString("video_path");
                String imagePath = resultSet.getString("image_path");
                String duration = resultSet.getString("duration"); // Retrieve the duration
        
                // Create and add each exercise item to the workout list
                HBox exerciseItem = createExerciseItem(name, description, videoPath, imagePath, duration, primaryStage);
                workoutList.getChildren().add(exerciseItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        ScrollPane scrollPane = new ScrollPane(workoutList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");

        root.setCenter(scrollPane);

        // Footer
        HBox footer = createFooter(primaryStage);
        root.setBottom(footer);

        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createHeader() {
        Label categoryLabel = new Label("ABS");
        categoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        categoryLabel.setTextFill(Color.DARKOLIVEGREEN);

        Label equipmentLabel = new Label("Tanpa Alat");
        equipmentLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));

        HBox stats = new HBox(20);
        stats.setAlignment(Pos.CENTER);

        HBox durationBox = createStatBox("Durasi", "\u23F1", "7:24");
        HBox exerciseBox = createStatBox("Latihan", "\u2692", "12");
        HBox calorieBox = createStatBox("Kalori", "\uD83C\uDF21", "50 kkal");

        stats.getChildren().addAll(durationBox, exerciseBox, calorieBox);

        VBox header = new VBox(10, categoryLabel, equipmentLabel, stats);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #e8f5e9; -fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10;");
        return header;
    }

    private HBox createStatBox(String label, String icon, String value) {
        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox statBox = new VBox(5, iconLabel, valueLabel);
        statBox.setAlignment(Pos.CENTER);

        HBox container = new HBox(statBox);
        container.setAlignment(Pos.CENTER);
        return container;
    }

    private HBox createExerciseItem(String name, String description, String videoPath, String imagePath, String duration, Stage stage) {
        // Workout image
        ImageView imageView;
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            imageView = new ImageView(new Image(imageFile.toURI().toString()));
        } else {
            imageView = new ImageView(new Image("file:images/placeholder.png")); // Default image
        }
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
    
        // Workout details (name and duration)
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    
        // Display duration under the name
        Label durationLabel = new Label("Durasi: " + duration);
        durationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
    
        VBox details = new VBox(5, nameLabel, durationLabel); // Include durationLabel here
    
        HBox itemBox = new HBox(10, imageView, details);
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setPadding(new Insets(10));
        itemBox.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-color: #f9f9f9; -fx-background-radius: 5;");
    
        // Click event to open workout detail page
        itemBox.setOnMouseClicked(e -> showWorkoutDetail(name, description, videoPath, stage)); // Still pass description
        return itemBox;
    }
    
    

    private HBox createFooter(Stage stage) {
        Button backButton = new Button("Kembali");
        backButton.setOnAction(e -> stage.close());

        Button startButton = new Button("Mulai");
        startButton.setOnAction(e -> {
            // Open first workout
            showWorkoutDetail("Workout 1", "Latihan pertama", "path/to/video.mp4", stage);
        });

        HBox footer = new HBox(10, backButton, startButton);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        return footer;
    }

    private void showWorkoutDetail(String name, String description, String videoPath, Stage stage) {
        WorkoutAppWithVideo workoutApp = new WorkoutAppWithVideo(name, description, videoPath);
        workoutApp.start(stage);
    }

    
}