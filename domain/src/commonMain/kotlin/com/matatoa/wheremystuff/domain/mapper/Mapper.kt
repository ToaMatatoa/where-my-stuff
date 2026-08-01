package com.matatoa.wheremystuff.domain.mapper

import com.matatoa.wheremystuff.core.database.entity.PlaceEntity
import com.matatoa.wheremystuff.core.database.entity.StuffEntity
import com.matatoa.wheremystuff.core.database.entity.SubPlaceEntity
import com.matatoa.wheremystuff.domain.model.PlaceData
import com.matatoa.wheremystuff.domain.model.StuffData
import com.matatoa.wheremystuff.domain.model.SubPlaceData

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

fun SubPlaceEntity.toSubPlaceData(): SubPlaceData =
    SubPlaceData(
        id = id,
        placeId = placeId,
        name = name
    )

/** Leaves the id at its default so Room generates one on insert. */
fun SubPlaceData.toSubPlaceEntity(): SubPlaceEntity =
    SubPlaceEntity(
        placeId = placeId,
        name = name
    )

fun StuffEntity.toStuffData(): StuffData =
    StuffData(
        id = id,
        subPlaceId = subPlaceId,
        name = name
    )

/** Leaves the id at its default so Room generates one on insert. */
fun StuffData.toStuffEntity(): StuffEntity =
    StuffEntity(
        subPlaceId = subPlaceId,
        name = name
    )
