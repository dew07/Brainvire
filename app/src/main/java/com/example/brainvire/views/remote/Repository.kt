package com.example.brainvire.views.remote

import android.app.Application
import com.example.brainvire.model.ExchangeDTO
import com.example.brainvire.retrofitservices.networkutils.IDataSourceCallback
import kotlin.collections.ArrayList

class Repository(private var dataSource: DataSource?) :
    DataSource {

    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        @JvmStatic
        fun getInstance(
            mApplication: Application,
            initRemoteRepository: Boolean
        ): Repository? {
            if (INSTANCE == null) {
                synchronized(Repository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Repository(
                            if (initRemoteRepository) RemoteDataSource.getInstance(
                                mApplication
                            ) else null
                        )
                    }
                }
            }
            return INSTANCE
        }
    }

    override fun getList(
        callback: IDataSourceCallback<ArrayList<ExchangeDTO>>
    ) {
        dataSource?.getList(callback)

    }


}