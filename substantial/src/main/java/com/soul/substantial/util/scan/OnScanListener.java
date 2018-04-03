package com.soul.substantial.util.scan;

import com.soul.substantial.bean.MusicInfo;

/**
 * @author jerry
 * @date 2016-01-19
 */
public interface OnScanListener {
    void onSuccess();

    void onFail();

    //扫描后
    void onScan(MusicInfo musicInfo);
}
