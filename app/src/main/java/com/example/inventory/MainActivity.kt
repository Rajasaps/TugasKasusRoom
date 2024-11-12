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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.inventory.ui.theme.InventoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            InventoryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InventoryApp()
                }
            }
        }
    }
}
/**
 * MainActivity adalah kelas yang mewarisi dari ComponentActivity dan berfungsi
 * sebagai titik masuk utama untuk aplikasi Android ini.
 * Kelas ini mengatur konten UI aplikasi menggunakan Jetpack Compose.
 *
 * Pada saat aktivitas dibuat, metode onCreate dipanggil. Di dalam metode ini,
 * beberapa langkah penting dilakukan:
 *
 * - enableEdgeToEdge(): Memungkinkan konten aplikasi untuk mengisi area layar
 *   secara penuh, termasuk area di sekitar tepi layar, sehingga memberikan
 *   pengalaman visual yang lebih imersif.
 *
 * - super.onCreate(savedInstanceState): Memanggil metode onCreate dari kelas
 *   induk untuk memastikan bahwa semua inisialisasi dasar dilakukan.
 *
 * - setContent: Mengatur konten UI dari aktivitas ini dengan menggunakan
 *   Jetpack Compose. Di dalam blok ini, kita menggunakan InventoryTheme
 *   untuk menerapkan tema khusus pada aplikasi.
 *
 * - Surface: Komponen Surface digunakan sebagai wadah untuk menampung
 *   konten aplikasi. Di sini, kita mengatur modifier untuk mengisi
 *   ukuran maksimum layar (fillMaxSize) dan menetapkan warna latar belakang
 *   menggunakan warna yang ditentukan dalam tema material (MaterialTheme.colorScheme.background).
 *
 * - InventoryApp(): Memanggil fungsi InventoryApp yang akan menjadi
 *   komponen utama dari aplikasi ini, menampilkan UI yang sesuai
 *   dengan logika dan navigasi aplikasi.
 */