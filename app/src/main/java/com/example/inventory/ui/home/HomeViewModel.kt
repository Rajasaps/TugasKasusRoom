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

package com.example.inventory.ui.home

import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item

/**
 * HomeViewModel adalah kelas ViewModel yang bertanggung jawab untuk mengelola
 * data dan logika bisnis untuk layar Home. ViewModel ini digunakan untuk
 * mengambil semua item dari database Room dan menyediakan data tersebut
 * untuk tampilan. Dengan menggunakan ViewModel, data dapat bertahan
 * selama perubahan konfigurasi, seperti rotasi layar.
 */
class HomeViewModel : ViewModel() {
    companion object {
        // Konstanta untuk batas waktu yang digunakan dalam operasi tertentu, seperti
        // pengambilan data dari database. Dalam hal ini, batas waktu diatur ke 5000 ms (5 detik).
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * HomeUiState adalah data class yang merepresentasikan state UI untuk layar Home.
 * Ini menyimpan data yang diperlukan untuk menampilkan layar, termasuk daftar item
 * yang diambil dari database. Dengan menggunakan data class, kita dapat dengan mudah
 * membuat objek yang tidak dapat diubah (immutable) yang menyimpan state UI.
 *
 * @param itemList Daftar item yang akan ditampilkan di layar. Secara default, ini diinisialisasi
 *                  dengan daftar kosong, yang berarti tidak ada item yang tersedia untuk ditampilkan.
 */
data class HomeUiState(val itemList: List<Item> = listOf())
