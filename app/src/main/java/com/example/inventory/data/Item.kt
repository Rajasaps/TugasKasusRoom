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


/**
 * Entity data class represents a single row in the database.
 */
class Item(
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
/**
 * Item adalah kelas data yang merepresentasikan entitas item dalam aplikasi inventaris.
 * Kelas ini memiliki beberapa properti yang mendefinisikan atribut dari sebuah item,
 * yaitu:
 *
 * - id: Merupakan identifikasi unik untuk setiap item. Secara default, id diatur ke 0,
 *   yang biasanya digunakan untuk menandakan bahwa item baru belum disimpan ke dalam
 *   database (misalnya, saat melakukan operasi insert).
 *
 * - name: Merupakan nama dari item. Ini adalah properti yang wajib diisi dan
 *   merepresentasikan nama yang akan ditampilkan kepada pengguna.
 *
 * - price: Merupakan harga dari item, yang dinyatakan dalam tipe data Double.
 *   Ini juga merupakan properti yang wajib diisi, dan biasanya mencerminkan
 *   nilai uang dari item tersebut.
 *
 * - quantity: Merupakan jumlah stok item yang tersedia, dinyatakan dalam tipe data Int.
 *   Properti ini menunjukkan berapa banyak unit dari item tersebut yang ada dalam inventaris.
 *
 * Kelas ini berfungsi sebagai model data yang digunakan untuk menyimpan dan mengelola
 * informasi tentang item dalam aplikasi, dan dapat digunakan dalam berbagai operasi
 * seperti penyimpanan, pengambilan, dan pembaruan data di dalam database atau
 * sumber data lainnya.
 */