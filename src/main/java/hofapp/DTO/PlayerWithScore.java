package hofapp.DTO;

import hofapp.models.Player;

public class PlayerWithScore extends Player {

    private Double score;
    private Double certainScore;

    public PlayerWithScore(Player player, double score, double certainScore) {
        this.setId(player.getId());
        this.setFirstName(player.getFirstName());
        this.score = score;
        this.certainScore = certainScore;
    }

    public Double getCertainScore() {
        return certainScore;
    }

    public void setCertainScore(Double certainScore) {
        this.certainScore = certainScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCombinedScore() {
        return score + " (" + certainScore + ")";
    }
}
