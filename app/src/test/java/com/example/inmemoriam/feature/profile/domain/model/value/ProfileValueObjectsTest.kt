package com.example.inmemoriam.feature.profile.domain.model.value

import com.example.inmemoriam.features.profile.domain.model.*
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileValueObjectsTest {

    @Test
    fun `Name valido no lanza excepcion`() {
        val name = Name("Homero J. Simpson")
        assertEquals("Homero J. Simpson", name.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Name vacio lanza IllegalArgumentException`() {
        Name("")
    }

    @Test
    fun `Email valido no lanza excepcion`() {
        val email = Email("homero.simpson@springfield.com")
        assertEquals("homero.simpson@springfield.com", email.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Email invalido lanza IllegalArgumentException`() {
        Email("correo_invalido")
    }

    @Test
    fun `Cellphone valido no lanza excepcion`() {
        val phone = Cellphone("+1 939 555-7422")
        assertEquals("+1 939 555-7422", phone.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Cellphone vacio lanza IllegalArgumentException`() {
        Cellphone("")
    }

    @Test
    fun `Summary puede contener cualquier texto`() {
        val summary = Summary("Ciudadano de Springfield")
        assertEquals("Ciudadano de Springfield", summary.value)
    }

    @Test
    fun `Summary vacio es valido`() {
        val summary = Summary("")
        assertEquals("", summary.value)
    }

    @Test
    fun `PathUrl puede contener cualquier url`() {
        val path = PathUrl("https://example.com/image.jpg")
        assertEquals("https://example.com/image.jpg", path.value)
    }

    @Test
    fun `PathUrl vacio es valido`() {
        val path = PathUrl("")
        assertEquals("", path.value)
    }
}
