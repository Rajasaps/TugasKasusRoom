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

package com.example.inventory.ui.navigation

/**
 * Interface ini digunakan untuk mendeskripsikan tujuan navigasi dalam aplikasi.
 *
 * Dengan mendefinisikan interface ini, kita dapat memastikan bahwa setiap tujuan
 * navigasi memiliki atribut yang konsisten, yaitu rute dan judul. Ini membantu
 * dalam pengelolaan navigasi di seluruh aplikasi, terutama saat menggunakan
 * Jetpack Compose untuk membangun antarmuka pengguna.
 */
interface NavigationDestination {
    /**
     * Nama unik yang mendefinisikan jalur untuk sebuah composable.
     *
     * Atribut ini digunakan untuk menentukan rute navigasi yang akan
     * diakses dalam aplikasi. Setiap tujuan navigasi harus memiliki rute
     * yang berbeda agar dapat diidentifikasi dengan jelas.
     * Rute ini biasanya digunakan dalam fungsi navigasi untuk
     * menentukan layar mana yang harus ditampilkan.
     */
    val route: String

    /**
     * ID sumber daya string yang berisi judul yang akan ditampilkan untuk layar.
     *
     * Atribut ini memungkinkan kita untuk mengaitkan judul layar dengan
     * sumber daya string yang ada di dalam aplikasi. Dengan cara ini,
     * kita dapat dengan mudah mengelola dan menerjemahkan judul layar
     * tanpa mengubah logika navigasi. Judul ini biasanya ditampilkan
     * di bagian atas layar atau di dalam toolbar untuk memberikan
     * konteks kepada pengguna mengenai layar yang sedang mereka lihat.
     */
    val titleRes: Int
}
