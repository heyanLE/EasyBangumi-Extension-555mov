package io.github.peacefulprogram.easybangumi_555dy.dto


data class VideosOfType(
    val recommendVideos: List<MediaCardData>,
    val ranks: List<Pair<String, List<MediaCardData>>>,
    val videoGroups: List<Pair<String, List<MediaCardData>>>
)