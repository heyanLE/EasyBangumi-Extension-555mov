package io.github.peacefulprogram.easybangumi_555dy

import com.heyanle.extension_api.ExtensionIconSource
import com.heyanle.extension_api.ExtensionSource
import kotlin.reflect.KClass

class Dy555ApiSource : ExtensionSource(), ExtensionIconSource {
    override val describe: String?
        get() = label
    override val label: String
        get() = "555电影"
    override val version: String
        get() = "1.0"
    override val versionCode: Int
        get() = 1
    override val sourceKey: String
        get() = "io.github.peacefulprogram.easybangumi_555dy"


    override fun register(): List<KClass<*>> {
        return listOf(
            Dy555DetailComponent::class,
            Dy555PageComponent::class,
            Dy555SearchComponent::class,
            Dy555PlayComponent::class,
        )
    }

    override fun getIconResourcesId(): Int = R.drawable.dy555
}