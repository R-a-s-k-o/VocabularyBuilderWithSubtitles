
package com.mycompany.vocabularybuilder;

import java.security.spec.NamedParameterSpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;





public class DatabaseQueries {
    
    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement ps = null;
    
    public DatabaseQueries(){
        
        try {
            con = DriverManager.getConnection(Database.url);
            System.out.println("db connection is ok");
            
            
            
        } catch (SQLException ex) {
            System.out.println("Couldn't connected to database");
            
        
        }
    
    }
    
    public boolean isIncludedInDb(String word){
        String query = "SELECT * FROM words WHERE word = ?";
        
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, word);
            return ps.executeQuery().next();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
        
        



    }
    
    public void wordSaver(String word, String meaning, String sentence, String startingTime, String endingTime,
            String isTvSeries, String seasonAndEpisode, String type, String learned, String nameOfTvsOrMovie
            ){
    
        try {
            String query = "INSERT INTO \"words\" (\"word\",\"meaning\",\"sentence\",\"startingTime\",\"endingTime\",\"isTvSeries\",\"seasonAndEpisode\",\"type\",\"learned\",\"nameOfTvsOrMovie\") VALUES (?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, word);
            ps.setString(2, meaning);
            ps.setString(3, sentence);
            ps.setString(4, startingTime);
            ps.setString(5, endingTime);
            ps.setString(6, isTvSeries);
            ps.setString(7, seasonAndEpisode);
            ps.setString(8, type);
            ps.setString(9, learned);
            ps.setString(10, nameOfTvsOrMovie);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int wordCounter(boolean isMeaningEmpty, int ifLearned){
        /*
        ifLearned = 1 for the words are learned/known
        ifLearned = 0 for the words are not learned/known
        */
            
            if(!isMeaningEmpty){
                try {
                    String query = "Select * From words where meaning !='' AND learned = ? ";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, ifLearned);                    
                    ResultSet rs = ps.executeQuery();
                    int count = 0;
                    while(rs.next()){
                        count++;
                    
                    }
                    return count;
                    
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
            
            
            
            }else{
                try {
                    
                    String query = "Select * From words where meaning ='' AND learned = ? ";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, ifLearned);
                    ResultSet rs = ps.executeQuery();
                    int count = 0;
                    while(rs.next()){
                        count++;
                    }
                    return count;
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
                    return -1;
                }
            
            
            
            }
        
        
        
        }

    public HashSet returnTvsOrMovieNames(){
        HashSet<String> namesofMovies = new HashSet<>();
        
        try {
            String query = "SELECT nameOfTvsOrMovie FROM words";
            ps = con.prepareStatement(query);            
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                namesofMovies.add(rs.getString("nameOfTvsOrMovie"));
                   
            }
            return namesofMovies;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public HashSet returnSeasonAndEpisodes(String nameOfTvs){
        HashSet<String> seasonAndEpisodes = new HashSet<>();
        
        try {
            String query = "SELECT seasonAndEpisode FROM words where nameOfTVsOrMovie = ? ";
            ps = con.prepareStatement(query); 
            ps.setString(1, nameOfTvs);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                seasonAndEpisodes.add(rs.getString("seasonAndEpisode"));
            }
            return seasonAndEpisodes;
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList returnIdForStudying(int learned1, int learned2, String nameOfTvsOrMovie, String seasonAndEpisode){
        try {
            ArrayList<Integer> id = new ArrayList<>();
            String query = "SELECT id FROM words where (learned = ? OR learned = ?) "
                    + "AND nameOfTVsOrMovie = ? AND seasonAndEpisode = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, learned1);
            ps.setInt(2, learned2);
            ps.setString(3, nameOfTvsOrMovie);
            ps.setString(4, seasonAndEpisode);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id.add(rs.getInt("id"));
            }
            
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
        
    }
    
    public ArrayList returnIdOfAllEpisodesForStudying(int learned1, int learned2, String nameOfTvsOrMovie){
        try {
            ArrayList<Integer> id = new ArrayList<>();
            String query = "SELECT id FROM words where (learned = ? OR learned = ?) "
                    + "AND nameOfTVsOrMovie = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, learned1);
            ps.setInt(2, learned2);
            ps.setString(3, nameOfTvsOrMovie);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id.add(rs.getInt("id"));
            }
            
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
        
    }
    
    public ArrayList returnIdForAllMoviesAndTvsStudying(int learned1, int learned2){
        try {
            ArrayList<Integer> id = new ArrayList<>();
            String query = "SELECT id FROM words where (learned = ? OR learned = ?) ";
            ps = con.prepareStatement(query);
            ps.setInt(1, learned1);
            ps.setInt(2, learned2);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id.add(rs.getInt("id"));
            }
            
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
        
    }
    
    
    public String returnWordForStudying(int id){
        try {
            String query = "SELECT word FROM words where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String word = rs.getString("word");
            
            
            return word;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String returnSentenceForStudying(int id){
        try {
            String query = "SELECT sentence FROM words where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String sentence = rs.getString("sentence");
            
            
            return sentence;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String returnMeaningForStudying(int id){
        try {
            String query = "SELECT meaning FROM words where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String meaning = rs.getString("meaning");
            
            
            return meaning;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int returnIfLearnedForStudying(int id){
        try {
            String query = "SELECT learned FROM words where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int learned = rs.getInt("learned");
            
            
            return learned;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void updateLearned(int id,int learned){
        try {
            /*
            if the word is learned input should be 1
            if the word isn't learned input should be 0
            */
            String query = "Update words SET learned = ? where id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, learned);
            ps.setInt(2, id);
            ps.executeUpdate();
            //int rs = ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseQueries.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    
    }
} 
