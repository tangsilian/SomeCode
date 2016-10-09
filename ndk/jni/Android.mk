LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_CFLAGS += -pie -fPIE
LOCAL_LDFLAGS += -pie -fPIE
LOCAL_ARM_MODE := arm
LOCAL_MODULE    := tangsilian
LOCAL_SRC_FILES := tangsilian.c

include $(BUILD_EXECUTABLE)
