package com.example.inmemoriam.features.profile.data.repository

import com.example.inmemoriam.features.profile.domain.model.Cellphone
import com.example.inmemoriam.features.profile.domain.model.Email
import com.example.inmemoriam.features.profile.domain.model.Name
import com.example.inmemoriam.features.profile.domain.model.PathUrl
import com.example.inmemoriam.features.profile.domain.model.ProfileModel
import com.example.inmemoriam.features.profile.domain.model.Summary
import com.example.inmemoriam.features.profile.domain.repository.IProfileRepository

class ProfileRepository: IProfileRepository {
    override fun fetchData(): Result<ProfileModel> {
        return Result.success(
            ProfileModel(
                name = Name("Homero J. Simpson"),
                email = Email("homero.simpson@springfieldmail.com"),
                cellphone = Cellphone("+1 (939) 555-7422"),
                pathUrl = PathUrl("https://www.viaempresa.cat/uploads/s1/43/99/69/homer.jpg"),
                summary = Summary("Ciudadano de Springfield y dedicado inspector de seguridad en la Planta Nuclear.")
            )
        )
    }
}
