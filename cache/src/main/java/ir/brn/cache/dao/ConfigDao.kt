package ir.brn.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ir.brn.cache.db.ConfigConstans
import ir.brn.cache.model.Config

@Dao
abstract class ConfigDao {

    @Query(ConfigConstans.SELECT_CONFIG)
    abstract fun selectConfig(name:String): Single<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)
}