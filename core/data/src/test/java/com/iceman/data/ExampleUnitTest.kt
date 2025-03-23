package com.iceman.data

import com.iceman.data.di.dataModule
import com.iceman.data.repository.MathRepository
import com.iceman.network.di.networkModule
import com.iceman.network.response.CalculateMathResponse
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class NetworkUnitTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(networkModule,dataModule)
    }

    private val myRepository: MathRepository by inject()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `getData returns success`() = runTest {
        val expectedData = CalculateMathResponse(234, 2.0, 2.0, "+", 4.0, "")
        val dummyList = listOf(expectedData)

        // Mock the Retrofit service's response
        `when`(myRepository.getMathList()).thenReturn(flowOf(dummyList))

        myRepository.getMathList().collect { resultList ->
            val first = resultList.first()
            assertEquals(expectedData, first)
        }
    }

    //dummy data function
    fun dummyList(): List<CalculateMathResponse> {
        return listOf(CalculateMathResponse(234, 2.0, 2.0, "+", 4.0, ""))
    }
}

