package br.com.rfasioli.bootstrap.api

import io.mockk.junit5.MockKExtension
import io.mockk.junit5.MockKExtension.CheckUnnecessaryStub
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@CheckUnnecessaryStub
@Tag("Unit")
abstract class UnitTest
