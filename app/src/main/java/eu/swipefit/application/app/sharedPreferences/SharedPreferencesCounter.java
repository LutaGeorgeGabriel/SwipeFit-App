package eu.swipefit.application.app.sharedPreferences;
/**
 * FILE DESCRIPTION
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.File;

/** ADD COMMENTS */
public class SharedPreferencesCounter extends Activity{
    public static void setDefaults(String key, int value, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }

    public static void initializeSharedPreferences(Context context) {
        File f = new File(
                "/data/user/0/eu.swipefit.app/shared_prefs/eu.swipefit.app_preferences.xml");
        if (f.exists())
            return;
        else
            setDefaults("counter",0,context);

    }

    public static void incrementSharedPreferences() {

    }

    public void getSharedPreferencesCounter() {
        SharedPreferences countSettings = getSharedPreferences("count", 0);
        int count = countSettings.getInt("counts",0);
        count++;
        final SharedPreferences.Editor edit = countSettings.edit();
        edit.putInt("counts",count);
        edit.commit();
    }
}
