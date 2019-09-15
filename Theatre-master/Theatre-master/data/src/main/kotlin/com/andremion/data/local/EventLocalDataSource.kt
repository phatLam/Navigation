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

import com.andremion.data.local.dao.EventDao
import com.andremion.data.local.model.EventLocalModel
import io.reactivex.Observable

class EventLocalDataSource(private val eventDao: EventDao) {

    fun findByType(type: Int): Observable<List<EventLocalModel>> = eventDao.findByType(type).toObservable()

    fun deleteByType(type: Int) = eventDao.deleteByType(type)

    fun getById(id: Int): Observable<EventLocalModel> = eventDao.getById(id).toObservable()

    fun insertAll(events: List<EventLocalModel>) = eventDao.insertAll(*events.toTypedArray())

    fun insert(event: EventLocalModel) = eventDao.insert(event)
}