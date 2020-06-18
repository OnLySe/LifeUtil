package com.zzq.lifeutil.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "AccountsInfo")
data class AccountInfo(
    @PrimaryKey val id: String,
    @ColumnInfo val timeMillis: Long,
    @ColumnInfo val password: String,
    @ColumnInfo val account: String,
    @ColumnInfo val type: String,
    @ColumnInfo val description: String
) {
    override fun toString(): String {
        return "AccountInfo(id='$id', timeMillis=$timeMillis, password='$password', account='$account', type='$type', description='$description')"
    }
}