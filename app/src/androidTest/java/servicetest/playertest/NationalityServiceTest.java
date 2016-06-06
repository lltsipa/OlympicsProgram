package servicetest.playertest;

import android.content.Intent;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import conf.util.App;
import domain.player.Nationality;
import factories.playerfactory.NationalityFactory;
import services.player.impl.NationalityServiceImpl;

/**
 * Created by lodz on 2016/05/08.
 */
public class NationalityServiceTest extends AndroidTestCase{
    Intent intent;
    NationalityServiceImpl nationalityService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        nationalityService = NationalityServiceImpl.getInstance();
//        intent = new Intent(App.getAppContext(), NationalityServiceImpl.class);
    }

    public void testQualified() throws Exception {
        Nationality nationality = NationalityFactory.getNationality("Africa","Ghana");
        nationalityService.qualified(App.getAppContext(),nationality);
  //      nationalityService.handleQualified(nationality);

        Assert.assertNotNull("Testing nationality qualified ",nationalityService);
    }
}
