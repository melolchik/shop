package com.melolchik.common.util;

import android.app.Activity;
import android.content.Context;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

/**
 * Created by melolchik on 27.03.2017.
 */

public class UpdateCrashManager {

    private final boolean isAllowInstabugAndHockeyAppUpdate;


    public UpdateCrashManager(boolean allowInstabugAndHockeyAppUpdate) {
        isAllowInstabugAndHockeyAppUpdate = allowInstabugAndHockeyAppUpdate;
    }

    /**
     * Register hockey app manager.
     */
    public void registerHockeyAppManager(Activity activity) {
        checkForCrashes(activity);

        if (isAllowInstabugAndHockeyAppUpdate) {
            checkForUpdates(activity);
        }
    }

    /**
     * Unregister hockey app managers.
     */
    public void unregisterHockeyAppManagers() {

        if (isAllowInstabugAndHockeyAppUpdate) {
            UpdateManager.unregister();
        }
    }

    /**
     * Check for crashes.
     */
    protected void checkForCrashes(Context context) {
        CrashManager.register(context);
    }

    /**
     * Check for updates.
     */
    protected void checkForUpdates(Activity activity) {
        UpdateManager.register(activity);
    }
}
