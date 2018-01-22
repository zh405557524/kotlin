package com.soul.library.http

import com.soul.library.utils.UIUtils
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @描述：网络请求工厂类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/22 11:07
 */
open class RetrofitFactory {

    var client: OkHttpClient.Builder? = null

    /**
     * 静态方法区
     */
    companion object {

        private var sRetrofitFactory: RetrofitFactory? = null

        /**
         * 获取工厂对象
         */
        fun getInstance(): RetrofitFactory {
            if (sRetrofitFactory == null) {
                synchronized(RetrofitFactory::class.java) {
                    if (sRetrofitFactory == null) {
                        sRetrofitFactory = RetrofitFactory()
                    }
                }
            }
            return sRetrofitFactory!!
        }

    }

    /**
     * 初始化网络结构
     */
    init {
        val cacheFile = File(UIUtils.getContext().cacheDir, "okhttp")
        val cache = Cache(cacheFile, 1024 * 1024 * 10)
        client = OkHttpClient
                .Builder()
                .connectTimeout(12, TimeUnit.SECONDS)//连接超时时间
                .writeTimeout(12, TimeUnit.SECONDS)//写入超时时间
                .cache(cache)
    }

    /**
     *创建Retrofit
     * @param baseUrl 服务器地址
     * @param interceptor
     */
    open fun createRetrofit(baseUrl: String, vararg interceptor: Interceptor): Retrofit {
        for (i in interceptor) {
            client?.addInterceptor(i)
        }
        return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(client?.build()!!)
                .build()
    }

}