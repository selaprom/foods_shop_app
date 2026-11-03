package kh.com.sela.developer.basic.sela_food_app.presentation.login_screen

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kh.com.sela.developer.basic.sela_food_app.R

import kh.com.sela.developer.basic.sela_food_app.data.base.BaseUiState
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OldGreen
import kh.com.sela.developer.basic.sela_food_app.ui.theme.OrangeColor

@Preview(showBackground = true)
@Composable
fun LoginScreen(
    onForward: (userIdi: Int) -> Unit = {},
    onClicktoSignup: () -> Unit = {},
    loginVM: LoginVM = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordHidden by remember { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var isLoading by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf("") }

    val loginUiState by loginVM.loginUiState.collectAsState()

    fun isEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validateEmail(input: String) {
        emailError = when {
            input.isEmpty() -> ""
            !isEmail(input) -> "Invalid email format (e.g. user@example.com)"
            else -> ""
        }
    }

    fun validatePassword(input: String) {
        passwordError = when {
            input.isEmpty() -> ""
            input.length < 4 -> "At least 4 characters"
            else -> ""
        }
    }

    val isFormValid = isEmail(email) && password.length >= 4

    LaunchedEffect(loginUiState) {

        when (val state = loginUiState) {
            is BaseUiState.Loading -> {
                isLoading = true
            }

            is BaseUiState.Success -> {
                isLoading = false
                onForward(state.data.user.id)

            }
            is BaseUiState.Error -> {
                isError = true
                isLoading = false

            }

            else -> {
                isLoading = false

            }
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.image_logo),
                contentDescription = "logo app",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.4f),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,

                )
            Surface(
                modifier = Modifier
                    .imePadding()
                    .fillMaxHeight(0.68f)
                    .align(Alignment.BottomCenter),
                color = OldGreen.copy(1f),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 20.dp),
                ) {
                    Text(
                        "Welcome Back",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { input ->
                            // Restrict: no spaces allowed, max 100 chars
                            if (!input.contains(" ") && input.length <= 100) {
                                email = input
                                validateEmail(input)
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        isError = emailError.isNotEmpty(),
                        supportingText = {
                            if (emailError.isNotEmpty()) {
                                Text(
                                    text = emailError,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        },

                        singleLine = true,
                        placeholder = { Text("Email") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Gray,
                            errorContainerColor = Color.White,
                            errorSupportingTextColor = Color.White, // or your error color; see note below
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            validatePassword(it)

                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        singleLine = true,
                        placeholder = { Text("Password") },
                        visualTransformation = if (isPasswordHidden) PasswordVisualTransformation()
                        else VisualTransformation.None,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                        ),

                        trailingIcon = {
                            IconButton(onClick = { isPasswordHidden = !isPasswordHidden }) {
                                Icon(
                                    painter = painterResource(
                                        if (isPasswordHidden) R.drawable.ic_eye_close else R.drawable.ic_eye
                                    ),
                                    contentDescription = if (isPasswordHidden) "Show password" else "Hide password",
                                    tint = Color.Gray,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        },
                        isError = passwordError.isNotEmpty(),
                        supportingText = {
                            if (passwordError.isNotEmpty()) {
                                Text(
                                    text = passwordError,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                keyboardController?.hide()
                                focusManager.clearFocus()
                            }
                        )

                    )

                    TextButton(onClick = { }, modifier = Modifier.align(Alignment.End)) {
                        Text(
                            "Forgot Password?",
                            color = Color.White.copy(0.8f),
                            modifier = Modifier.align(Alignment.CenterVertically),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    FilledTonalButton(
                        enabled = isFormValid,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OrangeColor,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.White

                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        onClick = {
                            loginVM.login(email, password)
                        },
                    ) {
                        Text("Login", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Don't have an account?",
                            color = Color.White.copy(0.8f),
                            modifier = Modifier.align(Alignment.CenterVertically),
                            fontSize = 16.sp
                        )

                        TextButton(
                            onClick = onClicktoSignup,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Text(
                                "Sign up",
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color.White.copy(0.7f)
                        )

                        Text(
                            text = "Login with",
                            modifier = Modifier.padding(horizontal = 12.dp),
                            color = Color.White.copy(0.7f),
                        )

                        HorizontalDivider(
                            modifier = Modifier.weight(1f),
                            color = Color.White.copy(0.7f)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(
                            modifier = Modifier.size(50.dp),
                            onClick = {
                                // TODO: Google login
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .background(
                                        color = Color.White,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.image_google
                                    ),
                                    contentDescription = "Google",
                                    modifier = Modifier.size(25.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        IconButton(
                            modifier = Modifier.size(50.dp),
                            onClick = {
                                // TODO: Google login
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .background(
                                        color = Color.White,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.image_facebook
                                    ),
                                    contentDescription = "Google",
                                    modifier = Modifier.size(25.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        IconButton(
                            modifier = Modifier.size(50.dp),
                            onClick = {
                                // TODO: Google login
                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(35.dp)
                                    .background(
                                        color = Color.White,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(
                                        id = R.drawable.image_twitter
                                    ),
                                    contentDescription = "Google",
                                    modifier = Modifier.size(25.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }


                    }
                    Spacer(modifier = Modifier.height(32.dp))


                }


            }
        }


    }
    if (isLoading) {
        Dialog(
            onDismissRequest = {}
        ) {
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(tween(300)) +
                        scaleIn(
                            initialScale = 0.85f,
                            animationSpec = tween(300)
                        )
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Loading",
                            style = MaterialTheme.typography.titleLarge
                        )

                        CircularProgressIndicator(
                            color = OldGreen,
                            strokeWidth = 4.dp
                        )

                        Text(
                            text = "Please wait...",
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }

    LoginErrorDialog(
        isError = isError,
        message = "Invalid email or password",
        onDismiss = {
            isError = false
        }
    )
}

@Composable
fun LoginErrorDialog(
    isError: Boolean,
    message: String,
    onDismiss: () -> Unit
) {
    if (isError) {
        Dialog(
            onDismissRequest = onDismiss
        ) {
            AnimatedVisibility(
                visible = isError,
                enter = fadeIn(
                    animationSpec = tween(300)
                ) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = tween(300)
                ),
                exit = fadeOut(
                    animationSpec = tween(200)
                ) + scaleOut(
                    targetScale = 0.8f,
                    animationSpec = tween(200)
                )
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(48.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        Text(
                            text = "Login Failed",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = message,
                            textAlign = TextAlign.Center
                        )

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )

                        Button(
                            onClick = onDismiss,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = OldGreen
                            )
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}

