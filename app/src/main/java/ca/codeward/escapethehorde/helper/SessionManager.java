package ca.codeward.escapethehorde.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by Matt on 2017-08-09.
 */

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    SharedPreferences pref;
    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "PIT_Login";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_UID = "uid";
    private static final String KEY_USERID = "userid";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified: Logged in flag set.");
    }

    public void setUid(String uid) {
        editor.putString(KEY_UID, uid);
        editor.commit();
        Log.d(TAG, "User login session modified: Uid added.");
    }

    public void setUserId(int id) {
        editor.putInt(KEY_USERID, id);
        editor.commit();
        Log.d(TAG, "User login session modified: User ID added.");
    }

    public void setUsername(String un) {
        editor.putString(KEY_USERNAME, un);
        editor.commit();
        Log.d(TAG, "User login session modified: Username saved.");
    }

    public void setUserPass(String pass) {
        editor.putString(KEY_PASS, pass);
        editor.commit();
        Log.d(TAG, "User login session modified: Password saved.");
    }

    public boolean isRemembered() {
        return pref.contains(KEY_USERNAME) && pref.contains(KEY_PASS);
    }

    public String getUid() {
        return pref.getString(KEY_UID, "Uid not set.");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public int getUserId() { return pref.getInt(KEY_USERID, 0); }

    public String getUsername() { return pref.getString(KEY_USERNAME, "DEFAULT"); }

    public String getPassword() { return pref.getString(KEY_PASS, "DEFAULT"); }

    public void clearRemembered() {
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_PASS);
        editor.commit();
    }
}
