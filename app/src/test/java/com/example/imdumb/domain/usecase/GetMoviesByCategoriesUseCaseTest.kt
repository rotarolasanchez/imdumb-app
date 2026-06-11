package com.example.imdumb.domain.usecase

import com.example.imdumb.domain.model.CategoryModel
import com.example.imdumb.domain.repository.MovieRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetMoviesByCategoriesUseCaseTest {

    @Mock
    private lateinit var mockRepository: MovieRepository

    private lateinit var useCase: GetMoviesByCategoriesUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMoviesByCategoriesUseCase(mockRepository)
    }

    @Test
    fun `execute should return categories from repository`() {
        // Given
        val categories = listOf(CategoryModel(1, "Popular", emptyList()))
        whenever(mockRepository.getMoviesByCategories()).thenReturn(Single.just(categories))

        // When
        val result = useCase.execute()

        // Then
        result.test()
            .assertValue(categories)
            .assertNoErrors()
            .assertComplete()
    }
}
