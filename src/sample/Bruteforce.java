package sample;//Брутфорс
//Ключ неизвестен
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bruteforce {
    static void decryptionWithoutKey(List <Character> criptoAlphabet, String path_scr2, String path_dest2) throws IOException {

        //чтение данных из файла в массив
        char[] symbols = FileReader.fileReader(path_scr2);

        StringBuilder sb_temp = new StringBuilder();
        int count_temp = 0;
        int bruteforceKey = 0;


        //перебор криптоключей
        for (int brutekey = 0; brutekey < criptoAlphabet.size(); brutekey++) {
            StringBuilder sb = new StringBuilder();
            List<Character> listChars = new ArrayList<>();
            int count = 0;
            char ch = '\u0000';

            //расшифровка данных
            for (char symbol : symbols) {
                if (!criptoAlphabet.contains(Character.toLowerCase(symbol))) {
                    listChars.add(symbol);
                }
                else {
                    if (Character.isUpperCase(symbol)) {
                        int b = criptoAlphabet.indexOf(Character.toLowerCase(symbol)) - brutekey;
                        if (b < 0) {
                            ch = Character.toUpperCase (criptoAlphabet.get(criptoAlphabet.size() - Math.abs((b))));
                        } else {
                            ch = Character.toUpperCase(criptoAlphabet.get(b % criptoAlphabet.size()));
                        }
                        listChars.add(ch);
                    } else {
                        int b = criptoAlphabet.indexOf(symbol) - brutekey;
                        if (b < 0) {
                            ch = criptoAlphabet.get(criptoAlphabet.size() - Math.abs(b));
                            listChars.add(ch);
                        } else {
                            ch = criptoAlphabet.get(b % criptoAlphabet.size());
                            listChars.add(ch);
                        }
                    }
                }
            }

            //валидность расшифрованных данных
            List<Character> stopSymbolsList = new ArrayList<>(Arrays.asList('ъ', 'Ъ', 'ы', 'Ы', 'ь', 'Ь', '(', '.', ':', '-', '!', '?', ' ', '"'));
            List<Character> symbolsList = new ArrayList<>(Arrays.asList('.', '!', '?', ' '));
            char firstSymbol = listChars.get(1);
            char lastSymbols = listChars.get(listChars.size()-1);
            //первый символ не начинается на запрещенный символ?
            if (!stopSymbolsList.contains(firstSymbol)){
                //последний символ входит в массив разрешенных символов?
                if(symbolsList.contains(lastSymbols)){
                    for (char symb: listChars) {
                        //подсчет кол-ва пробелов
                        if (symb == ' '){
                            count ++;
                        }
                        sb.append(symb);
                    }
                    if (count > count_temp){
                        count_temp = count;
                        sb_temp = sb;
                        bruteforceKey = brutekey;
                    }
                }
            }
        }

        //запись данных в файл и вывод на экран криптоключа
        System.out.println("Криптоключ = " + bruteforceKey);
        FileWriter.fileWriter(path_dest2, sb_temp.toString());
        System.out.println("Расшифрованный файл записан!!!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Файл успешно расшифрован!!! Ключ = " + bruteforceKey);
        alert.showAndWait();

        bruteforceKey = 0;
    }
}

