import javax.swing.JScrollPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomePage extends Application {

    private VBox contentArea; // Kontainer untuk menampilkan konten dinamis

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Home");

        // Root layout
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #ffffff;");

        // Search Bar
        HBox searchBar = createSearchBar();
        root.getChildren().add(searchBar);

        // Tabs: For You, Workout, Meals
        HBox tabs = createTabs();
        root.getChildren().add(tabs);

        // Area untuk menampilkan konten dinamis
        contentArea = new VBox(10);
        contentArea.setPadding(new Insets(10));
        contentArea.setAlignment(Pos.TOP_LEFT);

        // Default: tampilkan konten "For You"
        showForYouContent();

        ScrollPane scrollPane = new ScrollPane(contentArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        hideScrollBars(scrollPane); // Sembunyikan scrollbar
        root.getChildren().add(scrollPane);

        // Scene setup
        Scene scene = new Scene(root, 390, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Sembunyikan scrollbar
    private void hideScrollBars(ScrollPane scrollPane) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: transparent; " +
                        "-fx-background-insets: 0; " +
                        "-fx-padding: 0;"
        );
    }

    // Search Bar
    private HBox createSearchBar() {
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefHeight(35);
        searchField.setPrefWidth(350);
        searchField.setStyle("-fx-background-color: #f5f5f5; -fx-border-radius: 15; -fx-background-radius: 15;");

        ImageView searchIcon = new ImageView(new Image("file:icons/search.png"));
        searchIcon.setFitHeight(20);
        searchIcon.setFitWidth(20);

        HBox searchBox = new HBox(10, searchIcon, searchField);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setStyle("-fx-background-color: #e8f5e9; -fx-border-radius: 15;");
        searchBox.setPadding(new Insets(5));
        return searchBox;
    }

    // Tabs untuk navigasi
    private HBox createTabs() {
        Button forYouTab = new Button("For You");
        Button workoutTab = new Button("Workout");
        Button mealsTab = new Button("Meals");

        forYouTab.setStyle("-fx-background-color: #e8f5e9;");
        workoutTab.setStyle("-fx-background-color: #e8f5e9;");
        mealsTab.setStyle("-fx-background-color: #e8f5e9;");

        forYouTab.setOnAction(e -> showForYouContent());
        workoutTab.setOnAction(e -> showWorkoutContent());
        mealsTab.setOnAction(e -> showMealsContent());

        HBox tabs = new HBox(10, forYouTab, workoutTab, mealsTab);
        tabs.setAlignment(Pos.CENTER);
        return tabs;
    }

    // yg bawah
    // private HBox createTab() {
    //     Button homeTab = new Button("assets\\Icons\\home.png");
    //     Button jadwalTab = new Button("assets\\Icons\\chat.png");
    //     Button profilTab = new Button("assets\\Icons\\profile.png");

    //     homeTab.setStyle("-fx-background-color: #e8f5e9;");
    //     jadwalTab.setStyle("-fx-background-color: #e8f5e9;");
    //     profilTab.setStyle("-fx-background-color: #e8f5e9;");

    //     homeTab.setOnAction(e -> showForYouContent());
    //     jadwalTab.setOnAction(e -> showWorkoutContent());
    //     profilTab.setOnAction(e -> showMealsContent());

    //     HBox tabs = new HBox(10, homeTab, jadwalTab, profilTab);
    //     tabs.setAlignment(Pos.CENTER);
    //     return tabs;
    // }

    // Konten "For You"
    // Konten "For You"
private void showForYouContent() {
    contentArea.getChildren().clear();
    
    // Label Rekomendasi Untuk Anda (bold)
    Label label = new Label("Rekomendasi Untuk Anda");
    label.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 16));
    contentArea.getChildren().add(label);
    
    // Konten Workout - dengan scroll horizontal dan tidak bisa ke bawah
    HBox workoutSection = createWorkoutSection();  // Menggunakan HBox untuk scroll horizontal
    ScrollPane workoutScrollPane = new ScrollPane(workoutSection);
    workoutScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Scroll horizontal aktif
    workoutScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Tidak ada scroll vertikal
    workoutScrollPane.setFitToHeight(true);
    workoutScrollPane.setFitToWidth(true);
    workoutScrollPane.setStyle(
        "-fx-background-color: transparent; " +
        "-fx-padding: 0; " +
        "-fx-border-color: transparent; " +
        "-fx-control-inner-background: transparent; " + 
        "-fx-scroll-bar: hidden;");
    contentArea.getChildren().add(workoutScrollPane);
    
    // Konten Meals tetap bisa scroll vertikal
    Label mealsLabel = new Label("Meals");
    mealsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
    contentArea.getChildren().add(mealsLabel);
    
    VBox mealsSection = createMealsSection();  // Menggunakan VBox untuk scroll vertikal
    ScrollPane mealsScrollPane = new ScrollPane(mealsSection);
    mealsScrollPane.setFitToWidth(true);
    mealsScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll
    mealsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Tidak bisa scroll horizontal
    mealsScrollPane.setStyle(
        "-fx-background-color: transparent; " +
        "-fx-padding: 0; " +
        "-fx-border-color: transparent; " +
        "-fx-control-inner-background: transparent; " + 
        "-fx-scroll-bar: hidden;");
    contentArea.getChildren().add(mealsScrollPane);
}

    // Konten "Workout"
    private void showWorkoutContent() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("Workout Harian di Rumah"));
        contentArea.getChildren().add(createWorkoutSection());
    }

    // Konten "Meals"
    private void showMealsContent() {
        contentArea.getChildren().clear();
        contentArea.getChildren().add(new Label("Pola Makan Harian"));
        contentArea.getChildren().add(createMealsSection());
    }

    // Bagian Workout
    // Bagian Workout (dengan konten digeser horizontal)
