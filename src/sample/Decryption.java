package sample;//расшифровка файла, зашифрованного шифром Цезаря
//ключ известен

import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Decryption {
    static void decryptionMethod(List <Character> criptoAlphabet, String path_scr2, String path_dest2, int key) throws IOException {
        System.out.println(path_scr2);
        System.out.println(path_dest2);
        System.out.println(key);
        //меню расшифровки
        StringBuilder sb = new StringBuilder();

        //чтение данных из файла в массив
        char[] symbols = FileReader.fileReader(path_scr2);

        //расшифровка данных, ключ известен
        for (char symbol: symbols) {
            if(!criptoAlphabet.contains(Character.toLowerCase(symbol))){
                sb.append(symbol);
            }
            else {
                if(Character.isUpperCase(symbol)){
                    int b = criptoAlphabet.indexOf(Character.toLowerCase(symbol)) - key;
                    if (b < 0) {
                        sb.append(criptoAlphabet.get(criptoAlphabet.size() - Math.abs(Character.toUpperCase(b))));
                    }
                    else {
                        sb.append(Character.toUpperCase((Character) criptoAlphabet.get(b % criptoAlphabet.size()))); }
                    }
                else {
                    int b = criptoAlphabet.indexOf(symbol) - key;
                    if (b < 0) {
                        sb.append(criptoAlphabet.get(criptoAlphabet.size() - Math.abs(b)));
                    } else {
                        sb.append(criptoAlphabet.get(b % criptoAlphabet.size()));
                    }
                }
            }
        }

        //запись расшифрованных данных в файл
        String str = sb.toString();
        FileWriter.fileWriter(path_dest2, str);
        System.out.println("Файл успешно расшифрован!!!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно расшифрован!!!");
        alert.showAndWait();
    }
}



