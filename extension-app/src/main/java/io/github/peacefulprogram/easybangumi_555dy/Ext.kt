package io.github.peacefulprogram.easybangumi_555dy

import com.heyanle.easybangumi4.source_api.entity.CartoonCover
import com.heyanle.easybangumi4.source_api.entity.CartoonCoverImpl
import io.github.peacefulprogram.easybangumi_555dy.dto.MediaCardData


fun List<MediaCardData>.toCartoonList(source: String): List<CartoonCover> {
    return map { video ->
        CartoonCoverImpl(
            id = video.id,
            source = source,
            url = "${HttpUtil.BASE_URL}/voddetail/${video.id}.html",
            title = video.title,
            intro = video.note,
            coverUrl = video.pic
        )
    }
}