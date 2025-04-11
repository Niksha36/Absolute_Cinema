package com.example.absolute_cinema.presentation.FavoritesScreen.components

import android.provider.MediaStore.Audio.Radio
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.absolute_cinema.util.FavoriteSortTypes

@Composable
fun CustomRadioButton(selected: Boolean, onClick: () -> Unit, text: String) {
    Row(Modifier.fillMaxWidth().clickable { onClick() }, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )
        Text(text)
    }


}