private HBox createWorkoutSection() {
    HBox workoutSection = new HBox(10);  // Gunakan HBox untuk horizontal scroll
    workoutSection.setAlignment(Pos.CENTER_LEFT);
    workoutSection.setPrefHeight(130); 

    workoutSection.getChildren().addAll(
        createWorkoutCard("ABS", "asetttt/Workout/ABS_frame.png"),
        createWorkoutCard("Upper Body", "asetttt/Workout/Upper Body.jpg"),
        createWorkoutCard("Buns n' Trights", "asetttt/Workout/Mediasi.png"),
        createWorkoutCard("HIIT", "asetttt/Workout/Mediasi.png"),
        createWorkoutCard("Full Body", "asetttt/Workout/Mediasi.png")
    );

    // Membungkus workoutSection dalam ScrollPane untuk scroll horizontal
    ScrollPane workoutScrollPane = new ScrollPane(workoutSection);
    workoutScrollPane.setFitToWidth(true);  // Menyesuaikan lebar konten dengan lebar ScrollPane
    workoutScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);  // Aktifkan scrollbar horizontal
    workoutScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);  // Nonaktifkan scrollbar vertikal

    // Sembunyikan scrollbar vertikal yang tidak perlu
    hideScrollBars(workoutScrollPane);

    return workoutSection;  // Mengembalikan HBox
}

