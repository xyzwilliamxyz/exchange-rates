package com.exchangerates

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

open class BaseInstrumentationTest {

    fun getJson(file: String): String {
        return BaseInstrumentationTest::class.java.getResource("/$file.json").readText()
    }

    fun enqueueMockResponse(mockWebServer: MockWebServer, request: String) {
        mockWebServer.enqueue(MockResponse().setBody(getJson(request)).setResponseCode(200))
    }

    fun enqueueMockError(mockWebServer: MockWebServer) {
        mockWebServer.enqueue(MockResponse().setResponseCode(500).setBody("Internal Server Error"))
    }
}
