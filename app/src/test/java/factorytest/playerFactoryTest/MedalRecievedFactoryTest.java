package factoryTest.playerFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.player.MedalRecieved;
import factories.playerfactory.MedalRecievedFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class MedalRecievedFactoryTest {
    @Test
    public void testCreate() throws Exception {
        Map<String,Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("Position",1);

        MedalRecieved medalRecieved = MedalRecievedFactory.getMedalRecieved(stringIntegerMap);
        Assert.assertEquals("Gold",medalRecieved.getMedal());
    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("Position",1);

        Map<String,Integer> stringPositionMap = new HashMap<>();
        stringPositionMap.put("Positions",1);

        MedalRecieved medalRecieved = MedalRecievedFactory.getMedalRecieved(stringIntegerMap);
        MedalRecieved newMedalRecieved = new MedalRecieved.Builder()
                .copy(medalRecieved)
                .setNextPosition(2)
                .build();
        Assert.assertEquals("Positions",newMedalRecieved.getMedal());
    }
}
