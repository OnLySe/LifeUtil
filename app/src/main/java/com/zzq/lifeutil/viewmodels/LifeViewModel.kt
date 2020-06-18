package com.zzq.lifeutil.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zzq.lifeutil.data.AccountInfo
import com.zzq.lifeutil.data.AccountRepository

class LifeViewModel internal constructor(
    accountRepo: AccountRepository,
    savedStateHandle:SavedStateHandle
) : ViewModel() {

    /*** 获取所有账号和历史*/
    val accounts: LiveData<List<AccountInfo>> = accountRepo.getAllCounts()


}