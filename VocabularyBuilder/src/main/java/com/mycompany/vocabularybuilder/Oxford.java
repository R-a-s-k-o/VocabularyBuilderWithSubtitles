package com.mycompany.vocabularybuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Oxford{
    public String oxfordReturn(String word,String app_id, String app_key){
        
        final String language = "en-gb";
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        final String restUrl = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
        
                
        try{
            
            URL url = new URL(restUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);
            
            
            //read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            
            String line = null;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            
            }
                return stringBuilder.toString();
        
        
        
        
        }catch (IOException e){
        
            e.printStackTrace();
            return "";
        }
    }
    
     public String returnMeaning(String responseOfApi){
         
         /*
           wherever its used, try and catch should be added if word is not found, pure word should be manually
         typed into wordTextField
         
         
         */
        
        
        JSONObject jsonObject = new JSONObject(responseOfApi);
        String meaning;
        meaning = jsonObject.getJSONArray("results")
                .getJSONObject(0).getJSONArray("lexicalEntries")
                .getJSONObject(0).getJSONArray("entries")
                .getJSONObject(0).getJSONArray("senses")
                .getJSONObject(0).getJSONArray("definitions").getString(0);
                
                
        
       return meaning;
    
    }
     
     public String returnType(String responseOfApi){
         
           /*
          should be in the same try-and-catch block with returnMeaning
         
         
         */
        
        
        JSONObject jsonObject = new JSONObject(responseOfApi);
        String type;
        type = jsonObject.getJSONArray("results")
                .getJSONObject(0).getJSONArray("lexicalEntries")
                .getJSONObject(0).getJSONObject("lexicalCategory")
                .getString("id");
                
       return type;
    
    }



}