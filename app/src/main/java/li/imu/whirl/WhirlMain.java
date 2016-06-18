package li.imu.whirl;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMain;
import org.gearvrf.scene_objects.GVRTextViewSceneObject;
import org.gearvrf.scene_objects.GVRViewSceneObject;
import org.gearvrf.utility.Log;

public class WhirlMain extends GVRMain {
    private final WhirlActivity mActivity;
    private GVRContext mContext;

    private GVRViewSceneObject mWebSceneObject;
    private int mTodo;

    public WhirlMain(WhirlActivity activity) {
        mActivity = activity;
    }

    @Override
    public void onInit(final GVRContext gvrContext) throws Throwable {
        mContext = gvrContext;

        GVRTextViewSceneObject plane = new GVRTextViewSceneObject(gvrContext);

        mWebSceneObject = new GVRViewSceneObject(gvrContext,
                mActivity.getWebView(), plane.getRenderData().getMesh());

        gvrContext.getNextMainScene().addSceneObject(mWebSceneObject);

        mWebSceneObject.getTransform().setPosition(0.0f, 0.0f, -2.5f);

        plane.close();
    }

    @Override
    public void onStep() {
        if((mTodo & 1) != 0) {
            Log.v("Whirl", "camera reset");
            mContext.getMainScene().getMainCameraRig().reset();
        }
        mTodo = 0;
    }

    public void doOnStep(int what){
        mTodo |= what;
    }

}
