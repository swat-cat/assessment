package com.hitechtalents.assessment.presentation.ui.profile_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hitechtalents.assessment.R
import com.hitechtalents.assessment.presentation.ui.sign_up.AvatarView


@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {

    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val uriHandler = LocalUriHandler.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Text(
                    text = profile.data?.firstName?.let {
                        "${stringResource(id = R.string.profile_title)}, $it"
                    } ?: stringResource(id = R.string.profile_title),
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.profile_subtitle),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                AvatarView(
                    avatarPath = profile.data?.avatarPath,
                    openPickerAction = null,
                    showMode = true,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(screenWidth / 3)
                )
                profile.data?.webSite?.let { url ->
                    ClickableText(
                        text = AnnotatedString(
                            text = url,
                        ), style = MaterialTheme.typography.bodyMedium.copy(
                            textDecoration = TextDecoration.Underline,
                            color = MaterialTheme.colorScheme.primary
                        ), onClick = {
                            //uriHandler.openUri(url)
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 24.dp)
                    )
                }
                profile.data?.firstName?.let { name ->
                    Text(
                        name,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp)
                    )
                }
                profile.data?.email?.let { email ->
                    Text(
                        email,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 12.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = stringResource(id = R.string.submit)
                    )
                }
            }
        }
    }

}