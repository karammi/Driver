package ir.brn.domain.interactor.product

import io.reactivex.Observable
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.domain.interactor.ObservableUseCase
import ir.brn.domain.model.product.Category
import ir.brn.domain.model.product.Product
import ir.brn.domain.repository.ProductRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
        private var productRepository: ProductRepository,
        postExecutionThread: PostExecutionThread) : ObservableUseCase<Product, GetProductDetailUseCase.Params?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<Product> {
        if (params == null) throw IllegalArgumentException("Params can not be null!")
        return productRepository.getProductDetail(params.productId)
    }

    data class Params constructor(val productId: Int) {
        companion object {
            fun forProductDetail(productId: Int): Params {
                return Params(productId)
            }
        }
    }
}