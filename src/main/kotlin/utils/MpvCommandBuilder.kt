package zechs.zplex.connect.utils


fun getMpvCommand(
    title: String,
    watchUrl: String,
    token: String
): String {
    val sanitizedTitle = title.replace("'", "").replace("!", "_").replace("\"", "")
    return "mpv --http-header-fields=\"Authorization: Bearer $token\" \"$watchUrl\" --force-media-title=\"$sanitizedTitle\""
}