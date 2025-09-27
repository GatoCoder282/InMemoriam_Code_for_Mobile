package com.example.inmemoriam.feature.profile.usecase

import com.example.inmemoriam.features.profile.domain.model.*
import com.example.inmemoriam.features.profile.domain.repository.IProfileRepository
import com.example.inmemoriam.features.profile.domain.usecase.GetProfileUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class GetProfileUseCaseTest {

    private val mockRepository = mockk<IProfileRepository>()
    private val useCase = GetProfileUseCase(mockRepository)

    @Test
    fun `useCase retorna success cuando repository tiene datos`() = runBlocking {
        val fakeProfile = ProfileModel(
            pathUrl = PathUrl("http://example.com"),
            name = Name("Homero"),
            email = Email("homero@springfield.com"),
            cellphone = Cellphone("123456"),
            summary = Summary("Inspector")
        )
        coEvery { mockRepository.fetchData() } returns Result.success(fakeProfile)

        val result = useCase.invoke()

        assertTrue(result.isSuccess)
    }
}
