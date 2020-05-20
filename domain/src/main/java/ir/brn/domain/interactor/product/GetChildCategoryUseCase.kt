package ir.brn.domain.interactor.product

import io.reactivex.Observable
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.domain.interactor.ObservableUseCase
import ir.brn.domain.model.product.Category
import ir.brn.domain.repository.ProductRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetChildCategoryUseCase @Inject constructor(
        private var productRepository: ProductRepository,
        postExecutionThread: PostExecutionThread) : ObservableUseCase<List<Category>, GetChildCategoryUseCase.Params?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<List<Category>> {
        if (params == null) throw IllegalArgumentException("Params can not be null!")
        return productRepository.getChildCategories(params.categoryId)
    }

    data class Params constructor(val categoryId: Int) {
        companion object {
            fun forProductRepository(categoryId: Int): Params {
                return Params(categoryId)
            }
        }
    }
}