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
import androidx.lifecycle.ViewModel
import com.example.inventory.data.Item
import java.text.NumberFormat

/**
 * ViewModel to validate and insert items in the Room database.
 */
class ItemEntryViewModel : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(itemDetails: ItemDetails) {
        itemUiState =
            ItemUiState(itemDetails = itemDetails, isEntryValid = validateInput(itemDetails))
    }

    private fun validateInput(uiState: ItemDetails = itemUiState.itemDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && quantity.isNotBlank()
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * Fungsi ekstensi ini mengonversi objek [ItemDetails] menjadi objek [Item].
 *
 * Proses konversi ini penting untuk menyimpan data item ke dalam database Room.
 * Jika nilai dari [ItemDetails.price] tidak dapat dikonversi menjadi tipe [Double],
 * maka fungsi ini akan mengatur harga menjadi 0.0. Demikian pula, jika nilai
 * dari [ItemDetails.quantity] tidak dapat dikonversi menjadi tipe [Int],
 * maka kuantitas akan diatur menjadi 0.
 *
 * Konversi ini memastikan bahwa data yang disimpan dalam database selalu valid
 * dan dapat digunakan tanpa menyebabkan kesalahan pada saat pengambilan data.
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0
)

/**
 * Fungsi ekstensi ini mengubah objek [Item] menjadi format harga yang sesuai
 * dengan format mata uang lokal.
 *
 * Penggunaan fungsi ini berguna saat menampilkan harga item di antarmuka pengguna,
 * karena harga yang terformat dengan baik dapat meningkatkan pengalaman pengguna
 * dan membuat informasi lebih mudah dipahami.
 *
 * Fungsi ini menggunakan [NumberFormat.getCurrencyInstance()] untuk mendapatkan
 * format mata uang yang sesuai dengan locale saat ini, sehingga hasilnya
 * akan berbeda tergantung pada pengaturan regional perangkat.
 */
fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Fungsi ekstensi ini mengonversi objek [Item] menjadi objek [ItemUiState].
 *
 * Konversi ini penting untuk mengelola state UI dalam aplikasi. Dengan
 * menggunakan fungsi ini, kita dapat dengan mudah mengubah objek [Item]
 * yang diambil dari database menjadi state yang dapat digunakan oleh UI.
 *
 * Parameter [isEntryValid] memungkinkan kita untuk menandai apakah
 * detail item yang dikonversi valid untuk dimasukkan ke dalam database
 * atau tidak. Ini membantu dalam pengelolaan validasi input sebelum
 * menyimpan data.
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Fungsi ekstensi ini mengonversi objek [Item] menjadi objek [ItemDetails].
 *
 * Konversi ini diperlukan untuk menyajikan detail item dalam bentuk yang
 * dapat digunakan untuk input pengguna. Dengan memisahkan detail item
 * dari objek [Item], kita dapat dengan mudah mengelola dan memperbarui
 * informasi item yang ditampilkan di antarmuka pengguna.
 *
 * Fungsi ini mengonversi tipe data harga dan kuantitas kembali ke
 * bentuk string untuk memungkinkan pengguna mengedit nilai-nilai tersebut
 * sebelum disimpan kembali ke dalam database.
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
