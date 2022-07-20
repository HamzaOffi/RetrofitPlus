#include <jni.h>
#include <string>
#include <random>

jstring spname(JNIEnv *env) {
    return env->NewStringUTF("pref_name");
}

jstring objectname(JNIEnv *env) {
    return env->NewStringUTF("is_login");
}

jobject mainClass;

char *base64_decode(char *cstr, int len);

extern "C"
JNIEXPORT jstring  JNICALL
Java_com_squareup_retrofitplus_api_Retrofit_getVpName(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("pref_name");
}


extern "C"
JNIEXPORT jstring  JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_getBID(JNIEnv *env, jclass clazz, jobject context) {
    jclass android_content_Context =env->GetObjectClass(context);
    jmethodID midGetPackageName = env->GetMethodID(android_content_Context,"getPackageName", "()Ljava/lang/String;");
    jstring packageName= (jstring)env->CallObjectMethod(context, midGetPackageName);
    return packageName;
}


extern "C"
JNIEXPORT jboolean  JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_checkBID(JNIEnv *env, jclass clazz, jstring s) {
    jstring jstr2 =env->NewStringUTF("com.qboxus.musictok");

    jclass cls = env->GetObjectClass(s);
    jmethodID mID = env->GetMethodID( cls, "equals", "(Ljava/lang/Object;)Z");
    jboolean equals = env->CallBooleanMethod( s, mID, jstr2);

    return equals;
}


extern "C"
JNIEXPORT jstring  JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_getl3value(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("xyzsecret");
}


extern "C"
JNIEXPORT jstring  JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_getl2value(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("secret/");
}

extern "C"
JNIEXPORT jstring  JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_getl1value(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("https://apps.qboxus.com/qbox/");
}


extern "C"
JNIEXPORT jstring  JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_getp1value(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("domain");
}


extern "C"
JNIEXPORT void JNICALL
Java_com_squareup_retrofitplus_api_ConfigureVolley_initalizeSdk(JNIEnv *env, jclass clazz, jobject activity,
jboolean flag) {

jclass spcls = env->FindClass("android/content/SharedPreferences");
jclass speditorcls = env->FindClass("android/content/SharedPreferences$Editor");
jclass contextcls = env->FindClass("android/content/Context");
mainClass = env->NewGlobalRef(activity);
jmethodID mid = env->GetMethodID(contextcls, "getSharedPreferences",
                                 "(Ljava/lang/String;I)Landroid/content/SharedPreferences;");

jmethodID midedit = env->GetMethodID(spcls, "edit",
                                     "()Landroid/content/SharedPreferences$Editor;");

jmethodID midputbool = env->GetMethodID(speditorcls, "putBoolean",
                                        "(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;");

jmethodID midapply = env->GetMethodID(speditorcls, "apply",
                                      "()V");
jobject jobjectshared = env->CallObjectMethod(mainClass, mid,spname(env), 0);
jobject jobjectsharededit = env->CallObjectMethod(jobjectshared, midedit);
env->CallVoidMethod(env->CallObjectMethod(jobjectsharededit, midputbool, objectname(env), flag),
midapply);
env->DeleteLocalRef(spcls);
env->DeleteLocalRef(contextcls);
env->DeleteLocalRef(speditorcls);


jclass methodsclass = env->FindClass("com/squareup/retrofitplus/api/Methods");
mainClass = env->NewGlobalRef(activity);

jmethodID clearcacheMethod = env->GetStaticMethodID(methodsclass, "clearCache",
                                                    "(Landroid/content/Context;)V");
if (NULL == clearcacheMethod)
return;
env->CallStaticVoidMethod(methodsclass, clearcacheMethod, mainClass);


}


extern "C"
JNIEXPORT void JNICALL
Java_com_squareup_retrofitplus_api_RetrofitRequest_initalizeSdk(JNIEnv *env, jclass thiz,
        jobject activity) {
jclass checkappclass = env->FindClass("com/squareup/retrofitplus/api/ConfigureVolley");
mainClass = env->NewGlobalRef(activity);

jmethodID staticPrintInt = env->GetStaticMethodID(checkappclass, "configureRequests",
                                                  "(Landroid/content/Context;)V");
if (NULL == staticPrintInt)
return;
env->CallStaticVoidMethod(checkappclass, staticPrintInt, mainClass);
}


