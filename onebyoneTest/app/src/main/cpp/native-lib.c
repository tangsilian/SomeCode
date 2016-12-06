#include <stdlib.h>
#include<Android/log.h>
//打印log头文件
#include <jni.h>
#include <stdio.h>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <unistd.h>
#include <fcntl.h>
#include <assert.h>
#include "string.h"
#define TAG "TSL" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
//#define LOGI(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define JNIREG_CLASS "com/example/tangsilian/onebyonetest/MyLoadSoClass"//指定要注册的类
//实质上是一个classloader
static jobject mydexClassLoaderJObject;
//声明一个char字符
static char *g_strtdf;
//声明两个方法
jstring getPackname(JNIEnv *env,  jobject obj);
void decodate(JNIEnv *env, jobject context);

//此方法用来读取asset目录下的文件并解密
void decodate(JNIEnv *env, jobject context){
    //在jni中获取AssetManager对象
    jobject  assetobj=(*env)->GetObjectClass(env, context);
    jmethodID v6= (*env)->GetMethodID(env, assetobj, "getAssets", "()Landroid/content/res/AssetManager;");
    jobject  assetManager=(*env)->CallObjectMethod(env, context, v6);
    AAssetManager* asMg= AAssetManager_fromJava(env, assetManager);
    if(asMg==NULL)
    {
        LOGD(" %s","AAssetManager==NULL");
    }
    LOGD("%s","getassetmanager sucess ");
    //找到文件的名字

    //打开Asset目录下的cdodj.jar文件
    AAsset* as= AAssetManager_open(asMg, "my.jar", 2);
    LOGD("%s","delerjar sucess ");
    //jar文件不存在
    if (as == NULL)
    {
        LOGD(" %s","asset==NULL");
    }
    //获取文件的大小
    int  bufferSize = AAsset_getLength(as);
    LOGD("file size   : %d",bufferSize);
    //声明缓冲区
    char *buffer=(char *)malloc(bufferSize+1);
    //文件要读取的次数
    buffer[bufferSize]=0;
    int numBytesRead = AAsset_read(as, buffer, bufferSize);
    LOGD("file size : %d\n",numBytesRead);
    //读取该文件
    FILE *fp = fopen("/data/data/com.example.tangsilian.onebyonetest/my.jar","w");
    //LOGD("open file :%s\n", strerror(errno));
    //这里要改成字节数
    // size_t fwrite(const void* buffer, size_t size, size_t count, FILE* stream);
    fwrite(buffer, numBytesRead, 1,fp);
    free(buffer) ;
    fclose(fp);
    /*关闭文件*/
    AAsset_close(as);
    LOGD(": %s","copy finshed");
}


//读取包名
jstring getPackname(JNIEnv *env,  jobject obj)//第个obj为context
{
    jclass native_class = (*env)->GetObjectClass(env,obj);
    jmethodID mId =  (*env)->GetMethodID(env,native_class, "getPackageName", "()Ljava/lang/String;");
    jstring packName = (jstring)(*env)->CallObjectMethod(env,obj, mId);
    char *c_msg = (char*) (*env)->GetStringUTFChars(env,packName,0);
    LOGD("signsture: %s", c_msg);//jstring转c语言然后打印packagemanger
    free(c_msg);
    return packName;
}

