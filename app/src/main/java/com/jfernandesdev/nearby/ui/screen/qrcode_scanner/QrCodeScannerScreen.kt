package com.jfernandesdev.nearby.ui.screen.qrcode_scanner

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.jfernandesdev.nearby.MainActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QrCodeScannerScreen(modifier: Modifier = Modifier, onCompleteScan: (String) -> Unit) {
    val context = LocalContext.current

    val scanOptions = ScanOptions()
        .setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        .setPrompt("Leia o QRCode do cupom")
        .setCameraId(0)
        .setBeepEnabled(false)
        .setOrientationLocked(true)
        .setBarcodeImageEnabled(true)

    val barcodeLauncher = rememberLauncherForActivityResult(
        ScanContract()
    ) { result ->
        onCompleteScan(result.contents.orEmpty())
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            ActivityResultContracts.RequestPermission()
        } else {
            barcodeLauncher.launch(scanOptions)
        }
    }

    fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            barcodeLauncher.launch(scanOptions)
        } else if (shouldShowRequestPermissionRationale(
                context as MainActivity,
                android.Manifest.permission.CAMERA
            )
        ) {
            Toast.makeText(
                context,
                "Necessário permitir o acesso à câmera para continuar",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(true) {
        checkCameraPermission()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Posicione a câmera no QRCode para realizar a leitura.",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
