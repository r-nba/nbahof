package hofapp.DTO;

import hofapp.models.Vote;
import org.joda.time.LocalDate;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamVoteRecords {

    private final LinkedHashMap<String, TeamVoteRecord> valuesThatHaveFinished;
    private final LinkedHashMap<String, TeamVoteRecord> valuesThatHaventFinished;
    private Map<String, TeamVoteRecord> values;


    public TeamVoteRecords(LiveTeamRecords liveTeamRecords) {

        values = liveTeamRecords.getValues().entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getPythagTotalWins() != null ? -1 * entry.getValue().getPythagTotalWins() : 0))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new TeamVoteRecord(entry.getValue()), (e1, e2) -> e1, LinkedHashMap::new));
        valuesThatHaveFinished = liveTeamRecords.getValues().entrySet().stream()
                .filter(entry -> entry.getValue().isUnderImpossible() || entry.getValue().isOverImpossible())
                .sorted(Comparator.comparing(entry -> entry.getValue().getPythagTotalWins() != null ? -1 * entry.getValue().getPythagTotalWins() : 0))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new TeamVoteRecord(entry.getValue()), (e1, e2) -> e1, LinkedHashMap::new));

        valuesThatHaventFinished = liveTeamRecords.getValues().entrySet().stream()
                .filter(entry -> !entry.getValue().isUnderImpossible() && !entry.getValue().isOverImpossible())
                .sorted(Comparator.comparing(entry -> entry.getValue().getPythagTotalWins() != null ? -1 * entry.getValue().getPythagTotalWins() : 0))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> new TeamVoteRecord(entry.getValue()), (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void setVotes(Iterable<Vote> votes) {

        for(Vote vote : votes) {
            values.get(vote.getTeam().getTeamName()).addVote(vote);
            if(valuesThatHaveFinished.containsKey(vote.getTeam().getTeamName())) {
                valuesThatHaveFinished.get(vote.getTeam().getTeamName()).addVote(vote);
            } else {
                valuesThatHaventFinished.get(vote.getTeam().getTeamName()).addVote(vote);
            }
        }
    }

    public LinkedHashMap<String, TeamVoteRecord> getValuesThatHaveFinished() {
        return valuesThatHaveFinished;
    }

    public Map<String, TeamVoteRecord> getValuesThatHaventFinished() {
        return valuesThatHaventFinished;
    }

    public Map<String, TeamVoteRecord> getValues() {
        return values;
    }
}