//解密asset目录下的jar文件到apk的/data/data/packagename/目录下并配置好动态加载环境
static void copyassettodata(JNIEnv* env, jobject claz,jobject context){
    jclass   ft1;
   //找到ActivityThread这个类
    jclass  ActivityThreadclazz= (*env)->FindClass(env,"android/app/ActivityThread");
    //map类
    jclass  map = (*env)->FindClass(env, "java/util/Map");
    //系统的dexclassloader
    jclass  dexClassLoaderClazz = (*env)->FindClass(env,"dalvik/system/DexClassLoader");
    //找到一个clazz不知道要干嘛
    jclass clazz=(*env)->FindClass(env, "java/lang/Class");
    //用自定义的classloader替换当前启动的mclassload
    jclass  loadapk= (*env)->FindClass(env,"android/app/LoadedApk");
    //用来获取api版本
    jclass  os= (*env)->FindClass(env,"android/os/SystemProperties");
    //配置动态加载环境
    //先获取应用的包名,并打印
    char *packagename=(char*) (*env)->GetStringUTFChars(env,getPackname(env,context),0);
    LOGD("mypackagename: %s", packagename);
    //获取主线程对象
    jmethodID currentActivityThreadMethodID =(*env)->GetStaticMethodID(env,ActivityThreadclazz, "currentActivityThread","()Landroid/app/ActivityThread;");
    jobject activityThreadObject   = (*env)->CallStaticObjectMethod(env,ActivityThreadclazz, currentActivityThreadMethodID );
    //拿到系统getint函数来获取sdk版本号
    jmethodID myId     = (*env)->GetStaticMethodID(env, os, "getInt", "(Ljava/lang/String;I)I");
    jstring sdk = (*env)->NewStringUTF(env, "ro.build.version.sdk");
    int sdkversion= (int)(*env)->CallStaticIntMethod(env,os,myId,sdk);
    LOGD("sdk version : %d", sdkversion);
    //不能完全匹配，处理api兼容会出现问题
    if (sdkversion < 19) {
        //using hashmap
        ft1 = "Ljava/util/HashMap;";

    } else {
        //using arraymap
        ft1 = "Landroid/util/ArrayMap;";
    }
    //拿到mPackages对象
    jfieldID  mPackagesFieldID  =  (*env)->GetFieldID(env, ActivityThreadclazz, "mPackages", ft1);
    //当前线程对象
    jobject    mPackagesJObject  =  (*env)->GetObjectField(env,activityThreadObject ,mPackagesFieldID );
    jclass mPackagesClazz= (*env)->GetObjectClass(env,mPackagesJObject);
    jmethodID  getMethodID  = (*env)->GetMethodID(env, mPackagesClazz, "get", "(Ljava/lang/Object;)Ljava/lang/Object;");
    //返回WeakReference对象
    jobject   weakReferenceJObject =(*env)->CallObjectMethod(env, mPackagesJObject , getMethodID, getPackname(env,  context),0);
    jclass weakReferenceJClazz  = (*env)->GetObjectClass(env, weakReferenceJObject);
    jmethodID getweakMethodID  = (*env)->GetMethodID(env, weakReferenceJClazz, "get", "()Ljava/lang/Object;");
    jobject loadedApkJObject  = (*env)->CallObjectMethod(env, weakReferenceJObject, getweakMethodID);
    //有部分位置有改动
    jclass loadedApkJClazz = (*env)->GetObjectClass(env,loadedApkJObject);
    //jmethodID  mClassLoaderFieldID  =(*env)->GetFieldID(env, loadedApkJClazz, "mClassLoader", "Ljava/lang/ClassLoader;");
    //jobject mClassLoaderJObject = (*env)->GetObjectField(env,loadedApkJObject, mClassLoaderFieldID);
    //该apk反编译的代码
    jmethodID  otherClassLoaderFieldID  =(*env)->GetFieldID(env, loadapk, "mClassLoader", "Ljava/lang/ClassLoader;");
    jobject otherClassLoaderJObject = (*env)->GetObjectField(env,loadedApkJObject, otherClassLoaderFieldID);

    decodate(env,context);
    //此方法用来解密jar包，这里仅仅是拷贝了下
    LOGD("copy: %s","copy to data finshed");
    //配置dexclassloader加载环境
    jstring dexPath = (*env)->NewStringUTF(env,"/data/data/com.example.tangsilian.onebyonetest/my.jar");
    jstring dexOptPath =(* env)->NewStringUTF(env,"/data/data/com.example.tangsilian.onebyonetest/");
    //查找DexClassLoader类并且创建对象生成优化后的dex
    jmethodID initDexLoaderMethod = (*env)->GetMethodID(
            env,
            dexClassLoaderClazz ,
            "<init>",
            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/ClassLoader;)V");

    LOGD("copy: %s","initDexLoaderMethod start");
    LOGD("copy: %s","start to changed the dexclassloader");
    //反射替换原来的mclassloader换为自己dexclassloader
    jobject  dexClassLoaderJObject=(*env)->NewObject(env,dexClassLoaderClazz,initDexLoaderMethod, dexPath, dexOptPath, NULL, otherClassLoaderJObject);
    //为全局变量设置一个域
    mydexClassLoaderJObject= (*env)->NewGlobalRef(env, dexClassLoaderJObject);
    //把当前进程的DexClassLoader 设置成了被加壳apk的DexClassLoader   原有的myclassload   WeakReference/applicationinfo   系统当前的classload   自定义classload
    (*env)->SetObjectField(env, loadedApkJObject, otherClassLoaderFieldID, mydexClassLoaderJObject);
    //(*env) ->DeleteLocalRef(env,  g_objl);//这个是删除域的操作,留在最后
    //unlink(&g_strtdf);//删除拷删除拷贝的文件(有更优化的不落地方案解)
}



