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

package com.andremion.data.local

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.andremion.data.local.model.EventTypeLocalModel
import com.andremion.data.local.system.SystemDatabase
import com.andremion.data.local.test.TestDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventTypeLocalDataSourceTest {

    private lateinit var database: SystemDatabase
    private lateinit var eventTypeLocalDataSource: EventTypeLocalDataSource

    @Before
    fun setUp() {
        database = TestDatabase.newSystemInstance(InstrumentationRegistry.getContext())
        eventTypeLocalDataSource = EventTypeLocalDataSource(database.eventTypeDao())
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun whenInsertEventTypesOnLocalDataSource_ThoseRowsShouldBeRetrieved() {

        // Given

        val localItems = listOf(EventTypeLocalModel(1, "name"))

        // When

        eventTypeLocalDataSource.insertAll(localItems)

        val testObserver = eventTypeLocalDataSource.getAll()
                .test()

        // Then

        testObserver
                .assertComplete()
                .assertNoErrors()
                .assertValue(localItems)
    }

}