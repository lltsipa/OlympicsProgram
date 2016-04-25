package factories.playerFactory;

import java.util.Map;

import domain.player.Catagory;

/**
 * Created by lodz on 2016/04/18.
 */
public class CatagoryFactory {
    public static Catagory getCatagory(Map<String,String> stringMap)
    {
        Catagory catagory = new Catagory.Builder()
                .Men(stringMap.get("Men"))
                .Women(stringMap.get("Women"))
                .build();
        return catagory;
    }
}
