package com.matatoa.wheremystuff.designsystem

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.matatoa.wheremystuff.designsystem.theme.WhereMyStuffTheme
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowLeft
import compose.icons.tablericons.ListSearch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    titleIcon: ImageVector? = null,
    showBackButton: Boolean = false,
    showSearchFilter: Boolean = false,
    onBackClick: () -> Unit = {},
    onShowSearchFilterClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            AnimatedVisibility(visible = showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = TablerIcons.ArrowLeft,
                        contentDescription = Strings.Common.BACK,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp)
            ) {
                if (titleIcon != null)
                    Icon(
                        imageVector = titleIcon,
                        contentDescription = Strings.Common.PLACE_ICON,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(size = 32.dp)
                    )

                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        actions = {
            AnimatedVisibility(visible = showSearchFilter) {
                IconButton(onClick = onShowSearchFilterClick) {
                    Icon(
                        imageVector = TablerIcons.ListSearch,
                        contentDescription = Strings.Common.SEARCH,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
        },
        windowInsets = WindowInsets(top = 0.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = modifier,
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    WhereMyStuffTheme {
        TopBar(
            title = Strings.Common.TOP_BAR_TITLE,
            showBackButton = true,
            showSearchFilter = true,
        )
    }
}
