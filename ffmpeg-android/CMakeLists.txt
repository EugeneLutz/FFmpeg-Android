# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html


# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)


set(ffmpeg_DIR ${CMAKE_SOURCE_DIR}/src/main/jniLibs/ffmpeg)
set(cpp_dir ${CMAKE_SOURCE_DIR}/src/main/cpp)

add_library(avutil SHARED IMPORTED)
set_target_properties(avutil PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libavutil.so)

add_library(swresample SHARED IMPORTED)
set_target_properties(swresample PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libswresample.so)

add_library(avcodec SHARED IMPORTED)
set_target_properties(avcodec PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libavcodec.so)

add_library(avformat SHARED IMPORTED)
set_target_properties(avformat PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libavformat.so)

add_library(swscale SHARED IMPORTED)
set_target_properties(swscale PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libswscale.so)

add_library(avfilter SHARED IMPORTED)
set_target_properties(avfilter PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libavfilter.so)

add_library(avdevice SHARED IMPORTED)
set_target_properties(avdevice PROPERTIES IMPORTED_LOCATION ${ffmpeg_DIR}/${ANDROID_ABI}/libavdevice.so)


include_directories(${ffmpeg_DIR}/${ANDROID_ABI}/include/)


# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library(nativelib SHARED
        ${cpp_dir}/avformat-av_format_context.cpp
        ${cpp_dir}/avformat-av_input_format.cpp
        ${cpp_dir}/avformat-av_output_format.cpp
        ${cpp_dir}/avformat-av_io.cpp
        ${cpp_dir}/avformat-av_stream.cpp
        ${cpp_dir}/avcodec-av_codec_parameters.cpp
        ${cpp_dir}/avformat-av_io_context.cpp
        ${cpp_dir}/avcodec-av_packet.cpp
        ${cpp_dir}/avutil-mathematics.cpp
        ${cpp_dir}/avfilter-av_filter.cpp
        ${cpp_dir}/avfilter-av_filter_pad.cpp
        ${cpp_dir}/avutil-av_class.cpp
        ${cpp_dir}/avutil-av_buffer_ref.cpp
        ${cpp_dir}/avfilter-av_filter_context.cpp
        ${cpp_dir}/avfilter-av_filter_link.cpp
        ${cpp_dir}/opaque_iterator.cpp
        ${cpp_dir}/swresample-swr_context.cpp
        ${cpp_dir}/avutil-av_rational.cpp
        ${cpp_dir}/swscale-sw_scale.cpp
        ${cpp_dir}/swscale-sws_vector.cpp
        ${cpp_dir}/swscale-sws_context.cpp
        ${cpp_dir}/swscale-sws_filter.cpp
        ${cpp_dir}/avdevice-av_device.cpp
        ${cpp_dir}/avdevice-av_device_info_list.cpp
        ${cpp_dir}/avutil-helper.cpp
        ${cpp_dir}/avcodec-helper.cpp
        ${cpp_dir}/avcodec-av_codec_descriptor.cpp
        ${cpp_dir}/avcodec-av_codec_context.cpp
        ${cpp_dir}/avcodec-av_codec.cpp
        ${cpp_dir}/avutil-raw_data.cpp
        ${cpp_dir}/avutil-av_dictionary.cpp
        ${cpp_dir}/avutil-av_dictionary_entry.cpp
        ${cpp_dir}/cstruct_wrapper.cpp
        ${cpp_dir}/avdevice-av_device_rect.cpp
        ${cpp_dir}/avdevice-helper.cpp
        ${cpp_dir}/avdevice-av_device_info.cpp
        ${cpp_dir}/avfilter-helper.cpp
        ${cpp_dir}/avfilter-av_filter_graph.cpp
        ${cpp_dir}/avfilter-av_filter_in_out.cpp
        ${cpp_dir}/avutil-av_frame.cpp
        ${cpp_dir}/avutil-av_util.cpp
        ${cpp_dir}/ExecuteResult.cpp
        ${cpp_dir}/remuxing_sample.cpp)


# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)


# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        nativelib

        avutil
        swresample
        avcodec
        avformat
        swscale
        avfilter
        avdevice

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})