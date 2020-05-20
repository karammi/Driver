package ir.brn.cache.db

object UserConstants {

    const val TABLE_NAME = "user"
    const val COLUMN_USER_ID = "user_id"
    const val COLUMN_USER_STATUS = "user_status"
    const val COLUMN_USER_PROFILE = "user_profile"
    const val COLUMN_USER_IS_UPDATE = "user_is_update"
    const val COLUMN_USER_IS_DRIVER = "user_is_driver"
    const val COLUMN_USER_TYPE = "user_type"

    const val SELECT_USER = "SELECT * FROM $TABLE_NAME"

    const val DELETE_USER = "DELETE FROM $TABLE_NAME"

    const val USER_EXISTS = "SELECT EXISTS(SELECT 1 FROM $TABLE_NAME)"


}