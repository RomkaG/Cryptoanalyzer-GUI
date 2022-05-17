package sample;
//шифрование файла шифром Цезаря
//ключ задаётся

import javafx.scene.control.Alert;

import java.io.*;
import java.util.List;

public class Encryption {
    static void encryptionMethod(List <Character> criptoAlphabet, String path_scr, String path_dest, int key) throws IOException {
        System.out.println(path_scr);
        System.out.println(path_dest);
        System.out.println(key);
        StringBuilder sb = new StringBuilder();

        //чтение данных из файла в массив
        char[] symbols = FileReader.fileReader(path_scr);

        //шифрование данных
        for (char symbol: symbols) {
            if(!criptoAlphabet.contains(Character.toLowerCase(symbol))){
                sb.append(symbol);
            }
            else {
                if(Character.isUpperCase(symbol)){
                    sb.append(Character.toUpperCase((Character) criptoAlphabet.get((criptoAlphabet.indexOf
                            (Character.toLowerCase(symbol))+ key) % criptoAlphabet.size())));
                }
                else {
                    sb.append(criptoAlphabet.get((criptoAlphabet.indexOf(symbol) + key) % criptoAlphabet.size()));
                }
            }
        }
        //запись зашифрованных данных в файл
        String str = sb.toString();
        FileWriter.fileWriter(path_dest, str);
        System.out.println("Файл успешно зашифрован!!!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно зашифрован!!!");
        alert.showAndWait();

    }
}

