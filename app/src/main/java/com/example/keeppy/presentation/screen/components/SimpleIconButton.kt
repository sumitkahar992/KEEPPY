package com.example.keeppy.presentation.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(percent = 30)
    Surface(
        shape = shape,
        modifier = modifier
            .clip(shape = shape)
            .clickable { onClick() },
        color = backgroundColor,
        contentColor = MaterialTheme.colors.surface
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.padding(12.dp)
        )
    }
}

@Composable
fun SimpleAlertDialog(
    openDialog: Boolean,
    title: String,
    text: String,
    positiveButtonText: String,
    negativeButtonText: String,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (openDialog) {
        AlertDialog(
            backgroundColor = MaterialTheme.colors.background,
            shape = RoundedCornerShape(12.dp),
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colors.onSurface
                )
            },
            text = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SimpleTextButton(
                        text = negativeButtonText,
                        onClick = { onDismiss() }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    SimpleTextButton(
                        text = positiveButtonText,
                        onClick = {
                            onDismiss()
                            onClick()
                        }
                    )

                }
            }
        )
    }


}


@Composable
fun SimpleTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(percent = 30)
    Surface(
        shape = shape,
        modifier = modifier
            .clip(shape = shape)
            .clickable { onClick() },
        color = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSurface
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
                color = MaterialTheme.colors.onSurface,
                fontSize = 16.sp
            )
        }
    }


}







































