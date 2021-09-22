package api.model

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

abstract class SuccessfulJsonResponse(
    override val message: String = ""
) : GenericJsonResponse {
    override val success: Boolean = true
}
