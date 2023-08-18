LOCAL_PATH := $(call my-dir)

ifeq ($(TARGET_ARCH_ABI),x86)

include $(CLEAR_VARS)
LOCAL_MODULE := crypto
LOCAL_SRC_FILES := C:/Users/jcall/.gradle/caches/transforms-3/18f9defc0c53f79ea334dc8688e39ce9/transformed/openssl-1.1.1q-beta-1/prefab/modules/crypto/libs/android.x86/libcrypto.so
LOCAL_EXPORT_C_INCLUDES := C:/Users/jcall/.gradle/caches/transforms-3/18f9defc0c53f79ea334dc8688e39ce9/transformed/openssl-1.1.1q-beta-1/prefab/modules/crypto/include
LOCAL_EXPORT_SHARED_LIBRARIES :=
LOCAL_EXPORT_STATIC_LIBRARIES :=
LOCAL_EXPORT_LDLIBS :=
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := ssl
LOCAL_SRC_FILES := C:/Users/jcall/.gradle/caches/transforms-3/18f9defc0c53f79ea334dc8688e39ce9/transformed/openssl-1.1.1q-beta-1/prefab/modules/ssl/libs/android.x86/libssl.so
LOCAL_EXPORT_C_INCLUDES := C:/Users/jcall/.gradle/caches/transforms-3/18f9defc0c53f79ea334dc8688e39ce9/transformed/openssl-1.1.1q-beta-1/prefab/modules/ssl/include
LOCAL_EXPORT_SHARED_LIBRARIES :=
LOCAL_EXPORT_STATIC_LIBRARIES :=
LOCAL_EXPORT_LDLIBS :=
include $(PREBUILT_SHARED_LIBRARY)

endif  # x86

