package hofapp.services;

import hofapp.DTO.PlayerWithScore;
import hofapp.DTO.TeamVoteRecords;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreCacheService {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private LiveTeamRecordService liveTeamRecordService;

    private Map<LocalDate, TeamVoteRecords> teamRecordsCache = new HashMap<>();

    public void getResultsModel(Model model, String year, String month, String day, List<String> players) {
        DateTime dateTime;
        if(StringUtils.isBlank(year)) {
            dateTime = new DateTime().withZone(DateTimeZone.UTC);
        } else {
            dateTime = new DateTime(Integer.valueOf(year), Integer.valueOf(month),Integer.valueOf(day),0,0,0);
        }

        DateTime sevenAm =  new DateTime().withTime(7,0,0, 0);
        DateTime fivePm =  new DateTime().withTime(17,0,0, 0);
        TeamVoteRecords teamVoteRecords = null;

        if(dateTime.isAfter(sevenAm) && dateTime.isBefore(fivePm)) {
            teamVoteRecords = teamRecordsCache.getOrDefault(dateTime.toLocalDate(), null);
            if(teamVoteRecords == null) {
                teamVoteRecords = liveTeamRecordService.getAllLiveTeamRecords();
            }
        } else {
            dateTime = dateTime.minusDays(1);
            teamVoteRecords = teamRecordsCache.getOrDefault(dateTime.toLocalDate(), null);
            if(teamVoteRecords == null) {
                teamVoteRecords = liveTeamRecordService.getAllLiveTeamRecords(dateTime.year().getAsString(),
                        String.valueOf(dateTime.monthOfYear().get()), dateTime.dayOfMonth().getAsString());
                teamRecordsCache.put(dateTime.toLocalDate(), teamVoteRecords);
            }
        }

        model.addAttribute("players", playerService.getSortedPlayersWithScores(teamVoteRecords, players));
        model.addAttribute("liveTeamRecords", teamVoteRecords);
        model.addAttribute("teams", teamVoteRecords.getValues().keySet());
    }

}
