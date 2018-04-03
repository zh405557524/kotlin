package com.soul.substantial.ui.Scan

import android.Manifest
import android.view.View
import android.widget.Toast
import com.soul.substantial.MainActivity
import com.soul.substantial.R
import com.soul.substantial.base.BaseActivity
import com.soul.substantial.bean.MusicInfo
import com.soul.substantial.config.AppConfig.IS_SCAN
import com.soul.substantial.util.scan.MusicUtil
import com.soul.substantial.util.scan.OnScanListener
import com.soul.substantial.utils.SPUtils
import com.soul.substantial.utils.ToastUtil
import com.soul.substantial.utils.UIUtils
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.PermissionListener
import kotlinx.android.synthetic.main.activity_scan_media.*
import org.jetbrains.anko.startActivity

/**
 * 扫描本地媒体文件
 */
class ScanMediaActivity : BaseActivity(), View.OnClickListener {


    private lateinit var mMusicUtil: MusicUtil
    private var count: Int = 0
    override fun getLayout(): Int? {
        return R.layout.activity_scan_media
    }

    override fun initView() {
        toolbar.title = "歌曲搜索"
    }

    override fun initData() {
        mMusicUtil = MusicUtil()

        AndPermission.with(this).permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .callback(object : PermissionListener {
                    override fun onSucceed(i: Int, list: List<String>) {
                        //开始搜索本地文件
                        mMusicUtil.startScan(mContext)

                    }

                    override fun onFailed(i: Int, list: List<String>) {
                        Toast.makeText(mContext, "无存储权限，无法扫描歌曲", Toast.LENGTH_SHORT).show()
                    }
                }).start()
    }

    override fun initEvent() {
        btnEnterHome.setOnClickListener(this)
        mMusicUtil.setListener(object : OnScanListener {
            override fun onSuccess() {
                UIUtils.getHandler().post { btnEnterHome.visibility = View.VISIBLE }
                SPUtils().putInt(IS_SCAN, 1)
            }

            override fun onFail() {
                UIUtils.getHandler().post { ToastUtil.showShort("扫描错误") }
            }

            override fun onScan(musicInfo: MusicInfo?) {

                UIUtils.getHandler().post {
                    tvScanNow.text = musicInfo?.title
                    tvScanCount.text = (++count).toString()
                }
            }
        })
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnEnterHome -> {
                startActivity<MainActivity>()//转跳奥main
                finish()
            }

        }

    }

}
