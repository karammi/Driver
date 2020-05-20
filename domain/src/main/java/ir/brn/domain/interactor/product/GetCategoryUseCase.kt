package ir.brn.domain.interactor.product

import io.reactivex.Observable
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.domain.interactor.ObservableUseCase
import ir.brn.domain.model.product.Category
import ir.brn.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(
        private var productRepository: ProductRepository,
        postExecutionThread: PostExecutionThread) : ObservableUseCase<List<Category>, Nothing?>(postExecutionThread) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Category>> {
        return productRepository.getCategories()
    }
}