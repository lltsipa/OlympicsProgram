package servicetest.playertest;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import conf.util.App;
import domain.player.MedalRecieved;
import factories.playerfactory.MedalRecievedFactory;
import repository.player.MedalRecievedRepository;
import services.player.MedalRecievedServices;
import services.player.impl.MedalRecievedServiceImpl;

/**
 * Created by lodz on 2016/06/06.
 */
public class MedalRecievedTest extends AndroidTestCase {
    MedalRecievedServiceImpl medalRecievedServices;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        medalRecievedServices = MedalRecievedServiceImpl.getInstance();
    }

    public void testMedalRecieved() throws Exception {
        MedalRecieved medalRecieved = MedalRecievedFactory.getMedalRecieved(2);
        medalRecievedServices.medals(App.getAppContext(),medalRecieved);
     //   medalRecievedServices.handleMedal(medalRecieved);

        Assert.assertNotNull("Testing Medal Service: ", medalRecievedServices);

    }
}
