package com.example.keeppy.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopBar(
    modifier: Modifier = Modifier,
    onSearchClicked: () -> Unit,
    biometricAuthState: Boolean,
    onLockedNotesClicked: () -> Unit,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onLockedNotesClicked) {
            if (biometricAuthState) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = null
                )
            } else {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Outlined.LockOpen,
                    contentDescription = null
                )
            }
        }

        Text(
            text = "Notes",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface,
            fontWeight = FontWeight.Medium
        )
        SimpleIconButton(imageVector = Icons.Rounded.Search) {
            onSearchClicked()
        }
    }
}


@Composable
fun SimpleSearchBar(
    modifier: Modifier = Modifier,
    onPerformQuery: (String) -> Unit,
    onCancelClicked: () -> Unit,
) {
    val (text, setText) = remember { mutableStateOf("") }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                setText(it)
                onPerformQuery(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
            decorationBox = {
                if (text.isEmpty()) {
                    Text(
                        text = "Search ..",
                        color = Color.Gray,
                        style = MaterialTheme.typography.h4
                    )
                }
                it()
            }
        )

        SimpleIconButton(imageVector = Icons.Rounded.Close) {
            onCancelClicked()
        }


    }

}




















































