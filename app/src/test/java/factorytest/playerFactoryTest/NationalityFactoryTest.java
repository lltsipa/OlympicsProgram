package factoryTest.playerFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.player.Nationality;
import factories.playerfactory.NationalityFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class NationalityFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Country","South Africa");
        stringMap.put("Continent","Africa");

        Nationality nationality = NationalityFactory.getNationality(stringMap);
        Assert.assertEquals("South Africa", nationality.getCountry());
        Assert.assertEquals("Africa", nationality.getContinent());

    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Country","South Africa");
        stringMap.put("Continent","Africa");

        Map<String,String> stringUpdateMap = new HashMap<>();
        stringMap.put("Countries","Brazil");
        stringMap.put("Continents","South America");

        Nationality nationality = NationalityFactory.getNationality(stringMap);

        Nationality newNationality = new Nationality.Builder()
                .copy(nationality)
                .Continent(stringMap.get("Continents"))
                .Country(stringMap.get("Countries"))
                .build();

        Assert.assertEquals("South America", newNationality.getContinent());
        Assert.assertEquals("Brazil",newNationality.getCountry());
    }
}
