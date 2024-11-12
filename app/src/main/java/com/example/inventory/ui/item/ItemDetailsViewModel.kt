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

package com.example.inventory.ui.item

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * ItemDetailsViewModel adalah kelas ViewModel yang bertanggung jawab untuk mengelola
 * data dan logika bisnis untuk layar detail item. ViewModel ini menggunakan
 * SavedStateHandle untuk mengambil argumen yang diteruskan melalui rute navigasi,
 * dalam hal ini, ID item yang akan ditampilkan.
 *
 * @param savedStateHandle Objek yang digunakan untuk mengakses dan menyimpan
 *                         status UI selama perubahan konfigurasi.
 */
class ItemDetailsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Mengambil ID item dari savedStateHandle. Ini digunakan untuk memuat detail
    // item dari database Room.
    private val itemId: Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])

    companion object {
        // Konstanta untuk batas waktu yang digunakan dalam operasi tertentu,
        // seperti pengambilan data dari database. Dalam hal ini, batas waktu diatur
        // ke 5000 ms (5 detik).
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * ItemDetailsUiState adalah data class yang merepresentasikan state UI untuk layar detail item.
 * Ini menyimpan data yang diperlukan untuk menampilkan layar, termasuk status ketersediaan
 * item dan detail item yang diambil dari database.
 *
 * @param outOfStock Boolean yang menunjukkan apakah item dalam keadaan habis
 *                    (true jika habis, false jika tersedia).
 * @param itemDetails Objek ItemDetails yang menyimpan informasi lengkap tentang item.
 */
data class ItemDetailsUiState(
    val outOfStock: Boolean = true, // Secara default, item dianggap habis
    val itemDetails: ItemDetails = ItemDetails() // Inisialisasi dengan objek ItemDetails kosong
)
