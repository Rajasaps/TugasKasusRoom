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
package com.example.inventory.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Objek [Shapes] mendefinisikan bentuk yang digunakan dalam aplikasi
 * untuk konsistensi desain antarmuka pengguna.
 */
val Shapes = Shapes(

    /**
     * Bentuk [extraSmall] dengan sudut potong 8.dp di bagian atas kanan
     * dan bawah kiri untuk elemen kecil.
     */
    extraSmall = CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp),

    /**
     * Bentuk [small] juga menggunakan sudut potong 8.dp, sama dengan
     * [extraSmall], menjaga konsistensi untuk elemen kecil.
     */
    small = CutCornerShape(topEnd = 8.dp, bottomStart = 8.dp),

    /**
     * Bentuk [medium] dengan sudut potong 16.dp, memberikan tampilan
     * yang lebih menonjol untuk elemen sedang.
     */
    medium = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
)
