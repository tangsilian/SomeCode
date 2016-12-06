//
// Created by tangsilian on 2016-11-30.
//
#ifndef JNI_UTIL_H
#define JNI_UTIL_H
#include <jni.h>
#include <android/log.h>
/*#include <string>
using namespace std;*/
#define LOG_TAG "JNI_LOG"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
class JNIUtil
{

public:
    static void SetJavaVm(JavaVM*);
public:
    JNIUtil();
    ~JNIUtil();
    JNIEnv* GetJNIEnv();
    void Detach();
    //string Jstring2String(jstring jstr);
    jstring String2Jstring(const char* str);
private:
    static JavaVM* m_sJVM;
public:
    bool m_bAttach;
};


#endif
