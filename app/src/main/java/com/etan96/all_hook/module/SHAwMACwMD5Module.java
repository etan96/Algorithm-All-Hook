package com.etan96.all_hook.module;

import com.etan96.all_hook.utils.Base64Util;
import com.etan96.all_hook.utils.HexUtil;
import com.etan96.all_hook.utils.LogUtil;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import de.robv.android.xqoseb.XC_MethodHook;
import de.robv.android.xqoseb.XposedBridge;
import de.robv.android.xqoseb.XposedHelpers;
import de.robv.android.xqoseb.callbacks.XC_LoadPackage;

public class SHAwMACwMD5Module {

    public static void shaWmacWmd5(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        XposedBridge.hookAllMethods(MessageDigest.class,
                "digest",
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        MessageDigest messageDigest = (MessageDigest) param.thisObject;
                        String algorithm = messageDigest.getAlgorithm();
                        byte[] data = new byte[0];
                        if (param.args != null){
                            if (param.args.length == 1) {
                                data = (byte[]) param.args[0];
                            } else if (param.args.length == 3) {
                                int offset = (int) param.args[1];
                                int len = (int) param.args[2];
                                System.arraycopy((byte[]) param.args[0], offset, data, 0, len);
                            }
                        }
                        byte[] res = (byte[]) param.getResult();
                        RuntimeException e = new RuntimeException("algorithm");
                        e.fillInStackTrace();
                        LogUtil.e("\n ====================================================================================" +
                                        "\n 加密方式：" + algorithm +
                                        "\n digest数据：" + new String(data) +
                                        "\n digest数据(Base64)：" + Base64Util.bytes2String(data) +
                                        "\n digest数据(HEX)：" + HexUtil.byteArrToHex(data) +
                                        "\n 加密后数据：" + new String(res) +
                                        "\n 加密后数据(Base64)：" + Base64Util.bytes2String(res) +
                                        "\n 加密后数据(HEX)：" + HexUtil.byteArrToHex(res) +
                                        "\n 调用栈", e);
                    }
                });

        XposedBridge.hookAllMethods(MessageDigest.class,
                "update",
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        MessageDigest messageDigest = (MessageDigest) param.thisObject;
                        String algorithm = messageDigest.getAlgorithm();
                        if (param.args.length <= 1){
                            byte[] data = (byte[]) param.args[0];
                            LogUtil.e("\n ====================================================================================" +
                                    "\n 加密方式：" + algorithm +
                                    "\n update数据：" + new String(data) +
                                    "\n update数据(Base64)：" + Base64Util.bytes2String(data) +
                                    "\n update数据(HEX)：" + HexUtil.byteArrToHex(data));
                        }else {
                            byte[] data = new byte[0];
                            int offset = (int) param.args[1];
                            int len = (int) param.args[2];
                            System.arraycopy((byte[]) param.args[0], offset, data, 0, len);
                            LogUtil.e("\n ====================================================================================" +
                                    "\n 加密方式：" + algorithm +
                                    "\n update数据：" + new String(data) +
                                    "\n update数据(Base64)：" + Base64Util.bytes2String(data) +
                                    "\n update数据(HEX)：" + HexUtil.byteArrToHex(data));
                        }
                    }
                });

        XposedHelpers.findAndHookConstructor(SecretKeySpec.class,
                byte[].class, String.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String algorithm = (String) param.args[1];
                        byte[] key = (byte[]) param.args[0];
                        LogUtil.e("\n ====================================================================================" +
                                "\n 加密方式：" + algorithm +
                                "\n 密钥：" + new String(key) +
                                "\n 密钥(Base64)：" + Base64Util.bytes2String(key) +
                                "\n 密钥(HEX)：" + HexUtil.byteArrToHex(key));
                    }
                });

        XposedHelpers.findAndHookMethod(Mac.class, "getInstance", String.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                LogUtil.e("\n ====================================================================================" +
                        "\n 加密方式：" + param.args[0]);
            }
        });

        XposedBridge.hookAllMethods(Mac.class, "update", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                if (param.args.length <= 1){
                    byte[] data = (byte[]) param.args[0];
                    LogUtil.e("\n ====================================================================================" +
                            "\n 加密方式：" + "MAC" +
                            "\n update数据：" + new String(data) +
                            "\n update数据(Base64)：" + Base64Util.bytes2String(data) +
                            "\n update数据(HEX)：" + HexUtil.byteArrToHex(data));
                }else {
                    byte[] data = new byte[0];
                    int offset = (int) param.args[1];
                    int len = (int) param.args[2];
                    System.arraycopy((byte[]) param.args[0], offset, data, 0, len);
                    LogUtil.e("\n ====================================================================================" +
                            "\n 加密方式：" + "MAC" +
                            "\n update数据：" + new String(data) +
                            "\n update数据(Base64)：" + Base64Util.bytes2String(data) +
                            "\n update数据(HEX)：" + HexUtil.byteArrToHex(data));
                }
            }
        });

        XposedBridge.hookAllMethods(Mac.class, "doFinal", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Mac mac = (Mac) param.thisObject;
                String algorithm = mac.getAlgorithm();
                byte[] res = (byte[]) param.getResult();
                RuntimeException e = new RuntimeException("algorithm");
                e.fillInStackTrace();
                if (param.args == null || param.args.length == 0){
                    LogUtil.e("\n ====================================================================================" +
                            "\n 加密方式：" + algorithm +
                            "\n 加密后数据：" + new String(res) +
                            "\n 加密后数据(Base64)：" + Base64Util.bytes2String(res) +
                            "\n 加密后数据(HEX)：" + HexUtil.byteArrToHex(res) +
                            "\n 调用栈", e);
                }else if (param.args.length == 1){
                    byte[] data = (byte[]) param.args[0];
                    LogUtil.e("\n ====================================================================================" +
                            "\n 加密方式：" + algorithm +
                            "\n doFinal数据：" + new String(data) +
                            "\n doFinal数据(Base64)：" + Base64Util.bytes2String(data) +
                            "\n doFinal数据(HEX)：" + HexUtil.byteArrToHex(data) +
                            "\n 加密后数据：" + new String(res) +
                            "\n 加密后数据(Base64)：" + Base64Util.bytes2String(res) +
                            "\n 加密后数据(HEX)：" + HexUtil.byteArrToHex(res) +
                            "\n 调用栈", e);
                }else if (param.args.length == 2){
                    int offset = (int) param.args[1];
                    byte[] tmp = (byte[]) param.args[0];
                    byte[] data = new byte[tmp.length - offset];
                    System.arraycopy((byte[]) param.args[0], offset, data, 0, tmp.length - offset -1);
                    LogUtil.e("\n ====================================================================================" +
                            "\n 加密方式：" + algorithm +
                            "\n doFinal数据：" + new String(data) +
                            "\n doFinal数据(Base64)：" + Base64Util.bytes2String(data) +
                            "\n doFinal数据(HEX)：" + HexUtil.byteArrToHex(data) +
                            "\n 加密后数据：" + new String(res) +
                            "\n 加密后数据(Base64)：" + Base64Util.bytes2String(res) +
                            "\n 加密后数据(HEX)：" + HexUtil.byteArrToHex(res) +
                            "\n 调用栈", e);
                }
            }
        });
    }
}
