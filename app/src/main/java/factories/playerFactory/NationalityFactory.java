package factories.playerFactory;

import java.util.Map;

import domain.player.Nationality;

/**
 * Created by lodz on 2016/04/18.
 */
public class NationalityFactory {
    public static Nationality getNationality(Map<String, String> stingStringMap)
    {
        Nationality nationality = new Nationality.Builder()
                .Continent(stingStringMap.get("Continent"))
                .Country(stingStringMap.get("Country"))
                .build();
        return nationality;
    }
}
