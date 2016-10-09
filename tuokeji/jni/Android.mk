LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := DumpDex.cy
LOCAL_SRC_FILES := DumpDex.cy.cpp

include $(BUILD_SHARED_LIBRARY)
