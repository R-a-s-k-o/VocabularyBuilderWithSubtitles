package com.mycompany.vocabularybuilder;

import java.util.ArrayList;

public class WordProcessing {
    
    public ArrayList<String> timeAndSentencesSeperator(String content){
     /*
    content is the subtitle file.
    sentences are collected in sentenceList
    time lines in the sub file are collected in timeList
    then these two ArrayList summed like summing an array --> sum = time + sentence
    */
     
        String htmlFilter = "\\<.*?\\>";
        content = content.replaceAll(htmlFilter, "");
        String splittedContent[] = content.split("\n");                
                int index = -1;                
                String sentence = "";          
                
                ArrayList<String> sentenceList = new ArrayList<>();
                ArrayList<String> timeList = new ArrayList<>();
                ArrayList<String> sumOfSentenceListAndTimeList = new ArrayList<>();
                                
                
                for(String element: splittedContent){    
                    
                    if(!element.isBlank() && !element.matches("[0-9]+")){
                        if(element.contains("-->")){
                            if(index != -1){
                            //System.out.println(index + " : " + sentence);
                            sentenceList.add(sentence);
                            }
                            index = index +1 ;
                            sentence = "";
                            //System.out.println("Time : " + Arrays.toString(element.split("-->",2)));                          
                            String time = element;
                            //System.out.println(time);
                            timeList.add(time);
                        }else{
                            sentence +=  " " + element.toLowerCase();
                        }
                    }
                }
                sentenceList.add(sentence);
                
                for(int i = 0; i <=  (2*timeList.size()-1) ; i++){
                    if(i <= timeList.size()-1){
                        sumOfSentenceListAndTimeList.add(i, timeList.get(i));
                    }else{
                        sumOfSentenceListAndTimeList.add(i,sentenceList.get(i-timeList.size()));
                    }
                }
        return sumOfSentenceListAndTimeList;
                
                
                
        
    
    
    
    }
  
    public ArrayList<String> getBeginningTimeList (ArrayList sumOfSentenceListAndTimeList){
        ArrayList<String> beginningTimeList = new ArrayList<>();
        
        for(int i = 0; i <= sumOfSentenceListAndTimeList.size()/2 - 1 ; i++ ){
            
           
            String[] splittedText = sumOfSentenceListAndTimeList.get(i).toString().split("-->", 2);
            beginningTimeList.add(splittedText[0].replace(" ", ""));
            
        }
        
    
    
       return beginningTimeList;
    }
    
    public ArrayList<String> getEndingTimeList (ArrayList sumOfSentenceListAndTimeList){
        ArrayList<String> endingTimeList = new ArrayList<>();
        
        for(int i = 0; i <= sumOfSentenceListAndTimeList.size()/2 - 1 ; i++ ){
            
           
            String[] splittedText = sumOfSentenceListAndTimeList.get(i).toString().split("-->", 2);
            endingTimeList.add(splittedText[1].replace(" ", ""));
            
        }
        
    
    
       return endingTimeList;
    }

    public ArrayList<String> getSentenceList(ArrayList sumOfSentenceListAndTimeList){
        
        ArrayList<String> sentenceList = new ArrayList<>();
        int beginningIndex = sumOfSentenceListAndTimeList.size()/2;
        int endingIndex = sumOfSentenceListAndTimeList.size();
        
        for(int i = beginningIndex; i < endingIndex ; i++ ){
            
            String splittedText = sumOfSentenceListAndTimeList.get(i).toString();
            if(splittedText.length() >= 1){
                sentenceList.add(splittedText.substring(1));
            
            }else{
                sentenceList.add(splittedText);
            }
            
            
        
        
        }
    
        return sentenceList;
    }

    public ArrayList<String> sentenceToWords (String sentence){
    
        ArrayList<String> words = new ArrayList<>();
        String grammarFilter = "'s|'ll|'ve|'m|'re|'d|n't|'S|'LL|'VE|'M|'RE|'D|N'T|\\W";
        // things shouldn't be filtered !!! : can't, won't, ain't 
        
        
        
        String[] wordArray = sentence.split(" ");
        for(String x : wordArray){
            switch(x){
                case "can't":
                    words.add("can");
                    break;
                case "CAN'T":
                    words.add("CAN");
                    break;
                case "won't":
                    words.add("won");
                    break;
                case "WON'T":
                    words.add("WON");
                    break;
                case "ain't":
                    words.add(x);
                    break;
                case "AIN'T":
                    words.add(x);
                    break;
                default:
                    words.add(x.replaceAll(grammarFilter, ""));
            
            }
                    
           
            
        }
        //
        
        
        return words;
    
    
    }
}
