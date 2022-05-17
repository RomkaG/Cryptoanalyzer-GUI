package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.*;

public class CryptoController {

    //криптоалфавит
    static List<Character> criptoAlphabet = new ArrayList<>(Arrays.asList('а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и',
            'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
            'э', 'ю', 'я', '(', '.', ':', '-', '!', '?', ' ', '"'));

        @FXML
        private Button chooseFile, chooseFile2, brtfrce;
        @FXML
        private Button saveFile, saveFile2;
        @FXML
        private Label pathChooseFile, pathChooseFile2;
        @FXML
        private Label pathSaveFile, pathSaveFile2;
        @FXML
        private Label enterLable, enterLable2;
        @FXML
        private TextField cryptoKey, cryptoKey2;
        @FXML
        private RadioButton decrypt;
        @FXML
        private RadioButton encrypt;
        @FXML
        private Button startEncrypt, startDecrypt;

    @FXML
    public void initialize(){
       /* ToggleGroup tg = new ToggleGroup();
        encrypt.setToggleGroup(tg);
        encrypt.setSelected(true);*/

        //предварительная инициализация кнопок
        chooseFile2.setDisable(true);
        saveFile.setDisable(true);
        cryptoKey2.setDisable(true);
        startDecrypt.setDisable(true);
        enterLable2.setDisable(true);
        decrypt.setSelected(false);
        encrypt.setSelected(true);

        chooseFile.setDisable(false);
        saveFile2.setDisable(false);
        cryptoKey.setDisable(false);
        startEncrypt.setDisable(false);
        enterLable.setDisable(false);
    }

    //выбор кнопки шифровка
    @FXML
    public void chooseEncrypt(){
        chooseFile2.setDisable(true);   //кнопка Меню расшифровки - Выбор файла неактивна
        saveFile.setDisable(true);      //кнопка Меню расшифровки - Выбор сохранения файла неактивна
        cryptoKey2.setDisable(true);    //кнопка Меню расшифровки - Ввод ключа неактивна
        startDecrypt.setDisable(true);  //кнопка Меню расшифровки - Decryption неактивна
        brtfrce.setDisable(true);       //Меню расшифровки - Брутфорс неактивен
        enterLable2.setDisable(true);   //Меню расшифровки - Метка "Введите ключ" неактивна
        decrypt.setSelected(false);     //Радиокнопка Decryption неактивна


        chooseFile.setDisable(false);   //кнопка Меню шифрования - Выбор файла активна
        saveFile2.setDisable(false);    //кнопка Меню шифрования - Выбор сохранения файла активна
        cryptoKey.setDisable(false);    //кнопка Меню шифрования - Ввод ключа активна
        startEncrypt.setDisable(false); //кнопка Меню шифрования - Encryption активна
        enterLable.setDisable(false);   //Меню шифрования - Метка "Введите ключ" активна


    }

    //выбор кнопки расшифровка
    @FXML
    public void chooseDecrypt(){
        chooseFile2.setDisable(false);
        saveFile.setDisable(false);
        cryptoKey2.setDisable(false);
        startDecrypt.setDisable(false);
        brtfrce.setDisable(false);
        enterLable2.setDisable(false);

        chooseFile.setDisable(true);
        saveFile2.setDisable(true);
        cryptoKey.setDisable(true);
        startEncrypt.setDisable(true);
        enterLable.setDisable(true);
        encrypt.setSelected(false);
    }


    //fileChooser
    FileChooser fileChooser = new FileChooser();
    // фильтр *.txt файлов
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
    String path_scr; //путь к файлу, который нужно шифровать
    String path_dest; //путь для хранения шифрованного файла
    String path_scr2; //путь к файлу, который нужно расшифровать
    String path_dest2; //путь для хранения расшифрованного файла
    int key;          //ключ шифрования

//---------Encryption Option------------------------------------------------------------//

    //открытие *.txt файла для шифрования
    @FXML
    public void openFile() {
        fileChooser.getExtensionFilters().add(extFilter);
        // получить выбранный файл
        File file = fileChooser.showOpenDialog(chooseFile.getScene().getWindow());
        if (file != null) {
            pathChooseFile.setText(file.getAbsolutePath() + "  selected");
            path_scr = file.getAbsolutePath();
        }
    }

    //выбор места сохранения зашифрованного файла
    @FXML
    public void saveFile() {
        fileChooser.getExtensionFilters().add(extFilter);
        // получить выбранный файл
        File file = fileChooser.showSaveDialog(saveFile.getScene().getWindow());
        if (file != null) {
            pathSaveFile.setText(file.getAbsolutePath() + "  selected");
            path_dest = file.getAbsolutePath();
        }
    }

    //чтение ключа для шифрования
    @FXML
    public void setKey(){
        String str = cryptoKey.getText();
        key = Integer.parseInt(str);
    }

    //чтение ключа для расшифрования
    @FXML
    public void setKey2(){
        String str = cryptoKey2.getText();
        key = Integer.parseInt(str);
    }

    //запуск шифрования
    @FXML
    public void startEncryption() throws IOException {
        String str = cryptoKey.getText();
        key = Integer.parseInt(str);
        Encryption.encryptionMethod(criptoAlphabet, path_scr,path_dest,key);
    }
//---------Decryption Option------------------------------------------------------------------//
//открытие *.txt файла для шифрования
@FXML
public void openFile2() {
    fileChooser.getExtensionFilters().add(extFilter);
    // получить выбранный файл
    File file = fileChooser.showOpenDialog(chooseFile2.getScene().getWindow());
    if (file != null) {
        pathChooseFile2.setText(file.getAbsolutePath() + "  selected");
        path_scr2 = file.getAbsolutePath();
    }
}

    //выбор места сохранения зашифрованного файла
    @FXML
    public void saveFile2() {
        fileChooser.getExtensionFilters().add(extFilter);
        // получить выбранный файл
        File file = fileChooser.showSaveDialog(saveFile2.getScene().getWindow());
        if (file != null) {
            pathSaveFile2.setText(file.getAbsolutePath() + "  selected");
            path_dest2= file.getAbsolutePath();
        }
    }

    //запуск дешифрования
    @FXML
    public void startDecryption() throws IOException {
            String str = cryptoKey2.getText();
            key = Integer.parseInt(str);
            Decryption.decryptionMethod(criptoAlphabet, path_scr2,path_dest2,key);
    }

    //запуск брутфорса
    public void startBruteforce() throws IOException {
        Bruteforce.decryptionWithoutKey(criptoAlphabet, path_scr2, path_dest2);
    }
}
