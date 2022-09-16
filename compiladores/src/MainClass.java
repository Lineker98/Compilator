import java.io.*;
import java.util.Arrays;

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
            return "%";
        }
        return "" + (char) readChar;
    }

    public static void main(String[] args) {
        final String fileName = "MyCode.txt";
        String number = "[0-9]";
        String letter = "[a-zA-Z]";
        String operator = "[-+*/=<!.,&]";
        String delim = "()[]{};";
        String[] coment = {"/*", "*/"};
        String[] decl = {"class","extends","public","static","void","main","length","this","new"};
        String[] fluxo = {"if", "else", "while", "return", "System.out.println"};
        String[] tipo = {"boolean", "int", "true", "false", "String"};
        String exception = "System.out.printl";

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
                    token="*";
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

                    while ((myData.matches(number) || myData.matches(letter) || exception.contains(outData)) && !outData.matches("int")) {
                        outData = outData + myData;

                        readData = MyFileObject.read();
                        myData = charToString(readData);
                        if (myData == "%"){
                            break;
                        }
                    }
                    if(Arrays.asList(decl).contains(outData)){
                        token = "decl";
                    }
                    else if(Arrays.asList(fluxo).contains(outData)){
                        token = "fluxo";
                    }
                    else if(Arrays.asList(tipo).contains(outData)){
                        token = "tipo";
                    }
                }

                if(token == "") {
                    while(myData.matches(operator)) {
                        token = "operator";
                        outData = outData + myData;

                        readData = MyFileObject.read();
                        myData = charToString(readData);
                    }

                    if(Arrays.asList(coment).contains(outData)){
                        token = "coment";
                    }
                }

                if(token == "") {
                    if (delim.contains(myData)) {
                        token = "delim";
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
                else if(token.matches("delim")){
                    System.out.println("<delim," + outData + ">");
                }
                else if(token.matches("coment")){
                    System.out.println("<coment," + outData + ">");
                }
                else if(token.matches("decl")){
                    System.out.println("<decl," + outData + ">");
                }
                else if(token.matches("fluxo")){
                    System.out.println("<fluxo," + outData + ">");
                }
                else if(token.matches("tipo")){
                    System.out.println("<tipo," + outData + ">");
                }

                if (myData == "%" || token == "") {
                    eof = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}

