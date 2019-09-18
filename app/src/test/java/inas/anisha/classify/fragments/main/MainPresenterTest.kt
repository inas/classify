package inas.anisha.classify.fragments.main

import inas.anisha.classify.base.BaseContract
import io.mockk.MockKAnnotations
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class MainPresenterTest {

    private lateinit var presenter: MainPresenter
    private val contract: BaseContract.View = spyk()

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = MainPresenter()
        presenter.setView(contract)
    }

    @Test
    fun `Should return username empty`() {
        // When
        val error = presenter.getUsernameError("")

        // Then
        assertEquals(MainPresenter.USERNAME_EMPTY, error)
    }

    @Test
    fun `Should return username too long`() {
        // When
        val error = presenter.getUsernameError("username username username username")

        // Then
        assertEquals(MainPresenter.USERNAME_TOO_LONG, error)
    }

    @Test
    fun `Should return username valid`() {
        // When
        val error = presenter.getUsernameError("username")

        // Then
        assertEquals(MainPresenter.USERNAME_VALID, error)
    }

}