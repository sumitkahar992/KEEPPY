package com.example.keeppy.presentation.screen.home

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable


@Composable
fun MyTaskAlertDialog(
    title: String,
    textDescription: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) {

    AlertDialog(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onBackground
            )
        },
        text = {
            Text(
                text = textDescription,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(
                    text = "yes".uppercase(),
                    color = MaterialTheme.colors.secondary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                Text(
                    text = "No".uppercase(),
                    color = MaterialTheme.colors.secondary
                )
            }
        },
        onDismissRequest = onDismissClick

    )


}




















