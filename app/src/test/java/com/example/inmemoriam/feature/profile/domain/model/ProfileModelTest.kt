package com.example.inmemoriam.feature.profile.domain.model

import com.example.inmemoriam.features.profile.domain.model.*
import org.junit.Assert.*
import org.junit.Test

class ProfileModelTest {

    @Test
    fun `crear ProfileModel exitosamente`() {
        val profile = ProfileModel(
            pathUrl = PathUrl("http://example.com"),
            name = Name("Homero"),
            email = Email("homero@springfield.com"),
            cellphone = Cellphone("123456"),
            summary = Summary("Inspector")
        )
        assertEquals("Homero", profile.name.value)
        assertEquals("homero@springfield.com", profile.email.value)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `email invalido lanza excepcion`() {
        Email("correo_invalido")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `nombre vacio lanza excepcion`() {
        Name("")
    }
}
