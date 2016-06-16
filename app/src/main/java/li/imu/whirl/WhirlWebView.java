package li.imu.whirl;

import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import org.gearvrf.GVRActivity;
import org.gearvrf.scene_objects.view.GVRWebView;

public class WhirlWebView extends GVRWebView{
    public WhirlWebView(GVRActivity context) {
        super(context);
        setWebChromeClient(new WhirlChromeClient());
        setWebViewClient(new WhirlWebViewClient());
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);

        setLayoutParams(new FrameLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        loadUrl("file:///android_asset/repl/index.html");
    }
}
