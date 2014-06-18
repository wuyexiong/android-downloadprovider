/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.providers.downloads;

import android.net.Uri;
import android.os.Environment;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import tree.love.providers.downloads.DownloadManager;
import tree.love.providers.downloads.DownloadManager.Request;
import tree.love.providers.downloads.Downloads;
import tree.love.providers.downloads.Helpers;

/**
 * This test exercises methods in the {@Helpers} utility class.
 */
@LargeTest
public class HelpersTest extends AndroidTestCase {

    public HelpersTest() {
    }

    public void testGetFullPath() throws Exception {
        String hint = "http://tree.love.providers.downloads/test.apk";

        // Test that we never change requested filename.
        String fileName0 = Helpers.generateSaveFile(getContext(), hint, hint, "video/mp4", "",
                "null", Downloads.Impl.DESTINATION_FILE_URI, 10100,
                new tree.love.providers.downloads.StorageManager(getContext()));
        Log.d("", "target file name : " + fileName0);
        // String fileName = Helpers.getFullPath(
        // hint,
        // "video/mp4", // MIME type corresponding to file extension .mp4
        // Downloads.Impl.DESTINATION_FILE_URI,
        // null);
        // assertEquals(hint, fileName);
    }

    public void addFileDownloadTest() throws Exception {

        String downloadUrl = "http://cc.mir.wdjcdn.com/files/release2/WanDouJia_2.76.0.6280_homepage.exe";
        DownloadManager downloadManager = new DownloadManager(
                getContext().getContentResolver(), getContext().getPackageName());

        DownloadManager.Request request = new Request(Uri.parse(downloadUrl));
        // request.addRequestHeader("User-Agent", "Android"); // 添加一个Http请求报头，
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);

        // request.setTitle("小豌豆" + i);
        request.setAllowedOverRoaming(false);
        request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                "WanDouJia_2.76.0.6280_homepage.exe");

        // 设置mime类型，这里看服务器配置，一般国家化的都为utf-8编码。
        // request.setMimeType();
        // request.setVisibleInDownloadsUi(true); // 设置下载管理类在处理过程中的界面是否显示
        downloadManager.enqueue(request);
    }

}
