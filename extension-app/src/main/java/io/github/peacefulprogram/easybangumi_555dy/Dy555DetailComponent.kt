package io.github.peacefulprogram.easybangumi_555dy

import com.heyanle.easybangumi4.source_api.SourceResult
import com.heyanle.easybangumi4.source_api.component.ComponentWrapper
import com.heyanle.easybangumi4.source_api.component.detailed.DetailedComponent
import com.heyanle.easybangumi4.source_api.entity.Cartoon
import com.heyanle.easybangumi4.source_api.entity.CartoonImpl
import com.heyanle.easybangumi4.source_api.entity.CartoonSummary
import com.heyanle.easybangumi4.source_api.entity.Episode
import com.heyanle.easybangumi4.source_api.entity.PlayLine
import com.heyanle.easybangumi4.source_api.utils.api.OkhttpHelper
import com.heyanle.easybangumi4.source_api.withResult
import io.github.peacefulprogram.easybangumi_555dy.dto.VideoDetailData
import kotlinx.coroutines.Dispatchers

class Dy555DetailComponent(
    private val okhttpHelper: OkhttpHelper,
) : ComponentWrapper(), DetailedComponent {
    override suspend fun getAll(summary: CartoonSummary): SourceResult<Pair<Cartoon, List<PlayLine>>> {
        return withResult(Dispatchers.IO) {
            val videoDetail = HttpUtil.getDetailPage(summary.id)
            Pair(videoDetail.toCartoon(source.key), videoDetail.toPlayLine())
        }
    }

    private fun VideoDetailData.toCartoon(source: String) = CartoonImpl(
        id = id,
        source = source,
        url = "${HttpUtil.BASE_URL}/voddetail/${id}.html",
        title = title,
        genre = tags.joinToString(", ") { it.name },
        coverUrl = pic,
        description = desc
    )

    private fun VideoDetailData.toPlayLine() = playLists.map { (playlistName, episodes) ->

       val res = arrayListOf<Episode>()
        res += episodes.mapIndexed { index, episode ->
            Episode(episode.id, episode.name, index)
        }
        PlayLine(
            id = playlistName,
            label = playlistName,
            episode = res
        )
    }

    override suspend fun getDetailed(summary: CartoonSummary): SourceResult<Cartoon> {
        return withResult(Dispatchers.IO) {
            HttpUtil.getDetailPage(summary.id).toCartoon(source.key)
        }
    }

    override suspend fun getPlayLine(summary: CartoonSummary): SourceResult<List<PlayLine>> {
        return withResult(Dispatchers.IO) {
            HttpUtil.getDetailPage(summary.id).toPlayLine()
        }
    }
}