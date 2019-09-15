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

package com.andremion.theatre.event.type

import android.app.Application
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.andremion.domain.entity.EventType
import com.andremion.domain.interactor.EventTypeGetAllUseCase
import com.andremion.theatre.R
import com.andremion.theatre.internal.util.BaseAndroidViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

class EventTypeViewModel(context: Context, private val eventTypeGetAllUseCase: EventTypeGetAllUseCase)
    : BaseAndroidViewModel(context.applicationContext as Application) {

    val loading = ObservableBoolean()
    val result = ObservableArrayList<EventType>()
    val empty = ObservableBoolean()
    val error = ObservableField<String>()

    fun loadEventTypeList() = addDisposable(getAllEventTypes())

    private fun getAllEventTypes(): Disposable {
        return eventTypeGetAllUseCase.execute()
                .subscribeWith(object : DisposableObserver<List<EventType>>() {

                    override fun onStart() {
                        loading.set(true)
                    }

                    override fun onNext(t: List<EventType>) {
                        loading.set(false)
                        result.clear()
                        result.addAll(t)
                        empty.set(t.isEmpty())
                    }

                    override fun onError(t: Throwable) {
                        loading.set(false)
                        error.set(t.localizedMessage ?: t.message ?: context.getString(R.string.unknown_error))
                    }

                    override fun onComplete() {
                        // no-op
                    }
                })
    }
}
