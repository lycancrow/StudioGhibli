package felipe.cuervo.ghiblimovies.dependencyInjection

import dagger.Component
import felipe.cuervo.ghiblimovies.model.GhibliService
import felipe.cuervo.ghiblimovies.viewmodel.RecyclerViewModel
import javax.inject.Inject

//import felipe.cuervo.ghiblimovies.viewmodel.RecyclerViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: GhibliService)
    fun inject(viewModel: RecyclerViewModel)
}