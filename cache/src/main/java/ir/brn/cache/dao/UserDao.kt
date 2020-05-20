package ir.brn.cache.dao

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single
import ir.brn.cache.db.UserConstants
import ir.brn.cache.model.CachedUser

@Dao
abstract class UserDao {

    @Query(UserConstants.SELECT_USER)
    abstract fun selectUser(): Observable<CachedUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUser(cachedUser: CachedUser)

    @Update
    abstract fun updateUser(cachedUser: CachedUser)

    @Query(UserConstants.DELETE_USER)
    abstract fun deleteUser()

    @Query(UserConstants.USER_EXISTS)
    abstract fun checkUserExists(): Single<Int>

}