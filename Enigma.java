import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Enigma{

    public static List<String> codeList;
    public static List<String> alphabet;
    public static Scanner sc;
    

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


        sc=new Scanner(System.in);
        System.out.println("Enter message to cipher : ");
        String message=sc.nextLine();
        System.out.println("Please enter rotor configuration : ");
        String rotorConfiguration=sc.nextLine();

        if(checkParams(message,rotorConfiguration))
            return;

        EnigmaStarter enigmaStarter =new EnigmaStarter(rotorList,rotorConfiguration,message);
        enigmaStarter.decode();
    
    }

    public static boolean checkParams(String message,String rotorConfiguration){
        //checking message and rotor configuration for any unsupported characters 
        Pattern pattern = Pattern.compile("[^A-Za-z]");
        Matcher matcherForMessage = pattern.matcher(message);
        Matcher matcherForRotor= pattern.matcher(rotorConfiguration);
        boolean messageWrong=matcherForMessage.find();
        boolean rotorConfigWrong=matcherForRotor.find();
        boolean permission=false;
        if(messageWrong)
            System.out.println("Message format is not supported");
        
        if(rotorConfigWrong)
            System.out.println("Rotor configuration format is not supported");
        if(rotorConfiguration.length() != 3){
            rotorConfigWrong=true;
            System.out.println("Rotor configuration is too long expected -> 3");
        }
        permission= (messageWrong | rotorConfigWrong);
        return permission;
    }



    public static void printer(){                // function to provide list.add code for this code            
        String code = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        StringBuilder codeR=new StringBuilder(code);
        StringBuilder printValue=new StringBuilder();
        for(int i=0;i<codeR.length();i++){
            System.out.print("reflectorBList.add(\""+codeR.charAt(i)+"\");");
        }
    }

}