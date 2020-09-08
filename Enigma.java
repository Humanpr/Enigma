import java.util.*;
public class Enigma{

    public static List<String> codeList;
    public static List<String> alphabet;
    

    public static void main(String[]args){
        
        codeList=new ArrayList<String>();
        codeList.add("E");codeList.add("K");codeList.add("M");codeList.add("F");codeList.add("L");codeList.add("G");codeList.add("D");codeList.add("Q");codeList.add("V");codeList.add("Z");codeList.add("N");codeList.add("T");codeList.add("O");codeList.add("W");codeList.add("Y");codeList.add("H");codeList.add("X");codeList.add("U");codeList.add("S");codeList.add("P");codeList.add("A");codeList.add("I");codeList.add("B");codeList.add("R");codeList.add("C");codeList.add("J");
        Rotor rotor1=new Rotor(codeList,1);
        codeList=new ArrayList<String>();
        codeList.add("A");codeList.add("J");codeList.add("D");codeList.add("K");codeList.add("S");codeList.add("I");codeList.add("R");codeList.add("U");codeList.add("X");codeList.add("B");codeList.add("L");codeList.add("H");codeList.add("W");codeList.add("T");codeList.add("M");codeList.add("C");codeList.add("Q");codeList.add("G");codeList.add("Z");codeList.add("N");codeList.add("P");codeList.add("Y");codeList.add("F");codeList.add("V");codeList.add("O");codeList.add("E");
        Rotor rotor2=new Rotor(codeList,2);
        codeList=new ArrayList<String>();
        codeList.add("B");codeList.add("D");codeList.add("F");codeList.add("H");codeList.add("J");codeList.add("L");codeList.add("C");codeList.add("P");codeList.add("R");codeList.add("T");codeList.add("X");codeList.add("V");codeList.add("Z");codeList.add("N");codeList.add("Y");codeList.add("E");codeList.add("I");codeList.add("W");codeList.add("G");codeList.add("A");codeList.add("K");codeList.add("M");codeList.add("U");codeList.add("S");codeList.add("Q");codeList.add("O");
        Rotor rotor3=new Rotor(codeList,3);
        List<Rotor> rotorList=new ArrayList<Rotor>();
        rotorList.add(rotor1);
        rotorList.add(rotor2);
        rotorList.add(rotor3);


        
        EnigmaStarter enigmaStarter =new EnigmaStarter(rotorList,"IEM","ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZA");//STUVWXYZA
        enigmaStarter.decode();

       
        
        //printer();


    }

    public static void printer(){                       
        String code = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        StringBuilder codeR=new StringBuilder(code);
        StringBuilder printValue=new StringBuilder();
        for(int i=0;i<codeR.length();i++){
            System.out.print("reflectorBList.add(\""+codeR.charAt(i)+"\");");
        }
    }

}