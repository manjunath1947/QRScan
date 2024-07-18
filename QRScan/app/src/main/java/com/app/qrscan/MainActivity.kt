package com.app.qrscan

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission granted
                Log.d("MainActivity", "Camera permission granted")
            } else {
                // Permission denied
                Log.d("MainActivity", "Camera permission denied")
            }
        }

        requestPermissionLauncher.launch(Manifest.permission.CAMERA)

        setContent {
            //ResultScreen(qrText = "abc")
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "qrscreen") {
                composable("qrscreen") {
                    QRScan(onNavigateToQrScanner = { navController.navigate("mainscreen") })
                }
                composable("mainscreen") {
                    MainScreen(navController)
                }
                composable("resultScreen/{qrText}") { backStackEntry ->
                    val qrText = backStackEntry.arguments?.getString("qrText") ?: ""
                    ResultScreen(qrText)
                }
            }
        }
        /*setContent {
            MaterialTheme {
                MainScreen()
            }
        }*/
    }

    @Composable
    fun QRScan(onNavigateToQrScanner: () -> Unit, modifier: Modifier = Modifier) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = { onNavigateToQrScanner() }) {
                Text(
                    text = "QR scan",
                    modifier = modifier
                )
            }

        }

    }

}

@Composable
fun QRScannner() {
    /*Button(onClick = { *//*TODO*//* }) {
        Text(text = "Qr Scanner")
    }*/
/*    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted
        } else {
            // Permission denied
        }
    }*/

    //requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    val navController = rememberNavController()
    MainScreen(navController = navController)
}

/*
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRScanner(*/
/*goTOAddKeysScreen: (DeviceInfo) -> Unit*//*
) {
*/
/*    val navController = LocalNavController.current
    val cameraPermission: PermissionState = cameraPermissionState()
    val deviceViewModel: DeviceViewModel = hiltViewModel()*//*

    Scaffold {

            Box(
                modifier = Modifier
                    .padding(it)
                    .background(color = Color.Gray),
                contentAlignment = Alignment.BottomCenter
            ) {
                if (!cameraPermission.status.isGranted)
                    QRPermissionModal(cameraPermission = cameraPermission) {
                        navController.navigateUp()
                    }
                else
                    QRCODEUi()
            }
            Modifier.padding(it)

    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun QRPermissionModal(
    modifier: Modifier = Modifier,
    cameraPermission: PermissionState,
    close: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .wrapContentSize()
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.scan_your_camera_qr),
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = AppTypography.fontStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                lineHeight = 20.sp,
                color = Color.Red,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.turn_on_your_mobile_camera_message),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = AppTypography.fontStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
                color = Color.Gray,
            )

            Spacer(modifier = Modifier.height(16.dp))

            AppRedButton(
                onClick = {
                    if (!cameraPermission.status.isGranted) {
                        cameraPermission.launchPermissionRequest()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                isOutlined = true,
                text = stringResource(R.string.turn_camera_on)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                color = Color.Gray,
                text = stringResource(R.string.do_it_later),
                fontSize = 14.sp,
                fontFamily = AppTypography.getDefaultFontFamily(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        close()
                    },
                textDecoration = TextDecoration.Underline
            )
        }

    }
}


@Composable
fun QRCODEUi(deviceViewModel: DeviceViewModel, goTOAddKeysScreen: (DeviceInfo) -> Unit) {
    val navController = LocalNavController.current
    val scanQrCodeLauncher =
        rememberLauncherForActivityResult(io.github.g00fy2.quickie.ScanQRCode()) { result ->
            // handle QRResult
            when (result) {
                is QRResult.QRSuccess -> {
                    val text = result.content.rawValue
                    // decoding with default UTF-8 charset when rawValue is null will not result in meaningful output, demo purpose
                        ?: result.content.rawBytes?.let { String(it) }.orEmpty()
                    Log.d("GetQRCodeExample", "text:$text")
                    redirectOnQrFound(deviceViewModel, text, goTOAddKeysScreen)
                }

                QRResult.QRUserCanceled -> {
                    navController.navigateUp()
                    val text = "User canceled"
                    Log.d("GetQRCodeExample", "text:$text")
                }

                QRResult.QRMissingPermission -> {
                    val text = "Missing permission"
                    Log.d("GetQRCodeExample", "text:$text")
                }

                is QRResult.QRError -> {
                    val text =
                        "${result.exception.javaClass.simpleName}: ${result.exception.localizedMessage}"
                    Log.d("GetQRCodeExample", "text:$text")
                }
            }

        }
    LaunchedEffect(Unit) {
        delay(500)
        scanQrCodeLauncher.launch(null)
    }

}


fun redirectOnQrFound(
    //deviceViewModel: DeviceViewModel,
    qrCode: String?,
   // goTOAddKeysScreen: (DeviceInfo) -> Unit
) {
    var isScan = false
    val qrData = qrCode?.split(",")
    Log.d(
        "onQRCodeFound",
        "qrCode: $qrCode, isScan: ${deviceViewModel.isScan}, qrData: $qrData"
    )
    if (!deviceViewModel.isScan
        && qrCode != null
        && qrData != null
        && qrData.size == 2
    ) {
        Log.d("onQRCodeFound", "qrCode: $qrData")
        deviceViewModel.isScan = true
        //Navigate to Next Screen
  */
/*      goTOAddKeysScreen(
            DeviceInfo(
                serialNumber = qrData.first(),
                key = qrData.last(),
                fromQR = true
            )
        )*//*

    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun cameraPermissionState(): PermissionState {
    return rememberPermissionState(permission = Manifest.permission.CAMERA)
}


*/
