cmake_minimum_required(VERSION 3.10)
add_library(
        news
        SHARED
        jni/news)

find_library( log-lib
        log )

target_link_libraries( news
        ${log-lib} )