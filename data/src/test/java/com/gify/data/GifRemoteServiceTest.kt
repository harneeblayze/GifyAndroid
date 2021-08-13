package com.gify.data

import com.gify.data.factory.DataFactory
import com.gify.data.remote.service.GifRemoteService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


@RunWith(JUnit4::class)
class GifRemoteServiceTest {

    private val mockWebServer = MockWebServer()
    private lateinit var gifRemoteService: GifRemoteService
    private lateinit var query: String
    private lateinit var apiKey: String
    private lateinit var lang: String

    @Before
    fun setUp() {
        mockWebServer.start()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        setUpGifRetrofitService()
        setUpQueryValues()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `read sample success json file`() {
        val fileContent = FileReader.readFileFromResources("gif_response.json")
        assertNotNull(fileContent)
    }

    @Test
    fun `Assert remote service returns data`(): Unit =
        runBlocking {
            val gifs =
                gifRemoteService.searchGifs(
                    apiKey, query, page = 1, lang
                )

            assertNotNull(gifs)
        }

    @Test
    fun `Assert remote service response match JSON Server response`() = runBlocking {
        val gifs = gifRemoteService.searchGifs(
            apiKey, query, page = 1, lang
        )

        assertEquals(
            GifData.provideRemoteGifsFromAssets().size,
            gifs.data.size
        )

        assertEquals(
            GifData.provideRemoteGifsFromAssets()[0],
            gifs.data[0]
        )

        assertEquals(
            GifData.provideRemoteGifsPaginationFromAssets(),
            gifs.pagination
        )
    }

    private fun setUpQueryValues() {
        query = DataFactory.query()
        lang = DataFactory.lang()
        apiKey = DataFactory.key()
    }

    private fun setUpGifRetrofitService() {
        gifRemoteService = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(GifRemoteService::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/gifs/search?api_key=${apiKey}&q=${query}&offset=1&lang=${lang}" -> {
                    MockResponse()
                        .setResponseCode(HttpURLConnection.HTTP_OK)
                        .setBody(FileReader.readFileFromResources("gif_response.json"))
                }

                else -> MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)

            }
        }
    }

}