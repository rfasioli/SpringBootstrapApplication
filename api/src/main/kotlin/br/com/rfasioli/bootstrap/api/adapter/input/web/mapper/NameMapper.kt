package br.com.rfasioli.bootstrap.api.adapter.input.web.mapper

import br.com.rfasioli.bootstrap.api.domain.model.Name

fun Name.Companion.splitFromString(source: String): Name =
    toTriple(source.split(" "))
        .let { Name(it.first, it.second, it.third) }

private fun toTriple(source: List<String>): Triple<String, String, String> {
    val mutableList = source.toMutableList()

    mutableList.removeFirst()
    if (mutableList.isNotEmpty()) {
        mutableList.removeLast()
    }

    return Triple(
        source.first(),
        mutableList.joinToString(separator = " "),
        if (source.size > 1) {
            source.last()
        } else {
            ""
        },
    )
}
