# Copyright (C) 2009 The Android Open Source Project
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
LOCAL_PATH := $(call my-dir)
CRYSTAX_PATH := C:/Users/d1004/AppData/Local/Android/pybridge
include $(CLEAR_VARS)
LOCAL_MODULE:= news
LOCAL_SRC_FILES := news.c
LOCAL_LDLIBS := -llog
LOCAL_SHARED_LIBRARIES := python3.8m
include $(BUILD_SHARED_LIBRARY)
include $(CLEAR_VARS)
LOCAL_MODULE    := python3.8m
LOCAL_SRC_FILES := $(CRYSTAX_PATH)/python-for-android/build/other_builds/python3/armeabi-v7a__ndk_target_21/python3/android-build/libpython3.8m.so
LOCAL_EXPORT_CFLAGS := -I $(CRYSTAX_PATH)/python-for-android/build/other_builds/python3/armeabi-v7a__ndk_target_21/python3/Include
include $(PREBUILT_SHARED_LIBRARY)
