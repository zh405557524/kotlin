package com.soul.music.util

import android.text.TextUtils
import com.litesuits.orm.LiteOrm
import com.litesuits.orm.db.DataBaseConfig
import com.litesuits.orm.db.assit.QueryBuilder
import com.litesuits.orm.db.assit.WhereBuilder
import com.litesuits.orm.db.model.ColumnsValue
import com.litesuits.orm.db.model.ConflictAlgorithm
import com.soul.music.utils.UIUtils
import java.util.*

/**
 * @描述：数据库帮助类
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/4/2 15:36
 */
class DBHelp {

    /**
     * 是否开启deBUg模式
     */
    private val debugged = true
    /**
     * 版本
     */
    private val dbVersion = 1

    /**
     * 数据库名称
     */
    private val dbName = UIUtils.getPackageName() + ".db"


    private  var mLiteOrm: LiteOrm? = null

    //静态方法区
    companion object {

        private var sDBHelp: DBHelp? = null

        fun getInstance(): DBHelp? {
            if (sDBHelp == null) {
                synchronized(DBHelp.javaClass) {
                    if (sDBHelp == null) {
                        sDBHelp = DBHelp()
                    }
                }
            }

            return sDBHelp
        }
    }

    init {
        if (mLiteOrm == null) {
            val config = DataBaseConfig(UIUtils.getContext(), dbName)
            config.debugged = debugged // open the log
            config.dbVersion = dbVersion // set database version
            config.onUpdateListener = null // set database update listener
            mLiteOrm = LiteOrm.newSingleInstance(config)
        }
    }


    /**
     * 存储数据
     *
     * @param tClass        类的字节码
     * @param t             类的实体
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
    </T> */
    fun <T> save(tClass: Class<T>, t: T, selection: String, selectionArgs: String) {
        val query = query(tClass, selection, selectionArgs)
        if (query == null || query.isEmpty()) {
            mLiteOrm?.save(t)
        } else {
            mLiteOrm?.update(t)
        }
    }


    fun <T> save(tClass: Class<T>, t: T, selection: String, selectionArgs: Int) {
        val query = mLiteOrm?.query(QueryBuilder(tClass).where(selection + "=?", selectionArgs))
        if (query == null || query!!.isEmpty()) {
            mLiteOrm?.save(t)
        } else {
            mLiteOrm?.update(t)
        }
    }

    fun <T> save(tClass: Class<T>, t: T, selection: Array<String>, selectionArgs: Array<String>) {
        save(tClass, t, selection, selectionArgs, null)
    }

    fun <T> save(tClass: Class<T>, t: T, selection: Array<String>, selectionArgs: Array<String>, columnsValue: ColumnsValue?) {
        val query = mLiteOrm?.query(getTQueryBuilder(tClass, selection, selectionArgs))
        if (query == null || query!!.isEmpty()) {
            mLiteOrm?.save(t)
        } else {
            delete(tClass, selection, selectionArgs)
            mLiteOrm?.insert(t)
        }
    }


    fun <T> save(list: List<T>) {
        mLiteOrm?.save(list)
    }

    /**
     * 删除数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
    </T> */
    fun <T> delete(tClass: Class<T>, selection: String, selectionArgs: String) {
        mLiteOrm?.delete(getWhereBuilder(tClass, selection, selectionArgs))
    }

    /**
     * 删除数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
    </T> */
    fun <T> delete(tClass: Class<T>, selection: Array<String>, selectionArgs: Array<String>): Int? {
        return mLiteOrm?.delete(getWhereBuilder(tClass, selection, selectionArgs))
    }

    /**
     * 修改数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
    </T> */
    fun <T> upData(tClass: Class<T>, selection: String, selectionArgs: String, columnsValue: ColumnsValue) {
        upData(getWhereBuilder(tClass, selection, selectionArgs), columnsValue)
    }

    fun upData(queryBuilder: WhereBuilder, columnsValue: ColumnsValue) {
        val update = mLiteOrm?.update(queryBuilder, columnsValue, ConflictAlgorithm.Abort)
    }

    /**
     * 查询数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return 当前类的list集合
    </T> */
    fun <T> query(tClass: Class<T>, selection: String, selectionArgs: String): ArrayList<T>? {
        return mLiteOrm?.query(getQueryBuilder(tClass, selection, selectionArgs))
    }


    /**
     * 查询数据
     *
     * @param tClass 类的字节码
     * @param <T>
     * @return 所有类的集合
    </T> */
    fun <T> query(tClass: Class<T>): ArrayList<T>? {
        return mLiteOrm?.query(tClass)
    }

