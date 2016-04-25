package factoryTest.playerFactoryTest;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import domain.player.Catagory;
import factories.playerFactory.CatagoryFactory;

/**
 * Created by lodz on 2016/04/24.
 */
public class CatagoryFactoryTest {
    @Test
    public void testCreate() throws Exception {

        Map<String,String> stringMap = new HashMap<String, String>();
        stringMap.put("Men", "Men");
        stringMap.put("Women","Women");

        Catagory catagory = CatagoryFactory.getCatagory(stringMap);
        Assert.assertEquals("Men", catagory.getMen());
        Assert.assertEquals("Women", catagory.getWomen());


    }

    @Test
    public void testUpdate() throws Exception {
        Map<String,String> stringMap = new HashMap<String, String>();
        stringMap.put("Men", "Men");
        stringMap.put("Women", "Women");

        Map<String,String> stringStringMap = new HashMap<String, String>();
        stringStringMap.put("Man", "Man");
        stringStringMap.put("Woman", "Woman");

        Catagory catagory = CatagoryFactory.getCatagory(stringMap);
        Catagory newCatagory = new Catagory.Builder()
                .copy(catagory)
                .Men(stringStringMap.get("Man"))
                .Women(stringMap.get("Woman"))
                .build();

        Assert.assertEquals("Man", newCatagory.getMen());
        Assert.assertEquals("Woman", newCatagory.getWomen());
       // Assert.assertEquals(DomainStat.);
    }
}
