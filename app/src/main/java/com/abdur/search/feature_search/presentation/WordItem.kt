package com.abdur.search.feature_search.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdur.search.feature_search.domain.model.Word

@Composable
fun WordItem(
    word: Word,
    modifier : Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = word.word,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        Spacer(modifier = modifier.height(12.dp))
        Text(text = word.phonetic, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = modifier.height(12.dp))

        word.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            meaning.definitions.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. " + definition.definition)
                Spacer(modifier = modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "Example: $example")
                    Spacer(modifier = modifier.height(8.dp))
                }
            }
            Spacer(modifier = modifier.height(8.dp))
        }
    }
}