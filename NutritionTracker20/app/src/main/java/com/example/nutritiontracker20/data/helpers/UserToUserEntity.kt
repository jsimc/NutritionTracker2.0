package com.example.nutritiontracker20.data.helpers

import com.example.nutritiontracker20.data.entities.UserEntity
import com.example.nutritiontracker20.data.models.User

fun userToUserEntity(user: User) : UserEntity {
    return UserEntity(user.username,
        user.password,
        user.age,
        user.height,
        user.weight,
        user.gender,
        user.weeklyActivity,
        user.suggestedKcal)
}

fun userEntityToUser(userEntity: UserEntity) : User {
    return User(userEntity.username,
        userEntity.password,
        userEntity.age,
        userEntity.height,
        userEntity.weight,
        userEntity.gender,
        userEntity.weeklyActivity,
        userEntity.suggestedKcal)
}
