package li.imu.whirl;

import org.gearvrf.GVRActivity;
import org.gearvrf.GVRResourceVolume;
import org.gearvrf.scene_objects.view.GVRView;
import org.gearvrf.script.GVRScriptBundle;
import org.gearvrf.script.GVRScriptException;
import org.gearvrf.script.GVRScriptManager;
import org.gearvrf.utility.Log;

import android.os.Bundle;
import android.view.KeyEvent;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class WhirlActivity extends GVRActivity {
    private WhirlMain mWhirlMain;
    private boolean mMeta = false;

    private WhirlWebView mWebView;
    private GVRScriptManager mScriptManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createView();

        mWhirlMain = new WhirlMain(this);
        setMain(mWhirlMain, "gvr.xml");

        mScriptManager = getGVRContext().getScriptManager();

        loadScriptBundles();

    }

    private void loadScriptBundles() {
        Log.v("WhirlActivity", "entering loadScriptBundles");
        File baseDir = getExternalFilesDir(null);
        if(baseDir == null) return;
        File[] dirs = baseDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        Log.v("WhirlActivity", "loadScriptBundles check");
        if(dirs == null) return;
        GVRScriptManager sm = getGVRContext().getScriptManager();
        Log.v("WhirlActivity", "loadScriptBundles loop");
        for(File dir : dirs){
            Log.v("WhirlActivity", "loadScriptBundles " + dir.getName());
            try {
                GVRResourceVolume rv = new GVRResourceVolume(getGVRContext(),
                        GVRResourceVolume.VolumeType.LINUX_FILESYSTEM,
                        dir.getAbsolutePath());
                GVRScriptBundle sb = mScriptManager.loadScriptBundle("bundle.json", rv);
                sm.bindScriptBundle(sb, mWhirlMain, true);
            } catch (NullPointerException | IOException | GVRScriptException e) {
                Log.e("WhirlActivity", "loadScriptBundles exception");
                e.printStackTrace();
            }
        }
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
            mMeta = event.getAction() == KeyEvent.ACTION_DOWN;
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
