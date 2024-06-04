package com.example.authapp.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authapp.R
import com.example.authapp.components.GoogleButton
import com.example.authapp.components.MessageBarComponent
import com.example.authapp.domain.model.MessageBarState

@Composable
fun LoginContent(
    signedInState: Boolean,
    messageBarState: MessageBarState,
    onButtonClicked: () -> Unit,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            MessageBarComponent(messageBarState = messageBarState)
        }

        Column(
            modifier = Modifier
                .weight(9f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CentralContent(
                signedInState = signedInState,
                onButtonClicked = onButtonClicked
            )
        }
    }
}

@Composable
fun CentralContent(
    signedInState: Boolean,
    onButtonClicked: () -> Unit,
) {

    Image(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .size(120.dp),
        painter = painterResource(id = R.drawable.ic_google_logo),
        contentDescription = "Google logo"
    )
    Text(
        text = "Sign in to Continue",
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        textAlign = TextAlign.Center
    )
    Text(
        text = stringResource(R.string.google_description),
        modifier = Modifier
            .alpha(alpha = 0.5f)
            .padding(top = 4.dp, bottom = 40.dp),
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        textAlign = TextAlign.Center
    )
    GoogleButton(
        isLoading = signedInState,
        onClick = onButtonClicked
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CentralContentPreview() {
    LoginContent(
        signedInState = false,
        messageBarState = MessageBarState(),
        onButtonClicked = {}
    )
}
