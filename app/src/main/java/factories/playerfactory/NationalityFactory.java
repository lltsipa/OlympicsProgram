package factories.playerfactory;

import java.util.Map;

import domain.player.Nationality;

/**
 * Created by lodz on 2016/04/18.
 */
public class NationalityFactory {
    public static Nationality getNationality(String continent,String country)
    {
        Nationality nationality = new Nationality.Builder()
                .Continent(continent)
                .Country(country)
                .build();
        return nationality;
    }
}
