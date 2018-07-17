package like.googlesupply.roomKotlin

import android.arch.persistence.room.*
import android.graphics.Bitmap
import like.googlesupply.room.UserEntity

/**
 * 声明 Entity(实体)类
 */
@Entity(
        foreignKeys = [(ForeignKey(entity = UserEntity::class,//foreignKeys(外键)
                parentColumns = ["id"],//父列-UserEntity.id
                childColumns = ["userId"],//子列-BookEntity.id，两者相互关联
                onDelete = ForeignKey.CASCADE))],//使当父节点删除时，子节点对应也随之删除
        indices = [(Index(value = ["id"], unique = true))])//使用索引快速通过"id"查找
data class BookEntity(

        @PrimaryKey(autoGenerate = true)//PrimaryKey(主键)；autoGenerate(自增)
        var id: Int? = null,

        var userId: Int = 0,
        var pageSize: Int = 0,
        var name: String? = null,

        @Ignore//Ignore(忽略)
        var img: Bitmap? = null
)