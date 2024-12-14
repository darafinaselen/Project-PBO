import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MealDetailPage extends Application {

    private String mealTitle;

    // Constructor untuk menerima data dari HomePage
    public MealDetailPage(String mealTitle) {
        this.mealTitle = mealTitle;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(mealTitle + " Detail");

        // Root layout
        StackPane root = new StackPane();

        // VBox untuk konten utama
        VBox content = new VBox(15);
        content.setPadding(new Insets(30));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #ffffff;");

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

        content.getChildren().addAll(imageView, titleLabel, descriptionLabel);

        // Tombol back di pojok kiri atas
        ImageView backIcon = new ImageView(new Image("file:assets/icon/icon_button_back.png"));
        backIcon.setFitWidth(30);
        backIcon.setFitHeight(30);
        backIcon.setOnMouseClicked(e -> {
            // Kembali ke halaman sebelumnya (HomePage)
            primaryStage.close();
            // Anda dapat menambahkan logika untuk membuka kembali HomePage di sini.
        });

        StackPane.setAlignment(backIcon, Pos.TOP_LEFT);
        StackPane.setMargin(backIcon, new Insets(10));

        root.getChildren().addAll(content, backIcon);

        // Scene setup
        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method untuk mendapatkan deskripsi berdasarkan meal
    private String getDescriptionForMeal(String mealTitle) {
        switch (mealTitle) {
            case "Dada Ayam":
                return "Sumber protein yang baik untuk tubuh.";
            case "Brokoli":
                return "Sayuran hijau yang kaya serat dan vitamin.";
            case "Buah":
                return "Makanan segar yang kaya vitamin dan mineral.";
            case "Nasi Merah":
                return "Pilihan karbohidrat sehat dengan serat tinggi.";
            case "Pisang":
                return "Buah yang kaya potasium untuk energi.";
            case "Daging":
                return "Sumber protein berkualitas tinggi.";
            case "Sayuran":
                return "Makanan sehat yang mengandung banyak serat.";
            case "Sereal Oat":
                return "Pilihan sarapan sehat yang kaya serat.";
            case "Susu Rendah Lemak":
                return "Sumber kalsium dengan kandungan lemak rendah.";
            case "Telur":
                return "Sumber protein yang sangat baik.";
            case "Salad":
                return "Kombinasi sayuran segar yang menyehatkan.";
            default:
                return "Makanan ini dirancang untuk memenuhi kebutuhan gizi harian Anda.";
        }
    }
}
