import android.content.Context
import com.example.androiddevchallenge.data.pets.impl.FakePetsRepository

interface AppContainer {
    val petsRepository: PetsRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val petsRepository: PetsRepository by lazy {
        FakePetsRepository(
            resources = applicationContext.resources
        )
    }
}
