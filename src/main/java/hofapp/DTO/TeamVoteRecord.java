package hofapp.DTO;

import hofapp.models.Answer;
import hofapp.models.LiveTeamRecord;
import hofapp.models.Vote;

import java.util.*;

public class TeamVoteRecord {
    private Map<Integer, Answer> votes;

    private LiveTeamRecord liveTeamRecord;

    TeamVoteRecord(LiveTeamRecord liveTeamRecord) {
        this.liveTeamRecord = liveTeamRecord;
    }

    public LiveTeamRecord getLiveTeamRecord() {
        return liveTeamRecord;
    }

    public void setLiveTeamRecord(LiveTeamRecord liveTeamRecord) {
        this.liveTeamRecord = liveTeamRecord;
    }

    private Optional<List<String>> getCorrectVoteAnswers() {
        if(liveTeamRecord.getPythagTotalWins() == null) {
            return Optional.of(new ArrayList<>());
        }

        if(liveTeamRecord.getPythagTotalWins() == liveTeamRecord.getOverUnder())
        {
            return Optional.empty();
        }

        if(liveTeamRecord.getPythagTotalWins() > liveTeamRecord.getOverUnder()) {
            return Optional.of(new ArrayList<>(Arrays.asList("OVER", "OVER LOCK")));
        }

        return Optional.of(new ArrayList<>(Arrays.asList("UNDER", "UNDER LOCK")));
    }

    private Optional<Boolean> isVoteCorrectForPlayer(Integer playerId) {
        Optional<List<String>> correctVoteAnswers = getCorrectVoteAnswers();
        if(votes == null) {
            return Optional.empty();
        }
        return correctVoteAnswers.map(strings -> strings.contains(votes.get(playerId).getVoteName()));
    }

    public String getCorrectnessColour(Integer playerId) {
        return isVoteCorrectForPlayer(playerId)
                .map(isCorrect -> isCorrect ? "green" : "red").orElse("yellow");
    }

    public double getPlayerScore(Integer playerId) {
        switch (votes.get(playerId).getVoteName()) {
            case "OVER LOCK":
                return liveTeamRecord.getOverLockScore();
            case "UNDER LOCK":
                return liveTeamRecord.getUnderLockScore();
            case "UNDER":
                return liveTeamRecord.getUnderScore();
            case "OVER":
                return liveTeamRecord.getOverScore();
            default:
                return 0;
        }
    }

    public double getPlayerCertainScore(Integer playerId) {
        if(!liveTeamRecord.isOverImpossible() || !liveTeamRecord.isUnderImpossible()) {
            return 0;
        }

        return getPlayerScore(playerId);
    }

    public boolean isOver() {
        return getCorrectVoteAnswers().map( answers -> answers.contains("OVER")).orElse(false);
    }

    public boolean isUnder() {
        return getCorrectVoteAnswers().map( answers -> answers.contains("UNDER")).orElse(false);
    }

    public boolean isPush() {
        return !isOver() && !isUnder();
    }

    public Map<Integer, Answer> getVotes() {
        return votes;
    }

    public void addVote(Vote vote) {
        if(votes == null) {
            votes = new HashMap<>();
        }
        votes.put(vote.getPlayer().getId(), vote.getAnswer());
    }
}