# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in F:\sdk\sdk\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}


### greendao
-keep class org.greenrobot.greendao.**{*;}
-keep public interface org.greenrobot.greendao.**
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

   # Gson
 -keep class com.google.gson.stream.** { *; }
 -keepattributes EnclosingMethod
 -keep class org.xz_sale.entity.**{*;}

      # OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
      # Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }

      # RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
          long producerIndex;
          long consumerIndex;
    }
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
          rx.internal.util.atomic.LinkedQueueNode producerNode;
      }
 -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
          rx.internal.util.atomic.LinkedQueueNode consumerNode;
      }
   ###rxjava retrofit

   -dontwarn javax.annotation.**
   -dontwarn javax.inject.**

     # EventBus
  -keepattributes *Annotation*
   -keepclassmembers class * {
      @org.greenrobot.eventbus.Subscribe <methods>;
      }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }
      # Only required if you use AsyncExecutor
  -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
      <init>(java.lang.Throwable);}


 -dontwarn org.androidannotations.**
 -keep class org.androidannotations.** {*;}

 -keepattributes Exceptions,InnerClasses

 -keepattributes Signature

           #如果引用了v4或者v7包
  -dontwarn android.support.**

 -keepattributes EnclosingMethod

            #如果有其它包有warning，在报出warning的包加入下面类似的-dontwarn 报名
 -dontwarn com.fengmap.*.**

           ## 注解支持
  -keepclassmembers class *{
              void *(android.view.View);
           }

           #保护注解
 -keepattributes *Annotation*
 -dontwarn org.apache.http.**
 -dontwarn org.springframework.**

 #枚举
  -keepclassmembers enum  * {
       public static **[] values();
       public static ** valueOf(java.lang.String);
   }

     # 不混淆bean，** 换成具体的类名则表示不混淆某个具体的类
     -dontwarn com.wzrd.m.bean.**
     -keep class com.wzrd.m.bean.** { *; }
     -keep class com.wzrd.m.v.view.** { *; }

     # Uncomment this to preserve the line number information for
     # debugging stack traces.
     -keepattributes SourceFile,LineNumberTable

     # If you keep the line number information, uncomment this to
     # hide the original source file name.
     -renamesourcefileattribute SourceFile

     -keepattributes Exceptions, Signature, InnerClasses