import com.iceman.network.request.MathRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun MathRequest.makeNewMathRequest(): MathRequest {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val currentDateTime = LocalDateTime.now()
    val formattedDate = currentDateTime.format(formatter)

    return MathRequest(
        id = id ,
        valorA = valorA,
        valorB = valorB,
        operation = operation,
        result = result,
        date = formattedDate
    )
}