// Bagian Meals (dengan konten digeser vertikal)
private VBox createMealsSection() {
    VBox mealsSection = new VBox(10);  // Gunakan VBox untuk vertikal scroll
    mealsSection.getChildren().addAll(
            createMealItem("Dada Ayam", "07.00", "asetttt/Meals/Ayam.jpg"),
            createMealItem("Brokoli", "07.00", "asetttt/Meals/Brokoli.jpeg"),
            createMealItem("Buah", "07.00", "asetttt/Meals/Buah.jpg"),
            createMealItem("Salad", "07.00", "asetttt/Meals/Salad.jpeg"),
            createMealItem("Nasi Merah", "12.00", "asetttt/Meals/Nasi Merah.jpeg"),
            createMealItem("Pisang", "12.00", "asetttt/Meals/Pisang.jpg.jpeg"),
            createMealItem("Daging", "12.00", "asetttt/Meals/Daging.jpeg"),
            createMealItem("Sayuran", "12.00", "asetttt/Meals/Sayuran.jpg"),
            createMealItem("Sereal", "19.00", "asetttt/Meals/Sereal.jpeg"),
            createMealItem("Susu", "19.00", "asetttt/Meals/Susu.jpg"),
            createMealItem("Telur", "19.00", "asetttt/Meals/Telur.jpeg")
    );

    return mealsSection;  // Mengembalikan VBox
}

// Card Workout
// Card Workout
private VBox createWorkoutCard(String title, String imagePath) {
    ImageView imageView = createImageView(imagePath, 90, 90);
    Label label = new Label(title);

    VBox card = new VBox(5, imageView, label);
    card.setAlignment(Pos.CENTER);

    // Efek saat hover (kursor diarahkan ke item)
    card.setOnMouseEntered(event -> {
        card.setStyle("-fx-background-color: #e8f5e9; -fx-cursor: hand;");  // Ubah warna latar belakang dan cursor
    });

    // Efek saat mouse keluar dari item
    card.setOnMouseExited(event -> {
        card.setStyle("-fx-background-color: transparent; -fx-cursor: default;");
    });

    // Efek saat item ditekan (klik)
    card.setOnMousePressed(event -> {
        card.setStyle("-fx-background-color: #c8e6c9;");  // Ubah warna latar belakang saat klik
    });

    // Mengembalikan warna latar belakang setelah klik dilepas
    card.setOnMouseReleased(event -> {
        card.setStyle("-fx-background-color: #e8f5e9;");
    });

    // Event handler untuk membuka WorkoutListApp ketika card diklik
    card.setOnMouseClicked(event -> {
        try {
            new WorkoutDetailPage(title).start(new Stage());  // Membuka WorkoutDetailPage dengan title
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    return card;
}

// Meal Item dengan Border dan Efek Hover & Klik
private HBox createMealItem(String name, String time, String imagePath) {
    ImageView imageView = createImageView(imagePath, 50, 50);
    Label label = new Label(name + " | " + time);

    HBox mealItem = new HBox(10, imageView, label);
    mealItem.setAlignment(Pos.CENTER_LEFT);

    // Border default
    mealItem.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: transparent;");

    // Efek saat hover (kursor diarahkan ke item)
    mealItem.setOnMouseEntered(event -> {
        mealItem.setStyle("-fx-border-color: #4caf50; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #e8f5e9; -fx-cursor: hand;");
    });

    // Efek saat mouse keluar dari item
    mealItem.setOnMouseExited(event -> {
        mealItem.setStyle("-fx-border-color: #cccccc; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: transparent; -fx-cursor: default;");
    });

    // Efek saat item ditekan (klik)
    mealItem.setOnMousePressed(event -> {
        mealItem.setStyle("-fx-border-color: #4caf50; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #c8e6c9;");
    });

    // Mengembalikan warna latar belakang setelah klik dilepas
    mealItem.setOnMouseReleased(event -> {
        mealItem.setStyle("-fx-border-color: #4caf50; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #e8f5e9;");
    });

    // Event handler untuk membuka WorkoutListApp ketika meal item diklik
    mealItem.setOnMouseClicked(event -> {
        try {
            new MealDetailPage(name).start(new Stage());  // Membuka MealDetailPage
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
    

    return mealItem;
}

// Helper untuk ImageView
private ImageView createImageView(String path, int width, int height) {
    ImageView imageView = new ImageView(new Image("file:" + path));
    imageView.setFitWidth(width);
    imageView.setFitHeight(height);
    return imageView;
}

}