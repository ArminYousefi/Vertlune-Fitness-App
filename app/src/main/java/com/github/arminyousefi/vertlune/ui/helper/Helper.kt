package com.github.arminyousefi.vertlune.ui.helper

import com.github.arminyousefi.vertlune.R

private val ImageResources = mapOf(
    "blue_short" to R.drawable.blue_short,
    "green_tshirt" to R.drawable.green_tshirt,
    "long_sleeve" to R.drawable.long_sleeve,
    "military_leg" to R.drawable.military_leg,
    "pink_top" to R.drawable.pink_top,
    "red_short" to R.drawable.red_short,
    "sweat_gray" to R.drawable.sweat_gray,
    "banner" to R.drawable.banner,
    "main_bg" to R.drawable.main_bg
)

fun getDrawableId(imagePath: String): Int {
    val fileName = imagePath.substringAfterLast("/").substringBeforeLast(".")
    return ImageResources[fileName] ?: R.drawable.ic_launcher_foreground
}