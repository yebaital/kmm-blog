package code.yousef.utilities

import code.yousef.models.Role
import io.smallrye.jwt.build.Jwt
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*


object TokenUtils {

    fun generateToken(username: String?, roles: Set<Role>?, duration: Long, issuer: String): String {
        val privateKeyLocation = "/privateKey.pem"
        val privateKey = privateKeyLocation.readPrivateKey()
        val claimsBuilder = Jwt.claims()
        val currentTimeInSecs = currentTimeInSecs().toLong()
        val groups: MutableSet<String> = HashSet()
        roles?.let { it -> for (role in it) groups.add(role.name.toString()) }
        println("Groups: $groups")
        claimsBuilder.issuer(issuer)
        claimsBuilder.subject(username)
        claimsBuilder.issuedAt(currentTimeInSecs)
        claimsBuilder.expiresAt(currentTimeInSecs + duration)
        claimsBuilder.groups(groups)
        return claimsBuilder.jws().keyId(privateKeyLocation).sign(privateKey)
    }


    private fun String.readPrivateKey(): PrivateKey? {
        TokenUtils::class.java.getResourceAsStream(this).use { contentIS ->
            val tmp = ByteArray(4096)
            val length = contentIS?.read(tmp)
            return length?.let { String(tmp, 0, it, charset("UTF-8")) }?.let { decodePrivateKey(it) }
        }
    }

    private fun decodePrivateKey(pemEncoded: String): PrivateKey {
        val encodedBytes = toEncodedBytes(pemEncoded)
        val keySpec = PKCS8EncodedKeySpec(encodedBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpec)
    }

    private fun toEncodedBytes(pemEncoded: String): ByteArray {
        val normalizedPem = removeBeginEnd(pemEncoded)
        return Base64.getDecoder().decode(normalizedPem)
    }

    private fun removeBeginEnd(pem: String): String {
        var normalizedPem = pem
        normalizedPem = normalizedPem.replace("-----BEGIN (.*)-----".toRegex(), "")
        normalizedPem = normalizedPem.replace("-----END (.*)----".toRegex(), "")
        normalizedPem = normalizedPem.replace("\r\n".toRegex(), "")
        normalizedPem = normalizedPem.replace("\n".toRegex(), "")
        return normalizedPem.trim { it <= ' ' }
    }

    private fun currentTimeInSecs(): Int {
        return (System.currentTimeMillis() / 1000).toInt()
    }
}