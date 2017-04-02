import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.currentgame.Participant;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.util.List;

class Main {
    public static void main(String[] args) {

        String APIKEY = "cda032a8-f14b-437d-a16d-e3941668ba5f";
        String summonerName = "Lunoski";


        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey(APIKEY);

        Summoner summoner = RiotAPI.getSummonerByName(summonerName);
        CurrentGame currentGame = summoner.getCurrentGame();
        List<Participant> participants = currentGame.getParticipants();

        final List<League> leagues;
        List<League> leaguesBySummonerName;

        String tier, champ;


        for(int i = 0; i < 10; i++) {
            leaguesBySummonerName = RiotAPI.getLeaguesBySummonerName(participants.get(i).getSummonerName());
            champ = participants.get(i).getChampion().getName();
            tier = leaguesBySummonerName.get(0).getTier().toString();

            System.out.println(participants.get(i).getSummonerName() + " ( " + tier + " )" + " as " + champ );
        }

    }
}