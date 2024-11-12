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

package com.example.inventory.data

import android.content.Context
/**
 * AppContainer adalah interface yang mendefinisikan kontrak untuk
 * pengelolaan dependensi dalam aplikasi. Ini menyediakan akses ke
 * repositori item ([ItemsRepository]) yang dapat digunakan di seluruh
 * aplikasi untuk melakukan operasi terkait item.
 */

interface AppContainer {
    val itemsRepository: ItemsRepository
}
/**
 * AppDataContainer adalah implementasi dari [AppContainer] yang menyediakan
 * instance dari [OfflineItemsRepository]. Kelas ini bertanggung jawab untuk
 * mengelola dan menyediakan akses ke repositori item, serta menyimpan
 * konteks aplikasi yang diperlukan untuk menginisialisasi repositori.
 */

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository()
    }
}
/**
 * Implementasi untuk [ItemsRepository]. Menggunakan lazy initialization
 * untuk menunda pembuatan instance [OfflineItemsRepository] sampai
 * pertama kali diperlukan. Ini membantu menghemat sumber daya dan
 * memastikan bahwa repositori hanya dibuat saat dibutuhkan.
 */