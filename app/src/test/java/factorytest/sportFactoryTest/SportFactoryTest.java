package factoryTest.sportFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.sport.Sport;
import factories.sportfactory.SportFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class SportFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Sport", "Football");

        Sport sport = SportFactory.getSport(stringMap);
        Assert.assertEquals("Football",sport.getSport());
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("Sport", "Football");

        Map<String,String> stringUpdateMap = new HashMap<>();
        stringUpdateMap.put("Sport", "Swimming");

        Sport sport = SportFactory.getSport(stringMap);
        Sport newSport = new Sport.Builder()
                .copy(sport)
                .Sport(stringUpdateMap.get("Sport"))
                .build();
        Assert.assertEquals("Swimming",newSport.getSport());
    }
}
