 LOCAL_PATH := $(call my-dir)   

 include $(CLEAR_VARS)          

 LOCAL_MODULE    := DexPacker    
 LOCAL_SRC_FILES := DexPacker.c    
 LOCAL_LDLIBS:=-llog -landroid
 include $(BUILD_SHARED_LIBRARY) 