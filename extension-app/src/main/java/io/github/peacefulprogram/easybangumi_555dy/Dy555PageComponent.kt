package io.github.peacefulprogram.easybangumi_555dy

import com.heyanle.easybangumi4.source_api.Source
import com.heyanle.easybangumi4.source_api.component.ComponentWrapper
import com.heyanle.easybangumi4.source_api.component.page.PageComponent
import com.heyanle.easybangumi4.source_api.component.page.SourcePage
import com.heyanle.easybangumi4.source_api.withResult
import io.github.peacefulprogram.easybangumi_555dy.dto.MediaCardData
import io.github.peacefulprogram.easybangumi_555dy.dto.VideosOfType
import kotlinx.coroutines.Dispatchers

class Dy555PageComponent(source: Source) : ComponentWrapper(), PageComponent {
    override fun getPages(): List<SourcePage> {
        val result = mutableListOf<SourcePage>()
        val homePage = SourcePage.Group("首页", false) {
            withResult(Dispatchers.IO) {
                HttpUtil.getHomePage().toSingleCartoonPage(source = source.key)
            }
        }
        result.add(homePage)
        val netflix = SourcePage.SingleCartoonPage.WithCover("Netflix", { 1 }) { page ->
            withResult(Dispatchers.IO) {
                val videoPage = HttpUtil.getNetflix(page)
                val nextPage = if (videoPage.hasNextPage) videoPage.page + 1 else null
                Pair(nextPage, videoPage.data.toCartoonList(source = source.key))
            }
        }
        result.add(netflix)
        listOf(
            Pair("动漫", 4),
            Pair("电影", 1),
            Pair("连续剧", 2),
            Pair("综艺", 3)
        ).forEach { (groupName, groupId) ->
            val page = SourcePage.Group(groupName, false) {
                withResult(Dispatchers.IO) {
                    HttpUtil.getVideoPageByType(groupId).toSingleCartoonPage(source = source.key)
                }
            }
            result.add(page)
        }

        return result
    }

    private fun VideosOfType.toSingleCartoonPage(source: String): List<SourcePage.SingleCartoonPage> {
        val result = mutableListOf<SourcePage.SingleCartoonPage>()
        result.add(recommendVideos.toSingleCartoonPage("推荐", source = source))
        ranks.forEach { (rankName, videos) ->
            result.add(videos.toSingleCartoonPage(rankName, source = source))
        }
        videoGroups.forEach { (groupName, videos) ->
            result.add(videos.toSingleCartoonPage(groupName, source = source))
        }
        return result
    }

    private fun List<MediaCardData>.toSingleCartoonPage(
        groupName: String,
        source: String
    ): SourcePage.SingleCartoonPage = SourcePage.SingleCartoonPage.WithCover(groupName, { 0 }) {
        withResult {
            Pair(null, this.toCartoonList(source))
        }
    }
}