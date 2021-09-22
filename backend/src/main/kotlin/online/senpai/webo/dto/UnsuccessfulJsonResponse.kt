package online.senpai.webo.dto

data class UnsuccessfulJsonResponse(
    override val message: String
) : GenericJsonResponse {
    override val success: Boolean = false
}
