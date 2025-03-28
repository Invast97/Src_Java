import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class CipherApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label inputLabel = new Label("Введіть текст:");
        TextField inputField = new TextField();

        ComboBox<String> cipherComboBox = new ComboBox<>();
        cipherComboBox.getItems().addAll(
                "Rail Fence Cipher",
                "Matrix Rotation Cipher",
                "Morse Shift Cipher",
                "Keyword Permutation Cipher"
        );
        cipherComboBox.setValue("Rail Fence Cipher");

        Button encodeButton = new Button("Зашифрувати");
        Button decodeButton = new Button("Розшифрувати");

        TableView<String[]> table = new TableView<>();

        TableColumn<String[], String> originalCol = new TableColumn<>("Оригінал");
        TableColumn<String[], String> encodedCol = new TableColumn<>("Зашифровано");
        TableColumn<String[], String> decodedCol = new TableColumn<>("Розшифровано");

        originalCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[0]));
        encodedCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[1]));
        decodedCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()[2]));

        table.getColumns().addAll(originalCol, encodedCol, decodedCol);

        encodeButton.setOnAction(e -> {
            String text = inputField.getText();
            String selectedCipher = cipherComboBox.getValue();
            String encoded = "";
            String decoded = "";

            if (selectedCipher.equals("Rail Fence Cipher")) {
                encoded = railFenceEncode(text, 3);
                decoded = railFenceDecode(encoded, 3);
            } else if (selectedCipher.equals("Matrix Rotation Cipher")) {
                encoded = matrixRotationEncode(text);
                decoded = matrixRotationDecode(encoded);
            } else if (selectedCipher.equals("Morse Shift Cipher")) {
                encoded = morseShiftEncode(text);
                decoded = morseShiftDecode(encoded);
            } else if (selectedCipher.equals("Keyword Permutation Cipher")) {
                String keyword = "KEYWORD";
                encoded = keywordPermutationEncode(text, keyword);
                decoded = keywordPermutationDecode(encoded, keyword);
            }

            table.getItems().clear();
            table.getItems().add(new String[]{text, encoded, decoded});
        });

        decodeButton.setOnAction(e -> {
            String encodedText = inputField.getText();
            String selectedCipher = cipherComboBox.getValue();
            String decoded = "";

            if (selectedCipher.equals("Rail Fence Cipher")) {
                decoded = railFenceDecode(encodedText, 3);
            } else if (selectedCipher.equals("Matrix Rotation Cipher")) {
                decoded = matrixRotationDecode(encodedText);
            } else if (selectedCipher.equals("Morse Shift Cipher")) {
                decoded = morseShiftDecode(encodedText);
            } else if (selectedCipher.equals("Keyword Permutation Cipher")) {
                String keyword = "HELLO";
                decoded = keywordPermutationDecode(encodedText, keyword);
            }

            table.getItems().clear();
            table.getItems().add(new String[]{"", encodedText, decoded});
        });

        VBox vbox = new VBox(10, inputLabel, inputField, cipherComboBox, encodeButton, decodeButton, table);
        vbox.setPadding(new Insets(15));

        Scene scene = new Scene(vbox, 800, 500);
        primaryStage.setTitle("Ciphers Showcase");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Rail Fence Cipher
    private String railFenceEncode(String text, int rails) {
        StringBuilder[] fence = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) fence[i] = new StringBuilder();

        int rail = 0;
        boolean down = true;

        for (char c : text.toCharArray()) {
            fence[rail].append(c);
            if (down) {
                rail++;
                if (rail == rails) {
                    down = false;
                    rail = rails - 2;
                }
            } else {
                rail--;
                if (rail < 0) {
                    down = true;
                    rail = 1;
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : fence) result.append(sb);
        return result.toString();
    }

    private String railFenceDecode(String text, int rails) {
        int[] pattern = new int[text.length()];
        int rail = 0;
        boolean down = true;
        for (int i = 0; i < text.length(); i++) {
            pattern[i] = rail;
            if (down) {
                rail++;
                if (rail == rails) {
                    down = false;
                    rail = rails - 2;
                }
            } else {
                rail--;
                if (rail < 0) {
                    down = true;
                    rail = 1;
                }
            }
        }
        int[] railLens = new int[rails];
        for (int r : pattern)
            railLens[r]++;

        String[] railStrs = new String[rails];
        int pos = 0;
        for (int i = 0; i < rails; i++) {
            railStrs[i] = text.substring(pos, pos + railLens[i]);
            pos += railLens[i];
        }

        int[] railPos = new int[rails];
        StringBuilder result = new StringBuilder();
        for (int r : pattern) {
            result.append(railStrs[r].charAt(railPos[r]++));
        }
        return result.toString();
    }

    // Matrix Rotation Cipher
    private String matrixRotationEncode(String text) {
        int size = (int) Math.ceil(Math.sqrt(text.length()));
        char[][] matrix = new char[size][size];
        Arrays.stream(matrix).forEach(row -> Arrays.fill(row, ' '));

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (index < text.length()) matrix[i][j] = text.charAt(index++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int j = 0; j < size; j++) {
            for (int i = size - 1; i >= 0; i--) {
                result.append(matrix[i][j]);
            }
        }
        return result.toString();
    }

    private String matrixRotationDecode(String text) {
        int size = (int) Math.ceil(Math.sqrt(text.length()));
        char[][] matrix = new char[size][size];
        Arrays.stream(matrix).forEach(row -> Arrays.fill(row, ' '));

        int index = 0;
        for (int j = 0; j < size; j++) {
            for (int i = size - 1; i >= 0; i--) {
                if (index < text.length()) matrix[i][j] = text.charAt(index++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] != ' ') result.append(matrix[i][j]);
            }
        }
        return result.toString();
    }

    // Morse Shift Cipher
    Map<Character, String> morse = Map.ofEntries(
            Map.entry('A', ".-"),
            Map.entry('B', "-..."),
            Map.entry('C', "-.-."),
            Map.entry('D', "-.."),
            Map.entry('E', "."),
            Map.entry('F', "..-."),
            Map.entry('G', "--."),
            Map.entry('H', "...."),
            Map.entry('I', ".."),
            Map.entry('J', ".---"),
            Map.entry('K', "-.-"),
            Map.entry('L', ".-.."),
            Map.entry('M', "--"),
            Map.entry('N', "-."),
            Map.entry('O', "---"),
            Map.entry('P', ".--."),
            Map.entry('Q', "--.-"),
            Map.entry('R', ".-."),
            Map.entry('S', "..."),
            Map.entry('T', "-"),
            Map.entry('U', "..-"),
            Map.entry('V', "...-"),
            Map.entry('W', ".--"),
            Map.entry('X', "-..-"),
            Map.entry('Y', "-.--"),
            Map.entry('Z', "--.."),
            Map.entry('0', "-----"),
            Map.entry('1', ".----"),
            Map.entry('2', "..---"),
            Map.entry('3', "...--"),
            Map.entry('4', "....-"),
            Map.entry('5', "....."),
            Map.entry('6', "-...."),
            Map.entry('7', "--..."),
            Map.entry('8', "---.."),
            Map.entry('9', "----.")
    );

    private String morseShiftEncode(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (morse.containsKey(c)) {
                String code = morse.get(c);
                sb.append(code.replace('.', 'X').replace('-', '.').replace('X', '-')).append(' ');
            }
        }
        return sb.toString().trim();
    }

    private String morseShiftDecode(String text) {
        String[] codes = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String code : codes) {
            String shifted = code.replace('-', 'X').replace('.', '-').replace('X', '.');
            for (Map.Entry<Character, String> entry : morse.entrySet()) {
                if (entry.getValue().equals(shifted)) {
                    sb.append(entry.getKey());
                    break;
                }
            }
        }
        return sb.toString();
    }

    // Keyword Permutation Cipher
    private String keywordPermutationEncode(String text, String keyword) {
        text = text.replaceAll("[^A-Z]", "").toUpperCase(); // Remove non-alphabetic chars
        keyword = keyword.toUpperCase(); // Ensure the keyword is uppercase

        int groupSize = keyword.length();
        StringBuilder sb = new StringBuilder();

        // Sort the keyword and build a map of original positions
        char[] sortedKey = keyword.toCharArray();
        char[] originalKey = keyword.toCharArray();
        Arrays.sort(sortedKey);

        Map<Character, Integer> keyOrder = new HashMap<>();
        for (int i = 0; i < keyword.length(); i++) {
            keyOrder.put(originalKey[i], i);
        }

        // Process the text in chunks of size `groupSize`
        for (int i = 0; i < text.length(); i += groupSize) {
            // Get the chunk of text (handles the case where the chunk is smaller than the group size)
            String chunk = text.substring(i, Math.min(i + groupSize, text.length()));
            char[] chunkArray = new char[groupSize];
            Arrays.fill(chunkArray, 'X'); // Fill with placeholder if chunk is smaller than the group size

            // Fill the chunk array with the characters from the current chunk
            for (int j = 0; j < chunk.length(); j++) {
                chunkArray[j] = chunk.charAt(j);
            }

            // Create the encoded chunk based on sorted key order
            char[] encodedChunk = new char[groupSize];
            for (int j = 0; j < groupSize; j++) {
                if (j < chunk.length()) {
                    int pos = keyOrder.get(sortedKey[j]); // Get the position of the sorted keyword char
                    encodedChunk[pos] = chunkArray[j]; // Place the character in the correct position
                }
            }

            sb.append(new String(encodedChunk)); // Append the encoded chunk to the result
        }

        return sb.toString();
    }

    private String keywordPermutationDecode(String text, String keyword) {
        text = text.replaceAll("[^A-Z]", "").toUpperCase(); // Remove non-alphabetic chars
        keyword = keyword.toUpperCase(); // Ensure the keyword is uppercase

        int groupSize = keyword.length();
        StringBuilder sb = new StringBuilder();

        // Sort the keyword and build a map of original positions
        char[] sortedKey = keyword.toCharArray();
        char[] originalKey = keyword.toCharArray();
        Arrays.sort(sortedKey);

        Map<Character, Integer> keyOrder = new HashMap<>();
        for (int i = 0; i < keyword.length(); i++) {
            keyOrder.put(sortedKey[i], i);
        }

        // Process the text in chunks of size `groupSize`
        for (int i = 0; i < text.length(); i += groupSize) {
            String chunk = text.substring(i, Math.min(i + groupSize, text.length()));
            char[] decodedChunk = new char[groupSize];

            // Decode the chunk by placing characters in their original positions
            for (int j = 0; j < groupSize; j++) {
                if (j < chunk.length()) {
                    int pos = keyOrder.get(originalKey[j]); // Get the original position of the character
                    decodedChunk[pos] = chunk.charAt(j); // Place the character in the correct position
                }
            }

            sb.append(new String(decodedChunk)); // Append the decoded chunk to the result
        }

        return sb.toString();
    }

    public void printRailFenceMatrix(String text, int rails) {
        // Створюємо порожню матрицю (всі клітинки спочатку пусті)
        char[][] matrix = new char[rails][text.length()];
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                matrix[i][j] = '.'; // Заповнюємо порожні клітинки точками
            }
        }

        // Моделюємо рух по рейках
        int rail = 0;
        boolean down = true;
        for (int i = 0; i < text.length(); i++) {
            matrix[rail][i] = text.charAt(i); // Записуємо символ на поточну рейку
            if (down) {
                rail++;
                if (rail == rails) {
                    down = false;
                    rail = rails - 2;
                }
            } else {
                rail--;
                if (rail < 0) {
                    down = true;
                    rail = 1;
                }
            }
        }

        // Виводимо матрицю
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < text.length(); j++) {
                System.out.print(matrix[i][j] + " "); // Виводимо рядок
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}