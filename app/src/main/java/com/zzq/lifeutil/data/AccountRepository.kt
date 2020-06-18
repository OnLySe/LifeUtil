package com.zzq.lifeutil.data

class AccountRepository private constructor(private val accountInfoDao: AccountInfoDao) {

    fun getAllCounts() = accountInfoDao.getAllAccounts()

    fun getAccountByType(type: String) = accountInfoDao.getAccountByType(type)

    fun getAllTypeAccount(account: String, type: String) =
        accountInfoDao.getAccount(account, type)

    fun addAccountInfo(accountInfo: AccountInfo) {
        accountInfoDao.addAccountInfo(accountInfo)
    }

    companion object {

        @Volatile
        private var instance: AccountRepository? = null

        fun getInstance(accountInfoDao: AccountInfoDao): AccountRepository {
            return instance ?: synchronized(this) {
                instance ?: AccountRepository(accountInfoDao).also { instance = it }
            }
        }
    }
}