package ir.brn.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.brn.cache.db.UserConstants

@Entity(tableName = UserConstants.TABLE_NAME)
data class CachedUser(@PrimaryKey
                      @ColumnInfo(name = UserConstants.COLUMN_USER_ID)
                      val id: Int?,
                      val username: String?,
                      @ColumnInfo(name = UserConstants.COLUMN_USER_TYPE)
                      val type: String?,
                      val appCurrentVersion: String?,
                      val gpsStatus: String?,
                      val firstName: String?,
                      val lastName: String?,
                      val nationalCode: String?,
                      val phone: String?,
                      val email: String?,
                      val mobile: String?,
                      val shaba: String?,
                      val address: String?,
                      val birthDate: String?,
                      val gcmToken: String?,
                      val profilePhoto: String?,
                      val profileCover: String?,
                      val truckTypeId: Int?,
                      val truckTypeName: String?,
                      val carrierTypeId: Int?,
                      val carrierTypeName: String?,
                      val smartCardNumber: String?,
                      val smartCardExpireAt: String?,
                      val navigationCard: String?,
                      val navigationCardExpireAt: String?,
                      val licensePlates: String?,
                      val vehicleModel: String?,
                      val vehicleColor: String?,
                      val vehicleDiagnosticAt: String?,
                      val vehicleOwnerFirstName: String?,
                      val vehicleOwnerLasName: String?,
                      val vehicleOwnerTel: String?,
                      val vehicleOwnerAddress: String?,
                      val cityId: Int?,
                      val cityName: String?,
                      val provinceId: Int?,
                      val provinceName: String?,
                      @ColumnInfo(name = UserConstants.COLUMN_USER_STATUS)
                      val status: String?,
                      val withLoadMeter: String?,
                      val withoutLoadMeter: String?,
                      val gpsIterateSecond: String?,
                      val gpsFastIterateSecond: String?,
                      val gpsSendIterateSecond: String?,
                      val gpsSyncIterateSecond: String?,
                      val currentLoadId: String?,
                      @ColumnInfo(name = UserConstants.COLUMN_USER_IS_DRIVER)
                      val isDriver: String?,
                      @ColumnInfo(name = UserConstants.COLUMN_USER_PROFILE)
                      val profileComplete: String?,
                      @ColumnInfo(name = UserConstants.COLUMN_USER_IS_UPDATE)
                      val isUpdated: String?)