package io.github.peacefulprogram.easybangumi_555dy

import com.heyanle.easybangumi4.source_api.Source
import com.heyanle.easybangumi4.source_api.SourceResult
import com.heyanle.easybangumi4.source_api.component.ComponentWrapper
import com.heyanle.easybangumi4.source_api.component.search.SearchComponent
import com.heyanle.easybangumi4.source_api.entity.CartoonCover
import com.heyanle.easybangumi4.source_api.withResult
import kotlinx.coroutines.Dispatchers
import java.net.URLEncoder

class Dy555SearchComponent(source: Source) : ComponentWrapper(), SearchComponent {
    override fun getFirstSearchKey(keyword: String): Int = 1

    override suspend fun search(
        pageKey: Int,
        keyword: String
    ): SourceResult<Pair<Int?, List<CartoonCover>>> = withResult(Dispatchers.IO) {
        val kw = URLEncoder.encode(keyword, Charsets.UTF_8.name())
        val pageResult = HttpUtil.searchVideo(
            "$kw-------------".split('-'), pageKey
        )
        val nextPage = if (pageResult.hasNextPage) pageResult.page + 1 else null
        Pair(nextPage, pageResult.data.toCartoonList(source = source.key))
    }
}