import org.junit.runner.RunWith;
import org.junit.runners.Suite;



import factories.playerfactory.MedalRecievedFactory;
import factoryTest.playerFactoryTest.CatagoryFactoryTest;
import factoryTest.playerFactoryTest.NationalityFactoryTest;
import factoryTest.playerFactoryTest.PlayerStatsFactoryTest;
import factoryTest.sportFactoryTest.SportFactoryTest;
import factoryTest.sportFactoryTest.VenueFactoryTest;
import factoryTest.teamFactoryTest.TeamDetailsFactoryTest;
import factoryTest.teamFactoryTest.TeamStatsFactoryTest;

/**
 * Created by lodz on 2016/04/24.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CatagoryFactoryTest.class,
        MedalRecievedFactory.class,
        NationalityFactoryTest.class,
        PlayerStatsFactoryTest.class,
        PlayerStatsFactoryTest.class,
        SportFactoryTest.class,
        VenueFactoryTest.class,
        TeamDetailsFactoryTest.class,
        TeamStatsFactoryTest.class
})
public class AppUnitTestSuite {
}
