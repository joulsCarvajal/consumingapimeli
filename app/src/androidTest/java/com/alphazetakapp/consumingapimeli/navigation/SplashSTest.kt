package com.alphazetakapp.consumingapimeli.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SplashSTest {
    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun firstTest(){
        composeTestRule.setContent {
            SplashS()
        }

        composeTestRule.onNodeWithTag("splashImage").assertDoesNotExist()
        composeTestRule.onNodeWithText("busqueda", ignoreCase = false)

    }
}