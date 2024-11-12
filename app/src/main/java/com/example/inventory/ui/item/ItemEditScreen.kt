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

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme

/**
 * ItemEditDestination adalah objek yang mendefinisikan rute navigasi untuk layar edit item.
 * Ini mengimplementasikan antarmuka NavigationDestination untuk menyediakan informasi
 * tentang rute dan judul layar. Rute ini digunakan oleh sistem navigasi untuk
 * menentukan tampilan yang harus ditampilkan ketika pengguna menavigasi ke layar ini.
 */
object ItemEditDestination : NavigationDestination {
    override val route = "item_edit" // Rute yang digunakan untuk navigasi ke layar edit item.
    override val titleRes = R.string.edit_item_title // Resource string untuk judul layar.
    const val itemIdArg = "itemId" // Nama argumen untuk ID item.
    val routeWithArgs = "$route/{$itemIdArg}" // Rute dengan argumen ID item.
}

/**
 * ItemEditScreen adalah fungsi komposabel yang menampilkan layar untuk mengedit item.
 * Fungsi ini mengatur tampilan dan interaksi untuk layar edit item, termasuk
 * menampilkan form untuk mengedit detail item.
 *
 * @param navigateBack Lambda yang dipanggil ketika pengguna ingin kembali
 *                     ke layar sebelumnya.
 * @param onNavigateUp Lambda yang dipanggil ketika pengguna ingin menavigasi
 *                      ke layar sebelumnya.
 * @param modifier Modifier opsional yang dapat digunakan untuk menyesuaikan
 *                   tampilan dan perilaku dari komponen ini.
 * @param viewModel Instance dari ItemEditViewModel yang menyediakan data dan logika
 *                  untuk layar ini.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            // Menampilkan AppBar di bagian atas layar
            InventoryTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp // Navigasi kembali ke layar sebelumnya
            )
        },
        modifier = modifier
    ) { innerPadding ->
        // Menampilkan konten utama layar
        ItemEntryBody(
            itemUiState = viewModel.itemUiState, // Mengambil state UI dari ViewModel
            onItemValueChange = { },  // Logika untuk menangani perubahan nilai item
            onSaveClick = { }, // Logika untuk menyimpan item setelah diedit
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState()) // Mengaktifkan scroll vertikal
        )
    }
}

/**
 * ItemEditScreenPreview adalah fungsi komposabel untuk menampilkan pratinjau
 * dari layar edit item.
 */
@Preview(showBackground = true)
@Composable
fun ItemEditScreenPreview() {
    InventoryTheme {
        ItemEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}
