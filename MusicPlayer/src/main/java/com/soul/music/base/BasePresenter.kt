package com.soul.music.base

import com.soul.library.mvp.IModel
import com.soul.library.mvp.IPresenter
import com.soul.library.mvp.IView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

/**
 * @描述：presenter的基类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/19 10:05
 */
open abstract class BasePresenter<V : IView, M : IModel> : IPresenter {


    private var mCompositeSubscription: CompositeDisposable? = null

    private var mActReference: WeakReference<IView>? = null

    protected var mView: V? = null

    protected var mModel: M? = null

    override fun attachView(view: IView) {
        mActReference = WeakReference(view)
        mView = mActReference!!.get() as V?
    }

    open fun addSubscription(s: Disposable) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = CompositeDisposable()

        }
        mCompositeSubscription?.add(s)
    }

    override fun detachView() {
        mActReference?.clear()
        mActReference = null
        mCompositeSubscription?.clear()
    }
}