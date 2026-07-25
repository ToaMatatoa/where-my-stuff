package com.matatoa.wheremystuff.domain.mapper

import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import com.matatoa.wheremystuff.domain.model.PlaceData

fun PlaceEntity.toPlaceData(): PlaceData =
    PlaceData(
        id = id,
        name = name,
        iconName = iconName
    )

fun PlaceData.toPlaceEntity(): PlaceEntity =
    PlaceEntity(
        name = name,
        iconName = iconName
    )
