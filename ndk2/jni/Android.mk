LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_ARM_MODE := arm
LOCAL_MODULE    := theone
LOCAL_SRC_FILES := tangsilian.c
include $(BUILD_SHARED_LIBRARY)
