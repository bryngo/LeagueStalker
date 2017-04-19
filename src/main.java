import com.robrua.orianna.api.core.RiotAPI;
import com.robrua.orianna.type.core.common.Region;
import com.robrua.orianna.type.core.currentgame.CurrentGame;
import com.robrua.orianna.type.core.currentgame.Participant;
import com.robrua.orianna.type.core.league.League;
import com.robrua.orianna.type.core.summoner.Summoner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {

        String APIKEY = getBryanAPIKey("League");
        String summonerName = "BubblyBryan";


        RiotAPI.setRegion(Region.NA);
        RiotAPI.setAPIKey(APIKEY);
        Summoner summoner = RiotAPI.getSummonerByName(summonerName);
        CurrentGame currentGame = summoner.getCurrentGame();
        List<Participant> participants = currentGame.getParticipants();

        List<League> leaguesBySummonerName;

        String tier, champ;


        for(int i = 0; i < 10; i++) {
            leaguesBySummonerName = RiotAPI.getLeaguesBySummonerID(participants.get(i).getSummonerID());
            champ = participants.get(i).getChampion().getName();
            tier = leaguesBySummonerName.get(0).getTier().toString();

            System.out.println(participants.get(i).getSummonerName() + " ( " + tier + " )" + " as " + champ );
        }

    }

    private static String getBryanAPIKey(String APIcompany) {

        // holds the list of api keys
        List<String> records = new ArrayList<String>();

        // open the file and add the keys to the list
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("/Users/Bryan1/keys.text"));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
        }

        // error
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", "/Users/Bryan1/keys.text");
            e.printStackTrace();
            return null;
        }

        // return the requested api key
        for (String record : records)
            if (record.contains(APIcompany)) {
                String[] tokens = record.split("=");
                return tokens[1];
            }


        return "ERROR";

    }

} // returns an APIKey.
