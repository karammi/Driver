package ir.brn.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.brn.cache.db.DriverDatabase
import ir.brn.cache.db.UserConstants
import ir.brn.cache.mapper.CachedUserMapper
import ir.brn.cache.model.Config
import ir.brn.data.model.user.LoginResponseEntity
import ir.brn.data.model.user.UserEntity
import ir.brn.data.repository.user.UserCache
import javax.inject.Inject

class UserCacheImpl @Inject constructor(
        private val driverDatabase: DriverDatabase,
        private val mapper: CachedUserMapper,
        private val cachedLoginResponseMapper: CachedLoginResponseMapper) : UserCache {

    override fun clearCache(): Completable {
        return Completable.defer {
            driverDatabase.userDao().deleteUser()
            Completable.complete()
        }
    }

    override fun saveUser(userEntity: UserEntity): Completable {
        return Completable.defer {
            driverDatabase
                    .userDao()
                    .insertUser(
                            mapper.mapToCached(userEntity)
                    )
            Completable.complete()
        }
    }

    override fun getUser(): Observable<UserEntity> {
        return driverDatabase
                .userDao()
                .selectUser()
                .map {
                    mapper.mapFromCached(it)
                }
    }

    override fun getUserToken(): Observable<String> {
        return driverDatabase
                .loginResponseDao()
                .selectLoginResponse()
                .map {
                    it.token.toString()
                }

    }

    override fun areUserCached(): Single<Boolean> {
        return driverDatabase
                .userDao()
                .checkUserExists()
                .map {
                    it > 0
                }
    }

    override fun setLastCacheTime(time: Long): Completable {
        return Completable.defer {
            driverDatabase
                    .configDao()
                    .insertConfig(Config(10,UserConstants.TABLE_NAME,time))
            Completable.complete()
        }
    }

    override fun isCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()


        val expirationTime = (7 * 24 * 60 * 1000).toLong()




        return driverDatabase
                .configDao()
                .selectConfig("%${UserConstants.TABLE_NAME}%")
                .onErrorReturn { Config(lastCacheTime = 0, name = "") }
                .map {
                    println("currentTime=> " + (currentTime - it.lastCacheTime))
                    currentTime - it.lastCacheTime > expirationTime }
    }

    override fun saveLoginInfo(saveLoginResponseEntity: LoginResponseEntity): Completable {
        return Completable.defer {
            driverDatabase
                    .loginResponseDao()
                    .insertLoginResponse(
                            cachedLoginResponseMapper.mapToCached(saveLoginResponseEntity))
            Completable.complete()
        }
    }

    override fun getLoginInfo(): Observable<LoginResponseEntity> {

        return driverDatabase
                .loginResponseDao()
                .selectLoginResponse()
                .map {
                    cachedLoginResponseMapper.mapFromCached(it)
                }
    }

    override fun areUserLogging(): Single<Boolean> {
        return driverDatabase
                .loginResponseDao()
                .checkLoginExist()
                .map {
                    it > 0
                }
    }

    override fun checkUserLogging(): Single<Boolean> {
        return driverDatabase
                .loginResponseDao()
                .checkLoginExist()
                .map {
                    it > 0
                }
    }
}