import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * Lineker Aguiar Alcântara
 * Jamisson Jader Moraes Pereira Junior
 */
/**
 *
 * @author aluno
 */
public class MainClass {

    static String charToString(int readChar){
        if (readChar == -1) {
            return "error";
        }
        return "" + (char) readChar;
    }

    public static void main(String[] args) {
        final String fileName = "MyCode.txt";
        String number = "[0-9]";
        String letter = "[a-zA-Z]";
        String operator = "[-+*/=]";

        try {
            var MyFileObject = new FileInputStream(fileName);
            int readData;
            String myData = " ";
            String outData;
            String token;
            Boolean eof = false;

            while (!eof) {
                outData = "";
                token = "";

                if(myData.matches("\\s")){
                    readData = MyFileObject.read();
                    myData = charToString(readData);
                }

                while (myData.matches(number)) {
                    token = "number";
                    outData = outData + myData;

                    readData = MyFileObject.read();
                    myData = charToString(readData);
                }

                if (myData.matches(letter)) {
                    token = "id";

                    while (myData.matches(number) || myData.matches(letter)) {
                        outData = outData + myData;

                        readData = MyFileObject.read();
                        myData = charToString(readData);
                    }
                }

                if(token == "") {
                    while (myData.matches(operator)) {
                        token = "operator";
                        outData = outData + myData;

                        readData = MyFileObject.read();
                        myData = charToString(readData);
                    }
                }

                if(token.matches("number")){
                    System.out.println("<number," + outData + ">");
                }
                else if(token.matches("id")){
                    System.out.println("<id," + outData + ">");
                }
                else if(token.matches("operator")){
                    System.out.println("<op," + outData + ">");
                }

                if (myData == "error") {
                    eof = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}

