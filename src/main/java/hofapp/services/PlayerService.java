package hofapp.services;

import hofapp.DTO.PlayerWithScore;
import hofapp.DTO.TeamVoteRecords;
import hofapp.models.Player;
import hofapp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    private List<Player> cachedPlayers;

    public List<PlayerWithScore> getSortedPlayersWithScores(TeamVoteRecords teamVoteRecords, List<String> players) {
        populateCacheIfEmpty();
        List<PlayerWithScore> playerWithScores = players.size() > 0
                ? getFilteredPlayerWithScore(teamVoteRecords, players.stream().limit(20).collect(Collectors.toList()))
                : getAllPlayersWithScores(teamVoteRecords);
        return playerWithScores.stream().sorted(Comparator.comparingDouble(player -> player.getScore()*-1)).limit(20).collect(Collectors.toList());
    }

    private List<PlayerWithScore> getAllPlayersWithScores(TeamVoteRecords teamVoteRecords) {
        return scorePlayers(cachedPlayers, teamVoteRecords);
    }

    private List<PlayerWithScore> getFilteredPlayerWithScore(TeamVoteRecords teamVoteRecords, List<String> playerNames) {
        List<Player> players = cachedPlayers.stream().filter(player -> playerNames.contains(player.getFirstName()))
                .collect(Collectors.toList());
        return scorePlayers(players, teamVoteRecords);
    }

    private synchronized void populateCacheIfEmpty() {
        if(cachedPlayers == null) {
            cachedPlayers = new ArrayList<>();
            playerRepository.findAll().forEach(cachedPlayers::add);
        }
    }

    private List<PlayerWithScore> scorePlayers(List<Player> players, TeamVoteRecords teamVoteRecords) {
        return players.stream().map(player -> scorePlayer(teamVoteRecords, player))
                .collect(Collectors.toList());
    }

    private PlayerWithScore scorePlayer(TeamVoteRecords teamVoteRecords, Player player) {
        double score = teamVoteRecords.getValues().entrySet().stream()
                .mapToDouble(team -> team.getValue().getPlayerScore(player.getId()))
                .sum();

        double certainScore = teamVoteRecords.getValues().entrySet().stream()
                .mapToDouble(team -> team.getValue().getPlayerCertainScore(player.getId()))
                .sum();

        return new PlayerWithScore(player, score, certainScore);
    }
}


