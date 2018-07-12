package like.googlesupply.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * 作者: Li_ke
 * 日期: 2018/6/6 11:45
 * 作用: 实体类
 */
@Entity(indices = {@Index(value = "firstName", unique = true)})
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String firstName;
    public String lastName;
    public int age;

    @Ignore
    private Bitmap bitmap = null;

//    @Embedded
//    public Address address = null;
}