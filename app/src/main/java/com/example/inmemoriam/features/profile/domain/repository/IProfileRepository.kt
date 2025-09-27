package com.example.inmemoriam.features.profile.domain.repository

import com.example.inmemoriam.features.profile.domain.model.ProfileModel

interface IProfileRepository {
    fun fetchData(): Result<ProfileModel>
}