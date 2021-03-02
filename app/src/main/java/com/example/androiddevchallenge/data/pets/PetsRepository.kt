import com.example.androiddevchallenge.model.BaseBean
import com.example.androiddevchallenge.model.PetBean
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the Pets data layer.
 */
interface PetsRepository {

    /**
     * Get a specific JetNews post.
     */
    suspend fun getPets(petId: String): BaseBean<PetBean>

    /**
     * Get JetNews posts.
     */
    suspend fun getPets(): BaseBean<List<PetBean>>

    /**
     * Observe the current favorites
     */
    //fun observeFavorites(): Flow<Set<String>>

    /**
     * Toggle a postId to be a favorite or not.
     */
    //suspend fun toggleFavorite(postId: String)
}
