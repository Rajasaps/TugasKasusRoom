/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory

import android.app.Application
import com.example.inventory.data.AppContainer
import com.example.inventory.data.AppDataContainer

/**
 * Kelas utama aplikasi yang memperluas Application.
 * Berfungsi sebagai titik awal untuk inisialisasi aplikasi dan pengelolaan dependensi.
 */
class InventoryApplication : Application() {

    /**
     * Metode yang dipanggil saat aplikasi dimulai.
     * Di sini, kita menginisialisasi container dengan instance dari AppDataContainer,
     * yang bertanggung jawab untuk menyediakan dependensi yang diperlukan oleh aplikasi.
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
