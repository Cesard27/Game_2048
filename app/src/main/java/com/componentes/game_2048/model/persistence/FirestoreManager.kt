import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.componentes.game_2048.model.GameState
import com.google.firebase.firestore.firestore

object FirestoreManager {

    private val collection = "gameStates"
    private val document = "currentGameState"

    private val db: FirebaseFirestore by lazy { Firebase.firestore }

    fun saveGameState(gameState: GameState) {
        val db = com.google.firebase.Firebase.firestore
        val gameStateRef = db
            .collection(collection)
            .document(document)

        val gameStateMap = gameState.toMap()

        gameStateRef.set(gameStateMap)
            .addOnSuccessListener {
                Log.d("guardando", "Game state saved successfully")
            }
            .addOnFailureListener { e ->
                Log.w("guardando", "Error saving game state", e)
            }
    }

    fun loadGameState(onSuccess: (GameState) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collection)
            .document(document)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    try {
                        val gameState = GameState.fromMap(document.data!!)
                        onSuccess(gameState)
                    } catch (e: Exception) {
                        onFailure(e)
                    }
                } else {
                    onFailure(Exception("GameState document not found"))
                }
            }
            .addOnFailureListener { onFailure(it) }
    }





}
