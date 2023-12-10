package io.github.peacefulprogram.easybangumi_555dy.dto

data class PageResult<T>(
    val data: List<T>,
    val page: Int,
    val hasNextPage: Boolean
)
