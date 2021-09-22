package api.model

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
interface EvolveRowsNumberModel : GenericJsonResponse {
    override val success: Boolean get() = true
    val rowsNumber: Number
}
