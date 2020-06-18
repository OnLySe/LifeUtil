package com.zzq.lifeutil.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.zzq.lifeutil.data.AccountRepository
import com.zzq.lifeutil.data.AppDatabase
import com.zzq.lifeutil.viewmodels.LifeViewModel
import com.zzq.lifeutil.viewmodels.LifeViewModelFactory

object InjectorUtil {

    fun provideLifeViewModelFactory(fragment: Fragment): LifeViewModelFactory {
        val repository = getAccountInfoRepository(fragment.requireContext())
        return LifeViewModelFactory(repository, fragment)
    }

    private fun getAccountInfoRepository(context: Context): AccountRepository {
        return AccountRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).getAccountInfoDao()
        )
    }
}