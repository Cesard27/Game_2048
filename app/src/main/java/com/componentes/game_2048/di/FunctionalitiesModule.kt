package com.componentes.game_2048.di

import com.componentes.game_2048.view.utils.AddTilesInBoard
import com.componentes.game_2048.view.utils.CreateGameBoard
import com.componentes.game_2048.view.utils.TileMovement
import com.componentes.game_2048.view.utils.isMovingPossible
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object FunctionalitiesModule {

    @Provides
    fun providesAddingTilesToBoard(
    ): AddTilesInBoard {
        return AddTilesInBoard()
    }

    @Provides
    fun providesGameBoard(
        addTileCase: AddTilesInBoard
    ): CreateGameBoard {
        return CreateGameBoard(addTileCase)
    }

    @Provides
    fun provideIsMovingPossible(
    ): isMovingPossible {
        return isMovingPossible()
    }

    @Provides
    fun provideMovingTiles(
        addTileCase: AddTilesInBoard,
        possibleMoveCase: isMovingPossible
    ): TileMovement {
        return TileMovement(addTileCase, possibleMoveCase)
    }
}

