package com.arbtemey.solo.aztecgold2.dependencyinjection

import com.arbtemey.solo.aztecgold2.AztecViewModel
import com.arbtemey.solo.aztecgold2.data.GameStorageImpl
import com.arbtemey.solo.aztecgold2.domain.AztecMemoryStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideMainStorage(): AztecMemoryStorage{
        return GameStorageImpl()
    }
}