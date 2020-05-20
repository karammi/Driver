package ir.brn.cache.db

object ConfigConstans {

    const val TABLE_NAME = "config"

    const val SELECT_CONFIG = "SELECT * FROM $TABLE_NAME WHERE name like :name"
}