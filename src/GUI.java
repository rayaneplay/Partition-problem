import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class GUI extends Application {

   // private int[] T;
    private TextField sizeInputField;
    private TextField arrayInputField;
    private ListView<String> listView;
    private ComboBox<String> searchAlgorithmComboBox = new ComboBox<>();



    @Override
    public void start(Stage primaryStage) {


        // Create the input controls
        searchAlgorithmComboBox.setPromptText("Select search algorithm");

        Label sizeInputLabel = new Label("Size:");
        sizeInputField = new TextField();
        sizeInputField.setPrefWidth(400);
        sizeInputField.setStyle("-fx-font-size: 10pt;");
        HBox sizeInputBox = new HBox(sizeInputLabel, sizeInputField);

        Label arrayInputLabel = new Label("Array:");
        arrayInputField = new TextField();
        arrayInputField.setEditable(true);
        arrayInputField.setPrefWidth(400);
        arrayInputField.setStyle("-fx-font-size: 10pt;");
        HBox arrayInputBox = new HBox(arrayInputLabel, arrayInputField);

        Button generateButton = new Button("Generate random instance");
       // GridPane.setMargin(generateButton,new Insets(0, 0, 0, 1100));

        searchAlgorithmComboBox.getItems().addAll("DFS", "A* - Maximum Element", "A* - Sum of remaining elements");

       // searchAlgorithmComboBox.getStyleClass().add(".combo-box");
        //VBox inputControls2 = new VBox(sizeInputBox, arrayInputBox, searchAlgorithmComboBox, generateButton);



        generateButton.setOnAction(e -> {
            try {
                int size = Integer.parseInt(sizeInputField.getText());
                Main.T = genInstance(size);
                System.out.println(Arrays.toString(Main.T));
                arrayInputField.setText(Arrays.toString(Main.T));
            } catch (NumberFormatException ex) {
                // show an error message or log the exception
                ex.printStackTrace();
            }
        });

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> search());

// Group the input controls in a VBox
        VBox inputControls = new VBox(sizeInputBox, arrayInputBox, generateButton,searchAlgorithmComboBox);
        inputControls.setSpacing(10);
        inputControls.setPadding(new Insets(10));

// Create the list view for displaying the search results
        listView = new ListView<>();

// Create the layout for the user interface
        BorderPane layout = new BorderPane();
        layout.setTop(inputControls);
        layout.setCenter(listView);

        HBox buttonBox = new HBox(searchButton,searchAlgorithmComboBox);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setSpacing(10);

        layout.setBottom(buttonBox);

// Create the scene and set it as the primary stage scene
        Scene scene = new Scene(layout);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

        private int[] genInstance(int size) {
        int[] T = new int[size];
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            T[i] = random.nextInt(101);
        }
        return T;
    }

    private void search() {

        String selectedAlgorithm = searchAlgorithmComboBox.getValue();


       Main.T = stringToArray(arrayInputField.getText());
       System.out.println("T is : " +Arrays.toString(Main.T));

        switch(selectedAlgorithm) {
            case "DFS":
                ArrayList<int[]> results = DFS.search();
                ArrayList<int[]> translatedResult = new ArrayList<>();
                listView.getItems().clear();
                for(int  [] i : results){
                    translatedResult.add(Node.translate(i).get(0));
                    translatedResult.add(Node.translate(i).get(1));

                }
                for(int i=0;i< translatedResult.size();i=i+2){
                    listView.getItems().add("Solution number "+i/2+" :");
                    listView.getItems().add(Arrays.toString(translatedResult.get(i)));
                    listView.getItems().add(Arrays.toString(translatedResult.get(i+1)));
                    listView.getItems().add("The difference between the 2 subsets is : "+ Math.abs(Main.sumArray(translatedResult.get(i))-Main.sumArray(translatedResult.get(i+1))));
                }
                break;
            case "A* - Maximum Element":
                Heuristic h1 = new MaximumElementHeuristic();
                ArrayList<int[]> results2 = Astar.search(h1);
                ArrayList<int[]> translatedResult2 = new ArrayList<>();

                listView.getItems().clear();
                for(int  [] i : results2){
                    translatedResult2.add(Node.translate(i).get(0));
                    translatedResult2.add(Node.translate(i).get(1));

                }
                for(int i=0;i< translatedResult2.size();i=i+2){
                    listView.getItems().add("Solution number "+i/2+" :");
                    listView.getItems().add(Arrays.toString(translatedResult2.get(i)));
                    listView.getItems().add(Arrays.toString(translatedResult2.get(i+1)));
                    listView.getItems().add("The difference between the 2 subsets is : "+ Math.abs(Main.sumArray(translatedResult2.get(i))-Main.sumArray(translatedResult2.get(i+1))));
                }
                break;
            case "A* - Sum of remaining elements":
                Heuristic h2 = new SumOfRemainingElementsHeuristic();
                ArrayList<int[]> results3 = Astar.search(h2);
                ArrayList<int[]> translatedResult3 = new ArrayList<>();

                listView.getItems().clear();
                for(int  [] i : results3){
                    translatedResult3.add(Node.translate(i).get(0));
                    translatedResult3.add(Node.translate(i).get(1));

                }
                for(int i=0;i< translatedResult3.size();i=i+2){
                    listView.getItems().add("Solution number "+i/2+" :");
                    listView.getItems().add(Arrays.toString(translatedResult3.get(i)));
                    listView.getItems().add(Arrays.toString(translatedResult3.get(i+1)));
                    listView.getItems().add("The difference between the 2 subsets is : "+ Math.abs(Main.sumArray(translatedResult3.get(i))-Main.sumArray(translatedResult3.get(i+1))));
                }
                break;
        }

    }


    private int[] stringToArray(String str) {
        String[] strArray = str.replace("[", "").replace("]", "").split(",");
        int[] array = new int[strArray.length];
        for (int i = 0; i < strArray.length; i++) {
            array[i] = Integer.parseInt(strArray[i].trim());
        }
        return array;
    }
}
