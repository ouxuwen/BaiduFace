package com.baidu.face;

import android.content.Intent;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.ui.FaceDetectActivity;

/**
 * Created by kevin.zhang on 2018/3/1.
 */

public class BaiduFace extends CordovaPlugin {
    private final static int REQUESTCODE = 2; // 返回的结果码
    public CallbackContext callbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        try {
            if(action.equals("open")){
                Toast.makeText(cordova.getActivity().getApplicationContext(), "百度 face in ", Toast.LENGTH_SHORT).show();
                initLib();
                Intent baiduInitent = new Intent(cordova.getActivity(),FaceDetectActivity.class);
                cordova.getActivity().startActivityForResult(baiduInitent,REQUESTCODE);
    //            PluginResult baiData = new PluginResult(PluginResult.Status.NO_RESULT);
    //            baiData.setKeepCallback(true);
    //            callbackContext.sendPluginResult(baiData);
                PluginResult cardData = new PluginResult(
                        PluginResult.Status.NO_RESULT);
                cardData.setKeepCallback(true);
                callbackContext.sendPluginResult(cardData);

                return true;
            }else if(action.equals("test")){
                Toast.makeText(cordova.getActivity().getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                return true;
            }else{
                callbackContext.error("Invalid Action");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        }

    }

    /**
     * 初始化SDK
     */
    private void initLib() {
        // 为了android和ios 区分授权，appId=appname_face_android ,其中appname为申请sdk时的应用名
        // 应用上下文
        // 申请License取得的APPID
        // assets目录下License文件名
        FaceSDKManager.getInstance().initialize(cordova.getActivity().getApplicationContext(), Config.licenseID, Config.licenseFileName);
//        setFaceConfig();
    }

    // 为了获取结果

//    protected void onActivityResult(int requestCode,int resultCode,Intent data){
//        super.onActivityResult(requestCode,resultCode,data);
//        if(resultCode == 3){
//            if(requestCode == REQUESTCODE){
//                String bmp = data.getStringExtra("img");
//                JSONObject result = new JSONObject();
//                try {
//                result.put("img",bmp);
//                PluginResult cardData = new PluginResult(PluginResult.Status.OK,
//                        result);
//                cardData.setKeepCallback(false);
//                callbackContext.sendPluginResult(result);
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//
//                    e.printStackTrace();
//                    callbackContext.error(e.getMessage());
//                }
//
//            }
//        }
//    }

    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);

        // send plugin result if success



        String imgData = FaceDetectActivity.imgData;
        if (imgData != null) {
                     JSONObject result = new JSONObject();
                    try {
                        result.put("img",imgData);
                        PluginResult cardData = new PluginResult(PluginResult.Status.OK,
                                result);
                        cardData.setKeepCallback(false);
                        callbackContext.sendPluginResult(cardData);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block

                        e.printStackTrace();
                        callbackContext.error(e.getMessage());
                    }



        } else {
            PluginResult cardData = new PluginResult(
                    PluginResult.Status.NO_RESULT);
            cardData.setKeepCallback(false);
            callbackContext.sendPluginResult(cardData);
        }

    }


}
