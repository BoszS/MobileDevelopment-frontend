package be.thomasmore.ezsports.models;

import java.util.Date;
import java.util.List;

public class Match {
    private int id;
    private String name;
    private String begin_at;
    private League league;
    private Serie serie;
    private Tournament tournament;
    private List<Team> opponents;

    public Match() {
    }

    public Match(int id, String name ,String begin_at, League league, Serie serie, Tournament tournament, List<Team> opponents) {
        this.id = id;
        this.name = name;
        this.begin_at = begin_at;
        this.league = league;
        this.serie = serie;
        this.tournament = tournament;
        this.opponents = opponents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBegin_at() {
        return begin_at;
    }

    public void setBegin_at(String begin_at) {
        this.begin_at = begin_at;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<Team> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Team> opponents) {
        this.opponents = opponents;
    }

    public String getDate(){
        String date = this.getBegin_at();
        String substringDate = date.substring(0, date.indexOf("T"));

        return substringDate;

    }

    public String getTime(){
        String time = this.getBegin_at();
        String substringTime = time.substring(time.indexOf("T") + 1, time.length() - 4);

        return substringTime;

    }
}
