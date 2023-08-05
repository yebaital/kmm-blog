package code.yousef.utilities

import com.github.slugify.Slugify

fun String.slugify(): String {
    val slg = Slugify.builder().transliterator(true).build();
    return slg.slugify(this)
}
