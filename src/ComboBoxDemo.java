import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ComboBoxDemo extends Application {


    private String[] flagTitles = {"United Arabs Emirates", "Saudi Arabia", "Qatar",
            "Oman", "Kuwait", "bahrain"};


    private ImageView[] flagImage = {new ImageView("UAE.png"),
            new ImageView("SaudiArabia.jpg"),
            new ImageView("Qatar.png"),
            new ImageView("Oman.jpg"),
            new ImageView("Kuwait.png"),
            new ImageView("Bahrain.png")};

    private String[] flagDescription = new String[6];

    private DescriptionPane descriptionPane = new DescriptionPane();


    private ComboBox<String> cbo = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        flagDescription[0] = "The United Arab Emirates,\n or simply the Emirates,\nis a country in Western Asia.";
        flagDescription[1] = "Saudi Arabia, \n officially the Kingdom of Saudi Arabia,\n is a country on the Arabian \nPeninsula in Western Asia.";
        flagDescription[2] = "Qatar is a peninsular Arab \ncountry whose terrain comprises \narid desert and a long Persian (Arab)\n Gulf shoreline of beaches and dunes. ";
        flagDescription[3] = "Oman, officially the Sultanate of Oman,\n is a country in Western Asia. \nIt is situated on the southeastern\n coast of the Arabian Peninsula,\n and spans the mouth of the Persian Gulf.";
        flagDescription[4] = "Kuwait, officially the State of Kuwait,\n is a country in Western Asia.\n It is situated in the northern edge of \nEastern Arabia at the tip of the Persian Gulf,\n bordering Iraq to the north and Saudi Arabia to the south";
        flagDescription[5] = "Bahrain, officially the Kingdom of Bahrain,\n is an island country in Western Asia.";
        for (int i = 0; i < flagDescription.length; i++) {
            Scanner input;
            String s = "";

            try {
                input = new Scanner(new File("src/Text Files/description" + i + ".txt"));

                while (input.hasNext()) {
                    s += input.nextLine() + "\n";
                }
                flagDescription[i] = s;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        setDisplay(0);

        BorderPane pane = new BorderPane();

        BorderPane paneForComboBox = new BorderPane();
        paneForComboBox.setLeft(new Label("Select a country: "));
        paneForComboBox.setCenter(cbo);
        pane.setTop(paneForComboBox);
        cbo.setPrefWidth(400);
        cbo.setValue("United Arabs Emirates");

        ObservableList<String> items =
                FXCollections.observableArrayList(flagTitles);
        cbo.getItems().addAll(items);
        pane.setCenter(descriptionPane);


        cbo.setOnAction(e -> setDisplay(items.indexOf(cbo.getValue())));

        Scene scene = new Scene(pane, 450, 170);
        primaryStage.setTitle("ComboBoxDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setDisplay(int index) {
        descriptionPane.setTitle(flagTitles[index]);
        descriptionPane.setImageView(flagImage[index]);
        descriptionPane.setDescription(flagDescription[index]);
    }


    public static void main(String[] args) {
        launch(args);
    }

    private class DescriptionPane extends BorderPane {

        private Label lblImageTitle = new Label();

        private TextArea taDescription = new TextArea();

        public DescriptionPane() {
            lblImageTitle.setContentDisplay(ContentDisplay.TOP);
            lblImageTitle.setPrefSize(200,  100);
            lblImageTitle.setFont(new Font("SansSerif", 16));
            taDescription.setFont(new Font("Serif", 14));

            taDescription.setWrapText(true);
            taDescription.setEditable(false);

            ScrollPane scrollPane = new ScrollPane(taDescription);

            setLeft(lblImageTitle);
            setCenter(scrollPane);
            setPadding(new Insets(5, 5, 5, 5));
        }

        public void setTitle(String title) {
            lblImageTitle.setText(title);
        }
        public void setImageView(ImageView icon) {
            lblImageTitle.setGraphic(icon);
        }


        public void setDescription(String text) {
            taDescription.setText(text);
        }
    }
}
