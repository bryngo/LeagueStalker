import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.currentgame.Participant;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.util.List;

class Main {
    public static void main(String[] args) {

        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey("cda032a8-f14b-437d-a16d-e3941668ba5f");
        String summonerName = "Prince Turtle";

        Summoner summoner = RiotAPI.getSummonerByName(summonerName);
        CurrentGame currentGame = summoner.getCurrentGame();
        List<League> rankedStats;
        List<Participant> participants = currentGame.getParticipants();

        for(int i = 0; i < 10; i++) {
            System.out.println("=== " + participants.get(i).getSummonerName() + " ===");
            rankedStats = RiotAPI.getSummonerByID(participants.get(i).getSummonerID()).getLeagues();
        }

    }
}