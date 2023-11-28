package com.example.comics.renan.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.comics.renan.data.mock.MockRepository
import com.example.comics.renan.domain.model.Comic
import com.example.comics.renan.presentation.theme.ComicsTheme

@Composable
fun ComicCardItem(modifier: Modifier = Modifier, comic: Comic, onCardClicked: (Comic)-> Unit) {

    Column(modifier = modifier.height(130.dp).fillMaxWidth().clickable { onCardClicked(comic) },
        verticalArrangement = Arrangement.Center) {
        Row{
            ImageHolder(
                imageUrl = "${comic.thumbnail.path}.${comic.thumbnail.extension}",
                modifier = Modifier
                    .widthIn(min = 100.dp, max = 200.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Black)
                    .aspectRatio(4 / 3f),
                contentDescription = "Image ${comic.title}")

            Column(modifier = Modifier.padding(start = 8.dp)) {
                comic.title?.let {
                    Text(
                        text = it,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ) }
                comic.description?.let {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = it,
                        maxLines = 2,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }
        }

    }
    Divider()

}

@Preview(showBackground = true,)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewComicCardItem(){
    ComicsTheme {
        ComicCardItem(comic = MockRepository().getComic(), onCardClicked = {})
    }
}
