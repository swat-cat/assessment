package com.hitechtalents.assessment.presentation.ui.sign_up

import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.hitechtalents.assessment.R
import com.hitechtalents.assessment.core_ui.composeform.fields.PasswordField
import com.hitechtalents.assessment.core_ui.composeform.fields.TextField
import com.hitechtalents.assessment.domain.model.Result
import com.hitechtalents.assessment.presentation.navigation.Screen
import com.hitechtalents.assessment.presentation.theme.AssessmentTheme
import com.huhx.picker.constant.AssetPickerConfig
import com.huhx.picker.constant.RequestType
import com.huhx.picker.data.PickerPermissions
import com.huhx.picker.view.AssetPicker

@Composable
fun SignUpScreen(navController: NavController?, viewModel: SignUpViewModel = hiltViewModel()) {
    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val showPicker by viewModel.showPicker.collectAsStateWithLifecycle()
    val avatarPath by viewModel.avatarPath.collectAsStateWithLifecycle()
    profile.data?.let {
        if (profile is Result.Success) {
            LaunchedEffect(key1 = true) {
                navController?.navigate(Screen.ProfileDetails.route)
            }
        }
    }

    SignUpPage(
        avatarPath = avatarPath,
        form = viewModel.form,
        openPickerAction = viewModel::openPicker,
    ) {
        viewModel.submit()
    }
    if (showPicker) {
        PickerPermissions(
            permissions = listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        ) {
            AssetPicker(
                assetPickerConfig = AssetPickerConfig(
                    gridCount = 3,
                    maxAssets = 1,
                    requestType = RequestType.IMAGE
                ),
                onPicked = {
                    viewModel.onPickAvatar(it.firstOrNull())
                },
                onClose = {
                    viewModel.onClosePicker(it.firstOrNull())
                }
            )
        }
    }
}

@Composable
private fun SignUpPage(
    avatarPath: String?,
    form: SignUpForm,
    openPickerAction: (() -> Unit)?,
    submitAction: () -> Unit
) {
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
                    .imePadding()
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_title),
                    style = MaterialTheme.typography.displayMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.sign_up_subtitle),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(24.dp))

                AvatarView(
                    avatarPath = avatarPath,
                    openPickerAction = openPickerAction,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(screenWidth / 3)
                )
                TextField(
                    form = form,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.first_name),
                    fieldState = form.firstName,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ).Field()

                TextField(
                    form = form,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.email),
                    fieldState = form.email,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ).Field()

                PasswordField(
                    form = form,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.password),
                    fieldState = form.password,
                    imeAction = ImeAction.Next,
                ).Field()

                TextField(
                    form = form,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    label = stringResource(id = R.string.website),
                    fieldState = form.webSite,
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Done,
                ).Field()

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = submitAction,
                    enabled = form.isValid,
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

@Composable
fun AvatarView(
    avatarPath: String?,
    openPickerAction: (() -> Unit)?,
    modifier: Modifier,
    showMode: Boolean = false
) {
    Box(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                openPickerAction?.invoke()
            }
            .aspectRatio(0.75f)
            .background(color = if (avatarPath != null) Color.Transparent else MaterialTheme.colorScheme.primaryContainer)
    ) {
        if (avatarPath != null)
            Image(
                painter = rememberAsyncImagePainter(avatarPath),
                contentDescription = "Avatar",
                contentScale = ContentScale.Fit
            )
        else
            Text(
                text = if (showMode) stringResource(id = R.string.avatar_not_added) else stringResource(
                    id = R.string.tap_avatar
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
                    .padding(horizontal = 16.dp)
            )
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    AssessmentTheme {
        SignUpPage(null, SignUpForm(null), {}) {}
    }
}