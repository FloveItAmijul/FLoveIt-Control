package com.floveit.floveitcontrol

import kotlinx.coroutines.flow.StateFlow

/**
 * Common HID API:
 *  - `isConnected` drives your UI
 *  - `discover()` kicks off NSD/Bonjour + socket connect
 *  - `send()` writes HID data
 *  - `disconnect()` tears down
 */
interface HidClient {
    val findingMirror: StateFlow<Boolean>
    val isConnected: StateFlow<Boolean>
    val serverMessage: StateFlow<String>
    val deviceName: StateFlow<String>
    val deviceID: StateFlow<String>
    suspend fun discover(deviceName: String)
    suspend fun send(data: String): Boolean
    fun disconnect()
    fun lookupKeyForServiceName(serviceName: String): String?
    fun disconnectMirror(key: String)

}

interface WindowController {
    fun enableImmersiveMode()
}


/**
 * Factory: platform modules must supply one.
 */
expect fun provideHidClient(): HidClient
