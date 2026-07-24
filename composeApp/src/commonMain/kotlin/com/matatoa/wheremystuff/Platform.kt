package com.matatoa.wheremystuff

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform