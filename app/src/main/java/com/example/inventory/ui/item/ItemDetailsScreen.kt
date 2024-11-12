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

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme

/**
 * ItemDetailsDestination adalah objek yang mendefinisikan rute navigasi untuk layar detail item.
 * Ini mengimplementasikan antarmuka NavigationDestination untuk menyediakan informasi
 * tentang rute dan judul layar. Rute ini digunakan oleh sistem navigasi untuk
 * menentukan tampilan yang harus ditampilkan ketika pengguna menavigasi ke layar ini.
 */
object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details" // Rute yang digunakan untuk navigasi ke layar detail item.
    override val titleRes = R.string.item_detail_title // Resource string untuk judul layar.
    const val itemIdArg = "itemId" // Nama argumen untuk ID item.
    val routeWithArgs = "$route/{$itemIdArg}" // Rute dengan argumen ID item.
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /**
         * ItemDetailsScreen adalah fungsi komposabel yang menampilkan layar detail item.
         * Fungsi ini mengatur tampilan dan interaksi untuk layar detail item, termasuk
         * menampilkan informasi item dan tombol untuk mengedit atau menghapus item.
         *
         * @param navigateToEditItem Lambda yang dipanggil ketika pengguna ingin
         *                              menavigasi ke layar untuk mengedit item.
         * @param navigateBack Lambda yang dipanggil ketika pengguna ingin kembali
         *                     ke layar sebelumnya.
         * @param modifier Modifier opsional yang dapat digunakan untuk menyesuaikan
         *                   tampilan dan perilaku dari komponen ini.
         */
fun ItemDetailsScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            // Menampilkan AppBar di bagian atas layar
            InventoryTopAppBar(
                title = stringResource(ItemDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, floatingActionButton = {
            // Tombol untuk mengedit item
            FloatingActionButton(
                onClick = { navigateToEditItem(0) }, // ID item yang akan diedit (0 sebagai placeholder)
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.edit_item_title),
                )
            }
        }, modifier = modifier
    ) { innerPadding ->
        // Menampilkan konten utama layar
        ItemDetailsBody(
            itemDetailsUiState = ItemDetailsUiState(), // UI state untuk detail item
            onSellItem = { }, // Logika untuk menjual item (belum diimplementasikan)
            onDelete = { }, // Logika untuk menghapus item (belum diimplementasikan)
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
 * ItemDetailsBody adalah fungsi komposabel yang menampilkan konten utama dari layar detail item.
 * Fungsi ini bertanggung jawab untuk menampilkan informasi item, tombol untuk menjual,
 * dan tombol untuk menghapus item.
 *
 * @param itemDetailsUiState UI state untuk detail item.
 * @param onSellItem Lambda yang dipanggil ketika pengguna ingin menjual item.
 * @param onDelete Lambda yang dipanggil ketika pengguna ingin menghapus item.
 * @param modifier Modifier opsional untuk menyesuaikan tampilan.
 */
@Composable
private fun ItemDetailsBody(
    itemDetailsUiState: ItemDetailsUiState,
    onSellItem: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) } // State untuk konfirmasi penghapusan

        ItemDetails(
            item = itemDetailsUiState.itemDetails.toItem(), // Mengonversi itemDetails menjadi Item
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            // Tombol untuk menjual item
            onClick = onSellItem,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            enabled = true
        ) {
            Text(stringResource(R.string.sell))
        }
        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.delete)) // Teks tombol untuk menghapus item
        }
        if (deleteConfirmationRequired) { // Dialog konfirmasi penghapusan jika diperlukan
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false // Menyembunyikan dialog setelah konfirmasi
                    onDelete() // Memanggil fungsi penghapusan
                },
                onDeleteCancel = { deleteConfirmationRequired = false }, // Menyembunyikan dialog jika dibatalkan
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}

/**
 * ItemDetails adalah fungsi komposabel yang menampilkan detail dari item.
 * Ini menampilkan informasi seperti nama item, jumlah dalam stok, dan harga.
 *
 * @param item Item yang akan ditampilkan.
 * @param modifier Modifier opsional untuk menyesuaikan tampilan.
 */
@Composable
fun ItemDetails(
    item: Item, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_medium)
            )
        ) {
            // Menampilkan baris detail item
            ItemDetailsRow(
                labelResID = R.string.item,
                itemDetail = item.name,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.quantity_in_stock,
                itemDetail = item.quantity.toString(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.price,
                itemDetail = item.formatedPrice(),
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
        }
    }
}

/**
 * ItemDetailsRow adalah fungsi komposabel yang menampilkan satu baris detail item.
 * Ini menampilkan label dan nilai detail item.
 *
 * @param labelResID Resource ID untuk label.
 * @param itemDetail Nilai detail item.
 * @param modifier Modifier opsional untuk menyesuaikan tampilan.
 */
@Composable
private fun ItemDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(stringResource(labelResID)) // Menampilkan label
        Spacer(modifier = Modifier.weight(1f)) // Spacer untuk memisahkan label dan detail
        Text(text = itemDetail, fontWeight = FontWeight.Bold) // Menampilkan detail item dengan tebal
    }
}

/**
 * DeleteConfirmationDialog adalah fungsi komposabel yang menampilkan dialog konfirmasi
 * untuk menghapus item.
 *
 * @param onDeleteConfirm Lambda yang dipanggil ketika pengguna mengonfirmasi penghapusan.
 * @param onDeleteCancel Lambda yang dipanggil ketika pengguna membatalkan penghapusan.
 * @param modifier Modifier opsional untuk menyesuaikan tampilan.
 */
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.attention)) }, // Judul dialog
        text = { Text(stringResource(R.string.delete_question)) }, // Teks pertanyaan penghapusan
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(stringResource(R.string.no)) // Tombol untuk membatalkan penghapusan
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(stringResource(R.string.yes)) // Tombol untuk mengonfirmasi penghapusan
            }
        })
}

/**
 * ItemDetailsScreenPreview adalah fungsi komposabel untuk menampilkan pratinjau
 * dari layar detail item.
 */
@Preview(showBackground = true)
@Composable
fun ItemDetailsScreenPreview() {
    InventoryTheme {
        ItemDetailsBody(
            ItemDetailsUiState(
                outOfStock = true,
                itemDetails = ItemDetails(1, "Pen", "$100", "10")
            ),
            onSellItem = {},
            onDelete = {}
        )
    }
}
