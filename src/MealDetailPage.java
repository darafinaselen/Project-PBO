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

public class MealDetailPage extends Application {

    private String mealTitle;

    // Konstruktor untuk menerima data dari HomePage
    public MealDetailPage(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(mealTitle + " Detail");

        // Root layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #ffffff;");

        // Gambar meal
        ImageView imageView = new ImageView(new Image("file:asetttt/Meals/" + mealTitle + ".jpg"));
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);

        // Label untuk nama meal
        Label titleLabel = new Label(mealTitle);
        titleLabel.setStyle("-fx-font-size: 22; -fx-font-weight: bold; -fx-text-fill: #333333;");

        // Deskripsi meal
        Label descriptionLabel = new Label(getDescriptionForMeal(mealTitle));
        descriptionLabel.setStyle("-fx-font-size: 16; -fx-text-fill: #555555;");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(350);

        // Tombol untuk kembali ke HomePage
        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14; -fx-padding: 10 20;");
        backButton.setOnAction(e -> primaryStage.close());

        root.getChildren().addAll(imageView, titleLabel, descriptionLabel, backButton);

        // Scene setup
        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method untuk mendapatkan deskripsi berdasarkan meal
    private String getDescriptionForMeal(String mealTitle) {
        switch (mealTitle) {
            case "Sarapan":
                return "Mulailah hari Anda dengan energi maksimal! Sarapan ini mengandung nutrisi penting untuk meningkatkan fokus dan daya tahan.";
            case "Makan Siang":
                return "Hidangan sehat dan lezat untuk mengisi tenaga di tengah hari. Kaya protein dan rendah lemak.";
            case "Makan Malam":
                return "Makanan ringan untuk malam hari, membantu tubuh memulihkan diri tanpa membebani pencernaan.";
            default:
                return "Makanan ini dirancang untuk memenuhi kebutuhan gizi harian Anda.";
        }
    }
}
