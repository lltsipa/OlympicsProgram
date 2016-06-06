package servicetest.sporttest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import conf.util.App;
import domain.sport.Sport;
import factories.sportfactory.SportFactory;
import services.sport.impl.SportServiceImpl;

/**
 * Created by lodz on 2016/06/06.
 */
public class SportTest extends AndroidTestCase {

    SportServiceImpl sportService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sportService = SportServiceImpl.getInstance();
    }

    public void testAddSport() throws Exception {
        Sport sport = SportFactory.getSport("Rugby");

        sportService.addSport(App.getAppContext(),sport);

        Assert.assertNotNull("Testing sport added: ", sportService);
    }
}
