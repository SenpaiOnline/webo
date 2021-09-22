package api.model

import kotlin.js.ExperimentalJsExport

@ExperimentalJsExport
interface UnsuccessfulJsonResponse : GenericJsonResponse {
    override val success: Boolean
        get() = false
}
