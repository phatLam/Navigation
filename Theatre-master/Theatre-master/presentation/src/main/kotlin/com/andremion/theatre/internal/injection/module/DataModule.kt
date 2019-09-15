/*
 * Copyright (c) 2018. André Mion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andremion.theatre.internal.injection.module

import android.content.Context
import com.andremion.data.BuildConfig
import com.andremion.data.gateway.InventoryGatewayImpl
import com.andremion.data.gateway.SystemGatewayImpl
import com.andremion.data.local.EventLocalDataSource
import com.andremion.data.local.EventTypeLocalDataSource
import com.andremion.data.local.RatingLocalDataSource
import com.andremion.data.local.VenueLocalDataSource
import com.andremion.data.local.dao.EventDao
import com.andremion.data.local.dao.EventTypeDao
import com.andremion.data.local.dao.RatingDao
import com.andremion.data.local.dao.VenueDao
import com.andremion.data.local.inventory.InventoryDatabase
import com.andremion.data.local.system.SystemDatabase
import com.andremion.data.remote.EventRemoteDataSource
import com.andremion.data.remote.EventTypeRemoteDataSource
import com.andremion.data.remote.RatingRemoteDataSource
import com.andremion.data.remote.VenueRemoteDataSource
import com.andremion.data.remote.api.TheatreApi
import com.andremion.data.remote.api.TheatreService
import com.andremion.data.repository.EventRepository
import com.andremion.data.repository.EventTypeRepository
import com.andremion.data.repository.RatingRepository
import com.andremion.data.repository.VenueRepository
import com.andremion.data.repository.mapper.EventMapper
import com.andremion.data.repository.mapper.EventTypeMapper
import com.andremion.data.repository.mapper.RatingMapper
import com.andremion.data.repository.mapper.VenueMapper
import com.andremion.domain.gateway.InventoryGateway
import com.andremion.domain.gateway.SystemGateway
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DataModule {

    @Provides
    @Singleton
    internal fun provideTheatreService(): TheatreService = TheatreApi(BuildConfig.API_URL)

    @Provides
    @Singleton
    internal fun provideEventTypeRemoteDataSource(theatreService: TheatreService): EventTypeRemoteDataSource {
        return EventTypeRemoteDataSource(theatreService)
    }

    @Provides
    @Singleton
    internal fun provideVenueRemoteDataSource(theatreService: TheatreService): VenueRemoteDataSource {
        return VenueRemoteDataSource(theatreService)
    }

    @Provides
    @Singleton
    internal fun provideEventRemoteDataSource(theatreService: TheatreService): EventRemoteDataSource {
        return EventRemoteDataSource(theatreService)
    }

    @Provides
    @Singleton
    internal fun provideRatingRemoteDataSource(theatreService: TheatreService): RatingRemoteDataSource {
        return RatingRemoteDataSource(theatreService)
    }

    @Provides
    @Singleton
    internal fun provideSystemDatabase(context: Context): SystemDatabase {
        return SystemDatabase.newInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideInventoryDatabase(context: Context): InventoryDatabase {
        return InventoryDatabase.newInstance(context)
    }

    @Provides
    @Singleton
    internal fun provideEventTypeDao(systemDatabase: SystemDatabase): EventTypeDao = systemDatabase.eventTypeDao()

    @Provides
    @Singleton
    internal fun provideVenueDao(systemDatabase: SystemDatabase): VenueDao = systemDatabase.venueDao()

    @Provides
    @Singleton
    internal fun provideEventDao(inventoryDatabase: InventoryDatabase): EventDao = inventoryDatabase.eventDao()

    @Provides
    @Singleton
    internal fun provideRatingTypeDao(inventoryDatabase: InventoryDatabase): RatingDao = inventoryDatabase.ratingDao()

    @Provides
    @Singleton
    internal fun provideEventTypeDiskDataSource(eventTypeDao: EventTypeDao): EventTypeLocalDataSource {
        return EventTypeLocalDataSource(eventTypeDao)
    }

    @Provides
    @Singleton
    internal fun provideVenueDiskDataSource(venueDao: VenueDao): VenueLocalDataSource {
        return VenueLocalDataSource(venueDao)
    }

    @Provides
    @Singleton
    internal fun provideEventDiskDataSource(eventDao: EventDao): EventLocalDataSource {
        return EventLocalDataSource(eventDao)
    }

    @Provides
    @Singleton
    internal fun provideRatingDiskDataSource(ratingDao: RatingDao): RatingLocalDataSource {
        return RatingLocalDataSource(ratingDao)
    }

    @Provides
    @Singleton
    internal fun provideEventTypeRepository(eventTypeLocalDataSource: EventTypeLocalDataSource,
                                            eventTypeRemoteDataSource: EventTypeRemoteDataSource): EventTypeRepository {
        return EventTypeRepository(eventTypeLocalDataSource, eventTypeRemoteDataSource, EventTypeMapper())
    }

    @Provides
    @Singleton
    internal fun provideVenueRepository(venueLocalDataSource: VenueLocalDataSource,
                                        venueRemoteDataSource: VenueRemoteDataSource): VenueRepository {
        return VenueRepository(venueLocalDataSource, venueRemoteDataSource, VenueMapper())
    }

    @Provides
    @Singleton
    internal fun provideEventRepository(eventLocalDataSource: EventLocalDataSource,
                                        eventRemoteDataSource: EventRemoteDataSource): EventRepository {
        return EventRepository(eventLocalDataSource, eventRemoteDataSource, EventMapper())
    }

    @Provides
    @Singleton
    internal fun provideRatingRepository(ratingLocalDataSource: RatingLocalDataSource,
                                         ratingRemoteDataSource: RatingRemoteDataSource): RatingRepository {
        return RatingRepository(ratingLocalDataSource, ratingRemoteDataSource, RatingMapper())
    }

    @Provides
    @Singleton
    internal fun provideSystemRepository(eventTypeRepository: EventTypeRepository): SystemGateway {
        return SystemGatewayImpl(eventTypeRepository)
    }

    @Provides
    @Singleton
    internal fun provideInventoryRepository(eventTypeRepository: EventTypeRepository,
                                            eventRepository: EventRepository,
                                            venueRepository: VenueRepository,
                                            ratingRepository: RatingRepository): InventoryGateway {
        return InventoryGatewayImpl(eventTypeRepository, eventRepository, venueRepository, ratingRepository)
    }
}
