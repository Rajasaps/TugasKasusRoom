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

package com.example.inventory.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.inventory.InventoryApplication
import com.example.inventory.ui.home.HomeViewModel
import com.example.inventory.ui.item.ItemDetailsViewModel
import com.example.inventory.ui.item.ItemEditViewModel
import com.example.inventory.ui.item.ItemEntryViewModel

/**
 * Objek [AppViewModelProvider] menyediakan factory untuk membuat instance
 * dari berbagai ViewModel yang digunakan dalam aplikasi.
 * Menggunakan [viewModelFactory] untuk mendefinisikan initializer
 * untuk setiap ViewModel.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Inisialisasi untuk ItemEditViewModel
        initializer {
            ItemEditViewModel(
                this.createSavedStateHandle()
            )
        }
        // Inisialisasi untuk ItemEntryViewModel
        initializer {
            ItemEntryViewModel()
        }

        // Inisialisasi untuk ItemDetailsViewModel
        initializer {
            ItemDetailsViewModel(
                this.createSavedStateHandle()
            )
        }

        // Inisialisasi untuk HomeViewModel
        initializer {
            HomeViewModel()
        }
    }
}

/**
 * Fungsi ekstensi [CreationExtras] untuk mendapatkan instance
 * dari [InventoryApplication] dengan menggunakan kunci
 * [AndroidViewModelFactory.APPLICATION_KEY].
 */
fun CreationExtras.inventoryApplication(): InventoryApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
