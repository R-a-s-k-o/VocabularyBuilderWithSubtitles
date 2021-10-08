/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vocabularybuilder;

//
public class Words {
    private int id;
    private String word;
    private String sentence;
    private String startingTime;
    private String endingTime;
    private String tvSeriesOrMovie;
    private String seasonAndEpisode;
    private String type;
    private int learned;

    public Words(int id, String word, String sentence, String startingTime, String endingTime, String tvSeriesOrMovie, String seasonAndEpisode, String type, int learned) {
        this.id = id;
        this.word = word;
        this.sentence = sentence;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.tvSeriesOrMovie = tvSeriesOrMovie;
        this.seasonAndEpisode = seasonAndEpisode;
        this.type = type;
        this.learned = learned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getTvSeriesOrMovie() {
        return tvSeriesOrMovie;
    }

    public void setTvSeriesOrMovie(String tvSeriesOrMovie) {
        this.tvSeriesOrMovie = tvSeriesOrMovie;
    }

    public String getSeasonAndEpisode() {
        return seasonAndEpisode;
    }

    public void setSeasonAndEpisode(String seasonAndEpisode) {
        this.seasonAndEpisode = seasonAndEpisode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }
    
    
    
    
         
}