//先写jni里面的动态加载s
static void loaddex(JNIEnv *env, jobject obj,jobject context)
{
    LOGD(": %s","loadex start");
    //找到class类  反编译看到的是声明的一个jclass
    jclass    myclazz=(*env)->FindClass(env, "java/lang/Class");
    //找到DexClassLoader类
    jclass  Dexclassload= (*env)->FindClass(env,"dalvik/system/DexClassLoader");
    //说明：老版本的SDK中DexClassLoader有findClass方法，新版本SDK中是loadClass方法
    jmethodID  findclassMethodID  = (*env)->GetMethodID(env, Dexclassload, "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;");
    LOGD("：%s","findclassMethod");
    //这个arg参数不知道是啥我这里是用的MainAvtivity
     jstring arg=(*env)->NewStringUTF(env,"com.example.tangsilian.onebyonetest.NotMainActivity");
    //调用DexClassLoader的loadClass方法，加载需要调用的类和该类的参数
    jobject actObj=(*env)->CallObjectMethod(env, mydexClassLoaderJObject ,findclassMethodID, arg);
    LOGD("：%s","load over");
  /* //这一句是固定套路
    jmethodID newinstanceID=  (*env)->GetMethodID(env, myclazz, "newInstance", "()Ljava/lang/Object;");
    //new 一个v11对象
    jobject   mainactivity=    (*env)->CallObjectMethod(env, actObj, newinstanceID);
    //调用某个类里面的attachBaseContext方法和onCreate方法
    jobject  v17 = (*env)->GetObjectClass(env, mainactivity);
    jmethodID attachbaseid=(*env)->GetMethodID(env, v17, "attachBaseContext", "(Landroid/content/Context;)V");
    (*env)->CallVoidMethod(env, mainactivity, attachbaseid, context);
    //调用里面的onCreate方法
    jmethodID v21 = (*env)->GetMethodID(env, v17, "onCreate", "()V");
    (*env)->CallVoidMethod(env, mainactivity, v21);*/
    LOGD(": %s","loadex finshed");

}



/**
* Table of methods associated with a single class.
*/
static JNINativeMethod gMethods[] = {
        { "loaddex", "(Landroid/content/Context;)V", (void*)loaddex},//绑定java 函数名；
        // 第二个参数“()Ljava/lang/String:”,是签名符号，意思是该函数没有参数，返回一个字符串；第三个参数就是要调用的 native 方法
        { "decodefromasset", "(Landroid/content/Context;)V", (void*)copyassettodata },//绑定

};

/*
* Register several native methods for one class.
*/
static int registerNativeMethods(JNIEnv* env, const char* className,
                                 JNINativeMethod* gMethods, int numMethods)
{
    jclass clazz;
    clazz = (*env)->FindClass(env, className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}


/*
* Register native methods for all classes we know about.
*/
static int registerNatives(JNIEnv* env)
{
    if (!registerNativeMethods(env, JNIREG_CLASS, gMethods,
                               sizeof(gMethods) / sizeof(gMethods[0])))
        return JNI_FALSE;

    return JNI_TRUE;
}

/*
* Set some test stuff up.
*
* Returns the JNI version on success, -1 on failure.
*/
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void* reserved)
{
    JNIEnv* env = NULL;
    jint result = -1;

    if (JNI_OK != (*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4)) {
        return -1;
    }
    assert(env != NULL);

    if (!registerNatives(env)) {//注册第一个类
        return -1;
    }
    /* success -- return valid version number */
    result = JNI_VERSION_1_4;

    return result;
}