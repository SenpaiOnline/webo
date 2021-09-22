package online.senpai.webo.repository

import arrow.typeclasses.Show

sealed class EvolveDataProviderError(val errorMessage: String) {
    abstract val show: Show<*>
    object CharacterDoesNotExist : EvolveDataProviderError(
        errorMessage = "The requested character doesn't exist in the database."
    ) {
        override val show: Show<CharacterDoesNotExist> = Show { errorMessage }
    }
    object CharacterHasNoData : EvolveDataProviderError(
        errorMessage = "The requested character has no data."
    ) {
        override val show: Show<CharacterHasNoData> = Show { errorMessage }
    }

    data class CouldNotConnectToDatabase(val throwable: Throwable) : EvolveDataProviderError(
        errorMessage = "Couldn't connect to the database to retrieve data."
    ) {
        override val show: Show<CouldNotConnectToDatabase> = Show { "$errorMessage\n$throwable" }
    }
}