    /**
     * @param tClass 查询生成器
     * @param <T>
     * @return
    </T> */
    fun <T> query(tClass: QueryBuilder<T>): ArrayList<T>? {
        return mLiteOrm?.query(tClass)
    }


    /**
     * 查询数据
     *
     * @param tClass    类的字节码
     * @param orderDesc 排序的字段
     * @param <T>
     * @return 按照orderDesc排序的所有类的集合
    </T> */
    fun <T> query(tClass: Class<T>, orderDesc: String): ArrayList<T>? {
        val tQueryBuilder = QueryBuilder(tClass)
        tQueryBuilder.appendOrderDescBy(orderDesc)
        return mLiteOrm?.query(tQueryBuilder)
    }

    /**
     * 查询数据
     *
     * @param tClass    类的字节码
     * @param orderDesc 排序的字段
     * @param <T>
     * @return 按照orderDesc排序的所有类的集合
    </T> */
    fun <T> query(tClass: Class<T>, orderDesc: String, startPosition: Int): ArrayList<T>? {
        val tQueryBuilder = QueryBuilder(tClass)
        tQueryBuilder.appendOrderDescBy(orderDesc)
        tQueryBuilder.limit(startPosition.toString() + "")
        return mLiteOrm?.query(tQueryBuilder)
    }

    /**
     * 查询数据
     *
     * @param tClass    类的字节码
     * @param orderDesc 排序的字段
     * @param <T>
     * @return 按照orderDesc排序的所有类的集合
    </T> */
    fun <T> query(tClass: Class<T>, orderDesc: String, startPosition: Int, endPosition: Int): ArrayList<T>? {
        val tQueryBuilder = QueryBuilder(tClass)
        tQueryBuilder.appendOrderDescBy(orderDesc)
        tQueryBuilder.limit(startPosition, endPosition)
        return mLiteOrm?.query(tQueryBuilder)
    }

    /**
     * 查询数据
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return 当前类的list集合
    </T> */
    fun <T> query(tClass: Class<T>, selection: Array<String>, selectionArgs: Array<String>): ArrayList<T>? {
        return mLiteOrm?.query(getTQueryBuilder(tClass, selection, selectionArgs))
    }


    /**
     * 获取查询参数
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return WhereBuilder
    </T> */
    fun <T> getWhereBuilder(tClass: Class<T>, selection: String, selectionArgs: String): WhereBuilder {
        return WhereBuilder(tClass).where(selection + " = ?", selectionArgs)
    }

    /**
     * 获取查询参数
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return WhereBuilder
    </T> */
    fun <T> getWhereBuilder(tClass: Class<T>, selection: Array<String>?, selectionArgs: Array<String>?): WhereBuilder {
        val whereBuilder = WhereBuilder(tClass)
        if (selection != null && selectionArgs != null) {
            for (i in selection.indices) {
                if (i == 0) {
                    whereBuilder.where(selection[i] + "=?", selectionArgs[i])
                } else {
                    whereBuilder.and(selection[i] + "=?", selectionArgs[i])
                }
            }
        }
        return whereBuilder
    }


    /**
     * 获取查询参数
     *
     * @param tClass        类的字节码
     * @param selection     区分类的标签的形参
     * @param selectionArgs 区分类的标签的实参
     * @param <T>           类
     * @return QueryBuilder
    </T> */
    fun <T> getQueryBuilder(tClass: Class<T>, selection: String, selectionArgs: String): QueryBuilder<T> {

        return getTQueryBuilder(tClass, arrayOf(selection), arrayOf(selection))
    }

    private fun <T> getTQueryBuilder(tClass: Class<T>, selection: Array<String>, selectionArgs: Array<String>): QueryBuilder<T> {
        return getTQueryBuilder(tClass, selection, selectionArgs, null
        )
    }

    private fun <T> getTQueryBuilder(tClass: Class<T>, selection: Array<String>?, selectionArgs: Array<String>?, order: String?): QueryBuilder<T> {
        val tQueryBuilder = QueryBuilder(tClass)
        if (!TextUtils.isEmpty(order)) {
            tQueryBuilder.appendOrderDescBy(order)
        }
        if (selection != null && selectionArgs != null) {
            for (i in selection.indices) {
                if (i == 0) {
                    tQueryBuilder.where(selection[i] + "=?", selectionArgs[i])
                } else {
                    tQueryBuilder.whereAnd(selection[i] + "=?", selectionArgs[i])
                }
            }
        }
        return tQueryBuilder
    }

}