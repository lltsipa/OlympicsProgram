package services.team;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import conf.util.App;
import domain.team.TeamDetails;
import repository.team.TeamDetailsRepository;
import repository.team.impl.TeamDetailsRepositoryImpl;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TeamDetailsServiceImpl extends IntentService {

    private final TeamDetailsRepository repo;
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_ADD_DETAILS = "services.team.action.ADDDETAILS";
    private static final String ACTION_UPDATE_DETAILS = "services.team.action.UPDATEDETAILS";


    private static final String EXTRA_ADD_DETAILS = "services.team.extra.ADDDETAILS";
    private static final String EXTRA_UPDATE_DETAILS = "services.team.extra.UPDATEDETAILS";

    private static TeamDetailsServiceImpl service = null;

    public static TeamDetailsServiceImpl getInstance() {
        if (service == null)
            service = new TeamDetailsServiceImpl();
        return service;
    }

    // TODO: Customize helper method
    public static void addTeamDetails(Context context, TeamDetails teamDetails) {
        Intent intent = new Intent(context, TeamDetailsServiceImpl.class);
        intent.setAction(ACTION_ADD_DETAILS);
        intent.putExtra(EXTRA_ADD_DETAILS, teamDetails);
        context.startService(intent);
    }

    //
    public static void updateTeamDetails(Context context, TeamDetails teamDetails) {
        Intent intent = new Intent(context, TeamDetailsServiceImpl.class);
        intent.setAction(ACTION_UPDATE_DETAILS);
        intent.putExtra(EXTRA_UPDATE_DETAILS, teamDetails);
        context.startService(intent);
    }

    public TeamDetailsServiceImpl() {
        super("TeamDetailsServiceImpl");
        repo = new TeamDetailsRepositoryImpl(App.getAppContext());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD_DETAILS.equals(action)) {
                final TeamDetails teamDetails = (TeamDetails)intent.getSerializableExtra(EXTRA_ADD_DETAILS);
                handleAddTeamDetails(teamDetails);
            } else if (ACTION_UPDATE_DETAILS.equals(action)) {
                final TeamDetails teamDetails = (TeamDetails)intent.getSerializableExtra(EXTRA_UPDATE_DETAILS);
                handleUpdateTeamDetails(teamDetails);
            }
        }
    }

    private void handleAddTeamDetails(TeamDetails teamDetails) {
        repo.save(teamDetails);
    }

    private void handleUpdateTeamDetails(TeamDetails teamDetails) {
        repo.update(teamDetails);
    }
}
