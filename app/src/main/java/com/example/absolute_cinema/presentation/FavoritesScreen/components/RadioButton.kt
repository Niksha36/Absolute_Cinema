package com.example.absolute_cinema.presentation.FavoritesScreen.components

import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.absolute_cinema.util.FavoriteSortTypes

@Composable
fun CustomRadioButton(selected: Boolean, onClick: () -> Unit, text: String) {
    Row(Modifier.fillMaxWidth().clickable { onClick() }) {
        RadioButton(
            selected = selected,
            onClick = null
        )
        Text(text)
    }


}