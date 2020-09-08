import java.util.*;
public class EnigmaStarter{
//Debug Version
    List<Rotor> rotorList;
    String rotorStartPosition;
    String sentence;
    List<String> alphabetList;
    List<String> reflectorBList;
    int[] generalRotorPositions;
    StringBuilder decoded;
    

    EnigmaStarter(List<Rotor> rotorList,String rotorStartPosition,String sentence){

        generalRotorPositions=new int[]{0,0,0};// Rotor 1,2,3

        this.rotorList=rotorList;
        StringBuilder sb=new StringBuilder(rotorStartPosition);//Translated to "our language" no need to worry  
        this.rotorStartPosition=sb.toString();        // Reversing rotor start position for conviniance
        reflectorBList=new ArrayList<String>();
        reflectorBList.add("Y");reflectorBList.add("R");reflectorBList.add("U");reflectorBList.add("H");reflectorBList.add("Q");reflectorBList.add("S");reflectorBList.add("L");reflectorBList.add("D");reflectorBList.add("P");reflectorBList.add("X");reflectorBList.add("N");reflectorBList.add("G");reflectorBList.add("O");reflectorBList.add("K");reflectorBList.add("M");reflectorBList.add("I");reflectorBList.add("E");reflectorBList.add("B");reflectorBList.add("F");reflectorBList.add("Z");reflectorBList.add("C");reflectorBList.add("W");reflectorBList.add("V");reflectorBList.add("J");reflectorBList.add("A");reflectorBList.add("T");
        this.sentence=sentence;
        System.out.println(sentence+"is Sentence "+sentence.length());
        alphabetList=new ArrayList<String>();
        alphabetList.add("A");alphabetList.add("B");alphabetList.add("C");alphabetList.add("D");alphabetList.add("E");alphabetList.add("F");alphabetList.add("G");alphabetList.add("H");alphabetList.add("I");alphabetList.add("J");alphabetList.add("K");alphabetList.add("L");alphabetList.add("M");alphabetList.add("N");alphabetList.add("O");alphabetList.add("P");alphabetList.add("Q");alphabetList.add("R");alphabetList.add("S");alphabetList.add("T");alphabetList.add("U");alphabetList.add("V");alphabetList.add("W");alphabetList.add("X");alphabetList.add("Y");alphabetList.add("Z");
        decoded=new StringBuilder();
        generalRotorPositionConfuguration(); // Setting generalRotorPosition array according to rotor position input

}

public void decode(){ //start
    rotorConfiguration();

    for(int i=0;i<sentence.length();i++){ //Started to "press" letters 
        String letter=String.valueOf(sentence.charAt(i));
        System.out.println("Letter in "+letter);
        System.out.println("Current rotor config:"+generalRotorPositions[0]+" "+generalRotorPositions[1]+" "+generalRotorPositions[2]+" Confuguration:"+rotorList.get(0).rotorConfig);
        click(0);
        for(int a=0;a<rotorList.size();a++){ //going through rotors{0,1,2}
            letter=forwardFind(rotorList.get(a),letter,a);
        }
        System.out.println("********** Letter -> Reflect : "+letter);
        letter=reflectorfind(letter);
        for(int a=2;a>-1;a--){ //going through rotors reverse {2,1,0}
            letter=reverseFind(rotorList.get(a),letter,a);
        }
        System.out.println("********** Letter -> Out : "+letter);
        System.out.println("Current rotor config:"+generalRotorPositions[0]+" "+generalRotorPositions[1]+" "+generalRotorPositions[2]+" Confuguration:"+rotorList.get(0).rotorConfig);
        decoded.append(letter);
}
System.out.println("DECODED : "+decoded.toString()+" -----------------------------------------");
System.out.println("DECODED  :"+sentence.toString()+" -----------------------------------------");
System.out.println("Current rotor config:"+generalRotorPositions[0]+" "+generalRotorPositions[1]+" "+generalRotorPositions[2]+" Confuguration:"+rotorList.get(0).rotorConfig);





}//End
public int giveCircularIndex(int index,int size){ //array size = 26 input=26,27,-1 ouput=0,1,25
    //int size=26;
    if(index<0){
        index=size+index;
    }else if(index>size){
        index=index-size;
    }else if(index==size){
        index=0;
    }
    return index;

}
public String reverseFind(Rotor rotor,String letter,int a){

    int position= rotor.rotorConfig.indexOf(letter);//letter = H
    //int positionOfCode=alphabetList.indexOf(rotor.rotorConfig.get(position))-generalRotorPositions[a];//finding next letter to send next rotor
    String entryLetter;
    if(a==0){
         entryLetter=alphabetList.get(position);
    }else{
         entryLetter=alphabetList.get(giveCircularIndex(position+generalRotorPositions[a-1],26)); //D
    }
    
    System.out.println("Position : "+position+" entryLetter :"+entryLetter+"Rotor number"+a);
    return entryLetter;
}
public String reflectorfind(String letter){ //-1
    int alphaposition=alphabetList.indexOf(letter);
    String reflected= reflectorBList.get(alphaposition);
    reflected=alphabetList.get(giveCircularIndex(alphabetList.indexOf(reflected)+generalRotorPositions[generalRotorPositions.length-1],26));
    
    System.out.println("Reflected : "+reflected);
    return reflected;
}
public String forwardFind(Rotor rotor,String letter,int a){

    int position=alphabetList.indexOf(String.valueOf(letter)); 
    int positionOfCode=alphabetList.indexOf(rotor.rotorConfig.get(position))-generalRotorPositions[a];//finding next letter to send next rotor 
    String codeLetter=alphabetList.get(giveCircularIndex(positionOfCode,26)); //J
    System.out.println("Position : "+position+" positionOfCode :"+positionOfCode+" CodeLetter :"+codeLetter);
    return codeLetter;
}

public void generalRotorPositionConfuguration(){
    for(int i=0;i<rotorStartPosition.length();i++){
        String position= String.valueOf(rotorStartPosition.charAt(i));
        int rotorposition= alphabetList.indexOf(position);
        if(i+1==2){
            if(position.equals("E")){// !!! Second rotor special here
                turnover=true;
            }
        }
        generalRotorPositions[i]=rotorposition;
}
System.out.println("Rotor config is: "+generalRotorPositions[0]+" "+generalRotorPositions[1]+" "+generalRotorPositions[2]);
}

public void rotorConfiguration(){  // Changing rotorConfig param inside rotor object according to rotor start position ("AAA")
    for(int i=0;i<rotorList.size();i++){
        Rotor rotor = rotorList.get(i);   // 0 = 1 Rotor
        rotor.rotorConfig=getPositionedArray(rotor.rotorConfig,String.valueOf(rotorStartPosition.charAt(i)));
        System.out.println("Rotor number :"+rotor.rotorNumber+" Rotor Config is "+rotor.rotorConfig);
    }
}//End

public List<String> getPositionedArray(List<String> code,String position){   // Changing rotorConfig param inside rotor object according to rotor start position ("A")
    int posAlpha=alphabetList.indexOf(position);
    if(posAlpha==0){
        return code;
    }
System.out.println("Position index is "+posAlpha);
for(int i=0;i<posAlpha;i++){
    code.add(code.get(0));
    code.remove(0);
}
return code;
}//End
boolean turnover=false;
public void click(int currentRotorNumber){ //Handles all possible gear actions. Needed to call after reading each letter.
    System.out.println("Current__"+generalRotorPositions[currentRotorNumber]);
    int currentRealRNumber=rotorList.get(currentRotorNumber).rotorNumber;
    System.out.println("------------------------------------------------------------------------------------------------------------"+currentRealRNumber);
       generalRotorPositions[currentRotorNumber]=generalRotorPositions[currentRotorNumber]+1; // {1,0,0}

       if(generalRotorPositions[currentRotorNumber]==26){   //Current rotor done one turn IMDEX !!!
           generalRotorPositions[currentRotorNumber]=0; //Resetting current rotor position and after clicking nect rotor
       
        }
        
        
        if(currentRotorNumber+1==1){
            if(turnover){
                click(currentRotorNumber+1);
                turnover=false;
            }
            if(generalRotorPositions[currentRotorNumber]==17){   //Current rotor done one turn IMDEX !!!
                
                     click(currentRotorNumber+1);
                     
                     System.out.println("SEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE !"+generalRotorPositions[currentRotorNumber]);
                
                
             }
        } if(currentRotorNumber+1==2){
            if(generalRotorPositions[currentRotorNumber]==4){
                turnover=true;
                System.out.println("Turnover /*/*/*/*/*/*/*/ "+alphabetList.get(generalRotorPositions[currentRotorNumber]));
            }
           // System.out.println("ENTERED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+generalRotorPositions[currentRotorNumber]);
            if(generalRotorPositions[currentRotorNumber]==5){   //Current rotor done one turn IMDEX !!!
               
                     click(currentRotorNumber+1);
                     System.out.println("ENTERED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+generalRotorPositions[currentRotorNumber]);
                
                
             }

        } if(currentRotorNumber+1==3){
            if(generalRotorPositions[currentRotorNumber]==21){   //Current rotor done one turn IMDEX !!!
               
                
                // click(currentRotorNumber+1);  Third rotor special !! need to config here right way if want to change rotor places randomly
                
           
           
        }
        }
       List<String> listA =rotorList.get(currentRotorNumber).rotorConfig;
       String temp=listA.get(0);
       listA.remove(0);
       listA.add(temp);
       rotorList.get(currentRotorNumber).rotorConfig=listA; // Setting new code config after click
    }



}