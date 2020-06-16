package com.zzq.lifeutil.viewmodel

import com.zzq.base.base.BaseViewModel
import com.zzq.lifeutil.repo.LifeRepo

class LifeViewModel : BaseViewModel<LifeRepo>() {
    override fun initialRepo(): LifeRepo {

        //TODO 反射创建
        return LifeRepo()
    }
}