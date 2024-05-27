package com.componentes.game_2048.model.persistence

import android.content.ContentValues.TAG
import androidx.annotation.OptIn
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.componentes.game_2048.model.GameState
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

object FirestoreUtil {

    private val db = Firebase.firestore
    private val gameRef = db.collection("games").document("game1")


    @OptIn(UnstableApi::class)
    fun saveGameState(gameState: GameState) {
        gameRef
            .set(gameState)
            .addOnSuccessListener { Log.d(TAG, "Game state saved") }
            .addOnFailureListener { e -> Log.w(TAG, "Error saving game state", e) }
    }

    @OptIn(UnstableApi::class)
    fun loadGameState(onSuccess: (GameState) -> Unit, onFailure: (Exception) -> Unit) {
        gameRef.get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val gameState = document.toObject(GameState::class.java) ?: GameState()
                    onSuccess(gameState)
                    Log.d(TAG, "Game state loaded")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}
