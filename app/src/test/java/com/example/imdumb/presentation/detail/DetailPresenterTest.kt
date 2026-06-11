package com.example.imdumb.presentation.detail

import com.example.imdumb.domain.model.ActorModel
import com.example.imdumb.domain.usecase.GetMovieCreditsUseCase
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
import org.mockito.kotlin.whenever

class DetailPresenterTest {

    @Mock
    private lateinit var mockView: DetailContract.View

    @Mock
    private lateinit var mockUseCase: GetMovieCreditsUseCase

    private lateinit var presenter: DetailPresenter

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        presenter = DetailPresenter(mockUseCase)
        presenter.attachView(mockView)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun `loadActors success should show actors in view`() {
        // Given
        val movieId = 123
        val actors = listOf(ActorModel(1, "Actor Name", "Character", "/path.jpg"))
        whenever(mockUseCase.execute(movieId)).thenReturn(Single.just(actors))

        // When
        presenter.loadActors(movieId)

        // Then
        verify(mockView).showActors(actors)
    }

    @Test
    fun `loadActors failure should show error in view`() {
        // Given
        val movieId = 123
        val errorMessage = "Network Error"
        whenever(mockUseCase.execute(movieId)).thenReturn(Single.error(Exception(errorMessage)))

        // When
        presenter.loadActors(movieId)

        // Then
        verify(mockView).showError(errorMessage)
    }
}
