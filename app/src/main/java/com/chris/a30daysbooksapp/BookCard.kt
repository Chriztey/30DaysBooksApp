package com.chris.a30daysbooksapp

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer


import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chris.a30daysbooksapp.model.BookList
import com.chris.a30daysbooksapp.model.Books
import com.chris.a30daysbooksapp.ui.theme._30DaysBooksAppTheme

@Composable
fun BookCard(
    books: Books,
) {
    var expandStatus by remember{ mutableStateOf(false) }

    val colors by animateColorAsState(
        targetValue = if(expandStatus) MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.primaryContainer
    )


    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .clickable { expandStatus = !expandStatus }
        ) {
        Column(modifier= Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .background(colors)
            .verticalScroll(rememberScrollState())
        ) {
            Row (modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .sizeIn(maxHeight = 160.dp)) {

                Image(
                    painter = painterResource(id = books.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(height = 160.dp, width = 120.dp)
                        .clip(shape = RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                    )

                Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier
                    .fillMaxSize()
                    .padding(start = dimensionResource(id = R.dimen.padding_small))) {
                    Row(Modifier.fillMaxWidth()) {
                        repeat(books.rating) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_star_24),
                                contentDescription = ""
                            )
                        }
                    }
                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Title: " + stringResource(id = books.name),
                            style = MaterialTheme.typography.titleLarge,

                        )
                        Spacer(modifier = Modifier.weight(1f))
                        CardExpandButton(expanded = expandStatus, onClick = { expandStatus = !expandStatus })

                    }
                }
            }


            if (expandStatus) {
                Details(synopsis = books.synopsis)
            }
        }


    }
    
}

@Composable
fun Details(
    @StringRes synopsis: Int
) {
    Text(text = stringResource(id = synopsis),
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
    )
    
}

@Composable
fun CardExpandButton(
    expanded: Boolean,
    onClick: () -> Unit) {
    IconButton(onClick =  onClick ) {
        Icon(painter = painterResource(
            id = if (expanded) R.drawable.baseline_expand_less_24 else R.drawable.baseline_expand_more_24),
            contentDescription = "")
    }
}

@Composable
fun ListBooks(bookList: List<Books>) {
    LazyColumn {
        items(bookList) {
                book -> BookCard(book)
        }
    }
    




}

@Preview(showBackground = true)
@Composable
fun BookCardPreview() {
    _30DaysBooksAppTheme(darkTheme = false) {
        ListBooks(BookList.BookListing)
    }
}