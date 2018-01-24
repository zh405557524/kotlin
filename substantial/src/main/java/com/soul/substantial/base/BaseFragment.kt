package com.soul.library.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soul.substantial.utils.FragmentUserVisibleController
import org.jetbrains.anko.AnkoLogger

/**
 * @描述：Fragment的基类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/1/19 16:33
 */
abstract class BaseFragment : Fragment(), AnkoLogger, FragmentUserVisibleController.UserVisibleCallback {

    val STATE_NONE = -1            // 默认状态
    val STATE_LOADING = 0            // 加载中
    val STATE_ERROR = 1            // 错误
    val STATE_EMPTY = 2            // 空
    val STATE_SUCCESS = 3            // 成功

    protected var mCurState = STATE_NONE

    /**
     * 是否重置状态为默认
     */
    private var isReset: Boolean = false
    /**
     * 根布局View
     */
    protected var mView: View? = null

    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private var hasCreateView: Boolean = false
    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private var isFragmentVisible: Boolean = false


    /**
     * Fragment显示隐藏的控制类
     */
    private val userVisibleController: FragmentUserVisibleController? = FragmentUserVisibleController(this, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariable()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = getRootView()
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
        initEvent()
    }


    private fun initVariable() {
        hasCreateView = false
        isFragmentVisible = false
    }

    /**
     * 获取根布局
     */
    abstract fun getRootView(): View

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化方法
     */
    protected fun initEvent() {

    }

    /**
     * 加载网络请求
     */
    protected fun loadData() {

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleController != null) {
            userVisibleController.setUserVisibleHint(isVisibleToUser)
        }
        if (mView == null) {
            return
        }
        hasCreateView = true
        if (isVisibleToUser) {
            onFragmentVisibleChange(true)
            isFragmentVisible = true
            return
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false)
            isFragmentVisible = false
        }
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 [.setUserVisibleHint]一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     * false 可见  -> 不可见
     */
    protected fun onFragmentVisibleChange(isVisible: Boolean) {
        if (isReset) {
            mCurState = STATE_NONE
        }
        if (isVisible) {
            if (mCurState == STATE_NONE || mCurState == STATE_ERROR || mCurState == STATE_EMPTY) {
                loadData()
                mCurState = STATE_LOADING
            }
        }
    }


    override fun setWaitingShowToUser(waitingShowToUser: Boolean) {
        userVisibleController?.setWaitingShowToUser(waitingShowToUser)
    }

    override fun isWaitingShowToUser(): Boolean {
        if (userVisibleController != null) {
            return userVisibleController.isWaitingShowToUser()
        }
        return false
    }

    override fun isVisibleToUser(): Boolean {
        if (userVisibleController != null) {
            return userVisibleController.isVisibleToUser()
        }
        return false
    }

    override fun callSuperSetUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onVisibleToUserChanged(isVisibleToUser: Boolean, invokeInResumeOrPause: Boolean) {

    }

    /**
     * 设置每次界面可见时，是否重置当前状态{ }为默认状态
     *
     * @return
     */
    protected fun setResetState(b: Boolean) {
        isReset = b
    }

}