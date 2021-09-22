package online.senpai.webo.model.evolve

data class EvolveLinesModel(
    val lines: Iterable<EvolveLineModel>
) {
    data class EvolveLineModel(
        val name: String,
        val text: String,
        val files: Iterable<String>
    )
}
