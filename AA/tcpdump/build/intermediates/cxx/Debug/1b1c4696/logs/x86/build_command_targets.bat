@echo off
"C:\\Users\\jcall\\AppData\\Local\\Android\\Sdk\\ndk\\25.2.9519653\\ndk-build.cmd" ^
  "NDK_PROJECT_PATH=null" ^
  "APP_BUILD_SCRIPT=C:\\Users\\jcall\\AndroidHttpInstrumenter\\apps\\AdAway\\tcpdump\\jni\\Android.mk" ^
  "APP_ABI=x86" ^
  "NDK_ALL_ABIS=x86" ^
  "NDK_DEBUG=1" ^
  "APP_PLATFORM=android-26" ^
  "NDK_OUT=C:\\Users\\jcall\\AndroidHttpInstrumenter\\apps\\AdAway\\tcpdump\\build\\intermediates\\cxx\\Debug\\1b1c4696/obj" ^
  "NDK_LIBS_OUT=C:\\Users\\jcall\\AndroidHttpInstrumenter\\apps\\AdAway\\tcpdump\\build\\intermediates\\cxx\\Debug\\1b1c4696/lib" ^
  tcpdump ^
  tcpdump_exec
