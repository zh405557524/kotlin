package com.soul.substantial.bean

import com.litesuits.orm.db.annotation.Column
import com.litesuits.orm.db.annotation.PrimaryKey
import com.litesuits.orm.db.enums.AssignType

/**
 * @描述：TODO
 * @作者：祝明
 * @项目名:player
 * @创建时间：2018/4/3 10:08
 */
open class BaseBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id")
    var _id: Long = 0
}