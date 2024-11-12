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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.inventory.data.ItemsRepository

/**
 * ItemEditViewModel adalah kelas ViewModel yang bertanggung jawab untuk mengelola
 * data dan logika bisnis untuk layar edit item. ViewModel ini menggunakan
 * SavedStateHandle untuk mengambil argumen yang diteruskan melalui rute navigasi,
 * dalam hal ini, ID item yang akan diedit.
 *
 * @param savedStateHandle Objek yang digunakan untuk mengakses dan menyimpan
 *                         status UI selama perubahan konfigurasi.
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    /**
     * Menyimpan state UI item saat ini. Ini adalah properti yang dapat di-observe
     * oleh UI untuk mendapatkan pembaruan ketika ada perubahan.
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set // Setter privat untuk mencegah perubahan langsung dari luar

    // Mengambil ID item dari savedStateHandle. Ini digunakan untuk memuat detail
    // item dari database Room.
    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    /**
     * Memvalidasi input dari UI state item. Fungsi ini memeriksa apakah semua
     * field input (nama, harga, dan kuantitas) tidak kosong.
     *
     * @param uiState State item yang akan divalidasi. Defaultnya adalah itemDetails
     *                dari itemUiState saat ini.
     * @return Boolean yang menunjukkan apakah input valid (true) atau tidak (false).
     */
    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}
