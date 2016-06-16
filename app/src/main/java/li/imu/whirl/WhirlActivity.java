package li.imu.whirl;

import org.gearvrf.GVRActivity;
import org.gearvrf.scene_objects.view.GVRView;
import org.gearvrf.utility.Log;

import android.os.Bundle;
import android.view.KeyEvent;

public class WhirlActivity extends GVRActivity {
    private WhirlMain mWhirlMain;
    private boolean mMeta = false;

    private WhirlWebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createView();

        mWhirlMain = new WhirlMain(this);
        setMain(mWhirlMain, "gvr.xml");
    }

    private void createView() {
        mWebView = new WhirlWebView(this);
    }

    public GVRView getWebView() {
        return mWebView;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.KEYCODE_ALT_RIGHT){
            mMeta = event.getAction() == KeyEvent.ACTION_DOWN ? true : false;
            Log.v("Whirl", "meta: " + (mMeta ? "true" : "false"));
            return true;
        }

        if(!mMeta)
            return mWebView.dispatchKeyEvent(event);

        switch(event.getKeyCode()){
            case KeyEvent.KEYCODE_R:
                Log.v("Whirl", "queueing camera reset");
                mWhirlMain.doOnStep(1);
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
//        Log.v("Whirl", String.format("%d", keyCode));
        return false;
    }
}
