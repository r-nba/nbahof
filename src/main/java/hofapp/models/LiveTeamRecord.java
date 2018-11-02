package hofapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LiveTeamRecord {

    @JsonProperty("OU")
    private double overUnder;
    private int actualWins;
    private int losses;
    private double differential;
    private Double winPerc;
    private Integer pythagTotalWins;
    private Integer pythagWinsSoFar;
    private boolean isUnderImpossible;
    private boolean isOverImpossible;
    private String lastYearsRecord;
    private double overScore;
    private double underScore;
    private double underLockScore;
    private double overLockScore;

    public double getOverUnder() {
        return overUnder;
    }

    public void setOverUnder(double overUnder) {
        this.overUnder = overUnder;
    }

    public int getActualWins() {
        return actualWins;
    }

    public void setActualWins(int actualWins) {
        this.actualWins = actualWins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getDifferential() {
        return differential;
    }

    public void setDifferential(double differential) {
        this.differential = differential;
    }

    public Double getWinPerc() {
        return winPerc;
    }

    public void setWinPerc(Double winPerc) {
        this.winPerc = winPerc;
    }

    public Integer getPythagTotalWins() {
        return pythagTotalWins;
    }

    public void setPythagTotalWins(Integer pythagTotalWins) {
        this.pythagTotalWins = pythagTotalWins;
    }

    public Integer getPythagWinsSoFar() {
        return pythagWinsSoFar;
    }

    public void setPythagWinsSoFar(Integer pythagWinsSoFar) {
        this.pythagWinsSoFar = pythagWinsSoFar;
    }

    public boolean isUnderImpossible() {
        return actualWins > overUnder;
    }

    public void setUnderImpossible(boolean underImpossible) {
        isUnderImpossible = underImpossible;
    }

    public boolean isOverImpossible() {
        return 82 - losses < overUnder;
    }

    public void setOverImpossible(boolean overImpossible) {
        isOverImpossible = overImpossible;
    }

    public String getLastYearsRecord() {
        return lastYearsRecord;
    }

    public void setLastYearsRecord(String lastYearsRecord) {
        this.lastYearsRecord = lastYearsRecord;
    }

    public String getRowStyle() {
        return isUnderImpossible() ? "over-hit" : isOverImpossible() ? "under-hit" : "normal";
    }

    public double getUnderLockScore() {
        return underLockScore;
    }

    public void setUnderLockScore(double underLockScore) {
        this.underLockScore = underLockScore;
    }

    public double getOverLockScore() {
        return overLockScore;
    }

    public void setOverLockScore(double overLockScore) {
        this.overLockScore = overLockScore;
    }

    public double getUnderScore() {
        return underScore;
    }

    public void setUnderScore(double underScore) {
        this.underScore = underScore;
    }

    public double getOverScore() {
        return overScore;
    }

    public void setOverScore(double overScore) {
        this.overScore = overScore;
    }
}
