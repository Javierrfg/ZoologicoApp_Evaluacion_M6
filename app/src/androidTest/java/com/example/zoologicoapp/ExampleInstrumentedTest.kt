package com.example.zoologicoapp

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimalListInstrumentedTest {

    // Le decimos al test que inicie abriendo la MainActivity
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun verificar_que_la_lista_es_visible() {
        // Le pedimos a Espresso que busque el RecyclerView en la pantalla
        // y verifique que se esté mostrando (isDisplayed)
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
}