package code.yousef.utilities

import com.github.slugify.Slugify
import java.util.*

fun String.slugify(): String {
    val slg = Slugify.builder().transliterator(true).build();
    return slg.slugify(this)
}

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it ->
        it.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
