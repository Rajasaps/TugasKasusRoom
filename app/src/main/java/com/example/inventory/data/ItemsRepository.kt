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
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface ItemsRepository
/**
 * ItemsRepository adalah interface yang mendefinisikan kontrak untuk operasi yang berkaitan
 * dengan pengelolaan data item dalam aplikasi.
 * Interface ini bertujuan untuk menyediakan metode untuk melakukan operasi dasar
 * seperti memasukkan (insert), memperbarui (update), menghapus (delete),
 * dan mengambil (retrieve) data dari sumber data yang diberikan.
 *
 * Implementasi dari interface ini akan bertanggung jawab untuk menentukan
 * bagaimana dan dari mana data item diambil, apakah itu dari database lokal
 * (seperti Room), API jarak jauh, atau sumber data lainnya.
 *
 * Dengan menggunakan repository pattern, kita dapat memisahkan logika akses data
 * dari logika presentasi, sehingga membuat kode lebih bersih, terstruktur,
 * dan lebih mudah untuk diuji.
 */