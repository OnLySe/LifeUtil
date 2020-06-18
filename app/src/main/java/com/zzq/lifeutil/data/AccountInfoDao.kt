package com.zzq.lifeutil.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AccountInfoDao {

    /**
     * 查询所有的记录。每次有变动都会发出通知
     */
    @Query("select * from AccountsInfo")
    fun getAllAccounts(): LiveData<List<AccountInfo>>

    @Insert
    fun addAccountInfo(accountInfo: AccountInfo)

    @Delete
    fun deleteAccount(accountInfo: AccountInfo)

    @Query("update AccountsInfo set password = :password where id = :id")
    fun modifyAccountInfo(id: String, password: String)

    /**
     * 查看指定type的账号信息，即查询这个账户的密码修改历史，不排除一个类型下有多个账号
     */
    @Query("select * from AccountsInfo where type = :type")
    fun getAccountByType(type: String): LiveData<List<AccountInfo>>

    /**
     * 查询指定账号
     */
    @Query("select * from AccountsInfo where account=:account and type = :type")
    fun getAccount(account: String, type: String): List<AccountInfo>
}