package li.imu.whirl;

import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

import org.gearvrf.utility.Log;

import static android.webkit.ConsoleMessage.*;

public class WhirlChromeClient extends WebChromeClient {
    private String messageLevel(ConsoleMessage cm){
        switch(cm.messageLevel()){
            case DEBUG: return "DEBUG";
            case ERROR: return "ERROR";
            case LOG: return "LOG";
            case TIP: return "TIP";
            case WARNING: return "WARNING";
        }
        return "";
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage cm){
        Log.v("Whirl", messageLevel(cm) + " " +
                cm.sourceId() + ":" + cm.lineNumber() + " " +
                cm.message());
        return true;
    }
}
