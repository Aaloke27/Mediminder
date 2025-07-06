package com.example.mediminder

import android.app.Activity
import android.net.Uri
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.rememberAsyncImagePainter
import java.nio.file.WatchEvent


@Composable
fun Profile (navHostController: NavHostController,name : String, email: String, age: String, mobileNumber: String) {

    val email by rememberSaveable { mutableStateOf(email) }
    val number by rememberSaveable { mutableStateOf(mobileNumber) }
    val age by rememberSaveable { mutableStateOf(age) }
    val profileOwner by rememberSaveable { mutableStateOf(name) }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val painter = rememberAsyncImagePainter(
        model = imageUri ?: R.drawable.ic_person
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imageUri = uri
    }
    var notificationCheck by rememberSaveable { mutableStateOf(true) }
    var darkMode by rememberSaveable { mutableStateOf(false) }
    HideSystemBarsProfilePage()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F3EF))
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 26.dp)
        ) {
            IconButton(
                onClick = {
                    navHostController.popBackStack() // this matches HomePageWithArg
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(bottom = 10.dp)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Medi")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append("minder")
                    }
                },
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color(0xFF0F84A2)
            )

            Spacer(modifier = Modifier.weight(1f))


            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(end = 12.dp, top = 26.dp)
                    .size(26.dp)
                    .background(Color.White, CircleShape)

            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit",
                    tint = Color(0xFF0F84A2)
                )
            }
        }


        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { launcher.launch("image/*") }

            ) {
                Image(
                    painter = painter,
                    contentDescription = "Profile picture",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
        }
        Text(
            text = "Hi, $profileOwner",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Staying on track with your meds",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
//            Total medicine taken...
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(
                        shape = RoundedCornerShape(16.dp),
                        width = 1.dp,
                        color = Color.White
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "Meds Check",
                    tint = Color(0xFF00BCD4)
                )

                Text(
                    "Meds taken",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "4/5",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(5.dp))

//            Next Dose...
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
//
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(
                        shape = RoundedCornerShape(16.dp),
                        width = 1.dp,
                        color = Color.White
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = "Clock sign",
                    tint = Color(0xFF00BCD4)
                )
                Text(
                    "Next Dose",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    "8:30 AM",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
//            Reminder set...
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
//                    .fillMaxSize()
//                    .wrapContentSize()
                    .weight(1f)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(
                        shape = RoundedCornerShape(16.dp),
                        width = 1.dp,
                        color = Color.White
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification",
                    tint = Color(0xFF00BCD4)
                )
                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    "Reminder Set",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    "5 Active",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "User info",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 8.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .border(
                    shape = RoundedCornerShape(16.dp),
                    width = 1.dp,
                    color = Color.White
                )
                .background(Color.White)
        ) {
            Text(
                text = "Age:  $age",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Text(
                text = "Email: $email",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Text(
                text = "Contact:+91 $number",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )


        }
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Settings",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 8.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .border(
                    shape = RoundedCornerShape(16.dp),
                    width = 1.dp,
                    color = Color.White
                )
                .background(Color.White)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notification Switch",
                    tint = Color(0xFF00BCD4)
                )

                Text(
                    "Notifications",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Switch(
                    checked = notificationCheck,
                    onCheckedChange = { notificationCheck = it },
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 20.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.DarkMode,
                    contentDescription = "Light - Dark Switch",
                    tint = Color(0xFF00BCD4)
                )

                Text(
                    "Dark Mode",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Switch(
                    checked = darkMode,
                    onCheckedChange = { darkMode = it },
                    modifier = Modifier
                        .size(32.dp)
                        .padding(end = 20.dp)
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Language,
                    contentDescription = "Language",
                    tint = Color(0xFF00BCD4)
                )

                Text(
                    "Language",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "English",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )

                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = "Button for Language Settings"
                    )
                }
            }


            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.MusicNote,
                    contentDescription = "Alarm Tone",
                    tint = Color(0xFF00BCD4)
                )

                Text(
                    "Notification Tone",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))


                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(end = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ChevronRight,
                        contentDescription = "Alarm Tone"
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
@Composable
fun HideSystemBarsProfilePage() {
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    DisposableEffect(Unit) {
        val window = activity.window

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            controller?.hide(android.view.WindowInsets.Type.systemBars())
            controller?.systemBarsBehavior =
                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        onDispose {
            // Restore system UI if needed on dispose
        }
    }
}
