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

package com.andremion.data.local.dao

import android.arch.persistence.room.*
import com.andremion.data.local.model.RatingAndReviewLocalModel
import com.andremion.data.local.model.RatingLocalModel
import com.andremion.data.local.model.ReviewLocalModel
import io.reactivex.Maybe

@Dao
interface RatingDao {

    @Transaction // https://developer.android.com/reference/android/arch/persistence/room/Transaction.html
    @Query("SELECT * FROM Rating WHERE event = :event")
    fun findByEvent(event: Int): Maybe<RatingAndReviewLocalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rating: RatingLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg reviews: ReviewLocalModel)

    @Transaction
    fun deleteByEvent(event: Int) {
        deleteReviewByEvent(event) // Delete reviews first to respect the constraints
        deleteRatingByEvent(event)
    }

    @Query("DELETE FROM Rating WHERE event = :event")
    fun deleteRatingByEvent(event: Int)

    @Query("DELETE FROM Review WHERE event = :event")
    fun deleteReviewByEvent(event: Int)
}