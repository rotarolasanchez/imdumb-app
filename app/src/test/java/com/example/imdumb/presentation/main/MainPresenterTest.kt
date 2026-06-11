package com.example.imdumb.presentation.main

import com.example.imdumb.domain.model.CategoryModel
import com.example.imdumb.domain.usecase.GetMoviesByCategoriesUseCase
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

class MainPresenterTest {

    @Mock
    private lateinit var mockView: MainContract.View

    @Mock
    private lateinit var mockUseCase: GetMoviesByCategoriesUseCase

    private lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        presenter = MainPresenter(mockUseCase)
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun `loadMovies success should show movies in view`() {
        // Given
        val categories = listOf(CategoryModel(1, "Test Category", emptyList()))
        whenever(mockUseCase.execute()).thenReturn(Single.just(categories))

        // When
        presenter.loadMovies()

        // Then
        verify(mockView).showLoading()
        verify(mockView).hideLoading()
        verify(mockView).showMovies(categories)
    }

    @Test
    fun `loadMovies failure should show error in view`() {
        // Given
        val errorMessage = "Error message"
        whenever(mockUseCase.execute()).thenReturn(Single.error(Exception(errorMessage)))

        // When
        presenter.loadMovies()

        // Then
        verify(mockView).showLoading()
        verify(mockView).hideLoading()
        verify(mockView).showError(errorMessage)
    }
}
