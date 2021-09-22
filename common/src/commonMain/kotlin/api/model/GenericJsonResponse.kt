package api.model

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
interface GenericJsonResponse {
    val success: Boolean
    val message: String
}
