package io.github.peacefulprogram.easybangumi_555dy


import com.heyanle.easybangumi4.source_api.Source
import com.heyanle.easybangumi4.source_api.SourceResult
import com.heyanle.easybangumi4.source_api.component.ComponentWrapper
import com.heyanle.easybangumi4.source_api.component.play.PlayComponent
import com.heyanle.easybangumi4.source_api.entity.CartoonSummary
import com.heyanle.easybangumi4.source_api.entity.Episode
import com.heyanle.easybangumi4.source_api.entity.PlayLine
import com.heyanle.easybangumi4.source_api.entity.PlayerInfo
import com.heyanle.easybangumi4.source_api.withResult
import kotlinx.coroutines.Dispatchers

class Dy555PlayComponent(source: Source) : ComponentWrapper(), PlayComponent {


    override suspend fun getPlayInfo(
        summary: CartoonSummary,
        playLine: PlayLine,
        episode: Episode
    ): SourceResult<PlayerInfo> = withResult(Dispatchers.IO) {
        val episodeId = episode.id
        PlayerInfo(
            decodeType = PlayerInfo.DECODE_TYPE_HLS,
            uri = HttpUtil.queryVideoUrl(episodeId)
                ?: throw RuntimeException("视频播放链接为空")
        )
    }
}