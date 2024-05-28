package com.componentes.game_2048.di

import com.componentes.game_2048.view.utils.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object FunctionalitiesModule {

    @Provides
    fun providesAddingTilesToBoard() = AddTilesInBoard()

    @Provides
    fun providesGameBoard(
        addTileCase: AddTilesInBoard
    ) = CreateGameBoard(addTileCase)

    @Provides
    fun provideIsMovingPossible() = isMovingPossible()


    @Provides
    fun provideCheckWinCondition() = CheckWinCondition()

    @Provides
    fun provideMovingTiles(
        addTileCase: AddTilesInBoard,
        possibleMoveCase: isMovingPossible,
        checkWinCondition: CheckWinCondition

    ): TileMovement {
        return TileMovement(addTileCase, possibleMoveCase, checkWinCondition)
    }

}

