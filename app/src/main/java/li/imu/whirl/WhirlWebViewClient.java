package li.imu.whirl;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WhirlWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        return false;
    }
}
