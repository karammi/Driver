package ir.brn.driver.mapper

interface ViewMapper<in P, out V> {

    fun mapToView(presentation: P): V
}