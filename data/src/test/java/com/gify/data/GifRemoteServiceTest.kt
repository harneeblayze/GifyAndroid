package com.gify.data

import com.gify.data.factory.DataFactory
import com.gify.data.factory.GifDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertNotNull


@RunWith(JUnit4::class)
class GifRemoteServiceTest {


    @Test
     fun testRemoteServiceReturnsData() {

        val apiKey =  DataFactory.randomString()
        val query = DataFactory.randomString()
        val page = DataFactory.randomInt()
        val lang = DataFactory.randomString()

        val remotemodel = GifDataFactory.makeGifyRemoteModel()

        assertNotNull(apiKey)
        assertNotNull(query)
        assertNotNull(page)
        assertNotNull(lang)
        assertNotNull(remotemodel)
    }

}