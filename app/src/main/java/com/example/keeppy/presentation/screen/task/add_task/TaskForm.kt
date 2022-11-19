package com.example.keeppy.presentation.screen.task.add_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keeppy.presentation.common.theme.KEEPPYTheme
import com.example.keeppy.presentation.screen.task.edit_task.EditTaskFormState
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    taskFormState: TaskFormState,
    keyboardController: SoftwareKeyboardController?
) {

    val (focusRequester) = FocusRequester.createRefs()

    Column(modifier = modifier) {
        TaskTextField(
            value = taskFormState.task.title,
            onValueChange = { taskFormState.onTitleChange(it) },
            textStyle = MaterialTheme.typography.h6,
            placeholderText = "Task Title",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester.requestFocus() }
            ),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            PriorityMenuSelector(
                priority = taskFormState.task.priority,
                onPriorityChange = { taskFormState.onPriorityChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 20.dp, top = 10.dp, end = 10.dp)
                    .focusRequester(focusRequester)
            )

            if (taskFormState is EditTaskFormState){
                StatusMenuSelector(
                    status = taskFormState.task.status,
                    onStatusChange = { taskFormState.onStatusChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(start = 20.dp, top = 10.dp, end = 10.dp)
                )
            }

        }

        TimePicker(
            value = taskFormState.task.time,
            onValueChange = { hour, minute -> taskFormState.onTimeChange(hour, minute) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        DatePicker(
            value = taskFormState.task.date,
            onValueChange = { date -> taskFormState.onDateChange(date) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp, end = 10.dp)
        )

        TaskTextField(
            value = taskFormState.task.description,
            onValueChange = { taskFormState.onDescriptionChange(it) },
            textStyle = MaterialTheme.typography.h4,
            placeholderText = "Description",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            singleLine = false,
            maxLines = 20,
            modifier = Modifier.fillMaxWidth()
        )
    }


}


@Composable
fun TaskTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    textStyle: TextStyle,
    placeholderText: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    singleLine: Boolean,
    maxLines: Int
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            placeholderColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = MaterialTheme.colors.primary
        )
    )

/*    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        readOnly = readOnly,
        textStyle = textStyle,
        placeholder = {
            Text(
                text = placeholderText,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
            )
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.primary,
            placeholderColor = MaterialTheme.colors.primaryVariant,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = MaterialTheme.colors.primary
        ),

    )

 */
}

@Composable
@Preview(name = "Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview() {
    KEEPPYTheme {
        TaskTextField(
            value = "Estudar Kotlin",
            onValueChange = {},
            textStyle = MaterialTheme.typography.h6,
            placeholderText = "Título",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {}
            ),
            singleLine = true,
            maxLines = 1
        )
    }
}

@Composable
fun TimePicker(
    value: String,
    onValueChange: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .height(TextFieldDefaults.MinHeight)
            .clickable {
                showTimerPickerDialog(context) { hour, minute ->
                    onValueChange(hour, minute)
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBox()

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Tim e",
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.caption
            )

            Text(
                text = value,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 5.dp)
            )

        }


    }

}


@Composable
private fun IconBox() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(start = 10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color = MaterialTheme.colors.onPrimary.copy(alpha = 0.3f))
    ) {
        Icon(
            imageVector = Icons.Filled.Alarm,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(45.dp)
                .align(Alignment.Center)
        )
    }

}

@Composable
private fun IconBox2() {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(start = 10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color = MaterialTheme.colors.onPrimary.copy(alpha = 0.3f))
    ) {
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .size(45.dp)
                .align(Alignment.Center)
        )
    }

}


private fun showTimerPickerDialog(
    context: Context,
    onTimeValues: (Int, Int) -> Unit
) {
    val calendar = Calendar.getInstance()
    val calendarHours = calendar.get(Calendar.HOUR_OF_DAY)
    val calendarMinutes = calendar.get(Calendar.MINUTE)
    val isSystem24Hour = android.text.format.DateFormat.is24HourFormat(context)

    val timePickerDialog = TimePickerDialog(
        context, { _, hour: Int, minute: Int ->
            onTimeValues(hour, minute)
        }, calendarHours, calendarMinutes, isSystem24Hour
    )

    timePickerDialog.show()

}


@Composable
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview53() {
    KEEPPYTheme {
        Surface(color = MaterialTheme.colors.surface) {
            TimePicker(
                value = "13:00",
                onValueChange = { _, _ ->

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun Prev2424() {
    Row {
        IconBox()
        IconBox2()
    }
}

private fun showDatePickerDialog(
    context: Context,
    onDateValue: (String) -> Unit
) {
    val calendar = Calendar.getInstance()

    val cYear = calendar.get(Calendar.YEAR)
    val cMonth = calendar.get(Calendar.MONTH)
    val cDay = calendar.get(Calendar.DAY_OF_MONTH)

    val datePicker = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, day: Int ->
            onDateValue("$day/$month/$year")
        }, cYear, cMonth, cDay
    )
    datePicker.show()

}

@Composable
fun DatePicker(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .height(TextFieldDefaults.MinHeight)
            .clickable {
                showDatePickerDialog(context) {
                    onValueChange(it)
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconBox2()

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                text = "Dat e",
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                style = MaterialTheme.typography.caption
            )

            Text(
                text = value,
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 5.dp)
            )

        }

    }

}


@Composable
@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun Preview645() {
    KEEPPYTheme {
        Surface(color = MaterialTheme.colors.surface) {
            DatePicker(
                value = "17/08/2000",
                onValueChange = {

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
















































